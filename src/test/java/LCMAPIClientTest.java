import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.Assert;

import org.bdigital.compose.sdk.client.IDMAPI;
import org.bdigital.compose.sdk.client.IDMAPIClient;
import org.bdigital.compose.sdk.client.LCMAPI;
import org.bdigital.compose.sdk.client.LCMAPIClient;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpError;
import org.bdigital.compose.sdk.model.request.AbstractServiceObject;
import org.bdigital.compose.sdk.model.request.ComposeUserAccess;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ServiceObjectResponse;
import org.junit.Ignore;
import org.junit.Test;


public class LCMAPIClientTest {

	
	@Test
	public final void testGetEntities() throws HttpError{
		
		ComposeAPICredentials apiCredentials = new ComposeAPICredentials();
		IDMAPI idmapi = new IDMAPIClient(apiCredentials);
		ComposeUserAccess user = new ComposeUserAccess("test_compose_bdigital", "c6jvUBDV");
		AccessToken token = idmapi.userAuthoritzation(user);
		
		LCMAPI lcmapi = new LCMAPIClient(apiCredentials);
		
		String response = lcmapi.getEntities(token);
		
		System.out.println(response);
	}
	

	
	
}
