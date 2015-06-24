package org.bdigital.compose.sdk.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeUserAccess extends ComposeAbstractUser {

    @JsonProperty("password")
    private String password;

    @JsonCreator
    public ComposeUserAccess(@JsonProperty("username") String username, @JsonProperty("password") String password) {
	super(username);
	this.password = password;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
    
    @Override
    public String toString() {
	return new Gson().toJson(this); 
    }

}
