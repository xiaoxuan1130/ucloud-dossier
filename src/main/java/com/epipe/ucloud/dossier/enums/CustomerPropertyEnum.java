package com.epipe.ucloud.dossier.enums;

/**
 * 客户类型枚举
 * @author luos
 */
public enum CustomerPropertyEnum {

    COMPANY("1", "公司"),
    PERSONAL("2", "个人");

    private String code;

    private String name;

    CustomerPropertyEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (CustomerPropertyEnum type : CustomerPropertyEnum.values()) {
            if (type.getCode().equals(code)) {
                return type.name;
            }
        }
        return "";
    }

    public String getCode() {
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
