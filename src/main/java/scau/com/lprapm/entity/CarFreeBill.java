package scau.com.lprapm.entity;

public class CarFreeBill {
    private Integer carfreeId;

    private Integer carId;

    private Integer carnId;

    public CarFreeBill(Integer carfreeId, Integer carId, Integer carnId) {
        this.carfreeId = carfreeId;
        this.carId = carId;
        this.carnId = carnId;
    }

    public CarFreeBill() {
        super();
    }

    public Integer getCarfreeId() {
        return carfreeId;
    }

    public void setCarfreeId(Integer carfreeId) {
        this.carfreeId = carfreeId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCarnId() {
        return carnId;
    }

    public void setCarnId(Integer carnId) {
        this.carnId = carnId;
    }
}