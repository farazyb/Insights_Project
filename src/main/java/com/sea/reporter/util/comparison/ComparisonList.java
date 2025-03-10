package com.sea.reporter.util.comparison;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.sea.reporter.util.Tools;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A generic list implementation that extends ArrayList to provide comparison functionality.
 * This class is designed to handle collections of objects that need to be compared,
 * particularly focusing on amount-based comparisons.
 *
 * @param <E> The type of elements in the list
 */
public class ComparisonList<E> extends ArrayList<E> {

    private final Class<E> type;
    private Field amountField;
    private String fieldName = "AMOUNT";

    /**
     * Creates a new ComparisonList with the specified type.
     *
     * @param c The class type of elements to be stored in the list
     */
    public ComparisonList(Class c) {
        this.type = c;
    }

    /**
     * Creates a new ComparisonList with initial data and specified type.
     *
     * @param listData Initial list of elements
     * @param c The class type of elements to be stored in the list
     */
    public ComparisonList(List<E> listData, Class c) {
        this.type = c;
        this.addAll(listData);
    }

    /**
     * Retrieves the amount field from the specified class type.
     * The field name must contain the specified fieldName (default: "AMOUNT").
     *
     * @param fieldName The name of the field to search for
     * @throws Exception if no field containing the specified name is found
     */
    private void getAmountField(String fieldName) throws Exception {
        boolean haveAmountCharacter = false;
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().toUpperCase().contains(fieldName)) {
                haveAmountCharacter = true;
                amountField = field;
                break;
            }
        }
        if (!haveAmountCharacter) {
            throw new Exception("List Must have property with contain Word \" " + fieldName + "\"  for use This method");
        }
    }

    /**
     * Adds all elements from the specified collection to this list.
     *
     * @param collection The collection of elements to add
     * @return true if the list was modified as a result of the operation
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return super.addAll(collection);
    }

    /**
     * Calculates the sum of all amount fields in the list.
     * The amount field is determined by the fieldName property (default: "AMOUNT").
     *
     * @return The sum of all amount values
     * @throws Exception if the amount field cannot be found or accessed
     */
    public long sumAmount() throws Exception {
        getAmountField(fieldName);
        long sum = 0l;
        for (E e : this) {
            Field amount = e.getClass().getDeclaredField(amountField.getName());
            amount.setAccessible(true);
            sum = new Long((Long) amount.get(e)) + sum;
        }
        return sum;
    }

    public long sumAmount(String fieldName) throws Exception {
        this.fieldName = fieldName;
        return sumAmount();
    }

    // TODO: 5/30/2022 Start From here
    public boolean listToText(String path) {
        try {
            File file = Tools.createFile(path);
           return Tools.createTextFile(file, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean listToExcel(String path) {
        try {
            File file = Tools.createFile(path);
            Tools.createExcel(file, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean listToExcel() throws IOException {
        return listToExcel(Tools.createFile(System.getProperty("user.dir") + File.separator + type.getName() + ".csv").getPath());
    }

    public void gsonFileToList(String path) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(path));
        if (this.size() > 0)
            this.removeAll(this);
        this.addAll(gson.fromJson(reader, setModelAndGetCorrespondingList2(type)));
        reader.close();
    }

    public void listToGsonFile(String path) throws IOException {
        try {
            Writer writer = new FileWriter(Tools.createFile(path));
            Gson gson = new GsonBuilder().create();
            gson.toJson(this, writer);
            writer.close();

        } catch (JsonIOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ComparisonList<E> clone() {
        Gson gson = new GsonBuilder().create();
        return new ComparisonList<E>(gson.fromJson(gson.toJson(this), setModelAndGetCorrespondingList2(type)), type);
    }

    private Type setModelAndGetCorrespondingList2(Class<?> typeArgument) {
        return TypeToken.getParameterized(ArrayList.class, typeArgument).getType();
    }
}
