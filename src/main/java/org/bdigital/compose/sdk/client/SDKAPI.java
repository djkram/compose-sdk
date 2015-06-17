package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.request.AbstractServiceObject;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ComposeServiceObject;

/**
 * API Client for Compose SDK {@link http://docs.composesdk.apiary.io/}
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public interface SDKAPI {

	public ComposeServiceObject createServiceObject(AccessToken token, AbstractServiceObject serviceObject) throws HttpErrorException;

}
