package scau.com.lprapm.entity;

public class LogPrice {
    private Integer logId;

    private String logDept;

    private String logPerson;

    private Double logPrice;

    private String logState;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogDept() {
        return logDept;
    }

    public void setLogDept(String logDept) {
        this.logDept = logDept == null ? null : logDept.trim();
    }

    public String getLogPerson() {
        return logPerson;
    }

    public void setLogPerson(String logPerson) {
        this.logPerson = logPerson == null ? null : logPerson.trim();
    }

    public Double getLogPrice() {
        return logPrice;
    }

    public void setLogPrice(Double logPrice) {
        this.logPrice = logPrice;
    }

    public String getLogState() {
        return logState;
    }

    public void setLogState(String logState) {
        this.logState = logState == null ? null : logState.trim();
    }
}