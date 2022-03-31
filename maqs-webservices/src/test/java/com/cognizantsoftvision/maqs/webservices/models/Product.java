/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.math.BigDecimal;

@JsonPropertyOrder({"id", "name", "category", "price"})
@JacksonXmlRootElement(localName = "Product")
public class Product {

    @JacksonXmlProperty(isAttribute = true)
    private final String xmlns = "http://schemas.datacontract.org/2004/07/MainTestService.Models";

    @JacksonXmlProperty(localName = "Id")
    @JsonAlias({"Id", "id"})
    private int Id;

    @JacksonXmlProperty(localName = "Name")
    @JsonAlias({"Name", "name"})
    private String Name;

    @JacksonXmlProperty(localName = "Category")
    @JsonAlias({"Category", "category"})
    private String Category;

    @JacksonXmlProperty(localName = "Price")
    @JsonAlias({"Price", "price"})
    private BigDecimal Price;

    public Product(int id, String name, String category, BigDecimal price) {
        this.Id = id;
        this.Name = name;
        this.Category = category;
        this.Price = price;
    }

    // Used to populate the product class
    public Product() {
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public String getCategory() {
        return Category;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        this.Price = price;
    }

    public String toString() {
        return String.format("%s:%d\n", "Id", this.getId()) + String
            .format("%s:%s\n", "Name", this.getName()) + String
            .format("%s:%s\n", "Category", this.getCategory()) + String
            .format("%s:%s\n", "Price", this.getPrice());
    }
}
