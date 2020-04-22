/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.epipe.ucloud.dossier.modules.dossiermanager.entity;

import com.jeeplus.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import com.jeeplus.modules.sys.entity.User;

import com.jeeplus.core.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 档案管理员Entity
 * @author liyuexuan
 * @version 2020-04-22
 */
public class DossierManager extends DataEntity<DossierManager> {
	
	private static final long serialVersionUID = 1L;
	private String companyId;		// 公司id
	private Office belongCompany;		// 所属公司
	private User manager;		// 管理员id
	
	public DossierManager() {
		super();
	}

	public DossierManager(String id){
		super(id);
	}

	@ExcelField(title="公司id", align=2, sort=1)
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@NotNull(message="所属公司不能为空")
	@ExcelField(title="所属公司", fieldType=Office.class, value="", align=2, sort=2)
	public Office getBelongCompany() {
		return belongCompany;
	}

	public void setBelongCompany(Office belongCompany) {
		this.belongCompany = belongCompany;
	}
	
	@NotNull(message="管理员id不能为空")
	@ExcelField(title="管理员id", fieldType=User.class, value="", align=2, sort=3)
	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
}