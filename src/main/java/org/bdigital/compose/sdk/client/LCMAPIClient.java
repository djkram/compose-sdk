package org.bdigital.compose.sdk.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ComposeEntities;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class LCMAPIClient implements LCMAPI {

    private static Log logger = LogFactory.getLog(LCMAPIClient.class);
    
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final String ENTITIES_ENDPOINT = "/entities";

    private ComposeAPICredentials apiCredentials;

    public LCMAPIClient(ComposeAPICredentials apiCredentials) {
	this.apiCredentials = apiCredentials;
    }

    public ComposeEntities getEntities(AccessToken token) throws HttpErrorException {

	RestTemplate restTemplate = new RestTemplate();
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add(AUTHORIZATION, BEARER + token.getAccessToken());

	HttpEntity<String> entity = new HttpEntity<String>(headers);

	try {
	    ResponseEntity<ComposeEntities> response = restTemplate.exchange(apiCredentials.getLcm_host() + ENTITIES_ENDPOINT, HttpMethod.GET, entity, ComposeEntities.class);

	    if (!response.getStatusCode().is2xxSuccessful()) {
		throw new HttpErrorException("HTTP Error - Code: " + response.getStatusCode().value() + " Cause: " + response.getStatusCode().name());
	    }

	    return response.getBody();

	} catch (HttpStatusCodeException e) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}

    }

}
