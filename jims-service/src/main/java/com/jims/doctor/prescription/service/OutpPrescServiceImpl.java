/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.prescription.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.prescription.api.OutpPrescServiceApi;
import com.jims.prescription.entity.OutpPresc;
import com.jims.doctor.prescription.bo.OutpPrescBo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 处方医嘱明细记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")
public class OutpPrescServiceImpl implements OutpPrescServiceApi{

    @Autowired
    private OutpPrescBo outpPrescBo;


    @Override
    public List<OutpPresc> findList(OutpPresc outpPresc) {
        return outpPrescBo.findList(outpPresc);
    }

    @Override
    public List<OutpPresc> getOutpPresc(String orgId, String clinicId) {
        return outpPrescBo.getOutpPresc(orgId,clinicId);
    }

    @Override
    public String save(OutpPresc outpPresc) {
        return outpPrescBo.save(outpPresc);
    }

    @Override
    public String deletePresc(String ids) {
        return outpPrescBo.delete(ids);
    }

    @Override
    public List<OutpPresc> findListByParams(OutpPresc outpPresc) {
        return outpPrescBo.findListByParams(outpPresc);
    }
    /**
     * 根据处方号删除处方
     * @param outpPresc
     * @author CTQ
     * @return
     */
    @Override
    public String delByPrescNo(OutpPresc outpPresc) {
        return outpPrescBo.delByPrescNo(outpPresc);
    }


}