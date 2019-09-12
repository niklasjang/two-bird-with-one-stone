package com.example.twobirdwithonestone.Activity;

import android.os.Parcel;
import android.os.Parcelable;

public class Items implements Parcelable {
    public int icon;
    public String name;
    public String price;


    public Items(int icon, String name,String price) {
        this.icon = icon;
        this.name = name;
        this.price = price;
    }

    public Items(Parcel in) {
        icon = in.readInt();
        name = in.readString();
        price = in.readString();

    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public String getname() {
        return name;
    }

    public String getprice() {
        return price;
    }
    public int geticon() {
        return icon;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeString(name);
        dest.writeString(price);

    }


    };
