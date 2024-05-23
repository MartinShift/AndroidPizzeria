package com.example.android_pizzeria.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SizeType implements Parcelable {
    private String name;
    private int sizeInCm;
    private double priceMultiplier;

    public SizeType(String name, int sizeInCm, double priceMultiplier)  {
        this.name = name;
        this.sizeInCm = sizeInCm;
        this.priceMultiplier = priceMultiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSizeInCm() {
        return sizeInCm;
    }

    public void setSizeInCm(int sizeInCm) {
        this.sizeInCm = sizeInCm;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    protected SizeType(Parcel in) {
        name = in.readString();
        priceMultiplier = in.readDouble();
    }

    public static final Creator<SizeType> CREATOR = new Creator<SizeType>() {
        @Override
        public SizeType createFromParcel(Parcel in) {
            return new SizeType(in);
        }

        @Override
        public SizeType[] newArray(int size) {
            return new SizeType[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(priceMultiplier);
    }
}