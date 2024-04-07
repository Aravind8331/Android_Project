package com.example.goodwillapp.common;

import java.io.Serializable;

public class ContributorModel implements Serializable {

    String contributorName;
    String itemName;
    String itemType;
    String itemCondition;
    String itemImage;

    String emailID;
    String address;
    String mobileNumber;
    String dbKey;

    public String getContributorName() {
        return contributorName;
    }

    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDbKey() {
        return dbKey;
    }

    public void setDbKey(String dbKey) {
        this.dbKey = dbKey;
    }

    public ContributorModel(String contributorName, String itemName, String itemType, String itemCondition, String itemImage, String emailID, String address, String mobileNumber, String dbKey) {
        this.contributorName = contributorName;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemCondition = itemCondition;
        this.itemImage = itemImage;
        this.emailID = emailID;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.dbKey = dbKey;
    }

    public ContributorModel() {
    }

}
