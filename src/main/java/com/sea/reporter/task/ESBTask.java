package com.sea.reporter.task;

import com.sea.reporter.model.dto.ESBContradiction;
import com.sea.reporter.service.Services;
import com.sea.reporter.util.Tools;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Log4j2
@Service
@Configuration
public class ESBTask {
    @Qualifier("servicesImp")
    @Autowired
    Services services;
    @Qualifier("servicesImpWithCutOff")
    @Autowired
    Services CutOffToCutOffService;

    private static final String VAS_OUT_ADDRESS = System.getProperty("user.dir") + File.separator + "vasout.txt";

    @Scheduled(cron = "0 0 4 * * *")
    public void taskForCreateSettlementFor24HOfDay() {
        log.info(" Task Create Ud 24H Settlement Exactly at {}  called", Calendar.getInstance().getTime().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String to = simpleDateFormat.format(calendar.getTime()) + " 00:04:00";
        calendar.add(Calendar.DATE, -1);
        String from = simpleDateFormat.format(calendar.getTime()) + " 00:00:00";
        ESBContradiction esbContradiction = services.conflict(from, to, new File(System.getProperty("user.dir")));
        if (esbContradiction == null)
            log.warn("Conflict is empty ");
        String outPut = "";
        if (esbContradiction.getDiffTypeTotalAmount("SAMANEH") <= 30000000000l) {
            outPut = services.makeSettlement(new File(VAS_OUT_ADDRESS), new File(services.getDefaultDirectory()), Tools.EncryptStatus.ZIP_WITH_PASSWORD_AND_RAW, esbContradiction.getUdForms());
            try {
                log.info("Start compress the result");
                if (Tools.compress(new File(outPut), new File(outPut + ".zip"), Tools.EncryptStatus.ZIP)) {
                    log.info("compress process done completely");
                } else
                    log.warn("Compress process failed");
            } catch (IOException e) {
                log.error("exception in  Compress Export File {}", e);
            }
            log.info(" Task Create Ud Settlement Exactly at {}  Ended", Calendar.getInstance().getTime().toString());
        } else {
            log.warn("Contradiction is more than 3,000,000,000  rial");
        }
        taskForCreateSettlementForCutOffToCutOff();
    }


    public void taskForCreateSettlementForCutOffToCutOff() {
        log.info(" Task Create Ud CutOffToCutOff Settlement Exactly at {}  called", Calendar.getInstance().getTime().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String to = simpleDateFormat.format(calendar.getTime()) + " 00:04:00";
        calendar.add(Calendar.DATE, -1);
        String from = simpleDateFormat.format(calendar.getTime()) + " 00:00:00";
        ESBContradiction esbContradiction = CutOffToCutOffService.conflict(from, to, new File(System.getProperty("user.dir")));
        if (esbContradiction == null)
            log.warn("Conflict is empty ");
        String outPut = "";
        if (esbContradiction.getDiffTypeTotalAmount("SAMANEH") <= 30000000000l) {
            outPut = CutOffToCutOffService.makeSettlement(new File(VAS_OUT_ADDRESS), new File(CutOffToCutOffService.getDefaultDirectory()), Tools.EncryptStatus.ZIP_WITH_PASSWORD_AND_RAW, esbContradiction.getUdForms());
            try {
                log.info("Start compress the result");
                if (Tools.compress(new File(outPut), new File(outPut + ".zip"), Tools.EncryptStatus.ZIP)) {
                    log.info("compress process done completely");
                } else
                    log.warn("Compress process failed");
            } catch (IOException e) {
                log.error("exception in  Compress Export File {}", e);
            }
            log.info(" Task Create Ud Settlement Exactly at {}  Ended", Calendar.getInstance().getTime().toString());
        } else {
            log.warn("Contradiction is more than 3,000,000,000  rial");
        }
    }

    public void test(String from, String to) {
        log.info(" Task Create Ud Settlement Exactly at {}  called", Calendar.getInstance().getTime().toString());
        services.conflict(from, to, new File(System.getProperty("user.dir")));
        log.info(" Task Create Ud Settlement Exactly at {}  Ended", Calendar.getInstance().getTime().toString());

    }


}
