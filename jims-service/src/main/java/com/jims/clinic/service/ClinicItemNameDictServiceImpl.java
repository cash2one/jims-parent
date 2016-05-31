package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ClinicItemNameDictServiceApi;
import com.jims.clinic.dao.ClinicItemNameDictDao;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 诊疗项目名称表
 * @author xueyx
 * @version 2016-05-04
 */
@Service(version = "1.0.0")

public class ClinicItemNameDictServiceImpl extends CrudImplService<ClinicItemNameDictDao, ClinicItemNameDict> implements ClinicItemNameDictServiceApi {

    @Autowired
    private ClinicItemNameDictDao clinicItemNameDictDao;

    /**
     * 查询检验项目
     * @param标本expand1
     * @param检验类别expand2
     * @param科室expand3
     * @author xueyx
     * @version 2016/5/06
     */
    public List<ClinicItemNameDict> selectLabItem(ClinicItemNameDict clinicItemNameDict){
        return clinicItemNameDictDao.selectLabItem(clinicItemNameDict);
    }
}
