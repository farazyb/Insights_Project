package com.sea.reporter.controller.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;




public class ResultData {
    @JsonProperty("CompanyCode")
    private String CompanyCode;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("OrganizationType")
    private String OrganizationType;
    @JsonProperty("Period")
    private String Period;
    @JsonProperty("PaymentId")
    private String PaymentId;
    @JsonProperty("BillId")
    private String BillId;
    @JsonProperty("BillAmount")
    private long BillAmount;
    @JsonProperty("Result")
    private String Result;
    @JsonProperty("DateTime")
    private String DateTime;
    @JsonProperty("TerminalType")
    private String TerminalType;
    @JsonProperty("RRN")
    private long RRN;
    @JsonProperty("SAB")
    private int SAB;
    @JsonProperty("CardNumber")
    private String CardNumber;
    @JsonProperty("TerminalId")
    private int TerminalId;

    @Override
    public String toString() {

        return new Gson().toJson(this);
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrganizationType() {
        return OrganizationType;
    }

    public void setOrganizationType(String organizationType) {
        OrganizationType = organizationType;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(String paymentId) {
        PaymentId = paymentId;
    }

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public long getBillAmount() {
        return BillAmount;
    }

    public void setBillAmount(long billAmount) {
        BillAmount = billAmount;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getTerminalType() {
        return TerminalType;
    }

    public void setTerminalType(String terminalType) {
        TerminalType = terminalType;
    }

    public long getRRN() {
        return RRN;
    }

    public void setRRN(long RRN) {
        this.RRN = RRN;
    }

    public int getSAB() {
        return SAB;
    }

    public void setSAB(int SAB) {
        this.SAB = SAB;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public int getTerminalId() {
        return TerminalId;
    }

    public void setTerminalId(int terminalId) {
        TerminalId = terminalId;
    }
}
