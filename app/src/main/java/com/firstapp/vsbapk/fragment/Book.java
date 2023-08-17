package com.firstapp.vsbapk.fragment;

import java.io.Serializable;

public class Book implements Serializable {
    private String name ;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    private String seller = "XYZ";

    private String standard ;

    private String meduim ;

    private String mrp ;

    private String price ;

    private String condition ;

    private boolean cod ;

    private boolean returnAvaiable ;

    private String delivery ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getMeduim() {
        return meduim;
    }

    public void setMeduim(String meduim) {
        this.meduim = meduim;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isCod() {
        return cod;
    }

    public void setCod(boolean cod) {
        this.cod = cod;
    }

    public boolean isReturnAvaiable() {
        return returnAvaiable;
    }

    public void setReturnAvaiable(boolean returnAvaiable) {
        this.returnAvaiable = returnAvaiable;
    }

    public String getDelivery() {
        return delivery;
    }

    public Book() {
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;



    }
}
