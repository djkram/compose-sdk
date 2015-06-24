package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.user.ComposeUserAccess;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;
import org.bdigital.compose.sdk.model.user.ComposeUserRegistered;

/**
 * API Client for Compose IDM {@link https://github.com/nopbyte/compose-idm}
 * 
 * Client for user management {@link https://github.com/nopbyte/compose-idm/blob/master/curl/tests-digest-authentication/users.txt}
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public interface IDMAPI {

    public ComposeUserAccessToken userAuthoritzation(ComposeUserAccess user) throws HttpErrorException;

    public ComposeUserRegistered createUser(ComposeUserAccess user) throws HttpErrorException;

    public ComposeUserRegistered getUserById(ComposeUserAccessToken token, String id) throws HttpErrorException;

    public ComposeUserRegistered getSelfUser(ComposeUserAccessToken token) throws HttpErrorException;

    public void deleteUser(String id, String lastModifiedDateinMillis) throws HttpErrorException;

}
