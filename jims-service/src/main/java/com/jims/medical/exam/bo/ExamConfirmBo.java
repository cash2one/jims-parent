package com.jims.medical.exam.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.doctor.cliniIcnspect.dao.ExamAppointsDao;
import com.jims.doctor.cliniIcnspect.dao.ExamItemsDao;
import com.jims.drugPresc.dao.InpBillDetailDao;
import com.jims.drugPresc.entity.InpBillDetail;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;
import com.jims.exam.entity.ExamMaster;
import com.jims.medical.exam.dao.ExamMasterDao;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.vo.PriceListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 * 检查确认 Bo
 * @author zhaoning
 */
@Service
@Transactional(readOnly = false)
public class ExamConfirmBo extends CrudImplService<ExamMasterDao,ExamMaster> {
    @Autowired
    private ExamMasterDao examMasterDao;
    @Autowired
    private ExamAppointsDao examAppointsDao;
    @Autowired
    private ExamItemsDao examItemsDao;
    @Autowired
    private PriceListDao priceListDao;
    @Autowired
    private InpBillDetailDao inpBillDetailDao;
    /**
     * 检查确认列表
     * @param performedBy 执行科室
     * @return
     * @author zhaoning
     */
    public List<ExamAppoints> getExamAppointses(String performedBy,String outOrIn){
     return examMasterDao.getExamAppointses(performedBy,outOrIn);
    }

    /**
     * 检查确认
     * @param examAppoints
     * @return
     * @author zhaoning
     */
    public String confrimExam(ExamAppoints examAppoints) throws Exception{
        int num=0;
        Integer regPrnFlag=examAppoints.getRegPrnFlag();
        if(regPrnFlag!=null && regPrnFlag==1){//已经确认
          num=2;
        }else if(examAppoints!=null && examAppoints.getId()!=null){
            //更新 examAppoints
            examAppointsDao.updateAppoints(examAppoints.getId());
            String inOrOut=examAppoints.getInOrOut();//门诊 or 住院
            if(inOrOut!=null && inOrOut.equals("0")){//门诊
               num= confirmClinic(examAppoints);
            }else if(inOrOut!=null && inOrOut.equals("1")){//住院
               num=confirmInHos(examAppoints);
            }
        }
        return num+"";
    }

    /**
     * 门诊检查确认
     * @param examAppoints
     * @return
     * @author zhaoning
     */
    public int  confirmClinic(ExamAppoints examAppoints){
        int num=0;
        ExamMaster examMaster = new ExamMaster();
        examMaster.setExamNo(examAppoints.getExamNo());
        examMaster.setLocalIdClass(examAppoints.getLocalIdClass());
        examMaster.setPatientId(examAppoints.getPatientId());
        examMaster.setName(examAppoints.getName());
        examMaster.setSex(examAppoints.getSex());
        examMaster.setDateOfBirth(examAppoints.getDateOfBirth());
        examMaster.setExamClass(examAppoints.getExamClass());
        examMaster.setExamSubClass(examAppoints.getExamSubClass());
        examMaster.setClinDiag(examAppoints.getClinDiag());
        examMaster.setPhysSign(examAppoints.getPhysSign());
        examMaster.setRelevantDiag(examAppoints.getRelevantDiag());
        examMaster.setRelevantLabTest(examAppoints.getRelevantLabTest());
        examMaster.setClinDiag(examAppoints.getClinDiag());
        examMaster.setExamMode(examAppoints.getExamMode());
        examMaster.setExamGroup(examAppoints.getExamGroup());
        examMaster.setDevice(examAppoints.getDevice());
        examMaster.setPerformedBy(examAppoints.getPerformedBy());
        examMaster.setPatientSource(examAppoints.getPatientSource());
        examMaster.setFacility(examAppoints.getFacility());
        examMaster.setReqDateTime(examAppoints.getReqDateTime());
        examMaster.setReqDept(examAppoints.getReqDept());
        examMaster.setReqMemo(examAppoints.getReqMemo());
        examMaster.setScheduledDateTime(examAppoints.getScheduledDate());
        examMaster.setNotice(examAppoints.getNotice());
        examMaster.setCosts(examAppoints.getCosts());
        examMaster.setCharges(examAppoints.getCharges());
        examMaster.setChargeType(examAppoints.getChargeType());
        examMaster.setIdentity(examAppoints.getIdentity());
        examMaster.setResultStatus("1");
        if (examMaster.getIsNewRecord()){
            examMaster.preInsert();
            num=examMasterDao.insert(examMaster);
        }else{
            examMaster.preUpdate();
            num=examMasterDao.update(examMaster);
        }
        return num;
    }

    /**
     * 检查确认 --住院
     * @param examAppoints
     * @return
     */
    public int confirmInHos(ExamAppoints examAppoints) throws Exception{
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         int num=0;
         List<ExamItems> list=examItemsDao.getItemList(examAppoints.getId());//检查主记录 对应的检查项目
         String orgId="";
        for(int i=0;i<list.size();i++){
            //查询检查项目 对应的 计价项目
            List<PriceListVo>  listPriceListVo=priceListDao.listByClinicItemCodeAndOrgId(orgId, list.get(i).getExamItemCode());
            for(int j=0;j<listPriceListVo.size();j++){
                PriceListVo priceListVo=listPriceListVo.get(j);
                InpBillDetail inpBillDetail=new InpBillDetail();//住院病人 费用明细
                inpBillDetail.setPatientId(examAppoints.getPatientId());
                inpBillDetail.setVisitId(examAppoints.getVisitId());
                //inpBillDetail.setItemNo(1);//项目序号
                inpBillDetail.setItemClass(priceListVo.getItemClass());
                inpBillDetail.setItemName(priceListVo.getItemName());
                inpBillDetail.setItemCode(priceListVo.getItemCode());
                inpBillDetail.setItemSpec(priceListVo.getItemSpec());
                //inpBillDetail.setAmount(1);//数量
                inpBillDetail.setUnits(priceListVo.getUnits());
                inpBillDetail.setOrderedBy(examAppoints.getReqDept());//申请科室==开单科室
                inpBillDetail.setOrderDoctor(examAppoints.getReqPhysician());//申请医生==申请科室
                inpBillDetail.setPerformedBy(priceListVo.getPerformedBy());//执行科室
                inpBillDetail.setCosts(priceListVo.getPrice());
                inpBillDetail.setCharges(priceListVo.getPrice());
                inpBillDetail.setBillingDateTime(format.parse(DateUtils.getDateTime()));
                inpBillDetail.setOperatorNo("当前登录人");//计价会员
                inpBillDetail.setRcptNo("");//对应的结算 序号 （待）
                inpBillDetail.setClassOnInpRcpt(priceListVo.getClassOnInpRcpt());
                inpBillDetail.setSubjCode(priceListVo.getSubjCode());
                inpBillDetail.setClassOnMr(priceListVo.getClassOnMr());
                inpBillDetail.setItemPrice(priceListVo.getPrice());//项目标准单价
                inpBillDetail.setPriceQuotiety(0.0);//收费系数(待确定)
                inpBillDetail.setClassOnReckoning("");//核算项目分类（待确定）
                inpBillDetail.setOrderGroup("");//开单医生核算组
                inpBillDetail.setOrderDoctor(examAppoints.getReqPhysician());//开单医生姓名
                inpBillDetail.setPerformGroup("");//当前登录医生所在核算组
                inpBillDetail.setPerformDoctor("");//当前登录 医生姓名
                inpBillDetail.setConveyDate(format.parse(DateUtils.getDateTime()));//转储时间（待确定 是否是 当前时间）
                inpBillDetail.setDoctorUser("");//医生代码 （待确定，是否是当前登录医生的代码）
                inpBillDetail.setTransflag("0");//医保传送标志
                inpBillDetail.setMemo(priceListVo.getMemo());
                if (inpBillDetail.getIsNewRecord()){
                    inpBillDetail.preInsert();
                    num=inpBillDetailDao.insert(inpBillDetail);
                }else{
                    inpBillDetail.preUpdate();
                    num=inpBillDetailDao.update(inpBillDetail);
                }
            }
        }

        return num;
    }
}
