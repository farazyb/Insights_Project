package com.sea.reporter.util;

import com.sea.reporter.froms.UDForm;
import com.sea.reporter.model.dto.IsoDTO;
import com.sea.reporter.util.comparison.ComparisonList;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Utility class for filtering and processing User Data (UD) from ISO8583 messages.
 * This class handles the extraction and transformation of UD information from ISO messages
 * into a standardized UDForm format.
 */
@Log4j2
public class UDFilter {
    
    /**
     * Filters and processes ISO8583 messages to extract User Data information.
     * Creates UDForm objects from ISO messages that match the specified date.
     *
     * @param isoDTOS List of ISO8583 messages to process
     * @param date The date to filter messages by
     * @return A ComparisonList containing the processed UDForm objects
     * @throws ClassNotFoundException if the UDForm class cannot be found
     */
    public static ComparisonList<UDForm> filter(List<IsoDTO> isoDTOS, String date) throws ClassNotFoundException {
        ComparisonList<UDForm> udForms = new ComparisonList<>(UDForm.class);
        try {
            log.info("Start Filter {} Ud Data", isoDTOS.size());
            for (IsoDTO iSO8583Fields : isoDTOS) {
                if (!(iSO8583Fields.getDatetimeCreated().toString() + "").contains(date)) {
                    continue;
                }
                try {
                    UDForm udForm = new UDForm();
                    String text = iSO8583Fields.getAdditionalDataPrivate();
                    udForm.setCode1(text.substring(24, 37));
                    int len = Integer.parseInt(text.substring(37, 39));
                    udForm.setCode2(text.substring(39, 39 + len));
                    udForm.setTerminalCode(iSO8583Fields.getTerminalIdentification());
                    udForm.setDate(iSO8583Fields.getDatetimeCreated().toString() + "");
                    udForm.setSourcePan(iSO8583Fields.getPrimaryAccountNumber());
                    udForm.setAmount(iSO8583Fields.getAmountTransaction());
                    udForm.setRrn(iSO8583Fields.getRetrievalReferenceNumber());
                    udForm.setStan(iSO8583Fields.getSystemTraceAuditNumber());
                    udForms.add(udForm);
                } catch (IndexOutOfBoundsException ex) {
                    log.warn("Can't Pars Ud(" + iSO8583Fields.toString() + ") Cause -> {}", ex);
                }
                if (udForms.size() == 0) {
                    throw new Exception("None Of IsoDTOS Are Standard And Had Exception Then List Is Empty");
                }
            }
        } catch (Exception ex) {
            log.error("Exception in Filter Cause : -> {}", ex);
            return null;
        }
        return udForms;
    }
    public static ComparisonList<UDForm> filter(List<IsoDTO> isoDTOS) throws ClassNotFoundException {
        ComparisonList<UDForm> udForms = new ComparisonList<>(UDForm.class);
        try {
            log.info("Start Filter {} Ud Data", isoDTOS.size());
            for (IsoDTO iSO8583Fields : isoDTOS) {
                try {
                    UDForm udForm = new UDForm();
                    String text = iSO8583Fields.getAdditionalDataPrivate();
                    udForm.setCode1(text.substring(24, 37));
                    int len = Integer.parseInt(text.substring(37, 39));
                    udForm.setCode2(text.substring(39, 39 + len));
                    udForm.setTerminalCode(iSO8583Fields.getTerminalIdentification());
                    udForm.setDate(iSO8583Fields.getDatetimeCreated().toString() + "");
                    udForm.setSourcePan(iSO8583Fields.getPrimaryAccountNumber());
                    udForm.setAmount(iSO8583Fields.getAmountTransaction());
                    udForm.setRrn(iSO8583Fields.getRetrievalReferenceNumber());
                    udForm.setStan(iSO8583Fields.getSystemTraceAuditNumber());
                    udForms.add(udForm);
                } catch (IndexOutOfBoundsException ex) {
                    log.warn("Can't Pars Ud(" + iSO8583Fields.toString() + ") Cause -> {}", ex);
                }
                if (udForms.size() == 0) {
                    throw new Exception("None Of IsoDTOS Are Standard And Had Exception Then List Is Empty");
                }
            }
        } catch (Exception ex) {
            log.error("Exception in Filter Cause : -> {}", ex);
            return null;
        }
        return udForms;
    }
}
