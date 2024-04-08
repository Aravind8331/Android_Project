package com.example.goodwillapp.common;

public class ItemModel {
    String itemName ;
    int itemID ;

    public ItemModel() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public ItemModel(String itemName, int itemID) {
        this.itemName = itemName;
        this.itemID = itemID;
    }
}
