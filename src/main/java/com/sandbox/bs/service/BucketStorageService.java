package com.sandbox.bs.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sandbox.bs.dao.BucketStorageDAO;
import com.sandbox.bs.exception.ResourceNotFoundException;


@Service
public class BucketStorageService {

	public static final Logger LOGGER = LoggerFactory.getLogger(BucketStorageService.class);
 
	@Autowired
	private BucketStorageDAO bsDAO;
	
	public String getObjectByBucketId(String bucket, String objectId)  throws ResourceNotFoundException{
		LOGGER.info("Calling service getObjectByBucketId() bucket : {}, objectID : {} ", bucket, objectId.toString());
		return bsDAO.getObjectByBucket(bucket, objectId);
	}


	public String addObjectByBucket(String bucket, String objectId) {
		LOGGER.info("Calling service addObjectByBucket()");
		return bsDAO.addBucketObject(bucket, objectId);
	}


	public void removeObjectByBucket(String bucket, String objectId) throws ResourceNotFoundException{
		LOGGER.info("Calling service removeObjectByBucket()");
		bsDAO.deleteObject(bucket, objectId);
		
	}
}
