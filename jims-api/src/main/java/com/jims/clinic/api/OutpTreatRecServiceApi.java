package com.jims.clinic.api;

import com.jims.clinic.entity.OutpTreatRec;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 * @author zhaoning
 * @version 2016-04-25
 */
public interface OutpTreatRecServiceApi {

    /**
     * 根据就诊日期和就诊序号获取检验项目
     * @param visitDate
     * @param visitNo
     * @return
     */
    public  Integer loadItmes(Date visitDate ,Integer visitNo ,String itemClass);
    /**
     * 保存检查治疗医嘱明细记录
     * @param outpTreatRec
     */
    public  void saveTreatRec(OutpTreatRec outpTreatRec);

    /**
     * 删除治疗医嘱主记录
     * @param visitNo
     */
    public Integer deleteTreatRec(int visitNo);

    /**
     * 得到检查治疗医嘱的最大流水号
     * @return
     */
    public Integer getSerialNo();

    /**
     * 打印检验信息
     * @param serialNo
     * @param visitDate
     * @param visitNo
     * @return
     */
    public List<OutpTreatRec> getPrintLab(String serialNo  , Date visitDate ,Integer visitNo);

}
