/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.prescription.bo;

import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.clinic.entity.OutpOrders;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.common.persistence.BaseEntity;
import com.jims.common.utils.NumberUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.prescription.entity.OutpPresc;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.IdGen;
import com.jims.common.web.impl.BaseDto;
import com.jims.doctor.prescription.dao.OutpPrescDao;
import com.jims.sys.dao.AdministrationDictDao;
import com.jims.sys.entity.OrgStaff;
import oracle.jdbc.util.Login;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 处方医嘱明细记录Bo
 *
 * @author zhangyao
 * @version 2016-07-5
 */
@Service
@Transactional(readOnly = false)
public class OutpPrescBo extends CrudImplService<OutpPrescDao, OutpPresc> {

    @Autowired
    private OutpOrdersDao outpOrdersDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private AdministrationDictDao administrationDictDao;

    /**
     * @param outpPresc 传递参数
     * @return java.lang.String    返回类型
     * @throws
     * @Title: save
     * @Description: (保存处方，医嘱，收费明细)
     * @author CTQ
     * @date 2016/5/9
     */
    public String save(OutpPresc outpPresc, LoginInfo loginInfo) {
        String num = "";
        String serialNo = "";
        if (outpPresc != null) {
            //根据病人就诊ID，查询病人就诊记录信息
            ClinicMaster clinicMaster = clinicMasterDao.get(outpPresc.getClinicId());
            if (outpPresc.getSerialNo() != null && !"".equals(outpPresc)) {
                serialNo = outpPresc.getSerialNo();
            } else {
                serialNo = IdGen.uuid();
            }
            OutpOrders oo = new OutpOrders();

            List<OutpPresc> lists = outpPresc.getList();
            List<OutpOrdersCosts> ordersCostsesList = new ArrayList<OutpOrdersCosts>();
            if (lists != null && lists.size() > 0) {
                for (int i = 0; i < lists.size(); i++) {
                    OutpPresc op = lists.get(i);
                    op.setChargeIndicator(0); // 未收费
                    op.setVisitNo(clinicMaster.getVisitNo());
                    op.setVisitDate(clinicMaster.getVisitDate());
                    op.setClinicId(clinicMaster.getId());
                    op.setItemClass(outpPresc.getItemClass());
                    op.setPrescAttr(outpPresc.getPrescAttr());
                    op.setGetdrugFlag(1);//未取药
                    op.setAbidance(1);
                    op.setOrgId(loginInfo.getOrgId());
                    op.setPerformedBy(outpPresc.getItemClass());//执行诊室
                    op.setPatientId(clinicMaster.getPatientId());
                    if (op.getItemClass() != null && "B".equals(op.getItemClass())) {//中药
                        if (op.getAmount() != null && !"".equals(op.getAmount()) && op.getAmount() != 0) {//中药数量>0
                            if (op.getRepetition() != null && !"".equals(op.getRepetition()) && op.getRepetition() != 0) {//剂数>0
                                op.setCosts(op.getCharges() * op.getAmount() * op.getRepetition());//中药单价*中药数量*中药剂数为总额
                            } else {
                                op.setCosts(op.getCharges() * op.getAmount());
                            }
                        } else {
                            op.setCosts(op.getCharges());
                        }
                    } else {//西药
                        if (op.getAmount() != null && !"".equals(op.getAmount()) && op.getAmount() != 0) {
                            op.setCosts(op.getCharges() * op.getAmount());
                        } else {
                            op.setCosts(op.getCharges());
                        }
                    }
                    op.setCharges(op.getCosts());
                    op.setItemNo(i + 1);
                    op.setSerialNo(serialNo);
                    if (op.getId() != null && !op.getId().equals("")) {
                        num = String.valueOf(dao.update(op));
                        ordersCostsesList.add(makeOutpOrderCosts(op, clinicMaster, loginInfo));
                    } else {
                        op.setProvidedIndicator(0);//自备标记
                        op.preInsert();
                        num = String.valueOf(dao.insert(op));
                        ordersCostsesList.add(makeOutpOrderCosts(op, clinicMaster, loginInfo));
                    }
                    if (op.getSubOrderNo().equals(op.getOrderNo())) {//主医嘱，由于子处方用药途径与主处方用药途径只收一次途径项目费用
                        if (op.getAdministration() != null && !"".equals(op.getAdministration())) {
                            BaseDto baseDto = administrationDictDao.findByParams(op.getAdministration(), loginInfo.getOrgId());
                            if (baseDto != null && baseDto.get("price").toString() != null && !"".equals(baseDto.get("price").toString())) {
                                OutpOrdersCosts outpOrdersCosts = new OutpOrdersCosts();
                                outpOrdersCosts.setMasterId(outpPresc.getId());
                                outpOrdersCosts.setClinicId(clinicMaster.getId());
                                outpOrdersCosts.setOrgId(clinicMaster.getOrgId());
                                outpOrdersCosts.setOrderClass(outpPresc.getItemClass());//诊疗项目类别
                                outpOrdersCosts.setPatientId(clinicMaster.getPatientId());
                                outpOrdersCosts.setSerialNo(serialNo);
                                outpOrdersCosts.setVisitDate(clinicMaster.getVisitDate());
                                outpOrdersCosts.setVisitNo(clinicMaster.getVisitNo());
                                outpOrdersCosts.setClinicNo(DateFormatUtils.format(clinicMaster.getVisitDate(), "yyyyMMdd") + clinicMaster.getVisitNo());
                                outpOrdersCosts.setOrderNo(outpPresc.getOrderNo());
                                outpOrdersCosts.setOrderSubNo(outpPresc.getSubOrderNo());
                                outpOrdersCosts.setItemNo(outpPresc.getItemNo());
                                outpOrdersCosts.setItemClass(baseDto.get("charge_item_class").toString());//收费项目类别
                                outpOrdersCosts.setItemName(baseDto.get("item_name").toString());
                                outpOrdersCosts.setItemCode(baseDto.get("item_code").toString());
                                outpOrdersCosts.setItemSpec(baseDto.get("item_spec").toString());
                                outpOrdersCosts.setUnits(baseDto.get("units").toString());
                                outpOrdersCosts.setRepetition(outpPresc.getRepetition());
                                outpOrdersCosts.setAmount(outpPresc.getAmount());
                                outpOrdersCosts.setOrderedByDept(loginInfo.getDeptId()); // 当前医师坐诊科室
                                outpOrdersCosts.setOrderedByDoctor(loginInfo.getPersionId());
                                outpOrdersCosts.setPerformedBy(outpPresc.getItemClass());//执行诊室,开药的话是药房，检查检验则是检查检验科室
                                outpOrdersCosts.setCosts(outpPresc.getCosts() * outpPresc.getAmount());
                                outpOrdersCosts.setCharges(outpPresc.getCharges() * outpPresc.getAmount());
                                outpOrdersCosts.setSubjCode(outpPresc.getSubjCode());
                                outpOrdersCosts.setChargeIndicator(BaseEntity.LAB_BILLINGINDICATOR_APPLY);
                                ordersCostsesList.add(outpOrdersCosts);
                            }
                        }
                    }
                }

            }
            //根据唯一流水号删除ORDER
            outpOrdersDao.deleteOutpOrders(serialNo);
            //保存门诊医嘱信息
            oo.setClinicId(clinicMaster.getId());
            oo.setVisitDate(clinicMaster.getVisitDate());
            oo.setPatientId(clinicMaster.getPatientId());
            oo.setVisitNo(clinicMaster.getVisitNo());
            oo.setSerialNo(serialNo);
            oo.setOrderedBy(loginInfo.getDeptId());//开单科室
            oo.setDoctor(loginInfo.getUserName());//开单医生
            oo.setClinicNo(DateFormatUtils.format(clinicMaster.getVisitDate(), "yyyyMMdd") + oo.getVisitNo());
            oo.setDoctorNo(loginInfo.getPersionId());//医生编号
            oo.setOrgId(loginInfo.getOrgId());
            oo.preInsert();
            outpOrdersDao.insert(oo);
            //根据流水号删除ordercosts
            outpOrdersCostsDao.deleteOutpOrdersCosts(serialNo);
            //保存门诊处方药品价目表信息
            saveOutpOrdersCosts(ordersCostsesList);
        }
        return String.valueOf(num);
    }

    /**
     * 根据病人诊断记录查询处方主记录
     *
     * @param orgId,clinicId updateBy CTQ
     * @return
     */
    public List<OutpPresc> getOutpPresc(String orgId, String clinicId) {
        return dao.getOutpPresc(orgId, clinicId);
    }


    /**
     * @param ordersCostsesList 传递参数
     * @return void    返回类型
     * @throws
     * @Title: saveOutpOrdersCosts
     * @Description: (保存门诊处方药品收费明细)
     * @author CTQ
     * @date 2016/5/7
     */
    public void saveOutpOrdersCosts(List<OutpOrdersCosts> ordersCostsesList) {
        try {
            if (ordersCostsesList != null && ordersCostsesList.size() > 0) {
                for(int i = 0;i< ordersCostsesList.size();i++){
                    OutpOrdersCosts outpOrdersCosts = new OutpOrdersCosts();
                    outpOrdersCosts.setItemNo(i+1);
                    outpOrdersCosts.preInsert();
                    outpOrdersCostsDao.insert(outpOrdersCosts);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param outpPresc 传递参数
     * @return com.jims.clinic.entity.OutpOrdersCosts    返回类型
     * @throws
     * @Title: makeOutpOrderCosts
     * @Description: (设置收费项目数据)
     * @author CTQ
     * @date 2016/5/7
     */
    public OutpOrdersCosts makeOutpOrderCosts(OutpPresc outpPresc, ClinicMaster clinicMaster, LoginInfo loginInfo) {
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
        outpOrdersCosts.setOrderedByDept(loginInfo.getDeptId()); // 当前医师坐诊科室
        outpOrdersCosts.setOrderedByDoctor(loginInfo.getPersionId());
        outpOrdersCosts.setPerformedBy(outpPresc.getItemClass());//执行诊室，开药的话是药房，检查检验则是检查检验科室
        outpOrdersCosts.setCosts(outpPresc.getCosts());
        outpOrdersCosts.setCharges(outpPresc.getCharges());
        outpOrdersCosts.setSubjCode(outpPresc.getSubjCode());
        outpOrdersCosts.setChargeIndicator(BaseEntity.LAB_BILLINGINDICATOR_APPLY);
        return outpOrdersCosts;
    }

    /**
     * @param ids 传递参数
     * @return java.lang.String    返回类型
     * @throws
     * @Title: deletePresc
     * @Description: (删除处方时，删除相应的计价收费项目)
     * @author CTQ
     * @date 2016/5/9
     */
    public String deletePresc(String ids) {
        int num = 0;
        if (ids != null && !ids.equals("")) {
            if (ids.contains(",")) {
                String[] idArr = ids.split(",");
                for (String str : idArr) {
                    OutpOrdersCosts opc = new OutpOrdersCosts();
                    opc.setMasterId(str);
                    //删除计价项目
                    outpOrdersCostsDao.removeByMasterId(opc);
                    //删除处方
                    num = dao.delete(str);
                }
            } else {
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
     * @param outpPresc 传递参数
     * @return java.util.List<OutpPresc>    返回类型
     * @throws
     * @Title: findListByParams
     * @Description: (根据条件查询处方相关信息)
     * @author CTQ
     * @date 2016/5/10
     */
    public List<OutpPresc> findListByParams(OutpPresc outpPresc) {
        return dao.findListByParams(outpPresc);
    }

    /**
     * 根据处方号删除处方
     *
     * @param outpPresc
     * @return
     * @author CTQ
     */
    public String delByPrescNo(OutpPresc outpPresc) {
        List<OutpPresc> list = new ArrayList<OutpPresc>();
        list = dao.findListByParams(outpPresc);
        if (list != null) {
            for (OutpPresc op : list) {
                OutpOrdersCosts opc = new OutpOrdersCosts();
                opc.setMasterId(op.getId());
                //删除计价项目
                outpOrdersCostsDao.removeByMasterId(opc);
            }
        }
        return String.valueOf(dao.delByPrescNo(outpPresc));
    }
}