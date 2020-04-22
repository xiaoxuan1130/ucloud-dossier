package com.epipe.ucloud.dossier.app.service;

import com.alibaba.fastjson.JSONObject;
import com.epipe.ucloud.dossier.app.entity.*;
import com.epipe.ucloud.dossier.common.BusiException;
import com.epipe.ucloud.dossier.utils.FastJsonUtils;
import com.google.common.collect.Maps;
import com.jeeplus.modules.tools.utils.HttpPostTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * App接口对接Service
 */
@Service
public class AppInterfaceService {
    public static final Logger logger = LoggerFactory.getLogger(AppInterfaceService.class);
    @Autowired
    private AppUrlProperty appUrlProperty;



    /**
     * 根据用户账号及明文密码登录
     *
     * @param username
     * @param password
     * @return
     */
    public UserLoginResult loginByUsernameAndPwd(String username, String password) {
        String url = appUrlProperty.getLoginUrl();
        logger.info("根据用户账号及明文密码登录用户中心Input : [{}]", url);
        HashMap<String, String> params = Maps.newHashMap();
        params.put("account", username);
        params.put("password", password);
        String jsonResp = HttpPostTest.post(url, params);
        logger.info("根据用户账号及明文密码登录用户中心Output : [{}]", jsonResp);
        AppInterfaceResponse appInterfaceResponse = FastJsonUtils.toBean(jsonResp, new AppInterfaceResponse<UserLoginResult>().getClass());
        if (appInterfaceResponse.getH().getCode() != 200) {
            throw new BusiException(appInterfaceResponse.getH().getMsg());
        }
        Object body = appInterfaceResponse.getB();
        if (Objects.isNull(body)) {
            throw new BusiException("登陆失败,请重新登陆");
        }
        UserLoginResult userLoginResult = FastJsonUtils.toBean(body.toString(), UserLoginResult.class);
        return userLoginResult;
    }

    /**
     * 根据用户id去APP查询用户信息
     *
     * @param id
     * @return
     */
    public AppUserInfo getUserInfoByIdFromApp(String id) throws BusiException {
        String userUrl = appUrlProperty.getGetUserUrl();
        String url = new StringBuilder(userUrl).append("?userId="+id).toString();
        logger.info("app同步用户信息Input : [{}]", url);
        String jsonResp = HttpPostTest.get(url);
        logger.info("app同步用户信息Output : [{}]", jsonResp);
        AppInterfaceResponse appInterfaceResponse = FastJsonUtils.toBean(jsonResp, new AppInterfaceResponse<AppUserInfo>().getClass());
        if (appInterfaceResponse.getH().getCode() != 200) {
            throw new BusiException(appInterfaceResponse.getH().getMsg());
        }
        AppUserInfo appUserInfo = FastJsonUtils.toBean(appInterfaceResponse.getB().toString(), AppUserInfo.class);
        return appUserInfo;
    }

    /**
     * 根据公司ID去app查询公司信息
     *
     * @return
     */
    public AppCompanyInfo getCompanyInfoByIdFromApp(String id) throws BusiException {
        String companyUrl = appUrlProperty.getGetCompanyUrl();
        String url = new StringBuilder(companyUrl).append("?id="+id).toString();
        logger.info("app同步公司信息Input : [{}]", url);
        String jsonResp = HttpPostTest.get(url);
        logger.info("app同步公司信息Output : [{}]", jsonResp);
        AppInterfaceResponse appInterfaceResponse =FastJsonUtils.toBean(jsonResp, new AppInterfaceResponse<AppCompanyInfo>().getClass());
        if (appInterfaceResponse.getH().getCode() != 200) {
            throw new BusiException(appInterfaceResponse.getH().getMsg());
        }
        AppCompanyInfo appCompanyInfo = FastJsonUtils.toBean(appInterfaceResponse.getB().toString(), AppCompanyInfo.class);
        return appCompanyInfo;
    }

    public AppUserInfo getUserByToken(String token){
        String userUrl=appUrlProperty.getUserByToken();
        String url = new StringBuilder(userUrl).append("?token="+token).toString();
        logger.info("app根据token获取用户信息Input : [{}]", url);
        String jsonResp = HttpPostTest.get(url);
        logger.info("app根据token获取用户信息Output : [{}]", jsonResp);
        AppInterfaceResponse appInterfaceResponse = FastJsonUtils.toBean(jsonResp, new AppInterfaceResponse<AppUserInfo>().getClass());
        if (appInterfaceResponse.getH().getCode() != 200) {
            throw new BusiException(appInterfaceResponse.getH().getMsg());
        }
        AppUserInfo appUserInfo = FastJsonUtils.toBean(appInterfaceResponse.getB().toString(), AppUserInfo.class);
        return appUserInfo;
    }

    public String getAppUuid(){
        String userUrl=appUrlProperty.getGetAppUuid();
        String url = new StringBuilder(userUrl).toString();
        logger.info("app根据token获取用户信息Input : [{}]", url);
        String jsonResp = HttpPostTest.get(url);
        logger.info("app根据token获取用户信息Output : [{}]", jsonResp);
        try {
            AppInterfaceResponse appInterfaceResponse = FastJsonUtils.toBean(jsonResp, new AppInterfaceResponse<AppUserInfo>().getClass());
            if (appInterfaceResponse.getH().getCode() != 200) {
                throw new BusiException(appInterfaceResponse.getH().getMsg());
            }
            String result=appInterfaceResponse.getB().toString();
            return result;
        } catch (BusiException e) {
            throw new BusiException(e.getMessage());
        }
    }

    public String getAppStatus(String code){
        String userUrl=appUrlProperty.getAppCodeStatus();
        String url = new StringBuilder(userUrl).append("?uuid="+code).toString();
        logger.info("app根据token获取用户信息Input : [{}]", url);
        String jsonResp = HttpPostTest.get(url);
        logger.info("app根据token获取用户信息Output : [{}]", jsonResp);
        AppInterfaceResponse appInterfaceResponse = FastJsonUtils.toBean(jsonResp, new AppInterfaceResponse<AppUserInfo>().getClass());
        if (appInterfaceResponse.getH().getCode() != 200) {
            throw new BusiException(appInterfaceResponse.getH().getMsg());
        }
        String result=appInterfaceResponse.getB().toString();
        return result;
    }
}