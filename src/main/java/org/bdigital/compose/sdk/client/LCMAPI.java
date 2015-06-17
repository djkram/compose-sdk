package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.request.AbstractServiceObject;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ComposeEntities;
import org.bdigital.compose.sdk.model.response.ComposeServiceObject;

/**
 * API CLient for Compose Life Cycle Manager {@link http://docs.composelifecycle.apiary.io/}
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public interface LCMAPI {

    public ComposeEntities getEntities(AccessToken token) throws HttpErrorException;

}
