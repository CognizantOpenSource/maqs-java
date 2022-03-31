package com.cognizantsoftvision.maqs.webservices.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ArrayOfProduct {

  @JacksonXmlProperty(isAttribute = true)
  private final String xmlns = "http://schemas.datacontract.org/2004/07/MainTestService.Models";

  private ArrayOfProductProduct[] productField;

  @JsonAlias({"Product", "product"})
  public ArrayOfProductProduct[] product;

  public ArrayOfProductProduct[] getProduct() {
    return this.product;
  }

  public void setProduct(ArrayOfProductProduct[] product) {
    this.product = product;
  }
}
