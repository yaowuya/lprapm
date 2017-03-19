package scau.com.lprapm.entity;

import java.util.Date;

public class CarPlan {
    private Integer carplanId;

    private String orderIds;

    private String orderNames;

    private Integer carnId;

    private Double allWeight;

    private Double allNumber;

    private Double allVolume;

    private String carplanDept;

    private String carplanPerson;

    private String carplanDesrciption;

    private String provinceid;

    private String cityid;

    private String areaid;

    private Date carplanTime;

    public CarPlan(Integer carplanId, String orderIds, String orderNames, Integer carnId, Double allWeight, Double allNumber, Double allVolume, String carplanDept, String carplanPerson, String carplanDesrciption, String provinceid, String cityid, String areaid, Date carplanTime) {
        this.carplanId = carplanId;
        this.orderIds = orderIds;
        this.orderNames = orderNames;
        this.carnId = carnId;
        this.allWeight = allWeight;
        this.allNumber = allNumber;
        this.allVolume = allVolume;
        this.carplanDept = carplanDept;
        this.carplanPerson = carplanPerson;
        this.carplanDesrciption = carplanDesrciption;
        this.provinceid = provinceid;
        this.cityid = cityid;
        this.areaid = areaid;
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

    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds == null ? null : orderIds.trim();
    }

    public String getOrderNames() {
        return orderNames;
    }

    public void setOrderNames(String orderNames) {
        this.orderNames = orderNames == null ? null : orderNames.trim();
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

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid == null ? null : provinceid.trim();
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public Date getCarplanTime() {
        return carplanTime;
    }

    public void setCarplanTime(Date carplanTime) {
        this.carplanTime = carplanTime;
    }
}