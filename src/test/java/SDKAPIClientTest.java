import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.bdigital.compose.sdk.client.IDMAPI;
import org.bdigital.compose.sdk.client.IDMAPIClient;
import org.bdigital.compose.sdk.client.SDKAPI;
import org.bdigital.compose.sdk.client.SDKAPIClient;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractSOChannelType;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractSOChannels;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractSOLocation;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractSOStreams;
import org.bdigital.compose.sdk.model.serviceobject.ComposeAbstractServiceObject;
import org.bdigital.compose.sdk.model.serviceobject.ComposeServiceObjectRegistered;
import org.bdigital.compose.sdk.model.user.ComposeUserAccess;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;
import org.bdigital.compose.sdk.utils.HttpRestClient;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class SDKAPIClientTest {

    @Test
    public final void testCreateServiceObject() throws HttpErrorException, URISyntaxException, ConnectException, ClientProtocolException {

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
	channels.put("temperature", new ComposeAbstractSOChannelType("Celsius", "number"));
	serviceObject.addStream("sensor", channels);
	serviceObject.setCustomFields(new HashMap(0));
	serviceObject.setActions(new ArrayList(0));
	serviceObject.setProperties(new ArrayList(0));
	
	System.out.println(serviceObject);

	// Map<String, Object> payload = new HashMap<String, Object>();
	// payload.put("name", "Test Object");
	// payload.put("description", "Test Description");
	// payload.put("URL", "Web Object URL");
	// payload.put("public", "true");
	// payload.put("streams", new HashMap(0));
	// JSONObject json = new JSONObject(payload);
	//
	// URI uri = new
	// URI("http://compose-sdk4.147.83.30.133.xip.io/serviceobjects");
	// String[] result = RestClient.doPost(uri, json,
	// token.getAccessToken());
	// System.out.println(result[1]);

	ComposeServiceObjectRegistered response = sdkapi.createServiceObject(token, serviceObject);
	Assert.assertNotNull(response);
	Assert.assertNotNull(response.getId());
	Assert.assertNotNull(response.getApi_token());
    }

}
