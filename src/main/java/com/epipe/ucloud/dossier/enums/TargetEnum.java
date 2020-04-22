package com.epipe.ucloud.dossier.enums;

/**
 * 业绩目标枚举
 * @author liyuexuan
 */
public enum TargetEnum {

    ONE("1", "合同金额"),
    TWO("2", "回款金额"),
    THREE("3", "赢取商机数"),
    FOUR("4", "线索数"),
    FIVE("5", "客户数"),
    SIX("6", "拜访数"),
    SEVEN("7", "商机赢单金额");


    private String code;

    private String name;

    TargetEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (TargetEnum type : TargetEnum.values()) {
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
