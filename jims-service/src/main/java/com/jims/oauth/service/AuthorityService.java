package com.jims.oauth.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.oauth.api.AuthorityApi;
import com.jims.oauth.dao.AuthorityDao;
import com.jims.oauth.entity.Authority;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * authorityService
 * @author luohk
 * @version 2016-05-30
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class AuthorityService extends CrudImplService<AuthorityDao, Authority> implements AuthorityApi{

    public Authority findUnique(String appKey, String userId){
         return dao.findUnique(appKey,userId);
    }

    public String save(Authority authority){
        String data = String.valueOf(dao.insert(authority));
        return data;
    }

    public Authority findAppKey(String appKey){
        return dao.findAppKey(appKey);
    }
	
}