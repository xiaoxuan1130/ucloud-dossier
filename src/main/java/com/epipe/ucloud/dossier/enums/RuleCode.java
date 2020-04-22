package com.epipe.ucloud.dossier.enums;

/**
 * @author liyuexuan
 * @date 2019-06-08 14:33
 **/
public enum RuleCode {

    //客户编码
    customer_no("customer_no"),
    //产品编码
    product_no("product_no"),
    //标价单编码
    quotation_no("quotation_no"),
    //销售订单编码
    order_no("order_no"),
    //合同编码
    contract_no("contract_no"),
    //回款编号
    receive_no("receive_no");

    private String code;

    RuleCode(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

}
