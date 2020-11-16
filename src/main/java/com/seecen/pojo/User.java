package com.seecen.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/9
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String tel;
    private int role;

    public User() {
    }

    public User(int id, String username, String password, String tel, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", role=" + role +
                '}';
    }
}
