package scau.com.lprapm.entity;

public class Car {
    private Integer carId;

    private Integer userId;

    private String carLicense;

    private String carType;

    private Double carVolume;

    private Double carWeight;

    private Double preKmPrice;

    private String isFree;

    public Car(Integer carId, Integer userId, String carLicense, String carType, Double carVolume, Double carWeight, Double preKmPrice, String isFree) {
        this.carId = carId;
        this.userId = userId;
        this.carLicense = carLicense;
        this.carType = carType;
        this.carVolume = carVolume;
        this.carWeight = carWeight;
        this.preKmPrice = preKmPrice;
        this.isFree = isFree;
    }

    public Car() {
        super();
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense == null ? null : carLicense.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public Double getCarVolume() {
        return carVolume;
    }

    public void setCarVolume(Double carVolume) {
        this.carVolume = carVolume;
    }

    public Double getCarWeight() {
        return carWeight;
    }

    public void setCarWeight(Double carWeight) {
        this.carWeight = carWeight;
    }

    public Double getPreKmPrice() {
        return preKmPrice;
    }

    public void setPreKmPrice(Double preKmPrice) {
        this.preKmPrice = preKmPrice;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree == null ? null : isFree.trim();
    }
}