/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.BloodApplyServiceApi;
import com.jims.clinic.dao.BloodApplylDao;
import com.jims.clinic.dao.BloodCapacityDao;
import com.jims.clinic.entity.BloodApply;
import com.jims.clinic.entity.BloodCapacity;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.awt.font.FontRenderContext;

/**
 * 用血申请Service
 * @author qlx
 * @version 2016-04-28
 */
@Service(version ="1.0.0")
@Transactional(readOnly = true)
public class BloodApplyServiceImpl extends CrudImplService<BloodApplylDao,BloodApply> implements BloodApplyServiceApi {
    @Autowired
    private BloodCapacityDao bloodCapacityDao;
    /**
     * 保存用血申请和用血量申请
     * @author qlx
     * @version 2016-04-28
     */
    public String saveBloodApply(BloodApply bloodApply){
        String strState=super.save(bloodApply);
        bloodCapacityDao.delBloodCapacity(bloodApply.getId());
        if (bloodApply!=null){
            for (BloodCapacity bloodCapacity:bloodApply.getBloodCapacityList()){
                bloodCapacity.preInsert();
                bloodCapacityDao.insert(bloodCapacity);
            }
        }
        return strState;
    }
}