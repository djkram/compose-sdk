package org.bdigital.compose.sdk.model.serviceobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ComposeAbstractSOLocation {

    @JsonCreator
    public ComposeAbstractSOLocation(@JsonProperty("channels") ComposeAbstractSOChannels channels, @JsonProperty("type") String type) {
	super();
	this.channels = channels;
	this.type = type;
    }

    @JsonProperty("channels")
    protected ComposeAbstractSOChannels channels;

    @JsonProperty("type")
    protected String type;

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

}
