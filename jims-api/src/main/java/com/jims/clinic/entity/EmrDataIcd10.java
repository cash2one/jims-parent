/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">EMR</a> All rights reserved.
 */
package com.jims.clinic.entity;


import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * ICD10编码Entity
 * @author pq
 * @version 2015-04-26
 */
public class EmrDataIcd10 extends DataEntity<EmrDataIcd10> {
	
	private static final long serialVersionUID = 1L;
	private String pid;		// 父id
	private String code;		// ICD-10编码
	private String zhongwenMingcheng;		// 中文名称
	private String pinyinIndex;		// 拼音检索码
	private String keywordShuoming;		// 用于检索的关键词，同时也是简短说明
	private String yingwenMingcheng;		// 英文名称
	private String shuoming;		// 具体说明内容
	private String otherInfo;		// 其它说明
	private String subDirectNumber;		// 直接子元素个数 1:是 0:否
	private String subTotalNumber;		// 所有子元素个数 1:是 0:否
	
	public EmrDataIcd10() {
		super();
	}

	public EmrDataIcd10(String id){
		super(id);
	}

	@Length(min=0, max=10, message="父id长度必须介于 0 和 10 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Length(min=0, max=255, message="ICD-10编码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=255, message="中文名称长度必须介于 0 和 255 之间")
	public String getZhongwenMingcheng() {
		return zhongwenMingcheng;
	}

	public void setZhongwenMingcheng(String zhongwenMingcheng) {
		this.zhongwenMingcheng = zhongwenMingcheng;
	}
	
	@Length(min=0, max=255, message="拼音检索码长度必须介于 0 和 255 之间")
	public String getPinyinIndex() {
		return pinyinIndex;
	}

	public void setPinyinIndex(String pinyinIndex) {
		this.pinyinIndex = pinyinIndex;
	}
	
	@Length(min=0, max=255, message="用于检索的关键词，同时也是简短说明长度必须介于 0 和 255 之间")
	public String getKeywordShuoming() {
		return keywordShuoming;
	}

	public void setKeywordShuoming(String keywordShuoming) {
		this.keywordShuoming = keywordShuoming;
	}
	
	@Length(min=0, max=255, message="英文名称长度必须介于 0 和 255 之间")
	public String getYingwenMingcheng() {
		return yingwenMingcheng;
	}

	public void setYingwenMingcheng(String yingwenMingcheng) {
		this.yingwenMingcheng = yingwenMingcheng;
	}
	
	public String getShuoming() {
		return shuoming;
	}

	public void setShuoming(String shuoming) {
		this.shuoming = shuoming;
	}
	
	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	
	@Length(min=0, max=10, message="直接子元素个数 1:是 0:否长度必须介于 0 和 10 之间")
	public String getSubDirectNumber() {
		return subDirectNumber;
	}

	public void setSubDirectNumber(String subDirectNumber) {
		this.subDirectNumber = subDirectNumber;
	}
	
	@Length(min=0, max=10, message="所有子元素个数 1:是 0:否长度必须介于 0 和 10 之间")
	public String getSubTotalNumber() {
		return subTotalNumber;
	}

	public void setSubTotalNumber(String subTotalNumber) {
		this.subTotalNumber = subTotalNumber;
	}
	
}