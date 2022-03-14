package com.cognizantsoftvision.maqs.webservices.soap;

import java.util.Collections;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Soap Utilities unit test class.
 */
public class SoapConfigUnitTest {

  @Test
  public void getSoapPrefix() {
    Assert.assertEquals(SoapConfig.getSoapPrefix(),
        "soapenv", "the SOAP Prefixes does not match");
  }

  @Test
  public void getSoapNamespaces() {
    Map<String, String> namespaces = Collections.singletonMap("gs", "http://spring.io/guides/gs-producing-web-service");

    Assert.assertEquals(SoapConfig.getSoapNamespaces(),
       namespaces , "The SOAP Namespaces do not matche");
  }
}
