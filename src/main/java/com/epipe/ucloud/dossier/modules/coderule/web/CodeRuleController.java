/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.epipe.ucloud.dossier.modules.coderule.web;

import com.epipe.ucloud.dossier.modules.coderule.entity.CodeRule;
import com.epipe.ucloud.dossier.modules.coderule.service.CodeRuleService;
import com.google.common.collect.Lists;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.web.BaseController;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

/**
 * 编码规则Controller
 * @author gongtao
 * @version 2019-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/coderule/codeRule")
public class CodeRuleController extends BaseController {

	@Autowired
	private CodeRuleService codeRuleService;
	
	@ModelAttribute
	public CodeRule get(@RequestParam(required=false) String id) {
		CodeRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = codeRuleService.get(id);
		}
		if (entity == null){
			entity = new CodeRule();
		}
		return entity;
	}
	
	/**
	 * 编码规则列表页面
	 */
	@RequiresPermissions("coderule:codeRule:list")
	@RequestMapping(value = {"list", ""})
	public String list(CodeRule codeRule, Model model) {
		model.addAttribute("codeRule", codeRule);
		return "modules/coderule/codeRuleList";
	}
	
		/**
	 * 编码规则列表数据
	 */
	@ResponseBody
	@RequiresPermissions("coderule:codeRule:list")
	@RequestMapping(value = "data")
	public Map<String, Object> data(CodeRule codeRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CodeRule> page = codeRuleService.findPage(new Page<>(request, response), codeRule);
		return getBootstrapData(page);
	}

	/**
	 * 查看，增加，编辑编码规则表单页面
	 */
	@RequiresPermissions(value={"coderule:codeRule:view","coderule:codeRule:add","coderule:codeRule:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(CodeRule codeRule, Model model) {
		model.addAttribute("codeRule", codeRule);
		return "modules/coderule/codeRuleForm";
	}

	/**
	 * 保存编码规则
	 */
	@ResponseBody
	@RequiresPermissions(value={"coderule:codeRule:add","coderule:codeRule:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public AjaxJson save(CodeRule codeRule, Model model) throws Exception{
		AjaxJson j = new AjaxJson();
		/**
		 * 后台hibernate-validation插件校验
		 */
		String errMsg = beanValidator(codeRule);
		if (StringUtils.isNotBlank(errMsg)){
			j.setSuccess(false);
			j.setMsg(errMsg);
			return j;
		}
		//新增或编辑表单保存
		codeRuleService.save(codeRule);//保存
		j.setSuccess(true);
		j.setMsg("保存编码规则成功");
		return j;
	}
	
	/**
	 * 删除编码规则
	 */
	@ResponseBody
	@RequiresPermissions("coderule:codeRule:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(CodeRule codeRule) {
		AjaxJson j = new AjaxJson();
		codeRuleService.delete(codeRule);
		j.setMsg("删除编码规则成功");
		return j;
	}
	
	/**
	 * 批量删除编码规则
	 */
	@ResponseBody
	@RequiresPermissions("coderule:codeRule:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		AjaxJson j = new AjaxJson();
		String idArray[] =ids.split(",");
		for(String id : idArray){
			codeRuleService.delete(codeRuleService.get(id));
		}
		j.setMsg("删除编码规则成功");
		return j;
	}
	
	/**
	 * 导出excel文件
	 */
	@ResponseBody
	@RequiresPermissions("coderule:codeRule:export")
    @RequestMapping(value = "export")
    public AjaxJson exportFile(CodeRule codeRule, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "编码规则"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CodeRule> page = codeRuleService.findPage(new Page<CodeRule>(request, response, -1), codeRule);
    		new ExportExcel("编码规则", CodeRule.class).setDataList(page.getList()).write(response, fileName).dispose();
    		j.setSuccess(true);
    		j.setMsg("导出成功！");
    		return j;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出编码规则记录失败！失败信息："+e.getMessage());
		}
			return j;
    }

	/**
	 * 导入Excel数据

	 */
	@ResponseBody
	@RequiresPermissions("coderule:codeRule:import")
    @RequestMapping(value = "import")
   	public AjaxJson importFile(@RequestParam("file")MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CodeRule> list = ei.getDataList(CodeRule.class);
			for (CodeRule codeRule : list){
				try{
					codeRuleService.save(codeRule);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条编码规则记录。");
			}
			j.setMsg( "已成功导入 "+successNum+" 条编码规则记录"+failureMsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导入编码规则失败！失败信息："+e.getMessage());
		}
		return j;
    }
	
	/**
	 * 下载导入编码规则数据模板
	 */
	@ResponseBody
	@RequiresPermissions("coderule:codeRule:import")
    @RequestMapping(value = "import/template")
     public AjaxJson importFileTemplate(HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
            String fileName = "编码规则数据导入模板.xlsx";
    		List<CodeRule> list = Lists.newArrayList(); 
    		new ExportExcel("编码规则数据", CodeRule.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
		}
		return j;
    }

}