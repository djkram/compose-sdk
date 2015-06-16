package org.bdigital.compose.sdk.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AbstractServiceObject<S,C,A,P> {

	private String name;
	private String description;
	private String URL;
	@JsonProperty("public") 
	private String public_property;
	private S streams;
	private C customFields;
	private A actions;
	private P properties;
	
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
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getPublic_property() {
		return public_property;
	}
	public void setPublic_property(String public_property) {
		this.public_property = public_property;
	}
	public S getStreams() {
		return streams;
	}
	public void setStreams(S streams) {
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
	
}
