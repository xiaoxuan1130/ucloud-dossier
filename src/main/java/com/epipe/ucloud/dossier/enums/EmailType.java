package com.epipe.ucloud.dossier.enums;

/**
 * 邮件类型
 * @author liyuexuan
 * @date 2019-01-1 9:46
 **/
public enum EmailType {

    //
    QUOTATION("1","报价"),
    ORDER("2", "订单");

    private String code;

    private String name;

    EmailType(String code, String name) {
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
