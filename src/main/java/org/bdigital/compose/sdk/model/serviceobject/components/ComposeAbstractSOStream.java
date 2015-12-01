package org.bdigital.compose.sdk.model.serviceobject.components;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeAbstractSOStream  {

    @JsonCreator
    public ComposeAbstractSOStream(@JsonProperty("name") String name, @JsonProperty("channels") ComposeAbstractSOChannels channels, @JsonProperty("description") String description, @JsonProperty("type") String type, @JsonProperty("subscriptions") ArrayList<String> subscriptions) {
	super();
	this.name = name;
	this.channels = channels;
	this.description = description;
	this.type = type;
	this.subscriptions = subscriptions;
    }

    @JsonProperty("name")
    protected String name;    
    
    @JsonProperty("channels")
    protected ComposeAbstractSOChannels channels;

    @JsonProperty("description")
    protected String description;
    
    @JsonProperty("type")
    protected String type;

    @JsonProperty("subscriptions")
    protected ArrayList<String> subscriptions;    
    
    
    public ComposeAbstractSOChannels getChannels() {
	return channels;
    }

    public void setChannels(ComposeAbstractSOChannels channels) {
	this.channels = channels;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

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

    public List<String> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<String> subscriptions) {
        this.subscriptions = subscriptions;
    }

}
