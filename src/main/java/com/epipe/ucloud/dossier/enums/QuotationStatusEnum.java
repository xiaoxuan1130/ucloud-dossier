package com.epipe.ucloud.dossier.enums;

/**
 * 报价单状态
 * @author liyuexuan
 * @date 2019-04-10 9:46
 **/
public enum QuotationStatusEnum {

    //
    CREATED("1","已创建"),
    SENDED("2", "已发送"),
    REFUSED("3", "已拒绝"),
    RECEIVED("4", "已接受"),
    CONFIRM("5", "已销售确认");

    private String code;

    private String name;

    QuotationStatusEnum(String code, String name) {
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
