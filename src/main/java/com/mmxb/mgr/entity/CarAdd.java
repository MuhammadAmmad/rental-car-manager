package com.mmxb.mgr.entity;

/**
 * Created by Xing on 2015/11/26.
 */
public class CarAdd {
    private Integer id;
    private String carNumber;
    private String carType;
    private String shopName;
    private String isRental;
    private String carStatus;
    private String price;
    private String memo;
    private String car_type;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getIsRental() {
        return isRental;
    }

    public void setIsRental(String isRental) {
        this.isRental = isRental;
    }
}
