package com.example.twobirdwithonestone.Activity;

import android.os.Parcel;
import android.os.Parcelable;

public class Items implements Parcelable {
    public byte[] image;
    public String name;
    public String price;


    public Items(byte[] image, String name,String price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public Items(Parcel in) {
        readFromParcel(in);

    }

    public void readFromParcel(Parcel in){
        image = new byte[in.readInt()];
        in.readByteArray(image);
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
    public byte[] getBytes() {
        return image;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image.length);
        dest.writeByteArray(image);
        dest.writeString(name);
        dest.writeString(price);

    }


    };
