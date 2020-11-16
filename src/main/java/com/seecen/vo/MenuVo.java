package com.seecen.vo;

import com.seecen.pojo.Menu;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/14
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public class MenuVo {
    private int code;
    private int count;
    private String msg;
    private List<Menu> data;

    private List<Menu> navs;

    public MenuVo() {
    }

    public MenuVo(int code, int count, String msg, List<Menu> data) {
        this.code = code;
        this.count = count;
        this.msg = msg;
        this.data = data;
    }

    public MenuVo(List<Menu> navs) {
        this.navs = navs;
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

    public List<Menu> getData() {
        return data;
    }

    public void setData(List<Menu> data) {
        this.data = data;
    }

    public List<Menu> getNavs() {
        return navs;
    }

    public void setNavs(List<Menu> navs) {
        this.navs = navs;
    }

    @Override
    public String toString() {
        return "MenuVo{" +
                "code=" + code +
                ", count=" + count +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", navs=" + navs +
                '}';
    }
}
