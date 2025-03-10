package com.sea.reporter.froms.reportForm;

import lombok.Data;

import java.util.List;

@Data
public class MonthlyByTerminalReport {
    private List<ByTerminalReportForm> byTerminalReportFormList;
    private long sumSuccessTransactionsAmount;
    private long countAllTransactions;
    private long countFailedTransactions;
    private long countSuccessTransactions;
    private long countShetabTransactions;
    private long countLocalTransactions;
    private int countTotalUd;
    private int countTotalCharge;
    private int countTotalBill;
    private int countTotalCardToCard;
    private int countTotalChangeFirstPassword;
    private int countTotalChangeSecondPassword;
    private int countTotalMiniStatement;


}
