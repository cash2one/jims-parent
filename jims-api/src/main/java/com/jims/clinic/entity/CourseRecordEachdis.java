/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 病程记录---每日病程记录Entity
 * @author zhaoning
 * @version 2016-04-20
 */
@XmlRootElement
public class CourseRecordEachdis extends DataEntity<CourseRecordEachdis> {
	
	private static final long serialVersionUID = 1L;
	private String bingchengId;		// bingcheng_id
	private Date bingchengjilutime;		// bingchengjilutime
	private String content;		// 内容
	private String docQianming;		// 医师签名

	private CourseRecord courseRecord;//病程主记录
	public CourseRecordEachdis() {
		super();
	}

	public CourseRecordEachdis(String id){
		super(id);
	}

	@Length(min=0, max=64, message="bingcheng_id长度必须介于 0 和 64 之间")
	public String getBingchengId() {
		return bingchengId;
	}

	public void setBingchengId(String bingchengId) {
		this.bingchengId = bingchengId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBingchengjilutime() {
		return bingchengjilutime;
	}

	public void setBingchengjilutime(Date bingchengjilutime) {
		this.bingchengjilutime = bingchengjilutime;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="医师签名长度必须介于 0 和 64 之间")
	public String getDocQianming() {
		return docQianming;
	}

	public void setDocQianming(String docQianming) {
		this.docQianming = docQianming;
	}

	public CourseRecord getCourseRecord() {
		return courseRecord;
	}

	public void setCourseRecord(CourseRecord courseRecord) {
		this.courseRecord = courseRecord;
	}
}