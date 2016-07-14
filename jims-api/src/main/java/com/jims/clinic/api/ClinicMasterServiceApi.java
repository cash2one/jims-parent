package com.jims.clinic.api;

import com.jims.clinic.entity.ClinicMaster;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 * 病人就诊记录Api
 *@author zhaonig
 * @version 2016-04-22
 */
public interface ClinicMasterServiceApi {

    /**
     * 获取就诊记录信息
     * @param id
     * @return
     */
    public ClinicMaster get(String id);

    /**
     * 获取基本信息 （clinicMaster，patMasterIndex 中的信息）
     * @param id
     * @return
     */
    public ClinicMaster getPatInfo(String id);

    /**
     * 根据当前登录人 查询 病人列表(待诊病人)
     * @param doctorID
     * @return
     */
    public List<ClinicMaster> getClinicMasterList(String doctorID,String visitDept);

    /**
     * 根据当前登录人 查询  病人列表（已诊病人）
     * @param doctorID
     * @return
     */
    public List<ClinicMaster> getClinicMasterDiagnosed(String doctorID,String visitDept);

    /**
     * 根据参数查询挂号数据
     * @param operator
     * @param date
     * @author CTQ
     * @return
     */
    public ClinicMaster findFeeForm(String operator,String date);

    /**
     * 根据操作员和挂号时间查询并按照支付方式分组数据
     * @param operator
     * @param registeringDate
     * @author CTQ
     * @return
     */
    public List<ClinicMaster> getGroupData(String operator,String registeringDate);

    /**
     * 根据参数获取检查费用项目信息
     * @param operator
     * @param registeringDate
     * @author CTQ
     * @return
     */
    public ClinicMaster getCheckItem(String operator,String registeringDate);

    /**
     * 更新 病人信息
     * @param clinicMaster
     * @return
     */
    public String updatePatInfo(ClinicMaster clinicMaster);


    public ClinicMaster getPatient(String id);

    /**
     * 拿出最大的收据单号
     * @author pq
     * @return
     */
    public String getMaxAcctNo();
}
