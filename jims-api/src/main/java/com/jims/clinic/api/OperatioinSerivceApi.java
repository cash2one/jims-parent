package com.jims.clinic.api;

import com.jims.clinic.entity.Operatioin;
import com.jims.common.persistence.Page;

/**
 * Created by qinlongxin on 2016/4/26.
 */
public interface OperatioinSerivceApi {
    /**
     * 根据手术申请表id查询该表
     * @author qinlongxin
     * @version 2016/4/23
     */
    public Operatioin get(String id);
    /**
     * 查询术申请表分页信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public Page<Operatioin> findPage(Page<Operatioin> page, Operatioin operatioin);
    /**
     * 保存或编辑
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String saveOperatioin(Operatioin operatioin);
    /**
     * 删除病术申请表数据
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String delete(String ids);
}
