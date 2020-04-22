package com.epipe.ucloud.dossier.enums;

/**
 * 邮件状态
 * @author liyuexuan
 * @date 2019-01-1 9:46
 **/
public enum EmailStatus {

    //
    CONTENT("1","正文"),
    ATTACH("2", "附件");

    private String code;

    private String name;

    EmailStatus(String code, String name) {
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
