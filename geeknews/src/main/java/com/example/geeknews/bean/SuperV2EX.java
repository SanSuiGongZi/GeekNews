package com.example.geeknews.bean;

import java.io.Serializable;

public class SuperV2EX implements Serializable {

    private String img;
    private String title;
    private String str;
    private String num;

    public SuperV2EX(String img, String title, String str, String num) {
        this.img = img;
        this.title = title;
        this.str = str;
        this.num = num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
