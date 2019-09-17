package com.example.twobirdwithonestone.Activity;

import android.os.Parcel;
import android.os.Parcelable;

public class Items implements Parcelable {
    public byte[] image;
    public String name;
    public String price;
    public String explanation;


    public Items(byte[] image, String name,String price,String explanation) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.explanation = explanation;
    }

    public Items(Parcel in) {
        readFromParcel(in);

    }

    public void readFromParcel(Parcel in){
        // readByteArray가 받는 인자를 readInt형으로 바꾸어 줌
        image = new byte[in.readInt()];
        in.readByteArray(image);
        name = in.readString();
        price = in.readString();
        explanation = in.readString();
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
    public String getexplanation() {
        return explanation;
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
        // read 형식과 동일하게 int형 써주고 bytearray, readfromparcel 와 같이 순서 동일하게 적어주어야함
        dest.writeInt(image.length);
        dest.writeByteArray(image);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(explanation);
    }


    };
