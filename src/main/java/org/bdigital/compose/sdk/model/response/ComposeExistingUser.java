package org.bdigital.compose.sdk.model.response;

import java.util.ArrayList;

import org.bdigital.compose.sdk.model.request.ComposeBaseUser;

public class ComposeExistingUser extends ComposeBaseUser {

	private String id;
	private String lastModified;
	private ArrayList<String> approvedMemberships;
	
	public ComposeExistingUser(){
		super();
	}
	
	public ComposeExistingUser(String id, String username, String lastModified, ArrayList<String> approvedMemberships){
		super(username);
		this.id = id;
		this.lastModified = lastModified;
		this.approvedMemberships = approvedMemberships;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public ArrayList<String> getApprovedMemberships() {
		return approvedMemberships;
	}

	public void setApprovedMemberships(ArrayList<String> approvedMemberships) {
		this.approvedMemberships = approvedMemberships;
	}
	
	
	
}
