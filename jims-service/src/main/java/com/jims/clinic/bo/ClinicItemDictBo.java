package com.jims.clinic.bo;

import com.jims.clinic.dao.ClinicItemDictDao;
import com.jims.clinic.dao.ClinicItemNameDictDao;
import com.jims.clinic.dao.ClinicVsChargeDao;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.clinic.entity.ClinicVsCharge;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.vo.PriceDictListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 诊疗项目BO层
 * @author lgx
 * @version 2016-06-16
 */
@Service
@Transactional(readOnly = false)
public class ClinicItemDictBo extends CrudImplService<ClinicItemDictDao, ClinicItemDict> {

    @Autowired
    private ClinicItemNameDictDao nameDao;
    @Autowired
    private ClinicVsChargeDao vsDao;

    /**
     *  查询诊疗项目列表通过组织机构id
     * @param orgId 组织机构id
     * @return
     * @author txb
     */
    public List<ClinicItemDict> itemListByOrgId(String orgId) {
        return dao.itemListByOrgId(orgId);
    }

    /**
     * 编码或名称已存在个数
     * @param entity
     * @return
     */
    public boolean codeOrNameHas(ClinicItemDict entity){
        List<ClinicItemDict> list = dao.findExisted(entity);
        if(list != null && list.size() > 0){
            for(int i=0;i<list.size();i++){
                if(!list.get(i).getId().equals(entity.getId()))
                    return true;
            }
        }
        return false;
    }

    /**
     * 批量保存临床诊疗项目数据（插入或更新）
     * @param entityList
     */
    public void save(List<ClinicItemDict> entityList){
        if(entityList != null){
            for(ClinicItemDict entity : entityList){
                super.save(entity);
            }
        }
    }

    /**
     * 删除诊疗项目数据以及所属名称和价表对照
     * @param entity
     */
    public void deleteCascade(ClinicItemDict entity) {
        deleteName(entity);
        deleteVs(entity);
        super.delete(entity);
    }

    /**
     * 删除诊疗项目数据以及所属名称和价表对照
     * @param ids,多个id以逗号隔开
     */
    public void deleteCascade(String ids) {
        String[] id = ids.split(",");
        for (int j = 0; j < id.length; j++){
            ClinicItemDict entity = super.get(id[j]);
            deleteCascade(entity);
        }
    }

    /**
     * 获取临床诊疗项目名称（正/别名）信息
     * @param entity
     * @return
     */
    public List<ClinicItemNameDict> findNameList(ClinicItemNameDict entity) {
        return nameDao.findList(entity);
    }

    /**
     * 保存临床诊疗项目名称(正/别名)数据（插入或更新）
     * @param entity
     */
    public void save(ClinicItemNameDict entity) {
        if (entity.getIsNewRecord()){
            entity.preInsert();
            nameDao.insert(entity);
        }else{
            entity.preUpdate();
            nameDao.update(entity);
        }
    }

    /**
     * 批量保存临床诊疗项目名称(正/别名)数据（插入或更新）
     * @param entityList
     */
    public void saveNameList(List<ClinicItemNameDict> entityList){
        if(entityList != null){
            for(ClinicItemNameDict entity : entityList){
                save(entity);
            }
        }
    }

    /**
     * 删除临床诊疗项目名称(正/别名)数据
     * @param ids ,多个id以逗号隔开
     */
    public void deleteName(String ids) {
        if(ids != null && ids.trim().length() > 0) {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++) {
                nameDao.delete(id[j]);
            }
        }
    }

    /**
     * 删除临床诊疗项目所有名称(正/别名)数据
     * @param entity
     */
    public void delete(ClinicItemNameDict entity) {
        if(entity.getId() == null){
            nameDao.deleteNoId(entity);
        } else {
            nameDao.delete(entity);
        }
    }

    /**
     * 删除临床诊疗项目所有名称(正/别名)数据
     * @param entity
     */
    public void deleteName(ClinicItemDict entity) {
        ClinicItemNameDict itemName = new ClinicItemNameDict();
        itemName.setOrgId(entity.getOrgId());
        itemName.setItemClass(entity.getItemClass());
        itemName.setItemCode(entity.getItemCode());
        delete(itemName);
    }

    /**
     * 获取临床诊疗与价表对照信息
     * @param entity
     * @return
     */
    public List<ClinicVsCharge> findVsList(ClinicVsCharge entity) {
        return vsDao.findList(entity);
    }

    /**
     * 保存临床诊疗与价表对照数据（插入或更新）
     * @param entity
     */
    public void save(ClinicVsCharge entity) {
        if (entity.getIsNewRecord()){
            entity.preInsert();
            vsDao.insert(entity);
        }else{
            entity.preUpdate();
            vsDao.update(entity);
        }
    }

    /**
     * 批量保存临床诊疗与价表对照数据（插入或更新）
     * @param entityList
     */
    public void saveVsList(List<ClinicVsCharge> entityList){
        if(entityList != null){
            for(ClinicVsCharge entity : entityList){
                save(entity);
            }
        }
    }

    /**
     * 删除临床诊疗与价表对照数据
     * @param entity
     */
    public void delete(ClinicVsCharge entity) {
        if(entity.getId() == null){
            vsDao.deleteNoId(entity);
        } else {
            vsDao.delete(entity);
        }
    }

    /**
     * 删除临床诊疗与价表对照数据
     * @param ids,多个id以逗号隔开
     */
    public void deleteVs(String ids) {
        if(ids != null && ids.trim().length() > 0) {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++) {
                vsDao.delete(id[j]);
            }
        }
    }

    /**
     * 删除临床诊疗与价表对照数据
     * @param entity
     */
    public void deleteVs(ClinicItemDict entity) {
        ClinicVsCharge vs = new ClinicVsCharge();
        vs.setOrgId(entity.getOrgId());
        vs.setClinicItemClass(entity.getItemClass());
        vs.setClinicItemCode(entity.getItemCode());
        delete(vs);
    }

    /**
     * 根据组织机构ID和诊疗项目类别获取诊疗项目名称
     * @param orgId 组织机构Id
     * @param clinicClass 诊疗项目名称
     * @author ztq
     * @return
     */
    public List<ClinicItemDict> findListByOrgIdAndClinicClass(String orgId, String clinicClass) {
        ClinicItemDict clinicItemDict = new ClinicItemDict() ;
        clinicItemDict.setOrgId(orgId);
        clinicItemDict.setItemClass(clinicClass);
        return findList(clinicItemDict);
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
    public void saveBatch(List<ClinicItemDict> list){
        for (int i = 0, j = (list != null ? list.size() : 0); i < j; i++) {
            ClinicItemDict itemObj = list.get(i);
            if ("1".equals(itemObj.getDelFlag())) {
                deleteCascade(itemObj.getId());
            } else {
                if(itemObj.getSaveNameList() != null && itemObj.getSaveNameList().size() > 0){
                    nameDao.deleteNoId(itemObj.getSaveNameList().get(0));
                    for(ClinicItemNameDict name : itemObj.getSaveNameList()){
                        name.preInsert();
                        nameDao.insert(name);
                    }
                }
                if(itemObj.getSaveVsList() != null && itemObj.getSaveVsList().size() > 0){
                    vsDao.deleteNoId(itemObj.getSaveVsList().get(0));
                    for(ClinicVsCharge vs : itemObj.getSaveVsList()){
                        vs.preInsert();
                        vsDao.insert(vs);
                    }
                }
                if(itemObj.getItemCode() != null && itemObj.getItemCode().trim().length() > 0) {
                    save(itemObj);
                }
            }
        }
    }
}