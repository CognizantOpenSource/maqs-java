/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

/**
 * The enum Media type.
 */
public enum MediaType {
  /**
   * App json media type.
   */
  APP_JSON("application/json"),
  /**
   * App xml media type.
   */
  APP_XML("application/xml"),
  /**
   * Plain text media type.
   */
  PLAIN_TEXT("text/plain"),
  /**
   * Image png media type.
   */
  IMAGE_PNG("image/png"),

  /**
   * Octet stream media type.
   */
  OCTET_STREAM("application/octet-stream"),

  /**
   * Form urlencoded media type.
   */
  FORM_URLENCODED("application/x-www-form-urlencoded");

  /**
   * Getter for property 'mediaTypeString'.
   *
   * @return Value for property 'mediaTypeString'.
   */
  public String getMediaTypeString() {
    return mediaTypeString;
  }

  /**
   * The Media type.
   */
  private final String mediaTypeString;

  MediaType(String string) {
    this.mediaTypeString = string;
  }

  @Override
  public String toString() {
    //HACK: This is an attempt to avoid calling for the string explicitly.  Review when necessary
    return mediaTypeString;
  }
}
