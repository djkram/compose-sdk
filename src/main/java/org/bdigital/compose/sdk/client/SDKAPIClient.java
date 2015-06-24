package org.bdigital.compose.sdk.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.ComposeServiceObjectRegistered;
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

    public ComposeServiceObjectRegistered createServiceObject(ComposeUserAccessToken token, ComposeAbstractServiceObject serviceObject) throws HttpErrorException, HttpServerErrorException {

	RestTemplate restTemplate = new RestTemplate();
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add(AUTHORIZATION, BEARER + token.getAccessToken());
	headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

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

}
