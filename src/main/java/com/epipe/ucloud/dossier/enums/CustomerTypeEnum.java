package com.epipe.ucloud.dossier.enums;

/**
 * 客户类型枚举
 * @author luos
 */
public enum CustomerTypeEnum {

    PRIVATE("0", "私有客户"),
    PUBLIC("1", "公海客户");

    private String code;

    private String name;

    CustomerTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (CustomerTypeEnum type : CustomerTypeEnum.values()) {
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
