package com.jims.clinic.api;

import com.jims.clinic.entity.DocOperationApply;
import com.jims.common.persistence.Page;

/**
 * Created by qinlongxin on 2016/5/6.
 */
public interface DocOperationApplyServiceApi {
    /**
     * 根据id查询该表信息
     * @author qinlongxin
     * @version 2016/4/23
     */
    public DocOperationApply  get(String id);
    /**
     * 查询用血主表分页信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public Page<DocOperationApply> findPage(Page<DocOperationApply> page, DocOperationApply docOperationApply);
    /**
     * 保存或编辑
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String saveDocOperationApply(DocOperationApply docOperationApply);
    /**
     * 删除
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String delete(String ids);
}
