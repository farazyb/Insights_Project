package com.sea.reporter.model.dto;

import com.sea.reporter.controller.dto.ResultData;
import com.sea.reporter.froms.EsbForm;
import com.sea.reporter.froms.UDForm;
import com.sea.reporter.util.comparison.ComparisonList;
import com.sea.reporter.util.mapper.DifferentMapper;

import org.mapstruct.factory.Mappers;


public class ESBContradiction extends Contradiction {
    private ComparisonList<UDForm> udForms;
    private ComparisonList<EsbForm> esbForms;
    private ComparisonList<UDForm> deepCopyUdForms;
    private ComparisonList<EsbForm> deepCopyEsbForms;


    public ESBContradiction(ComparisonList<UDForm> udForms, ComparisonList<EsbForm> esbForms) {
        super(udForms, esbForms);
        this.udForms = udForms;
        this.esbForms = esbForms;
        this.deepCopyUdForms = udForms.clone();
        this.deepCopyEsbForms = esbForms.clone();


    }

    @Override
    public void compare() throws Exception {
        try {

            DifferentMapper differentMapper = Mappers.getMapper(DifferentMapper.class);
            ComparisonList<UDForm> udFormRemove = new ComparisonList<>(UDForm.class);
            ComparisonList<EsbForm> esbFormRemove = new ComparisonList<>(ResultData.class);
            for (UDForm udForm : deepCopyUdForms) {
                EsbForm temp = null;
                for (EsbForm esbForm : deepCopyEsbForms) {
                    if (esbForm.getBillAmount() == udForm.getAmount() && esbForm.getTerminalId() == udForm.getTerminalCode() && esbForm.getCardNumber().equals(udForm.getSourcePan()) && esbForm.getBillId().equals(udForm.getCode1()) && esbForm.getRrn() == udForm.getRrn() && esbForm.getStan() == udForm.getStan()) {
                        udFormRemove.add(udForm);
                        esbFormRemove.add(esbForm);
                        temp = esbForm;
                        break;
                    }
                }
                if (temp != null) {
                    deepCopyEsbForms.remove(temp);
                }
            }
            deepCopyUdForms.removeAll(udFormRemove);
            deepCopyEsbForms.removeAll(esbFormRemove);
            difference.addAll(differentMapper.mapListOfDifferentToEsb(deepCopyEsbForms));
            difference.addAll(differentMapper.mapListOfDifferentToUdForm(deepCopyUdForms));
            if (deepCopyUdForms.size() > 0) {
                if (!udForms.removeAll(deepCopyUdForms)) {
                    throw new Exception("Cant Remove Ud Difference From List");
                }
            }

        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public ComparisonList<UDForm> getUdForms() {
        return udForms;
    }

    private void setUdForms(ComparisonList<UDForm> udForms) {
        this.udForms = udForms;
    }

    public ComparisonList<EsbForm> getEsbForms() {
        return esbForms;
    }

    private void setEsbForms(ComparisonList<EsbForm> esbForms) {
        this.esbForms = esbForms;
    }
}
