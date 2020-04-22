/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.epipe.ucloud.dossier.modules.coderule.service;

import com.epipe.ucloud.dossier.common.BusiException;
import com.epipe.ucloud.dossier.common.service.CommonService;
import com.epipe.ucloud.dossier.enums.Enabled;
import com.epipe.ucloud.dossier.modules.coderule.entity.CodeRule;
import com.epipe.ucloud.dossier.modules.coderule.mapper.CodeRuleMapper;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.jeeplus.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 编码规则Service
 * @author gongtao
 * @version 2019-04-19
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CodeRuleService extends CrudService<CodeRuleMapper, CodeRule> {
	@Resource
	private CodeRuleMapper codeRuleMapper;

	public CodeRule get(String id) {
		return super.get(id);
	}
	
	public List<CodeRule> findList(CodeRule codeRule) {
		return super.findList(codeRule);
	}
	
	public Page<CodeRule> findPage(Page<CodeRule> page, CodeRule codeRule) {
		String companyId=UserUtils.getUser().getCompany().getId();
		codeRule.setCompanyId(companyId);
		Page<CodeRule> pageData = super.findPage(page, codeRule);
		List<CodeRule> list = pageData.getList();
		for (CodeRule rule : list) {
			CommonService.setRuleDetail(rule);
		}
		return pageData;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(CodeRule codeRule) {
		if (codeRule.getLastSerialStartNo() < 1){
			throw new BusiException("流水号起始值不能小于0！");
		}
		String companyId=UserUtils.getUser().getCompany().getId();
		CodeRule originalBean = getByCode(codeRule.getCode(),companyId);
		if (originalBean != null && Objects.equals(codeRule.getId(), originalBean.getId())){
			throw new BusiException("编码已存在，不能重复！");
		}
		if (codeRule.getIsNewRecord()){
			codeRule.setCurrentSerialNo(codeRule.getLastSerialStartNo() - 1);
		}
		codeRule.setStatus(Enabled.yes.code());
		codeRule.setCompanyId(companyId);
		super.save(codeRule);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(CodeRule codeRule) {
		super.deleteByLogic(codeRule);
	}

	/**
	 * 获取下一连续的序列号
	 * @param code 编码
	 * @return int 下一序列号
	 */
	@Transactional(rollbackFor = Exception.class)
	public synchronized int getAndSetNextSerialNo(String code){
		String companyId=UserUtils.getUser().getCompany().getId();
		CodeRule codeRule = getByCode(code,companyId);
		int currentSerialNo = codeRule.getCurrentSerialNo();
		codeRule.setCurrentSerialNo(currentSerialNo + 1);
		codeRule.preUpdate();
		codeRuleMapper.update(codeRule);
		return codeRule.getCurrentSerialNo();
	}

	/**
	 * 获取下一序列号并重置序列号
	 * @param code 编码
	 * @return int 下一序列号
	 */
	@Transactional(rollbackFor = Exception.class)
	public synchronized int getAndResetSerialNo(String code){
		String companyId=UserUtils.getUser().getCompany().getId();
		CodeRule codeRule = getByCode(code,companyId);
		codeRule.setCurrentSerialNo(codeRule.getLastSerialStartNo() - 1);
		codeRule.preUpdate();
		codeRuleMapper.update(codeRule);
		return codeRule.getCurrentSerialNo();
	}

	/**
	 * 通过编码获取编码规则
	 * @param code
	 * @return
	 */
	public CodeRule getByCode(String code,String companyId){
		return codeRuleMapper.findByCode(code,companyId);
	}

	/**
	 * 数据同步
	 */
	public void sync(String companyId){
	    //判断默认公司是否存在编码规则
        CodeRule codeRule=new CodeRule();
        codeRule.setCompanyId(companyId);
        List<CodeRule> defalutList=findList(codeRule);
        if(defalutList.size()==0){
            throw new BusiException("请维护默认公司编码规则");
        }
		//判断当前公司是否已存在编码规则
		String currentCompanyId=UserUtils.getUser().getCompany().getId();
		codeRule.setCompanyId(currentCompanyId);
		List<CodeRule> list=findList(codeRule);
		if(list.size()>0){
			throw new BusiException("该公司已维护编码规则");
		}
		codeRule.setDefaultCompanyId(companyId);
		codeRule.setIsNewRecord(true);
		codeRule.preInsert();
		codeRuleMapper.sync(codeRule);
	}
	
}