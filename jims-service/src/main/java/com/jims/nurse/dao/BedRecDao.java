package com.jims.nurse.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.web.impl.BaseDto;
import com.jims.nurse.entity.BedRec;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 床位记录DAO接口
 * @author pq
 * @version 2016-06-02
 */
@MyBatisDao
public interface BedRecDao extends CrudDao<BedRec> {

    /**
     * 判断 病区 下的床位号的唯一性
     * @param bedNo
     * @param wardCode
     * @author pq
     * @return
     */
    public List<BedRec> judgeBedNo(@Param(value = "bedNo")Integer bedNo,@Param(value = "wardCode")String wardCode);

    /**
     * 查询病区下所有的床位信息
     * @param wardCode
     * @author pq
     * @return
     */
    public List<BaseDto> getAllBed(@Param(value = "wardCode")String wardCode);


    /**
     * 已经分配了床位的在院病人列表
     * @param wardCode
     * @author pq
     * @return
     */
    public  List<BaseDto> getInPat(@Param(value = "wardCode")String wardCode);


    /**
     * 包床
     * @param bedRec
     * @author pq
     * @return
     */
    public int packBed(BedRec bedRec);
}