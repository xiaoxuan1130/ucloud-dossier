package com.epipe.ucloud.dossier.enums;

/**
 * 合同状态
 * @author liyuexuan
 * @date 2019-04-10 9:46
 **/
public enum DiscountTypeEnum {

    //
    DIRECT_REDUCTION("1","直接降价"),
    PERCENTAGE_REDUCTION("2", "百分比降价");

    private String code;

    private String name;

    DiscountTypeEnum(String code, String name) {
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
