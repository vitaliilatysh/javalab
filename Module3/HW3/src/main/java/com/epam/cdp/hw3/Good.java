package com.epam.cdp.hw3;

import java.io.Serializable;
import java.math.BigDecimal;

public class Good implements Serializable {
    private String name;
    private GoodType type;
    private int amount;
    private BigDecimal price;

    Good(){

    }

    Good(String name, GoodType type, int amount, BigDecimal price){
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.price = price;
    }
    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    GoodType getType() {
        return type;
    }

    void setType(GoodType type) {
        this.type = type;
    }

    int getAmount() {
        return amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }

    BigDecimal getPrice() {
        return price;
    }

    void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
