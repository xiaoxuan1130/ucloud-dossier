/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.epipe.ucloud.dossier.modules.coderule.mapper;

import com.epipe.ucloud.dossier.modules.coderule.entity.CodeRule;
import com.jeeplus.core.persistence.BaseMapper;
import com.jeeplus.core.persistence.annotation.MyBatisMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 编码规则MAPPER接口
 * @author gongtao
 * @version 2019-04-19
 */
@MyBatisMapper
public interface CodeRuleMapper extends BaseMapper<CodeRule> {

    /**
     * 通过 code 查询
     * @param code
     * @return
     */
    CodeRule findByCode(@Param("code") String code, @Param("companyId") String companyId);

    /**
     * 数据同步
     */
    void sync(CodeRule codeRule);
	
}