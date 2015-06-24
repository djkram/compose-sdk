package org.bdigital.compose.sdk.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeEntities {

    @JsonCreator
    public ComposeEntities(@JsonProperty("metadata") ComposeEntitiesMetadata metadata, @JsonProperty("data") List<ComposeEntitiesData> data) {
	this.metadata = metadata;
	this.data = data;
    }

    @JsonProperty("metadata") private ComposeEntitiesMetadata metadata;
    @JsonProperty("data") private List<ComposeEntitiesData> data;

    public ComposeEntitiesMetadata getMetadata() {
	return metadata;
    }

    public void setMetadata(ComposeEntitiesMetadata metadata) {
	this.metadata = metadata;
    }

    public List<ComposeEntitiesData> getData() {
	return data;
    }

    public void setData(List<ComposeEntitiesData> data) {
	this.data = data;
    }

    @Override
    public String toString() {
	return new Gson().toJson(this); 
    }

}
