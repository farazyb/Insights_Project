package com.sea.reporter;

import com.sea.reporter.froms.EsbForm;
import com.sea.reporter.froms.UDForm;
import com.sea.reporter.model.dto.ESBContradiction;
import com.sea.reporter.settlement.ComputeSettlement;
import com.sea.reporter.util.Tools;
import com.sea.reporter.util.comparison.ComparisonList;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Application runner that executes scheduled tasks on application startup.
 * Handles the initialization and execution of report generation tasks.
 */
@Component
public class MyRunner {

    /**
     * Executes scheduled tasks when the application starts.
     * Initializes report generation with default date range.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) throws Exception {
        ComparisonList<UDForm> udForms = new ComparisonList<>(UDForm.class);
        udForms.gsonFileToList("D:\\1401-04-22-From-2022-07-12 000000-To-2022-07-13 000400\\UDFormAll.txt");
        ComparisonList<EsbForm> esbForms;
        esbForms = new ComparisonList<>(EsbForm.class);
        esbForms.gsonFileToList("D:\\1401-04-22-From-2022-07-12 000000-To-2022-07-13 000400\\ESBFormAll.txt");
        ESBContradiction esbContradiction = new ESBContradiction(udForms, esbForms);
        System.out.println(esbContradiction.getUdForms().size());
        System.out.println(esbContradiction.getEsbForms().size());
        esbContradiction.getUdForms().
                listToText("D:\\1401-04-22-From-2022-07-12 000000-To-2022-07-13 000400" +
                        "\\UDFormAllWithDelimiter.txt");
        esbContradiction.compare();
        esbContradiction.createFile("D:\\1401-04-22-From-2022-07-12 000000-To-2022-07-13 000400\\ud.csv", "D:\\1401-04-22-From-2022-07-12 000000-To-2022-07-13 000400\\esb.csv", "D:\\1401-04-22-From-2022-07-12 000000-To-2022-07-13 000400\\diff.txt");
        ComputeSettlement computeSettlement = ComputeSettlement.getInstance(
                new File("D:\\1401-04-22-From-2022-07-12 000000-To-2022-07-13 000400" +
                        "\\vasout.txt"),
                esbContradiction.getUdForms());
        computeSettlement.compute();
        computeSettlement.checkValidation(esbContradiction.getUdForms().sumAmount());
        computeSettlement.settlementsToFile(Tools.createFile("D:\\1401-04-22-From-2022-07-12 000000-To-2022-07-13 000400\\total-out.txt"), Tools.EncryptStatus.ZIP_WITH_PASSWORD_AND_RAW);
        Tools.createTextFile(Tools.createFile("D:\\1401-04-22-From-2022-07-12 000000-To-2022-07-13 000400\\UD-out.txt"),UDForm.export(esbContradiction.getUdForms()));

        System.out.println(esbContradiction.getUdForms().size());
        System.out.println(esbContradiction.getEsbForms().size());
    }
}
