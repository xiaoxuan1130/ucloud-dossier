/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.mapper;

import com.jeeplus.core.persistence.TreeMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.sys.entity.Office;

import java.util.List;

/**
 * 机构MAPPER接口
 * @author jeeplus
 * @version 2017-05-16
 */
@MyBatisMapper
public interface OfficeMapper extends TreeMapper<Office> {
	
	public Office getByCode(String code);

	public List<Office> getCurrentAndChildren(String id);

	/**
	 * 获取直接挂在公司下面的用户的部门列表
	 * @param office
	 * @return
	 */
	public List<Office> findAdminsList(Office office);

	public List<Office> findByCondition(Office office);
}
