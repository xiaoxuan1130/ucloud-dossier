package com.epipe.ucloud.dossier.app.entity;

import java.io.Serializable;

/**
 * App对接状态返回信息
 */
public class AppStatusInfo implements Serializable {
    private static final long serialVersionUID = 7009767474213410137L;

    private Integer code;// 200:成功
    private String msg;
    private String tid;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
