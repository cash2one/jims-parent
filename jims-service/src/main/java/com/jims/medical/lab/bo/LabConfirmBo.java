package com.jims.medical.lab.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.doctor.lab.dao.LabTestItemsDao;
import com.jims.doctor.lab.dao.LabTestMasterDao;
import com.jims.drugPresc.dao.InpBillDetailDao;
import com.jims.drugPresc.entity.InpBillDetail;
import com.jims.lab.entity.LabTestItems;
import com.jims.lab.entity.LabTestMaster;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.vo.PriceListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 * 检验确认 bo
 * @author zhaoning
 */
@Service
@Transactional(readOnly = false)
public class LabConfirmBo extends CrudImplService<LabTestMasterDao,LabTestMaster> {
    @Autowired
    private LabTestMasterDao labTestMasterDao;
    @Autowired
    private LabTestItemsDao labTestItemsDao;
    @Autowired
    private PriceListDao priceListDao;
    @Autowired
    private InpBillDetailDao inpBillDetailDao;
    /**
     * 根据当前登录人所在科室，查询检验主记录
     * @param performedBy
     * @return
     * @author zhaoning
     */
    public List<LabTestMaster> getLabMaster(String performedBy,String inOrOut){
     return labTestMasterDao.getLabMaster(performedBy,inOrOut);
    }

    /**
     * 检验确认
     * @param labTestMaster
     * @return
     * @author zhaoning
     */
    public String confirmLab(LabTestMaster labTestMaster)throws Exception{

        String code="";
        if(labTestMaster!=null && labTestMaster.getId()!=null){
            if(labTestMaster.getStatus()!=null && labTestMaster.getStatus().equals("1")){//已经确认
                code="2";
            }else {//待确认
                String id=labTestMaster.getId();
                labTestMasterDao.updateLabMaster(id);
                if (labTestMaster.getInOrOutFlag() != null && labTestMaster.getInOrOutFlag().equals("1")) {//住院 检验
                    confirmLabInHos(labTestMaster);
                }
                code="1";
            }

        }
        return code;
    }

    /**
     * 检验确认 住院
     * @param labTestMaster
     * @return
     * @author zhaoning
     */
    public void confirmLabInHos(LabTestMaster labTestMaster) throws Exception{
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orgId="";
            List<LabTestItems> list = labTestItemsDao.getItemName(labTestMaster.getId());
            for (int i = 0; i < list.size(); i++) {
                List<PriceListVo> listPriceListVo = priceListDao.listByClinicItemCodeAndOrgId(orgId, list.get(i).getItemCode());
                for (int j = 0; j < listPriceListVo.size(); j++) {
                    PriceListVo priceListVo = listPriceListVo.get(j);
                    InpBillDetail inpBillDetail = new InpBillDetail();//住院病人 费用明细
                    inpBillDetail.setPatientId(labTestMaster.getPatientId());
                    inpBillDetail.setVisitId(labTestMaster.getVisitId());
                    //inpBillDetail.setItemNo(1);//项目序号
                    inpBillDetail.setItemClass(priceListVo.getItemClass());
                    inpBillDetail.setItemName(priceListVo.getItemName());
                    inpBillDetail.setItemCode(priceListVo.getItemCode());
                    inpBillDetail.setItemSpec(priceListVo.getItemSpec());
                    //inpBillDetail.setAmount(1);//数量
                    inpBillDetail.setUnits(priceListVo.getUnits());
                    inpBillDetail.setOrderedBy(labTestMaster.getOrderingDept());//申请科室==开单科室orderingDept
                    inpBillDetail.setOrderDoctor(labTestMaster.getOrderingProvider());//申请医生==开单医生
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
                    inpBillDetail.setOrderDoctor(labTestMaster.getOrderingProvider());//开单医生姓名
                    inpBillDetail.setPerformGroup("");//当前登录医生所在核算组
                    inpBillDetail.setPerformDoctor("");//当前登录 医生姓名
                    inpBillDetail.setConveyDate(format.parse(DateUtils.getDateTime()));//转储时间（待确定 是否是 当前时间）
                    inpBillDetail.setDoctorUser("");//医生代码 （待确定，是否是当前登录医生的代码）
                    inpBillDetail.setTransflag("0");//医保传送标志
                    inpBillDetail.setWardCode(labTestMaster.getWardCode());
                    inpBillDetail.setMemo(priceListVo.getMemo());
                    if (inpBillDetail.getIsNewRecord()) {
                        inpBillDetail.preInsert();
                        inpBillDetailDao.insert(inpBillDetail);
                    } else {
                        inpBillDetail.preUpdate();
                        inpBillDetailDao.update(inpBillDetail);
                    }

                }
            }
    }

}
