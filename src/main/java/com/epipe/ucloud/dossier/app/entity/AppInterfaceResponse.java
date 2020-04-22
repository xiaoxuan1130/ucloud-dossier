package com.epipe.ucloud.dossier.app.entity;

import java.io.Serializable;

/**
 * App对接返回实体接收类
 * @param <T>
 */
public class AppInterfaceResponse<T> implements Serializable {

    private static final long serialVersionUID = -5802905477748952135L;
    private AppStatusInfo h;// App返回状态信息
    private T b;// 返回实体类信息


    public AppStatusInfo getH() {
        return h;
    }

    public void setH(AppStatusInfo h) {
        this.h = h;
    }

    public T getB() {
        return b;
    }

    public void setB(T b) {
        this.b = b;
    }
}