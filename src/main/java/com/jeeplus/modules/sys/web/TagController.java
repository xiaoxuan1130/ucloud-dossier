/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.web;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeeplus.common.utils.FileUtils;
import com.jeeplus.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeeplus.core.web.BaseController;

/**
 * 标签Controller
 * @author jeeplus
 * @version 2016-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/tag")
public class TagController extends BaseController {
	
	/**
	 * 树结构选择标签（treeselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "treeselect")
	public String treeselect(HttpServletRequest request, Model model) {
		Map<String,String> paramMap = getRequestMap(request);
		model.addAttribute("url", paramMap.remove("url")); 	// 树结构数据URL
		model.addAttribute("extId",  paramMap.remove("extId")); // 排除的编号ID
		model.addAttribute("checked",  paramMap.remove("checked")); // 是否可复选
		model.addAttribute("selectIds",  paramMap.remove("selectIds")); // 指定默认选中的ID
		model.addAttribute("isAll",  paramMap.remove("isAll")); 	// 是否读取全部数据，不进行权限过滤
		model.addAttribute("allowSearch",  paramMap.remove("allowSearch"));	// 是否显示查找
		StringBuilder sb = new StringBuilder();
		if (paramMap.size() > 0){
			for (Map.Entry<String, String> entry : paramMap.entrySet()) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		model.addAttribute("paramStr", sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "");
		return "modules/common/tagTreeselect";
	}
	
	/**
	 * 图标选择标签（iconselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "iconselect")
	public String iconselect(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		return "modules/common/tagIconselect";
	}
	
//	/**
//	 * gridselect选择框
//	 */
//	@RequiresPermissions("user")
//	@RequestMapping(value = "gridselect")
//	public String gridselect(String url, String fieldLabels, String fieldKeys, String searchLabels, String searchKeys, String isMultiSelected, HttpServletRequest request, HttpServletResponse response, Model model) {
//		try {
//			fieldLabels = URLDecoder.decode(fieldLabels, "UTF-8");
//			fieldKeys = URLDecoder.decode(fieldKeys, "UTF-8");
//			searchLabels = URLDecoder.decode(searchLabels, "UTF-8");
//			searchKeys = URLDecoder.decode(searchKeys, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		model.addAttribute("isMultiSelected", isMultiSelected);
//		model.addAttribute("fieldLabels", fieldLabels.split("\\|"));
//		model.addAttribute("fieldKeys", fieldKeys.split("\\|"));
//		model.addAttribute("url", url);
//		model.addAttribute("searchLabels", searchLabels.split("\\|"));
//		model.addAttribute("searchKeys", searchKeys.split("\\|"));
//		return "modules/common/gridselect";
//	}

	/**
	 * gridselect选择框
	 * update by gongtao 2018-11-19
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "gridselect")
	public String gridselect(HttpServletRequest request ,Model model) {
		Map<String,String> paramMap = getRequestMap(request);
		String fieldLabels = paramMap.remove("fieldLabels");
		String fieldKeys = paramMap.remove("fieldKeys");
		String searchLabels = paramMap.remove("searchLabels");
		String searchKeys = paramMap.remove("searchKeys");
		String isMultiSelected = paramMap.remove("isMultiSelected");
		String url = paramMap.remove("url");
		model.addAttribute("isMultiSelected", isMultiSelected);
		model.addAttribute("fieldLabels", fieldLabels.split("\\|"));
		model.addAttribute("fieldKeys", fieldKeys.split("\\|"));
		model.addAttribute("url", url);
		model.addAttribute("searchLabels", searchLabels.split("\\|"));
		model.addAttribute("searchKeys", searchKeys.split("\\|"));
		StringBuilder sb = new StringBuilder();
		if (paramMap.size() > 0){
			for (Map.Entry<String, String> entry : paramMap.entrySet()) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("paramStr", sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "");
		return "modules/common/gridselect";
	}


	/**
	 * 文件上传
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "fileUpload")
	public String fileUpload(HttpServletRequest request, Model model) {
		String fileValues = request.getParameter("fileValues");
		String readonly = request.getParameter("readonly");
		String uploadPath = request.getParameter("uploadPath");
		String type = request.getParameter("type");
		String fileNumLimit = request.getParameter("fileNumLimit");
		String fileSizeLimit = request.getParameter("fileSizeLimit");
		String allowedExtensions = request.getParameter("allowedExtensions");
		String target = request.getParameter("target");
		String[] fieldValuesArra = fileValues.split("\\|");
		StringBuilder fileIds = new StringBuilder();
		StringJoiner joiner = new StringJoiner("|");
		for(String value: fieldValuesArra){
			if (StringUtils.isNotEmpty(value) && Objects.equals(target, "qiniu")){
				try {
					String fileName = value.substring(value.lastIndexOf("/") + 1);
					value = value + "?attname="+ URLEncoder.encode(fileName, "utf-8");
					joiner.add(value);
				} catch (UnsupportedEncodingException e) {
					//ignore
				}
			}
			fileIds.append(FileUtils.getFileDir(value)).append("|");
		}

		if(StringUtils.isNotBlank(fileIds.toString())){
			fileIds = new StringBuilder(fileIds.substring(0, fileIds.length() - 1));
		}
		StringBuilder mimeTypes = new StringBuilder();
		if("all".equals(type)){
			allowedExtensions = "";
			mimeTypes = new StringBuilder("All types(*.*)");
		}else if("file".equals(type)){
			allowedExtensions = "7z,aiff,asf,avi,bmp,csv,doc,docx,fla,flv,gif,gz,gzip,jpeg,jpg,mid,mov,mp3,mp4,mpc,mpeg,mpg,ods,odt,pdf,png,ppt,pptx,pxd,qt,ram,rar,rm,rmi,rmvb,rtf,sdc,sitd,swf,sxc,sxw,tar,tgz,tif,tiff,txt,vsd,wav,wma,wmv,xls,xlsx,zip";
			mimeTypes = new StringBuilder(".7z,.aiff,.asf,.avi,.bmp,.csv,.doc,.docx,.fla,.flv,.gif,.gz,.gzip,.jpeg,.jpg,.mid,.mov,.mp3,.mp4,.mpc,.mpeg,.mpg,.ods,.odt,.pdf,.png,.ppt,.pptx,.pxd,.qt,.ram,.rar,.rm,.rmi,.rmvb,.rtf,.sdc,.sitd,.swf,.sxc,.sxw,.tar,.tgz,.tif,.tiff,.txt,.vsd,.wav,.wma,.wmv,.xls,.xlsx,.zip");
		}else if("image".equals(type)){
			allowedExtensions = "gif,jpg,jpeg,bmp,png";
			mimeTypes = new StringBuilder("image/*");
		}else if("audio".equals(type)){
			allowedExtensions = "CD,OGG,MP3,ASF,WMA,WAV,MP3PRO,RM,REAL,APE,MODULE,MIDI,VQF";
			mimeTypes = new StringBuilder("audio/*");
		}else if("video".equals(type)){
			allowedExtensions = "AVI,WMV,RM,RMVB,MPEG1,MPEG2,MPEG4(MP4),3GP,ASF,SWF,VOB,DAT,MOV,M4V,FLV,F4V,MKV,MTS,TS";
			mimeTypes = new StringBuilder("video/*");
		}else if("office".equals(type)){
			allowedExtensions = "txt,xls,xlsx,xlsm,xltx,xltm,xlsb,xlam,doc,docx,docm,dotx,dotm,ppt,pptx,pptm,ppsx,ppsm,potx,potm,ppam";
			mimeTypes = new StringBuilder(".txt,.xls,.xlsx,.xlsm,.xltx,.xltm,.xlsb,.xlam,.doc,.docx,.docm,.dotx,.dotm,.ppt,.pptx,.pptm,.ppsx,.ppsm,.potx,.potm,.ppam");
		}else {
			String[] ex=allowedExtensions.split(",");
			for(String e:ex){
				mimeTypes.append(".").append(e).append(",");
			}
			if(StringUtils.isNotBlank(mimeTypes.toString())){
				mimeTypes = new StringBuilder(mimeTypes.substring(0, mimeTypes.length() - 1));
			}
		}
		model.addAttribute("fileIds", fileIds.toString());
		model.addAttribute("fileValues", joiner.toString());
		model.addAttribute("uploadPath", uploadPath);
		model.addAttribute("type", type);
		model.addAttribute("fileNumLimit", fileNumLimit);
		model.addAttribute("fileSizeLimit", fileSizeLimit);
		model.addAttribute("allowedExtensions", allowedExtensions);
		model.addAttribute("mimeTypes", mimeTypes.toString());
		model.addAttribute("readonly", "true".equals(readonly));
		model.addAttribute("target", target);
		return "modules/common/fileUpload";
	}

	/**
	 * 文件上传
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "importExcel")
	public String importExcel() {
		return "modules/common/importExcel";
	}

	private Map<String,String> getRequestMap(HttpServletRequest request){
		Enumeration<String> enumeration = request.getParameterNames();
		Map<String,String> paramMap = new HashMap<>();
		while (enumeration.hasMoreElements()){
			String key = enumeration.nextElement();
			String value = request.getParameter(key);
			try {
				paramMap.put(key, URLDecoder.decode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return paramMap;
	}
}
