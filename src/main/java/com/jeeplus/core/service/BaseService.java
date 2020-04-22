/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.core.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.BaseEntity;
import com.jeeplus.modules.sys.entity.DataRule;
import com.jeeplus.modules.sys.utils.UserUtils;

/**
 * Service基类
 * @author jeeplus
 * @version 2017-05-16
 */
@Transactional(readOnly = true)
public abstract class BaseService {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	
	/**
	 * 数据范围过滤
	 * @param entity 当前过滤的实体类
	 */
	public static void dataRuleFilter(BaseEntity<?> entity) {

		entity.setCurrentUser(UserUtils.getUser());
		List<DataRule> dataRuleList = UserUtils.getDataRuleList();
		
		// 如果是超级管理员，则不过滤数据
		if (dataRuleList.size() == 0) {
			return;
		}
		int count = 0;
		// 数据范围
		StringBuilder sqlString = new StringBuilder();

			for(DataRule dataRule : dataRuleList){
				if(entity.getClass().getSimpleName().equals(dataRule.getClassName())){
					if (count++ >= 1){
						sqlString.append(dataRule.getDataScopeSql().replaceFirst("AND", "OR"));
					}else {
						sqlString.append(dataRule.getDataScopeSql().replaceFirst("AND","AND ("));
					}
				}
				
			}
			if(StringUtils.isNotBlank(sqlString)){
				sqlString.append(" )");
			}

		entity.setDataScope(sqlString.toString());
		
	}

}
