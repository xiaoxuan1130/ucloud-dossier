/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.service.TreeService;
import com.jeeplus.modules.sys.entity.Area;
import com.jeeplus.modules.sys.mapper.AreaMapper;
import com.jeeplus.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author jeeplus
 * @version 2017-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaMapper, Area> {

	@Autowired
	private AreaMapper mapper;
	
	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeL2Cache(UserUtils.AREA_REGION);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeL2Cache(UserUtils.AREA_REGION);
	}

	public List<Area> findProvinceList() {
		return mapper.selectProvinceList();
	}

	public List<Area> getCityByProvince(String provinceId) {
		return mapper.selectCityByProvince(provinceId);
	}


}
