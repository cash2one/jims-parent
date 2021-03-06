package com.jims.nurse.bo;

import com.jims.clinic.dao.PatsInHospitalDao;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.vo.LoginInfo;
import com.jims.common.web.impl.BaseDto;
import com.jims.nurse.dao.BedRecDao;
import com.jims.nurse.entity.BedRec;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BedRecBo
 *
 * @author PangQian
 * @date2016/6/2 0002
 */
@Service
@Component
@Transactional(readOnly = false)
public class BedRecBo extends CrudImplService<BedRecDao, BedRec> {
 @Autowired
 private PatsInHospitalDao patsInHospitalDao;
    /**
     * 保存床位信息
     * @param bedRecList
     * @author pq
     * @return
     */
    public String saveBed(List<BedRec> bedRecList,LoginInfo loginInfo){
        String str="";
        int num=0;
      if(bedRecList !=null){
            for(int i=0;i<bedRecList.size();i++){
                BedRec bedRec = new BedRec();
                bedRec = bedRecList.get(i);
                bedRec.setDeptCode(loginInfo.getDeptId());
                bedRec.setOrgId(loginInfo.getOrgId());
                if(bedRec.getId()==null){
                    bedRec.setBedStatus("0");
                }
                if(judgeBedNo(bedRec.getBedNo(),bedRec.getWardCode())){
                    str="error";
                }else{
                    str = save(bedRec);
                }

            }
      }else{
       return str;
      }
        return str;
    }

    /**
     * 判断 病区 下的床位号的唯一性
     * @param bedNo
     * @param wardCode
     * @author pq
     * @return
     */
    public  boolean judgeBedNo(Integer bedNo,String wardCode){
        boolean flag = false;
      List<BedRec> bedRecs=dao.judgeBedNo(bedNo,wardCode);
        if(bedRecs!=null && bedRecs.size()>0){
            flag =true;
        }
        return flag;
    }


    /**
     * 查询病区下所有的床位信息
     * @param bedRec
     * @author pq
     * @return
     */
    public List<BaseDto> getAllBed(BedRec bedRec){
        List<BaseDto> list=dao.getAllBed(bedRec);
      return list;
    }


    /**
     * 已经分配了床位的在院病人列表
     * @param bedRec
     * @author pq
     * @return
     */
    public  List<BaseDto> getInPat(BedRec bedRec){
        return dao.getInPat(bedRec);
    }

    /**
     * 包床
     * @param bedRecList
     * @author pq
     * @return
     */
    public String packBed(List<BedRec> bedRecList,String patId){
        String num="";
       if(bedRecList !=null && bedRecList.size()>0){

           BedRec bedRec=new BedRec();
           for (int i = 0; i < bedRecList.size(); i++) {
               bedRec= bedRecList.get(i);
               bedRec.setPatientId(patId);
               num=  num +  dao.packBed(bedRec);
           }
       }else{
           num = "0";
       }
        return  num;
    }


    /**
     * 护士端-换床
     * @param patsInHospital
     * @author pq
     * @return
     */
    public int updateBedNo(PatsInHospital patsInHospital){
      return dao.updateBedNo(patsInHospital);
    }

    /**
     * 护士端-换床
     * @param bedStatus
     * @author pq
     * @return
     */
    public int updateBedStatus(String bedStatus,Integer oldBedNo,Integer newBedNo,String wardCode,String patientId){
        BedRec bedRec= new BedRec();
        bedRec.setBedStatus(bedStatus);
        bedRec.setOldBedNo(oldBedNo);
        bedRec.setNewBedNo(newBedNo);
        bedRec.setWardCode(wardCode);
        bedRec.setPatientId(patientId);
      return  dao.updateBedStatus(bedRec);
    }

    /**
     * 已经分配了床位的在院病人列表
     * @param bedRec
     * @author pq
     * @return
     */
    public  BaseDto getInPatOne(BedRec bedRec){
        return dao.getInPatOne(bedRec);
    }



    /**
     * 通过visitId 拿到在医院病人的在院信息
     * @param visitId
     * @return
     */
    public PatsInHospital getInPat(String visitId){
       return patsInHospitalDao.get(visitId);
    }



    /**
     * 查询床位相关费用
     * @author pq
     * @param itemClass
     * @return
     */
    public List<BaseDto> findBedPrice(String itemClass){
      return dao.findBedPrice(itemClass);
    }

    /**
     * 解除包床
     * @author pq
     * @param bedRecList
     * @return
     */
    public String accountsConfirm(List<BedRec> bedRecList){
        String num="";
        if(bedRecList !=null && bedRecList.size()>0){
            BedRec bedRec=new BedRec();
            for (int i = 0; i < bedRecList.size(); i++) {
                bedRec= bedRecList.get(i);
                num=  num +  dao.accountsConfirm(bedRec);
            }
        }else{
            num = "0";
        }
        return  num;
    }
}
