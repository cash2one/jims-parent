/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.OutpPrescServiceApi;
import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.dao.OutpPrescDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.clinic.entity.OutpOrders;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpPresc;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.IdGen;
import com.jims.common.utils.StringUtils;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.vo.PriceListVo;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

/**
 * 处方医嘱明细记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")

public class OutpPrescServiceImpl extends CrudImplService<OutpPrescDao, OutpPresc> implements OutpPrescServiceApi{

    @Autowired
    private OutpOrdersDao outpOrdersDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private PriceListDao priceListDao;

    /**
     * @param         outpPresc    传递参数
     * @return java.lang.String    返回类型
     * @throws
     * @Title: save
     * @Description: (保存处方，医嘱，收费明细)
     * @author CTQ
     * @date 2016/5/9
     */
    @Override
    public String save(OutpPresc outpPresc){
        String num = "";
        try {
            if(outpPresc!=null){
                //根据病人就诊ID，查询病人就诊记录信息
                ClinicMaster clinicMaster = clinicMasterDao.get(outpPresc.getClinicId());
                String serialNo = IdGen.uuid();
                OutpOrders oo = new OutpOrders();
                List<OutpPresc> lists = outpPresc.getList();
                List<OutpOrdersCosts> ordersCostsesList = new ArrayList<OutpOrdersCosts>();
               if(lists!=null && lists.size()>0){
                   for (OutpPresc op : lists) {
                       op.setChargeIndicator(0); // 未收费
                       op.setVisitNo(clinicMaster.getVisitNo());
                       op.setVisitDate(clinicMaster.getVisitDate());
                       op.setClinicId(clinicMaster.getId());
                       op.setItemClass(outpPresc.getItemClass());
                       op.setPrescAttr(outpPresc.getPrescAttr());
                       op.setCosts(op.getCharges()/op.getAmount());
                       op.setItemNo(1);
                       if(op.getId()!=null && !op.getId().equals("")){
                           num = String.valueOf(dao.update(op));
                           ordersCostsesList.add(makeOutpOrderCosts(op, clinicMaster));
                       }else{
                           Integer prescno = dao.getMaxPrescNo(clinicMaster.getOrgId());
                           /**判断机构下该处方号是否存在，如果存在表示为存在处方增加药品，如果不存在则表示该处方也是新开处方**/
                           Integer flag = dao.searchPrescNoIfExist(/*clinicMaster.getOrgId()*/"","", op.getPrescNo());
                           if(flag<=0 || flag==null){
                               op.setPrescNo(prescno!=null?prescno:1);
                           }else{
                              /**如果机构下该处方号存在，则判断是否是当前病人的处方号，如果不是，则重新创建**/
                               flag = dao.searchPrescNoIfExist("",clinicMaster.getId(),op.getPrescNo());
                               if(flag<=0 || flag==null){
                                   op.setPrescNo(prescno!=null?prescno:1);
                               }
                           }
                           op.setSerialNo(serialNo);
                           op.setProvidedIndicator(0);//自备标记
                           op.preInsert();
                           num = String.valueOf(dao.insert(op));
                           ordersCostsesList.add(makeOutpOrderCosts(op, clinicMaster));
                       }
                   }
               }
                //保存门诊医嘱信息
                oo.setClinicId(clinicMaster.getId());
                oo.setVisitDate(clinicMaster.getVisitDate());
                oo.setVisitNo(clinicMaster.getVisitNo());
                oo.setSerialNo(outpPresc.getSerialNo());
                oo.setOrderedBy(clinicMaster.getVisitDept());
                oo.setDoctor("李俊山");
                oo.setClinicNo(DateFormatUtils.format(clinicMaster.getVisitDate(), "yyyyMMdd") + oo.getVisitNo());
                oo.setDoctorNo(clinicMaster.getDoctor());
                oo.preInsert();
                outpOrdersDao.insert(oo);
                //保存门诊处方药品价目表信息
                saveOutpOrdersCosts(ordersCostsesList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return String.valueOf(num);
    }
    /**
     * 根据病人诊断记录查询处方主记录
     * @param orgId,clinicId
     * updateBy CTQ
     * @return
     */
    @Override
    public List<OutpPresc> getOutpPresc(String orgId,String clinicId) {
        return dao.getOutpPresc(orgId,clinicId);
    }


    /**
     * @param   ordersCostsesList          传递参数
     * @return void    返回类型
     * @throws
     * @Title: saveOutpOrdersCosts
     * @Description: (保存门诊处方药品收费明细)
     * @author CTQ
     * @date 2016/5/7
     */
    public void saveOutpOrdersCosts(List<OutpOrdersCosts> ordersCostsesList){
        try{
            if(ordersCostsesList!=null&&ordersCostsesList.size()>0){
                for(OutpOrdersCosts outpOrdersCosts : ordersCostsesList) {
                    outpOrdersCosts.preInsert();
                    outpOrdersCostsDao.insert(outpOrdersCosts);
                }
            }
        }catch (Exception e){
           e.printStackTrace();
        }
    }

    /**
     * @param      outpPresc       传递参数
     * @return com.jims.clinic.entity.OutpOrdersCosts    返回类型
     * @throws
     * @Title: makeOutpOrderCosts
     * @Description: (设置收费项目数据)
     * @author CTQ
     * @date 2016/5/7
     */
    public OutpOrdersCosts makeOutpOrderCosts(OutpPresc outpPresc,ClinicMaster clinicMaster){
        OrgStaff orgStaff=new OrgStaff();
            OutpOrdersCosts outpOrdersCosts = new OutpOrdersCosts();
            outpOrdersCosts.setMasterId(outpPresc.getId());
            outpOrdersCosts.setClinicId(clinicMaster.getId());
            outpOrdersCosts.setOrgId(clinicMaster.getOrgId());
            outpOrdersCosts.setOrderClass(outpPresc.getItemClass());//诊疗项目类别
            outpOrdersCosts.setPatientId(clinicMaster.getPatientId());
            outpOrdersCosts.setSerialNo(outpPresc.getSerialNo());
            outpOrdersCosts.setVisitDate(clinicMaster.getVisitDate());
            outpOrdersCosts.setVisitNo(clinicMaster.getVisitNo());
            outpOrdersCosts.setClinicNo(DateFormatUtils.format(clinicMaster.getVisitDate(), "yyyyMMdd") + clinicMaster.getVisitNo());
            outpOrdersCosts.setOrderNo(outpPresc.getOrderNo());
            outpOrdersCosts.setOrderSubNo(outpPresc.getSubOrderNo());
            outpOrdersCosts.setItemNo(outpPresc.getItemNo());
            outpOrdersCosts.setItemClass(outpPresc.getItemClass());//收费项目类别
            outpOrdersCosts.setItemName(outpPresc.getDrugName());
            outpOrdersCosts.setItemCode(outpPresc.getDrugCode());
            outpOrdersCosts.setItemSpec(outpPresc.getDrugSpec());
            outpOrdersCosts.setUnits(outpPresc.getUnits());
            outpOrdersCosts.setRepetition(outpPresc.getRepetition());
            outpOrdersCosts.setAmount(outpPresc.getAmount());
            outpOrdersCosts.setOrderedByDept(""); // 当前医师坐诊科室
            outpOrdersCosts.setOrderedByDoctor("");
            outpOrdersCosts.setPerformedBy(outpPresc.getPerformedBy());
            outpOrdersCosts.setCosts(outpPresc.getCosts());
            outpOrdersCosts.setCharges(outpPresc.getCharges());
            outpOrdersCosts.setSubjCode(outpPresc.getSubjCode());
            outpOrdersCosts.setOrderedByDept(orgStaff.getDeptId());
            outpOrdersCosts.setOrderedByDoctor(orgStaff.getPersionId());

        return  outpOrdersCosts;
    }
    /**
     * @param        ids     传递参数
     * @return java.lang.String    返回类型
     * @throws
     * @Title: deletePresc
     * @Description: (删除处方时，删除相应的计价收费项目)
     * @author CTQ
     * @date 2016/5/9
     */
    @Override
    public String deletePresc(String ids){
        int num = 0;
        if(ids!=null && !ids.equals("")){
            if (ids.contains(",")){
                String [] idArr = ids.split(",");
                for(String str : idArr){
                    OutpOrdersCosts opc = new OutpOrdersCosts();
                    opc.setMasterId(str);
                    //删除计价项目
                    outpOrdersCostsDao.removeByMasterId(opc);
                    //删除处方
                    num = dao.delete(str);
                }
            }else {
                OutpOrdersCosts opc = new OutpOrdersCosts();
                opc.setMasterId(ids);
                //删除计价项目
                outpOrdersCostsDao.removeByMasterId(opc);
                num = dao.delete(ids);
            }
        }
        return String.valueOf(num);
    }
    /**
     * @param       outpPresc      传递参数
     * @return java.util.List<OutpPresc>    返回类型
     * @throws
     * @Title: findListByParams
     * @Description: (根据条件查询处方相关信息)
     * @author CTQ
     * @date 2016/5/10
     */
    @Override
    public List<OutpPresc> findListByParams(OutpPresc outpPresc){
        return dao.findListByParams(outpPresc);
    }
}