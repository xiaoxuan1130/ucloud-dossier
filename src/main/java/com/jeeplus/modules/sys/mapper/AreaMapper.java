/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.mapper;

import com.jeeplus.core.persistence.TreeMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.sys.entity.Area;

import java.util.List;

/**
 * 区域MAPPER接口
 * @author jeeplus
 * @version 2017-05-16
 */
@MyBatisMapper
public interface AreaMapper extends TreeMapper<Area> {

    /**
     * 查询所有省级地区列表
     * @return
     */
    List<Area> selectProvinceList();

    /**
     * 根据省份获取市
     * @param provinceId
     * @return
     */
    List<Area> selectCityByProvince(String provinceId);

    /**
     * 根据条件查询地区
     * @param a
     * @return
     */
    List<Area> findByConditions(Area a);
}
