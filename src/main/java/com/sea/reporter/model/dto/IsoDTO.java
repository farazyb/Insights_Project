package com.sea.reporter.model.dto;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;


public interface IsoDTO {
    String getMessageTypeIdentification();
    String getPrimaryAccountNumber();
    String getProcessingCode();
    @Value("#{ new Long(target.amountTransaction==null?0:target.amountTransaction)}")
    long getAmountTransaction();
    @Value("#{ new Integer(target.systemTraceAuditNumber==null?0:target.systemTraceAuditNumber)}")
    int getSystemTraceAuditNumber();
    String getTimeLocalTransaction();
    String getDateLocalTransaction();
    @Value("#{ new Long(target.retrievalReferenceNumber==null?0:target.retrievalReferenceNumber)}")
    long getRetrievalReferenceNumber();
    @Value("#{ new Integer(target.terminalIdentification==null?0:target.terminalIdentification)}")
    int getTerminalIdentification();
    String getCardAcceptorIdentificationCode();
    String getAdditionalDataPrivate();
    String getReservedPrivate2();
    Date getDatetimeCreated();



}
