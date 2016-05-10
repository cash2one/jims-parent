/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.AdministrationDictApi;
import com.jims.sys.dao.AdministrationDictDao;
import com.jims.sys.entity.AdministrationDict;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 字典Service
 * @author zhangyao
 * @version 2014-05-18
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class AdministrationDictImpl extends CrudImplService<AdministrationDictDao, AdministrationDict> implements AdministrationDictApi {

    /**
     * 根据试用返回获取用药途径 ，传入是门诊则获取门诊+全部
     * 传入的是住院 = 住院+ 全部
     * 传入的全部 = 门诊 + 住院+全部
     * @param inpOrOutpFlag 全部（综合）、门诊、住院
     * @return
     * @author  yangruidong
     */
    @Override
    public List<AdministrationDict> listAdministrationByInpOrOutpFlag(String inpOrOutpFlag) {
        return null;
    }
}
