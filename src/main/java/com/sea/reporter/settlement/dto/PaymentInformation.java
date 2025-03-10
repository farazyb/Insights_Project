package com.sea.reporter.settlement.dto;

import com.sea.reporter.froms.UDForm;
import com.sea.reporter.froms.VasForm;
import com.sea.reporter.util.Tools;
import com.sea.reporter.util.comparison.ComparisonList;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static com.sea.reporter.settlement.dto.ApportionmentWithPercentage.checkNull;

/**
 * Data transfer object representing payment information for settlement processing.
 * Contains payment code, apportionment details, and total amount information.
 */
@Data
public class PaymentInformation {
    private String code;
    private List<Apportionment> apportionment;
    private long sumAmount;

    /**
     * Private constructor to enforce factory method usage.
     */
    private PaymentInformation() {
    }

    /**
     * Creates a list of PaymentInformation instances from UDForm data and VAS output file.
     *
     * @param udForms List of UDForm objects containing transaction data
     * @param vasOut File containing VAS output data
     * @return List of PaymentInformation objects
     * @throws Exception if there's an error processing the data
     */
    public static List<PaymentInformation> getInstance(ComparisonList<UDForm> udForms, File vasOut) throws Exception {
        List<PaymentInformation> paymentInformationList = new ArrayList<>();
        ApportionmentType apportionmentType = new ApportionmentWithPercentage();
        Map<String, List<Apportionment>> vasInformation = apportionmentType.fetchInformation(vasOut);
        Map<String, Long> eachCodeSumAMount = computeUdForms(udForms);
        AtomicBoolean status = new AtomicBoolean(true);
        AtomicReference<Exception> exception = new AtomicReference<>();
        eachCodeSumAMount.forEach((key, value) -> {
            if (!status.get())
                return;
            PaymentInformation paymentInformation = new PaymentInformation();
            try {
                if (!vasInformation.containsKey(key)) {
                    paymentInformation.setCode(key);
                    paymentInformation.setSumAmount(value);
                    paymentInformation.setApportionment(null);
                    return;
                }
                paymentInformation.setApportionment(vasInformation.get(key));
                paymentInformation.setCode(key);
                paymentInformation.setSumAmount(value);
                paymentInformationList.add(paymentInformation);
            } catch (Exception e) {
                exception.set(e);
                status.set(false);
            }
        });
        if (!status.get()) {
            throw new Exception(exception.get());
        }
        return paymentInformationList;
    }

    private static Map<String, Long> computeUdForms(ComparisonList<UDForm> udForms) {
        Map<String, Long> eachCodeSumAMount = new HashMap<>();
        for (UDForm udForm : udForms) {
            String subCode = udForm.getCode1().substring(0, 8);
            if (eachCodeSumAMount.containsKey(subCode)) {
                long amount = eachCodeSumAMount.get(subCode) + udForm.getAmount();
                eachCodeSumAMount.put(subCode, amount);
            } else {
                eachCodeSumAMount.put(subCode, udForm.getAmount());
            }
        }
        return eachCodeSumAMount;
    }


    public boolean isPercentageOfApportionmentCorrect(List<Apportionment> apportionment) {
        if (apportionment == null || apportionment.size() == 0) {
            return false;
        }
        int sum = 0;
        for (Apportionment x : apportionment) {
            if (x == null)
                continue;
            sum = x.getPercentage() + sum;
        }
        return sum == 100;
    }


    public boolean isApportionmentCorrect(List<Apportionment> apportionment) {
        if (apportionment == null || apportionment.size() == 0) {
            return false;
        }
        long sumAmountWithPercentage = 0;
        for (Apportionment x : apportionment) {
            if (x == null)
                continue;
            sumAmountWithPercentage = ((sumAmount * x.getPercentage()) / 100) + sumAmountWithPercentage;
        }
        return sumAmountWithPercentage <= sumAmount;
    }

    public List<Apportionment> getApportionment() {
        return apportionment;
    }

    public void setApportionment(List<Apportionment> apportionment) throws Exception {
        if (!isPercentageOfApportionmentCorrect(apportionment)) {
            throw new Exception("The Percentage of Apportionment is not 100% {"+apportionment+"}");
        }
        if (!isApportionmentCorrect(apportionment)) {
            throw new Exception("The Apportionment is not valid  Sum amount is not equal with the one by one computing");
        }
        this.apportionment = apportionment;
    }
}
