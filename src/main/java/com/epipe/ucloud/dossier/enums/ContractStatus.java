package com.epipe.ucloud.dossier.enums;

/**
 * 合同状态
 * @author gongtao
 * @date 2019-04-10 9:46
 **/
public enum  ContractStatus {

    //
    NEW("10","新建"),
    PENDING("20", "审核中"),
    FINISHED("30","已完成"),
    OVER("40","已结束");

    private String code;

    private String name;

    ContractStatus(String code, String name) {
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
