package com.sandbox.bs.controller;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class BucketStorageControllerTest {

	@Autowired(required = true)
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("/Get Object By Bucket Name - SUCCESS 200 OK")
	void testGetObjectByBucketSuccess() {
		ResponseEntity<String> response = this.restTemplate.getForEntity("/api/v1/objects/arizona/1000", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	@DisplayName("/Get Object By Bucket Name - 400 OBJECT NOT FOUND")
	void testGetObjectByBucketObjectNotFound() {
		ResponseEntity<String> response = this.restTemplate.getForEntity("/api/v1/objects/arizona/10008", String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("/Add Object By Bucket Name - CREATED 201 OK")
	void testAddObjectByBucket() {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity < String > entity = new HttpEntity < String > ("parameters", headers);

	        ResponseEntity < String > response = restTemplate.exchange("http://localhost:8080/api/v1/objects/neveda/1000", HttpMethod.PUT, entity,String.class);

		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@DisplayName("/Delete Object By Bucket Name - SUCCESS 200 OK")
	void testDeleteObjectByBucket() {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity < String > entity = new HttpEntity < String > ("parameters", headers);

	        ResponseEntity < String > response = restTemplate.exchange("http://localhost:8080/api/v1/objects/texas/1000", HttpMethod.DELETE, entity,String.class);

		
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("/Delete Object By Bucket Name - 400 OBJECT NOT FOUND")
	void testDeleteObjectByBucketObjectNotFound() {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity < String > entity = new HttpEntity < String > ("parameters", headers);

	        ResponseEntity < String > response = restTemplate.exchange("http://localhost:8080/api/v1/objects/newyowrk/1000", HttpMethod.DELETE, entity,String.class);
	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
