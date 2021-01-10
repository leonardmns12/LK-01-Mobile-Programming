package com.example.ezyfood.model;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private String itemName;
    private int qty;
    private Long date;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Transaction(String itemName , int qty) {
        this.itemName = itemName;
        this.qty = qty;
        this.date = new Date().getTime();
    }

}
