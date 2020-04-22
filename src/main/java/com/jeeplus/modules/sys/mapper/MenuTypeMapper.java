/**
 * Copyright &copy; 2015-2020 <a href="https://www.epipe.cn/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.mapper;

import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.sys.entity.MenuType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单类型接口
 * @author 龚涛
 * @version 2018-10-30
 */
@MyBatisMapper
public interface MenuTypeMapper {

	/**
	 * 通过菜单id获取菜单类型对应关系
	 * @param menuId
	 * @return
	 */
	List<MenuType> findListByMenuId(@Param("menuId") String menuId);

	/**
	 * 新增
	 * @param menuType
	 * @return
	 */
	int insert(MenuType menuType);

	/**
	 * 根据菜单id删除对应关系
	 * @param menuId
	 * @return
	 */
	int deleteByMenuId(@Param("menuId") String menuId);
	
}
