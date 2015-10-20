package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.entity.ComposeEntities;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.ComposeServiceObjectRegistered;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;

/**
 * API CLient for Compose Life Cycle Manager {@link http://docs.composelifecycle.apiary.io/}
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public interface LCMAPI {

    public ComposeEntities getEntities(ComposeUserAccessToken token) throws HttpErrorException;
    
    //public 

}
