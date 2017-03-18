package scau.com.lprapm.entity;

public class Repertory {
    private Integer repoId;

    private String areaid;

    private String area;

    private String cityid;

    private String city;

    private String provinceid;

    private String province;

    private String repoAddress;

    public Repertory(Integer repoId, String areaid, String area, String cityid, String city, String provinceid, String province, String repoAddress) {
        this.repoId = repoId;
        this.areaid = areaid;
        this.area = area;
        this.cityid = cityid;
        this.city = city;
        this.provinceid = provinceid;
        this.province = province;
        this.repoAddress = repoAddress;
    }

    public Repertory() {
        super();
    }

    public Integer getRepoId() {
        return repoId;
    }

    public void setRepoId(Integer repoId) {
        this.repoId = repoId;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid == null ? null : provinceid.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getRepoAddress() {
        return repoAddress;
    }

    public void setRepoAddress(String repoAddress) {
        this.repoAddress = repoAddress == null ? null : repoAddress.trim();
    }
}