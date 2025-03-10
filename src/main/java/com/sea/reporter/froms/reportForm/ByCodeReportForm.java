package com.sea.reporter.froms.reportForm;

import com.sea.reporter.settlement.dto.AccountInformation;
import lombok.Data;

@Data
public class ByCodeReportForm {
    private AccountInformation vasForm;
    private long sumAmount;
    private int totalTransaction;
}
