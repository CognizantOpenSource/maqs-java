package com.magenic.jmaqs.webservices;

import com.magenic.jmaqs.webservices.models.Product;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServiceUtilitiesTest extends BaseWebServiceTest {

    @Test
    public void testGetResponseBody() throws Exception {
        CloseableHttpResponse response = this.getHttpClientWrapper().getContent("/api/String/1",
                ContentType.TEXT_PLAIN, true);
        String responseString = WebServiceUtilities.getResponseBody(response);

        Assert.assertNotNull(responseString, "Response body did not deserialize string correctly");
    }

    @Test
    public void testCreateEntity() throws Exception {
        HttpEntity content = WebServiceUtilities.createEntity("", ContentType.TEXT_PLAIN);
        CloseableHttpResponse response = this.getHttpClientWrapper().postContent("/api/String", content,
                ContentType.TEXT_PLAIN, false);
    }

    @Test
    public void testDeserializeJson() throws Exception {
        CloseableHttpResponse response = this.getHttpClientWrapper().getContent("/api/XML_JSON/GetProduct/1",
                ContentType.APPLICATION_JSON, true);
        Product product = WebServiceUtilities.deserializeJson(response, Product.class);

        Assert.assertNotNull(product);
    }

    @Test
    public void testDeserializeXml() throws Exception {
        CloseableHttpResponse response = this.getHttpClientWrapper().getContent("/api/XML_JSON/GetProduct/1",
                ContentType.APPLICATION_XML, true);
        Product product = WebServiceUtilities.deserializeXml(response, Product.class);

        Assert.assertNotNull(product);
    }

    @Test
    public void testGetXmlAsString() throws Exception {
        CloseableHttpResponse response = this.getHttpClientWrapper().getContent("/api/XML_JSON/GetProduct/1",
                ContentType.APPLICATION_XML, true);

    }
}
