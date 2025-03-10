package com.sea.reporter.settlement;

import com.sea.reporter.froms.UDForm;
import com.sea.reporter.settlement.dto.SettlementInfo;
import com.sea.reporter.settlement.dto.Apportionment;
import com.sea.reporter.settlement.dto.PaymentInformation;
import com.sea.reporter.util.Tools;
import com.sea.reporter.util.Tools.EncryptStatus;
import com.sea.reporter.util.comparison.ComparisonList;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ComputeSettlement {

    private List<PaymentInformation> paymentInformation;

    private List<SettlementInfo> settlementInfos;

    private ComputeSettlement() {
    }

    public static ComputeSettlement getInstance(File vasOut, ComparisonList<UDForm> udForms) {
        ComputeSettlement computeSettlement = new ComputeSettlement();
        try {
            computeSettlement.paymentInformation = PaymentInformation.getInstance(udForms, vasOut);
            computeSettlement.settlementInfos = new ArrayList<>();
        } catch (Exception e) {
            log.error("Compute instance returned Null cause -> {}", e);
            return null;
        }
        return computeSettlement;
    }

    private void computeApportionment(PaymentInformation paymentInformation) throws Exception {
        List<Apportionment> apportionment = paymentInformation.getApportionment();
        if (apportionment == null || apportionment.size() == 0) {
            return;
        }
        if (!paymentInformation.isPercentageOfApportionmentCorrect(paymentInformation.getApportionment())) {
            throw new Exception("Apportionment is not correct for SubCode {" + paymentInformation.getCode() + "}");
        }
        for (Apportionment x : apportionment) {
            SettlementInfo settlementInfo = new SettlementInfo();
            if (x == null) {
                settlementInfo.setAmount(paymentInformation.getSumAmount());
                settlementInfo.setDescription("Account Not Found");
                settlementInfo.setAccountNumber(paymentInformation.getCode());
                this.settlementInfos.add(settlementInfo);
                continue;
            }
            settlementInfo.setAmount((paymentInformation.getSumAmount() * x.getPercentage()) / 100);
            settlementInfo.setDescription(x.getAccountInformation().getDescription());
            settlementInfo.setAccountNumber(x.getAccountInformation().getAccountNumber());
            this.settlementInfos.add(settlementInfo);
        }
    }

    public void compute() throws Exception {
        for (PaymentInformation information : this.paymentInformation) {
            computeApportionment(information);
        }
    }

    public boolean checkValidation(long checkAmount) {
        if (this.settlementInfos == null || this.settlementInfos.size() == 0) {
            return false;
        }
        long sum = 0;
        for (SettlementInfo settlementInfo : this.settlementInfos) {
            sum = sum + settlementInfo.getAmount();
        }
        return sum == checkAmount;
    }

    public boolean settlementsToFile(File file, EncryptStatus encryptStatus) throws Exception {
        try {
            this.settlementInfos.removeIf(s -> {

                if (s.getAccountNumber().equals("0000000000000")) {
                    try {
                        if (new File(file.getParent() + File.separator + "kart.txt").exists()) {
                            Tools.addTextToFile(new File(file.getParent() + File.separator + "kart.txt"), s);
                        } else
                            Tools.addTextToFile(Tools.createFile(file.getParent() + File.separator + "kart.txt"), s);
                    } catch (IOException e) {
                        log.error("Exception in adding to Kart.txt {}",e);
                    }
                }
                return s.getAccountNumber().equals("0000000000000");
            });
            return Tools.createTextFile(file, this.settlementInfos) && Tools.compress(file, new File(file.getPath() + ".zip"), encryptStatus);
        } catch (FileNotFoundException e) {
            throw new Exception(e);
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

    public List<PaymentInformation> getPaymentInformation() {
        return paymentInformation;
    }


    public List<SettlementInfo> getSettlementInfos() {
        return settlementInfos;
    }

}
