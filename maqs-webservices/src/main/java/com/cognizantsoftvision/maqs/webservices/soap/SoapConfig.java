/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices.soap;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import java.util.Map;

/**
 * The Soap Config class.
 */
public final class SoapConfig {

  private SoapConfig() {
  }

  /**
   * Gets the Soap Prefix from the config file.
   *
   * @return the string of the soap prefix
   */
  public static String getSoapPrefix() {
    return Config.getValueForSection(ConfigSection.SOAP_MAQS, "SoapPrefix");
  }

  /**
   * Gets the Soap namespaces from the config.
   *
   * @return the Soap namespaces in a map format
   */
  public static Map<String, String> getSoapNamespaces() {
    return Config.getSection(ConfigSection.SOAP_NAMESPACE_MAQS);
  }
}
