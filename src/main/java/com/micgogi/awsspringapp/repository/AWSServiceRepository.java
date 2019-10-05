package com.micgogi.awsspringapp.repository;

import com.micgogi.awsspringapp.model.AwsService;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface AWSServiceRepository extends CrudRepository<AwsService, String> {
}
