package org.bdigital.compose.sdk.client;

import java.util.Set;

import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeStreamDataUpdate;
import org.bdigital.compose.sdk.model.serviceobject.response.ComposeSODetailResponse;
import org.bdigital.compose.sdk.model.serviceobject.response.ComposeSORegisteredResponse;
import org.bdigital.compose.sdk.model.serviceobject.response.ComposeSOStreamsResponse;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;

/**
 * API Client for Compose SDK {@link http://docs.composesdk.apiary.io/}
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public interface ServioticyAPI {

    public ComposeSORegisteredResponse createServiceObject(ComposeUserAccessToken token, ComposeAbstractServiceObject serviceObject) throws HttpErrorException;

    public Set<String> listServiceObject(ComposeUserAccessToken token) throws HttpErrorException;
    
    public ComposeSODetailResponse getServiceObject(ComposeUserAccessToken token, String id) throws HttpErrorException;
    
    public void removeServiceObject(ComposeUserAccessToken token, String id) throws HttpErrorException;
    
    public ComposeSOStreamsResponse getServiceObjectStreams(ComposeUserAccessToken token, String id) throws HttpErrorException;
    
    public String uploadDataOnServiceObjectStreams (String api_token, String soId,  String streamId, ComposeStreamDataUpdate data) throws HttpErrorException;
    
    public String getDataFromServiceObjectStreams (ComposeUserAccessToken token, String soId,  String streamId, String timeModifier) throws HttpErrorException;
    
}
