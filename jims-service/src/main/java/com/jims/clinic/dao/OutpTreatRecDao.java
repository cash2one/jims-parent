/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 检查治疗医嘱明细记录DAO接口
 *
 * @author zhaoning
 * @version 2016-04-25
 */
@MyBatisDao
public interface OutpTreatRecDao extends CrudDao<OutpTreatRec> {
    /**
     * 获取检验项目序号
     *
     * @param visitDate
     * @param visitNo
     * @param itemClass
     * @return
     */
    public Integer loadItems(Date visitDate, Integer visitNo, String itemClass);

    /**
     * 保存检查治疗医嘱明细记录
     *
     * @param outpTreatRec
     */
    public void saveTreatRec(OutpTreatRec outpTreatRec);

    /**
     * 删除治疗医嘱主记录
     *
     * @param visitNo
     * @return
     */
    public Integer deleteTreatRec(String visitNo);

    /**
     * 获得检查治疗医嘱最大的流水号
     *
     * @return
     */
    public Integer getSerialNo();

    /**
     * 打印检验信息
     *
     * @param serialNo
     * @param visitDate
     * @param visitNo
     * @return
     */
    public List<OutpTreatRec> getPrintLab(String serialNo, Date visitDate, Integer visitNo);
    //执行单数据
    //  public List<PrintZhixing> getPrintZhixing(Date visitDate ,Integer visitNo);



    /**
     * 通过clinicId查找治疗信息
     * @param clinicId
     * @return
     */
   public List<OutpTreatRec> findTreatment(@Param(value = "clinicId")String clinicId);

    /**
     * 通过项目code，itemClass(检查治疗医嘱明细里的code和class)
     * @param itemCode
     * @param itemClass
     * @return
     */
    public  List<OutpOrdersCosts> findSubTreatment(@Param(value = "itemCode")String itemCode,@Param(value = "itemClass")String itemClass);


}