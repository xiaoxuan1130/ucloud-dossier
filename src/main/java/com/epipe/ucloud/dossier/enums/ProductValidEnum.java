package com.epipe.ucloud.dossier.enums;

/**
 * 产品有效状态
 */
public enum ProductValidEnum {

    VALID("1", "有效"),
    INVALID("0", "无效");

    private String code;

    private String name;

    ProductValidEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (ProductValidEnum type : ProductValidEnum.values()) {
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
