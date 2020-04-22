package com.epipe.ucloud.dossier.enums;

/**
 * @author gongtao
 * @date 2019-04-19 14:57
 **/
public enum Enabled {
    //启用状态
    yes("1","启用"),
    no("0", "禁用");

    private String code;

    private String name;

    Enabled(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String code() {
        return code;
    }

    public String getName() {
        return name;
    }
}
