package com.example.android_pizzeria.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PizzaType implements Parcelable {
    private String name;
    private double basePrice;
    private String imageUrl;

    public PizzaType(String name, double basePrice, String imageUrl) {
        this.name = name;
        this.basePrice = basePrice;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    protected PizzaType(Parcel in) {
        name = in.readString();
        basePrice = in.readDouble();
        imageUrl = in.readString();
    }

    public static final Parcelable.Creator<PizzaType> CREATOR = new Parcelable.Creator<PizzaType>() {
        @Override
        public PizzaType createFromParcel(Parcel in) {
            return new PizzaType(in);
        }

        @Override
        public PizzaType[] newArray(int size) {
            return new PizzaType[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(basePrice);
        parcel.writeString(imageUrl);
    }
}