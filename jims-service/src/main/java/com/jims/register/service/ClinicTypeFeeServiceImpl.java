/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicTypeFeeServiceApi;
import com.jims.register.bo.ClinicTypeFeeBo;
import com.jims.register.dao.ClinicTypeFeeDao;
import com.jims.register.dao.ClinicTypeSettingDao;
import com.jims.register.entity.ClinicTypeFee;
import com.jims.register.entity.ClinicTypeSetting;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 号类费用Service
 * @author zhaoning
 * @version 2016-05-16
 */
@Service(version = "1.0.0")
public class ClinicTypeFeeServiceImpl implements ClinicTypeFeeServiceApi {
     @Autowired
    private ClinicTypeFeeBo clinicTypeFeeBo;

    @Override
    public List<ClinicTypeFee> findList(ClinicTypeFee clinicTypeFee) {
        return clinicTypeFeeBo.findList(clinicTypeFee);
    }

    /**
     * 保存号类
     * @param clinicTypeFeeList
     * @return
     */
    @Override
    public String saveList(List<ClinicTypeFee> clinicTypeFeeList,String type,String clinicTypeId) {
       return clinicTypeFeeBo.saveList(clinicTypeFeeList,type,clinicTypeId);
    }

    @Override
    public String delete(String id) {
        return clinicTypeFeeBo.delete(id);
    }
}