package com.micgogi.awsspringapp.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.micgogi.awsspringapp.model.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
public class S3Controller {
    private AmazonS3Service amazonS3Service;

    @Autowired
    public S3Controller(AmazonS3Service amazonS3Service){
        this.amazonS3Service = amazonS3Service;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam(value = "file") MultipartFile file){
        return this.amazonS3Service.uploadFile(file);

    }
    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestParam (value = "filename") String filename){
        System.out.println(filename);
        return this.amazonS3Service.deleteFileFromS3Bucket(filename);
    }

}
