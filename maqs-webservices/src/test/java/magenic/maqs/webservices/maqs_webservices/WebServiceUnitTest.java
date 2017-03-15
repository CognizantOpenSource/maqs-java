package magenic.maqs.webservices.maqs_webservices;

import magenic.maqs.webservices.BaseWebServiceTest.HttpClientWrapper;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServiceUnitTest {

	/**
	 * Verifies that basic GET features work with the HttpClientWrapper
	 * 
	 * @throws Exception
	 */
	@Test
	public void webServiceGETVerificationTest() throws Exception {
		HttpClientWrapper client = new HttpClientWrapper(
				"http://magenicautomation.azurewebsites.net",
				ContentType.TEXT_PLAIN);
		CloseableHttpResponse response = client.GetWithResponse(
				"/api/String/1", true);
		Assert.assertTrue(response.toString().contains("Tomato Soup"),
				"Was expecting a result with Tomato Soup but instead got - "
						+ response.toString());

	}

}