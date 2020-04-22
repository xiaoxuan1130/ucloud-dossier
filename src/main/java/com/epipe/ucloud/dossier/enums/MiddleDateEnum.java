package com.epipe.ucloud.dossier.enums;



import com.epipe.ucloud.dossier.common.NoSuchDataException;

import java.util.Objects;

/**
 * @author gongtao
 * @date 2019-04-22 10:17
 **/
public enum MiddleDateEnum {

    //两位年份
    TWO_YEAR("01"),
    //两位年份+两位月份
    TWO_YEAR_TWO_MONTH("02"),
    //两位年份+两位月份+两位日份
    TWO_YEAR_TWO_MONTH_TWO_DAY("03"),
    //四位年份
    FOUR_YEAR("04"),
    //四位年份+两位月份
    FOUR_YEAR_TWO_MONTH("05"),
    //四位年份+两位月份+两位日份
    FOUR_YEAR_TWO_MONTH_TWO_DAY("06");

    private String code;

    MiddleDateEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MiddleDateEnum of(String code){
        MiddleDateEnum[] dateEnums = values();
        for (MiddleDateEnum dateEnum : dateEnums) {
            if (Objects.equals(code, dateEnum.code)){
                return dateEnum;
            }
        }
        throw new NoSuchDataException();
    }
}
