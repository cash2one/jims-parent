package com.jims.lab.bo;

import com.jims.clinic.bo.CostOrdersUtilsService;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.common.service.impl.CrudImplService;
import com.jims.lab.dao.LabTestItemsDao;
import com.jims.lab.dao.LabTestMasterDao;
import com.jims.lab.entity.LabTestItems;
import com.jims.lab.entity.LabTestMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
@Service
@Transactional(readOnly = false)
public class ClinicLabTestBo extends CrudImplService<LabTestMasterDao,LabTestMaster> {

    @Autowired
    private LabTestItemsDao labTestItemsDao;
    @Autowired
    private CostOrdersUtilsService costOrdersUtilsService;
    @Autowired
    private LabTestMasterDao labTestMasterDao;

    /***
     * 问诊检验保存
     * @param labTestMaster
     * @return
     */
    public String saveAll(LabTestMaster labTestMaster){
        int  num;
        if(true){//labTestMaster!=null && labTestMaster.getId()!=null
            String a ="";
            //patientId病人标识号页面有公共值
            //labTestMaster.setPatientId("");
            labTestMaster.preInsert();
            labTestMaster.setOrderingProvider("");
            labTestMaster.setOrderingDept("");
            //结果状态
            labTestMaster.setResultStatus("1");
            labTestMaster.setDelFlag("0");
            //申请序号
            labTestMaster.setTestNo(creatTestNo());
            labTestMaster.setBillingIndicator(0);
            labTestMaster.setPrintIndicator(0);
            List<ClinicItemDict> clinicItemDictList=new ArrayList<ClinicItemDict>();
            List<LabTestItems> labTestItemsList = labTestMaster.getList();
            if(labTestItemsList.size()>0){
                for (int i= 0; i < labTestItemsList.size()-1;i++){
                    ClinicItemDict clinicItemDict=new ClinicItemDict();
                    LabTestItems labTestItems=labTestItemsList.get(i);
                    clinicItemDict.setItemCode(labTestItems.getItemCode());
                    clinicItemDict.setOrgId(labTestMaster.getOrgId());
                    labTestItems.setDelFlag("0");
                    labTestItems.setItemNo(i+1);
                    labTestItems.setTestNo(labTestMaster.getTestNo());
                    labTestItems.preInsert();
                    labTestItemsDao.insert(labTestItems);
                    clinicItemDictList.add(clinicItemDict);
                }
                costOrdersUtilsService.save(labTestMaster.getClinicId(),clinicItemDictList,labTestMaster.getId());
                num = labTestMasterDao.insert(labTestMaster);
                return  num+"";
            }
        }
        return "0";
    }
    /**
     * 生成申请序号
     * @param主表 当前日期
     * @author xueyx
     * @version 2016/5/09
     */
    public String creatTestNo(){
        String no =dao.creatTestNo();
        Date dt=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String d1 =format.format(dt);
        String result="";
        if(no!=null){
            if(d1.equals(no.substring(0,6))){
                int temp = Integer.valueOf(no.substring(6));
                temp=temp+1;
                result = String.format("%4d", temp).replace(" ", "0");
                if(result.length()>4){
                    result = d1.concat("0000");
                }else{
                    result=d1.concat(result);
                }
            }else{
                result=d1.concat("0001");
            }
            return result;
        }
        return "000000";
    }

}
