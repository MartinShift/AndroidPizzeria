package com.example.android_pizzeria.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Pizza implements Parcelable {
    private SizeType size;
    private PizzaType type;
    private ArrayList<Topping> toppings;

    public Pizza(SizeType size, PizzaType type) {
        this.size = size;
        this.type = type;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public SizeType getSize() {
        return size;
    }

    public void setSize(SizeType size) {
        this.size = size;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public double getPrice() {
        double total = type.getBasePrice();
        for (Topping topping : toppings) {
            total += topping.getPrice();
        }
        return total  * size.getPriceMultiplier();
    }

    protected Pizza(Parcel in) {
        size = in.readParcelable(SizeType.class.getClassLoader());
        type = in.readParcelable(PizzaType.class.getClassLoader());
        toppings = in.createTypedArrayList(Topping.CREATOR);
    }

    public static final Parcelable.Creator<Pizza> CREATOR = new Parcelable.Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(size, flags);
        dest.writeParcelable(type, flags);
        dest.writeTypedList(toppings);
    }
}