package com.micgogi.awsspringapp;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.google.gson.Gson;
import com.micgogi.awsspringapp.model.AwsService;
import com.micgogi.awsspringapp.repository.AWSServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class AwsspringappApplication implements CommandLineRunner {
	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private AWSServiceRepository awsServiceRepository;
	public static void main(String[] args) {
		SpringApplication.run(AwsspringappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		CreateTableRequest tableRequest = dynamoDBMapper
				.generateCreateTableRequest(AwsService.class);

		tableRequest.setProvisionedThroughput(
				new ProvisionedThroughput(1L, 1L));

		TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);

		AwsService awsService = new AwsService();
		awsService.setServiceName("AWS DynamoDB");
		awsService.setServiceHomePageUrl("https://aws.amazon.com/dynamodb/?nc2=h_m1");

		awsService = awsServiceRepository.save(awsService);

		//logger.info("Saved AwsService object: " + new Gson().toJson(awsService));

		String awsServiceId = awsService.getId();


		System.out.println("AWS Service ID: " + awsServiceId);
		Optional<AwsService> awsServiceQueried = awsServiceRepository.findById(awsServiceId);

		if (awsServiceQueried.get() != null) {

			System.out.println("Queried object: " + new Gson().toJson(awsServiceQueried.get()));
		}

		Iterable<AwsService> awsServices = awsServiceRepository.findAll();

		for (AwsService awsServiceObject : awsServices) {

			System.out.println("List object: " + new Gson().toJson(awsServiceObject));
		}


	}
}
