package com.jims.register.api;

import com.jims.common.web.impl.BaseDto;
import com.jims.register.entity.ClinicSchedule;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 * 号别安排 Api
 * @author zhaoning
 */
public interface ClinicScheduleApi {
    /**
     * 查询 安排录入 list
     * @param clinicSchedule
     * @return
     * @author zhaoning
     */
    public List<ClinicSchedule> findList(ClinicSchedule clinicSchedule);

    /**
     * 查询 安排录入 list
     * @param clinicSchedule
     * @return
     * @author zhaoning
     */
    public List<BaseDto> findListTable(ClinicSchedule clinicSchedule);

    /**
     * 保存 安排录入
     * @param list
     * @param clinicIndexId
     * @return
     */
    public String saveList(List<ClinicSchedule> list,String clinicIndexId);

    /**
     *删除安排录入
     * @param id
     * @return
     */
    public String delete(String id);
}
