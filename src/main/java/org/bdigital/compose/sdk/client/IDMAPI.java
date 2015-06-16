package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.exception.HttpError;
import org.bdigital.compose.sdk.model.request.ComposeUserAccess;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ComposeExistingUser;

public interface IDMAPI {

	public AccessToken userAuthoritzation(ComposeUserAccess user) throws HttpError;
	
	public ComposeExistingUser createUser(ComposeUserAccess user) throws HttpError;
	
	public ComposeExistingUser getUserById(AccessToken token, String id) throws HttpError;
	
	public ComposeExistingUser getSelfUser(AccessToken token) throws HttpError;
	
}
