package scau.com.lprapm.entity;

public class roleAction {
    private Integer raId;

    private Integer roleId;

    private Integer actionId;

    public Integer getRaId() {
        return raId;
    }

    public void setRaId(Integer raId) {
        this.raId = raId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    @Override
    public String toString() {
        return "roleAction{" +
                "raId=" + raId +
                ", roleId=" + roleId +
                ", actionId=" + actionId +
                '}';
    }
}