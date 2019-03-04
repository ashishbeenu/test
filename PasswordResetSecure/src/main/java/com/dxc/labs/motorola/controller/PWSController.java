package com.dxc.labs.motorola.controller;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.labs.motorola.exception.UserNotFoundException;
import com.dxc.labs.motorola.model.user.Profile;

@RestController
@RequestMapping("/api")
public interface PWSController {

	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String welcome() throws UserNotFoundException;


	@RequestMapping(value="/v1/test",method=RequestMethod.GET)
	public String test();

	@RequestMapping(value="/v1/user/profile/{userId}",method=RequestMethod.GET)
	public Profile getUserProfile(@PathVariable("userId") String userId) throws UserNotFoundException;
	
	@RequestMapping(value="/v1/user/profile",method=RequestMethod.POST)
	public boolean validateSecurityAnswer(@RequestBody Profile profile) throws UserNotFoundException;
	
//	@RequestMapping(value="/v1/user/newprofile",method=RequestMethod.POST)
//	public boolean createUpdateProfile(@RequestBody Profile profile) throws UserNotFoundException;
	
	@RequestMapping(value="/v1/sso",method=RequestMethod.GET)
	public ResponseEntity<Object> redirectToExternalUrl(
			@RequestParam("state") 			String state,
			@RequestParam("client_id") 		String client_id,
			@RequestParam("response_type") 	String response_type,
			@RequestParam("scope") 			String scope,
			@RequestParam("redirect_uri") 	String redirect_uri ) throws URISyntaxException;

	
//	@RequestMapping("/v1/sso")
//	public ResponseEntity<Object> redirectToExternalUrl() throws URISyntaxException;
	
	
	//https://github.com/joelittlejohn/jsonschema2pojo/blob/master/jsonschema2pojo-maven-plugin/src/test/resources/example/schema/formattedProperties.json
	//	https://www.liquid-technologies.com/online-schema-to-json-converter
	//	http://www.littlebigextra.com/how-to-convert-json-schema-to-java-classes-using-maven-plugin/#Introduction
	//	http://www.littlebigextra.com/how-to-convert-json-schema-to-java-classes-using-maven-plugin/#Add-jsonschema2pojo-maven-plugin-in-POMXML
	//Validation 
	//https://www.compose.com/articles/document-validation-in-mongodb-by-example/
	//	https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#dependencies.spring-boot
	//	http://www.baeldung.com/spring-data-mongodb-tutorial

	//mongodb CRUD
	//	http://www.javased.com/?api=org.springframework.data.mongodb.core.query.Update
	//	https://www.programcreek.com/java-api-examples/?api=org.springframework.data.mongodb.core.query.Criteria
}
