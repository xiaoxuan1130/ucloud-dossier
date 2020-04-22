package com.epipe.ucloud.dossier.enums;

/**
 * 客户类型枚举
 * @author luos
 */
public enum EnterTypeEnum {

    MANUAL("0", "手工退回"),
    AUTO("2", "自动退回"),
    CREATE("3", "手工创建");

    private String code;

    private String name;

    EnterTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (EnterTypeEnum type : EnterTypeEnum.values()) {
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
