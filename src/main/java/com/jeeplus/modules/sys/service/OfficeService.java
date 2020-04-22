/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.service;

import java.util.Date;
import java.util.List;

import com.epipe.ucloud.dossier.app.entity.AppCompanyInfo;
import com.epipe.ucloud.dossier.common.BusiException;
import com.jeeplus.common.utils.IdGen;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.sys.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.service.TreeService;
import com.jeeplus.modules.sys.entity.Area;
import com.jeeplus.modules.sys.entity.Office;
import com.jeeplus.modules.sys.mapper.AreaMapper;
import com.jeeplus.modules.sys.mapper.OfficeMapper;
import com.jeeplus.modules.sys.utils.UserUtils;

/**
 * 机构Service
 * @author jeeplus
 * @version 2017-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeMapper, Office> {

	@Autowired
	private OfficeMapper officeMapper;
	
	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		office.setParentIds(office.getParentIds()+"%");
		return officeMapper.findByParentIdsLike(office);
	}

	@Transactional(readOnly = true)
	public List<Office> findList(String parentId){
		return UserUtils.getOfficeList(parentId);
	}
	
	@Transactional(readOnly = true)
	public Office getByCode(String code){
		return officeMapper.getByCode(code);
	}
	
	public List<Office> getChildren(String parentId){
		return officeMapper.getChildren(parentId);
	}

	public List<Office> getCurrentAndChildren(String id){
		return officeMapper.getCurrentAndChildren(id);
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	public void syncOffice(AppCompanyInfo companyInfo){
		Office office=new Office();
		office.setAppOfficeId(companyInfo.getId());
		//查询公司是否存在
		List<Office> officeList=officeMapper.findByCondition(office);
		if(officeList.size()==0){//新增
			office.setId(IdGen.uuid());
		}else{
			office.setId(officeList.get(0).getId());
		}
		office.setParent(new Office(companyInfo.getParentId()));
		office.setArea(new Area(companyInfo.getAreaId()));
		office.setType(companyInfo.getType().equals("0")?"1":companyInfo.getType());
		office.setDeputyPerson(new User(companyInfo.getDeputyPerson()));
		BeanUtils.copyProperties(companyInfo,office);
		// 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
		if(office.getParent().getId().equals("0")){
			Office parent=new Office();
			parent.setId("0");
			office.setParent(parent);
		}else{
			Office parent=new Office();
			parent.setAppOfficeId(office.getParentId());
			List<Office> parentList=officeMapper.findByCondition(parent);
			if(parentList.size()==0){
				throw new BusiException("获取父级机构错误");
			}
			office.setParent(parentList.get(0));
			String parentIds=office.getParentIds();
			String[] str=parentIds.split(",");
			String localParentIds="";
			for(String s:str){
				if(s.equals("0")){
					localParentIds=localParentIds+s+",";
				}else{
					Office o=new Office();
					o.setAppOfficeId(s);
					List<Office> oList=officeMapper.findByCondition(o);
					if(oList.size()==0){
						throw new BusiException("获取父级机构错误");
					}
					String oid=oList.get(0).getId();
					localParentIds=localParentIds+oid+",";
				}
			}
			office.setParentIds(localParentIds);
		}
		Date currentDate=new Date();
		User createBy=new User();
		createBy.setId("1");
		if(officeList.size()==0){
			office.setCreateBy(createBy);
			office.setUpdateBy(createBy);
			office.setCreateDate(currentDate);
			office.setUpdateDate(currentDate);
			officeMapper.insert(office);
		}else{
			office.setUpdateBy(createBy);
			office.setUpdateDate(currentDate);
			officeMapper.update(office);
		}
		//UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	
	
}
