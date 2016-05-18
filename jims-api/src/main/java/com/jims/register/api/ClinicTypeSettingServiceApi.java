package com.jims.register.api;


import com.jims.register.entity.ClinicTypeSetting;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 * 号类定义
 * @author 张耀
 * @version 2016-05-16
 */
public interface ClinicTypeSettingServiceApi {
    /**
     * 查询号类集合
     * @param clinicTypeSetting
     * @return
     */
   public List<ClinicTypeSetting> findList(ClinicTypeSetting clinicTypeSetting);
}

