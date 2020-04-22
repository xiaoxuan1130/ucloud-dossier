package com.epipe.ucloud.dossier.utils;

import com.jeeplus.common.utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ImportUtils {

    private static String verifyField(ImportType importType) {
        String errorStr = "";
        int fieldLength = importType.getFieldLength();
        int type = importType.getCell();
        Object value = importType.getValue();
        String remarks = importType.getRemarks();
        if (type == Cell.CELL_TYPE_NUMERIC) {//数值类型
            try {
                if (value == null) {
                    errorStr = remarks + "应为数值类型";
                } else {
                    double v = Double.parseDouble(value.toString());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                errorStr = remarks + "应为数值类型";
            }
        } else if (type == Cell.CELL_TYPE_STRING) {
            if (StringUtils.isBlank(value.toString())) {
                errorStr = remarks + "为空";
            } else if (value.toString().length() > fieldLength) {
                errorStr = remarks + "超过指定长度" + fieldLength;
            }

        } else if (type == Cell.CELL_TYPE_FORMULA) {
        } else if (type == Cell.CELL_TYPE_BOOLEAN) {
        } else if (type == Cell.CELL_TYPE_ERROR) {
        }
        return errorStr;
    }


    public static List<String> getError(ImportType importType, List<String> errorList){
        String error= verifyField(importType);
        if(StringUtils.isNotBlank(error)){
            errorList.add(error);
        }
        return errorList;
    }


    public static List<String> getErrorList(Map<Integer, List<String>> rowMap){
        List<String> errList = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> m : rowMap.entrySet()) {
            Integer key = m.getKey();
            StringBuilder value = new StringBuilder();
            List<String> errorList = m.getValue();
            for (String s : errorList) {
                value.append(s).append(",");
            }
            String msg = "第" + key + "行错误信息如下:" + value;
            errList.add(msg);
        }
        return errList;
    }






}
