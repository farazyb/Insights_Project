package com.sea.reporter.settlement.dto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ApportionmentType {
    Map<String, List<Apportionment>> fetchInformation(File vasOut) throws Exception;

    boolean check(List<Apportionment> apportionment);

}
