package com.emapel.seeyou.seeyou.classes;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class User implements Serializable{
    private String name;
    private String email;
    private String addrMac;
    private LatLng actual_location;

    public User(){};

    public User(String name, String email, String addrMac, LatLng actual_location){
        this.name = name;
        this.email = email;
        this.addrMac = addrMac;
        this.actual_location = actual_location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddrMac() {
        return addrMac;
    }

    public void setAddrMac(String addrMac) {
        this.addrMac = addrMac;
    }

    public LatLng getActual_location() {
        return actual_location;
    }

    public void setActual_location(LatLng actual_location) {
        this.actual_location = actual_location;
    }
}
