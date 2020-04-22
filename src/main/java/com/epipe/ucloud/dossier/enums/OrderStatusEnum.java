package com.epipe.ucloud.dossier.enums;

/**
 * 报价单状态
 * @author liyuexuan
 * @date 2019-04-10 9:46
 **/
public enum OrderStatusEnum {

    //
    CREATED("1","已创建"),
    CHECKING("2", "审批中"),
    CHECKED("3", "已审核"),
    PART_DELIVERY("4", "部分发货"),
    ALL_DELIVERY("5", "全部发货");

    private String code;

    private String name;

    OrderStatusEnum(String code, String name) {
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
