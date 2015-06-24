package org.bdigital.compose.sdk.model.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeUserRegistered extends ComposeAbstractUser {

    @JsonProperty("id")
    private String id;
    @JsonProperty("lastModified")
    private String lastModified;
    @JsonProperty("approvedMemberships")
    private List<String> approvedMemberships;

    @JsonCreator
    public ComposeUserRegistered(@JsonProperty("id") String id, @JsonProperty("username") String username, @JsonProperty("lastModified") String lastModified,
	    @JsonProperty("approvedMemberships") List<String> approvedMemberships) {
	super(username);
	this.id = id;
	this.lastModified = lastModified;
	this.approvedMemberships = approvedMemberships;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getLastModified() {
	return lastModified;
    }

    public void setLastModified(String lastModified) {
	this.lastModified = lastModified;
    }

    public List<String> getApprovedMemberships() {
	return approvedMemberships;
    }

    public void setApprovedMemberships(List<String> approvedMemberships) {
	this.approvedMemberships = approvedMemberships;
    }

    @Override
    public String toString() {
	return new Gson().toJson(this);
    }

}
