package com.example.ezyfood.model;

import java.io.Serializable;

public class Order implements Serializable {

    private int quantity;
    private int price;
    private String FoodName;

    public Order(int quantity, int price, String foodName) {
        this.quantity = quantity;
        this.price = price;
        FoodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }
}
