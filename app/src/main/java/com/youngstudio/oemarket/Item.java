package com.youngstudio.oemarket;

public class Item {

    String title;
    String date;
    String mainmsg;
    int img;

    public Item(String title, String date, String mainmsg, int img) {
        this.title = title;
        this.date = date;
        this.mainmsg = mainmsg;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMainmsg() {
        return mainmsg;
    }

    public void setMainmsg(String mainmsg) {
        this.mainmsg = mainmsg;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
