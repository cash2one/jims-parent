package com.jims.oauth.service;




import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.oauth.api.RefreshTokenApi;
import com.jims.oauth.dao.RefreshTokenDao;
import com.jims.oauth.entity.RefreshToken;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * refresh_tokenService
 * @author luohk
 * @version 2016-05-30
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class RefreshTokenService extends CrudImplService<RefreshTokenDao, RefreshToken> implements RefreshTokenApi {

    public RefreshToken findUnique(String appKey, String userId){
        return  dao.findUnique(appKey,userId);
    }

    public String save(RefreshToken refreshToken){
        String data = String.valueOf(dao.insert(refreshToken));
        return data;
    }

    public String update(RefreshToken refreshToken){
        String data = String.valueOf(dao.update(refreshToken));
        return data;
    }
	
}