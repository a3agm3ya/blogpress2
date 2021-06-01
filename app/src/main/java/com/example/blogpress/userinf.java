package com.example.blogpress;

public class userinf {
    String name;
    String hobb;
    String aboutu;

    public userinf(){}

    public userinf(String name, String hobb, String aboutu) {
        this.name = name;
        this.hobb = hobb;
        this.aboutu = aboutu;
    }

    public String getName() {
        return name;
    }

    public String getHobb() {
        return hobb;
    }

    public String getAboutu() {
        return aboutu;
    }
}
