package scau.com.lprapm.entity;

import java.util.Date;

public class User {
    private Integer userId;

    private String userTrueName;

    private String userPassword;

    private String userEmail;

    private String userPhone;

    private Date userBirthday;

    private String userSex;

    private String userCompany;

    private String userDept;

    private String userDeptPhone;

    private String userDeptDesc;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName == null ? null : userTrueName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany == null ? null : userCompany.trim();
    }

    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept == null ? null : userDept.trim();
    }

    public String getUserDeptPhone() {
        return userDeptPhone;
    }

    public void setUserDeptPhone(String userDeptPhone) {
        this.userDeptPhone = userDeptPhone == null ? null : userDeptPhone.trim();
    }

    public String getUserDeptDesc() {
        return userDeptDesc;
    }

    public void setUserDeptDesc(String userDeptDesc) {
        this.userDeptDesc = userDeptDesc == null ? null : userDeptDesc.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userTrueName='" + userTrueName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userBirthday=" + userBirthday +
                ", userSex='" + userSex + '\'' +
                ", userCompany='" + userCompany + '\'' +
                ", userDept='" + userDept + '\'' +
                ", userDeptPhone='" + userDeptPhone + '\'' +
                ", userDeptDesc='" + userDeptDesc + '\'' +
                '}';
    }
}