package com.jims.drugPresc.dao;


import com.jims.clinic.dto.InpBillDetailDto;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.drugPresc.entity.InpBillDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 住院病人费用明细记录DAO接口
 * @author pq
 * @version 2016-05-30
 */
@MyBatisDao
public interface InpBillDetailDao extends CrudDao<InpBillDetail> {
    /**
     * 查询病人本次住院的收费记录最大的项目序号
     * @param patientId
     * @param visitId
     * @return
     * @author pq
     */
    public Integer getMaxItemNo(@Param("patientId")String patientId,@Param("visitId")String visitId);

    /**
     * 病案首页 费用查询
     * @param patientId
     * @return
     * @author zhaoning
     */
    public InpBillDetailDto getInpDetail(String patientId);

	
}