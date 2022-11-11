/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.helper;

import java.util.List;

public class ConfigValidation {

  List<String> requiredFields;

  List<String> requiredOneOfFields;

  /// <summary>
  /// Gets or sets the list of required fields for a config
  /// </summary>
  public List<String> getRequiredFields() {
    return requiredFields;
  }


  public void setRequiredFields(List<String> requiredFields) {
    this.requiredFields = requiredFields;
  }

  /// <summary>
  /// Gets or sets the list of fields you need at least one of for a config
  /// </summary>
  public List<String> getRequiredOneOfFields() {
    return this.requiredOneOfFields;
  }

  public void setRequiredOneOfFields(List<String> requiredFields) {
    requiredOneOfFields = requiredFields;
  }
}
