package org.bdigital.compose.sdk.model.stream;

import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractSOChannels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ComposeUploadStreamData {

    @JsonProperty("channels")
    protected ComposeAbstractSOChannels channels;
    
    @JsonProperty("lastUpdate")
    protected Number lastUpdate;

    
    public ComposeAbstractSOChannels getChannels() {
        return channels;
    }

    public void setChannels(ComposeAbstractSOChannels channels) {
        this.channels = channels;
    }

    public Number getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Number lastUpdate) {
        this.lastUpdate = lastUpdate;
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
