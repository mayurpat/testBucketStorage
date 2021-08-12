package com.sandbox.bs.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.bs.exception.ResourceNotFoundException;
import com.sandbox.bs.service.BucketStorageService;



@RestController
@RequestMapping(value = "/api/v1/")
public class BucketStorageController {

	public static final Logger LOGGER = LoggerFactory.getLogger(BucketStorageController.class);
	
	@Autowired
	BucketStorageService bsService;

	@GetMapping("/objects/{bucket}/{objectID}")
	public ResponseEntity<String> getObjectByBucketId(@PathVariable(value = "bucket") String bucket,
			@PathVariable(value = "objectID") String objectId) throws ResourceNotFoundException {
		LOGGER.info("Calling getObjectByBucketId()... bucket : {}, objectID : {}", bucket, objectId.toString());
		
		String objId = bsService.getObjectByBucketId(bucket, objectId);
		LOGGER.info("Calling getObjectByBucketId()... : SUCCESS");
		return ResponseEntity.ok().body(objId);
	}

	@PutMapping("/objects/{bucket}/{objectID}")
	public ResponseEntity<String> addObjectByBucket(@PathVariable(value = "bucket") String bucket,
			@PathVariable(value = "objectID") String objectId) throws ResourceNotFoundException {
		LOGGER.info("Calling addObjectByBucket()...");
		return new ResponseEntity<>(bsService.addObjectByBucket(bucket, objectId), HttpStatus.CREATED);
	}

	@DeleteMapping("/objects/{bucket}/{objectID}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(value = "bucket") String bucket,
			@PathVariable(value = "objectID") String objectId) throws ResourceNotFoundException {
		LOGGER.info("Calling deleteEmployee()...");
		bsService.removeObjectByBucket(bucket, objectId);
		return ResponseEntity.ok().body(objectId);
	}

}
