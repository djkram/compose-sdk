package org.bdigital.compose.sdk.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.user.ComposeUserAccess;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;
import org.bdigital.compose.sdk.model.user.ComposeUserRegistered;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class IDMAPIClient implements IDMAPI {

    private static Log logger = LogFactory.getLog(IDMAPIClient.class);

    private ComposeAPICredentials apiCredentials;

    public IDMAPIClient(ComposeAPICredentials apiCredentials) {
	this.apiCredentials = apiCredentials;
    }

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String AUTHORITZATION_ENDPOINT = "/auth/user/";
    private static final String USER_ENDPOINT = "/idm/user/";

    public ComposeUserAccessToken userAuthoritzation(ComposeUserAccess user) throws HttpErrorException {

	RestTemplate restTemplate = new RestTemplate();
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	try {

	    ResponseEntity<ComposeUserAccessToken> token = restTemplate.postForEntity(apiCredentials.getIdm_host() + AUTHORITZATION_ENDPOINT, user, ComposeUserAccessToken.class);

	    if (!token.getStatusCode().is2xxSuccessful()) {
		throw new HttpErrorException("HTTP Error - Code: " + token.getStatusCode().value() + " Cause: " + token.getStatusCode().name());
	    }

	    return token.getBody();

	} catch (HttpStatusCodeException e) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}
    }

    public ComposeUserRegistered createUser(ComposeUserAccess user) throws HttpErrorException {

	CredentialsProvider provider = new BasicCredentialsProvider();
	UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("component", "ZXJpZHMiLCJ");
	provider.setCredentials(AuthScope.ANY, credentials);

	CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).useSystemProperties().build();

	HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);

	RestTemplate restTemplate = new RestTemplate(factory);
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	try {
	    ResponseEntity<ComposeUserRegistered> userResponse = restTemplate.postForEntity(apiCredentials.getIdm_host() + USER_ENDPOINT, user, ComposeUserRegistered.class);
	    logger.debug("Code: "+ userResponse.getStatusCode().value() +" Body: "+ userResponse.getBody());
	    
	    if (!userResponse.getStatusCode().is2xxSuccessful()) {
		throw new HttpErrorException("HTTP Error - Code: " + userResponse.getStatusCode().value() + " Cause: " + userResponse.getStatusCode().name());
	    }

	    return userResponse.getBody();

	} catch (HttpStatusCodeException e) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}
    }
    
    public void deleteUser(String id, String lastModifiedDateinMillis) throws HttpErrorException {

	CredentialsProvider provider = new BasicCredentialsProvider();
	UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("component", "ZXJpZHMiLCJ");
	provider.setCredentials(AuthScope.ANY, credentials);

	CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).useSystemProperties().build();

	HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);

	RestTemplate restTemplate = new RestTemplate(factory);
	
	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add("If-Unmodified-Since", lastModifiedDateinMillis);
	
	HttpEntity request = new HttpEntity(headers);

	String url = apiCredentials.getIdm_host() + USER_ENDPOINT + id;
	
	try {
	    ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
	    
	    logger.debug("Code: "+ userResponse.getStatusCode().value() +" Body: "+ userResponse.getBody());
	    
	    if (!userResponse.getStatusCode().is2xxSuccessful()) {
		throw new HttpErrorException("HTTP Error - Code: " + userResponse.getStatusCode().value() + " Cause: " + userResponse.getStatusCode().name());
	    }

	    return;

	} catch (HttpStatusCodeException e) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}
    }

    public ComposeUserRegistered getUserById(ComposeUserAccessToken token, String id) throws HttpErrorException {

	RestTemplate restTemplate = new RestTemplate();
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add(AUTHORIZATION, BEARER + token.getAccessToken());
	headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

	HttpEntity request = new HttpEntity(headers);

	String url = apiCredentials.getIdm_host() + USER_ENDPOINT + id;

	try {
	    ResponseEntity<ComposeUserRegistered> user = restTemplate.exchange(url, HttpMethod.GET, request, ComposeUserRegistered.class);

	    if (!user.getStatusCode().is2xxSuccessful()) {
		throw new HttpErrorException("HTTP Error - Code: " + user.getStatusCode().value() + " Cause: " + user.getStatusCode().name());
	    }

	    return user.getBody();

	} catch (HttpStatusCodeException e) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}

    }

    public ComposeUserRegistered getSelfUser(ComposeUserAccessToken token) throws HttpErrorException {

	RestTemplate restTemplate = new RestTemplate();
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add(AUTHORIZATION, BEARER + token.getAccessToken());
	headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

	HttpEntity request = new HttpEntity(headers);

	String url = apiCredentials.getIdm_host() + USER_ENDPOINT + "info/";
	try {
	    ResponseEntity<ComposeUserRegistered> user = restTemplate.exchange(url, HttpMethod.GET, request, ComposeUserRegistered.class);

	    if (!user.getStatusCode().is2xxSuccessful()) {
		throw new HttpErrorException("HTTP Error - Code: " + user.getStatusCode().value() + " Cause: " + user.getStatusCode().name());
	    }

	    return user.getBody();
	} catch (HttpStatusCodeException e) {
	    String message = "HTTP Error - Code: " + e.getStatusCode().value() + " Cause: [" + e.getStatusCode().name() + "] - " + e.getStatusText();
	    logger.warn(message);
	    throw new HttpErrorException(message, e);
	}
    }

}
