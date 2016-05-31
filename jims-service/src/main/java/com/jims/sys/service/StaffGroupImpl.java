package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.api.InputSettingServiceApi;
import com.jims.sys.api.StaffGroupApi;
import com.jims.sys.dao.InputSettingDetailDao;
import com.jims.sys.dao.InputSettingMasterDao;
import com.jims.sys.dao.StaffGroupClassDictDao;
import com.jims.sys.dao.StaffGroupDictDao;
import com.jims.sys.entity.InputSettingDetail;
import com.jims.sys.entity.InputSettingMaster;
import com.jims.sys.entity.StaffGroupClassDict;
import com.jims.sys.entity.StaffGroupDict;
import com.jims.sys.vo.InputInfoVo;
import com.jims.sys.vo.InputParamVo;
import com.jims.sys.vo.InputSettingVo;
import com.jims.sys.vo.StaffGroupVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

/**
 *  用户工作组Service
 * Created by yangruidong on 2016/5/18 .
 */
@Service(version = "1.0.0")

public class StaffGroupImpl extends CrudImplService<StaffGroupDictDao, StaffGroupDict> implements StaffGroupApi {


    @Autowired
    private StaffGroupClassDictDao staffGroupClassDictDao;

    /**
     * 保存  增删改   工作组类
     * @param staffGroupClassDictVo
     * @return
     *  @author  yangruidong
     */
    @Override

    public List<StaffGroupClassDict> saveGroupClass(StaffGroupVo<StaffGroupClassDict> staffGroupClassDictVo) {
        List<StaffGroupClassDict> newUpdateDict = new ArrayList<StaffGroupClassDict>();
        List<StaffGroupClassDict> inserted = staffGroupClassDictVo.getInserted();
        List<StaffGroupClassDict> updated = staffGroupClassDictVo.getUpdated();
        List<StaffGroupClassDict> deleted = staffGroupClassDictVo.getDeleted();
        //插入
        for (StaffGroupClassDict staffGroupClassDict : inserted) {
            staffGroupClassDict.preInsert();
            staffGroupClassDict.setOrgId(staffGroupClassDictVo.getOrgId());
            int num = staffGroupClassDictDao.insert(staffGroupClassDict);
        }
        //更新
        for (StaffGroupClassDict staffGroupClassDict : updated) {
            staffGroupClassDict.preUpdate();
            int num = staffGroupClassDictDao.update(staffGroupClassDict);
        }
        //删除
        List<String> ids = new ArrayList<String>();

        for (StaffGroupClassDict staffGroupClassDict : deleted) {
            ids.add(staffGroupClassDict.getId());
        }
        for (String id : ids) {
            staffGroupClassDictDao.delete(id);
        }
        return newUpdateDict;
    }


    /**
     * 保存  增删改   工作组
     * @param staffGroupDictVo
     * @return
     *  @author  yangruidong
     */
    @Override

    public List<StaffGroupDict> saveGroup(StaffGroupVo<StaffGroupDict> staffGroupDictVo) {
        List<StaffGroupDict> newUpdateDict = new ArrayList<StaffGroupDict>();
        List<StaffGroupDict> inserted = staffGroupDictVo.getInserted();
        List<StaffGroupDict> updated = staffGroupDictVo.getUpdated();
        List<StaffGroupDict> deleted = staffGroupDictVo.getDeleted();
        //插入
        for (StaffGroupDict staffGroupDict : inserted) {
            staffGroupDict.preInsert();
            staffGroupDict.setGroupClassId(staffGroupDictVo.getStaff_group_class__id());
            staffGroupDict.setDeptCode(staffGroupDict.getGroupCode());
            staffGroupDict.setDeptName(staffGroupDict.getGroupName());
            int num = dao.insert(staffGroupDict);
        }
        //更新
        for (StaffGroupDict staffGroupDict : updated) {
            staffGroupDict.preUpdate();
            staffGroupDict.setDeptCode(staffGroupDict.getGroupCode());
            staffGroupDict.setDeptName(staffGroupDict.getGroupName());
            int num = dao.update(staffGroupDict);
        }
        //删除
        List<String> ids = new ArrayList<String>();

        for (StaffGroupDict staffGroupDict: deleted) {
            ids.add(staffGroupDict.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }
        return newUpdateDict;
    }

    /**
     * 根据组织机构id查询工作组类的全部信息
     * @param orgId 组织机构id
     * @return
     * @author yangruidong
     */
    @Override
    public List<StaffGroupClassDict> findAllListByOrgId(String orgId) {
        return staffGroupClassDictDao.findAllListByOrgId(orgId);
    }

    /**
     * 根据工作组类的id查询工作组的信息
     * @param id
     * @return
     * @author yangruidong
     */
    @Override
    public List<StaffGroupDict> findListGroupDict(String id) {
        return dao.findListGroupDict(id);
    }

}
