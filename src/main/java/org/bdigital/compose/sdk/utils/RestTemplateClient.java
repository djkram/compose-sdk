package org.bdigital.compose.sdk.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tika.mime.MimeType;
import org.bdigital.compose.sdk.client.IDMAPIClient;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.user.ComposeUserRegistered;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class RestTemplateClient {
    
    private static Log logger = LogFactory.getLog(IDMAPIClient.class);

    public static <T> T callDigestAuthoritzation(String username, String password, String url, HttpMethod method, Object payload, Class<T> responseType) throws HttpErrorException{
	
	CredentialsProvider provider = new BasicCredentialsProvider();
	UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
	provider.setCredentials(AuthScope.ANY, credentials);

	CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).useSystemProperties().build();

	HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);

	RestTemplate restTemplate = new RestTemplate(factory);
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	
	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
	HttpEntity request = new HttpEntity(headers);

	try {
	    ResponseEntity<T> userResponse = restTemplate.exchange(url, method, request, responseType);
	    
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
    
}
