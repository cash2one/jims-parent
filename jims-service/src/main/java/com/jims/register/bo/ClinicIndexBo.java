/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.bo;


import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.register.api.ClinicIndexServiceApi;
import com.jims.register.dao.ClinicIndexDao;
import com.jims.register.entity.ClinicIndex;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 好别Bo
 * @author zhangyao
 * @version 2016-06-16
 */
@Service
@Transactional(readOnly = false)
public class ClinicIndexBo extends CrudImplService<ClinicIndexDao, ClinicIndex> {

    /**
     * 保存号别---多条
     * @param clinicIndexList
     * @return
     */
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
    public BaseDto getCost(String id) {
        return dao.getCost(id);
    }

}