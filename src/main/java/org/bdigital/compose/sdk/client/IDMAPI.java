package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.request.ComposeUserAccess;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ComposeUserRegistered;

/**
 * API Client for Compose IDM {@link https://github.com/nopbyte/compose-idm}
 * 
 * Client for user management {@link https://github.com/nopbyte/compose-idm/blob/master/curl/tests-digest-authentication/users.txt}
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public interface IDMAPI {

    public AccessToken userAuthoritzation(ComposeUserAccess user) throws HttpErrorException;

    public ComposeUserRegistered createUser(ComposeUserAccess user) throws HttpErrorException;

    public ComposeUserRegistered getUserById(AccessToken token, String id) throws HttpErrorException;

    public ComposeUserRegistered getSelfUser(AccessToken token) throws HttpErrorException;

    public void deleteUser(String id, String lastModifiedDateinMillis) throws HttpErrorException;

}
