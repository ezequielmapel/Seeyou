package com.emapel.seeyou.seeyou.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class User implements Parcelable{
    private String name;
    private String email;
    private String addrMac;
    private String pass;
    private String cpf;



    private LatLng actual_location;

    public User(){};

    public User(String name, String email, String addrMac, LatLng actual_location){
        this.name = name;
        this.email = email;
        this.addrMac = addrMac;
        this.actual_location = actual_location;
    }


    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        addrMac = in.readString();
        pass = in.readString();
        cpf = in.readString();
        actual_location = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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
    public String getPass() { return pass; }
    public String getCpf() {return cpf;}
    public void setCpf(String cpf) { this.cpf = cpf; }

    public void setPass(String pass) { this.pass = pass; }
    public void setActual_location(LatLng actual_location) {
        this.actual_location = actual_location;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(addrMac);
        dest.writeString(pass);
        dest.writeString(cpf);
        dest.writeParcelable(actual_location, flags);
    }
}
