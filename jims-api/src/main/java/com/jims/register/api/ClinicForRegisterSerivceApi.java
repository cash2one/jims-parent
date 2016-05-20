package com.jims.register.api;

import com.jims.register.entity.ClinicForRegist;
import com.jims.register.entity.ClinicSchedule;

import java.util.Date;
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

    /**
     * 保存 号表
     * @param clinicSchedules
     * @param startTime
     * @param endTime
     * @return
     */
    public String saveRegister (List<ClinicSchedule> clinicSchedules,String startTime,String endTime)throws Exception;

    /**
     * 删除 已经生成的号表
     * @param id
     * @return
     * @author zhaoning
     */
    public String delete(String id);
}
