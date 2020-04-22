package com.jeeplus.modules.sys.enums;

import java.util.Objects;

/**
 * 文件存储目标方向
 * @author gongtao
 * @date 2019-03-18 10:36
 **/
public enum FileDestination {
    //本地
    local,
    //七牛
    qiniu;


    public static boolean contains(String name){
        FileDestination[] destinations = values();
        boolean exists = false;
        for (FileDestination destination : destinations) {
            if (Objects.equals(destination.name(), name)){
                exists = true;
                break;
            }
        }
        return exists;
    }


}
