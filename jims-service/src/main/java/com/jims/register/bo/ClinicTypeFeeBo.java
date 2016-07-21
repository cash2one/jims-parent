/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.bo;



import com.jims.common.service.impl.CrudImplService;
import com.jims.common.vo.LoginInfo;
import com.jims.register.dao.ClinicTypeFeeDao;
import com.jims.register.dao.ClinicTypeSettingDao;
import com.jims.register.entity.ClinicTypeFee;
import com.jims.register.entity.ClinicTypeSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 号类费用Bo
 * @author zhangyao
 * @version 2016-06-16
 */
@Service
@Transactional(readOnly = false)
public class ClinicTypeFeeBo extends CrudImplService<ClinicTypeFeeDao, ClinicTypeFee>{
     @Autowired
    private ClinicTypeSettingDao clinicTypeSettingDao;
    /**
     * 保存号类
     * @param clinicTypeFeeList
     * @return
     */
    public String saveList(List<ClinicTypeFee> clinicTypeFeeList,String type,String clinicTypeId,LoginInfo loginInfo) {
        String num = "";
        ClinicTypeSetting clinicTypeSetting = new ClinicTypeSetting();
        if(type!=null){
            clinicTypeSetting.setClinicTypeName(type);
            if(clinicTypeId!=null && !clinicTypeId.equals("")){
                clinicTypeSetting.setId(clinicTypeId);
            }//保存号类主表
            clinicTypeSetting.setOrgId(loginInfo.getOrgId());
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