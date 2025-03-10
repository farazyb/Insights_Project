package com.sea.reporter.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


public class Different {
   private String cardNumber;
   private long amount;
   private long rrn;
   private int stan;
   private int terminalId;
   private String additionalData1;
   private String additionalData2;
   private String type;
   private String date;

   public String getCardNumber() {
      return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
      this.cardNumber = cardNumber;
   }

   public long getAmount() {
      return amount;
   }

   public void setAmount(long amount) {
      this.amount = amount;
   }

   public long getRrn() {
      return rrn;
   }

   public void setRrn(long rrn) {
      this.rrn = rrn;
   }

   public int getStan() {
      return stan;
   }

   public void setStan(int stan) {
      this.stan = stan;
   }

   public int getTerminalId() {
      return terminalId;
   }

   public void setTerminalId(int terminalId) {
      this.terminalId = terminalId;
   }

   public String getAdditionalData1() {
      return additionalData1;
   }

   public void setAdditionalData1(String additionalData1) {
      this.additionalData1 = additionalData1;
   }

   public String getAdditionalData2() {
      return additionalData2;
   }

   public void setAdditionalData2(String additionalData2) {
      this.additionalData2 = additionalData2;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }
}
