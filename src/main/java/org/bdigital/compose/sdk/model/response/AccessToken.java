package org.bdigital.compose.sdk.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken {

    @JsonProperty("accessToken")
    private String accessToken;
    @JsonProperty("token_type")
    private String token_type;

    public String getAccessToken() {
	return accessToken;
    }

    public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
    }

    public String getToken_type() {
	return token_type;
    }

    public void setToken_type(String token_type) {
	this.token_type = token_type;
    }

    @Override
    public String toString() {
	return new Gson().toJson(this); 
    }

}
