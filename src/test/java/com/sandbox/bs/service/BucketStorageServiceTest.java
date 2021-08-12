package com.sandbox.bs.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.bs.exception.ResourceNotFoundException;


@SpringBootTest
class BucketStorageServiceTest {

	
	@Autowired
	BucketStorageService bsService;
	
	@BeforeEach
	void testInit() {
		
	}

	@Test
	public void testGetObjectByBucket() throws ResourceNotFoundException {
		assertEquals(bsService.getObjectByBucketId("arizona","1001"),"1001");
	}

	@Test
	public void testAddObjectByBucket() throws ResourceNotFoundException {
		assertEquals(bsService.addObjectByBucket("arizona","1006"),"1006");
	}
	
	@Test
	public void testDeleteObjectByBucket() throws ResourceNotFoundException {
		assertEquals(bsService.addObjectByBucket("texas","1000"),"1000");
	}

	@Test
	public void testGetObjectByBucketResourceNotFoundExceptionWhenObjectNotFound() throws ResourceNotFoundException {
		
		Assertions.assertThrows(ResourceNotFoundException.class, ()->{
			bsService.getObjectByBucketId("arizona","10009");
		});

	}

	@Test
	public void testDeleteObjectByBucketResourceNotFoundExceptionWhenObjectNotFound() throws ResourceNotFoundException {
		Assertions.assertThrows(ResourceNotFoundException.class, ()->{
			bsService.removeObjectByBucket("nevada", "100020");
		});
	}
	

}
