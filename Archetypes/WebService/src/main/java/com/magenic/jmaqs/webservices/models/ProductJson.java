package com.magenic.jmaqs.webservices.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductJson {

  @SerializedName("Id") @Expose private int id;
  @SerializedName("Name") @Expose private String name;
  @SerializedName("Category") @Expose private String category;
  @SerializedName("Price") @Expose private Integer price;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }
}
