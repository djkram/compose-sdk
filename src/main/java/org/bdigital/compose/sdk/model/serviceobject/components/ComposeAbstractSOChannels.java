package org.bdigital.compose.sdk.model.serviceobject.components;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * "streams:{}" component for ServiceObject JSON Model
 * 
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
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public class ComposeAbstractSOChannels extends HashMap<String, ComposeAbstractSOChannel> {

    private static final long serialVersionUID = 7827639722646425885L;
    
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
