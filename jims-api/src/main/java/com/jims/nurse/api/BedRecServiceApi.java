package com.jims.nurse.api;

import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.common.web.impl.BaseDto;
import com.jims.nurse.entity.BedRec;

import java.util.List;

/**
 * 床位记录
 *
 * @author PangQian
 * @date2016/6/2 0002
 */
public interface BedRecServiceApi {
    /**
     * 查询护士所负责的病区的床位信息
     * @param page
     * @param bedRec
     * @author pq
     * @return
     */
    public Page<BedRec> findPage(Page<BedRec> page, BedRec bedRec);

    /**
     * 保存床位信息
     * @param bedRecList
     * @author pq
     * @return
     */
    public String saveBed(List<BedRec> bedRecList,LoginInfo loginInfo);

    /**
     * 判断 病区 下的床位号的唯一性
     * @param bedNo
     * @param wardCode
     * @author pq
     * @return
     */
    public boolean judgeBedNo(Integer bedNo,String wardCode);

    /**
     * 删除床位信息
     * @param ids
     * @author pq
     * @return
     */
    public String delete(String ids);

    /**
     * 查询病区下所有的床位信息
     * @param bedRec
     * @author pq
     * @return
     */
    public List<BaseDto> getAllBed(BedRec bedRec);


    /**
     * 已经分配了床位的在院病人列表
     * @param bedRec
     * @author pq
     * @return
     */
    public  List<BaseDto> getInPat(BedRec bedRec);

    /**
     * 包床
     * @param bedRec
     * @author pq
     * @return
     */
    public String packBed(List<BedRec> bedRec,String patId);


    /**
     * 查询护士所负责的病区的床位信息
     * @param bedRec
     * @author pq
     * @return
     */
    public List<BedRec> findList(BedRec bedRec);

    /**
     * 护士端-换床
     * @param bedRec
     * @author pq
     * @return
     */
    public String changeBed(BedRec bedRec);

    /**
     * 已经分配了床位的在院病人列表
     * @param bedRec
     * @author pq
     * @return
     */
    public  BaseDto getInPatOne(BedRec bedRec);

    /**
     * 通过visitId 拿到在医院病人的在院信息
     * @param visitId
     * @return
     */
    public PatsInHospital getInPat(String visitId);


    /**
     * 查询床位相关费用
     * @author pq
     * @param itemClass
     * @return
     */
     public List<BaseDto> findBedPrice(String itemClass);


    /**
     * 解除包床
     * @param bedRec
     * @author pq
     * @return
     */
    public String accountsConfirm(List<BedRec> bedRec);
}
