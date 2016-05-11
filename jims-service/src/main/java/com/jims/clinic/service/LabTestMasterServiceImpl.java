/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.lab.api.LabTestMasterServiceApi;
import com.jims.lab.dao.LabTestItemsDao;
import com.jims.lab.dao.LabTestMasterDao;
import com.jims.lab.entity.LabTestItems;
import com.jims.lab.entity.LabTestMaster;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检验主记录Service
 * @author xueyx
 * @version 2016-04-28
 */
@Service
@Transactional(readOnly = true)
public class LabTestMasterServiceImpl  extends CrudImplService<LabTestMasterDao, LabTestMaster> implements LabTestMasterServiceApi {

    @Autowired
    private LabTestItemsDao labTestItemsDao;
    /**
     * 保存或编辑
     * 整个主表、字表list
     * @param主表LabTestMaster
     * @param子表List
     * @author xueyx
     * @version 2016/5/06
     */
    public void saveAll(LabTestMaster labTestMaster){
        if(labTestMaster!=null && labTestMaster.getId()!=null){
            //申请序号
            //优先标志
            //本次住院标识对门诊病人为空
            //费别
            //结果状态
            labTestMaster.setResultStatus("1");
            labTestMaster.setDelFlag("0");
            save(labTestMaster);
            List<LabTestItems> list = labTestMaster.getList();
            if(list.size()>0){
                for (int i= 0; i < list.size();i++){
                    LabTestItems labTestItems=list.get(i);
                    labTestItems.setDelFlag("0");
                    labTestItems.preInsert();
                    labTestItems.setTestNo(labTestMaster.getTestNo());
                    labTestItemsDao.insert(labTestItems);
                }
            }
        }
    }
}