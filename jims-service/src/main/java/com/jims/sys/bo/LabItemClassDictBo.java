package com.jims.sys.bo;

/**
 * Created by wei on 2016/6/3.
 */

import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.LabItemClassDictDao;
import com.jims.sys.entity.LabItemClassDict;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检验项目分类
 * @author zhuq
 * @version 2016-06-3
 */
@Service
@Component
@Transactional(readOnly = false)
public class LabItemClassDictBo extends CrudImplService<LabItemClassDictDao ,LabItemClassDict> {
    @Autowired
    private LabItemClassDictDao labItemClassDictDao;

    /**
     * 查询科室代码下的检验类别
     * @return
     */
    public List<LabItemClassDict> findListByDeptCode(@Param("deptCode") String deptCode, @Param("orgId")String orgId){
        return labItemClassDictDao.findListByDeptCode(deptCode,orgId);
    };

    /**
     * 获取列表
     */
    public List<LabItemClassDict> findAllList(){
        return labItemClassDictDao.findAllList();
    }


}
