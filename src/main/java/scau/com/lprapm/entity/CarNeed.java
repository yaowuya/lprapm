package scau.com.lprapm.entity;

import java.util.Date;

public class CarNeed {
    private Integer carnId;

    private String carnType;

    private Integer carnNum;

    private String carnExamState;

    private String carnExamPerson;

    private String carnExamDept;

    private Date carnTime;

    private Date carnExamTime;

    public CarNeed(Integer carnId, String carnType, Integer carnNum, String carnExamState, String carnExamPerson, String carnExamDept, Date carnTime, Date carnExamTime) {
        this.carnId = carnId;
        this.carnType = carnType;
        this.carnNum = carnNum;
        this.carnExamState = carnExamState;
        this.carnExamPerson = carnExamPerson;
        this.carnExamDept = carnExamDept;
        this.carnTime = carnTime;
        this.carnExamTime = carnExamTime;
    }

    public CarNeed() {
        super();
    }

    public Integer getCarnId() {
        return carnId;
    }

    public void setCarnId(Integer carnId) {
        this.carnId = carnId;
    }

    public String getCarnType() {
        return carnType;
    }

    public void setCarnType(String carnType) {
        this.carnType = carnType == null ? null : carnType.trim();
    }

    public Integer getCarnNum() {
        return carnNum;
    }

    public void setCarnNum(Integer carnNum) {
        this.carnNum = carnNum;
    }

    public String getCarnExamState() {
        return carnExamState;
    }

    public void setCarnExamState(String carnExamState) {
        this.carnExamState = carnExamState == null ? null : carnExamState.trim();
    }

    public String getCarnExamPerson() {
        return carnExamPerson;
    }

    public void setCarnExamPerson(String carnExamPerson) {
        this.carnExamPerson = carnExamPerson == null ? null : carnExamPerson.trim();
    }

    public String getCarnExamDept() {
        return carnExamDept;
    }

    public void setCarnExamDept(String carnExamDept) {
        this.carnExamDept = carnExamDept == null ? null : carnExamDept.trim();
    }

    public Date getCarnTime() {
        return carnTime;
    }

    public void setCarnTime(Date carnTime) {
        this.carnTime = carnTime;
    }

    public Date getCarnExamTime() {
        return carnExamTime;
    }

    public void setCarnExamTime(Date carnExamTime) {
        this.carnExamTime = carnExamTime;
    }
}