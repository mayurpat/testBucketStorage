package com.sandbox.bs.dao;


import java.util.HashSet;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sandbox.bs.exception.ResourceNotFoundException;


@Repository
public class BucketStorageDAO {

	public static final Logger LOGGER = LoggerFactory.getLogger(BucketStorageDAO.class);
	
	
	
	@Autowired
	Map<String,HashSet<String>> bucketStorage;


	public String getObjectByBucket(String bucket, String objectId) throws ResourceNotFoundException{
		
		LOGGER.debug("Calling getObjectByBucket() .... bucketStorage : {} ", bucketStorage.values());

		if(null == bucketStorage.get(bucket) || !(bucketStorage.get(bucket).contains(objectId.toString()))) {
			LOGGER.debug("Calling getObjectByBucket() .... Object Not Found");
			 throw new ResourceNotFoundException("Object Not Found");
		}
		if(bucketStorage.get(bucket).contains(objectId.toString())) {
			LOGGER.debug("Calling getObjectByBucket() .... Object Found");
			return objectId;
		}
		return null;
	}
	public  String addBucketObject(String bucket, String objectId) {

		if (null == bucketStorage.get(bucket)) {
			LOGGER.debug("Calling addBucketObject() .... Bucket Not Found, creating bucket and adding Object");
			HashSet<String> hSet = new HashSet<String>();
			hSet.add(objectId.toString());
			bucketStorage.put(bucket, hSet);
			return objectId;
		} else {
			HashSet<String> objList = bucketStorage.get(bucket);
			if (objList.isEmpty()) {
				LOGGER.debug("Calling addBucketObject() .... Bucket is empty, adding Object");
				objList.add(objectId.toString());
			} else {
				objList.add(objectId.toString());
			}
		}

		return objectId;
	}

	public  void deleteObject(String bucket, String objectId)throws ResourceNotFoundException {

		if (null != bucketStorage.get(bucket) && !bucketStorage.get(bucket).isEmpty()
				&& bucketStorage.get(bucket).contains(objectId.toString())) {
			LOGGER.debug("Calling deleteObject() .... Object Found In Bucket , deleting Object");
		    bucketStorage.get(bucket).remove(objectId.toString());
		}else {
			 throw new ResourceNotFoundException("Object Not Found");
		}
		
	}

}