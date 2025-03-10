package com.sea.reporter.froms.reportForm;

import jdk.nashorn.internal.ir.Terminal;
import lombok.Data;

@Data
public class ByTerminalReportForm {
    private String terminalId;
    private long sumAmount;
    private int totalTransaction;
    private int countUd;
    private int countCharge;
    private int countBill;
    private int countCardToCard;
    private int countChangeFirstPassword;
    private int countChangeSecondPassword;
    private int countMiniStatement;
    private int totalSuccessTransactions;
    private int totalReversTransactions;
    private int totalFiledTransactions;
    private int totalShetabTransactions;
    private int localTransactions;
}
