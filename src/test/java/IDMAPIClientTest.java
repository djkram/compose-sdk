import java.util.UUID;

import org.bdigital.compose.sdk.client.IDMAPI;
import org.bdigital.compose.sdk.client.IDMAPIClient;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.request.ComposeUserAccess;
import org.bdigital.compose.sdk.model.response.AccessToken;
import org.bdigital.compose.sdk.model.response.ComposeUserRegistered;
import org.junit.Assert;
import org.junit.Test;

public class IDMAPIClientTest {

    @Test
    public final void testUserAuthoritzation() throws HttpErrorException {

	String username = "bdigital_user_test_" + UUID.randomUUID().toString();
	// "test_compose_bdigital"
	String password = "c6jvUBDV";

	ComposeAPICredentials apiCredentials = new ComposeAPICredentials();

	IDMAPI idmapi = new IDMAPIClient(apiCredentials);

	ComposeUserAccess user = new ComposeUserAccess(username, password);

	ComposeUserRegistered createResponse = idmapi.createUser(user);

	System.out.println(createResponse);
	Assert.assertNotNull(createResponse);

	AccessToken token = idmapi.userAuthoritzation(user);

	Assert.assertNotNull(token);
	Assert.assertNotNull(token.getAccessToken());
	Assert.assertNotNull(token.getToken_type());

	ComposeUserRegistered selfuser = idmapi.getSelfUser(token);
	Assert.assertNotNull(selfuser);

	ComposeUserRegistered userById = idmapi.getUserById(token, createResponse.getId());
	Assert.assertNotNull(userById);

	idmapi.deleteUser(createResponse.getId(), createResponse.getLastModified());
    }

}
