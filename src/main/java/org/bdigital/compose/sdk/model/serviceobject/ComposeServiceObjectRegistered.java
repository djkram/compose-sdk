package org.bdigital.compose.sdk.model.serviceobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeServiceObjectRegistered {

    @JsonProperty("id")
    private String id;
    @JsonProperty("api_token")
    private String api_token;
    @JsonProperty("createdAt")
    private Long createdAt;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getApi_token() {
	return api_token;
    }

    public void setApi_token(String api_token) {
	this.api_token = api_token;
    }

    public Long getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
	this.createdAt = createdAt;
    }

    @Override
    public String toString() {
	String json = "";
	ObjectMapper mapper = new ObjectMapper();
	try {
	    json = mapper.writeValueAsString(this);
	} catch (JsonProcessingException e) {
	    json = super.toString();
	}

	return json;
    }

}
