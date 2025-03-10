package com.sea.reporter.settlement.dto;

import com.sea.reporter.froms.VasForm;
import com.sea.reporter.util.Tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApportionmentWithPercentage implements ApportionmentType {


    @Override
    public Map<String, List<Apportionment>> fetchInformation(File vasOut) throws Exception {
        List<VasForm> unCorrectVas = new ArrayList<>();
        Map<String, List<Apportionment>> vasInformation = new HashMap<>();
        List<VasForm> vasForms = VasForm.getInstance(Tools.readFileString(vasOut));
        for (VasForm vasForm : vasForms) {
            if (vasInformation.containsKey(vasForm.getCode())) {
                List<Apportionment> apportionments = vasInformation.get(vasForm.getCode());
                apportionments.add(Apportionment.getInstance(vasForm));
                if (!check(apportionments)) {
                    unCorrectVas.add(vasForm);
                }
                vasInformation.put(vasForm.getCode(), apportionments);
            } else {
                List<Apportionment> apportionments = new ArrayList<>();
                apportionments.add(Apportionment.getInstance(vasForm));
                vasInformation.put(vasForm.getCode(), apportionments);
            }
        }
        if (unCorrectVas.size() > 0) {

            throw new Exception("The Percentage of Apportionment is over 100% {"+unCorrectVas.toString()+"}" );
        }
        return vasInformation;
    }

    public static Map<String, List<Apportionment>> getFetchedInformation(File vasOut) throws Exception {
        return new ApportionmentWithPercentage().fetchInformation(vasOut);
    }


    public boolean check(List<Apportionment> apportionment) {
        return checkNull(apportionment);
    }

    static boolean checkNull(List<Apportionment> apportionment) {
        if (apportionment == null || apportionment.size() == 0) {
            return false;
        }
        int sum = 0;
        for (Apportionment x : apportionment) {
            if (x == null)
                continue;
            sum = x.getPercentage() + sum;
        }
        return sum <= 100;
    }


}
