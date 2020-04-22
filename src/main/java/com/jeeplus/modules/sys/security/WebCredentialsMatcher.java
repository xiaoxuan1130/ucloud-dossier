package com.jeeplus.modules.sys.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Web登陆，shiro鉴权认证比较器
 */
public class WebCredentialsMatcher extends SimpleCredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // token : app Token交换接口提交的usernamePasswordToken
        // info : 下一步要返回的鉴权用户信息
        Object tokenCredentials = token.getCredentials();
        Object infoCredentials = info.getCredentials();
        return super.equals(tokenCredentials, infoCredentials);
    }
}
