package scau.com.lprapm.entity;

public class Goods {
    private Integer goodsId;

    private String goodsName;

    private Double goodsNumber;

    private Double goodsVolume;

    private Double goodsPerweight;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Double getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Double goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Double getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(Double goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public Double getGoodsPerweight() {
        return goodsPerweight;
    }

    public void setGoodsPerweight(Double goodsPerweight) {
        this.goodsPerweight = goodsPerweight;
    }
}