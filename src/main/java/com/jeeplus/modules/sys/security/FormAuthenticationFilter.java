/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epipe.ucloud.dossier.app.entity.AppUserInfo;
import com.epipe.ucloud.dossier.app.entity.UserLoginResult;
import com.epipe.ucloud.dossier.app.service.AppInterfaceService;
import com.epipe.ucloud.dossier.common.BusiException;
import com.jeeplus.common.config.Global;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.json.PrintJSON;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.core.mapper.JsonMapper;
import com.jeeplus.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.jeeplus.modules.sys.utils.UserUtils;

import java.util.List;
import java.util.Objects;

/**
 * 表单验证（包含验证码）过滤类
 * @author jeeplus
 * @version 2017-5-19
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    @Autowired
    private AppInterfaceService appInterfaceService;

    @Autowired
    private UserMapper userMapper;

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
	public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
	public static final String DEFAULT_MESSAGE_PARAM = "message";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String token=request.getParameter("token");;// 实际用于校验的令牌

		if (password==null){
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
		String captcha = getCaptcha(request);
		boolean mobile = isMobileLogin(request);
		if(StringUtils.isNotBlank(token)){
			//根据token调用app接口获取用户信息
			try {
				AppUserInfo userInfo=appInterfaceService.getUserByToken(token);
				if(userInfo==null){
					return new UsernamePasswordToken();
				}
				// 查询本地数据库是否已同步该用户
				User dbUser= queryUserFromDb(userInfo.getUserId());
				// 存储token至cache供shiro鉴权
				cleanAndSaveToken2Cache(dbUser.getId(),token);
				return new UsernamePasswordToken(userInfo.getUserName(), token.toCharArray(), rememberMe, host,captcha, mobile);
			} catch (Exception e) {
				String msg=e.getMessage();
				return new UsernamePasswordToken(msg);
			}

		}
//		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, mobile);
		// 判断是否是系统内置超级管理员,若是,就不从app获取token,使用本地数据库鉴权(超级管理员只能在web端登陆)
		try {
			if (UserUtils.SUPER_ADMIN_USERNAME.equals(username)) {
				token = password;
			} else {
				// 用用户名密码调用用户中心获取token
				UserLoginResult userLoginResult = appInterfaceService.loginByUsernameAndPwd(username, password);
				// 查询本地数据库是否已同步该用户
				User dbUser=queryUserFromDb(userLoginResult.getUserId());
				// 存储token至cache供shiro鉴权
				cleanAndSaveToken2Cache(dbUser.getId(), userLoginResult.getAuthToken());
				token = userLoginResult.getAuthToken();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new UsernamePasswordToken(e.getMessage());
		}
		return new UsernamePasswordToken(username, token.toCharArray(), rememberMe, host,captcha, mobile);
	}

    /**
     * 查询数据库是否已同步该用户
     *
     * @param userId
     * @return
     */
    private User queryUserFromDb(String userId) {
        User dbUser=new User();
        dbUser.setAppUserId(userId);
        List<User> userList =userMapper.getUserByCondition(dbUser);
        if (userList.size()==0) {
			throw new BusiException("crm还未同步该用户,请稍后重试");
        }
        if (Global.NO.equals(userList.get(0).getLoginFlag())) {
            throw new BusiException("该帐号已被禁用，请联系管理员");
        }
        return userList.get(0);
    }

    /**
     * `
     * 存储用户token至缓存
     *
     * @param userId
     * @param token
     */
    private void cleanAndSaveToken2Cache(String userId, String appToken) {
        UserUtils.removeTokenById(userId);
        UserUtils.saveTokenToCache(userId, appToken);
    }

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public String getMobileLoginParam() {
		return mobileLoginParam;
	}
	
	protected boolean isMobileLogin(ServletRequest request) {
        return WebUtils.isTrue(request, getMobileLoginParam());
    }
	
	public String getMessageParam() {
		return messageParam;
	}
	
	/**
	 * 登录成功之后跳转URL
	 */
	public String getSuccessUrl() {
		return super.getSuccessUrl();
	}
	
	@Override
	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse response) throws Exception {
		Principal p = UserUtils.getPrincipal();
		if (p != null && !p.isMobileLogin()){
			 WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
		}else{
			//super.issueSuccessRedirect(request, response);//手机登录
			AjaxJson j = new AjaxJson();
			j.setSuccess(true);
			j.setMsg("登录成功!");
			j.put("username", p.getLoginName());
			j.put("name", p.getName());
			j.put("mobileLogin", p.isMobileLogin());
			j.put("JSESSIONID", p.getSessionid());
			PrintJSON.write((HttpServletResponse)response, j.getJsonStr());
		}
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request, ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		if (IncorrectCredentialsException.class.getName().equals(className)
				|| UnknownAccountException.class.getName().equals(className)){
			message = "用户或密码错误, 请重试.";
		}
		else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")){
			message = StringUtils.replace(e.getMessage(), "msg:", "");
		}
		else{
			e.printStackTrace(); // 输出到控制台
			message=e.getMessage();
		}
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(getMessageParam(), message);
        return true;
	}
	
}