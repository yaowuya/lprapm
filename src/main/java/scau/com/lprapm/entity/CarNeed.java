package scau.com.lprapm.entity;

public class CarNeed {
    private Integer carnId;

    private String carnType;

    private Integer carnNum;

    private String carnExamState;

    private String carExamPerson;

    private String carnExamDept;

    private String carIds;

    public CarNeed(Integer carnId, String carnType, Integer carnNum, String carnExamState, String carExamPerson, String carnExamDept, String carIds) {
        this.carnId = carnId;
        this.carnType = carnType;
        this.carnNum = carnNum;
        this.carnExamState = carnExamState;
        this.carExamPerson = carExamPerson;
        this.carnExamDept = carnExamDept;
        this.carIds = carIds;
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

    public String getCarExamPerson() {
        return carExamPerson;
    }

    public void setCarExamPerson(String carExamPerson) {
        this.carExamPerson = carExamPerson == null ? null : carExamPerson.trim();
    }

    public String getCarnExamDept() {
        return carnExamDept;
    }

    public void setCarnExamDept(String carnExamDept) {
        this.carnExamDept = carnExamDept == null ? null : carnExamDept.trim();
    }

    public String getCarIds() {
        return carIds;
    }

    public void setCarIds(String carIds) {
        this.carIds = carIds == null ? null : carIds.trim();
    }
}