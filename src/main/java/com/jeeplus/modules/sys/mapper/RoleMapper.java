/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.mapper;

import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import com.jeeplus.modules.sys.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色MAPPER接口
 * @author jeeplus
 * @version 2016-12-05
 */
@MyBatisMapper
public interface RoleMapper extends BaseMapper<Role> {

	public Role getByName(Role role);
	
	public Role getByEnname(Role role);

	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);
	
	/**
	 * 维护角色与数据权限关系
	 * @param role
	 * @return
	 */
	public int deleteRoleDataRule(Role role);

	public int insertRoleDataRule(Role role);


	/**
	 * 通过中文名前缀判断是否角色存在
	 * @param name
	 * @param officeId
	 * @return
	 */
	public int existRoleByName(@Param("name") String name, @Param("officeId") String officeId);

	/**
	 * 通过英文名前缀判断是否角色存在
	 * @param enname
	 * @param officeId
	 * @return
	 */
	public int existRoleByEnname(@Param("enname") String enname, @Param("officeId") String officeId);

	/**
	 * 判断角色是否拥有权限
	 * @param roleIdList
	 * @return
	 */
	int getPermCount(@Param("roleIdList") List<String> roleIdList);

}
