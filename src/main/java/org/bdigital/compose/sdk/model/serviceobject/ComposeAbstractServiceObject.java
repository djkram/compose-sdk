package org.bdigital.compose.sdk.model.serviceobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeAbstractServiceObject<C, A, P> {

    @JsonProperty("name")
    protected String name;
    @JsonProperty("description")
    protected String description;
    @JsonProperty("URL")
    protected String URL;
    @JsonProperty("public")
    protected String public_property;
    @JsonProperty("streams")
    protected ComposeAbstractSOStreams streams;
    @JsonProperty("customFields")
    protected C customFields;
    @JsonProperty("actions")
    protected A actions;
    @JsonProperty("properties")
    protected P properties;

    /**
     * Adding a Stream on Service object
     * 
     * Example:
     * "streams": { 
     * 		"location": {
     * 			"channels": { 
     * 				"temperature": { "type": "Celsius", "unit": "number" },
     * 				"humidity": { "type": "Number", "unit": "Integer" } , 
     * 				"light": { "type": "Number", "unit": "Lux" } },
     * 			"type": "sensor" } }
     * 
     * @param type
     * @param channels
     */
    public void addStream(String type, ComposeAbstractSOChannels channels) {
	if(this.streams==null){
	    this.streams = new ComposeAbstractSOStreams(new ComposeAbstractSOLocation(channels, type)); 
	}else{
	    this.streams.getLocation().setType(type);;
	    this.streams.getLocation().setChannels(channels);
	}
    }

    // Setters

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

    public ComposeAbstractSOStreams getStreams() {
	return streams;
    }

    public void setStreams(ComposeAbstractSOStreams streams) {
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
