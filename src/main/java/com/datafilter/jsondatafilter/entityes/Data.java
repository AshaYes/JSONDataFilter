package com.datafilter.jsondatafilter.entityes;

import java.util.List;
import java.util.Map;

@lombok.Data
public class Data {
    List<Map<String, Object>> data;

    public Data(List<Map<String, Object>> data) {
        this.data = data;
    }

    public Data() {
    }
}
