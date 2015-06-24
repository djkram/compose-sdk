package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.ComposeServiceObjectRegistered;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;

/**
 * API Client for Compose SDK {@link http://docs.composesdk.apiary.io/}
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public interface SDKAPI {

	public ComposeServiceObjectRegistered createServiceObject(ComposeUserAccessToken token, ComposeAbstractServiceObject serviceObject) throws HttpErrorException;

}
