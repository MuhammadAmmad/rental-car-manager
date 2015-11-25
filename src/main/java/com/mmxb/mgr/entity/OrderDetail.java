package com.mmxb.mgr.entity;

import java.util.Date;

/**
 * Created by Xing on 2015/11/25.
 */
public class OrderDetail {
    private Integer id;
    private String orderId;
    private String name;
    private String phoneName;
    private String carNumber;
    private String shopName;
    private String orderSatus;
    private Date outTime;
    private Date backTime;
    private String otherServer;
    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOrderSatus() {
        return orderSatus;
    }

    public void setOrderSatus(String orderSatus) {
        this.orderSatus = orderSatus;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public String getOtherServer() {
        return otherServer;
    }

    public void setOtherServer(String otherServer) {
        this.otherServer = otherServer;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
