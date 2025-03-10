package com.sea.reporter.util.mapper;

import com.sea.reporter.controller.dto.ResultData;
import com.sea.reporter.froms.EsbForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ESBMapper {


    @Mappings({
            @Mapping(target ="rrn" ,source = "RRN"),
            @Mapping(target ="stan" ,source = "SAB")
    })
    EsbForm map(ResultData resultData);
    List<EsbForm> map(List<ResultData> resultDataList);
}
