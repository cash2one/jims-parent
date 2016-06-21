package com.jims.register.api;

import com.jims.common.persistence.Page;
import com.jims.register.entity.ClinicIndex;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 * 号别 api
 * @author zhaoning
 */
public interface ClinicIndexServiceApi {

    /**
     * 分页查询啊
     * @param page
     * @param
     * @return
     */
    public Page<ClinicIndex> findPage(Page<ClinicIndex> page,ClinicIndex clinicIndex);


    /**
     * 查询 号别List
     * @param clinicIndex
     * @return
     * @author zhaoning
     */
    public List<ClinicIndex> findList(ClinicIndex clinicIndex);

    /**
     * 保存 号别 --多条保存
     * @param clinicIndexList
     * @return
     */
    public String saveList(List<ClinicIndex> clinicIndexList);

    /**
     * 删除 号别
     * @param id
     * @return
     */
    public String delete(String id);
}
