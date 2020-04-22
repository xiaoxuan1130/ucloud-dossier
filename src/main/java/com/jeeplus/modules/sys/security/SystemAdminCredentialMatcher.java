package com.jeeplus.modules.sys.security;

import com.jeeplus.modules.sys.service.SystemService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 系统内置管理员 鉴权比较器
 * @author wwj
 * @version 2019/08/20 22:46
 */
public class SystemAdminCredentialMatcher extends HashedCredentialsMatcher {

    public SystemAdminCredentialMatcher() {
        super(SystemService.HASH_ALGORITHM);
        super.setHashIterations(SystemService.HASH_INTERATIONS);
    }
}
