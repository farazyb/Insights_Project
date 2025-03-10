package com.sea.reporter.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UdBillRequestDTO {
    @JsonProperty("OrganizationType")
    private String OrganizationType;
    @JsonProperty("CompanyCode")
    private String CompanyCode;
    @JsonProperty("FromDate")
    private String FromDate;
    @JsonProperty("ToDate")
    private String ToDate;

    @Override
    public String toString() {
        return "UdBillRequestDTO{" +
                "OrganizationType='" + OrganizationType + '\'' +
                ", CompanyCode='" + CompanyCode + '\'' +
                ", FromDate='" + FromDate + '\'' +
                ", ToDate='" + ToDate + '\'' +
                '}';
    }
}
