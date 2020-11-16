package com.seecen.pojo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/14
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
public class Menu {
    private int id;
    private String title = "";
    private String icon = "";
    private String href = "";
    private int spread;
    private int parentId;

    private List<Menu> children;

    public Menu() {
    }

    public Menu(int id, String title, String icon, String href, int spread, int parentId) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getSpread() {
        return spread;
    }

    public void setSpread(int spread) {
        this.spread = spread;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", spread=" + spread +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
