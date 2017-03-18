package scau.com.lprapm.entity;

public class CarType {
    private Integer ctId;

    private String ctType;

    private Double ctVolume;

    private Double ctWeight;

    private Double kmPrice;

    public CarType(Integer ctId, String ctType, Double ctVolume, Double ctWeight, Double kmPrice) {
        this.ctId = ctId;
        this.ctType = ctType;
        this.ctVolume = ctVolume;
        this.ctWeight = ctWeight;
        this.kmPrice = kmPrice;
    }

    public CarType() {
        super();
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    public String getCtType() {
        return ctType;
    }

    public void setCtType(String ctType) {
        this.ctType = ctType == null ? null : ctType.trim();
    }

    public Double getCtVolume() {
        return ctVolume;
    }

    public void setCtVolume(Double ctVolume) {
        this.ctVolume = ctVolume;
    }

    public Double getCtWeight() {
        return ctWeight;
    }

    public void setCtWeight(Double ctWeight) {
        this.ctWeight = ctWeight;
    }

    public Double getKmPrice() {
        return kmPrice;
    }

    public void setKmPrice(Double kmPrice) {
        this.kmPrice = kmPrice;
    }
}