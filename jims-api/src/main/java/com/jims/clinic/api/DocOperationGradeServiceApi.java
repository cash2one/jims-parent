package com.jims.clinic.api;

import com.jims.clinic.entity.DocOperationGrade;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * Created by qinlongxin on 2016/5/6.
 */
public interface DocOperationGradeServiceApi {
    /**
     * 根据id查询该表信息
     * @author qinlongxin
     * @version 2016/4/23
     */
    public  DocOperationGrade  get(String id);
    /**
     * 查询用血主表分页信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public Page<DocOperationGrade> findPage(Page< DocOperationGrade> page,  DocOperationGrade docOperationGrade);
    /**
     * 删除
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String delete(String ids);
    /**
     * 根据用血申请单号查询用血数量申请
     */
    public List<DocOperationGrade> getDocOperationGradeList(String operationId);
}
