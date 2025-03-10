package com.sea.reporter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sea.reporter.controller.dto.ResultData;
import com.sea.reporter.controller.dto.UdBillRequestDTO;
import com.sea.reporter.controller.dto.UdBillResponseDTO;
import com.sea.reporter.controller.requests.ThirdpartyReport;
import com.sea.reporter.util.RSAUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.List;

/**
 * Service class for handling ESB (Enterprise Service Bus) operations.
 * Manages communication with the Organization's ESB system for bill inquiries and data retrieval.
 */
@Service
@Log4j2
public class ESBService {

    private static final String ORGANIZATION_TYPE = "0";
    private static final String COMPANY_CODE = "0650";

    @Value("${organization.api.key}")
    private String apiKey;

    @Autowired
    private ThirdpartyReport thirdpartyReport;

    @Autowired
    private RSAUtil rsaUtil;

    /**
     * Calls the Organization's ESB service to retrieve bill data for a specified date range.
     *
     * @param from Start date in yyyy-MM-dd format
     * @param to End date in yyyy-MM-dd format
     * @return List of result data from the ESB service
     * @throws IOException if there's an I/O error
     * @throws NoSuchAlgorithmException if the encryption algorithm is not available
     * @throws InvalidKeyException if the API key is invalid
     * @throws SignatureException if there's an error with the digital signature
     * @throws InvalidKeySpecException if the key specification is invalid
     */
    public List<ResultData> callThirdparty(String from, String to) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {
        return getResultData(from, to, thirdpartyReport);
    }

    /**
     * Retrieves and processes result data from the ESB service.
     *
     * @param from Start date
     * @param to End date
     * @param thirdpartyReport The thirdparty report client
     * @return List of processed result data
     */
    private List<ResultData> getResultData(String from, String to, ThirdpartyReport thirdpartyReport) {
        List<ResultData> resultData;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            log.info("Starting Calling Thirdparty-ESB WebService ...... ");
            log.info("From : {} , TO : {}", from, to);
            UdBillRequestDTO udBillRequestDTO = new UdBillRequestDTO(ORGANIZATION_TYPE, COMPANY_CODE, from, to);
            String objectText = objectMapper.writeValueAsString(udBillRequestDTO);
            log.info("JSON OBJECT TO TEXT : {}", objectText);
            String signBody = "POST#/Api/InquiryBillVAS#" + apiKey + "#" + objectText;
            log.info("REQUEST SIGN BODY : {}", signBody);
            String sign = rsaUtil.sha1(signBody, rsaUtil.sha1(signBody, ""));
            log.info("SIGN : {}", sign);
            UdBillResponseDTO udBillResponseDTO = thirdpartyReport.ThirdpartyRequest(apiKey, sign, udBillRequestDTO);
            resultData = udBillResponseDTO.getResultData();
            if (!resultData.isEmpty()) {
                log.info("{}  TRANSACTIONS FROM ESB FOUNDED", resultData.size());
            } else {
                log.warn("NO TRANSACTION FOUND Between {} And {}", from, to);
                return null;
            }
        } catch (Exception ex) {
            log.error("FAILED TO SEND REQUEST :{} ", ex);
            return null;
        }
        log.info("CALLING SERVICE ENDED SUCCESSFULLY");
        return resultData;
    }

    /**
     * Writes ESB export data to a file.
     *
     * @param resultData List of result data to write
     * @param from Start date
     * @param to End date
     * @throws IOException if there's an I/O error
     */
    private void writeEsbExportToFile(List<ResultData> resultData, String from, String to) throws IOException {
        BufferedWriter bufferedWriter = null;
        String userDirectory = System.getProperty("user.dir");
        String fileName = userDirectory + File.separator + from + "-" + to + "-" + Calendar.getInstance().getTimeInMillis() + ".txt";
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            log.info("Happily File({}) Location Created", fileName);
        } catch (IOException ioEx) {
            log.error("Failed To Create File Cause :{}", ioEx);
        }
        if (bufferedWriter != null) {
            log.info("START CREATE FILE .....");
            BufferedWriter finalBufferedWriter = bufferedWriter;
            resultData.forEach(s -> {
                try {
                    finalBufferedWriter.write(s.toString());
                    finalBufferedWriter.newLine();
                } catch (IOException e) {
                    log.error("FAILED TO CREATE FILE CAUSE :{} ", e);
                }
            });
            bufferedWriter.close();
            log.info("FILE {} CREATED SUCCESSFULLY .....", fileName);
        }
    }
}
