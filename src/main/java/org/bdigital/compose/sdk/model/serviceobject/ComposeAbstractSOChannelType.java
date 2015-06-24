package org.bdigital.compose.sdk.model.serviceobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ComposeAbstractSOChannelType {
    
    @JsonCreator
    public ComposeAbstractSOChannelType(@JsonProperty("type") String type, @JsonProperty("unit") String unit) {
	super();
	this.type = type;
	this.unit = unit;
    }

    @JsonProperty("type")
    protected String type;
    
    @JsonProperty("unit")
    protected String unit;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    
}
