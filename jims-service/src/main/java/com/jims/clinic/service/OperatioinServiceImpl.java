/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.OperatioinSerivceApi;
import com.jims.clinic.dao.OperatioinDao;
import com.jims.clinic.dao.OperationGradeDao;
import com.jims.clinic.entity.Operatioin;
import com.jims.clinic.entity.OperationGrade;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 手术申请Service
 *
 * @author qlx
 * @version 2016-04-26
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class OperatioinServiceImpl extends CrudImplService<OperatioinDao, Operatioin> implements OperatioinSerivceApi {
    @Autowired
    private OperationGradeDao operationGradeDao;

    /**
     * 保存手术申请记录
     *
     * @param
     * @return String
     * @Author zhaoning
     * @version 2016-04-21
     */
    public String saveOperatioin(Operatioin operatioin) {
        String str_state =super.save(operatioin);
        operationGradeDao.delOperation(operatioin.getId());
        if (operatioin.getList() != null) {
            for (OperationGrade column : operatioin.getList()) {
                column.preInsert();
                operationGradeDao.insert(column);
            }
        }
        return str_state;
    }
}