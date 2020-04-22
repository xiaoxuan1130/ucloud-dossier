package com.epipe.ucloud.dossier.enums;

/**
 * 操作类型enum
 * @author luos
 */
public enum OperateTypeEnum {

    //
    IMPORT("import", "导入"),
    SAVE("save", "保存"),
    ADD("add", "新增"),
    UPDATE("update", "修改"),
    DELETE("delete", "删除"),
    BACK_TO_CUSTOMER_PUBLIC("backToCustomerPublic", "退回公海"),
    CUSTOMER_RECEIVE("receive", "领取客户"),
    CUSTOMER_DISTRIBUTE("distribute", "分配客户"),
    CHANGE_SALES("changeSales", "更换负责人"),
    BACK_TO_CLUE_POOL("backToCluePool", "退回线索池"),
    MARK_LOSE("markLose", "标记失去"),
    CLUE_TRANSLATE("translateClueInfo", "转化"),
    POOL_CLUE_RECEIVE("poolClueReceive", "领取线索"),
    POOL_CLUE_DISTRIBUTE("poolClueDistribute", "分配线索"),
    ADD_FLOW("saveRecord", "添加跟进记录"),
    TRANSLATE("translateClueInfo", "转化"),
    UPDATE_CHANCE_STATE("changePeriod","修改商机状态");


    private String code;

    private String name;

    OperateTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (OperateTypeEnum type : OperateTypeEnum.values()) {
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
