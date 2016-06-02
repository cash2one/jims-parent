/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.clinic.dao.*;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.clinic.utils.CostOrdersUtils;
import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;
import com.jims.sys.dao.OrgRoleDao;
import com.jims.sys.entity.OrgRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查预约记录BAO层
 *
 * @author zhangyao
 * @version 2016-04-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class OrgRoleBo extends CrudImplService<OrgRoleDao, OrgRole> {

    @Autowired
    private OrgRoleDao orgRoleDao;

    /**
     * 根据orgId获取所有的角色
     * @return
     */
    public List<OrgRole> findAllList(String orgId) {
        return orgRoleDao.findAllList(orgId);
    }
}