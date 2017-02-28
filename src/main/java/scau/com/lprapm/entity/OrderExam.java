package scau.com.lprapm.entity;

public class OrderExam {
    private Integer oeId;

    private String oeState;

    private String oePerson;

    private String oeDept;

    private String oeReason;

    public Integer getOeId() {
        return oeId;
    }

    public void setOeId(Integer oeId) {
        this.oeId = oeId;
    }

    public String getOeState() {
        return oeState;
    }

    public void setOeState(String oeState) {
        this.oeState = oeState == null ? null : oeState.trim();
    }

    public String getOePerson() {
        return oePerson;
    }

    public void setOePerson(String oePerson) {
        this.oePerson = oePerson == null ? null : oePerson.trim();
    }

    public String getOeDept() {
        return oeDept;
    }

    public void setOeDept(String oeDept) {
        this.oeDept = oeDept == null ? null : oeDept.trim();
    }

    public String getOeReason() {
        return oeReason;
    }

    public void setOeReason(String oeReason) {
        this.oeReason = oeReason == null ? null : oeReason.trim();
    }
}