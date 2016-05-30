package com.jims.oauth.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.oauth.api.AppApi;
import com.jims.oauth.dao.AppDao;
import com.jims.oauth.entity.App;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/5/30.
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class AppService extends CrudImplService<AppDao, App>  implements AppApi{

    public App selectByPrimaryKey(String appKey){
        return  dao.selectByPrimaryKey(appKey);
    }
}
