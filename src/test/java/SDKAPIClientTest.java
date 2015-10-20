import java.net.ConnectException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.bdigital.compose.sdk.client.IDMAPI;
import org.bdigital.compose.sdk.client.IDMAPIClient;
import org.bdigital.compose.sdk.client.SDKAPI;
import org.bdigital.compose.sdk.client.SDKAPIClient;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractSOChannel;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractSOChannels;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.ComposeServiceObjectRegistered;
import org.bdigital.compose.sdk.model.stream.ComposeResponseStreams;
import org.bdigital.compose.sdk.model.stream.ComposeUploadStreamData;
import org.bdigital.compose.sdk.model.user.ComposeUserAccess;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class SDKAPIClientTest {

    @Ignore
    @Test
    public final void testCreateServiceObjectSDK() throws HttpErrorException, URISyntaxException, ConnectException, ClientProtocolException {

	ComposeAPICredentials apiCredentials = new ComposeAPICredentials();
	IDMAPI idmapi = new IDMAPIClient(apiCredentials);
	ComposeUserAccess user = new ComposeUserAccess("test_compose_bdigital", "c6jvUBDV");
	ComposeUserAccessToken token = idmapi.userAuthoritzation(user);

	SDKAPI sdkapi = new SDKAPIClient(apiCredentials);

	ComposeAbstractServiceObject<HashMap, ArrayList, ArrayList> serviceObject = new ComposeAbstractServiceObject<HashMap, ArrayList, ArrayList>();
	serviceObject.setName("Test Object");
	serviceObject.setDescription("Test Description");
	serviceObject.setURL("Web Object URL");
	serviceObject.setPublic_property("true");
	ComposeAbstractSOChannels channels = new ComposeAbstractSOChannels();
	channels.put("temperature", new ComposeAbstractSOChannel("Number", null, "Celcius"));
	serviceObject.addStream("temperature", "sensor", "description", channels);
	serviceObject.setCustomFields(new HashMap(0));
	serviceObject.setActions(new ArrayList(0));
	serviceObject.setProperties(new ArrayList(0));
	
	System.out.println(serviceObject);

	ComposeServiceObjectRegistered response = sdkapi.createServiceObjectSDK(token, serviceObject);
	System.out.println(response);
	Assert.assertNotNull(response);
	Assert.assertNotNull(response.getId());
	Assert.assertNotNull(response.getApi_token());
	
	String list = sdkapi.listServiceObjectSDK(token);
	System.out.println(list);
	
	String so = sdkapi.getServiceObjectSDK(token, "1434538273565017ccb06185c4aaf8b6d4f9f5a2ad03b");
	System.out.println(so);
    }
    
    
    @Test
    public final void testCreateServiceObjectServiocity() throws HttpErrorException, URISyntaxException, ConnectException, ClientProtocolException {

	ComposeAPICredentials apiCredentials = new ComposeAPICredentials();
	IDMAPI idmapi = new IDMAPIClient(apiCredentials);
	ComposeUserAccess user = new ComposeUserAccess("test_compose_bdigital", "c6jvUBDV");
	ComposeUserAccessToken token = idmapi.userAuthoritzation(user);

	SDKAPI serviocity = new SDKAPIClient(apiCredentials);

	ComposeAbstractServiceObject<HashMap, ArrayList, ArrayList> serviceObject = new ComposeAbstractServiceObject<HashMap, ArrayList, ArrayList>();
	serviceObject.setName("Test Object");
	serviceObject.setDescription("Test Description");
	serviceObject.setURL("Web Object URL");
	serviceObject.setPublic_property("true");
	ComposeAbstractSOChannels channels = new ComposeAbstractSOChannels();
	channels.put("temperature", new ComposeAbstractSOChannel("number", null, "celcius"));
	serviceObject.addStream("temperature", "sensor", "description", channels);
	serviceObject.setCustomFields(new HashMap(0));
	serviceObject.setActions(new ArrayList(0));
	serviceObject.setProperties(new ArrayList(0));
	
	System.out.println(serviceObject);

	ComposeServiceObjectRegistered response = serviocity.createServiceObject(token, serviceObject);
	System.out.println(response);
	Assert.assertNotNull(response);
	Assert.assertNotNull(response.getId());
	Assert.assertNotNull(response.getApi_token());

	ArrayList<String> list = serviocity.listServiceObject(token);
	System.out.println(list);
	
	for (String string : list) {
	    serviocity.removeServiceObject(token, string);
	}
	
	response = serviocity.createServiceObject(token, serviceObject);
	System.out.println(response);
	
	String so = serviocity.getServiceObject(token, response.getId());
	System.out.println(so);
	
	ComposeResponseStreams streams = serviocity.getServiceObjectStreams(token, response.getId());
	System.out.println(streams);
	
	String dataStreams = serviocity.getDataFromServiceObjectStreams(token, response.getId(), "temperature", null);
	System.out.println(dataStreams);
	
	ComposeAbstractSOChannels channelsData = new ComposeAbstractSOChannels();
	channelsData.put("temperature", new ComposeAbstractSOChannel(null, 25.8, "celcius"));
	ComposeUploadStreamData data = new ComposeUploadStreamData();
	data.setChannels(channelsData);
	data.setLastUpdate(Calendar.getInstance().getTimeInMillis());
	
	String result = serviocity.uploadDataOnServiceObjectStreams(response.getApi_token(), response.getId(), "temperature", data);
	System.out.println(result);
	
	dataStreams = serviocity.getDataFromServiceObjectStreams(token, response.getId(), "temperature", null);
	System.out.println(dataStreams);
	
	String removeResult = serviocity.removeServiceObject(token, response.getId());
	System.out.println(removeResult);
	
    }
    

}
