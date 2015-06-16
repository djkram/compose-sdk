import org.bdigital.compose.sdk.client.IDMAPI;
import org.bdigital.compose.sdk.client.IDMAPIClient;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpError;
import org.bdigital.compose.sdk.model.request.ComposeUserAccess;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ComposeExistingUser;
import org.junit.Assert;
import org.junit.Test;


public class IDMAPIClientTest {

	@Test
	public final void testUserAuthoritzation() throws HttpError {
		
		ComposeAPICredentials apiCredentials = new ComposeAPICredentials();
		
		IDMAPI idmapi = new IDMAPIClient(apiCredentials);
		
		ComposeUserAccess user = new ComposeUserAccess("bdigital_user_test", "c6jvUBDV");
		
		ComposeExistingUser createResponse = idmapi.createUser(user);
		
		//ComposeUserAccess acces = new ComposeUserAccess("test_compose_bdigital", "c6jvUBDV");
		
		AccessToken token = idmapi.userAuthoritzation(user);
		
		System.out.println(token);
		Assert.assertNotNull(token);
		Assert.assertNotNull(token.getAccessToken());
		Assert.assertNotNull(token.getToken_type());
		
	}

}
