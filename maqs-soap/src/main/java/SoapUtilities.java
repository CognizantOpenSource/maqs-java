/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SoapUtilities {

  private SoapUtilities() {

  }

  public static Object getResponseBodyAsObjects(HttpResponse<String> closeableHttpResponse, Unmarshaller unMarshaller)
      throws SOAPException, IOException, JAXBException {
    SOAPMessage soapResp = MessageFactory.newInstance()
        .createMessage(null, new ByteArrayInputStream(
            closeableHttpResponse.body().getBytes(StandardCharsets.UTF_8)));
    return unMarshaller.unmarshal(soapResp.getSOAPBody().extractContentAsDocument());
  }
}
