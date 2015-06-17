package org.bdigital.compose.sdk.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractServiceObject<S, C, A, P> {

    @JsonProperty("name") private String name;
    @JsonProperty("description") private String description;
    @JsonProperty("URL") private String URL;
    @JsonProperty("public") private String public_property;
    @JsonProperty("streams") private S streams;
    @JsonProperty("customFields") private C customFields;
    @JsonProperty("actions") private A actions;
    @JsonProperty("properties")  private P properties;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getURL() {
	return URL;
    }

    public void setURL(String uRL) {
	URL = uRL;
    }

    public String getPublic_property() {
	return public_property;
    }

    public void setPublic_property(String public_property) {
	this.public_property = public_property;
    }

    public S getStreams() {
	return streams;
    }

    public void setStreams(S streams) {
	this.streams = streams;
    }

    public C getCustomFields() {
	return customFields;
    }

    public void setCustomFields(C customFields) {
	this.customFields = customFields;
    }

    public A getActions() {
	return actions;
    }

    public void setActions(A actions) {
	this.actions = actions;
    }

    public P getProperties() {
	return properties;
    }

    public void setProperties(P properties) {
	this.properties = properties;
    }

    @Override
    public String toString() {
	return new Gson().toJson(this); 
    }

}
