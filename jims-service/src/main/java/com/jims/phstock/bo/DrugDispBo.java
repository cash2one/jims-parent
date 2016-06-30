package com.jims.phstock.bo;

import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.utils.DateUtils;
import com.jims.phstock.api.DrugDispApi;
import com.jims.phstock.dao.DrugDispDao;
import com.jims.phstock.vo.OrdersDispInfo;
import com.jims.phstock.vo.PatDrugDisp;
import com.jims.phstock.vo.PatientBaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.awt.image.PixelConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 医嘱摆药
 * Created by heren on 2016/6/29.
 */
@Component
public class DrugDispBo {

    @Autowired
    private DrugDispDao drugDispDao;

    /**
     * 计算摆药
     *
     * @param orgId           组织机构ID
     * @param deptCode        入院科室
     * @param wardDeptCode    入院护理单元
     * @param administations  摆药类型
     * @param bedNos          摆药床位
     * @param repeatIndicator 长临
     * @param dispDays        摆药天数
     * @param dispStartTime
     * @param dispStopTime    @return
     */
    public List<PatDrugDisp> calcPatDrugDisp(String orgId, String deptCode, String wardDeptCode,
                                             List<String> administations, List<String> bedNos,
                                             String repeatIndicator, int dispDays, Date dispStartTime, Date dispStopTime) {
        //第一步获取所有的摆药医嘱
        List<PatDrugDisp> patDrugDisps = new ArrayList<PatDrugDisp>();
        List<PatientBaseVo> patsInHospitals = drugDispDao.findPatsInHospital(wardDeptCode, deptCode, orgId, bedNos);

        if (patsInHospitals == null) {
            return null;
        }

        if (dispDays > 1) {
            dispStopTime = DateUtils.getDaysAfter(dispStopTime, dispDays);
        } else {
            return null;//必须进行整天摆药
        }


        //region 循环遍历每一个病人的
        for (PatientBaseVo baseVo : patsInHospitals) {
            String patientId = baseVo.getPatientId();
            int visitId = baseVo.getVisitId();
            String orgIdTemp = baseVo.getOrgId();
            List<OrdersDispInfo> ordersDispInfos = drugDispDao.findOrdersDispInfos(patientId, visitId, orgIdTemp);

            PatDrugDisp patDrugDisp = new PatDrugDisp();
            patDrugDisp.setOrdersDispInfos(ordersDispInfos);

            for (OrdersDispInfo ordersDispInfo : ordersDispInfos) {
                calcAmount(ordersDispInfo, dispStartTime, dispStopTime, baseVo.getPreDischargeDate(), ordersDispInfo.getLastPerformDateTime(),
                        ordersDispInfo.getStartDateTime(), ordersDispInfo.getStopDateTime());
            }

        }
        //endregion

        //第二步获取摆药开始时间、摆药结束时间
        //第三步计算每一条医嘱摆药数量

        return null;
    }

    /**
     * 根据摆药算法计算摆药数量
     *
     * @param ordersDispInfo      更新其中的amount
     * @param dispStartTime       医嘱摆药的开始时间（包含）
     * @param dispStopTime        医嘱摆药的结束时间（不包含）
     * @param preDischargeDate    预出院时间（不包含）
     * @param lastPerformDateTime 上次摆药结束时间（包含）
     * @param startDateTime       医嘱开始时间（包含）
     * @param stopDateTime        医嘱结束时间（不包含）
     */
    private void calcAmount(OrdersDispInfo ordersDispInfo, Date dispStartTime, Date dispStopTime, Date preDischargeDate, Date lastPerformDateTime, Date startDateTime, Date stopDateTime) {
        String frequency = ordersDispInfo.getFrequency();//执行描述
        double freqInterval = ordersDispInfo.getFreqInterval();//执行频率间隔
        String freqIntervalUnit = ordersDispInfo.getFreqIntervalUnit();//执行频率间隔单位
        double freqCounter = ordersDispInfo.getFreqCounter();//频率次数
        String performSchedule = ordersDispInfo.getPerformSchedule();
        double dispCount = 0.0;//摆药次数
        String repeatIndicator = ordersDispInfo.getRepeatIndicator();

        //计算摆药次数

        if ("1".equals(repeatIndicator)) {
            //长期医嘱
            if ("周".equals(freqIntervalUnit)) {
                //一周三次，一周一次等
                calcDispAmountByWeek(ordersDispInfo,frequency, freqInterval, freqCounter, performSchedule,
                        startDateTime, stopDateTime, preDischargeDate, dispStartTime, dispStopTime, lastPerformDateTime);

            }

            if ("日".equals(freqIntervalUnit)) {
                //一日三次，隔日一次等
                calcDispAmountByDays(ordersDispInfo,frequency,freqInterval,freqCounter,performSchedule,startDateTime,stopDateTime,
                        preDischargeDate,dispStartTime,dispStopTime,lastPerformDateTime) ;
            }
        }


    }

    /**
     * 计算一天N次的摆药算法
     * @param ordersDispInfo
     * @param frequency
     * @param freqInterval
     * @param freqCounter
     * @param performSchedule
     * @param startDateTime
     * @param stopDateTime
     * @param preDischargeDate
     * @param dispStartTime
     * @param dispStopTime
     * @param lastPerformDateTime
     */
    private void calcDispAmountByDays(OrdersDispInfo ordersDispInfo, String frequency, double freqInterval, double freqCounter, String performSchedule, Date startDateTime, Date stopDateTime, Date preDischargeDate, Date dispStartTime, Date dispStopTime, Date lastPerformDateTime) {
        Date beginDate = null ;
        Date endDate = null ;
        beginDate= getDispRoundBeginDate(startDateTime,dispStartTime,lastPerformDateTime) ;
        endDate = getDispRoundEndDate(stopDateTime,dispStopTime,preDischargeDate) ;

        
    }

    /**
     * 计算一周N 此的摆药算法
     * @param ordersDispInfo
     * @param frequency
     * @param freqInterval
     * @param freqCounter
     * @param performSchedule
     * @param startDateTime
     * @param stopDateTime
     * @param preDischargeDate
     * @param dispStartTime
     * @param dispStopTime
     * @param lastPerformDateTime
     * @return
     */
    private double calcDispAmountByWeek(OrdersDispInfo ordersDispInfo, String frequency, double freqInterval, double freqCounter, String performSchedule, Date startDateTime, Date stopDateTime, Date preDischargeDate, Date dispStartTime, Date dispStopTime, Date lastPerformDateTime) {


        Date beginDate = null;//摆药范围开始时间
        Date endDate = null;//摆药范围结束时间
        List<Date> dates = new ArrayList<Date>() ;//执行时间

        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        //region 根据摆药开始时间，摆药结束时间，医嘱结束时间，医嘱开始时间，上次摆药结束时间，预出院时间 算出摆药区间
        beginDate = getDispRoundBeginDate(startDateTime, dispStartTime, lastPerformDateTime);
        endDate = getDispRoundEndDate(stopDateTime, dispStopTime, preDischargeDate);
        if (beginDate == null) {
            return 0;//计算区间开始时间错误
        }else{
            calendar.setTime(beginDate);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.HOUR,0);
            calendar.set(Calendar.SECOND,0);
            beginDate = calendar.getTime();
        }
        if (endDate == null) {
            return 0;//计算结束区间失败
        }else{
            calendar.setTime(endDate);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.HOUR,0);
            calendar.set(Calendar.SECOND,0);
            endDate = calendar.getTime();
        }
        //endregion

        if (endDate.getTime() <= beginDate.getTime()) {
            return 0;//摆药区间为开区间。
        }

        String[] performDays = performSchedule.split(";");
        if (performDays.length <= 0) {
            return 0;//摆药时间有问题
        }

        //region 计算一周N次，能够取到几次要，分别的日期在什么时候
        if(freqInterval==1){//一周N次的
            List<Integer> days = new ArrayList<Integer>() ;
            for(String str:performDays){
                days.add(Integer.parseInt(str));
            }
            calendar.setTime(beginDate);
            for(int i=0;beginDate.getTime()<endDate.getTime();i++){
                int day=calendar.get(Calendar.DAY_OF_WEEK)-1;
                days.contains(day) ;
                ordersDispInfo.setAmount(ordersDispInfo.getAmount()+1);
                Date date= new Date() ;
                date.setTime(beginDate.getTime());
                dates.add(date) ;
                calendar.add(Calendar.DAY_OF_MONTH,1);
                i++ ;
            }
            ordersDispInfo.setLastPerformDateTime(endDate);
            ordersDispInfo.setPerformDates(dates);
        }
        //endregion



        return ordersDispInfo.getAmount();

    }

    /**
     * 根据医嘱停止时间，摆药截止时间，预出院时间计算出摆药区间的结束时间
     *
     * @param stopDateTime     医嘱停止时间
     * @param dispStopTime     摆药停止时间
     * @param preDischargeDate 预出院时间
     * @return
     */
    private Date getDispRoundEndDate(Date stopDateTime, Date dispStopTime, Date preDischargeDate) {
        Date endDate = null;
        if (preDischargeDate == null) {
            if (stopDateTime == null) {
                endDate = dispStopTime;
            } else {
                if (stopDateTime.getTime() > dispStopTime.getTime()) {
                    endDate = dispStopTime;
                } else {
                    endDate = stopDateTime;
                }
            }
        } else {
            if (stopDateTime == null) {
                if (dispStopTime.getTime() > preDischargeDate.getTime()) {
                    endDate = preDischargeDate;
                } else {
                    endDate = dispStopTime;
                }
            }
        }
        return endDate;
    }

    /**
     * 获取医嘱表要开始时间
     *
     * @param startDateTime       医嘱开始时间
     * @param dispStartTime       摆药开始时间
     * @param lastPerformDateTime 上次执行时间
     * @return
     */
    private Date getDispRoundBeginDate(Date startDateTime, Date dispStartTime, Date lastPerformDateTime) {
        Date beginDate = null;
        if (startDateTime.getTime() > dispStartTime.getTime()) {
            beginDate = startDateTime;
        } else {
            beginDate = dispStartTime;
        }
        if (lastPerformDateTime != null) {
            if (beginDate.getTime() < lastPerformDateTime.getTime()) {
                beginDate = lastPerformDateTime;
            }
        }
        return beginDate;
    }


}
