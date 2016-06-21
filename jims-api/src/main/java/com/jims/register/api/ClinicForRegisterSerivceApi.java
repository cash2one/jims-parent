package com.jims.register.api;

import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.persistence.Page;
import com.jims.register.entity.ClinicForRegist;
import com.jims.register.entity.ClinicSchedule;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 * 号表生成 Api接口
 * @author  zhaoning
 */
public interface ClinicForRegisterSerivceApi {


    /**
     * 分页查询啊
     * @param page
     * @param clinicForRegist
     * @return
     */
    public Page<ClinicForRegist> findPage(Page<ClinicForRegist> page,ClinicForRegist clinicForRegist);

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

    /**
     * 查询当前天的号表
     * @return
     */
    public List<ClinicForRegist> findListReg(String status);

    /**
     * 保存挂号信息
     * @param clinicMaster
     * @return
     */
    public String saveClinic(ClinicMaster clinicMaster)throws Exception;
}
