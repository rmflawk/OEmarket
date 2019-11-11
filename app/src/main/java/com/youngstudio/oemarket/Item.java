package com.youngstudio.oemarket;

public class Item {

    int no;
    String name;
    String date;
    String price;
    String mainmsg;
    String imgPath;
    String subName;
    int img;

    public Item(int no, String name, String price, String date, String mainmsg, String imgPath) {
        this.no = no;
        this.name = name;
        this.date = date;
        this.price = price;
        this.mainmsg = mainmsg;
        this.imgPath = imgPath;
    }

    public Item(String name, String date, String subName, int img) {
        this.name = name;
        this.date = date;
        this.subName = subName;
        this.img = img;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMainmsg() {
        return mainmsg;
    }

    public void setMainmsg(String mainmsg) {
        this.mainmsg = mainmsg;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
