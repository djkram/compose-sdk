package org.bdigital.compose.sdk.model.request;

public class ComposeUserAccess extends ComposeBaseUser {

	private String password;
	
	public ComposeUserAccess(){
		super();
	}
	
	public ComposeUserAccess(String username, String password){
		super(username);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
