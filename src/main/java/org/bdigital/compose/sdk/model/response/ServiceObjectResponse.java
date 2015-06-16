package org.bdigital.compose.sdk.model.response;

public class ServiceObjectResponse {

	private String id;
	private String api_token;
	private Long createdAt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApi_token() {
		return api_token;
	}
	public void setApi_token(String api_token) {
		this.api_token = api_token;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "ServiceObjectResponse [id=" + id + ", api_token=" + api_token
				+ ", createdAt=" + createdAt + "]";
	}
	
}
