package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpError;
import org.bdigital.compose.sdk.model.request.AbstractServiceObject;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ServiceObjectResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class LCMAPIClient implements LCMAPI {
	
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";

	private final String ENTITIES_ENDPOINT = "/entities";
	
	private ComposeAPICredentials apiCredentials;
	
	public LCMAPIClient(ComposeAPICredentials apiCredentials){
		this.apiCredentials = apiCredentials;
	}
	
	
	public String getEntities(AccessToken token) throws HttpError{
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add(AUTHORIZATION, BEARER + token.getAccessToken());
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(apiCredentials.getLcm_host() + ENTITIES_ENDPOINT, HttpMethod.GET, entity, String.class);
        
		if(!response.getStatusCode().is2xxSuccessful()){
			throw new HttpError("HTTP Error - Code: " + response.getStatusCode().value() + " Cause: " + response.getStatusCode().name());
		}
		
		return response.getBody();
		
	}

}
