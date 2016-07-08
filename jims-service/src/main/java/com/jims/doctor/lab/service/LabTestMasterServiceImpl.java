/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.lab.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.doctor.lab.bo.ClinicLabTestBo;
import com.jims.lab.api.LabTestMasterServiceApi;
import com.jims.doctor.lab.bo.HosLabTestBo;
import com.jims.doctor.lab.dao.LabTestMasterDao;
import com.jims.lab.entity.LabTestMaster;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 检验主记录Service
 * @author xueyx
 * @version 2016-04-28
 */
@Service(version = "1.0.0")

public class LabTestMasterServiceImpl  extends CrudImplService<LabTestMasterDao, LabTestMaster> implements LabTestMasterServiceApi {

    @Autowired
    private ClinicLabTestBo clinicLabTestBo;
    @Autowired
    private HosLabTestBo hosLabTestBo;


    /**
     * 门诊保存
     * 整个主表、字表list
     * @param主表LabTestMaster
     * @param子表List
     * @author xueyx
     * @version 2016/5/06
     */
    //@Override

    public String saveAll(LabTestMaster labTestMaster){
       String num =  clinicLabTestBo.saveAll(labTestMaster);
        return num;
    }

    /**
     *
     * @param ids
     */
    public void delAll(String ids){
        clinicLabTestBo.delete(ids);
    }

    public String creatTestNo(){
        return clinicLabTestBo.creatTestNo();
    }

    /**
     * 住院检验保存
     * @param labTestMaster
     * @return
     */
    public String saveAllIn(LabTestMaster labTestMaster){
        return hosLabTestBo.saveAllIn(labTestMaster);
    }

    /**
     * 门诊删除
     * @param ids
     * @return
     */
    public String deleteLabTestMaster(String ids){
        String  num= clinicLabTestBo.deleteLabTestMaster(ids);
        return num;
    }

    /**
     * 住院删除
     * @param ids
     * @return
     */
    @Override
    public String deleteLabTestMasterHos(String ids) {
       String num = hosLabTestBo.deleteLabTestMasterHos(ids);
        return num;
    }
}