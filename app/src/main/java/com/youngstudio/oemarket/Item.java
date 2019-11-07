package com.youngstudio.oemarket;

public class Item {

    String msg1;
    String msg2;
    String msg3;
    int img;

    public Item(String msg1, String msg2, String msg3, int img) {
        this.msg1 = msg1;
        this.msg2 = msg2;
        this.msg3 = msg3;
        this.img = img;
    }


    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public String getMsg3() {
        return msg3;
    }

    public void setMsg3(String msg3) {
        this.msg3 = msg3;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


}
