package com.example.landmarkbook;

import java.io.Serializable;

public class landMark implements Serializable {

    String name;
    String country;
    int image;

    public landMark(String name, String country, int image) {
        this.name = name;
        this.country = country;
        this.image = image;
    }
}
