/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices.soap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * The Soap Utilities class.
 */
public class SoapUtilities {

  private SoapUtilities() {

  }

  /**
   * Gets the response Body as an Object.
   *
   * @param httpResponse the http response
   * @param unMarshaller the unmarshaller for the soap process
   * @return the response object
   * @throws SOAPException if a SOAP exception is thrown
   * @throws IOException if an IO exception is thrown
   * @throws JAXBException if a JAXB exception is thrown
   */
  public static Object getResponseBodyAsObjects(HttpResponse<String> httpResponse, Unmarshaller unMarshaller)
      throws SOAPException, IOException, JAXBException {
    SOAPMessage soapResp = MessageFactory.newInstance()
        .createMessage(null, new ByteArrayInputStream(
            httpResponse.body().getBytes(StandardCharsets.UTF_8)));
    return unMarshaller.unmarshal(soapResp.getSOAPBody().extractContentAsDocument());
  }
}
