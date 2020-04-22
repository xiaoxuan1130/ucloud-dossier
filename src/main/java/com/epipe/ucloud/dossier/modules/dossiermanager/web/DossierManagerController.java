/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.epipe.ucloud.dossier.modules.dossiermanager.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.epipe.ucloud.dossier.modules.dossiermanager.entity.DossierManager;
import com.epipe.ucloud.dossier.modules.dossiermanager.service.DossierManagerService;

/**
 * 档案管理员Controller
 * @author liyuexuan
 * @version 2020-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/dossiermanager/dossierManager")
public class DossierManagerController extends BaseController {

	@Autowired
	private DossierManagerService dossierManagerService;
	
	@ModelAttribute
	public DossierManager get(@RequestParam(required=false) String id) {
		DossierManager entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dossierManagerService.get(id);
		}
		if (entity == null){
			entity = new DossierManager();
		}
		return entity;
	}
	
	/**
	 * 档案管理员列表页面
	 */
	@RequiresPermissions("dossiermanager:dossierManager:list")
	@RequestMapping(value = {"list", ""})
	public String list(DossierManager dossierManager, Model model) {
		model.addAttribute("dossierManager", dossierManager);
		return "modules/dossiermanager/dossierManagerList";
	}
	
		/**
	 * 档案管理员列表数据
	 */
	@ResponseBody
	@RequiresPermissions("dossiermanager:dossierManager:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(DossierManager dossierManager, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DossierManager> page = dossierManagerService.findPage(new Page<DossierManager>(request, response), dossierManager); 
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑档案管理员表单页面
	 */
	@RequiresPermissions(value={"dossiermanager:dossierManager:view","dossiermanager:dossierManager:add","dossiermanager:dossierManager:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(DossierManager dossierManager, Model model) {
		model.addAttribute("dossierManager", dossierManager);
		return "modules/dossiermanager/dossierManagerForm";
	}

	/**
	 * 保存档案管理员
	 */
	@ResponseBody
	@RequiresPermissions(value={"dossiermanager:dossierManager:add","dossiermanager:dossierManager:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(DossierManager dossierManager, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(dossierManager);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		dossierManagerService.save(dossierManager);//保存
		j.setSuccess(true);
		j.setMsg("保存档案管理员成功");
		return j;
	}
	
	/**
	 * 删除档案管理员
	 */
	@ResponseBody
	@RequiresPermissions("dossiermanager:dossierManager:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(DossierManager dossierManager) {
		AjaxJson j = new AjaxJson();
		dossierManagerService.delete(dossierManager);
		j.setMsg("删除档案管理员成功");
		return j;
	}
	
	/**
	 * 批量删除档案管理员
	 */
	@ResponseBody
	@RequiresPermissions("dossiermanager:dossierManager:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			dossierManagerService.delete(dossierManagerService.get(id));
		}
		j.setMsg("删除档案管理员成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("dossiermanager:dossierManager:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(DossierManager dossierManager, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "档案管理员"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<DossierManager> page = dossierManagerService.findPage(new Page<DossierManager>(request, response, -1), dossierManager);
    		new ExportExcel("档案管理员", DossierManager.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出档案管理员记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("dossiermanager:dossierManager:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DossierManager> list = ei.getDataList(DossierManager.class);
			for (DossierManager dossierManager : list){
				try{
					dossierManagerService.save(dossierManager);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条档案管理员记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条档案管理员记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入档案管理员失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入档案管理员数据模板
	 */
	@ResponseBody
	@RequiresPermissions("dossiermanager:dossierManager:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "档案管理员数据导入模板.xlsx";
    		List<DossierManager> list = Lists.newArrayList(); 
    		new ExportExcel("档案管理员数据", DossierManager.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}