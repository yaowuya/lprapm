package scau.com.lprapm.entity;

public class PositionTracking {
    private Integer positionId;

    private Integer withcarId;

    private Integer repoId;

    private String trackStatus;

    private String provinceid;

    private String cityid;

    private String areaid;

    public PositionTracking(Integer positionId, Integer withcarId, Integer repoId, String trackStatus, String provinceid, String cityid, String areaid) {
        this.positionId = positionId;
        this.withcarId = withcarId;
        this.repoId = repoId;
        this.trackStatus = trackStatus;
        this.provinceid = provinceid;
        this.cityid = cityid;
        this.areaid = areaid;
    }

    public PositionTracking() {
        super();
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getWithcarId() {
        return withcarId;
    }

    public void setWithcarId(Integer withcarId) {
        this.withcarId = withcarId;
    }

    public Integer getRepoId() {
        return repoId;
    }

    public void setRepoId(Integer repoId) {
        this.repoId = repoId;
    }

    public String getTrackStatus() {
        return trackStatus;
    }

    public void setTrackStatus(String trackStatus) {
        this.trackStatus = trackStatus == null ? null : trackStatus.trim();
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid == null ? null : provinceid.trim();
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }
}