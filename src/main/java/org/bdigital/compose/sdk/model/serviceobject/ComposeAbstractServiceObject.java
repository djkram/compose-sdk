package org.bdigital.compose.sdk.model.serviceobject;

import java.util.ArrayList;
import java.util.HashMap;

import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOChannels;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOStream;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOStreams;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Abstract JSON model for Compose Service Object. 
 * Extend it for modeling adHoc service object.
 * 
 * Example:
 * 
 * {  
 *  "name": "Phone", 
 *  "description": "COMPOSE phone", 
 *  "URL": "Web Object URL ",
 *  "public":"true",
 *  "streams": {
 *        "location": {
 *           "channels": {
 *               "latitude": {
 *                   "type": "number",
 *                   "unit": "degrees"
 *               },
 *               "longitude": {
 *                   "type": "number",
 *                   "unit": "degrees"
 *               }
 *           },
 *           "description": "GPS outdoor location",
 *           "type": "sensor"
 *       }
 *   },
 *   "customFields": {},
 *   "actions": [],
 *   "properties": [],
 *   "id": "14454346490058a70be39105a4763b0bdc1939f5d00a1",
 *   "createdAt": 1445434649006,
 *   "updatedAt": 1445434649006
 * }
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 * @param <C>
 *            Type for customFields
 * @param <A>
 *            Type for actions
 * @param <P>
 *            Type for properties
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeAbstractServiceObject<C extends HashMap, A extends ArrayList, P extends ArrayList> {

    @JsonProperty("name")
    protected String name;
    @JsonProperty("description")
    protected String description;
    @JsonProperty("URL")
    protected String url;
    @JsonProperty("public")
    protected String public_;
    @JsonProperty("streams")
    protected ComposeAbstractSOStreams streams;
    @JsonProperty("customFields")
    protected C customFields;
    @JsonProperty("actions")
    protected A actions;
    @JsonProperty("properties")
    protected P properties;

    
    public ComposeAbstractServiceObject() {
	super();
    }

    @JsonCreator
    public ComposeAbstractServiceObject(@JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("URL") String url,
	    @JsonProperty("public") String public_property, @JsonProperty("streams") ComposeAbstractSOStreams streams, @JsonProperty("customFields") C customFields,
	    @JsonProperty("actions") A actions, @JsonProperty("properties") P properties) {
	super();
	this.name = name;
	this.description = description;
	this.url = url;
	this.public_ = public_property;
	this.streams = streams;
	this.customFields = customFields;
	this.actions = actions;
	this.properties = properties;
    }

    /**
     * Adding a Stream on Service object
     * 
     * Example: "streams": { "location": { "channels": { "temperature": {
     * "type": "Celsius", "unit": "number" }, "humidity": { "type": "Number",
     * "unit": "Integer" } , "light": { "type": "Number", "unit": "Lux" } },
     * "type": "sensor" } }
     * 
     * @param type
     * @param channels
     */
    public void addStream(String name, String type, String description, ComposeAbstractSOChannels channels) {
	if (this.streams == null) {
	    this.streams = new ComposeAbstractSOStreams();
	}

	ComposeAbstractSOStream stream = new ComposeAbstractSOStream(name, channels, description, type, null);
	this.streams.put(name, stream);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublic_() {
        return public_;
    }

    public void setPublic_(String public_) {
        this.public_ = public_;
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
