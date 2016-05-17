package com.jims.register.api;

import com.jims.register.entity.ClinicTypeFee;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 * 号类Api接口
 * @author zhaoning
 */
public interface ClinicTypeFeeServiceApi {
    /**
     * 根据typeId  查询 号类项目及收费
     * @param clinicTypeFee
     * @return
     */
    public List<ClinicTypeFee> findList(ClinicTypeFee clinicTypeFee);

    /**
     * 保存号类
     * @param clinicTypeFeeList
     * @return
     */
    public String saveList (List<ClinicTypeFee> clinicTypeFeeList,String type,String ClinicTypeId);

    /**
     * 删除数据
     * @param id
     * @return
     */
    public String delete(String id);
}
