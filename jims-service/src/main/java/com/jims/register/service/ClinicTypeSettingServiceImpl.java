package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicTypeSettingServiceApi;
import com.jims.register.bo.ClinicIndexBo;
import com.jims.register.bo.ClinicTypeSettingBo;
import com.jims.register.dao.ClinicTypeSettingDao;
import com.jims.register.entity.ClinicIndex;
import com.jims.register.entity.ClinicTypeSetting;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 号类字典Service
 * @author 张耀
 * @version 2016-05-16
 */
@Service(version = "1.0.0")
public class ClinicTypeSettingServiceImpl  implements ClinicTypeSettingServiceApi {

    @Autowired
    private ClinicTypeSettingBo clinicTypeSettingBo;
    @Autowired
    private ClinicIndexBo clinicIndexBo;
    @Override
    public List<ClinicTypeSetting> findList(ClinicTypeSetting clinicTypeSetting) {
        return clinicTypeSettingBo.findList(clinicTypeSetting);
    }

    /**
     * 删除号类
      * @param id
     * @return
     */
    @Override
    public String delete(String id) {
        String  code="";
       List<ClinicIndex> list=  clinicIndexBo.getClinicIndexs(id);
        if(list!=null && list.size()>0){
            code="0";
        }else {
            code=clinicTypeSettingBo.delete(id);
        }

        return code;
    }
}