/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.web.impl.BaseDto;
import com.jims.register.api.ClinicIndexServiceApi;
import com.jims.register.bo.ClinicIndexBo;
import com.jims.register.bo.ClinicScheduleBo;
import com.jims.register.entity.ClinicIndex;
import com.jims.register.entity.ClinicSchedule;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 号别Service
 * @author zhaoning
 * @version 2016-05-17
 */
@Service(version="1.0.0")
public class ClinicIndexServiceImpl  implements ClinicIndexServiceApi {

    @Autowired
    private ClinicIndexBo clinicIndexBo;
    @Autowired
    private ClinicScheduleBo clinicScheduleBo;


    @Override
    public Page<ClinicIndex> findPage(Page<ClinicIndex> page,ClinicIndex clinicIndex) {
        return clinicIndexBo.findPage(page,clinicIndex);
    }

    @Override
    public List<ClinicIndex> findList(ClinicIndex clinicIndex) {
        return clinicIndexBo.findList(clinicIndex);
    }

    @Override
    public String saveList(List<ClinicIndex> clinicIndexList,String orgId) {
        return clinicIndexBo.saveList(clinicIndexList,orgId);
    }

    /**
     * 删除号别
     * @param id
     * @return
     */
    @Override
    public String delete(String id) {
        String code="";
        List<ClinicSchedule> list=clinicScheduleBo.getClinicSchedules(id);
        if(list!=null && list.size()>0){
           code="0";
        }else{
            clinicIndexBo.delete(id);
        }
        return code;
    }

    @Override
    public BaseDto getCost(String id) {
        return clinicIndexBo.getCost(id);
    }
}