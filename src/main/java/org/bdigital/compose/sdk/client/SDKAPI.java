package org.bdigital.compose.sdk.client;

import java.util.Set;

import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.response.ComposeSORegisteredResponse;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;

/**
 * API Client for Compose SDK {@link http://docs.composesdk.apiary.io/}
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public interface SDKAPI {

    public Set<String> listServiceObjectSDK(ComposeUserAccessToken token) throws HttpErrorException;
    
    public ComposeSORegisteredResponse getServiceObjectSDK(ComposeUserAccessToken token, String id) throws HttpErrorException;

    public ComposeSORegisteredResponse createServiceObjectSDK(ComposeUserAccessToken token, ComposeAbstractServiceObject serviceObject) throws HttpErrorException;
    
    public void deleteServiceObjectSDK(ComposeUserAccessToken token, String id) throws HttpErrorException;

}
