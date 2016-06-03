package com.jims.nurse.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.nurse.api.BedRecServiceApi;
import com.jims.nurse.bo.BedRecBo;
import com.jims.nurse.entity.BedRec;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BedRecServiceImpl
 *
 * @author PangQian
 * @date2016/6/2 0002
 */
@Service(version = "1.0.0")
public class BedRecServiceImpl implements BedRecServiceApi {
  @Autowired
    private BedRecBo bedRecBo;

    public Page<BedRec> findPage(Page<BedRec> bedRecPage,BedRec bedRec){
       return bedRecBo.findPage(bedRecPage,bedRec);
    }

}
