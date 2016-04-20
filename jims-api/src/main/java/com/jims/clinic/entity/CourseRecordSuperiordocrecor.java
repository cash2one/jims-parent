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
 * 病程记录--上级医师查房记录Entity
 * @author zhaoning
 * @version 2016-04-20
 */
@XmlRootElement
public class CourseRecordSuperiordocrecor extends DataEntity<CourseRecordSuperiordocrecor> {
	
	private static final long serialVersionUID = 1L;
	private String bingchengId;		// bingcheng_id
	private String content;		// content
	private Date jilushijian;		// jilushijian
	
	public CourseRecordSuperiordocrecor() {
		super();
	}

	public CourseRecordSuperiordocrecor(String id){
		super(id);
	}

	@Length(min=1, max=64, message="bingcheng_id长度必须介于 1 和 64 之间")
	public String getBingchengId() {
		return bingchengId;
	}

	public void setBingchengId(String bingchengId) {
		this.bingchengId = bingchengId;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJilushijian() {
		return jilushijian;
	}

	public void setJilushijian(Date jilushijian) {
		this.jilushijian = jilushijian;
	}
	
}