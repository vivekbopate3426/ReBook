package com.firstapp.vsbapk;

public class Profile {

    public String getMono() {
        return Mono;
    }

    public void setMono(String mono) {
        Mono = mono;
    }

    public String getSeconMono() {
        return seconMono;
    }

    public void setSeconMono(String seconMono) {
        this.seconMono = seconMono;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Profile(){}
    private String Mono;
    private String seconMono;
    private String address;

}
