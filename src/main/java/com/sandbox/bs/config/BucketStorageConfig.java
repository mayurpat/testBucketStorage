package com.sandbox.bs.config;


import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.Map.Entry;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class BucketStorageConfig {

	
	public static final Logger LOGGER = LoggerFactory.getLogger(BucketStorageConfig.class);
	

	@Bean
	public Map<String,HashSet<String>> bucketStorage(){
		Map<String,HashSet<String>> bucketStorage = new HashMap<String, HashSet<String>>();
		HashSet<String> az = new HashSet<String>();
		HashSet<String> tx = new HashSet<String>();
		
		az.add("1000");
		az.add("1001");
		
		tx.add("1000");
		tx.add("1001");
		
		bucketStorage.put("arizona", az);
		bucketStorage.put("texas", tx);
		
		
		LOGGER.info("Initilazing Bucket Storage complete.... {} ", bucketStorage.size());
		
		for (Entry<String, HashSet<String>> entry : bucketStorage.entrySet()) {
		    LOGGER.debug(entry.getKey() + ":" + entry.getValue().toString());
		}

		return bucketStorage;
	}
	
	   @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
	
}
