/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices.soap;

import com.cognizantsoftvision.maqs.webservices.WebServiceConfig;
import java.util.HashMap;
import java.util.function.Consumer;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * The Soap Web Service Driver Factory class.
 */
public class SoapWebServiceDriverFactory {

  private SoapWebServiceDriverFactory() {
  }

  public static SOAPMessage getDefaultMessage() throws SOAPException {
    SOAPMessage message = MessageFactory.newInstance().createMessage();
    SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();

    if (!SoapConfig.getSoapPrefix().isEmpty()) {
      modifySoapPrefix(envelope);
    }

    if (!SoapConfig.getSoapNamespaces().isEmpty()) {
      addNameSpaceCollectionToMessage(envelope, (HashMap<String, String>) SoapConfig.getSoapNamespaces());
    }
    return message;
  }

  public static SoapWebServiceDriver getDefaultSoapDriver() throws SOAPException {
    return new SoapWebServiceDriver(getDefaultMessage(), WebServiceConfig.getWebServiceURIString());
  }

  protected static SOAPEnvelope modifySoapPrefix(SOAPEnvelope envelope) throws SOAPException {
    envelope.removeNamespaceDeclaration(envelope.getPrefix());
    addNameSpaceToMessage(envelope, SoapConfig.getSoapPrefix(), "http://schemas.xmlsoap.org/soap/envelope/");
    envelope.setPrefix(SoapConfig.getSoapPrefix());
    envelope.getBody().setPrefix(SoapConfig.getSoapPrefix());
    envelope.getHeader().setPrefix(SoapConfig.getSoapPrefix());
    return envelope;
  }

  protected static void addNameSpaceCollectionToMessage(SOAPEnvelope envelope, HashMap<String, String> map) {
    Consumer<String> consumer = key -> {
      String value = map.get(key);
      try {
        addNameSpaceToMessage(envelope, key, value);
      } catch (SOAPException e) {
        e.printStackTrace();
      }
    };
    map.keySet().stream().iterator().forEachRemaining(consumer);
  }

  public static void addNameSpaceToMessage(SOAPEnvelope envelope, String key, String value) throws SOAPException {
    envelope.addNamespaceDeclaration(key, value);
  }
}
