package org.bdigital.compose.sdk.model.serviceobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeAbstractSOChannel {
    
    @JsonCreator
    public ComposeAbstractSOChannel(@JsonProperty("type") String type, @JsonProperty("current-value") Number current_value, @JsonProperty("unit") String unit) {
	super();
	this.type = type;
	this.current_value = current_value;
	this.unit = unit;
    }

    @JsonProperty("type")
    protected String type;
    
    @JsonProperty("current-value")
    protected Number current_value;
    
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

    public Number getCurrent_value() {
        return current_value;
    }

    public void setCurrent_value(Number current_value) {
        this.current_value = current_value;
    }
    
    
}
