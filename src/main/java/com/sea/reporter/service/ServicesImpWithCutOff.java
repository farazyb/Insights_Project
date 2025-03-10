package com.sea.reporter.service;

import com.sea.reporter.controller.dto.ResultData;
import com.sea.reporter.froms.EsbForm;
import com.sea.reporter.froms.UDForm;
import com.sea.reporter.model.dto.ESBContradiction;
import com.sea.reporter.settlement.ComputeSettlement;
import com.sea.reporter.util.CalendarConversion;
import com.sea.reporter.util.StringUtils;
import com.sea.reporter.util.Tools;
import com.sea.reporter.util.comparison.ComparisonList;
import com.sea.reporter.util.mapper.ESBMapper;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Log4j2
@Component
public class ServicesImpWithCutOff implements Services {

    private String defaultDirectory;
    @Autowired
    ESBService esbService;
    @Autowired
    ISOService isoService;
    private File createdPath;

    @Override
    public ESBContradiction conflict(String from, String to, File outPut) {
        ESBContradiction esbContradiction = null;
        this.defaultDirectory = outPut.getPath();
        if (!outPut.isDirectory()) {
            defaultDirectory = outPut.getParent();
        }
        try {
            ESBMapper esbMapper = Mappers.getMapper(ESBMapper.class);
            // Convert input dates to capture dates
            String fromDateCapture = dateToDateCapture(from);
            String toDateCapture = dateToDateCapture(to);

            // Set time window for ESB data (22:00 previous day to 04:00 current day)
            String esbFrom = changeDate(from, -1) + " 22:00:00"; 
            String esbTo = StringUtils.getDatePart(to) + " 04:00:00";

            // Use same time window for ISO data
            String isoFrom = esbFrom;
            String isoTo = esbTo;
            List<ResultData> resultDataList = esbService.callThirdparty(esbFrom, esbTo);
            if (resultDataList == null) {
                log.warn("ESB Data -> is null Or Not Founded Between {} And {}", esbFrom, esbTo);
                return null;
            }
            ComparisonList<EsbForm> esbData = new ComparisonList<>(esbMapper.map(resultDataList), EsbForm.class);
            if (esbData == null || esbData.isEmpty()) {
                log.warn("ESB Data  cant map-> is null Or Not Founded Between {} And {}", esbFrom, esbTo);
                return null;
            }
            log.info("Start Calling Iso service With Date Capture  From {} and To {} and Date capture {} and {}", isoFrom, isoTo, fromDateCapture, toDateCapture);
            ComparisonList<UDForm> udForms = isoService.getDataByDate(isoFrom, isoTo, fromDateCapture, toDateCapture);
            if (udForms == null || udForms.isEmpty()) {
                log.warn("UD Not Founded Between {} And {} DateCapture {} and {} ", isoFrom, isoTo, fromDateCapture, toDateCapture);
                return null;
            }
            esbContradiction = new ESBContradiction(udForms, esbData);
            try {
                esbContradiction.getUdForms().listToGsonFile(createPath(".txt", "UDFormAll", from, to));
                esbContradiction.getEsbForms().listToGsonFile(createPath(".txt", "ESBFormAll", from, to));
                esbContradiction.compare();
            } catch (Exception ex) {
                log.error("Exception in compare  -> {}", ex);

            }
            try {
                esbContradiction.createFile(createPath(".csv", "UDForm", from, to), createPath(".csv", "EsbForm", from, to), createPath(".csv", "Diff", from, to));
            } catch (Exception ex) {
                log.error("Fail On Create Excel List  -> {}", ex);
            }

        } catch (Exception e) {
            log.error("Exception in Conflict  -> {}", e);
        }

        return esbContradiction;
    }

    @Override
    public String makeSettlement(File vasOut, File outPut, Tools.EncryptStatus encryptStatus, ComparisonList<UDForm> udForms) {
        ComputeSettlement computeSettlement = null;
        if (udForms == null || udForms.size() == 0)
            return null;
        try {
            computeSettlement = ComputeSettlement.getInstance(vasOut, udForms);
        } catch (Exception ex) {
            log.error("Exception in getting instance from ComputeSettlement {}", ex);
        }

        try {
            computeSettlement.compute();
            if (!computeSettlement.checkValidation(udForms.sumAmount())) {
                throw new Exception("Ud and Settlement Are not Equal");

            }
            computeSettlement.settlementsToFile(new File(outPut.getPath() + File.separator+"Total-out.txt"), encryptStatus);
            Tools.createTextFile(new File(outPut.getPath() + File.separator+"ud-out.txt"), UDForm.export(udForms));
        } catch (Exception ex) {
            log.error("Exception in Compute Settlement {}", ex);
        }
        return outPut.getPath();
    }
    @Override
    public String createPath(String extension, String fileName, String from, String to) {
        String userDir = defaultDirectory;
        CalendarConversion calendarConversion = new CalendarConversion();
        String today = calendarConversion.getIranianDate().replace('/', '-');
        String path = userDir + File.separator + (today + "-From-" + from + "-To-" + to).trim().replace(":", "") + File.separator + fileName + extension;
        createdPath = new File(path);
        log.info("  Path Created successfully ({})", path);
        return path;
    }

    public String changeDate(String date, int changeDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newDate = simpleDateFormat.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(newDate);
            calendar.add(Calendar.DATE, changeDate);
            String newDateForm = simpleDateFormat.format(calendar.getTime());
            return newDateForm;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String dateToDateCapture(String date) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateCapture = "0000";
        try {
            Date dateObject = simpleDateFormat.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateObject);
            dateCapture = StringUtils.padleft((calendar.get(Calendar.MONTH) + 1) + "", 2, '0') + StringUtils.padleft((calendar.get(Calendar.DAY_OF_MONTH)) + "", 2, '0');
            return dateCapture;
        } catch (ParseException e) {
            throw new Exception(e);
        }
    }

    @Override
    public String getDefaultDirectory() {
        return createdPath.getParent();
    }
}
