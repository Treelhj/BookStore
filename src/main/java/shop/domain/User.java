package shop.domain;

import java.io.Serializable;


public class User implements Serializable {
    private int uid;
    private String username;
    private String password;
    private String email;
    private String status;
    private String code;

    public User() {
    }

    public User(int uid, String username, String password, String email, String status, String code) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.code = code;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
