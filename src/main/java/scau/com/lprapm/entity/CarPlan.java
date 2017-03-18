package scau.com.lprapm.entity;

import java.util.Date;

public class CarPlan {
    private Integer carplanId;

    private Integer orderId;

    private Integer carnId;

    private Double allWeight;

    private Double allNumber;

    private Double allVolume;

    private String carplanDept;

    private String carplanPerson;

    private String carplanDesrciption;

    private Date carplanTime;

    public CarPlan(Integer carplanId, Integer orderId, Integer carnId, Double allWeight, Double allNumber, Double allVolume, String carplanDept, String carplanPerson, String carplanDesrciption, Date carplanTime) {
        this.carplanId = carplanId;
        this.orderId = orderId;
        this.carnId = carnId;
        this.allWeight = allWeight;
        this.allNumber = allNumber;
        this.allVolume = allVolume;
        this.carplanDept = carplanDept;
        this.carplanPerson = carplanPerson;
        this.carplanDesrciption = carplanDesrciption;
        this.carplanTime = carplanTime;
    }

    public CarPlan() {
        super();
    }

    public Integer getCarplanId() {
        return carplanId;
    }

    public void setCarplanId(Integer carplanId) {
        this.carplanId = carplanId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCarnId() {
        return carnId;
    }

    public void setCarnId(Integer carnId) {
        this.carnId = carnId;
    }

    public Double getAllWeight() {
        return allWeight;
    }

    public void setAllWeight(Double allWeight) {
        this.allWeight = allWeight;
    }

    public Double getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(Double allNumber) {
        this.allNumber = allNumber;
    }

    public Double getAllVolume() {
        return allVolume;
    }

    public void setAllVolume(Double allVolume) {
        this.allVolume = allVolume;
    }

    public String getCarplanDept() {
        return carplanDept;
    }

    public void setCarplanDept(String carplanDept) {
        this.carplanDept = carplanDept == null ? null : carplanDept.trim();
    }

    public String getCarplanPerson() {
        return carplanPerson;
    }

    public void setCarplanPerson(String carplanPerson) {
        this.carplanPerson = carplanPerson == null ? null : carplanPerson.trim();
    }

    public String getCarplanDesrciption() {
        return carplanDesrciption;
    }

    public void setCarplanDesrciption(String carplanDesrciption) {
        this.carplanDesrciption = carplanDesrciption == null ? null : carplanDesrciption.trim();
    }

    public Date getCarplanTime() {
        return carplanTime;
    }

    public void setCarplanTime(Date carplanTime) {
        this.carplanTime = carplanTime;
    }
}