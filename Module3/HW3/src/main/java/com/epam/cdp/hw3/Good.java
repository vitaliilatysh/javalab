package com.epam.cdp.hw3;

import java.io.Serializable;
import java.math.BigDecimal;

public class Good implements Serializable {
    private String name;
    private GoodType type;
    private int amount;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodType getType() {
        return type;
    }

    public void setType(GoodType type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
