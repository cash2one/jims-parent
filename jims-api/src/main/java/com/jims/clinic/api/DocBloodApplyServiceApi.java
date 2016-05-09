package com.jims.clinic.api;

import com.jims.clinic.entity.DocBloodApply;
import com.jims.common.persistence.Page;

/**
 * Created by qinlongxin on 2016/5/6.
 */
public interface DocBloodApplyServiceApi {
    /**
     * 根据id查询该表信息
     * @author qinlongxin
     * @version 2016/4/23
     */
    public DocBloodApply  get(String id);
    /**
     * 查询用血主表分页信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public Page<DocBloodApply> findPage(Page<DocBloodApply> page, DocBloodApply docBloodApply);
    /**
     * 保存或编辑
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String saveDocBloodApply(DocBloodApply docBloodApply);
    /**
     * 删除
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String delete(String ids);
}
