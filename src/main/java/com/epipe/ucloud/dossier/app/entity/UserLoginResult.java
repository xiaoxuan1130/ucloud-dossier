package com.epipe.ucloud.dossier.app.entity;

import java.io.Serializable;

/**
 * 用户中心登录返回结果实体类
 * @author wwj
 * @version 2019/8/20/0020
 */
public class UserLoginResult implements Serializable {
    private static final long serialVersionUID = -7535108028676276152L;

    private String authToken;
    private String userId;
    private String userName;
    private String profileImg;
    private String mobile;
    private String phone;
    private String email;
    private String name;
    private String sex;
    private Integer regionId;
    private String address;
    private String companyName;
    private String officeName;
    private Integer userNameFlag;
    private String no;
    private String companyId;
    private String imUserName;
    private String imPassword;
    private String shortPhone;
    private String shortMobile;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Integer getUserNameFlag() {
        return userNameFlag;
    }

    public void setUserNameFlag(Integer userNameFlag) {
        this.userNameFlag = userNameFlag;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getImUserName() {
        return imUserName;
    }

    public void setImUserName(String imUserName) {
        this.imUserName = imUserName;
    }

    public String getImPassword() {
        return imPassword;
    }

    public void setImPassword(String imPassword) {
        this.imPassword = imPassword;
    }

    public String getShortPhone() {
        return shortPhone;
    }

    public void setShortPhone(String shortPhone) {
        this.shortPhone = shortPhone;
    }

    public String getShortMobile() {
        return shortMobile;
    }

    public void setShortMobile(String shortMobile) {
        this.shortMobile = shortMobile;
    }
}
