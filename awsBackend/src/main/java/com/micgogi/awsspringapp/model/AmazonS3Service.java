package com.micgogi.awsspringapp.model;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.DataFormatException;

@Service
public class AmazonS3Service {
    private AmazonS3 amazonS3Client;

    @Value("${amazon.aws.s3endpoint}")
    private String endPointUrl;
    @Value("${amazon.aws.bucketname}")
    private String bucket;
    @Value("${amazon.aws.accesskey}")
    private String accessKey;
    @Value("${amazon.aws.secretkey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon(){
        AWSCredentials awsCredentials = new BasicAWSCredentials(this.accessKey,this.secretKey);
        this.amazonS3Client = new AmazonS3Client(awsCredentials);
    }
    public String uploadFile(MultipartFile multipartFile){
        String fileUrl = "";
        try{
            File file = convertMultipartFileToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endPointUrl+"/"+bucket+"/"+fileName;
            uploadFileToS3Bucket(fileName,file);
            file.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileUrl;
    }

    private void uploadFileToS3Bucket(String fileName, File file) {

        amazonS3Client.putObject(new PutObjectRequest(bucket,fileName,file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private String generateFileName(MultipartFile multipartFile) {
        return new Date().getTime()+"-"+multipartFile.getOriginalFilename().replace(" ","-");
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return convFile;
    }

    public String deleteFileFromS3Bucket(String filename){

        System.out.println(filename);
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket,filename));
        return "HAKUNA MATATA";
    }

}
