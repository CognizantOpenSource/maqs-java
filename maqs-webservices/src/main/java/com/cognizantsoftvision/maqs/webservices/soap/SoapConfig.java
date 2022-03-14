/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices.soap;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import java.util.Map;

/**
 * The Soap Config class.
 */
public final class SoapConfig {

  private static final String SOAP_SECTION = "SoapMaqs";
  private static final String SOAP_NAMESPACE_SECTION = "SoapMaqsNameSpaces";

  private SoapConfig() {

  }

  /**
   * Gets the Soap Prefix from the config file.
   *
   * @return the string of the soap prefix
   */
  public static String getSoapPrefix() {
    return Config.getValueForSection(SOAP_SECTION, "SoapPrefix");
  }

  /**
   * Gets the Soap namespaces from the config.
   *
   * @return the Soap namespaces in a map format
   */
  public static Map<String, String> getSoapNamespaces() {
    return Config.getSection(SOAP_NAMESPACE_SECTION);
  }
}
