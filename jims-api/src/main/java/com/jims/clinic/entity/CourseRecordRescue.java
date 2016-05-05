/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 病程记录--抢救记录Entity
 * @author zhaoning
 * @version 2016-04-20
 */
public class CourseRecordRescue extends DataEntity<CourseRecordRescue> {
	
	private static final long serialVersionUID = 1L;
	private String bingchengId;		// 日常病程id
	private String yibanqingkuang;		// 一般情况
	private String ruyuanzhenduan;		// 入院诊断
	private String biingqingbianhuaqingkuang;		// 病情变化情况
	private String chubuzhenduan;		// 初步诊断
	private String qiangjiujingguo;		// 抢救经过
	private String canjiarenyuan;		// 参加人员
	private Date qiangjiushijian;		// 抢救时间
	
	public CourseRecordRescue() {
		super();
	}

	public CourseRecordRescue(String id){
		super(id);
	}

	@Length(min=1, max=64, message="日常病程id长度必须介于 1 和 64 之间")
	public String getBingchengId() {
		return bingchengId;
	}

	public void setBingchengId(String bingchengId) {
		this.bingchengId = bingchengId;
	}
	
	public String getYibanqingkuang() {
		return yibanqingkuang;
	}

	public void setYibanqingkuang(String yibanqingkuang) {
		this.yibanqingkuang = yibanqingkuang;
	}
	
	public String getRuyuanzhenduan() {
		return ruyuanzhenduan;
	}

	public void setRuyuanzhenduan(String ruyuanzhenduan) {
		this.ruyuanzhenduan = ruyuanzhenduan;
	}
	
	public String getBiingqingbianhuaqingkuang() {
		return biingqingbianhuaqingkuang;
	}

	public void setBiingqingbianhuaqingkuang(String biingqingbianhuaqingkuang) {
		this.biingqingbianhuaqingkuang = biingqingbianhuaqingkuang;
	}
	
	public String getChubuzhenduan() {
		return chubuzhenduan;
	}

	public void setChubuzhenduan(String chubuzhenduan) {
		this.chubuzhenduan = chubuzhenduan;
	}
	
	public String getQiangjiujingguo() {
		return qiangjiujingguo;
	}

	public void setQiangjiujingguo(String qiangjiujingguo) {
		this.qiangjiujingguo = qiangjiujingguo;
	}
	
	@Length(min=0, max=200, message="参加人员长度必须介于 0 和 200 之间")
	public String getCanjiarenyuan() {
		return canjiarenyuan;
	}

	public void setCanjiarenyuan(String canjiarenyuan) {
		this.canjiarenyuan = canjiarenyuan;
	}

    @JsonSerialize(using = CustomDateSerializer.class)
	public Date getQiangjiushijian() {
		return qiangjiushijian;
	}
    @JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setQiangjiushijian(Date qiangjiushijian) {
		this.qiangjiushijian = qiangjiushijian;
	}
	
}