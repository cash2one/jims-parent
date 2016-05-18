package com.jims.register.api;

import com.jims.register.entity.ClinicForRegist;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 * 号表生成 Api接口
 * @author  zhaoning
 */
public interface ClinicForRegisterSerivceApi {
    /**
     * 查询号表 list
     * @param clinicForRegist
     * @return
     */
    public List<ClinicForRegist> findList(ClinicForRegist clinicForRegist);
}
