package org.bdigital.compose.sdk.client;

import java.util.ArrayList;

import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractSOChannels;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.ComposeServiceObjectRegistered;
import org.bdigital.compose.sdk.model.stream.ComposeResponseStreams;
import org.bdigital.compose.sdk.model.stream.ComposeUploadStreamData;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;

/**
 * API Client for Compose SDK {@link http://docs.composesdk.apiary.io/}
 * 
 * @author mplanaguma <mplanaguma@bdigital.org>
 *
 */
public interface SDKAPI {

    public String listServiceObjectSDK(ComposeUserAccessToken token);
    
    public String getServiceObjectSDK(ComposeUserAccessToken token, String id);

    public ComposeServiceObjectRegistered createServiceObjectSDK(ComposeUserAccessToken token, ComposeAbstractServiceObject serviceObject) throws HttpErrorException;

    
    public ComposeServiceObjectRegistered createServiceObject(ComposeUserAccessToken token, ComposeAbstractServiceObject serviceObject) throws HttpErrorException;

    public ArrayList<String> listServiceObject(ComposeUserAccessToken token);
    
    public String getServiceObject(ComposeUserAccessToken token, String id);
    
    public String removeServiceObject(ComposeUserAccessToken token, String id);
    
    public ComposeResponseStreams getServiceObjectStreams(ComposeUserAccessToken token, String id);
    
    public String uploadDataOnServiceObjectStreams (String api_token, String soId,  String streamId, ComposeUploadStreamData data) throws HttpErrorException;
    
    public String getDataFromServiceObjectStreams (ComposeUserAccessToken token, String soId,  String streamId, String timeModifier);
    
}
