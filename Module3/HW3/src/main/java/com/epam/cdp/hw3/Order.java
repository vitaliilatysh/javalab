package com.epam.cdp.hw3;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private String customerName;
    private List<Good> listGoods;

    public Order() {

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Good> getListGoods() {
        return listGoods;
    }

    public void setListGoods(List<Good> listGoods) {
        this.listGoods = listGoods;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", listGoods=" + listGoods +
                '}';
    }
}
