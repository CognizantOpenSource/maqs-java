package com.cognizantsoftvision.maqs.webservices.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ArrayOfProductProduct {

  @JacksonXmlProperty(isAttribute = true)
  private final String xmlns = "http://schemas.datacontract.org/2004/07/MainTestService.Models";

  private String categoryField;

  private byte idField;

  private String nameField;

  private float priceField;

  public String getCategoryField() {
    return this.categoryField;
  }

  public void setCategoryField(String categoryField) {
    this.categoryField = categoryField;
  }

  public byte getIdField() {
    return this.idField;
  }

  public void setIdField(byte idField) {
    this.idField = idField;
  }

  public String getNameField() {
    return this.nameField;
  }

  public void setNameField(String nameField) {
    this.nameField = nameField;
  }

  public float getPriceField() {
    return this.priceField;
  }

  public void setPriceField(float priceField) {
    this.priceField = priceField;
  }
}
