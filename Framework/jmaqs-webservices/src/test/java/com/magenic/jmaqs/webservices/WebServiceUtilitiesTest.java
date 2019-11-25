package com.magenic.jmaqs.webservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magenic.jmaqs.webservices.models.Product;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.Charset;

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
    public void testCreateStringEntityJson() throws JsonProcessingException {
        Product product = new Product();
        HttpEntity entity = WebServiceUtilities.createStringEntity(product, ContentType.APPLICATION_JSON);

        Assert.assertNotNull(entity, "string entity wasn't created using content type application/json");
    }

    @Test
    public void testCreateStringEntityXml() throws JsonProcessingException {
        Product product = new Product();
        HttpEntity entity = WebServiceUtilities.createStringEntity(product, ContentType.APPLICATION_XML);

        Assert.assertNotNull(entity, "string entity wasn't created using content type application/xml");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateStringEntityNotJsonOrXml() throws JsonProcessingException {
        Product product = new Product();
        HttpEntity entity = WebServiceUtilities.createStringEntity(product, ContentType.APPLICATION_FORM_URLENCODED);

        Assert.fail("Expected exception of IllegalArgumentException was not caught for content type that did not contain xml or json");
    }

    @Test
    public void testCreateStringEntityCustomContentType() {
        Product product = new Product();
        HttpEntity entity = WebServiceUtilities.createStringEntity(product.toString(), Charset.defaultCharset(), "application/json");

        Assert.assertNotNull(entity, "Entity was not set correctly with custom charset and mime-type");
    }
}
