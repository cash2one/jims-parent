/**
 * Created by Administrator on 2016/6/25.
 */
package com.jims.asepsis.dao;

import com.jims.asepsis.entity.AsepsisAntiRec;
import com.jims.asepsis.vo.AsepsisDictVo;
import com.jims.clinic.vo.PreDischgedPatsVo;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 消毒包处理DAO接口
 * @author louhuili
 * @version 2016-06-27
 */
@MyBatisDao
public interface AsepsisAntiRecDao extends CrudDao<AsepsisAntiRec> {

    /**
     * 查询病人是否有出院通知单
     * @param patientId
     * @return
     */
    public Integer findByPatientId(@Param("patientId")String patientId);

    /**
     * 查询某状态下的消毒包
     * @return
     * @author pq
     */
    public List<AsepsisAntiRec> getAsepsisAntiRecByState(AsepsisAntiRec aar);

    /**
     * 保存清洗功能相关数据
     * @param asepsisAntiRecs
     * @author pq
     * @return
     */
    public  int saveClean(AsepsisAntiRec asepsisAntiRecs);



    public int delete(String id);
}