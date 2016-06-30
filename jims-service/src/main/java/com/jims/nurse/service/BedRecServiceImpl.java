package com.jims.nurse.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.persistence.Page;
import com.jims.common.web.impl.BaseDto;
import com.jims.nurse.api.BedRecServiceApi;
import com.jims.nurse.bo.BedRecBo;
import com.jims.nurse.entity.BedRec;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * BedRecServiceImpl
 *
 * @author PangQian
 * @date2016/6/2 0002
 */
@Service(version = "1.0.0")
public class BedRecServiceImpl implements BedRecServiceApi {
  @Autowired
    private BedRecBo bedRecBo;
    /**
     * 查询护士所负责的病区的床位信息
     * @param bedRecPage
     * @param bedRec
     * @author pq
     * @return
     */
    public Page<BedRec> findPage(Page<BedRec> bedRecPage,BedRec bedRec){
       return bedRecBo.findPage(bedRecPage,bedRec);
    }

    /**
     * 保存床位信息
     * @param bedRecList
     * @author pq
     * @return
     */
    public String saveBed(List<BedRec> bedRecList){
    return  bedRecBo.saveBed(bedRecList);
    }

    /**
     * 判断 病区 下的床位号的唯一性
     * @param bedNo
     * @param wardCode
     * @author pq
     * @return
     */
    public boolean judgeBedNo(Integer bedNo,String wardCode){
      return bedRecBo.judgeBedNo(bedNo,wardCode);
    }

    /**
     * 删除床位信息
     * @param ids
     * @author pq
     * @return
     */
    public String delete(String ids){
     return bedRecBo.delete(ids);
    }


  /**
   * 查询病区下所有的床位信息
   * @param bedRec
   * @author pq
   * @return
   */
  public List<BaseDto> getAllBed(BedRec bedRec){
    return  bedRecBo.getAllBed(bedRec);
  }


  /**
   * 已经分配了床位的在院病人列表
   * @param bedRec
   * @author pq
   * @return
   */
  public  List<BaseDto> getInPat(BedRec bedRec){
    return bedRecBo.getInPat(bedRec);
  }


  /**
   * 包床
   * @param bedRec
   * @author pq
   * @return
   */
  public String packBed(List<BedRec> bedRec){
    return  bedRecBo.packBed(bedRec);
  }

  public List<BedRec> findList(BedRec bedRec){
    return  bedRecBo.findList(bedRec);
  }


  /**
   * 护士端-换床
   * @param bedRec
   * @author pq
   * @return
   */
  public String changeBed(BedRec bedRec){
    int num = 0;
    PatsInHospital patsInHospital = new PatsInHospital();
    patsInHospital.setBedNo(bedRec.getOldBedNo());
    patsInHospital.setPatientId(bedRec.getPatientId());
     bedRecBo.updateBedNo(patsInHospital);//修改床位
     bedRecBo.updateBedStatus("0",bedRec.getOldBedNo(),0,bedRec.getWardCode());//以前的s
    num = bedRecBo.updateBedStatus("1",0, bedRec.getNewBedNo(),bedRec.getWardCode());
    return num+"";
  }



  /**
   * 已经分配了床位的在院病人列表
   * @param bedRec
   * @author pq
   * @return
   */
  public  BaseDto getInPatOne(BedRec bedRec){
    return bedRecBo.getInPatOne(bedRec);
  }


  /**
   * 通过visitId 拿到在医院病人的在院信息
   * @param visitId
   * @return
   */
  public PatsInHospital getInPat(String visitId){
   return  bedRecBo.getInPat(visitId);
  }



  /**
   * 查询床位相关费用
   * @author pq
   * @param itemClass
   * @return
   */
  public List<BaseDto> findBedPrice(String itemClass){
    return bedRecBo.findBedPrice(itemClass);
  }

  /**
   * 解除包床
   * @param bedRecList
   * @return
   */
  public String accountsConfirm(List<BedRec> bedRecList){
    return bedRecBo.accountsConfirm(bedRecList);
  }
}
