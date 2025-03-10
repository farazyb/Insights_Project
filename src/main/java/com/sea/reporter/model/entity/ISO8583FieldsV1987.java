package com.sea.reporter.model.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author navand
 */
@Entity
@Table(name = "ISO8583Fields_V1987")
public class ISO8583FieldsV1987 implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISO8583Fields_id")
    private long iso8583fieldsId;
    @Column(name = "message_type")
    private int messageType;
    @Column(name = "message_type_identification")
    private String messageTypeIdentification;
    @Column(name = "first_bitmap")
    private String firstBitmap;
    @Column(name = "second_bitmap")
    private String secondBitmap; //1
    @Column(name = "primary_account_number")
    private String primaryAccountNumber; //2
    @Column(name = "processing_code")
    private String processingCode; //3
    @Column(name = "amount_transaction")
    private String amountTransaction; //4
    @Column(name = "amount_settlement")
    private String amountSettlement; //5
    @Column(name = "amount_cardholder_billing")
    private String amountCardholderBilling; //6
    @Column(name = "transmission_date_time")
    private String transmissionDateTime; //7
    @Column(name = "amount_cardholder_billing_fee")
    private String amountCardholderBillingFee; //8
    @Column(name = "conversion_rate_settlement")
    private String conversionRateSettlement; //9
    @Column(name = "conversion_rate_cardholder_billing")
    private String conversionRateCardholderBilling; //10
    @Column(name = "system_trace_audit_number")
    private String systemTraceAuditNumber; //11
    @Column(name = "time_local_transaction")
    private String timeLocalTransaction; //12
    @Column(name = "date_local_transaction")
    private String dateLocalTransaction; //13
    @Column(name = "expiration_date")
    private String expirationDate; //14
    @Column(name = "date_settlement")
    private String dateSettlement; //15
    @Column(name = "date_conversion")
    private String dateConversion; //16
    @Column(name = "date_capture")
    private String dateCapture; //17
    @Column(name = "merchant_type")
    private String merchantType; //18
    @Column(name = "acquiring_institution_country_code")
    private String acquiringInstitutionCountryCode; //19
    @Column(name = "pan_estended_country_code")
    private String panExtendedCountryCode; //20
    @Column(name = "forwarding_institution_country_code")
    private String forwardingInstitutionCountryCode; //21
    @Column(name = "point_of_service_entry_mode")
    private String pointOfServiceEntryMode; //22
    @Column(name = "card_sequence_number")
    private String cardSequenceNumber; //23
    @Column(name = "network_international_identifier")
    private String networkInternationalIdentifier; //24
    @Column(name = "point_of_service_condition_code")
    private String pointOfServiceConditionCode; //25
    @Column(name = "point_of_service_capture_code")
    private String pointOfServiceCaptureCode; //26
    @Column(name = "authorizing_identification_response_length")
    private String authorizingIdentificationResponseLength; //27
    @Column(name = "amount_transaction_fee")
    private String amountTransactionFee; //28
    @Column(name = "amount_settlement_fee")
    private String amountSettlementFee; //29
    @Column(name = "amount_transaction_processing_fee")
    private String amountTransactionProcessingFee; //30
    @Column(name = "amount_settlement_processing_fee")
    private String amountSettlementProcessingFee; //31
    @Column(name = "acquiring_institution_identification_code")
    private String acquiringInstitutionIdentificationCode; //32
    @Column(name = "forwarding_institution_identification_code")
    private String forwardingInstitutionIdentificationCode; //33
    @Column(name = "primary_account_number_extended")
    private String primaryAccountNumberExtended; //34
    @Column(name = "track2_data")
    private String track2Data; //35
    @Column(name = "track3_data")
    private String track3Data; //36
    @Column(name = "retrieval_reference_number")
    private String retrievalReferenceNumber; //37
    @Column(name = "authorization_identification_response")
    private String authorizationIdentificationResponse; //38
    @Column(name = "response_code")
    private String responseCode; //39
    @Column(name = "service_restriction_code")
    private String serviceRestrictionCode; //40
    @Column(name = "terminal_identification")
    private String terminalIdentification; //41
    @Column(name = "card_acceptor_identification_code")
    private String cardAcceptorIdentificationCode; //42
    @Column(name = "card_acceptor_name_location")
    private String cardAcceptorNameLocation; //43
    @Column(name = "additional_response_data",length = 1000)
    private byte[] additionalResponseData; //44
    @Column(name = "track1_data")
    private String track1Data; //45
    @Column(name = "additional_data_iso")
    private String additionalDataISO; //46
    @Column(name = "additional_data_national")
    private String additionalDataNational; //47
    @Column(name = "additional_data_private",length = 4000)
    private String additionalDataPrivate; //48
    @Column(name = "currency_code_transaction")
    private String currencyCodeTransaction; //49
    @Column(name = "currency_code_settlement")
    private String currencyCodeSettlement; //50
    @Column(name = "currency_code_cardholder_billing")
    private String currencyCodeCardholderBilling; //51
    @Column(name = "pin_data")
    private String pinData; //52
    @Column(name = "security_related_control_information")
    private String securityRelatedControlInformation; //53
    @Column(name = "additional_amounts")
    private String additionalAmounts; //54
    @Column(name = "icc_system_related_data")
    private String iccSystemRelatedData; //55
    @Column(name = "icc_system_related_data2")
    private String iccSystemRelatedData2; //56
    @Column(name = "reserved_national1")
    private String reservedNational1; //57
    @Column(name = "reserved_national2")
    private String reservedNational2; //58
    @Column(name = "reserved_national3")
    private String reservedNational3; //59
    @Column(name = "reserved_national4")
    private String reservedNational4; //60
    @Column(name = "reserved_private1")
    private String reservedPrivate1; //61
    @Column(name = "reserved_private2")
    private String reservedPrivate2; //62
    @Column(name = "reserved_private3")
    private String reservedPrivate3; //63
    @Column(name = "message_authentication_code_mac")
    private String messageAuthenticationCodeMAC; //64
    @Column(name = "bitmap_extended")
    private String bitmapExtended; //65
    @Column(name = "settlement_code")
    private String settlementCode; //66
    @Column(name = "extended_payment_code")
    private String extendedPaymentCode; //67
    @Column(name = "receiving_institution_country_code")
    private String receivingInstitutionCountryCode; //68
    @Column(name = "settlement_institution_country_code")
    private String settlementInstitutionCountryCode; //69
    @Column(name = "network_management_identification_code")
    private String networkManagementIdentificationCode; //70
    @Column(name = "message_number")
    private String messageNumber; //71
    @Column(name = "message_number_last")
    private String messageNumberLast; //72
    @Column(name = "date_action")
    private String dateAction; //73
    @Column(name = "credits_number")
    private String creditsNumber; //74
    @Column(name = "credits_reversal_number")
    private String creditsReversalNumber; //75
    @Column(name = "debits_number")
    private String debitsNumber; //76
    @Column(name = "debits_reversal_number")
    private String debitsReversalNumber; //77
    @Column(name = "transfer_reversal_number")
    private String transferReversalNumber; //78
    @Column(name = "transfer_reversal_number2")
    private String transferReversalNumber2; //79
    @Column(name = "inquiries_number")
    private String inquiriesNumber; //80
    @Column(name = "quthorizations_number")
    private String authorizationsNumber; //81
    @Column(name = "credits_processing_fee_amount")
    private String creditsProcessingFeeAmount; //82
    @Column(name = "credits_transaction_fee_amount")
    private String creditsTransactionFeeAmount; //83
    @Column(name = "debits_processing_fee_amount")
    private String debitsProcessingFeeAmount; //84
    @Column(name = "debits_transaction_fee_amount")
    private String debitsTransactionFeeAmount; //85
    @Column(name = "credits_amount")
    private String creditsAmount; //86
    @Column(name = "credits_reversal_amount")
    private String creditsReversalAmount; //87
    @Column(name = "debits_amount")
    private String debitsAmount; //88
    @Column(name = "debits_reversal_amount")
    private String debitsReversalAmount; //89
    @Column(name = "original_data_elements")
    private String originalDataElements; //90
    @Column(name = "file_update_code")
    private String fileUpdateCode; //91
    @Column(name = "file_security_code")
    private String fileSecurityCode; //92
    @Column(name = "response_indicator")
    private String responseIndicator; //93
    @Column(name = "service_indicator")
    private String serviceIndicator; //94
    @Column(name = "replacement_amounts")
    private String replacementAmounts; //95
    @Column(name = "message_security_code")
    private byte[] messageSecurityCode; //96
    @Column(name = "amount_net_reconcilition")
    private String amountNetReconcilition; //97
    @Column(name = "payee")
    private String payee; //98
    @Column(name = "settlement_institution_identification_code")
    private String settlementInstitutionIdentificationCode; //99
    @Column(name = "receiving_institution_identification_code")
    private String receivingInstitutionIdentificationCode; //100
    @Column(name = "filename")
    private String fileName; //101
    @Column(name = "account_identification1")
    private String accountIdentification1; //102
    @Column(name = "account_identification2")
    private String accountIdentification2; //103
    @Column(name = "transaction_description")
    private String transactionDescription; //104
    @Column(name = "reserved_for_iso_use1")
    private String reservedForISOUse1; //105
    @Column(name = "reserved_for_iso_use2")
    private String reservedForISOUse2; //106
    @Column(name = "reserved_for_iso_use3")
    private String reservedForISOUse3; //107
    @Column(name = "reserved_for_iso_use4")
    private String reservedForISOUse4; //108
    @Column(name = "reserved_for_iso_use5")
    private String reservedForISOUse5; //109
    @Column(name = "reserved_for_iso_use6")
    private String reservedForISOUse6; //110
    @Column(name = "reserved_for_iso_use7")
    private String reservedForISOUse7; //111
    @Column(name = "reserved_for_national_use1")
    private String reservedForNationalUse1; //112
    @Column(name = "reserved_for_national_use2")
    private String reservedForNationalUse2; //113
    @Column(name = "reserved_for_national_use3")
    private String reservedForNationalUse3; //114
    @Column(name = "reserved_for_national_use4")
    private String reservedForNationalUse4; //115
    @Column(name = "reserved_for_national_use5")
    private String reservedForNationalUse5; //116
    @Column(name = "reserved_for_national_use6")
    private String reservedForNationalUse6; //117
    @Column(name = "reserved_for_national_use7")
    private String reservedForNationalUse7; //118
    @Column(name = "reserved_for_national_use8")
    private String reservedForNationalUse8; //119
    @Column(name = "reserved_for_private_use1")
    private String reservedForPrivateUse1; //120
    @Column(name = "reserved_for_private_use2")
    private byte[] reservedForPrivateUse2; //121
    @Column(name = "reserved_for_private_use3")
    private String reservedForPrivateUse3; //122
    @Column(name = "reserved_for_private_use4")
    private String reservedForPrivateUse4; //123
    @Column(name = "statement_data")
    private byte[] statementData; //124
    @Column(name = "reserved_for_private_use6")
    private String reservedForPrivateUse6; //125
    @Column(name = "reserved_for_private_use7")
    private String reservedForPrivateUse7; //126
    @Column(name = "reserved_for_private_use8")
    private String reservedForPrivateUse8; //127
    @Column(name = "message_authentication_code")
    private String messageAuthenticationCode; //128
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datetime_created", updatable = false)
    private Date datetimeCreated=new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datetime_modified", insertable = false, updatable = false)
    private Date datetimeModified=new Date();





    public long getIso8583fieldsId() {
        return iso8583fieldsId;
    }

    public void setIso8583fieldsId(long iso8583fieldsId) {
        this.iso8583fieldsId = iso8583fieldsId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageTypeIdentification() {
        return messageTypeIdentification;
    }

    public void setMessageTypeIdentification(String messageTypeIdentification) {
        this.messageTypeIdentification = messageTypeIdentification;
    }

    public String getFirstBitmap() {
       // this.firstBitmap = Convertor.bytesToHex(Conv(this.firstBitmap));
        return firstBitmap;
    }

    public void setFirstBitmap(String firstBitmap) {
       //this.bitmapBitSet = Convertor.byte2BitSet(this.bitmapBitSet, Convertor.hexStringToByteArray(firstBitmap), 0);
        this.firstBitmap = firstBitmap;
    }

    public String getSecondBitmap() {
       // this.secondBitmap = Convertor.bytesToHex(Convertor.bitSet2byte(this.secondBitmapBitSet));
        return secondBitmap;
    }

    public void setSecondBitmap(String secondBitmap) {
       // this.secondBitmapBitSet = Convertor.byte2BitSet(this.secondBitmapBitSet, Convertor.hexStringToByteArray(secondBitmap), 0);
        this.secondBitmap = secondBitmap;
        //setField(1);
    }

    public String getPrimaryAccountNumber() {
        return primaryAccountNumber;
    }

    public void setPrimaryAccountNumber(String primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
        //setField(2);
    }

    public String getProcessingCode() {
        return processingCode;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
        //setField(3);
    }

    public String getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(String amountTransaction) {
        this.amountTransaction = amountTransaction;
        //setField(4);
    }

    public String getAmountSettlement() {
        return amountSettlement;
    }

    public void setAmountSettlement(String amountSettlement) {
        this.amountSettlement = amountSettlement;
        //setField(5);
    }

    public String getAmountCardholderBilling() {
        return amountCardholderBilling;
    }

    public void setAmountCardholderBilling(String amountCardholderBilling) {
        this.amountCardholderBilling = amountCardholderBilling;
        //setField(6);
    }

    public String getTransmissionDateTime() {
        return transmissionDateTime;
    }

    public void setTransmissionDateTime(String transmissionDateTime) {
        this.transmissionDateTime = transmissionDateTime;
        //setField(7);
    }

    public String getAmountCardholderBillingFee() {
        return amountCardholderBillingFee;
    }

    public void setAmountCardholderBillingFee(String amountCardholderBillingFee) {
        this.amountCardholderBillingFee = amountCardholderBillingFee;
        //setField(8);
    }

    public String getConversionRateSettlement() {
        return conversionRateSettlement;
    }

    public void setConversionRateSettlement(String conversionRateSettlement) {
        this.conversionRateSettlement = conversionRateSettlement;
        //setField(9);
    }

    public String getConversionRateCardholderBilling() {
        return conversionRateCardholderBilling;
    }

    public void setConversionRateCardholderBilling(String conversionRateCardholderBilling) {
        this.conversionRateCardholderBilling = conversionRateCardholderBilling;
        //setField(10);
    }

    public String getSystemTraceAuditNumber() {
        return systemTraceAuditNumber;
    }

    public void setSystemTraceAuditNumber(String systemTraceAuditNumber) {
        this.systemTraceAuditNumber = systemTraceAuditNumber;
        //setField(11);
    }

    public String getTimeLocalTransaction() {
        return timeLocalTransaction;
    }

    public void setTimeLocalTransaction(String timeLocalTransaction) {
        this.timeLocalTransaction = timeLocalTransaction;
        //setField(12);
    }

    public String getDateLocalTransaction() {
        return dateLocalTransaction;
    }

    public void setDateLocalTransaction(String dateLocalTransaction) {
        this.dateLocalTransaction = dateLocalTransaction;
        //setField(13);
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        //setField(14);
    }

    public String getDateSettlement() {
        return dateSettlement;
    }

    public void setDateSettlement(String dateSettlement) {
        this.dateSettlement = dateSettlement;
        //setField(15);
    }

    public String getDateConversion() {
        return dateConversion;
    }

    public void setDateConversion(String dateConversion) {
        this.dateConversion = dateConversion;
        //setField(16);
    }

    public String getDateCapture() {
        return dateCapture;
    }

    public void setDateCapture(String dateCapture) {
        this.dateCapture = dateCapture;
        //setField(17);
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
        //setField(18);
    }

    public String getAcquiringInstitutionCountryCode() {
        return acquiringInstitutionCountryCode;
    }

    public void setAcquiringInstitutionCountryCode(String acquiringInstitutionCountryCode) {
        this.acquiringInstitutionCountryCode = acquiringInstitutionCountryCode;
        //setField(19);
    }

    public String getPanExtendedCountryCode() {
        return panExtendedCountryCode;
    }

    public void setPanExtendedCountryCode(String panExtendedCountryCode) {
        this.panExtendedCountryCode = panExtendedCountryCode;
        //setField(20);
    }

    public String getForwardingInstitutionCountryCode() {
        return forwardingInstitutionCountryCode;
    }

    public void setForwardingInstitutionCountryCode(String forwardingInstitutionCountryCode) {
        this.forwardingInstitutionCountryCode = forwardingInstitutionCountryCode;
        //setField(21);
    }

    public String getPointOfServiceEntryMode() {
        return pointOfServiceEntryMode;
    }

    public void setPointOfServiceEntryMode(String pointOfServiceEntryMode) {
        this.pointOfServiceEntryMode = pointOfServiceEntryMode;
        //setField(22);
    }

    public String getCardSequenceNumber() {
        return cardSequenceNumber;
    }

    public void setCardSequenceNumber(String cardSequenceNumber) {
        this.cardSequenceNumber = cardSequenceNumber;
        //setField(23);
    }

    public String getNetworkInternationalIdentifier() {
        return networkInternationalIdentifier;
    }

    public void setNetworkInternationalIdentifier(String networkInternationalIdentifier) {
        this.networkInternationalIdentifier = networkInternationalIdentifier;
        //setField(24);
    }

    public String getPointOfServiceConditionCode() {
        return pointOfServiceConditionCode;
    }

    public void setPointOfServiceConditionCode(String pointOfServiceConditionCode) {
        this.pointOfServiceConditionCode = pointOfServiceConditionCode;
        //setField(25);
    }

    public String getPointOfServiceCaptureCode() {
        return pointOfServiceCaptureCode;
    }

    public void setPointOfServiceCaptureCode(String pointOfServiceCaptureCode) {
        this.pointOfServiceCaptureCode = pointOfServiceCaptureCode;
        //setField(26);
    }

    public String getAuthorizingIdentificationResponseLength() {
        return authorizingIdentificationResponseLength;
    }

    public void setAuthorizingIdentificationResponseLength(String authorizingIdentificationResponseLength) {
        this.authorizingIdentificationResponseLength = authorizingIdentificationResponseLength;
        //setField(27);
    }

    public String getAmountTransactionFee() {
        return amountTransactionFee;
    }

    public void setAmountTransactionFee(String amountTransactionFee) {
        this.amountTransactionFee = amountTransactionFee;
        //setField(28);
    }

    public String getAmountSettlementFee() {
        return amountSettlementFee;
    }

    public void setAmountSettlementFee(String amountSettlementFee) {
        this.amountSettlementFee = amountSettlementFee;
        //setField(29);
    }

    public String getAmountTransactionProcessingFee() {
        return amountTransactionProcessingFee;
    }

    public void setAmountTransactionProcessingFee(String amountTransactionProcessingFee) {
        this.amountTransactionProcessingFee = amountTransactionProcessingFee;
        //setField(30);
    }

    public String getAmountSettlementProcessingFee() {
        return amountSettlementProcessingFee;
    }

    public void setAmountSettlementProcessingFee(String amountSettlementProcessingFee) {
        this.amountSettlementProcessingFee = amountSettlementProcessingFee;
        //setField(31);
    }

    public String getAcquiringInstitutionIdentificationCode() {
        return acquiringInstitutionIdentificationCode;
    }

    public void setAcquiringInstitutionIdentificationCode(String acquiringInstitutionIdentificationCode) {
        this.acquiringInstitutionIdentificationCode = acquiringInstitutionIdentificationCode;
        //setField(32);
    }

    public String getForwardingInstitutionIdentificationCode() {
        return forwardingInstitutionIdentificationCode;
    }

    public void setForwardingInstitutionIdentificationCode(String forwardingInstitutionIdentificationCode) {
        this.forwardingInstitutionIdentificationCode = forwardingInstitutionIdentificationCode;
        //setField(33);
    }

    public String getPrimaryAccountNumberExtended() {
        return primaryAccountNumberExtended;
    }

    public void setPrimaryAccountNumberExtended(String primaryAccountNumberExtended) {
        this.primaryAccountNumberExtended = primaryAccountNumberExtended;
        //setField(34);
    }

    public String getTrack2Data() {
        return track2Data;
    }

    public void setTrack2Data(String track2Data) {
        //setField(35);
        this.track2Data = track2Data;
    }

    public String getTrack3Data() {
        return track3Data;
    }

    public void setTrack3Data(String track3Data) {
        //setField(36);
        this.track3Data = track3Data;
    }

    public String getRetrievalReferenceNumber() {
        return retrievalReferenceNumber;
    }

    public void setRetrievalReferenceNumber(String retrievalReferenceNumber) {
        this.retrievalReferenceNumber = retrievalReferenceNumber;
        //setField(37);
    }

    public String getAuthorizationIdentificationResponse() {
        return authorizationIdentificationResponse;
    }

    public void setAuthorizationIdentificationResponse(String authorizationIdentificationResponse) {
        this.authorizationIdentificationResponse = authorizationIdentificationResponse;
        //setField(38);
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
        //setField(39);
    }

    public String getServiceRestrictionCode() {
        return serviceRestrictionCode;
    }

    public void setServiceRestrictionCode(String serviceRestrictionCode) {
        this.serviceRestrictionCode = serviceRestrictionCode;
        //setField(40);
    }

    public String getTerminalIdentification() {
        return terminalIdentification;
    }

    public void setTerminalIdentification(String terminalIdentification) {
        this.terminalIdentification = terminalIdentification;
        //setField(41);
    }

    public String getCardAcceptorIdentificationCode() {
        return cardAcceptorIdentificationCode;
    }

    public void setCardAcceptorIdentificationCode(String cardAcceptorIdentificationCode) {
        this.cardAcceptorIdentificationCode = cardAcceptorIdentificationCode;
        //setField(42);
    }

    public String getCardAcceptorNameLocation() {
        return cardAcceptorNameLocation;
    }

    public void setCardAcceptorNameLocation(String cardAcceptorNameLocation) {
        this.cardAcceptorNameLocation = cardAcceptorNameLocation;
        //setField(43);
    }

    public byte[] getAdditionalResponseData() {
        return additionalResponseData;
    }

    public void setAdditionalResponseData(byte[] additionalResponseData) {
        this.additionalResponseData = additionalResponseData;
    }

    public String getTrack1Data() {
        return track1Data;
    }

    public void setTrack1Data(String track1Data) {
        this.track1Data = track1Data;
        //setField(45);
    }

    public String getAdditionalDataISO() {
        return additionalDataISO;
    }

    public void setAdditionalDataISO(String additionalDataISO) {
        this.additionalDataISO = additionalDataISO;
        //setField(46);
    }

    public String getAdditionalDataNational() {
        return additionalDataNational;
    }

    public void setAdditionalDataNational(String additionalDataNational) {
        this.additionalDataNational = additionalDataNational;
        //setField(47);
    }

    public String getAdditionalDataPrivate() {
        return additionalDataPrivate;
    }

    public void setAdditionalDataPrivate(String additionalDataPrivate) {
        this.additionalDataPrivate = additionalDataPrivate;
        //setField(48);
    }

    public String getCurrencyCodeTransaction() {
        return currencyCodeTransaction;
    }

    public void setCurrencyCodeTransaction(String currencyCodeTransaction) {
        this.currencyCodeTransaction = currencyCodeTransaction;
        //setField(49);
    }

    public String getCurrencyCodeSettlement() {
        return currencyCodeSettlement;
    }

    public void setCurrencyCodeSettlement(String currencyCodeSettlement) {
        this.currencyCodeSettlement = currencyCodeSettlement;
        //setField(50);
    }

    public String getCurrencyCodeCardholderBilling() {
        return currencyCodeCardholderBilling;
    }

    public void setCurrencyCodeCardholderBilling(String currencyCodeCardholderBilling) {
        this.currencyCodeCardholderBilling = currencyCodeCardholderBilling;
        //setField(51);
    }

    public String getPinData() {
        return pinData;
    }

    public void setPinData(String pinData) {
        this.pinData = pinData;
        //setField(52);
    }

    public String getSecurityRelatedControlInformation() {
        return securityRelatedControlInformation;
    }

    public void setSecurityRelatedControlInformation(String securityRelatedControlInformation) {
        this.securityRelatedControlInformation = securityRelatedControlInformation;
        //setField(53);
    }

    public String getAdditionalAmounts() {
        return additionalAmounts;
    }

    public void setAdditionalAmounts(String additionalAmounts) {
        this.additionalAmounts = additionalAmounts;
        //setField(54);
    }

    public String getIccSystemRelatedData() {
        return iccSystemRelatedData;
    }

    public void setIccSystemRelatedData(String iccSystemRelatedData) {
        this.iccSystemRelatedData = iccSystemRelatedData;
        //setField(55);
    }

    public String getIccSystemRelatedData2() {
        return iccSystemRelatedData2;
    }

    public void setIccSystemRelatedData2(String iccSystemRelatedData2) {
        this.iccSystemRelatedData2 = iccSystemRelatedData2;
        //setField(56);
    }

    public String getReservedNational1() {
        return reservedNational1;
    }

    public void setReservedNational1(String reservedNational1) {
        this.reservedNational1 = reservedNational1;
        //setField(57);
    }

    public String getReservedNational2() {
        return reservedNational2;
    }

    public void setReservedNational2(String reservedNational2) {
        this.reservedNational2 = reservedNational2;
        //setField(58);
    }

    public String getReservedNational3() {
        return reservedNational3;
    }

    public void setReservedNational3(String reservedNational3) {
        this.reservedNational3 = reservedNational3;
        //setField(59);
    }

    public String getReservedNational4() {
        return reservedNational4;
    }

    public void setReservedNational4(String reservedNational4) {
        this.reservedNational4 = reservedNational4;
        //setField(60);
    }

    public String getReservedPrivate1() {
        return reservedPrivate1;
    }

    public void setReservedPrivate1(String reservedPrivate1) {
        this.reservedPrivate1 = reservedPrivate1;
        //setField(61);
    }

    public String getReservedPrivate2() {
        return reservedPrivate2;
    }

    public void setReservedPrivate2(String reservedPrivate2) {
        this.reservedPrivate2 = reservedPrivate2;
        //setField(62);
    }

    public String getReservedPrivate3() {
        return reservedPrivate3;
    }

    public void setReservedPrivate3(String reservedPrivate3) {
        this.reservedPrivate3 = reservedPrivate3;
        //setField(63);
    }

    public String getMessageAuthenticationCodeMAC() {
        return messageAuthenticationCodeMAC;
    }

    public void setMessageAuthenticationCodeMAC(String messageAuthenticationCodeMAC) {
        this.messageAuthenticationCodeMAC = messageAuthenticationCodeMAC;
        //setField(64);
    }

    public String getBitmapExtended() {
        return bitmapExtended;
    }

    public void setBitmapExtended(String bitmapExtended) {
        this.bitmapExtended = bitmapExtended;
        //setField(65);
    }

    public String getSettlementCode() {
        return settlementCode;
    }

    public void setSettlementCode(String settlementCode) {
        this.settlementCode = settlementCode;
        //setField(66);
    }

    public String getExtendedPaymentCode() {
        return extendedPaymentCode;
    }

    public void setExtendedPaymentCode(String extendedPaymentCode) {
        this.extendedPaymentCode = extendedPaymentCode;
        //setField(67);
    }

    public String getReceivingInstitutionCountryCode() {
        return receivingInstitutionCountryCode;
    }

    public void setReceivingInstitutionCountryCode(String receivingInstitutionCountryCode) {
        this.receivingInstitutionCountryCode = receivingInstitutionCountryCode;
        //setField(68);
    }

    public String getSettlementInstitutionCountryCode() {
        return settlementInstitutionCountryCode;
    }

    public void setSettlementInstitutionCountryCode(String settlementInstitutionCountryCode) {
        this.settlementInstitutionCountryCode = settlementInstitutionCountryCode;
        //setField(69);
    }

    public String getNetworkManagementIdentificationCode() {
        return networkManagementIdentificationCode;
    }

    public void setNetworkManagementIdentificationCode(String networkManagementIdentificationCode) {
        this.networkManagementIdentificationCode = networkManagementIdentificationCode;
        //setField(70);
    }

    public String getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
        //setField(71);
    }

    public String getMessageNumberLast() {
        return messageNumberLast;
    }

    public void setMessageNumberLast(String messageNumberLast) {
        this.messageNumberLast = messageNumberLast;
        //setField(72);
    }

    public String getDateAction() {
        return dateAction;
    }

    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
        //setField(73);
    }

    public String getCreditsNumber() {
        return creditsNumber;
    }

    public void setCreditsNumber(String creditsNumber) {
        this.creditsNumber = creditsNumber;
        //setField(74);
    }

    public String getCreditsReversalNumber() {
        return creditsReversalNumber;
    }

    public void setCreditsReversalNumber(String creditsReversalNumber) {
        this.creditsReversalNumber = creditsReversalNumber;
        //setField(75);
    }

    public String getDebitsNumber() {
        return debitsNumber;
    }

    public void setDebitsNumber(String debitsNumber) {
        this.debitsNumber = debitsNumber;
        //setField(76);
    }

    public String getDebitsReversalNumber() {
        return debitsReversalNumber;
    }

    public void setDebitsReversalNumber(String debitsReversalNumber) {
        this.debitsReversalNumber = debitsReversalNumber;
        //setField(77);
    }

    public String getTransferReversalNumber() {
        return transferReversalNumber;
    }

    public void setTransferReversalNumber(String transferReversalNumber) {
        this.transferReversalNumber = transferReversalNumber;
        //setField(78);
    }

    public String getTransferReversalNumber2() {
        return transferReversalNumber2;
    }

    public void setTransferReversalNumber2(String transferReversalNumber2) {
        this.transferReversalNumber2 = transferReversalNumber2;
        //setField(79);
    }

    public String getInquiriesNumber() {
        return inquiriesNumber;
    }

    public void setInquiriesNumber(String inquiriesNumber) {
        this.inquiriesNumber = inquiriesNumber;
        //setField(80);
    }

    public String getAuthorizationsNumber() {
        return authorizationsNumber;
    }

    public void setAuthorizationsNumber(String authorizationsNumber) {
        this.authorizationsNumber = authorizationsNumber;
        //setField(81);
    }

    public String getCreditsProcessingFeeAmount() {
        return creditsProcessingFeeAmount;
    }

    public void setCreditsProcessingFeeAmount(String creditsProcessingFeeAmount) {
        this.creditsProcessingFeeAmount = creditsProcessingFeeAmount;
        //setField(82);
    }

    public String getCreditsTransactionFeeAmount() {
        return creditsTransactionFeeAmount;
    }

    public void setCreditsTransactionFeeAmount(String creditsTransactionFeeAmount) {
        this.creditsTransactionFeeAmount = creditsTransactionFeeAmount;
        //setField(83);
    }

    public String getDebitsProcessingFeeAmount() {
        return debitsProcessingFeeAmount;
    }

    public void setDebitsProcessingFeeAmount(String debitsProcessingFeeAmount) {
        this.debitsProcessingFeeAmount = debitsProcessingFeeAmount;
        //setField(84);
    }

    public String getDebitsTransactionFeeAmount() {
        return debitsTransactionFeeAmount;
    }

    public void setDebitsTransactionFeeAmount(String debitsTransactionFeeAmount) {
        this.debitsTransactionFeeAmount = debitsTransactionFeeAmount;
        //setField(85);
    }

    public String getCreditsAmount() {
        return creditsAmount;
    }

    public void setCreditsAmount(String creditsAmount) {
        this.creditsAmount = creditsAmount;
        //setField(86);
    }

    public String getCreditsReversalAmount() {
        return creditsReversalAmount;
    }

    public void setCreditsReversalAmount(String creditsReversalAmount) {
        this.creditsReversalAmount = creditsReversalAmount;
        //setField(87);
    }

    public String getDebitsAmount() {
        return debitsAmount;
    }

    public void setDebitsAmount(String debitsAmount) {
        this.debitsAmount = debitsAmount;
        //setField(88);
    }

    public String getDebitsReversalAmount() {
        return debitsReversalAmount;
    }

    public void setDebitsReversalAmount(String debitsReversalAmount) {
        this.debitsReversalAmount = debitsReversalAmount;
        //setField(89);
    }

    public String getOriginalDataElements() {
        return originalDataElements;
    }

    public void setOriginalDataElements(String originalDataElements) {
        this.originalDataElements = originalDataElements;
        //setField(90);
    }

    public String getFileUpdateCode() {
        return fileUpdateCode;
    }

    public void setFileUpdateCode(String fileUpdateCode) {
        this.fileUpdateCode = fileUpdateCode;
        //setField(91);
    }

    public String getFileSecurityCode() {
        return fileSecurityCode;
    }

    public void setFileSecurityCode(String fileSecurityCode) {
        this.fileSecurityCode = fileSecurityCode;
        //setField(92);
    }

    public String getResponseIndicator() {
        return responseIndicator;
    }

    public void setResponseIndicator(String responseIndicator) {
        this.responseIndicator = responseIndicator;
        //setField(93);
    }

    public String getServiceIndicator() {
        return serviceIndicator;
    }

    public void setServiceIndicator(String serviceIndicator) {
        this.serviceIndicator = serviceIndicator;
        //setField(94);
    }

    public String getReplacementAmounts() {
        return replacementAmounts;
    }

    public void setReplacementAmounts(String replacementAmounts) {
        this.replacementAmounts = replacementAmounts;
        //setField(95);
    }


    public String getAmountNetReconcilition() {
        return amountNetReconcilition;
    }

    public void setAmountNetReconcilition(String amountNetReconcilition) {
        this.amountNetReconcilition = amountNetReconcilition;
        //setField(97);
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
        //setField(98);
    }

    public String getSettlementInstitutionIdentificationCode() {
        return settlementInstitutionIdentificationCode;
    }

    public void setSettlementInstitutionIdentificationCode(String settlementInstitutionIdentificationCode) {
        this.settlementInstitutionIdentificationCode = settlementInstitutionIdentificationCode;
        //setField(99);
    }

    public String getReceivingInstitutionIdentificationCode() {
        return receivingInstitutionIdentificationCode;
    }

    public void setReceivingInstitutionIdentificationCode(String receivingInstitutionIdentificationCode) {
        this.receivingInstitutionIdentificationCode = receivingInstitutionIdentificationCode;
        //setField(100);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        //setField(101);
    }

    public String getAccountIdentification1() {
        return accountIdentification1;
    }

    public void setAccountIdentification1(String accountIdentification1) {
        this.accountIdentification1 = accountIdentification1;
        //setField(102);
    }

    public String getAccountIdentification2() {
        return accountIdentification2;
    }

    public void setAccountIdentification2(String accountIdentification2) {
        this.accountIdentification2 = accountIdentification2;
        //setField(103);
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
        //setField(104);
    }

    public String getReservedForISOUse1() {
        return reservedForISOUse1;
    }

    public void setReservedForISOUse1(String reservedForISOUse1) {
        this.reservedForISOUse1 = reservedForISOUse1;
        //setField(105);
    }

    public String getReservedForISOUse2() {
        return reservedForISOUse2;
    }

    public void setReservedForISOUse2(String reservedForISOUse2) {
        this.reservedForISOUse2 = reservedForISOUse2;
        //setField(106);
    }

    public String getReservedForISOUse3() {
        return reservedForISOUse3;
    }

    public void setReservedForISOUse3(String reservedForISOUse3) {
        this.reservedForISOUse3 = reservedForISOUse3;
        //setField(107);
    }

    public String getReservedForISOUse4() {
        return reservedForISOUse4;
    }

    public void setReservedForISOUse4(String reservedForISOUse4) {
        this.reservedForISOUse4 = reservedForISOUse4;
        //setField(108);
    }

    public String getReservedForISOUse5() {
        return reservedForISOUse5;
    }

    public void setReservedForISOUse5(String reservedForISOUse5) {
        this.reservedForISOUse5 = reservedForISOUse5;
        //setField(109);
    }

    public String getReservedForISOUse6() {
        return reservedForISOUse6;
    }

    public void setReservedForISOUse6(String reservedForISOUse6) {
        this.reservedForISOUse6 = reservedForISOUse6;
        //setField(110);
    }

    public String getReservedForISOUse7() {
        return reservedForISOUse7;
    }

    public void setReservedForISOUse7(String reservedForISOUse7) {
        this.reservedForISOUse7 = reservedForISOUse7;
        //setField(111);
    }

    public String getReservedForNationalUse1() {
        return reservedForNationalUse1;
    }

    public void setReservedForNationalUse1(String reservedForNationalUse1) {
        this.reservedForNationalUse1 = reservedForNationalUse1;
        //setField(112);
    }

    public String getReservedForNationalUse2() {
        return reservedForNationalUse2;
    }

    public void setReservedForNationalUse2(String reservedForNationalUse2) {
        this.reservedForNationalUse2 = reservedForNationalUse2;
        //setField(113);
    }

    public String getReservedForNationalUse3() {
        return reservedForNationalUse3;
    }

    public void setReservedForNationalUse3(String reservedForNationalUse3) {
        this.reservedForNationalUse3 = reservedForNationalUse3;
        //setField(114);
    }

    public String getReservedForNationalUse4() {
        return reservedForNationalUse4;
    }

    public void setReservedForNationalUse4(String reservedForNationalUse4) {
        this.reservedForNationalUse4 = reservedForNationalUse4;
        //setField(115);
    }

    public String getReservedForNationalUse5() {
        return reservedForNationalUse5;
    }

    public void setReservedForNationalUse5(String reservedForNationalUse5) {
        this.reservedForNationalUse5 = reservedForNationalUse5;
        //setField(116);
    }

    public String getReservedForNationalUse6() {
        return reservedForNationalUse6;
    }

    public void setReservedForNationalUse6(String reservedForNationalUse6) {
        this.reservedForNationalUse6 = reservedForNationalUse6;
        //setField(117);
    }

    public String getReservedForNationalUse7() {
        return reservedForNationalUse7;
    }

    public void setReservedForNationalUse7(String reservedForNationalUse7) {
        this.reservedForNationalUse7 = reservedForNationalUse7;
        //setField(118);
    }

    public String getReservedForNationalUse8() {
        return reservedForNationalUse8;
    }

    public void setReservedForNationalUse8(String reservedForNationalUse8) {
        this.reservedForNationalUse8 = reservedForNationalUse8;
        //setField(119);
    }

    public String getReservedForPrivateUse1() {
        return reservedForPrivateUse1;
    }

    public void setReservedForPrivateUse1(String reservedForPrivateUse1) {
        this.reservedForPrivateUse1 = reservedForPrivateUse1;
        //setField(120);
    }

    public byte[] getMessageSecurityCode() {
        return messageSecurityCode;
    }

    public void setMessageSecurityCode(byte[] messageSecurityCode) {
        this.messageSecurityCode = messageSecurityCode;
    }

    public byte[] getReservedForPrivateUse2() {
        return reservedForPrivateUse2;
    }

    public void setReservedForPrivateUse2(byte[] reservedForPrivateUse2) {
        this.reservedForPrivateUse2 = reservedForPrivateUse2;
    }

    public String getReservedForPrivateUse3() {
        return reservedForPrivateUse3;
    }

    public void setReservedForPrivateUse3(String reservedForPrivateUse3) {
        this.reservedForPrivateUse3 = reservedForPrivateUse3;
        //setField(122);
    }

    public String getReservedForPrivateUse4() {
        return reservedForPrivateUse4;
    }

    public void setReservedForPrivateUse4(String reservedForPrivateUse4) {
        this.reservedForPrivateUse4 = reservedForPrivateUse4;
        //setField(123);
    }

    public byte[] getStatementData() {
        return statementData;
    }

    public void setStatementData(byte[] statementData) {
        this.statementData = statementData;
    }

    public String getReservedForPrivateUse6() {
        return reservedForPrivateUse6;
    }

    public void setReservedForPrivateUse6(String reservedForPrivateUse6) {
        this.reservedForPrivateUse6 = reservedForPrivateUse6;
        //setField(125);
    }

    public String getReservedForPrivateUse7() {
        return reservedForPrivateUse7;
    }

    public void setReservedForPrivateUse7(String reservedForPrivateUse7) {
        this.reservedForPrivateUse7 = reservedForPrivateUse7;
        //setField(126);
    }

    public String getReservedForPrivateUse8() {
        return reservedForPrivateUse8;
    }

    public void setReservedForPrivateUse8(String reservedForPrivateUse8) {
        this.reservedForPrivateUse8 = reservedForPrivateUse8;
        //setField(127);
    }

    public String getMessageAuthenticationCode() {
        return messageAuthenticationCode;
    }

    public void setMessageAuthenticationCode(String messageAuthenticationCode) {
        this.messageAuthenticationCode = messageAuthenticationCode;
        //setField(128);
    }

    public Date getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(Date datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

    public Date getDatetimeModified() {
        return datetimeModified;
    }

    public void setDatetimeModified(Date datetimeModified) {
        this.datetimeModified = datetimeModified;
    }


    @Override
    public String toString() {
        return "ISO8583FieldsV1987{" +
                "iso8583fieldsId=" + iso8583fieldsId +
                ", messageType=" + messageType +
                ", messageTypeIdentification='" + messageTypeIdentification + '\'' +
                ", firstBitmap='" + firstBitmap + '\'' +
                ", secondBitmap='" + secondBitmap + '\'' +
                ", primaryAccountNumber='" + primaryAccountNumber + '\'' +
                ", processingCode='" + processingCode + '\'' +
                ", amountTransaction='" + amountTransaction + '\'' +
                ", amountSettlement='" + amountSettlement + '\'' +
                ", amountCardholderBilling='" + amountCardholderBilling + '\'' +
                ", transmissionDateTime='" + transmissionDateTime + '\'' +
                ", amountCardholderBillingFee='" + amountCardholderBillingFee + '\'' +
                ", conversionRateSettlement='" + conversionRateSettlement + '\'' +
                ", conversionRateCardholderBilling='" + conversionRateCardholderBilling + '\'' +
                ", systemTraceAuditNumber='" + systemTraceAuditNumber + '\'' +
                ", timeLocalTransaction='" + timeLocalTransaction + '\'' +
                ", dateLocalTransaction='" + dateLocalTransaction + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", dateSettlement='" + dateSettlement + '\'' +
                ", dateConversion='" + dateConversion + '\'' +
                ", dateCapture='" + dateCapture + '\'' +
                ", merchantType='" + merchantType + '\'' +
                ", acquiringInstitutionCountryCode='" + acquiringInstitutionCountryCode + '\'' +
                ", panExtendedCountryCode='" + panExtendedCountryCode + '\'' +
                ", forwardingInstitutionCountryCode='" + forwardingInstitutionCountryCode + '\'' +
                ", pointOfServiceEntryMode='" + pointOfServiceEntryMode + '\'' +
                ", cardSequenceNumber='" + cardSequenceNumber + '\'' +
                ", networkInternationalIdentifier='" + networkInternationalIdentifier + '\'' +
                ", pointOfServiceConditionCode='" + pointOfServiceConditionCode + '\'' +
                ", pointOfServiceCaptureCode='" + pointOfServiceCaptureCode + '\'' +
                ", authorizingIdentificationResponseLength='" + authorizingIdentificationResponseLength + '\'' +
                ", amountTransactionFee='" + amountTransactionFee + '\'' +
                ", amountSettlementFee='" + amountSettlementFee + '\'' +
                ", amountTransactionProcessingFee='" + amountTransactionProcessingFee + '\'' +
                ", amountSettlementProcessingFee='" + amountSettlementProcessingFee + '\'' +
                ", acquiringInstitutionIdentificationCode='" + acquiringInstitutionIdentificationCode + '\'' +
                ", forwardingInstitutionIdentificationCode='" + forwardingInstitutionIdentificationCode + '\'' +
                ", primaryAccountNumberExtended='" + primaryAccountNumberExtended + '\'' +
                ", track2Data='" + track2Data + '\'' +
                ", track3Data='" + track3Data + '\'' +
                ", retrievalReferenceNumber='" + retrievalReferenceNumber + '\'' +
                ", authorizationIdentificationResponse='" + authorizationIdentificationResponse + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", serviceRestrictionCode='" + serviceRestrictionCode + '\'' +
                ", terminalIdentification='" + terminalIdentification + '\'' +
                ", cardAcceptorIdentificationCode='" + cardAcceptorIdentificationCode + '\'' +
                ", cardAcceptorNameLocation='" + cardAcceptorNameLocation + '\'' +
                ", additionalResponseData='" + additionalResponseData + '\'' +
                ", track1Data='" + track1Data + '\'' +
                ", additionalDataISO='" + additionalDataISO + '\'' +
                ", additionalDataNational='" + additionalDataNational + '\'' +
                ", additionalDataPrivate='" + additionalDataPrivate + '\'' +
                ", currencyCodeTransaction='" + currencyCodeTransaction + '\'' +
                ", currencyCodeSettlement='" + currencyCodeSettlement + '\'' +
                ", currencyCodeCardholderBilling='" + currencyCodeCardholderBilling + '\'' +
                ", pinData='" + pinData + '\'' +
                ", securityRelatedControlInformation='" + securityRelatedControlInformation + '\'' +
                ", additionalAmounts='" + additionalAmounts + '\'' +
                ", iccSystemRelatedData='" + iccSystemRelatedData + '\'' +
                ", iccSystemRelatedData2='" + iccSystemRelatedData2 + '\'' +
                ", reservedNational1='" + reservedNational1 + '\'' +
                ", reservedNational2='" + reservedNational2 + '\'' +
                ", reservedNational3='" + reservedNational3 + '\'' +
                ", reservedNational4='" + reservedNational4 + '\'' +
                ", reservedPrivate1='" + reservedPrivate1 + '\'' +
                ", reservedPrivate2='" + reservedPrivate2 + '\'' +
                ", reservedPrivate3='" + reservedPrivate3 + '\'' +
                ", messageAuthenticationCodeMAC='" + messageAuthenticationCodeMAC + '\'' +
                ", bitmapExtended='" + bitmapExtended + '\'' +
                ", settlementCode='" + settlementCode + '\'' +
                ", extendedPaymentCode='" + extendedPaymentCode + '\'' +
                ", receivingInstitutionCountryCode='" + receivingInstitutionCountryCode + '\'' +
                ", settlementInstitutionCountryCode='" + settlementInstitutionCountryCode + '\'' +
                ", networkManagementIdentificationCode='" + networkManagementIdentificationCode + '\'' +
                ", messageNumber='" + messageNumber + '\'' +
                ", messageNumberLast='" + messageNumberLast + '\'' +
                ", dateAction='" + dateAction + '\'' +
                ", creditsNumber='" + creditsNumber + '\'' +
                ", creditsReversalNumber='" + creditsReversalNumber + '\'' +
                ", debitsNumber='" + debitsNumber + '\'' +
                ", debitsReversalNumber='" + debitsReversalNumber + '\'' +
                ", transferReversalNumber='" + transferReversalNumber + '\'' +
                ", transferReversalNumber2='" + transferReversalNumber2 + '\'' +
                ", inquiriesNumber='" + inquiriesNumber + '\'' +
                ", authorizationsNumber='" + authorizationsNumber + '\'' +
                ", creditsProcessingFeeAmount='" + creditsProcessingFeeAmount + '\'' +
                ", creditsTransactionFeeAmount='" + creditsTransactionFeeAmount + '\'' +
                ", debitsProcessingFeeAmount='" + debitsProcessingFeeAmount + '\'' +
                ", debitsTransactionFeeAmount='" + debitsTransactionFeeAmount + '\'' +
                ", creditsAmount='" + creditsAmount + '\'' +
                ", creditsReversalAmount='" + creditsReversalAmount + '\'' +
                ", debitsAmount='" + debitsAmount + '\'' +
                ", debitsReversalAmount='" + debitsReversalAmount + '\'' +
                ", originalDataElements='" + originalDataElements + '\'' +
                ", fileUpdateCode='" + fileUpdateCode + '\'' +
                ", fileSecurityCode='" + fileSecurityCode + '\'' +
                ", responseIndicator='" + responseIndicator + '\'' +
                ", serviceIndicator='" + serviceIndicator + '\'' +
                ", replacementAmounts='" + replacementAmounts + '\'' +
                ", messageSecurityCode=" + Arrays.toString(messageSecurityCode) +
                ", amountNetReconcilition='" + amountNetReconcilition + '\'' +
                ", payee='" + payee + '\'' +
                ", settlementInstitutionIdentificationCode='" + settlementInstitutionIdentificationCode + '\'' +
                ", receivingInstitutionIdentificationCode='" + receivingInstitutionIdentificationCode + '\'' +
                ", fileName='" + fileName + '\'' +
                ", accountIdentification1='" + accountIdentification1 + '\'' +
                ", accountIdentification2='" + accountIdentification2 + '\'' +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", reservedForISOUse1='" + reservedForISOUse1 + '\'' +
                ", reservedForISOUse2='" + reservedForISOUse2 + '\'' +
                ", reservedForISOUse3='" + reservedForISOUse3 + '\'' +
                ", reservedForISOUse4='" + reservedForISOUse4 + '\'' +
                ", reservedForISOUse5='" + reservedForISOUse5 + '\'' +
                ", reservedForISOUse6='" + reservedForISOUse6 + '\'' +
                ", reservedForISOUse7='" + reservedForISOUse7 + '\'' +
                ", reservedForNationalUse1='" + reservedForNationalUse1 + '\'' +
                ", reservedForNationalUse2='" + reservedForNationalUse2 + '\'' +
                ", reservedForNationalUse3='" + reservedForNationalUse3 + '\'' +
                ", reservedForNationalUse4='" + reservedForNationalUse4 + '\'' +
                ", reservedForNationalUse5='" + reservedForNationalUse5 + '\'' +
                ", reservedForNationalUse6='" + reservedForNationalUse6 + '\'' +
                ", reservedForNationalUse7='" + reservedForNationalUse7 + '\'' +
                ", reservedForNationalUse8='" + reservedForNationalUse8 + '\'' +
                ", reservedForPrivateUse1='" + reservedForPrivateUse1 + '\'' +
                ", reservedForPrivateUse2=" + Arrays.toString(reservedForPrivateUse2) +
                ", reservedForPrivateUse3='" + reservedForPrivateUse3 + '\'' +
                ", reservedForPrivateUse4='" + reservedForPrivateUse4 + '\'' +
                ", statementData=" + Arrays.toString(statementData) +
                ", reservedForPrivateUse6='" + reservedForPrivateUse6 + '\'' +
                ", reservedForPrivateUse7='" + reservedForPrivateUse7 + '\'' +
                ", reservedForPrivateUse8='" + reservedForPrivateUse8 + '\'' +
                ", messageAuthenticationCode='" + messageAuthenticationCode + '\'' +
                ", datetimeCreated=" + datetimeCreated +
                ", datetimeModified=" + datetimeModified +
                '}';
    }
}

