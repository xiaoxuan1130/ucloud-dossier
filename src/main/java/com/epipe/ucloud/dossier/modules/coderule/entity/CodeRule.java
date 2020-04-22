/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.epipe.ucloud.dossier.modules.coderule.entity;


import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 编码规则Entity
 * @author gongtao
 * @version 2019-04-19
 */
public class CodeRule extends DataEntity<CodeRule> {

    private String code;		// 代码
    private String name;		// 规则名称
    private String prefix;		// 前缀
    private String middleDate;		// 中间日期（字典middle_date）
    private Integer lastSerialBit;		// 尾部流水位数
    private Integer lastSerialStartNo;		// 尾部流水号起始值
    private Integer currentSerialNo;         //流水号当前值
    private String example;		// 示例
    private String status;		// 状态，1、启用 0、禁用
    private String detail; 		//规则详情
    private String companyId;
    private String defaultCompanyId; //默认公司,用户数据同步

    public String getDefaultCompanyId() {
        return defaultCompanyId;
    }

    public void setDefaultCompanyId(String defaultCompanyId) {
        this.defaultCompanyId = defaultCompanyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public CodeRule() {
        super();
    }

    public CodeRule(String id){
        super(id);
    }

    @ExcelField(title="代码", align=2, sort=1)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ExcelField(title="规则名称", align=2, sort=2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title="前缀", align=2, sort=3)
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @ExcelField(title="中间日期", dictType="middle_date", align=2, sort=4)
    public String getMiddleDate() {
        return middleDate;
    }

    public void setMiddleDate(String middleDate) {
        this.middleDate = middleDate;
    }

    @ExcelField(title="尾部流水位数", align=2, sort=5)
    public Integer getLastSerialBit() {
        return lastSerialBit;
    }

    public void setLastSerialBit(Integer lastSerialBit) {
        this.lastSerialBit = lastSerialBit;
    }

    @ExcelField(title="尾部流水号起始值", align=2, sort=6)
    public Integer getLastSerialStartNo() {
        return lastSerialStartNo;
    }

    public void setLastSerialStartNo(Integer lastSerialStartNo) {
        this.lastSerialStartNo = lastSerialStartNo;
    }

    public Integer getCurrentSerialNo() {
        return currentSerialNo;
    }

    public void setCurrentSerialNo(Integer currentSerialNo) {
        this.currentSerialNo = currentSerialNo;
    }

    @ExcelField(title="示例", align=2, sort=7)
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @ExcelField(title="状态", align=2, sort=8)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}