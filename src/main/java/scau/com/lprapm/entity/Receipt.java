package scau.com.lprapm.entity;

import java.util.Date;

public class Receipt {
    private Integer receiptId;

    private String receiptName;

    private String receiptPhone;

    private String receiptProvinceid;

    private String receiptCityid;

    private String receiptAreaid;

    private String receiptAddress;

    private String receiptState;

    private Date receiptTime;

    public Receipt(Integer receiptId, String receiptName, String receiptPhone, String receiptProvinceid, String receiptCityid, String receiptAreaid, String receiptAddress, String receiptState, Date receiptTime) {
        this.receiptId = receiptId;
        this.receiptName = receiptName;
        this.receiptPhone = receiptPhone;
        this.receiptProvinceid = receiptProvinceid;
        this.receiptCityid = receiptCityid;
        this.receiptAreaid = receiptAreaid;
        this.receiptAddress = receiptAddress;
        this.receiptState = receiptState;
        this.receiptTime = receiptTime;
    }

    public Receipt() {
        super();
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName == null ? null : receiptName.trim();
    }

    public String getReceiptPhone() {
        return receiptPhone;
    }

    public void setReceiptPhone(String receiptPhone) {
        this.receiptPhone = receiptPhone == null ? null : receiptPhone.trim();
    }

    public String getReceiptProvinceid() {
        return receiptProvinceid;
    }

    public void setReceiptProvinceid(String receiptProvinceid) {
        this.receiptProvinceid = receiptProvinceid == null ? null : receiptProvinceid.trim();
    }

    public String getReceiptCityid() {
        return receiptCityid;
    }

    public void setReceiptCityid(String receiptCityid) {
        this.receiptCityid = receiptCityid == null ? null : receiptCityid.trim();
    }

    public String getReceiptAreaid() {
        return receiptAreaid;
    }

    public void setReceiptAreaid(String receiptAreaid) {
        this.receiptAreaid = receiptAreaid == null ? null : receiptAreaid.trim();
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress == null ? null : receiptAddress.trim();
    }

    public String getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(String receiptState) {
        this.receiptState = receiptState == null ? null : receiptState.trim();
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }
}