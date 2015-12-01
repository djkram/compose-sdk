package org.bdigital.compose.sdk.model.serviceobject.response;

import java.util.ArrayList;
import java.util.HashMap;

import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOChannels;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOStream;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOStreams;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Response on API Call
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
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeSODetailResponse extends ComposeAbstractServiceObject<HashMap, ArrayList, ArrayList> {

    @JsonProperty("id")
    protected String id;
    @JsonProperty("createdAt")
    protected Number createdAt;
    @JsonProperty("updatedAt")
    protected Number updatedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Number getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Number createdAt) {
        this.createdAt = createdAt;
    }

    public Number getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Number updatedAt) {
        this.updatedAt = updatedAt;
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
