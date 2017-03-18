package scau.com.lprapm.entity;

public class PositionTracking {
    private Integer positionId;

    private Integer withcarId;

    private Integer repoId;

    private String trackStatus;

    public PositionTracking(Integer positionId, Integer withcarId, Integer repoId, String trackStatus) {
        this.positionId = positionId;
        this.withcarId = withcarId;
        this.repoId = repoId;
        this.trackStatus = trackStatus;
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
}