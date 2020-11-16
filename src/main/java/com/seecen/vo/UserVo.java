package com.seecen.vo;

import com.seecen.pojo.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/9
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 */
public class UserVo {
    private int code;
    private int count;
    private String msg;
    private List<User> data;

    public UserVo() {
    }

    public UserVo(int code, int count, String msg, List<User> data) {
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

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "code=" + code +
                ", count=" + count +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
