package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpError;
import org.bdigital.compose.sdk.model.request.AbstractServiceObject;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ServiceObjectResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SDKAPIClient implements SDKAPI {
	
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";

	private final String SERVICEOBJECT_ENDPOINT = "/serviceobjects";
	
	private ComposeAPICredentials apiCredentials;
	
	public SDKAPIClient(ComposeAPICredentials apiCredentials){
		this.apiCredentials = apiCredentials;
	}
	
	public ServiceObjectResponse createServiceObject(AccessToken token, AbstractServiceObject serviceObject) throws HttpError {
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add(AUTHORIZATION, BEARER + token.getAccessToken());
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        
		HttpEntity<AbstractServiceObject> request = new HttpEntity<AbstractServiceObject>(serviceObject, headers);
		
		String url = apiCredentials.getSdk_host() + SERVICEOBJECT_ENDPOINT;
		ResponseEntity<ServiceObjectResponse> test = restTemplate.exchange(url,HttpMethod.POST, request, ServiceObjectResponse.class);
		
		try {
			ResponseEntity<ServiceObjectResponse> response = restTemplate.postForEntity(url, request, ServiceObjectResponse.class);
			
			if(!response.getStatusCode().is2xxSuccessful()){
				throw new HttpError("HTTP Error - Code: " + response.getStatusCode().value() + " Cause: " + response.getStatusCode().name());
			}
			
			return response.getBody();
			
		} catch (Exception e) {
			throw new HttpError("Http Error - Cause: " + e.getMessage(), e);
		}
		
	}

}
