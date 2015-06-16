package org.bdigital.compose.sdk.model.request;

public class ComposeBaseUser {

	private String username;
	
	public ComposeBaseUser(){
		super();
	}
	
	public ComposeBaseUser(String username){
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
