package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**阶段小结实体
 * Created by qinlongxin on 2016/4/20.
 */
public class CourseRecordStage extends DataEntity<CourseRecordStage> {
    private static final long serialVersionUID = 1L;
    private CourseRecord courseRecord;		// 日常病程id
    private String yibanqingkuang;		// 一般情况
    private String ruyuanzhenduan;		// 入院诊断
    private String zhenliaojingguo;		// 诊疗经过
    private String muqianqingkuang;		// 目前情况
    private String muqianzhenduan;		// 目前诊断
    private String zhenliaojihua;		// 诊疗计划
    private String zhusu;		        // 主诉
    protected Date lasttime;	// 上传时间
    protected Date nowtime;	// 本次时间
    private  String setBingchengId;
    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Date getNowtime() {
        return nowtime;
    }

    public void setNowtime(Date nowtime) {
        this.nowtime = nowtime;
    }

    public CourseRecordStage() {
        super();
    }

    public CourseRecordStage(String id){
        super(id);
    }

    public String getZhusu() {
        return zhusu;
    }

    public void setZhusu(String zhusu) {
        this.zhusu = zhusu;
    }

    @Length(min=1, max=64, message="日常病程id长度必须介于 1 和 64 之间")
    public CourseRecord getCourseRecord() {
        return courseRecord;
    }
    public void setCourseRecord(CourseRecord courseRecord) {
        this.courseRecord = courseRecord;
    }
    @Length(min=0, max=200, message="一般情况长度必须介于 0 和 200 之间")
    public String getYibanqingkuang() {
        return yibanqingkuang;
    }

    public void setYibanqingkuang(String yibanqingkuang) {
        this.yibanqingkuang = yibanqingkuang;
    }

    @Length(min=0, max=200, message="入院诊断长度必须介于 0 和 200 之间")
    public String getRuyuanzhenduan() {
        return ruyuanzhenduan;
    }

    public void setRuyuanzhenduan(String ruyuanzhenduan) {
        this.ruyuanzhenduan = ruyuanzhenduan;
    }

    @Length(min=0, max=200, message="诊疗经过长度必须介于 0 和 200 之间")
    public String getZhenliaojingguo() {
        return zhenliaojingguo;
    }

    public void setZhenliaojingguo(String zhenliaojingguo) {
        this.zhenliaojingguo = zhenliaojingguo;
    }

    @Length(min=0, max=255, message="目前情况长度必须介于 0 和 255 之间")
    public String getMuqianqingkuang() {
        return muqianqingkuang;
    }

    public void setMuqianqingkuang(String muqianqingkuang) {
        this.muqianqingkuang = muqianqingkuang;
    }

    @Length(min=0, max=255, message="目前诊断长度必须介于 0 和 255 之间")
    public String getMuqianzhenduan() {
        return muqianzhenduan;
    }

    public void setMuqianzhenduan(String muqianzhenduan) {
        this.muqianzhenduan = muqianzhenduan;
    }

    @Length(min=0, max=200, message="诊疗计划长度必须介于 0 和 200 之间")
    public String getZhenliaojihua() {
        return zhenliaojihua;
    }

    public void setZhenliaojihua(String zhenliaojihua) {
        this.zhenliaojihua = zhenliaojihua;
    }

    public String getSetBingchengId() {
        return setBingchengId;
    }

    public void setSetBingchengId(String setBingchengId) {
        this.setBingchengId = setBingchengId;
    }
}
