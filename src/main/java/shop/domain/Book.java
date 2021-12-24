package shop.domain;

import java.io.Serializable;

public class Book implements Serializable{
    private int bid;
    private String bname;
    private String bintroduce;
    private int bcount;
    private int bprice;
    private String bimgid;
    private String bstatus;

    public Book() {
    }

    public Book(int bid, String bname, String bintroduce, int bcount, int bprice, String bimgid, String bstatus) {
        this.bid = bid;
        this.bname = bname;
        this.bintroduce = bintroduce;
        this.bcount = bcount;
        this.bprice = bprice;
        this.bimgid = bimgid;
        this.bstatus = bstatus;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBintroduce() {
        return bintroduce;
    }

    public void setBintroduce(String bintroduce) {
        this.bintroduce = bintroduce;
    }

    public int getBcount() {
        return bcount;
    }

    public void setBcount(int bcount) {
        this.bcount = bcount;
    }

    public int getBprice() {
        return bprice;
    }

    public void setBprice(int bprice) {
        this.bprice = bprice;
    }

    public String getBimgid() {
        return bimgid;
    }

    public void setBimgid(String bimgid) {
        this.bimgid = bimgid;
    }

    public String getBstatus() {
        return bstatus;
    }

    public void setBstatus(String bstatus) {
        this.bstatus = bstatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", bintroduce='" + bintroduce + '\'' +
                ", bcount=" + bcount +
                ", bprice=" + bprice +
                ", bimgid='" + bimgid + '\'' +
                ", bstatus='" + bstatus + '\'' +
                '}';
    }
}
