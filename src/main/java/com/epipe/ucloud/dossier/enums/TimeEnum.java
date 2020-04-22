package com.epipe.ucloud.dossier.enums;

/**
 * 时间选择枚举
 * @author liyuexuan
 */
public enum TimeEnum {

    ONE("1", "今年"),
    TWO("2", "去年"),
    THREE("3", "本季度"),
    FOUR("4", "上季度"),
    FIVE("5", "本月"),
    SIX("6", "上月");


    private String code;

    private String name;

    TimeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (TimeEnum type : TimeEnum.values()) {
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
