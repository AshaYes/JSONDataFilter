package com.datafilter.jsondatafilter.controllers;

import com.datafilter.jsondatafilter.entityes.ProcessRequest;
import com.datafilter.jsondatafilter.entityes.Result;
import com.datafilter.jsondatafilter.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/processdata")
    public ResponseEntity<Result> processData(@RequestBody ProcessRequest processRequest) {
        Result result = dataService.process(processRequest.getData(), processRequest.getCondition());
        return ResponseEntity.ok(result);
    }
}