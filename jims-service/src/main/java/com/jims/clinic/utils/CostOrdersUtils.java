/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.utils;



import com.jims.clinic.dao.*;
import com.jims.clinic.entity.*;
import com.jims.common.utils.IdGen;
import com.jims.common.utils.SpringContextHolder;
import com.jims.common.utils.UserUtils;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.entity.ClinicItemClassDict;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.SysUser;
import com.jims.sys.entity.User;
import com.jims.sys.vo.PriceListVo;

import java.util.*;

/**
 * 门诊收费医嘱工具类
 *
 * @author zhangyao
 * @version 2016-5-11
 */
public class CostOrdersUtils {

    //就诊记录
    private static ClinicMasterDao clinicMasterDao = SpringContextHolder.getBean(ClinicMasterDao.class);
    //收费医嘱明细
    private static OutpOrdersCostsDao outpOrdersCostsDao = SpringContextHolder.getBean(OutpOrdersCostsDao.class);
    //门诊医嘱记录
    private static OutpOrdersDao outpOrdersDao = SpringContextHolder.getBean(OutpOrdersDao.class);
    //检查治疗医嘱明细
    private static OutpTreatRecDao outpTreatRecDao = SpringContextHolder.getBean(OutpTreatRecDao.class);
    //项目价格
    private static PriceListDao priceListDao = SpringContextHolder.getBean(PriceListDao.class);
    //诊疗项目
    private static ClinicItemDictDao clinicItemDictDao = SpringContextHolder.getBean(ClinicItemDictDao.class);

    /**
     * 保存收费医嘱
     * @param clinicId 就诊ID
     * @param itemDictList 诊疗项目list
     * @return
     */
    public static void save(String clinicId ,List<ClinicItemDict> itemDictList) {
        ClinicMaster clinicMaster=clinicMasterDao.get(clinicId);
        OrgStaff orgStaff=new OrgStaff();
       // SysUser user=new SysUser();
        String serialNo= IdGen.uuid();
        String orgId=clinicMaster.getOrgId();
        String patientId=clinicMaster.getPatientId();
        OutpOrders outpOrders=new OutpOrders();
        outpOrders.setClinicId(clinicId);
        outpOrders.setPatientId(patientId);
        outpOrders.setDoctor(orgStaff.getPersionId());
        outpOrders.setVisitDate(clinicMaster.getVisitDate());
        outpOrders.setSerialNo(serialNo);
        outpOrders.preInsert();
        outpOrdersDao.saveOutpOrders(outpOrders);
        Integer orderNo=outpOrdersCostsDao.getMaxOrderNo(clinicId,orgId);
        if(orderNo==null){
            orderNo=1;
        }
        for (int i = 0; i <itemDictList.size() ; i++) {
            ClinicItemDict clinicItemDict=itemDictList.get(i);
            clinicItemDict.setOrgId(orgId);
             List<ClinicItemDict> clinicItemDictList= clinicItemDictDao.findExisted(clinicItemDict);
            clinicItemDict=clinicItemDictList.get(0);
            OutpTreatRec outpTreatRec=new OutpTreatRec();
            outpTreatRec.setVisitDate(clinicMaster.getVisitDate());
            outpTreatRec.setClinicId(clinicId);
            outpTreatRec.setSerialNo(serialNo);
            outpTreatRec.setItemClass(clinicItemDict.getItemClass());
            outpTreatRec.setItemCode(clinicItemDict.getItemCode());
            outpTreatRec.setItemName(clinicItemDict.getItemName());
            outpTreatRec.setAmount(1.00);
            //执行科室
            outpTreatRec.setPerformedBy(clinicItemDict.getExpand3());
            outpTreatRec.setChargeIndicator(0);
            Double price=0.00;
            List<PriceListVo>  listPriceListVo=new ArrayList<PriceListVo>();
            for (int j = 0; j < listPriceListVo.size(); j++) {
                PriceListVo priceListVo=listPriceListVo.get(j);
                OutpOrdersCosts outpOrdersCosts=new OutpOrdersCosts();
                outpOrdersCosts.setOrgId(orgId);
                outpOrdersCosts.setClinicId(clinicId);
                outpOrdersCosts.setPatientId(patientId);
                outpOrdersCosts.setVisitDate(clinicMaster.getVisitDate());
                outpOrdersCosts.setSerialNo(serialNo);
                outpOrdersCosts.setItemCode(priceListVo.getItemCode());
                outpOrdersCosts.setItemName(priceListVo.getItemName());
                outpOrdersCosts.setItemClass(priceListVo.getItemClass());
                outpOrdersCosts.setOrderNo(orderNo);
                outpOrdersCosts.setOrderSubNo(1);
                outpOrdersCosts.setItemSpec(priceListVo.getItemSpec());
                outpOrdersCosts.setUnits(priceListVo.getUnits());
                outpOrdersCosts.setAmount(1.00);
                outpOrdersCosts.setOrderedByDept(orgStaff.getDeptId());
                outpOrdersCosts.setOrderedByDoctor(orgStaff.getPersionId());
                outpOrdersCosts.setPerformedBy(priceListVo.getPerformedBy());
                outpOrdersCosts.setCosts(priceListVo.getPrice());
                price+=priceListVo.getPrice();
                outpOrdersCosts.setCharges(priceListVo.getPrice());
                outpOrdersCosts.setChargeIndicator(0);
                orderNo++;
                outpOrdersCostsDao.saveOrdersCosts(outpOrdersCosts);
            }
            outpTreatRec.setCharges(price);
            outpTreatRec.setCosts(price);
            outpTreatRecDao.saveTreatRec(outpTreatRec);
        }
    }
}
