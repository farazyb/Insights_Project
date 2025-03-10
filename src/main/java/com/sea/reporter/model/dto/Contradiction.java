package com.sea.reporter.model.dto;

import com.sea.reporter.util.comparison.ComparisonList;
import com.sea.reporter.util.Tools;
import lombok.Data;

import java.io.File;
import java.io.IOException;

/**
 * Abstract base class for comparing two different types of data collections.
 * Provides functionality to identify differences and calculate sums between two data sets.
 *
 * @param <T> The type of the first data collection
 * @param <Z> The type of the second data collection
 */
@Data
public abstract class Contradiction<T, Z> {
    protected ComparisonList<T> FirstData;
    protected ComparisonList<Z> SecondDate;
    protected ComparisonList<Different> difference;
    protected long differentAmount;
    protected long firstDataSum;
    protected long secondDataSum;

    /**
     * Creates a new Contradiction instance with two data collections to compare.
     *
     * @param firstData The first collection of data
     * @param secondDate The second collection of data
     */
    public Contradiction(ComparisonList<T> firstData, ComparisonList<Z> secondDate) {
        FirstData = firstData;
        SecondDate = secondDate;
        difference = new ComparisonList<>(Different.class);
    }

    /**
     * Abstract method to be implemented by subclasses to define the comparison logic.
     *
     * @throws Exception if an error occurs during comparison
     */
    public abstract void compare() throws Exception;

    /**
     * Calculates the sum of amounts in both collections and their difference.
     *
     * @return true if calculation was successful
     * @throws Exception if an error occurs during calculation
     */
    public boolean Calculation() throws Exception {
        firstDataSum = FirstData.sumAmount();
        secondDataSum = SecondDate.sumAmount();
        differentAmount = firstDataSum - secondDataSum;
        return true;
    }

    /**
     * Calculates the sum of amounts in both collections using specified field names.
     *
     * @param firstAmountFieldName The field name for amount in the first collection
     * @param secondAmountFieldName The field name for amount in the second collection
     * @return true if calculation was successful
     * @throws Exception if an error occurs during calculation
     */
    public boolean Calculation(String firstAmountFieldName, String secondAmountFieldName) throws Exception {
        firstDataSum = FirstData.sumAmount(firstAmountFieldName);
        secondDataSum = SecondDate.sumAmount(secondAmountFieldName);
        differentAmount = firstDataSum - secondDataSum;
        return true;
    }

    /**
     * Calculates the total amount of differences for a specific type.
     *
     * @param type The type of difference to sum
     * @return The total amount of differences for the specified type
     */
    public long getDiffTypeTotalAmount(String type) {
        long sumType = 0;
        for (Different different : this.difference) {
            if (different.getType().equals(type)) {
                sumType = sumType + different.getAmount();
            }
        }
        return sumType;
    }

    /**
     * Checks if the two collections match (no differences found).
     *
     * @return true if there are no differences between the collections
     */
    public boolean isMatch() {
        return difference.isEmpty() || difference.size() == 0;
    }


    public void createFile(String firstPath, String secondPath, String diffPath) throws Exception {
        Tools tools = new Tools();
        try {
            File file;
            if (FirstData.size() > 0) {
                file = tools.createFile(firstPath);
                tools.createExcel(file, FirstData);
            }
            if (SecondDate.size() > 0) {
                file = tools.createFile(secondPath);
                tools.createExcel(file, SecondDate);
            }
            if (difference.size() > 0) {
                file = tools.createFile(diffPath);
                tools.createExcel(file, difference);
            }
        } catch (IOException | IllegalAccessException e) {
            throw new Exception(e);
        }
    }


}
