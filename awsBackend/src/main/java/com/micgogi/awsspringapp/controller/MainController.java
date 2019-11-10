package com.micgogi.awsspringapp.controller;

import com.micgogi.awsspringapp.model.AwsService;
import com.micgogi.awsspringapp.repository.AWSServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/awsdemo/")
public class MainController {
    @Autowired
    private AWSServiceRepository awsServiceRepository;
    @PostMapping("/insert")
    public ResponseEntity addData(@RequestBody AwsService awsService){
        awsServiceRepository.save(awsService);
        return new ResponseEntity("Successfully Added", HttpStatus.OK);


    }
//    @GetMapping("/get")
//    public ResponseEntity getData(){
//
//    }
}
