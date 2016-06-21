package com.jims.phstock.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.phstock.api.DrugPriceListServiceApi;
import com.jims.phstock.bo.DrugPriceListBo;
import com.jims.phstock.dao.DrugPriceModifyDao;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.entity.DrugPriceModify;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 药品价格Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
public class DrugPriceListService implements DrugPriceListServiceApi {


    @Autowired
    private DrugPriceListBo bo;

    @Override
    public String save(DrugPriceList drugPriceList) {
        try {
            bo.save(drugPriceList);
            return "1";
        } catch (RuntimeException e){
        }
        return "0";
    }

    /**
     * 保存药品调价数据
     * @param drugPriceModifyVo
     * @return
     * @author txb
     * @version 2016-05-18
     */
    @Override
    public String saveDrugPriceModify(DrugCatalogChangeVo drugPriceModifyVo) {
        try {
            bo.saveDrugPriceModify(drugPriceModifyVo);
            return "1";
        } catch (RuntimeException e){
        }
        return "0";
    }

    @Override
    public Page<DrugPriceList> findPage(Page<DrugPriceList> drugPriceListPage, DrugPriceList drugPriceList) {
        return bo.findPage(drugPriceListPage,drugPriceList);
    }

    @Override
    public List<DrugPriceList> findList(DrugPriceList drugPriceList) {
        return bo.findList(drugPriceList);
    }

    /**
     * 查询列表药品调价数据
     * @param drugPriceModify
     * @return
     * @author txb
     * @version 2016-05-18
     */
    @Override
    public List<DrugPriceModify> findListDrugPriceModify(DrugPriceModify drugPriceModify) {
        return bo.findListDrugPriceModify(drugPriceModify);
    }

    @Override
    public DrugPriceList get(DrugPriceList drugPriceList) {
        return bo.get(drugPriceList);
    }

    @Override
    public String delete(String ids) {
        try {
            bo.delete(ids);
            return "1";
        } catch (RuntimeException e){
        }
        return "0";
    }

    /**
     * 根据当前组织结构获取去本组织结构内所有的药品名称字典。
     * 关联durg_price_list,drug_name_dict drug_price_list drug_code 去重复。
     * @param orgId
     * @return
     * @author ztq
     *
     */
    @Override
    public List<DrugNameDict> listDrugNameDict(String orgId) {
        return bo.listDrugNameDict(orgId);
    }

    /**
     * 根据当前组织结构，和当前类别获取所有的药品名称字典。
     * @param orgId  组织机构id
     * @param classCode 类别代码
     * @return
     * @author txb
     *
     */
    @Override
    public List<DrugNameDict> listDrugNameDictByClassCode(String orgId, String classCode) {
        return bo.listDrugNameDictByClassCode(orgId, classCode);
    }

    /**
     * 根据药品代码查询当前组织结构的药品价格
     * 不同规格、不同厂商，不同单位，不同价格，不同零售价
     * @param drugCode
     * @param orgId
     * @return
     * @author ztq
     */
    @Override
    public List<DrugPriceList> listDrugPriceList(String drugCode, String orgId) {
        return bo.listDrugPriceList(drugCode,orgId);
    }

    /**
     * 检索当前日期所属机构的药品
     * @param orgId 机构ID
     * @return
     */
    public List<DrugDict> findDrugDict(String orgId){
        return bo.findDrugDict(orgId,null);
    }

    /**
     * 检索当前日期所属机构的药品
     * @param orgId 机构ID
     * @param q 模糊查询字段值
     * @return
     */
    public List<DrugDict> findDrugDict(String orgId,String q){
        return bo.findDrugDict(orgId,q);
    }
    /**
     * 药品价表保存
     * @param drugPriceVo 药品价表实体vo
     * @return
     * @author txb
     */
    @Override

    public String saveDrugPrice(DrugCatalogChangeVo drugPriceVo) {
        try {
            bo.saveDrugPrice(drugPriceVo);
            return "1";
        } catch (RuntimeException e){
        }
        return "0";
    }

    /**
     * 通过通知生效日期查询调价记录表
     * @param startDate 调价生效开始日期
     * @param endDate  调价生效结束日期
     * @author txb
     * @return
     */
    @Override
    public List<DrugPriceModify> findModifyListByNoticeEfficientDate(String startDate, String endDate) {
        return bo.findModifyListByNoticeEfficientDate(startDate,endDate);
    }

    /**
     * 保存调价记录确认
     * @param drugPriceModifyVo
     * @author txb
     * @return
     */
    @Override
    public String saveModifyConfirm(DrugCatalogChangeVo<DrugPriceModify> drugPriceModifyVo) {
        try {
            bo.saveModifyConfirm(drugPriceModifyVo);
            return "1";
        } catch (RuntimeException e){
        }
        return "0";
    }

}