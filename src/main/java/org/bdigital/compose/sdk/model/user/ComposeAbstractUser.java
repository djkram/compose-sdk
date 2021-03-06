package org.bdigital.compose.sdk.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeAbstractUser {

    @JsonProperty("username")
    protected String username;

    @JsonCreator
    public ComposeAbstractUser(@JsonProperty("username") String username) {
	this.username = username;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    @Override
    public String toString() {
	return new Gson().toJson(this); 
    }

}
