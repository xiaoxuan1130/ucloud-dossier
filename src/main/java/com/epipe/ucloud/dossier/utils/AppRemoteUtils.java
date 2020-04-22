package com.epipe.ucloud.dossier.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeeplus.common.config.Global;
import com.jeeplus.modules.sys.entity.Area;
import com.jeeplus.modules.sys.entity.Office;
import com.jeeplus.modules.sys.entity.User;

import java.util.Date;
import java.util.Map;

public class AppRemoteUtils {

    public static User getUserById(Map<String,Object> map){
        String result=HttpUtil.get(Global.getConfig("app.remote.url.user"),map);
        JSONObject object=JSONObject.parseObject(result);
        Object h=object.get("h");
        Object b=object.get("b");
        JSONObject hJson=(JSONObject)h;
        if(hJson.getInteger("code")==200){
            JSONObject bJson =(JSONObject)b ;
            String userId=bJson.getString("userId");
            String userName=bJson.getString("userName");
            String name=bJson.getString("name");
            String profileImg=bJson.getString("profileImg");
            String mobile=bJson.getString("mobile");
            String phone=bJson.getString("email");
            String no=bJson.getString("no");
            String sex=bJson.getString("sex");
            String regionId=bJson.getString("regionId");
            String address=bJson.getString("address");
            String companyId=bJson.getString("companyId");
            String officeId=bJson.getString("officeId");
            String jobStatus=bJson.getString("jobStatus");
            String createBy=bJson.getString("createBy");
            Date createDate=bJson.getDate("createDate");
            String updateBy=bJson.getString("updateBy");
            Date updateDate=bJson.getDate("updateDate");
            String delFlag=bJson.getString("delFlag");
            String password=bJson.getString("password");
            User user=new User();
            user.setId(userId);
            user.setPassword(password);
            user.setName(name);
            user.setLoginName(userName);
            user.setPhoto(profileImg);
            Office office=new Office();
            office.setId(officeId);
            Office company=new Office();
            company.setId(companyId);
            user.setOffice(office);
            user.setCompany(company);
            user.setMobile(mobile);
            user.setPhoto(phone);
            user.setNo(no);
            User createUser=new User();
            createUser.setId(createBy);
            user.setCreateBy(createUser);
            user.setCreateDate(createDate);
            User updateUser=new User();
            updateUser.setId(updateBy);
            user.setUpdateBy(updateUser);
            user.setUpdateDate(updateDate);
            user.setDelFlag(delFlag);
            user.setAppUserId(userId);
            user.setDelFlag("0");
            return user;
        }
        return null;
    }

    public static Office getOfficeById(Map<String,Object> map){
        String result=HttpUtil.get(Global.getConfig("app.remote.url.office"),map);
        JSONObject object=JSONObject.parseObject(result);
        Object h=object.get("h");
        Object b=object.get("b");
        JSONObject hJson=(JSONObject)h;
        if(hJson.getInteger("code")==200){
            JSONObject bJson =(JSONObject)b ;
            String id=bJson.getString("id");
            String parentId=bJson.getString("parentId");
            String parentIds=bJson.getString("parentIds");
            String name=bJson.getString("name");
            int sort=bJson.getInteger("sort");
            String areaId=bJson.getString("areaId");
            String code=bJson.getString("code");
            String type=bJson.getString("type");
            String address=bJson.getString("address");
            String zipCode=bJson.getString("zipCode");
            String master=bJson.getString("master");
            String fax=bJson.getString("fax");
            String email=bJson.getString("email");
            String deputyPersonId=bJson.getString("deputyPerson");
            String companyNo=bJson.getString("companyNo");
            String userable=bJson.getString("userable");
            String createBy=bJson.getString("createBy");
            Date createDate=bJson.getDate("createDate");
            String updateBy=bJson.getString("updateBy");
            Date updateDate=bJson.getDate("updateDate");
            String delFlag=bJson.getString("delFlag");
            Office office=new Office();
            office.setId(id);
            office.setName(name);
            office.setParentIds(parentIds);
            office.setType(type.equals("0")?"1":type);
            office.setAddress(address);
            Area area=new Area();
            area.setId(areaId);
            office.setArea(area);
            office.setUseable(userable);
            Office parent=new Office();
            parent.setId(parentId);
            office.setParent(parent);
            office.setSort(sort);
            User deputyPerson=new User();
            deputyPerson.setId(deputyPersonId);
            office.setDeputyPerson(deputyPerson);
            office.setCode(code);
            office.setMaster(master);
            office.setZipCode(zipCode);
            office.setEmail(email);
            office.setFax(fax);
            User createUser=new User();
            createUser.setId(createBy);
            office.setCreateBy(createUser);
            office.setCreateDate(createDate);
            User updateUser=new User();
            updateUser.setId(updateBy);
            office.setUpdateBy(updateUser);
            office.setUpdateDate(updateDate);
            office.setDelFlag(delFlag);
            office.setAppOfficeId(id);
            office.setDelFlag("0");
            return office;
        }
        return null;
    }
}
