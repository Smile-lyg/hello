package com.seecen.vo;

import com.seecen.pojo.Car;
import com.seecen.pojo.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/13
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 */
public class CarVo {
    private int code;
    private int count;
    private String msg;
    private List<Car> data;

    public CarVo() {
    }

    public CarVo(int code, int count, String msg, List<Car> data) {
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

    public List<Car> getData() {
        return data;
    }

    public void setData(List<Car> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CarVo{" +
                "code=" + code +
                ", count=" + count +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
