package com.mmxb.mgr.entity;

import java.util.Date;

/**
 * Created by Xing on 2015/11/29.
 */
public class CarRent {
    private String position;
    private String outTime;
    private String backTime;
    private String carType;
    private String memo;
    private String type;
    private String otherServer;
    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getOtherServer() {
        return otherServer;
    }

    public void setOtherServer(String otherServer) {
        this.otherServer = otherServer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
