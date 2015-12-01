import java.net.ConnectException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.bdigital.compose.sdk.client.IDMAPI;
import org.bdigital.compose.sdk.client.IDMAPIClient;
import org.bdigital.compose.sdk.client.SDKAPI;
import org.bdigital.compose.sdk.client.SDKAPIClient;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOChannel;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOChannels;
import org.bdigital.compose.sdk.model.serviceobject.response.ComposeSORegisteredResponse;
import org.bdigital.compose.sdk.model.user.ComposeUserAccess;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;
import org.junit.Assert;
import org.junit.Test;

public class SDKAPIClientTest {

    @Test
    public final void testCompletFlowServiceObjectSDK() throws HttpErrorException, URISyntaxException, ConnectException, ClientProtocolException {

	ComposeAPICredentials apiCredentials = new ComposeAPICredentials();
	IDMAPI idmapi = new IDMAPIClient(apiCredentials);
	ComposeUserAccess user = new ComposeUserAccess("test_compose_bdigital", "c6jvUBDV");
	ComposeUserAccessToken token = idmapi.userAuthoritzation(user);

	SDKAPI sdkapi = new SDKAPIClient(apiCredentials);

	ComposeAbstractServiceObject<HashMap, ArrayList, ArrayList> serviceObject = new ComposeAbstractServiceObject<HashMap, ArrayList, ArrayList>();
	serviceObject.setName("Test Object");
	serviceObject.setDescription("Test Description");
	serviceObject.setUrl("Web Object URL");
	serviceObject.setPublic_("true");
	ComposeAbstractSOChannels channels = new ComposeAbstractSOChannels();
	channels.put("temperature", new ComposeAbstractSOChannel("Number", null, "Celcius"));
	serviceObject.addStream("temperature", "sensor", "description", channels);
	serviceObject.setCustomFields(new HashMap(0));
	serviceObject.setActions(new ArrayList(0));
	serviceObject.setProperties(new ArrayList(0));
	
	System.out.println("Creating Sevice Object " + serviceObject);

	ComposeSORegisteredResponse response = sdkapi.createServiceObjectSDK(token, serviceObject);
	System.out.println("Created Sevice Object " + response);
	Assert.assertNotNull(response);
	Assert.assertNotNull(response.getId());
	Assert.assertNotNull(response.getApi_token());
	
	Set<String> list = sdkapi.listServiceObjectSDK(token);
	Assert.assertNotNull(list);
	Assert.assertFalse(list.isEmpty());
	System.out.println("List of Sevice Object " + list);
	
	ComposeSORegisteredResponse so = sdkapi.getServiceObjectSDK(token, response.getId());
	Assert.assertNotNull(so);
	System.out.println("Detail of Sevice Object " + so);
	
	for (String id : list) {
	    sdkapi.deleteServiceObjectSDK(token, id);
	    System.out.println("Deleted Sevice Object " + id);
	}
	
    }
    
    @Test(expected=HttpErrorException.class)
    public final void testErrorCredentialsServiceObjectSDK() throws HttpErrorException{
	
	ComposeAPICredentials apiCredentials = new ComposeAPICredentials();
	IDMAPI idmapi = new IDMAPIClient(apiCredentials);
	ComposeUserAccess user = new ComposeUserAccess("wrong", "wrong!");
	ComposeUserAccessToken token = idmapi.userAuthoritzation(user);

	SDKAPI sdkapi = new SDKAPIClient(apiCredentials);
	
    }
    
    @Test(expected=HttpErrorException.class)
    public final void testErrorListServiceObjectSDK() throws HttpErrorException{
	
	ComposeAPICredentials apiCredentials = new ComposeAPICredentials();
	ComposeUserAccessToken token = new ComposeUserAccessToken();

	SDKAPI sdkapi = new SDKAPIClient(apiCredentials);
	
	sdkapi.listServiceObjectSDK(token);
    }
    
    @Test(expected=HttpErrorException.class)
    public final void testErrorDeleteServiceObjectSDK() throws HttpErrorException{
	
	ComposeAPICredentials apiCredentials = new ComposeAPICredentials();
	IDMAPI idmapi = new IDMAPIClient(apiCredentials);
	ComposeUserAccess user = new ComposeUserAccess("test_compose_bdigital", "c6jvUBDV");
	ComposeUserAccessToken token = idmapi.userAuthoritzation(user);

	SDKAPI sdkapi = new SDKAPIClient(apiCredentials);
	
	sdkapi.deleteServiceObjectSDK(token, "000");
    }
    
    
    

}
