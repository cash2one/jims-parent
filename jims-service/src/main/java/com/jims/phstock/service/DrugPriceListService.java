/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugPriceListServiceApi;
import com.jims.phstock.dao.DrugPriceListDao;
import com.jims.phstock.dao.DrugPriceModifyDao;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.entity.DrugPriceModify;
import com.jims.phstock.vo.DrugCatalogBeanVo;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;
import java.util.List;


/**
 * 药品价格Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")

public class DrugPriceListService extends CrudImplService<DrugPriceListDao, DrugPriceList> implements DrugPriceListServiceApi {


    @Autowired
    private DrugPriceModifyDao drugPriceModifyDao;

    /**
     * 保存药品调价数据
     * @param drugPriceModifyVo
     * @return
     * @author txb
     * @version 2016-05-18
     */
    @Override

    public String saveDrugPriceModify(DrugCatalogChangeVo drugPriceModifyVo) {

        List<DrugPriceModify> insertDicts = drugPriceModifyVo.getInserted();
        List<DrugPriceModify> updateDicts = drugPriceModifyVo.getUpdated();
        List<DrugPriceModify> deleteDicts = drugPriceModifyVo.getDeleted();

        if (insertDicts != null && insertDicts.size() > 0) {
            for (DrugPriceModify drugPriceModify : insertDicts) {
                drugPriceModify.preInsert();
                drugPriceModifyDao.insert(drugPriceModify);

            }
        }
        if (updateDicts != null && updateDicts.size() > 0) {
            for (DrugPriceModify drugPriceModify : updateDicts) {
                drugPriceModify.preUpdate();
                drugPriceModifyDao.update(drugPriceModify);
            }
        }
        if (deleteDicts != null && deleteDicts.size() > 0) {
            for (DrugPriceModify drugPriceModify : deleteDicts) {
                drugPriceModifyDao.delete(drugPriceModify);
            }
        }
        return "1";
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
        return drugPriceModifyDao.findAllList(drugPriceModify);
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

    /**
     * 通过通知生效日期查询调价记录表
     * @param startDate 调价生效开始日期
     * @param endDate  调价生效结束日期
     * @author txb
     * @return
     */
    @Override
    public List<DrugPriceModify> findModifyListByNoticeEfficientDate(String startDate, String endDate) {
        return drugPriceModifyDao.findModifyListByNoticeEfficientDate(startDate,endDate);
    }

    /**
     * 保存调价记录确认
     * @param drugPriceModifyVo
     * @author txb
     * @return
     */
    @Override

    public String saveModifyConfirm(DrugCatalogChangeVo<DrugPriceModify> drugPriceModifyVo) {


        List<DrugPriceModify> insertDicts = drugPriceModifyVo.getInserted();
        List<DrugPriceModify> updateDicts = drugPriceModifyVo.getUpdated();
        List<DrugPriceModify> deleteDicts = drugPriceModifyVo.getDeleted();
        //无插入记录
//        if (insertDicts != null && insertDicts.size() > 0) {
//            for (DrugPriceModify drugPriceModify : insertDicts) {
//                drugPriceModify.preInsert();
//                drugPriceModifyDao.insert(drugPriceModify);
//
//            }
//        }
        if (updateDicts != null && updateDicts.size() > 0) {
            for (DrugPriceModify drugPriceModify : updateDicts) {
                //修改调价记录表
                drugPriceModify.preUpdate();
                drugPriceModifyDao.update(drugPriceModify);
                //修改价表
                String drugCode = drugPriceModify.getDrugCode();
                String drugSpec = drugPriceModify.getDrugSpec();
                String firmId = drugPriceModify.getFirmId();
                String units = drugPriceModify.getUnits();
                String orgId = drugPriceModify.getOrgId();
                DrugPriceList drugPriceList = dao.selectPriceList(drugCode, drugSpec, firmId, units, orgId).get(0);
                drugPriceList.setStopDate(drugPriceModify.getActualEfficientDate());

                drugPriceList.preUpdate();
                dao.update(drugPriceList);
                //插入价表
                DrugPriceList newDrugPriceList = new DrugPriceList();
                newDrugPriceList.setOrgId(orgId);
                newDrugPriceList.setDrugCode(drugCode);
                newDrugPriceList.setDrugSpec(drugSpec);
                newDrugPriceList.setFirmId(firmId);
                newDrugPriceList.setUnits(units);

                newDrugPriceList.setMinSpec(drugPriceList.getMinSpec());
                newDrugPriceList.setMinUnits(drugPriceList.getMinUnits());
                newDrugPriceList.setAmountPerPackage(drugPriceList.getAmountPerPackage());
                newDrugPriceList.setClassOnInpRcpt(drugPriceList.getClassOnInpRcpt());
                newDrugPriceList.setClassOnMr(drugPriceList.getClassOnMr());
                newDrugPriceList.setClassOnOutpRcpt(drugPriceList.getClassOnOutpRcpt());
                newDrugPriceList.setClassOnReckoning(drugPriceList.getClassOnReckoning());
                newDrugPriceList.setSubjCode(drugPriceList.getSubjCode());
                newDrugPriceList.setGmp(drugPriceList.getGmp());
                newDrugPriceList.setHlimitPrice(drugPriceList.getHlimitPrice());
                newDrugPriceList.setPriceClass(drugPriceList.getPriceClass());
                newDrugPriceList.setPassNo(drugPriceList.getPassNo());

                //调价价表修改数据
                newDrugPriceList.setTradePrice(drugPriceModify.getOriginalTradePrice());
                newDrugPriceList.setRetailPrice(drugPriceModify.getOriginalTradePrice());
                newDrugPriceList.setStartDate(drugPriceModify.getActualEfficientDate());

                newDrugPriceList.preInsert();
                dao.insert(newDrugPriceList);
            }
        }
        if (deleteDicts != null && deleteDicts.size() > 0) {
            for (DrugPriceModify drugPriceModify : deleteDicts) {
                drugPriceModifyDao.delete(drugPriceModify);
            }
        }




        return "1";
    }

}