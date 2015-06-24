package org.bdigital.compose.sdk.model.serviceobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeAbstractSOStreams {
    
    @JsonCreator
    public ComposeAbstractSOStreams(@JsonProperty("location") ComposeAbstractSOLocation location) {
	super();
	this.location = location;
    }

    @JsonProperty("location")
    protected ComposeAbstractSOLocation location;

    public ComposeAbstractSOLocation getLocation() {
        return location;
    }

    public void setLocation(ComposeAbstractSOLocation location) {
        this.location = location;
    }
    

}
