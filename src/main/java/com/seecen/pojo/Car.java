package com.seecen.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/13
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class Car {
    private int id;
    private String carNumber;
    private String type;
    private int isNew;
    private int isRepair;

    public Car() {
    }

    public Car(int id, String carNumber, String type, int isNew, int isRepair) {
        this.id = id;
        this.carNumber = carNumber;
        this.type = type;
        this.isNew = isNew;
        this.isRepair = isRepair;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(int isRepair) {
        this.isRepair = isRepair;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carNumber='" + carNumber + '\'' +
                ", type='" + type + '\'' +
                ", isNew=" + isNew +
                ", isRepair=" + isRepair +
                '}';
    }
}
