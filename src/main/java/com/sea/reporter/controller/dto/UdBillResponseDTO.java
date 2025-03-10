package com.sea.reporter.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sea.reporter.util.comparison.ComparisonList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UdBillResponseDTO {
    @JsonProperty("RsCode")
    String RsCode;
    @JsonProperty("IsSuccess")
    String IsSuccess;
    @JsonProperty("Message")
    String Message;
    @JsonProperty("ResultData")
    List<ResultData> resultData;
    @JsonProperty("ErrorList")
    List<Error> errorList;



}
