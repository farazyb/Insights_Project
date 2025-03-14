package com.sea.reporter.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    @JsonProperty("Code")
    private String Code;
    @JsonProperty("Desc")
    private String Desc;
    @JsonProperty("ParamName")
    private String ParamName;
    @JsonProperty("ParamPath")
    private String ParamPath;
}
