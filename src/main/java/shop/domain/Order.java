package shop.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {
    private String oid;
    private String username;
    private String name;
    private String address;
    private String telephone;
    private double price;
    private String status;
    private Timestamp time_stamp;

    public Order() {
    }

    public Order(String oid, String username, String name, String address, String telephone, double price, String status, Timestamp time_stamp) {
        this.oid = oid;
        this.username = username;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.price = price;
        this.status = status;
        this.time_stamp = time_stamp;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Timestamp time_stamp) {
        this.time_stamp = time_stamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", time_stamp=" + time_stamp +
                '}';
    }
}
