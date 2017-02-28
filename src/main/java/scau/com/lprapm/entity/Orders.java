package scau.com.lprapm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Orders {
    private Integer orderId;

    private Integer goodsId;

    private Integer oeId;

    private Integer receiptId;

    private Integer userId;

    private Integer purId;

    private Integer logId;

    private String userName;

    private String isPur;

    private String isAskPur;

    private String isAskLog;

    private String isSure;

    private Date createTime;

    private Date endTime;

    private String orderAddress;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOeId() {
        return oeId;
    }

    public void setOeId(Integer oeId) {
        this.oeId = oeId;
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPurId() {
        return purId;
    }

    public void setPurId(Integer purId) {
        this.purId = purId;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getIsPur() {
        return isPur;
    }

    public void setIsPur(String isPur) {
        this.isPur = isPur == null ? null : isPur.trim();
    }

    public String getIsAskPur() {
        return isAskPur;
    }

    public void setIsAskPur(String isAskPur) {
        this.isAskPur = isAskPur == null ? null : isAskPur.trim();
    }

    public String getIsAskLog() {
        return isAskLog;
    }

    public void setIsAskLog(String isAskLog) {
        this.isAskLog = isAskLog == null ? null : isAskLog.trim();
    }

    public String getIsSure() {
        return isSure;
    }

    public void setIsSure(String isSure) {
        this.isSure = isSure == null ? null : isSure.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress == null ? null : orderAddress.trim();
    }
}