package com.seecen.vo;

import com.seecen.pojo.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/14
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class RoleVo {
    private int code;
    private int count;
    private String msg;
    private List<Role> data;

    public RoleVo() {
    }

    public RoleVo(int code, int count, String msg, List<Role> data) {
        this.code = code;
        this.count = count;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Role> getData() {
        return data;
    }

    public void setData(List<Role> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RoleVo{" +
                "code=" + code +
                ", count=" + count +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
