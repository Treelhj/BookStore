package shop.domain;

import java.io.Serializable;

public class Cart implements Serializable{
    private int cid;
    private int bid;
    private int uid;
    private String username;
    private String bname;
    private int num;
    private double price;
    private double totalprice;
    private String status;

    public Cart() {
    }

    public Cart(int cid, int bid, int uid, String username, String bname, int num, double price, double totalprice, String status) {
        this.cid = cid;
        this.bid = bid;
        this.uid = uid;
        this.username = username;
        this.bname = bname;
        this.num = num;
        this.price = price;
        this.totalprice = totalprice;
        this.status = status;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", bid=" + bid +
                ", uid=" + uid +
                ", username='" + username + '\'' +
                ", bname='" + bname + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", totalprice=" + totalprice +
                ", status='" + status + '\'' +
                '}';
    }
}
