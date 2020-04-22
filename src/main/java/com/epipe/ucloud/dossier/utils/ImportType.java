package com.epipe.ucloud.dossier.utils;

import org.apache.poi.ss.usermodel.Cell;

public class ImportType {

    private int fieldLength;//字段长度

    private int cell;//字段类型

    private Object value;//字段值

    private String remarks;//描述

    public ImportType(int fieldLength, int cell, Object value,String remarks) {
        this.fieldLength = fieldLength;
        this.cell = cell;
        this.value = value;
        this.remarks=remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(int fieldLength) {
        this.fieldLength = fieldLength;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }
}
