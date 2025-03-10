package com.sea.reporter.controller.requests;

import com.sea.reporter.configuration.FeignClientConfiguration;
import com.sea.reporter.controller.dto.UdBillRequestDTO;
import com.sea.reporter.controller.dto.UdBillResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

//@FeignClient(name = "Dot",url="localhost:9000",
//        configuration = FeignClientConfiguration.class)
@FeignClient(name = "Thirdparty", url="${organization.api.url}",
        configuration = FeignClientConfiguration.class)
public interface ThirdpartyReport {
    @PostMapping(value = "/Api/InquiryBillVAS",consumes = "application/json")
     UdBillResponseDTO ThirdpartyRequest(@RequestHeader("ApiKey") String apiKey,
                                           @RequestHeader("Signature") String signature,
                                           @RequestBody UdBillRequestDTO udBillRequestDTO);



}
