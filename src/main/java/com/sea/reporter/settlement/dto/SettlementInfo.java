package com.sea.reporter.settlement.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SettlementInfo {
    private String accountNumber;
    private String description;
    private long amount;




    @Override
    public String toString() {
        return accountNumber + "$" + amount + "$+$9$ " + description;
    }
}
