package scau.com.lprapm.entity;

public class PurPrice {
    private Integer purId;

    private String purDept;

    private String purPerson;

    private Double purPrice;

    private String purState;

    public Integer getPurId() {
        return purId;
    }

    public void setPurId(Integer purId) {
        this.purId = purId;
    }

    public String getPurDept() {
        return purDept;
    }

    public void setPurDept(String purDept) {
        this.purDept = purDept == null ? null : purDept.trim();
    }

    public String getPurPerson() {
        return purPerson;
    }

    public void setPurPerson(String purPerson) {
        this.purPerson = purPerson == null ? null : purPerson.trim();
    }

    public Double getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(Double purPrice) {
        this.purPrice = purPrice;
    }

    public String getPurState() {
        return purState;
    }

    public void setPurState(String purState) {
        this.purState = purState == null ? null : purState.trim();
    }
}