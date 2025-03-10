package com.sea.reporter.settlement.dto;

import com.sea.reporter.froms.VasForm;
import com.sea.reporter.util.Tools;

import java.io.File;
import java.util.*;

public class ApportionmentWithOutPercentage implements ApportionmentType {


    @Override
    public Map<String, List<Apportionment>> fetchInformation(File vasOut) throws Exception {
        List<VasForm> unCorrectVas = new ArrayList<>();
        Map<String, List<Apportionment>> vasInformation = new HashMap<>();
        List<VasForm> vasForms = VasForm.getInstance(Tools.readFileString(vasOut));
        if (vasForms == null || vasForms.size() == 0) {
            return null;
        }
        for (VasForm vasForm : vasForms) {
            Apportionment apportionment = Apportionment.getInstance(vasForm);
            vasInformation.put(vasForm.getCode(), Arrays.asList(new Apportionment[]{apportionment}));
        }

        return vasInformation;
    }

    @Override
    public boolean check(List<Apportionment> apportionment) {
        return true;
    }

    public static Map<String, List<Apportionment>> getFetchedInformation(File vasOut) throws Exception {
        return new ApportionmentWithOutPercentage().fetchInformation(vasOut);
    }
}
