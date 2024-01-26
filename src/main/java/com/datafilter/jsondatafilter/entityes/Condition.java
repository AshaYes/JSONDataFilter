package com.datafilter.jsondatafilter.entityes;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Condition {
    private List<Map<String, Object>> include;
    private List<String> sort_by;
}
