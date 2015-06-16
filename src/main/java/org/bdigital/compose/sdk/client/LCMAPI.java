package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.exception.HttpError;
import org.bdigital.compose.sdk.model.request.AbstractServiceObject;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ServiceObjectResponse;

/**
 * @author mplanaguma
 *
 */
public interface LCMAPI {

	public String getEntities(AccessToken token) throws HttpError;
	
}
