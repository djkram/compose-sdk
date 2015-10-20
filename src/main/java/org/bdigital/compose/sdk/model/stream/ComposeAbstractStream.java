package org.bdigital.compose.sdk.model.stream;

import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractSOStream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeAbstractStream {
    
    @JsonCreator
    public ComposeAbstractStream(@JsonProperty("location") ComposeAbstractSOStream location) {
	super();
	this.location = location;
    }

    @JsonProperty("location")
    protected ComposeAbstractSOStream location;

    public ComposeAbstractSOStream getLocation() {
        return location;
    }

    public void setLocation(ComposeAbstractSOStream location) {
        this.location = location;
    }
    

}
