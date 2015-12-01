package org.bdigital.compose.sdk.client;

import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.entity.ComposeEntities;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.response.ComposeSORegisteredResponse;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;

/**
 * API CLient for Compose Life Cycle Manager {@link http://docs.composelifecycle.apiary.io/}
 * Fro Debu: http://compose-lcm-ptt4.147.83.30.133.xip.io/login
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public interface LCMAPI {

    public ComposeEntities getEntities(ComposeUserAccessToken token) throws HttpErrorException;
    
    //public 

}
