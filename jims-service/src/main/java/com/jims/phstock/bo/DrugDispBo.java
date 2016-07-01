package com.jims.phstock.bo;

import com.jims.common.utils.DateUtils;
import com.jims.phstock.dao.DrugDispDao;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.vo.OrdersDispInfo;
import com.jims.phstock.vo.PatDrugDisp;
import com.jims.phstock.vo.PatientBaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
     * @param orgId           组织机构ID
     * @param deptCode        入院科室
     * @param wardDeptCode    入院护理单元
     * @param administations  摆药类型
     * @param bedNos          摆药床位
     * @param repeatIndicator 长临
     * @param dispDays        摆药天数
     * @param dispStartTime   摆药开始时间
     * @param dispStopTime    摆药结束时间
     * @return
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
                calcTimes(ordersDispInfo, dispStartTime, dispStopTime, baseVo.getPreDischargeDate(), ordersDispInfo.getLastPerformDateTime(),
                        ordersDispInfo.getStartDateTime(), ordersDispInfo.getStopDateTime());
                calcAmount(ordersDispInfo);

            }
            patDrugDisps.add(patDrugDisp);

        }
        //endregion
        return patDrugDisps;
    }

    /**
     * 计算每一条摆药记录摆药数量
     * @param ordersDispInfo
     */
    private void calcAmount(OrdersDispInfo ordersDispInfo) {
        DrugPriceList drugPriceList = drugDispDao.findDrugPriceList(ordersDispInfo.getDrugCode(), ordersDispInfo.getDrugSpec(), ordersDispInfo.getOrgId()) ;
        double dosage = ordersDispInfo.getDosage() ;
        String dosageUnit = ordersDispInfo.getDosageUnits() ;
        String minSpec = drugPriceList.getMinSpec() ;
        double baseDosage = Double.parseDouble(minSpec.substring(0, minSpec.indexOf(dosageUnit))) ;
        double times =ordersDispInfo.getTimes() ;
        ordersDispInfo.setUnit(drugPriceList.getMinUnits());
        //dosage/baseDosage *times ;
        int amount = (int) Math.ceil(dosage/baseDosage * times);
        ordersDispInfo.setAmount(amount);
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
    private void calcTimes(OrdersDispInfo ordersDispInfo, Date dispStartTime, Date dispStopTime, Date preDischargeDate, Date lastPerformDateTime, Date startDateTime, Date stopDateTime) {
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
                calcLongDispAmountByWeek(ordersDispInfo, frequency, freqInterval, freqCounter, performSchedule,
                        startDateTime, stopDateTime, preDischargeDate, dispStartTime, dispStopTime, lastPerformDateTime);

            }

            if ("日".equals(freqIntervalUnit)) {
                //一日三次，隔日一次等
                calcLongDispAmountByDays(ordersDispInfo, frequency, freqInterval, freqCounter, performSchedule, startDateTime, stopDateTime,
                        preDischargeDate, dispStartTime, dispStopTime, lastPerformDateTime) ;
            }
        }

        if("0".equals(repeatIndicator)){
            calcShortDispAmount(ordersDispInfo,startDateTime,stopDateTime,dispStartTime,dispStopTime,preDischargeDate);
        }



    }

    /**
     * 临时医嘱，如果在摆药区间内，则摆药一次
     * @param ordersDispInfo
     * @param startDateTime
     * @param stopDateTime
     * @param dispStartTime
     * @param dispStopTime
     * @param preDischargeDate
     */
    private void calcShortDispAmount(OrdersDispInfo ordersDispInfo, Date startDateTime, Date stopDateTime, Date dispStartTime, Date dispStopTime, Date preDischargeDate) {
        //临时医嘱开始时间=结束时间，如果改时间在摆药区间则能够摆药一次
        if(startDateTime.getTime()==stopDateTime.getTime()&&startDateTime.getTime()>=dispStartTime.getTime()&&startDateTime.getTime()<dispStopTime.getTime()&&startDateTime.getTime()<=preDischargeDate.getTime()){
            ordersDispInfo.setTimes(1);
            ordersDispInfo.setLastPerformDateTime(dispStopTime);
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
    private void calcLongDispAmountByDays(OrdersDispInfo ordersDispInfo, String frequency, double freqInterval, double freqCounter, String performSchedule, Date startDateTime, Date stopDateTime, Date preDischargeDate, Date dispStartTime, Date dispStopTime, Date lastPerformDateTime) {
        Date beginDate = null ;
        Date endDate = null ;
        beginDate= getDispRoundBeginDate(startDateTime,dispStartTime,lastPerformDateTime) ;
        endDate = getDispRoundEndDate(stopDateTime,dispStopTime,preDischargeDate) ;
        Calendar calendar = Calendar.getInstance();
        List<Date> dates = new ArrayList<Date>() ;


        if(endDate.getTime()>beginDate.getTime()){
            return  ;
        }

        //region 隔日一次
        if(freqInterval>1&&performSchedule.indexOf(";")>0&&freqCounter==1){
            String[] performDays = performSchedule.split(";") ;
            Date date = new Date();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, 11) ;
            calendar.set(Calendar.MINUTE,59) ;
            date = calendar.getTime();
            if("1".equals(performDays[0])){
                //从今天开始

                while(date.getTime()<endDate.getTime()&&date.getTime()>=beginDate.getTime()){
                    dates.add(date) ;
                    ordersDispInfo.setTimes(ordersDispInfo.getTimes() +1 );
                    ordersDispInfo.setLastPerformDateTime(date);
                    calendar.add(Calendar.DAY_OF_MONTH,(int)freqInterval);
                    date = calendar.getTime() ;

                }
            }
            if("2".equals(performDays[0])){
                //从明天开始
                calendar.add(Calendar.DAY_OF_MONTH,1);
                date=calendar.getTime();
                while(date.getTime()<endDate.getTime()&&date.getTime()>=beginDate.getTime()){
                    dates.add(date) ;
                    ordersDispInfo.setTimes(ordersDispInfo.getTimes() +1 );
                    ordersDispInfo.setLastPerformDateTime(date);
                    calendar.add(Calendar.DAY_OF_MONTH,1);
                    date = calendar.getTime() ;
                }
            }
            if(dates.size()>0){
                ordersDispInfo.setPerformDates(dates);
            }
        }
        //endregion

        //region 一日Ｎ此
        if(freqInterval==1 && performSchedule.indexOf("-")>0&&freqCounter>1){
            String[] times = performSchedule.split("-") ;
            List<Integer> hours =new ArrayList<Integer>();
            for(String str:times){
                hours.add(Integer.parseInt(str)) ;
            }

            while(beginDate.getTime()<endDate.getTime()){
                calendar.setTime(beginDate);
                int hour= calendar.get(Calendar.HOUR)+12 ;
                if(hours.contains(hour)) {
                    ordersDispInfo.setTimes(ordersDispInfo.getTimes()+1);
                    ordersDispInfo.setLastPerformDateTime(beginDate);
                    dates.add(beginDate) ;
                }
                calendar.add(Calendar.HOUR,1);
                beginDate = calendar.getTime();
            }

        }
        //endregion

        //region 一日一次有具体时间点
        if(freqCounter==1&&performSchedule.indexOf(";")<0&&freqInterval==1){
            String times[] = performSchedule.split(";") ;
            if(times.length>1){
                int hour = Integer.parseInt(times[0]) ;
                int minute = Integer.parseInt(times[1]) ;
                Date date = new Date() ;
                calendar.setTime(date);
                calendar.set(Calendar.HOUR,hour-12) ;
                calendar.set(Calendar.MINUTE,minute);
                date = calendar.getTime() ;

                while(date.getTime()>=beginDate.getTime()&&date.getTime()<endDate.getTime()){
                    ordersDispInfo.setTimes(ordersDispInfo.getTimes() +1 );
                    ordersDispInfo.setLastPerformDateTime(date);
                    dates.add(date) ;
                    calendar.add(Calendar.DAY_OF_MONTH,1);
                    date =calendar.getTime();

                }
                if(dates.size()>0){
                    ordersDispInfo.setPerformDates(dates);
                }
            }

        }
        //endregion

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
    private double calcLongDispAmountByWeek(OrdersDispInfo ordersDispInfo, String frequency, double freqInterval, double freqCounter, String performSchedule, Date startDateTime, Date stopDateTime, Date preDischargeDate, Date dispStartTime, Date dispStopTime, Date lastPerformDateTime) {


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
            calendar.set(Calendar.HOUR,-12);
            calendar.set(Calendar.SECOND,0);
            beginDate = calendar.getTime();
        }
        if (endDate == null) {
            return 0;//计算结束区间失败
        }else{
            calendar.setTime(endDate);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.HOUR,11);
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
            while(beginDate.getTime()<endDate.getTime()){
                int day=calendar.get(Calendar.DAY_OF_WEEK)-1;
                if(days.contains(day)){
                    ordersDispInfo.setTimes(ordersDispInfo.getTimes()+1);
                    ordersDispInfo.setLastPerformDateTime(beginDate);
                }
                dates.add(beginDate) ;
                calendar.add(Calendar.DAY_OF_MONTH,1);
                beginDate =calendar.getTime();
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
