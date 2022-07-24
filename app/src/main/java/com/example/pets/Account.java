package com.example.pets;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Account implements Parcelable {
    public String key;
    public String username;
    public String password;
    public String email;
    public String nomor;
    public String twitter;
    public String facebook;

    public Account(){

    }

    public Account(String key, String username, String password, String email, String nomor, String twitter, String facebook){
        this.key = key;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nomor= nomor;
        this.twitter = twitter;
        this.facebook = facebook;

    }

    protected Account(Parcel in) {
        key = in.readString();
        username = in.readString();
        password = in.readString();
        email = in.readString();
        nomor = in.readString();
        twitter = in.readString();
        facebook = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeString(nomor);
        dest.writeString(twitter);
        dest.writeString(facebook);
    }
}
