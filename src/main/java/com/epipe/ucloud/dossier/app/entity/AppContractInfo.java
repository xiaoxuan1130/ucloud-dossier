package com.epipe.ucloud.dossier.app.entity;

import java.io.Serializable;
import java.util.Date;

public class AppContractInfo implements Serializable {

    private static final long serialVersionUID = -3496915038525673909L;

    private String contractId;

    // 审批编号
    private String contractNoAuto;

    // 合同编号
    private String contractNoInput;

    private String contractName;

    private String userId;

    private String username;
    private String userName; //兼容3.5.4之前版本

    private String applyCompanyName;

    private String profileImg;
    private String receiverIds;

    public String getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(String receiverIds) {
        this.receiverIds = receiverIds;
    }

    private String receiveCompanyName;
    private String url;
    private String receiveName;

    private String contractObj;

    private String contractDesc;

    private String auditStatus;

    private String auditUserIds;

    private String fileName;

    private String fileSize;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String auditId; //兼容3.5.4之前版本

    private Date applyTime;

    private String auditName;

    private String finalStatus;

    public String getApplyCompany() {
        return applyCompany;
    }

    public void setApplyCompany(String applyCompany) {
        this.applyCompany = applyCompany;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getReceiveCompany() {
        return receiveCompany;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany;
    }



    public String getAuditCompanyIds() {
        return auditCompanyIds;
    }

    public void setAuditCompanyIds(String auditCompanyIds) {
        this.auditCompanyIds = auditCompanyIds;
    }

    public String getReceiverCompanyIds() {
        return receiverCompanyIds;
    }

    public void setReceiverCompanyIds(String receiverCompanyIds) {
        this.receiverCompanyIds = receiverCompanyIds;
    }

    public String getApplyLinkIds() {
        return applyLinkIds;
    }

    public void setApplyLinkIds(String applyLinkIds) {
        this.applyLinkIds = applyLinkIds;
    }

    public String getLinkAuditNum() {
        return linkAuditNum;
    }

    public void setLinkAuditNum(String linkAuditNum) {
        this.linkAuditNum = linkAuditNum;
    }

    private String applyCompany;
    private String applyUserName;
    private String receiveCompany;

    public String getReveiveUserName() {
        return reveiveUserName;
    }

    public void setReveiveUserName(String reveiveUserName) {
        this.reveiveUserName = reveiveUserName;
    }

    private String reveiveUserName;
    private String auditCompanyIds;
    private String receiverCompanyIds;
    private String applyLinkIds;
    private String linkAuditNum;

    /**
     * 是否是草稿
     */
    private String draftFlag;


    /**
     * 是否是 本人申请
     */
    private String myselfApply;

    //  附件
//    private Accessory accessory;
//
//    private List<ContractApplyReceiver> receivers;
//
//    private List<ContractApplyAuditer> auditers;
//
//    private List<ApprovalProcessLinkInfo> links = new ArrayList<>();


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractNoAuto() {
        return contractNoAuto;
    }

    public void setContractNoAuto(String contractNoAuto) {
        this.contractNoAuto = contractNoAuto;
    }

    public String getContractNoInput() {
        return contractNoInput;
    }

    public void setContractNoInput(String contractNoInput) {
        this.contractNoInput = contractNoInput;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApplyCompanyName() {
        return applyCompanyName;
    }

    public void setApplyCompanyName(String applyCompanyName) {
        this.applyCompanyName = applyCompanyName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getReceiveCompanyName() {
        return receiveCompanyName;
    }

    public void setReceiveCompanyName(String receiveCompanyName) {
        this.receiveCompanyName = receiveCompanyName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getContractObj() {
        return contractObj;
    }

    public void setContractObj(String contractObj) {
        this.contractObj = contractObj;
    }

    public String getContractDesc() {
        return contractDesc;
    }

    public void setContractDesc(String contractDesc) {
        this.contractDesc = contractDesc;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }



    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }

    public String getDraftFlag() {
        return draftFlag;
    }

    public void setDraftFlag(String draftFlag) {
        this.draftFlag = draftFlag;
    }

    public String getMyselfApply() {
        return myselfApply;
    }

    public void setMyselfApply(String myselfApply) {
        this.myselfApply = myselfApply;
    }

    public String getAuditUserIds() {
        return auditUserIds;
    }

    public void setAuditUserIds(String auditUserIds) {
        this.auditUserIds = auditUserIds;
    }
}
