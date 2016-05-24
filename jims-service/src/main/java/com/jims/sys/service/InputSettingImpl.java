package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.api.InputSettingServiceApi;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.dao.InputSettingDetailDao;
import com.jims.sys.dao.InputSettingMasterDao;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.InputSettingDetail;
import com.jims.sys.entity.InputSettingMaster;
import com.jims.sys.vo.InputInfoVo;
import com.jims.sys.vo.InputParamVo;
import com.jims.sys.vo.InputSettingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入法字典Service
 * Created by yangruidong on 2016/5/18 .
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class InputSettingImpl extends CrudImplService<InputSettingMasterDao, InputSettingMaster> implements InputSettingServiceApi {


    @Autowired
    private InputSettingDetailDao inputSettingDetailDao;


    /**
     * 根据字典类型，查询字典设置，然后返回本字典的结果集
     *
     * @param dictType 字典类型
     * @param orgId    组织机构
     * @return
     * @Author ztq
     */
    @Override
    public List<BaseDto> listInputDataBy(String dictType, String orgId) {
        List<InputSettingVo> columnName = inputSettingDetailDao.getColumnName(dictType, orgId);
        StringBuilder sb = new StringBuilder();
        if (columnName.size() == 1) {
            sb.append(columnName.get(0).getColumnName());

        } else {

            for (int i = 0; i < columnName.size(); i++) {
                sb.append(columnName.get(i).getColumnName() + ",");
            }
        }

        if (columnName.size() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        String param = sb.toString();
        List<BaseDto> baseDtos = inputSettingDetailDao.findListBy(param, dictType);

        return baseDtos;
    }

    /**
     * 根据表名称，查询表中有什么样的字段
     *
     * @param tableName
     * @return
     */
    @Override
    public List<String> listTableColByTableName(String tableName) {
        return inputSettingDetailDao.listTableColByTableName(tableName);
    }

    /**
     * 根据组织机构id查询输入法字典的全部信息
     *
     * @param orgId 组织机构id
     * @return
     * @author yangruidong
     */
    @Override
    public List<InputSettingMaster> findAllListByOrgId(String orgId) {
        return dao.findAllListByOrgId(orgId);
    }


    /**
     * 保存  增删改
     *
     * @param inputSettingMasterVo
     * @return
     * @author yangruidong
     */
    @Override
    @Transactional(readOnly = true)
    public List<InputSettingMaster> saveAll(InputSettingVo<InputSettingMaster> inputSettingMasterVo) {
        List<InputSettingMaster> newUpdateDict = new ArrayList<InputSettingMaster>();
        List<InputSettingMaster> inserted = inputSettingMasterVo.getInserted();
        List<InputSettingMaster> updated = inputSettingMasterVo.getUpdated();
        List<InputSettingMaster> deleted = inputSettingMasterVo.getDeleted();
        //插入
        for (InputSettingMaster inputSettingMaster : inserted) {
            inputSettingMaster.preInsert();
            inputSettingMaster.setOrgId(inputSettingMasterVo.getOrgId());
            int num = dao.insert(inputSettingMaster);
        }
        //更新
        for (InputSettingMaster inputSettingMaster : updated) {
            inputSettingMaster.preUpdate();
            int num = dao.update(inputSettingMaster);
        }
        //删除
        List<String> ids = new ArrayList<String>();

        for (InputSettingMaster inputSettingMaster : deleted) {
            ids.add(inputSettingMaster.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }
        return newUpdateDict;
    }

    /**
     * 根据输入法主记录的id查询输入法字典明细表的信息
     *
     * @param id
     * @return
     * @author yangruidong
     */
    @Override
    public List<InputSettingDetail> findListDetail(String id) {
        return inputSettingDetailDao.findListDetail(id);
    }

    /**
     * 保存  增删改  输入法字典明细表
     *
     * @param inputSettingDetailVo
     * @return
     * @author yangruidong
     */
    @Override
    @Transactional(readOnly = true)
    public List<InputSettingDetail> saveDetail(InputSettingVo<InputSettingDetail> inputSettingDetailVo) {
        List<InputSettingDetail> newUpdateDict = new ArrayList<InputSettingDetail>();
        List<InputSettingDetail> inserted = inputSettingDetailVo.getInserted();
        List<InputSettingDetail> updated = inputSettingDetailVo.getUpdated();
        List<InputSettingDetail> deleted = inputSettingDetailVo.getDeleted();
        //插入
        for (InputSettingDetail inputSettingDetail : inserted) {
            inputSettingDetail.preInsert();
            inputSettingDetail.setInputSettingMasterId(inputSettingDetailVo.getInput_setting_master_id());
            int num = inputSettingDetailDao.insert(inputSettingDetail);
        }
        //更新
        for (InputSettingDetail inputSettingDetail : updated) {
            inputSettingDetail.preUpdate();
            int num = inputSettingDetailDao.update(inputSettingDetail);
        }
        //删除
        List<String> ids = new ArrayList<String>();

        for (InputSettingDetail inputSettingDetail : deleted) {
            ids.add(inputSettingDetail.getId());
        }
        for (String id : ids) {
            inputSettingDetailDao.delete(id);
        }
        return newUpdateDict;
    }


    /**
     * 根据传入的信息查询并过滤相关内容
     *
     * @param inputInfoVo
     * @return
     */
    @Override
    public List<BaseDto> listInputDataByParam(InputInfoVo inputInfoVo) {
        String dictType = inputInfoVo.getDictType();
        String orgId = inputInfoVo.getOrgId();
        List<InputSettingVo> columnName = inputSettingDetailDao.getColumnName(dictType, orgId);
        StringBuilder sb = new StringBuilder();
        if (columnName.size() == 1) {
            sb.append(columnName.get(0).getColumnName());

        } else {

            for (int i = 0; i < columnName.size(); i++) {
                sb.append(columnName.get(i).getColumnName() + ",");
            }
        }

        if (columnName.size() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }

        String param = sb.toString();
        List<InputParamVo> list = inputInfoVo.getInputParamVos();
        List<BaseDto> baseDtos = inputSettingDetailDao.listInputDataByParam(param, dictType, list);

        return baseDtos;
    }
}
