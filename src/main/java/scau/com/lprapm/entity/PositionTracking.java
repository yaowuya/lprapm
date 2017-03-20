package scau.com.lprapm.entity;

import java.util.Date;

public class PositionTracking {
    private Integer positionId;

    private Integer carplanId;

    private Integer orderId;

    private String provinceid;

    private String cityid;

    private String areaid;

    private String trackStatus;

    private Date trackTime;

    public PositionTracking(Integer positionId, Integer carplanId, Integer orderId, String provinceid, String cityid, String areaid, String trackStatus, Date trackTime) {
        this.positionId = positionId;
        this.carplanId = carplanId;
        this.orderId = orderId;
        this.provinceid = provinceid;
        this.cityid = cityid;
        this.areaid = areaid;
        this.trackStatus = trackStatus;
        this.trackTime = trackTime;
    }

    public PositionTracking() {
        super();
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
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

    public String getTrackStatus() {
        return trackStatus;
    }

    public void setTrackStatus(String trackStatus) {
        this.trackStatus = trackStatus == null ? null : trackStatus.trim();
    }

    public Date getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(Date trackTime) {
        this.trackTime = trackTime;
    }
}