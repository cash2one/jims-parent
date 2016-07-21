package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ClinicItemApi;
import com.jims.clinic.bo.ClinicItemDictBo;
import com.jims.clinic.dao.ClinicItemDictDao;
import com.jims.clinic.dao.ClinicItemNameDictDao;
import com.jims.clinic.dao.ClinicVsChargeDao;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.clinic.entity.ClinicVsCharge;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.vo.PriceDictListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * 诊疗项目Service
 * @author lgx
 * @version 2016-04-28
 */
@Service(version = "1.0.0")
public class ClinicItemDictServiceImpl implements ClinicItemApi{

    @Autowired
    private ClinicItemDictBo bo;

    @Override
    public ClinicItemDict get(String id) {
        return bo.get(id);
    }

    @Override
    public List<ClinicItemDict> findList(ClinicItemDict entity) {
        return bo.findList(entity);
    }

    /**
     *  查询诊疗项目列表通过组织机构id
     * @param orgId 组织机构id
     * @return
     * @author txb
     */
    @Override
    public List<ClinicItemDict> itemListByOrgId(String orgId,String q) {
        return bo.itemListByOrgId(orgId,q);
    }

    /**
     * 编码或名称已存在个数
     * @param entity
     * @return
     */
    @Override
    public boolean codeOrNameHas(ClinicItemDict entity){
        return bo.codeOrNameHas(entity);
    }

    @Override
    public Page<ClinicItemDict> findPage(Page<ClinicItemDict> page, ClinicItemDict entity) {
        return bo.findPage(page,entity);
    }

    @Override
    public String save(ClinicItemDict entity) {
        try {
            bo.save(entity);
            return "1";
        } catch (RuntimeException e){}
        return "0";
    }

    /**
     * 批量保存临床诊疗项目数据（插入或更新）
     * @param entityList
     * @return 0 失败，1成功
     */
    public String save(List<ClinicItemDict> entityList){
        String result = "0";
        try {
            bo.save(entityList);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    @Override
    public String delete(String ids) {
        try {
            bo.delete(ids);
            return "1";
        } catch (RuntimeException e){}
        return "0";
    }

    /**
     * 删除诊疗项目数据以及所属名称和价表对照
     * @param entity
     * @return 0 失败，1成功
     */
    public String deleteCascade(ClinicItemDict entity) {
        String result = "0";
        try {
            bo.deleteCascade(entity);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 删除诊疗项目数据以及所属名称和价表对照
     * @param ids,多个id以逗号隔开
     * @return 0 失败，1成功
     */
    public String deleteCascade(String ids) {
        String result = "0";
        try {
            bo.deleteCascade(ids);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 获取临床诊疗项目名称（正/别名）信息
     * @param entity
     * @return
     */
    public List<ClinicItemNameDict> findNameList(ClinicItemNameDict entity) {
        return bo.findNameList(entity);
    }

    /**
     * 保存临床诊疗项目名称(正/别名)数据（插入或更新）
     * @param entity
     * @return 0 失败，1成功
     */
    @Override
    public String save(ClinicItemNameDict entity) {
        String result = "0";
        try {
            bo.save(entity);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 批量保存临床诊疗项目名称(正/别名)数据（插入或更新）
     * @param entityList
     * @return 0 失败，1成功
     */
    @Override
    public String saveNameList(List<ClinicItemNameDict> entityList){
        String result = "0";
        try {
            bo.saveNameList(entityList);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 删除临床诊疗项目名称(正/别名)数据
     * @param ids ,多个id以逗号隔开
     * @return 0 失败，1成功
     */
    @Override
    public String deleteName(String ids) {
        String result = "0";
        try {
            bo.deleteName(ids);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 删除临床诊疗项目所有名称(正/别名)数据
     * @param entity
     * @return 0 失败，1成功
     */
    @Override
    public String delete(ClinicItemNameDict entity) {
        String result = "0";
        try {
            bo.delete(entity);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 删除临床诊疗项目所有名称(正/别名)数据
     * @param entity
     * @return 0 失败，1成功
     */
    @Override
    public String deleteName(ClinicItemDict entity) {
        String result = "0";
        try {
            bo.deleteName(entity);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 获取临床诊疗与价表对照信息
     * @param entity
     * @return
     */
    @Override
    public List<ClinicVsCharge> findVsList(ClinicVsCharge entity) {
        return bo.findVsList(entity);
    }

    /**
     * 保存临床诊疗与价表对照数据（插入或更新）
     * @param entity
     * @return 0 失败，1成功
     */
    @Override
    public String save(ClinicVsCharge entity) {
        String result = "0";
        try {
            bo.save(entity);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 批量保存临床诊疗与价表对照数据（插入或更新）
     * @param entityList
     * @return 0 失败，1成功
     */
    @Override
    public String saveVsList(List<ClinicVsCharge> entityList){
        String result = "0";
        try {
            bo.saveVsList(entityList);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 删除临床诊疗与价表对照数据
     * @param entity
     * @return 0 失败，1成功
     */
    @Override
    public String delete(ClinicVsCharge entity) {
        String result = "0";
        try {
            bo.delete(entity);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 删除临床诊疗与价表对照数据
     * @param ids,多个id以逗号隔开
     * @return 0 失败，1成功
     */
    @Override
    public String deleteVs(String ids) {
        String result = "0";
        try {
            bo.deleteVs(ids);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 删除临床诊疗与价表对照数据
     * @param entity
     * @return 0 失败，1成功
     */
    @Override
    public String deleteVs(ClinicItemDict entity) {
        String result = "0";
        try {
            bo.deleteVs(entity);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 根据组织机构ID和诊疗项目类别获取诊疗项目名称
     * @param orgId 组织机构Id
     * @param clinicClass 诊疗项目名称
     * @author ztq
     * @return
     */
    @Override
    public List<ClinicItemDict> findListByOrgIdAndClinicClass(String orgId, String clinicClass) {
        return bo.findListByOrgIdAndClinicClass(orgId,clinicClass);
    }

    /**
     * 批量处理（添加、修改、删除）诊疗项目、正别名以及对照
     * @param list ClinicItemDict对象序列
     *          如果ClinicItemDict对象delFlag为1，该对象为删除数据参数。
     *                      该对象的Id为需要删除的数据的Id(也有可能是多个Id以‘ , ’拼接的ID字符串)
     *          其余为诊疗项目保存的数据(ClinicItemDict数据没有itemCode则只处理正别名、对照)，
     *                      saveNameList为正别名数据
     *                      saveVsList  为对照数据
     *
     * @return 0 失败，1成功
     */
    @Override
    public String saveBatch(List<ClinicItemDict> list){
        String result = "0";
        try {
            bo.saveBatch(list);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }
}