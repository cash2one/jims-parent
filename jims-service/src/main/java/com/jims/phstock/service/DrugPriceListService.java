/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugPriceListServiceApi;
import com.jims.phstock.dao.DrugPriceListDao;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.vo.DrugCatalogBeanVo;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 药品价格Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugPriceListService extends CrudImplService<DrugPriceListDao, DrugPriceList> implements DrugPriceListServiceApi {


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
        return dao.listDrugNameDict(orgId);
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
        return dao.listDrugNameDictByClassCode(orgId, classCode);
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
        return dao.listDrugPriceList(drugCode,orgId);
    }

    /**
     * 检索当前日期所属机构的药品
     * @param orgId 机构ID
     * @return
     */
    public List<DrugDict> findDrugDict(String orgId){
        return dao.findDrugDict(orgId);
    }
    /**
     * 药品价表保存
     * @param drugPriceVo 药品价表实体vo
     * @return
     * @author txb
     */
    @Override
    public String saveDrugPrice(DrugCatalogChangeVo drugPriceVo) {
        List<DrugPriceList> insertDicts = drugPriceVo.getInserted();
        List<DrugPriceList> updateDicts = drugPriceVo.getUpdated();
        List<DrugPriceList> deleteDicts = drugPriceVo.getDeleted();

        if (insertDicts != null && insertDicts.size() > 0) {
            for (DrugPriceList drugPriceList : insertDicts) {
//                drugPriceList.setStartDate(drugPriceList.getStartDate());
                System.out.println(drugPriceList.getStartDate());
                System.out.println(drugPriceList.getStopDate());
//                if ()
                drugPriceList.preInsert();
                dao.insert(drugPriceList);

            }
        }
        if (updateDicts != null && updateDicts.size() > 0) {
            for (DrugPriceList drugPriceList : updateDicts) {

                System.out.println(drugPriceList.getStartDate());
                System.out.println(drugPriceList.getStopDate());
                drugPriceList.preUpdate();
                dao.update(drugPriceList);
            }
        }
        if (deleteDicts != null && deleteDicts.size() > 0) {
            for (DrugPriceList drugPriceList : deleteDicts) {
                dao.delete(drugPriceList);
            }
        }
        return "1";
    }
}