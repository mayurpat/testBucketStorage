package com.sandbox.bs.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.bs.exception.ResourceNotFoundException;

@SpringBootTest
class BucketStorageDAOTest {

	
	@Autowired
	BucketStorageDAO bsDAO;
	
	@BeforeEach
	void testInit() {
		
	}
	
	@Test
	public void testGetObjectByBucket() throws ResourceNotFoundException {
		assertNotNull(bsDAO.getObjectByBucket("arizona", "1001"));
	}
	
	@Test
	public void testGetObjectByBucketResourceNotFoundExceptionBucketNotExist() throws ResourceNotFoundException {
		Assertions.assertThrows(ResourceNotFoundException.class, ()->{
			bsDAO.getObjectByBucket("nevada", "1001");
		});
	}	
	
	@Test
	public void testGetObjectByBucketResourceNotFoundExceptionObjectNotFoundInBucket() throws ResourceNotFoundException {
		Assertions.assertThrows(ResourceNotFoundException.class, ()->{
			bsDAO.getObjectByBucket("arizona", "1005");
		});
	}
	
	@Test
	public void testAddObjectByBucket() throws ResourceNotFoundException {
		assertEquals(bsDAO.addBucketObject("arizona", "1003"),"1003");
	}
	
	
	@Test
	public void testAddObjectByBucketWhenBucketDoesnotExist() throws ResourceNotFoundException {
		assertEquals(bsDAO.addBucketObject("nevada", "1000"),"1000");
	}

	@Test
	public void testAddObjectByBucketWhenBucketDoesnotExistMissmatchBucketName() throws ResourceNotFoundException {
		assertEquals(bsDAO.addBucketObject("Nevada", "1000"),"1000");
	}

	@Test
	public void testDeleteObjectByBucket() throws ResourceNotFoundException {
			bsDAO.deleteObject("arizona", "1000");
	}
	
	@Test
	public void testDeleteObjectByBucketResourceNotFoundExceptionObjectDoesntExist() throws ResourceNotFoundException {
		Assertions.assertThrows(ResourceNotFoundException.class, ()->{
			bsDAO.deleteObject("nevada", "1005");
		});
	}	
}
