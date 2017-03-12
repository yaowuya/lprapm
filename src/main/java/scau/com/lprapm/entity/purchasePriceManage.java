package scau.com.lprapm.entity;

import java.util.Date;

public class purchasePriceManage {
    private Integer ppmId;

    private String goodsName;

    private Double goodsPrice;

    private Date ppmTime;

    public purchasePriceManage(Integer ppmId, String goodsName, Double goodsPrice, Date ppmTime) {
        this.ppmId = ppmId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.ppmTime = ppmTime;
    }

    public purchasePriceManage() {
        super();
    }

    public Integer getPpmId() {
        return ppmId;
    }

    public void setPpmId(Integer ppmId) {
        this.ppmId = ppmId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Date getPpmTime() {
        return ppmTime;
    }

    public void setPpmTime(Date ppmTime) {
        this.ppmTime = ppmTime;
    }
}