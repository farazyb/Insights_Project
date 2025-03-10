package com.sea.reporter.froms;

import com.google.gson.Gson;
import com.sea.reporter.util.StringUtils;
import com.sea.reporter.util.comparison.ComparisonList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class UDForm {
    private int stan;
    private String date;
    private String sourcePan;
    private long amount;
    private int terminalCode;
    private String code1;
    private String code2;
    private long rrn;

    public int getStan() {
        return stan;
    }

    public void setStan(int stan) {
        this.stan = stan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSourcePan() {
        return sourcePan;
    }

    public void setSourcePan(String sourcePan) {
        this.sourcePan = sourcePan;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(int terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public long getRrn() {
        return rrn;
    }

    public void setRrn(long rrn) {
        this.rrn = rrn;
    }


    public String toGson() {
        return new Gson().toJson(this);
    }

    @Override
    public String toString() {
        return sourcePan + "|" +
                StringUtils.padleft(terminalCode + "", 8, '0') + "|" +
                StringUtils.padleft(amount + "", 12, '0') + "|" +
                date + "|" +
                StringUtils.padleft(rrn + "", 12, '0') + "|" +
                code1.substring(0, 8) + "|" +
                StringUtils.padleft(stan + "", 6, '0');
    }

    public static List<String> export(ComparisonList<UDForm> udForms) {
        List<String> paidUd = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(calendar.getTime());
        udForms.forEach(s -> {
            paidUd.add(s.toString() + "|" + "4" + "|" + "1" + "|" + today + "|" + "0" );
        });
        return paidUd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UDForm)) return false;

        UDForm udForm = (UDForm) o;

        if (getStan() != udForm.getStan()) return false;
        if (getAmount() != udForm.getAmount()) return false;
        if (getTerminalCode() != udForm.getTerminalCode()) return false;
        if (getRrn() != udForm.getRrn()) return false;
        if (!getDate().equals(udForm.getDate())) return false;
        if (!getSourcePan().equals(udForm.getSourcePan())) return false;
        if (!getCode1().equals(udForm.getCode1())) return false;
        return getCode2().equals(udForm.getCode2());
    }

    @Override
    public int hashCode() {
        int result = getStan();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getSourcePan().hashCode();
        result = 31 * result + (int) (getAmount() ^ (getAmount() >>> 32));
        result = 31 * result + getTerminalCode();
        result = 31 * result + getCode1().hashCode();
        result = 31 * result + getCode2().hashCode();
        result = 31 * result + (int) (getRrn() ^ (getRrn() >>> 32));
        return result;
    }
}
