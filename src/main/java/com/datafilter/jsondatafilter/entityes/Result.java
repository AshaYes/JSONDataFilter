package com.datafilter.jsondatafilter.entityes;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Result {
    private List<Map<String, Object>> result;
}
