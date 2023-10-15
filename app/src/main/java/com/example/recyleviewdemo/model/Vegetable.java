package com.example.recyleviewdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Vegetable implements Parcelable {
    private int img;
    private String name;

    private double price;
    private String des;

    public Vegetable() {

    }

    public Vegetable(int img, String name, double price, String des) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.des = des;

    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(img);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(des);
    }

    public static final Parcelable.Creator<Vegetable> CREATOR = new Parcelable.Creator<Vegetable>() {
        public Vegetable createFromParcel(Parcel in) {
            return new Vegetable(in);
        }

        public Vegetable[] newArray(int size) {
            return new Vegetable[size];
        }
    };

    private Vegetable(Parcel in) {
        img = in.readInt();
        name = in.readString();
        price = in.readDouble();
        des = in.readString();
    }
}
