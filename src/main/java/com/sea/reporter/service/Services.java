package com.sea.reporter.service;


import com.sea.reporter.froms.UDForm;
import com.sea.reporter.model.dto.ESBContradiction;
import com.sea.reporter.util.Tools;
import com.sea.reporter.util.comparison.ComparisonList;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface Services {
    ESBContradiction conflict(String from, String to, File OutPut);
    String makeSettlement(File vasOut, File OutPut, Tools.EncryptStatus encryptStatus, ComparisonList<UDForm> udForms);
    String createPath(String extension, String fileName, String from, String to) ;
    public String getDefaultDirectory();

}
