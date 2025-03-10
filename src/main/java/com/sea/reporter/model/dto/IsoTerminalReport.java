package com.sea.reporter.model.dto;

public interface IsoTerminalReport {

    String getTerminalId();

    long getSumAmount();

    int getTotalTransaction();

    int getCountUd();

    int getCountCharge();

    int getCountBill();

    int getCountCardToCard();

    int getCountChangeFirstPassword();

    int getCountChangeSecondPassword();

    int getCountMiniStatement();

    int getTotalSuccessTransactions();

    int getTotalReversTransactions();

    int getTotalFiledTransactions();

    int getTotalShetabTransactions();

    int getLocalTransactions();
}
