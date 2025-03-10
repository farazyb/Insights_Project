package com.sea.reporter.froms;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VasForm {
    private String accountNumber;
    private String description;
    private int percentage;
    private String code;


    private VasForm(String code, String accountNumber, int percentage, String description) {
        this.code = code;
        this.accountNumber = accountNumber;
        this.description = description;
        this.percentage = percentage;
    }

    public VasForm(String code, String accountNumber, String description) {
        this.accountNumber = accountNumber;
        this.description = description;
        this.code = code;
    }

    public static List<VasForm> getInstance(List<String> vasInformation) throws Exception {
        List<VasForm> vasForms = new ArrayList<>();
        for (String s : vasInformation) {
            vasForms.add(pars(s));
        }
        return vasForms;
    }

    private static VasForm pars(String vasString) throws Exception {
        VasForm vasForm;
        String[] s1 = vasString.split("\\|?\\|");
        if (s1.length == 4) {
            vasForm = new VasForm(s1[0], s1[1], Integer.parseInt(s1[2]), s1[3]);
        } else if (s1.length == 3) {
            vasForm = new VasForm(s1[0], s1[1], 100, s1[2]);
        } else throw new Exception("VAS String is not correct");
        return vasForm;
    }


}
