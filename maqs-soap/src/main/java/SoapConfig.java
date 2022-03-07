/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import java.util.Map;

public final class SoapConfig {

  private static final String SOAP_SECTION = "SoapMaqs";
  private static final String SOAP_NAMESPACE_SECTION = "SoapMaqsNameSpaces";

  private SoapConfig() {

  }

  public static String getSoapPrefix() {
    return Config.getValueForSection(SOAP_SECTION, "SoapPrefix");
  }

  public static Map<String, String> getSoapNamespaces() {
    return Config.getSection(SOAP_NAMESPACE_SECTION);
  }

}
