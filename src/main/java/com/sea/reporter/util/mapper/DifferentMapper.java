package com.sea.reporter.util.mapper;

import com.sea.reporter.controller.dto.ResultData;
import com.sea.reporter.froms.EsbForm;
import com.sea.reporter.froms.UDForm;
import com.sea.reporter.model.dto.Different;
import com.sea.reporter.util.comparison.ComparisonList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DifferentMapper {

    @Mappings({
            @Mapping(target = "amount", source = "billAmount"),
            @Mapping(target = "rrn", source = "rrn"),
            @Mapping(target = "stan", source = "stan"),
            @Mapping(target = "cardNumber", source = "cardNumber"),
            @Mapping(target = "terminalId", source = "terminalId"),
            @Mapping(target = "additionalData1", source = "billAmount"),
            @Mapping(target = "additionalData2", source = "billId"),
            @Mapping(target = "date", source = "dateTime"),
            @Mapping(target = "type", constant = "ESB")})
    Different mapEsbToDifferent(EsbForm resultData);

    List<Different> mapListOfDifferentToEsb(List<EsbForm> resultDataList);

    default ComparisonList<Different> mapListOfDifferentToEsb(ComparisonList<EsbForm> resultDataList) throws ClassNotFoundException {
        if (resultDataList == null) {
            return null;
        }
        ComparisonList<Different> difference = new ComparisonList<>(ResultData.class);
        for (EsbForm resultData : resultDataList) {
            difference.add(mapEsbToDifferent(resultData));
        }
        return difference;
    }

    @Mappings({
            @Mapping(target = "amount", source = "amount"),
            @Mapping(target = "rrn", source = "rrn"),
            @Mapping(target = "stan", source = "stan"),
            @Mapping(target = "cardNumber", source = "sourcePan"),
            @Mapping(target = "terminalId", source = "terminalCode"),
            @Mapping(target = "additionalData1", source = "code2"),
            @Mapping(target = "additionalData2", source = "code1"),
            @Mapping(target = "date", source = "date"),
            @Mapping(target = "type", constant = "SAMANEH")})
    Different mapUdFormToDifferent(UDForm udForm);

    List<Different> mapListOfDifferentToUdForm(List<UDForm> udForms);

    default ComparisonList<Different> mapListOfDifferentToUdForm(ComparisonList<UDForm> udForms) throws ClassNotFoundException {
        if (udForms == null) {
            return null;
        }
        ComparisonList<Different> difference = new ComparisonList<>(ResultData.class);
        for (UDForm udForm : udForms) {
            difference.add(mapUdFormToDifferent(udForm));
        }
        return difference;
    }

}
