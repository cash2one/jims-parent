/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicIndexServiceApi;
import com.jims.register.dao.ClinicIndexDao;
import com.jims.register.entity.ClinicIndex;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * haobieService
 * @author zhaoning
 * @version 2016-05-17
 */
@Service(version="1.0.0")
@Transactional(readOnly = true)
public class ClinicIndexServiceImpl extends CrudImplService<ClinicIndexDao, ClinicIndex> implements ClinicIndexServiceApi {

    /**
     * 保存号别---多条
     * @param clinicIndexList
     * @return
     */
    @Override
    public String saveList(List<ClinicIndex> clinicIndexList) {
        String num="";
        if(clinicIndexList!=null && clinicIndexList.size()>0){
            for(int i=0;i<clinicIndexList.size();i++){
                ClinicIndex clinicIndex=clinicIndexList.get(i);
               num =save(clinicIndex);
            }
        }
        return String.valueOf(num);
    }
}