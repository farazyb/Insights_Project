package com.sea.reporter.service;

import com.sea.reporter.froms.UDForm;
import com.sea.reporter.model.dto.IsoDTO;
import com.sea.reporter.model.repository.ISO8583FieldsV1987Repository;
import com.sea.reporter.util.comparison.ComparisonList;
import com.sea.reporter.util.UDFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ISOService {
    @Autowired
    ISO8583FieldsV1987Repository iso8583FieldsV1987Repository;

    public ComparisonList<UDForm> getDataByDate(String from, String to) {
        String newFrom = from.split(" ")[0] + " 00:00:00";
        String newTo = to.split(" ")[0] + " 00:04:00";

        return getResultDate(from, newTo);
    }

    public ComparisonList<UDForm> getDataByDate(String from, String to, String fromDateCapture, String toDateCapture) {

        return getResultDate(from, to, fromDateCapture, toDateCapture);
    }

    private ComparisonList<UDForm> getResultDate(String from, String to) {
        ComparisonList<UDForm> udForms;
        try {
            log.info("start Fetch data from database from {} , to {} , date: {}", from, to, from.split(" ")[0]);
            List<IsoDTO> isoDTOS = iso8583FieldsV1987Repository.getByDatetimeCreated(from, to);
            if (isoDTOS == null || isoDTOS.isEmpty()) {
                log.warn("No UD Transaction Founded Between {} And {}", from, to);
                return null;
            }
            log.info("{} Ud Founded", isoDTOS.size());
            log.info("Start Filters IsoDTOS({})", isoDTOS.size());
            udForms = UDFilter.filter(isoDTOS, from.split(" ")[0]);
            if (udForms == null || udForms.size() == 0) {
                log.warn("From {} IsoDTOS / {} Converted ", isoDTOS.size(), 0);
                return null;
            }
            log.info("From {} IsoDTOS / {} Converted ", isoDTOS.size(), udForms.size());
        } catch (Exception ex) {
            log.error("Exception Cause: -> {}", ex);
            return null;
        }
        return udForms;
    }

    private ComparisonList<UDForm> getResultDate(String from, String to, String fromDateCapture, String toDateCapture) {
        ComparisonList<UDForm> udForms;
        try {
            log.info("start Fetch data from database from {} , to {} , date: {}", from, to, from.split(" ")[0]);
            List<IsoDTO> isoDTOS = iso8583FieldsV1987Repository.getByDatetimeCreatedAAndDateCapture(from, to, fromDateCapture, toDateCapture);
            if (isoDTOS == null || isoDTOS.isEmpty()) {
                log.warn("No UD Transaction Founded Between {} And {}", from, to);
                return null;
            }
            log.info("{} Ud Founded", isoDTOS.size());
            log.info("Start Filters IsoDTOS({})", isoDTOS.size());
            udForms = UDFilter.filter(isoDTOS);
            if (udForms == null || udForms.size() == 0) {
                log.warn("From {} IsoDTOS / {} Converted ", isoDTOS.size(), 0);
                return null;
            }
            log.info("From {} IsoDTOS / {} Converted ", isoDTOS.size(), udForms.size());
        } catch (Exception ex) {
            log.error("Exception Cause: -> {}", ex);
            return null;
        }
        return udForms;
    }

}
