package com.datafilter.jsondatafilter.entityes;

@lombok.Data
public class ProcessRequest {
    private Data data;
    private Condition condition;

    public ProcessRequest(Data data, Condition condition) {
        this.data = data;
        this.condition = condition;
    }

    public ProcessRequest() {
    }
}
