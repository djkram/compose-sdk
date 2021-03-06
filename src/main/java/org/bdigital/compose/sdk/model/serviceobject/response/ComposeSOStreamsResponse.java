package org.bdigital.compose.sdk.model.serviceobject.response;

import java.util.ArrayList;

import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOStream;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOStreams;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ComposeSOStreamsResponse {

    @JsonProperty("streams")
    protected ArrayList<ComposeAbstractSOStream> streams;
    
    public ArrayList<ComposeAbstractSOStream> getStreams() {
        return streams;
    }

    public void setStreams(ArrayList<ComposeAbstractSOStream> streams) {
        this.streams = streams;
    }

    @Override
    public String toString() {
	String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
	    json = mapper.writeValueAsString(this) ;
	} catch (JsonProcessingException e) {
	    json = super.toString();
	} 

        return json ;
    }
    
}
