package org.bdigital.compose.sdk.model.response;


public class AccessToken {

	private String accessToken;
	private String token_type;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	
	@Override
	public String toString() {
		return "AccessToken [accessToken=" + accessToken + ", token_type="
				+ token_type + "]";
	}
	
}
