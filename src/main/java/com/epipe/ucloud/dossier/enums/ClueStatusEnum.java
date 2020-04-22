package com.epipe.ucloud.dossier.enums;

/**
 * 线索状态枚举
 * @author luos
 */
public enum ClueStatusEnum {

    TO_DEAL("0", "待处理"),
    FOLLOWING("1", "跟进中"),
    TRANSLATED("2", "已转化"),
    INVALID("3", "失效");

    private String code;

    private String name;

    ClueStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (ClueStatusEnum type : ClueStatusEnum.values()) {
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
