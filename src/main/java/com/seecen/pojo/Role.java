package com.seecen.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/14
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */
public class Role {
    private int role;
    private String explain;

    public Role() {
    }

    public Role(int role, String explain) {
        this.role = role;
        this.explain = explain;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role=" + role +
                ", explain='" + explain + '\'' +
                '}';
    }
}
