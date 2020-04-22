package com.epipe.ucloud.dossier.enums;

/**
 * 合同审批状态
 * @author gongtao
 * @date 2019-04-10 9:46
 **/
public enum ContractAuditStatus {
    //
    UN_AUDIT("00","新建"),
    PASS("1", "通过"),
    REJECT("2","拒绝"),
    TRANSFER("3","转交"),
    BACK("4","退回");

    private String code;

    private String name;

    ContractAuditStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String code() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
