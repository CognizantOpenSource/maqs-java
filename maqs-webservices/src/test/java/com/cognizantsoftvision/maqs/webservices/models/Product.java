/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.math.BigDecimal;

@JsonPropertyOrder({"id", "name", "category", "price"})
public class Product {
    @JacksonXmlProperty(isAttribute = true)
    private final String xmlns = "http://schemas.datacontract.org/2004/07/MainTestService.Models";

    @JsonAlias({"Id", "id"})
    private final int Id;

    @JsonAlias({"Name", "name"})
    private final String Name;

    @JsonAlias({"Category", "category"})
    private final String Category;

    @JsonAlias({"Price", "price"})
    private final BigDecimal Price;

    public Product(int id, String name, String category, BigDecimal price) {
        this.Id = id;
        this.Name = name;
        this.Category = category;
        this.Price = price;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getCategory() {
        return Category;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public String toString() {
        return String.format("%s:%d\n", "Id", this.getId()) + String
            .format("%s:%s\n", "Name", this.getName()) + String
            .format("%s:%s\n", "Category", this.getCategory()) + String
            .format("%s:%s\n", "Price", this.getPrice());
    }
}
