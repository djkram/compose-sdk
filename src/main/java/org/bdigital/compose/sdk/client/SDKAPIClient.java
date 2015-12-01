package org.bdigital.compose.sdk.client;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.response.ComposeSORegisteredResponse;
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

    public ComposeSORegisteredResponse createServiceObjectSDK(ComposeUserAccessToken token, ComposeAbstractServiceObject serviceObject) throws HttpErrorException, HttpServerErrorException {

	RestTemplate restTemplate = this.getRestTemplate();
	MultiValueMap<String, String> headers = this.getHeaderFromToken(token);

	HttpEntity<ComposeAbstractServiceObject> request = new HttpEntity<ComposeAbstractServiceObject>(serviceObject, headers);

	String url = apiCredentials.getSdk_host() + SERVICEOBJECT_ENDPOINT;

	try {
	    ResponseEntity<ComposeSORegisteredResponse> response = restTemplate.postForEntity(url, request, ComposeSORegisteredResponse.class);

	    return response.getBody();

	} catch (HttpStatusCodeException e) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}

    }

    public Set<String> listServiceObjectSDK(ComposeUserAccessToken token) throws HttpErrorException {

	RestTemplate restTemplate = this.getRestTemplate();
	HttpEntity request = new HttpEntity(this.getHeaderFromToken(token));

	String url = apiCredentials.getSdk_host() + SERVICEOBJECT_ENDPOINT;

	try {

	    ResponseEntity<HashSet> response = restTemplate.exchange(url, HttpMethod.GET, request, HashSet.class);

	    return response.getBody();

	} catch (HttpStatusCodeException e) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}

    }

    public ComposeSORegisteredResponse getServiceObjectSDK(ComposeUserAccessToken token, String id) throws HttpErrorException {

	RestTemplate restTemplate = this.getRestTemplate();
	HttpEntity request = new HttpEntity(this.getHeaderFromToken(token));

	String url = apiCredentials.getSdk_host() + SERVICEOBJECT_ENDPOINT + "/" + id;

	try {

	    ResponseEntity<ComposeSORegisteredResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, ComposeSORegisteredResponse.class);

	    return response.getBody();

	} catch (HttpStatusCodeException e) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}
    }

    public void deleteServiceObjectSDK(ComposeUserAccessToken token, String id) throws HttpErrorException {
	RestTemplate restTemplate = this.getRestTemplate();
	HttpEntity request = new HttpEntity(this.getHeaderFromToken(token));

	String url = apiCredentials.getSdk_host() + SERVICEOBJECT_ENDPOINT + "/" + id;

	try {

	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);

	} catch (HttpStatusCodeException e) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}

    }

    private RestTemplate getRestTemplate() {
	RestTemplate restTemplate = new RestTemplate();
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	return restTemplate;
    }

    private MultiValueMap<String, String> getHeaderFromToken(ComposeUserAccessToken token) {

	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add(AUTHORIZATION, BEARER + token.getAccessToken());
	headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

	return headers;

    }

    private MultiValueMap<String, String> getHeaderNBFromToken(ComposeUserAccessToken token) {

	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add(AUTHORIZATION, token.getAccessToken());
	headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

	return headers;

    }

}
