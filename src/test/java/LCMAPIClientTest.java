import org.bdigital.compose.sdk.client.IDMAPI;
import org.bdigital.compose.sdk.client.IDMAPIClient;
import org.bdigital.compose.sdk.client.LCMAPI;
import org.bdigital.compose.sdk.client.LCMAPIClient;
import org.bdigital.compose.sdk.config.ComposeAPICredentials;
import org.bdigital.compose.sdk.exception.HttpErrorException;
import org.bdigital.compose.sdk.model.entity.ComposeEntities;
import org.bdigital.compose.sdk.model.user.ComposeUserAccess;
import org.bdigital.compose.sdk.model.user.ComposeUserAccessToken;
import org.junit.Assert;
import org.junit.Test;

public class LCMAPIClientTest {

    @Test
    public final void testGetEntities() throws HttpErrorException {

	ComposeAPICredentials apiCredentials = new ComposeAPICredentials();
	IDMAPI idmapi = new IDMAPIClient(apiCredentials);
	ComposeUserAccess user = new ComposeUserAccess("test_compose_bdigital", "c6jvUBDV");
	ComposeUserAccessToken token = idmapi.userAuthoritzation(user);

	LCMAPI lcmapi = new LCMAPIClient(apiCredentials);

	ComposeEntities response = lcmapi.getEntities(token);

	Assert.assertNotNull(response);
	Assert.assertNotNull(response.getMetadata());
	Assert.assertNotNull(response.getData());
    }

}
