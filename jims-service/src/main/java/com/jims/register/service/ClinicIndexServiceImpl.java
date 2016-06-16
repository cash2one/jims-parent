/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicIndexServiceApi;
import com.jims.register.bo.ClinicIndexBo;
import com.jims.register.dao.ClinicIndexDao;
import com.jims.register.entity.ClinicIndex;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 好别Service
 * @author zhaoning
 * @version 2016-05-17
 */
@Service(version="1.0.0")
public class ClinicIndexServiceImpl  implements ClinicIndexServiceApi {

    @Autowired
    private ClinicIndexBo clinicIndexBo;


    @Override
    public List<ClinicIndex> findList(ClinicIndex clinicIndex) {
        return clinicIndexBo.findList(clinicIndex);
    }

    @Override
    public String saveList(List<ClinicIndex> clinicIndexList) {
        return clinicIndexBo.saveList(clinicIndexList);
    }

    @Override
    public String delete(String id) {
        return clinicIndexBo.delete(id);
    }
}