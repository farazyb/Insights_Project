package com.sea.reporter.settlement.dto;

import com.sea.reporter.froms.VasForm;
import lombok.Data;

@Data
public class Apportionment {
    private AccountInformation accountInformation;
    private int percentage;

    private Apportionment() {
    }

    private Apportionment(AccountInformation accountInformation, int percentage) {
        this.accountInformation = accountInformation;
        this.percentage = percentage;
    }

    public static Apportionment getInstance(VasForm vasForm) {
        AccountInformation accountInformation = new AccountInformation();
        accountInformation.setAccountNumber(vasForm.getAccountNumber());
        accountInformation.setDescription(vasForm.getDescription());
        return new Apportionment(accountInformation, vasForm.getPercentage());
    }
}
