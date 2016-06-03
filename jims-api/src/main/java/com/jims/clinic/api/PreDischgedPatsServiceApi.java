package com.jims.clinic.api;

import com.jims.clinic.entity.PreDischgedPats;
import com.jims.common.persistence.Page;

/**
 * Created by qinlongxin on 2016/6/2.
 */
public interface PreDischgedPatsServiceApi {
    /**
     * 获取出院通知单信息
     * @param id
     *  @author qinlongxin
     * @return
     */
    public PreDischgedPats get(String id);
    /**
     * 出院通知单分页
     * @param page,preDischgedPats
     *  @author qinlongxin
     * @return
     */
    public Page<PreDischgedPats> findPage(Page<PreDischgedPats> page,PreDischgedPats preDischgedPats);
    /**
     * 删除出院通知单
     * @param ids 主键id集合
     *  @author qinlongxin
     * @return
     */
    public String delete(String ids);
    /**
     * 保存出院通知单
     * @param preDischgedPats 实体类型
     *  @author qinlongxin
     * @return
     */
    public String save(PreDischgedPats preDischgedPats);
}
