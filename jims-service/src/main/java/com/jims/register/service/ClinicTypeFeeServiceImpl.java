/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicTypeFeeServiceApi;
import com.jims.register.dao.ClinicTypeFeeDao;
import com.jims.register.dao.ClinicTypeSettingDao;
import com.jims.register.entity.ClinicTypeFee;
import com.jims.register.entity.ClinicTypeSetting;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 号类字典Service
 * @author zhaoning
 * @version 2016-05-16
 */
@Service(version = "1.0.0")

public class ClinicTypeFeeServiceImpl extends CrudImplService<ClinicTypeFeeDao, ClinicTypeFee> implements ClinicTypeFeeServiceApi {
     @Autowired
    private ClinicTypeSettingDao clinicTypeSettingDao;
    /**
     * 保存号类
     * @param clinicTypeFeeList
     * @return
     */
    @Override
    public String saveList(List<ClinicTypeFee> clinicTypeFeeList,String type,String clinicTypeId) {
        String num = "";
        ClinicTypeSetting clinicTypeSetting = new ClinicTypeSetting();
        if(type!=null){
            clinicTypeSetting.setClinicTypeName(type);
            if(clinicTypeId!=null && !clinicTypeId.equals("")){
                clinicTypeSetting.setId(clinicTypeId);
            }//保存号类主表
            if (clinicTypeSetting.getIsNewRecord()){
                clinicTypeSetting.preInsert();
                clinicTypeSettingDao.insert(clinicTypeSetting);
            }else{
                clinicTypeSetting.preUpdate();
                clinicTypeSettingDao.update(clinicTypeSetting);
            }
        }
        //保存号类子表
        if(clinicTypeFeeList.size()>0){
            for(int i=0;i<clinicTypeFeeList.size();i++){
                ClinicTypeFee clinicTypeFee = clinicTypeFeeList.get(i);
                clinicTypeFee.setTypeId(clinicTypeSetting.getId());
                num = save(clinicTypeFee);
            }
        }
        return String.valueOf(num);
    }
}