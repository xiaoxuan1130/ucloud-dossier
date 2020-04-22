package com.epipe.ucloud.dossier.enums;


public enum TranslateTypeEnum {

    TO_CUSTOMER("1", "客户"),
    TO_CONTACTS("2", "联系人"),
    TO_BIZCHANCE("3", "商机");

    private String code;

    private String name;

    TranslateTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (TranslateTypeEnum type : TranslateTypeEnum.values()) {
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
