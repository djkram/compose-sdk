import java.net.ConnectException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.bdigital.compose.sdk.client.IDMAPI;
import org.bdigital.compose.sdk.client.IDMAPIClient;
import org.bdigital.compose.sdk.client.ServioticyAPI;
import org.bdigital.compose.sdk.client.ServioticyAPIClient;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOChannel;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeAbstractSOChannels;
import org.bdigital.compose.sdk.model.serviceobject.components.ComposeStreamDataUpdate;
import org.bdigital.compose.sdk.model.serviceobject.response.ComposeSORegisteredResponse;
import org.bdigital.compose.sdk.model.serviceobject.response.ComposeSOStreamsResponse;
import org.bdigital.compose.sdk.model.user.ComposeUserAccess;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;
import org.junit.Assert;
import org.junit.Test;

public class ServioticyAPIClientTest {

    @Test
    public final void testCreateServiceObjectServiocity() throws HttpErrorException, URISyntaxException, ConnectException, ClientProtocolException {

	ComposeAPICredentials apiCredentials = new ComposeAPICredentials();
	IDMAPI idmapi = new IDMAPIClient(apiCredentials);
	ComposeUserAccess user = new ComposeUserAccess("test_compose_bdigital", "c6jvUBDV");
	ComposeUserAccessToken token = idmapi.userAuthoritzation(user);

	ServioticyAPI serviocity = new ServioticyAPIClient(apiCredentials);

	ComposeAbstractServiceObject<HashMap, ArrayList, ArrayList> serviceObject = new ComposeAbstractServiceObject<HashMap, ArrayList, ArrayList>();
	serviceObject.setName("Test Object");
	serviceObject.setDescription("Test Description");
	serviceObject.setUrl("Web Object URL");
	serviceObject.setPublic_("true");
	ComposeAbstractSOChannels channels = new ComposeAbstractSOChannels();
	channels.put("temperature", new ComposeAbstractSOChannel("number", null, "celcius"));
	serviceObject.addStream("temperature", "sensor", "description", channels);
	serviceObject.setCustomFields(new HashMap(0));
	serviceObject.setActions(new ArrayList(0));
	serviceObject.setProperties(new ArrayList(0));
	
	System.out.println("Creating Sevice Object " + serviceObject);

	ComposeSORegisteredResponse response = serviocity.createServiceObject(token, serviceObject);
	System.out.println("Created Sevice Object " + response);
	Assert.assertNotNull(response);
	Assert.assertNotNull(response.getId());
	Assert.assertNotNull(response.getApi_token());

	ComposeAbstractServiceObject so = serviocity.getServiceObject(token, response.getId());
	System.out.println("Detail of Sevice Object " + so);
	
	ComposeSOStreamsResponse streams = serviocity.getServiceObjectStreams(token, response.getId());
	System.out.println("Detail of Streams " + streams);
	
	ComposeAbstractSOChannels channelsData = new ComposeAbstractSOChannels();
	channelsData.put("temperature", new ComposeAbstractSOChannel(null, 25.8, "celcius"));
	ComposeStreamDataUpdate data = new ComposeStreamDataUpdate();
	data.setChannels(channelsData);
	data.setLastUpdate(Calendar.getInstance().getTimeInMillis());
	
	String result = serviocity.uploadDataOnServiceObjectStreams(response.getApi_token(), response.getId(), "temperature", data);
	System.out.println("Upload Data Response: " + result);
	
	String dataStreams = serviocity.getDataFromServiceObjectStreams(token, response.getId(), "temperature", null);
	System.out.println("Detail of Streams Data " + dataStreams);
	
	Set<String> list = serviocity.listServiceObject(token);
	System.out.println("List of Sevice Object " + list);
	
	for (String id : list) {
	    serviocity.removeServiceObject(token, id);
	    System.out.println("Deleted Sevice Object " + id);
	}
    }
    

}
