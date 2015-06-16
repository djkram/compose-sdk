package org.bdigital.compose.sdk.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpError;
import org.bdigital.compose.sdk.model.request.AbstractServiceObject;
import org.bdigital.compose.sdk.model.request.ComposeUserAccess;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ComposeExistingUser;
import org.bdigital.compose.sdk.model.response.ServiceObjectResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class IDMAPIClient implements IDMAPI {

	ComposeAPICredentials apiCredentials;
	
	public IDMAPIClient(ComposeAPICredentials apiCredentials){
		this.apiCredentials = apiCredentials;
	}
	
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";
	private final String auth_endpoint = "/auth/user/";
	private final String get_user_endpoint = "/idm/user/";
	
	
	public AccessToken userAuthoritzation(ComposeUserAccess user) throws HttpError {
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        
		ResponseEntity<AccessToken> token = restTemplate.postForEntity(apiCredentials.getIdm_host() + auth_endpoint, user, AccessToken.class);
		
		if(!token.getStatusCode().is2xxSuccessful()){
			throw new HttpError("HTTP Error - Code: " + token.getStatusCode().value() + " Cause: " + token.getStatusCode().name());
		}
		
		return token.getBody();
	}
	
	
	public ComposeExistingUser createUser(ComposeUserAccess user) throws HttpError{
		
		CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = 
          new UsernamePasswordCredentials(user.getUsername(), user.getPassword());
        provider.setCredentials(AuthScope.ANY, credentials);
		
		CloseableHttpClient client = HttpClientBuilder.create().
		          setDefaultCredentialsProvider(provider).useSystemProperties().build();
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client); 
		
		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		ResponseEntity<ComposeExistingUser> userResponse = restTemplate.postForEntity(apiCredentials.getIdm_host() + get_user_endpoint, user, ComposeExistingUser.class);

		if(!userResponse.getStatusCode().is2xxSuccessful()){
			throw new HttpError("HTTP Error - Code: " + userResponse.getStatusCode().value() + " Cause: " + userResponse.getStatusCode().name());
		}
		
		return userResponse.getBody();
		
	}
	
	
	public ComposeExistingUser getUserById(AccessToken token, String id) throws HttpError{
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add(AUTHORIZATION, BEARER + token.getAccessToken());
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        
		HttpEntity request = new HttpEntity(headers);
        
		String url = apiCredentials.getSdk_host() + get_user_endpoint + id;
		ResponseEntity<ComposeExistingUser> user = restTemplate.exchange(url,HttpMethod.POST, request, ComposeExistingUser.class);
		
		if(!user.getStatusCode().is2xxSuccessful()){
			throw new HttpError("HTTP Error - Code: " + user.getStatusCode().value() + " Cause: " + user.getStatusCode().name());
		}
		
		return user.getBody();
		
	}
	
	
	public ComposeExistingUser getSelfUser(AccessToken token) throws HttpError{
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add(AUTHORIZATION, BEARER + token.getAccessToken());
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        
		HttpEntity request = new HttpEntity(headers);
		
		String url = apiCredentials.getSdk_host() + get_user_endpoint + "info/";
		ResponseEntity<ComposeExistingUser> user = restTemplate.exchange(url,HttpMethod.POST, request, ComposeExistingUser.class);
		
		if(!user.getStatusCode().is2xxSuccessful()){
			throw new HttpError("HTTP Error - Code: " + user.getStatusCode().value() + " Cause: " + user.getStatusCode().name());
		}
		
		return user.getBody();
		
	}

}
