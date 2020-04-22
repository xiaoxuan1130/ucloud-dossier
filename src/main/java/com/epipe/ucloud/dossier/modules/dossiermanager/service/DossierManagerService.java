/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.epipe.ucloud.dossier.modules.dossiermanager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.epipe.ucloud.dossier.modules.dossiermanager.entity.DossierManager;
import com.epipe.ucloud.dossier.modules.dossiermanager.mapper.DossierManagerMapper;

/**
 * 档案管理员Service
 * @author liyuexuan
 * @version 2020-04-22
 */
@Service
@Transactional(readOnly = true)
public class DossierManagerService extends CrudService<DossierManagerMapper, DossierManager> {

	public DossierManager get(String id) {
		return super.get(id);
	}
	
	public List<DossierManager> findList(DossierManager dossierManager) {
		return super.findList(dossierManager);
	}
	
	public Page<DossierManager> findPage(Page<DossierManager> page, DossierManager dossierManager) {
		return super.findPage(page, dossierManager);
	}
	
	@Transactional(readOnly = false)
	public void save(DossierManager dossierManager) {
		super.save(dossierManager);
	}
	
	@Transactional(readOnly = false)
	public void delete(DossierManager dossierManager) {
		super.delete(dossierManager);
	}
	
}