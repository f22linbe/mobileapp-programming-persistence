package com.example.persistence;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;


public class Island {
    @SerializedName("ID")
    private String id;

    private String name;
    private String location;
    private String category;

    @SerializedName("size")
    private int squarekm;

    public Island(String _name, String _location, int _size) {
        name=_name;
        location=_location;
        squarekm= Integer.parseInt(Integer.toString(_size));
    }


    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public int getSquarekm() { return squarekm;}

}