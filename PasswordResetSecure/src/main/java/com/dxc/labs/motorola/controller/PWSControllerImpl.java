package com.dxc.labs.motorola.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxc.labs.motorola.exception.ErrorResponse;
import com.dxc.labs.motorola.exception.UserNotFoundException;
import com.dxc.labs.motorola.model.user.Profile;
import com.dxc.labs.motorola.services.PWSResetService;
import com.dxc.labs.motorola.util.MotorolaUtil;

@Component
public class PWSControllerImpl implements PWSController{

	private static final Logger LOGGER = LoggerFactory.getLogger(PWSControllerImpl.class);

	@Autowired
	private PWSResetService pwsService;

	@Autowired
	private MotorolaUtil util;

	/**
	 * Welcome page
	 */
	public String welcome() throws UserNotFoundException{
		LOGGER.info("doStuff took input - {}" );
		LOGGER.warn("doStuff needed to warn - {}");
		LOGGER.error("doStuff encountered an error with studentId - {}");
		throw new UserNotFoundException("Custom-ErroCode1","Custom-ErrorMessage");
		//return "Welcome to RestTemplate Example.";
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler1(UserNotFoundException ex) {
		ErrorResponse err  = new ErrorResponse();

		err.setErrorCode(ex.getErroCd());
		err.setMessage(ex.getMessage());

		return new ResponseEntity<ErrorResponse>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}	

	/**
	 * Welcome page
	 */
	public String test() {
		LOGGER.info("doStuff took input - {}" );
		LOGGER.warn("doStuff needed to warn - {}");
		LOGGER.error("doStuff encountered an error with studentId - {}");
		return "Welcome to RestTemplate Example.";
	}


	public Profile getUserProfile(@PathVariable("userId") String userId) throws UserNotFoundException{
		//		Profile profile = pwsService.getUserProfileById(userId);
		Profile profile = util.getUserDetails(pwsService.getUserProfileById(userId));
		return profile;
	}

	public boolean validateSecurityAnswer(@RequestBody Profile profile) throws UserNotFoundException{
		util.vaidate(profile);
		return pwsService.validateSecurityAnswer(profile);
	}


	public ResponseEntity<Object> redirectToExternalUrl(
			@RequestParam("state") 			String state,
			@RequestParam("client_id") 		String client_id,
			@RequestParam("response_type") 	String response_type,
			@RequestParam("scope") 			String scope,
			@RequestParam("redirect_uri") 	String redirect_uri ) throws URISyntaxException{

		//?state=KA&client_id=1234&response_type=type&scope=s&redirect_uri=url
		String str = "https://www.google.com?";
		if(state !=null){
			str+="state="+state;
		}if(client_id != null){
			str+="&client_id="+client_id;
		}if(response_type !=null){
			str+="&response_type="+response_type;
		}if(scope !=null){
			str+="&scope="+scope;
		}if(redirect_uri !=null){
			str+="&redirect_uri="+redirect_uri;
		}
		LOGGER.info("URL: "+ str);
		URI url = new URI(str);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(url);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}

	//	@RequestMapping("/v1/sso")
	//	public ResponseEntity<Object> redirectToExternalUrl() throws URISyntaxException {
	//	    URI yahoo = new URI("https://www.google.com/");
	//	    HttpHeaders httpHeaders = new HttpHeaders();
	//	    httpHeaders.setLocation(yahoo);
	//	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	//	}

	//Redirect HTTP requests to HTTPS
	//Not working
	//This is an optional step in case you want to redirect your HTTP traffic to HTTPS, 
	//so that the full site becomes secured. To do that in spring boot, we need to add HTTP connector at 8080 port and 
	//then we need to set redirect port 446. So that any request in 8080 through http, it would be automatically 
	//redirected to 8443 and https.

	//To do that you just need to add below configuration.
	//	@Bean
	//	public ServletWebServerFactory servletContainer() {
	//		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	//			@Override
	//			protected void postProcessContext(Context context) {
	//				SecurityConstraint securityConstraint = new SecurityConstraint();
	//				securityConstraint.setUserConstraint("CONFIDENTIAL");
	//				SecurityCollection collection = new SecurityCollection();
	//				collection.addPattern("/*");
	//				securityConstraint.addCollection(collection);
	//				context.addConstraint(securityConstraint);
	//			}
	//		};
	//		tomcat.addAdditionalTomcatConnectors(redirectConnector());
	//		return tomcat;
	//	}
	//
	//	private Connector redirectConnector() {
	//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	//		connector.setScheme("http");
	//		connector.setPort(8080);
	//		connector.setSecure(false);
	//		connector.setRedirectPort(446);
	//		return connector;
	//	}

}
