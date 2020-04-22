package com.jeeplus.common.utils;

import java.util.Random;

/**
 * @author gongtao
 * @version 2018-11-06 11:49
 **/
public class CommonUtils {


    /**
     * 获取随机数（当前时间+bit位数字）
     * @param bit
     * @return
     */
    public static String getTimestampSeq(int bit){
        long millis = System.currentTimeMillis();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1;i <= bit;i++){
            sb.append(9);
        }
        int number = random.nextInt(Integer.parseInt(sb.toString()));
        return millis + String.format("%0" + bit + "d",number);
    }

}

