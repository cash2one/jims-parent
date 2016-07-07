package com.jims.doctor.clinicItem.bo;

import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.dao.OutpTreatRecDao;
import com.jims.clinic.entity.OutpOrders;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TreatmentServiceImpl
 *
 * @author PangQian
 * @date2016/5/11 0011
 */
@Service
@Transactional(readOnly = false)
public class TreatmentBo extends CrudImplService<OutpTreatRecDao, OutpTreatRec>  {
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    private OutpOrdersDao outpOrdersDao;
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;
    /**
     * 处置治疗的查询
     * @param clinicId
     * @return
     */
    public List<OutpTreatRec> findTreatment(String clinicId){
        return   outpTreatRecDao.findTreatment(clinicId);
    }

    /**
     * 保存门诊收费明细  检查治疗医嘱明细  门诊医嘱主记录
     * @param outpTreatRecs OutpTreatRec OutpOrders
     * @return
     */
    public  String saveClinicItem(List<OutpTreatRec> outpTreatRecs,String clinicId){
        clinicId="15001159";
         /*门诊医嘱主记录*/
        OutpOrders outpOrders =new OutpOrders();//门诊医嘱主记录
        outpOrders.setPatientId(clinicId);//clinicId
     /*   outpOrders.setVisitDate(outpOrdersCosts.getVisitDate());
        outpOrders.setVisitNo(outpOrdersCosts.getVisitNo());
        outpOrders.setSerialNo(outpOrdersCosts.getSerialNo());//流水号
        outpOrders.setOrderedBy(outpOrdersCosts.getOrderedByDept());//开单科室(当前科室)
        outpOrders.setDoctor(outpOrdersCosts.getOrderedByDoctor());//开单医生(当前医生)
        outpOrders.setOrderDate(outpOrdersCosts.getCreateDate());//开单日期（当前日期）
        outpOrders.setDoctorNo("李俊山");//医生代码
        outpOrders.setNurse("护士");//当前护士*/
        if (outpOrders.getIsNewRecord()){
            outpOrders.preInsert();
            outpOrdersDao.saveOutpOrders(outpOrders);
        }else{
            outpOrders.preUpdate();
            outpOrdersDao.update(outpOrders);
        }
        if(outpTreatRecs.size()!=0){
            for(int i=0;i<outpTreatRecs.size();i++){
                int no=1;
                OutpOrdersCosts outpOrdersCosts=new OutpOrdersCosts();//门诊收费明细
                OutpTreatRec outpTreatRec = new OutpTreatRec();//检查治疗医嘱明细
                outpTreatRec=outpTreatRecs.get(i);
                //通过clinicId(patientId)拿到visitDate、visitNo  现在是死数据
               /*检查治疗医嘱明细*/

                outpTreatRec.setItemNo(no++);
                outpTreatRec.setItemClass("D");
                /**
                 * 通过itemcode和itemclass查找
                 */
                List<OutpOrdersCosts> outpOrdersCostses=outpTreatRecDao.findSubTreatment(outpTreatRec.getItemCode(), outpTreatRec.getItemName());

                /**
                 * 通过outpOrdersCostses的查询结果规格、单位、code、class查询实收金额
                 */
              /*  outpTreatRec.setCosts(outpOrdersCosts.getCosts());
                outpTreatRec.setCharges(outpOrdersCosts.getCharges());
                outpTreatRec.setChargeIndicator(outpOrdersCosts.getChargeIndicator());*/
                /*************************************************************************************/
              /*如果没有检查或者检验项目的话，（appointNo）申请号和appointItemNo申请明细号不需要填写*/
                           /*此处为治疗项目所以医嘱明细中不需要填写申请号以及申请明细号*/
                if (outpTreatRec.getIsNewRecord()){
                    outpTreatRec.preInsert();
                    outpTreatRecDao.insert(outpTreatRec);
                }else{
                    outpOrders.preUpdate();
                    outpTreatRecDao.update(outpTreatRec);
                }

                //outpTreatRecDao.saveTreatRec(outpTreatRec);
                // billVisitNo、billVisitDate、operatorTrturn、dateTrturn关于退费的现在暂时没做
              /*门诊医生收费明细*/
                if(outpOrdersCostses!=null&&outpOrdersCostses.size()>0){
                  if(outpOrdersCostses.size()>1){//含有明细收费项目
                      for(int j=0;j<outpOrdersCostses.size();j++){
                          OutpOrdersCosts outpOrdersCosts1=outpOrdersCostses.get(i);
                          outpOrdersCosts.setAmount(outpOrdersCosts1.getAmount());
                          outpOrdersCosts.setItemSpec(outpOrdersCosts1.getItemSpec());
                          outpOrdersCosts.setUnits(outpOrdersCosts1.getUnits());
                          /**
                           * 通过outpOrdersCostses的查询结果规格、单位、code、class查询实收金额
                           */
                          outpOrdersCosts.setClassOnRcpt(outpOrdersCosts1.getItemCode());
                         // outpOrdersCosts.setOrderClass(outpOrdersCosts1.getItemClass());//诊疗类别
                          outpOrdersCosts.setItemNo(no++);
                          outpOrdersCosts.setItemClass(outpOrdersCosts1.getItemClass());
                          outpOrdersCosts.setItemCode(outpOrdersCosts1.getItemCode());
                          outpOrdersCosts.setPerformedBy(outpTreatRec.getPerformedBy());
                          outpOrdersCosts.setClassOnRcpt(outpOrdersCosts1.getItemClass());
                          outpOrdersCosts.setWardCode(outpTreatRec.getWardCode());
                          outpOrdersCosts.setMasterId(clinicId);
                          //医嘱号
                          outpOrdersCosts.setPatientId(clinicId);
                          outpOrdersCosts.setOrderNo(no++);
                          //子医嘱号
                      }
                  }else{
                      outpOrdersCosts.setAmount(outpOrdersCostses.get(0).getAmount());
                      outpOrdersCosts.setItemSpec(outpOrdersCostses.get(0).getItemSpec());
                      outpOrdersCosts.setUnits(outpOrdersCostses.get(0).getUnits());
                      /**
                       * 通过outpOrdersCostses的查询结果规格、单位、code、class查询实收金额
                       */
                      outpOrdersCosts.setClassOnRcpt(outpOrdersCostses.get(0).getItemCode());
                      outpOrdersCosts.setOrderClass(outpOrdersCostses.get(0).getItemClass());//诊疗类别
                      outpOrdersCosts.setItemNo(no++);
                      outpOrdersCosts.setItemClass(outpOrdersCostses.get(0).getItemClass());
                      outpOrdersCosts.setItemCode(outpOrdersCostses.get(0).getItemCode());
                      outpOrdersCosts.setPerformedBy(outpTreatRec.getPerformedBy());
                      outpOrdersCosts.setClassOnRcpt(outpOrdersCostses.get(0).getItemClass());
                      outpOrdersCosts.setWardCode(outpTreatRec.getWardCode());
                      outpOrdersCosts.setMasterId(clinicId);
                      //医嘱号
                      outpOrdersCosts.setPatientId(clinicId);
                      outpOrdersCosts.setOrderNo(no++);
                      //子医嘱号
                  }
                }
                outpOrdersCosts.setAmount(outpTreatRec.getAmount());
                outpOrdersCosts.setItemSpec(outpTreatRec.getItemSpec());
                outpOrdersCosts.setUnits(outpTreatRec.getUnits());
                /**
                 * 通过outpOrdersCostses的查询结果规格、单位、code、class查询实收金额
                 */
                outpOrdersCosts.setClassOnRcpt(outpTreatRec.getItemCode());
                outpOrdersCosts.setOrderClass("D");//诊疗类别
                outpOrdersCosts.setItemNo(no++);
                outpOrdersCosts.setItemClass("D");
                outpOrdersCosts.setItemCode(outpTreatRec.getItemCode());
                outpOrdersCosts.setPerformedBy(outpTreatRec.getPerformedBy());
                outpOrdersCosts.setClassOnRcpt(outpTreatRec.getItemClass());
                outpOrdersCosts.setWardCode(outpTreatRec.getWardCode());
                outpOrdersCosts.setMasterId(clinicId);
                //医嘱号
                outpOrdersCosts.setPatientId(clinicId);
                outpOrdersCosts.setOrderNo(no++);
                //录入科室，录入医生为当前医生以及当前医生所在的科室
                if (outpOrdersCosts.getIsNewRecord()){
                    outpOrdersCosts.preInsert();
                    outpOrdersCostsDao.insert(outpOrdersCosts);
                }else{
                    outpOrdersCosts.preUpdate();
                    outpOrdersCostsDao.update(outpOrdersCosts);
                }
                return "1";
            }
        }else{
            return "0";
        }
        return "0";
    }

    /**
     * 处置治疗的删除
     * @param outpTreatRec
     * @return
     * pq
     */
    public int deleteTreat(OutpTreatRec outpTreatRec){
        if(outpTreatRec!=null){
            outpOrdersCostsDao.deleteTreatment(outpTreatRec.getItemCode());
            outpTreatRecDao.delete(outpTreatRec);
            return 1;
        }else{
            return 0;
        }

    }
}
