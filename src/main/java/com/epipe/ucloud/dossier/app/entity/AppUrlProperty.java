package com.epipe.ucloud.dossier.app.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/properties/epipe.properties", encoding = "UTF-8")
public class AppUrlProperty {
    @Value("${app.remote.url.login}")
    private String loginUrl;
    @Value("${app.remote.url.user}")
    private String getUserUrl;
    @Value("${app.remote.url.office}")
    private String getCompanyUrl;
    @Value("${app.remote.url.query_by_token}")
    private String getUserByToken;
    @Value("${app.remote.url.get_uuid}")
    private String getAppUuid;
    @Value("${app.remote.url.get_status}")
    private String getAppCodeStatus;
    @Value("${app.remote.url}")
    private String getAppUrl;
    @Value("${app.remote.url.get_process}")
    private String getProcess;
    @Value("${app.remote.url.save_contract}")
    private String getSave;
    @Value("${app.remote.url.contract_info}")
    private String getContractInfo;
    public String getGetContractInfo() {
        return getContractInfo;
    }




    public String getGetUserUrl() {
        return getUserUrl;
    }

    public String getGetProcess() {
        return getProcess;
    }

    public String getGetSave() {
        return getSave;
    }

    public String getGetCompanyUrl() {
        return getCompanyUrl;
    }


    public String getLoginUrl() {
        return loginUrl;
    }

    public String getUserByToken(){
        return getUserByToken;
    }

    public String getGetAppUuid(){
        return getAppUuid;
    }

    public String getAppCodeStatus(){
        return getAppCodeStatus;
    }

    public String getAppUrl(){
        return getAppUrl;
    }
}
