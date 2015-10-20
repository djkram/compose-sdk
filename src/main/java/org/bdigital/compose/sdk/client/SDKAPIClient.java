package org.bdigital.compose.sdk.client;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractSOChannels;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.ComposeServiceObjectRegistered;
import org.bdigital.compose.sdk.model.stream.ComposeResponseStreams;
import org.bdigital.compose.sdk.model.stream.ComposeUploadStreamData;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class SDKAPIClient implements SDKAPI {

    private static Log logger = LogFactory.getLog(SDKAPIClient.class);

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final String SERVICEOBJECT_ENDPOINT = "/serviceobjects";

    private ComposeAPICredentials apiCredentials;

    public SDKAPIClient(ComposeAPICredentials apiCredentials) {
	this.apiCredentials = apiCredentials;
    }

    public ComposeServiceObjectRegistered createServiceObjectSDK(ComposeUserAccessToken token, ComposeAbstractServiceObject serviceObject) throws HttpErrorException, HttpServerErrorException {

	RestTemplate restTemplate = this.getRestTemplate();
	MultiValueMap<String, String> headers = this.getHeaderFromToken(token);

	HttpEntity<ComposeAbstractServiceObject> request = new HttpEntity<ComposeAbstractServiceObject>(serviceObject, headers);

	String url = apiCredentials.getSdk_host() + SERVICEOBJECT_ENDPOINT;

	try {
	    ResponseEntity<ComposeServiceObjectRegistered> response = restTemplate.postForEntity(url, request, ComposeServiceObjectRegistered.class);

	    if (!response.getStatusCode().is2xxSuccessful()) {
		throw new HttpErrorException("HTTP Error - Code: " + response.getStatusCode().value() + " Cause: " + response.getStatusCode().name());
	    }

	    return response.getBody();

	} catch (HttpStatusCodeException e ) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}

    }
    
    public String listServiceObjectSDK(ComposeUserAccessToken token) {

	RestTemplate restTemplate = this.getRestTemplate();
	HttpEntity request = new HttpEntity(this.getHeaderFromToken(token));

	String url = apiCredentials.getSdk_host() + SERVICEOBJECT_ENDPOINT;
	
	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
	
	return response.getBody();
    }
    
    public String getServiceObjectSDK(ComposeUserAccessToken token, String id) {
	
	RestTemplate restTemplate = this.getRestTemplate();
	HttpEntity request = new HttpEntity(this.getHeaderFromToken(token));

	String url = apiCredentials.getSdk_host() + SERVICEOBJECT_ENDPOINT + "/" + id;
	
	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
	
	return response.getBody();
    }
    
    
    
    private RestTemplate getRestTemplate(){
	RestTemplate restTemplate = new RestTemplate();
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	
	return restTemplate;
    }
    
    private MultiValueMap<String, String> getHeaderFromToken(ComposeUserAccessToken token){
	
	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add(AUTHORIZATION, BEARER + token.getAccessToken());
	headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	
	return headers;
	
    }
    
    private MultiValueMap<String, String> getHeaderNBFromToken(ComposeUserAccessToken token){
	
	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add(AUTHORIZATION, token.getAccessToken());
	headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	
	return headers;
	
    }

    public ComposeServiceObjectRegistered createServiceObject(ComposeUserAccessToken token, ComposeAbstractServiceObject serviceObject) throws HttpErrorException, HttpServerErrorException {
    
        RestTemplate restTemplate = this.getRestTemplate();
        MultiValueMap<String, String> headers = this.getHeaderNBFromToken(token);
    
        HttpEntity<ComposeAbstractServiceObject> request = new HttpEntity<ComposeAbstractServiceObject>(serviceObject, headers);
    
        String url = apiCredentials.getServioticy_host() + "/";
    
        try {
            ResponseEntity<ComposeServiceObjectRegistered> response = restTemplate.postForEntity(url, request, ComposeServiceObjectRegistered.class);
    
            if (!response.getStatusCode().is2xxSuccessful()) {
        	throw new HttpErrorException("HTTP Error - Code: " + response.getStatusCode().value() + " Cause: " + response.getStatusCode().name());
            }
    
            return response.getBody();
    
        } catch (HttpStatusCodeException e ) {
            String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
            logger.warn(message);
            throw new HttpErrorException(message, e);
        }
    
       }

    public ArrayList<String> listServiceObject(ComposeUserAccessToken token) {
	RestTemplate restTemplate = this.getRestTemplate();
	HttpEntity request = new HttpEntity(this.getHeaderNBFromToken(token));

	String url = apiCredentials.getServioticy_host() + "/";
	
	ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, request, ArrayList.class);
	
	return response.getBody();
    }

    public String getServiceObject(ComposeUserAccessToken token, String id) {
	RestTemplate restTemplate = this.getRestTemplate();
	HttpEntity request = new HttpEntity(this.getHeaderNBFromToken(token));

	String url = apiCredentials.getServioticy_host() + "/" + id;
	
	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
	
	return response.getBody();
    }

    public String removeServiceObject(ComposeUserAccessToken token, String id) {
	RestTemplate restTemplate = this.getRestTemplate();
	HttpEntity request = new HttpEntity(this.getHeaderNBFromToken(token));

	String url = apiCredentials.getServioticy_host() + "/" + id;
	
	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
	
	return response.getBody();
    }

    public ComposeResponseStreams getServiceObjectStreams(ComposeUserAccessToken token, String id) {

	RestTemplate restTemplate = this.getRestTemplate();
	HttpEntity request = new HttpEntity(this.getHeaderNBFromToken(token));

	String url = apiCredentials.getServioticy_host() + "/" + id + "/streams";
	
	ResponseEntity<String> responseString = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
	System.out.println(responseString);
	ResponseEntity<ComposeResponseStreams> response = restTemplate.exchange(url, HttpMethod.GET, request, ComposeResponseStreams.class);
	
	return response.getBody();
	
    }

    public String uploadDataOnServiceObjectStreams(String token, String soId, String streamId, ComposeUploadStreamData data) throws HttpErrorException {

	RestTemplate restTemplate = this.getRestTemplate();
	
	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add(AUTHORIZATION, token);
	headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

	HttpEntity<ComposeUploadStreamData> request = new HttpEntity<ComposeUploadStreamData>(data, headers);

	String url = apiCredentials.getServioticy_host() + "/" + soId + "/streams/" + streamId;

	try {
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
		
	    if (!response.getStatusCode().is2xxSuccessful()) {
		throw new HttpErrorException("HTTP Error - Code: " + response.getStatusCode().value() + " Cause: " + response.getStatusCode().name());
	    }

	    return response.getBody();

	} catch (HttpStatusCodeException e ) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}
	
	
    }

    public String getDataFromServiceObjectStreams(ComposeUserAccessToken token, String soId, String streamId, String timeModifier) {
	RestTemplate restTemplate = this.getRestTemplate();
	HttpEntity request = new HttpEntity(this.getHeaderNBFromToken(token));

	String url = apiCredentials.getServioticy_host() + "/" + soId + "/streams/" + streamId;
	
	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
	
	return response.getBody();
    }



}
