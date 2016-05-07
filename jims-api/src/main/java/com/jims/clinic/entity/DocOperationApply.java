/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;
import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 门诊申请表Entity
 * @author qlx
 * @version 2016-05-06
 */
public class DocOperationApply extends DataEntity<DocOperationApply> {
	
	private static final long serialVersionUID = 1L;
	private String inpNo;		// inp_no
	private String shouqianzhenduan;		// 术前诊断
	private String bingqing;		// 病情
	private String geli;		// 隔离
	private String jizhen;		// 急诊
	private Date yuyueDate;		// 预约时间
	private String shoushuGrade;		// 手续等级
	private String shoushushi;		// 手术室
	private String shoushujian;		// 手术间
	private String shoushuDoctor;		// 手术医生
	private String zhushounone;		// 助手医师
	private String zhushoutwo;		// 助手医师
	private String zhushouthree;		// 助手医师
	private String zhushoufour;		// 助手医师
	private String mazuifangfa;		// 麻醉方法
	private String mazuiyishi;		// 麻醉医师
	private String zhushou;		// 助手
	private String gongxueyishi;		// 供血医师
	private String beizhu;		// 备注
	private String remark;		// 备注
	private List<DocOperationGrade> list=new ArrayList<DocOperationGrade>();

	public DocOperationApply() {
		super();
	}

	public DocOperationApply(String id){
		super(id);
	}

	@Length(min=1, max=64, message="inp_no长度必须介于 1 和 64 之间")
	public String getInpNo() {
		return inpNo;
	}

	public void setInpNo(String inpNo) {
		this.inpNo = inpNo;
	}
	
	@Length(min=0, max=2000, message="术前诊断长度必须介于 0 和 2000 之间")
	public String getShouqianzhenduan() {
		return shouqianzhenduan;
	}

	public void setShouqianzhenduan(String shouqianzhenduan) {
		this.shouqianzhenduan = shouqianzhenduan;
	}
	
	@Length(min=0, max=2000, message="病情长度必须介于 0 和 2000 之间")
	public String getBingqing() {
		return bingqing;
	}

	public void setBingqing(String bingqing) {
		this.bingqing = bingqing;
	}
	
	@Length(min=0, max=100, message="隔离长度必须介于 0 和 100 之间")
	public String getGeli() {
		return geli;
	}

	public void setGeli(String geli) {
		this.geli = geli;
	}
	
	@Length(min=0, max=100, message="急诊长度必须介于 0 和 100 之间")
	public String getJizhen() {
		return jizhen;
	}

	public void setJizhen(String jizhen) {
		this.jizhen = jizhen;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getYuyueDate() {
		return yuyueDate;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public void setYuyueDate(Date yuyueDate) {
		this.yuyueDate = yuyueDate;
	}
	
	@Length(min=0, max=100, message="手续等级长度必须介于 0 和 100 之间")
	public String getShoushuGrade() {
		return shoushuGrade;
	}

	public void setShoushuGrade(String shoushuGrade) {
		this.shoushuGrade = shoushuGrade;
	}
	
	@Length(min=0, max=100, message="手术室长度必须介于 0 和 100 之间")
	public String getShoushushi() {
		return shoushushi;
	}

	public void setShoushushi(String shoushushi) {
		this.shoushushi = shoushushi;
	}
	
	@Length(min=0, max=100, message="手术间长度必须介于 0 和 100 之间")
	public String getShoushujian() {
		return shoushujian;
	}

	public void setShoushujian(String shoushujian) {
		this.shoushujian = shoushujian;
	}
	
	@Length(min=0, max=100, message="手术医生长度必须介于 0 和 100 之间")
	public String getShoushuDoctor() {
		return shoushuDoctor;
	}

	public void setShoushuDoctor(String shoushuDoctor) {
		this.shoushuDoctor = shoushuDoctor;
	}
	
	@Length(min=0, max=100, message="助手医师长度必须介于 0 和 100 之间")
	public String getZhushounone() {
		return zhushounone;
	}

	public void setZhushounone(String zhushounone) {
		this.zhushounone = zhushounone;
	}
	
	@Length(min=0, max=100, message="助手医师长度必须介于 0 和 100 之间")
	public String getZhushoutwo() {
		return zhushoutwo;
	}

	public void setZhushoutwo(String zhushoutwo) {
		this.zhushoutwo = zhushoutwo;
	}
	
	@Length(min=0, max=100, message="助手医师长度必须介于 0 和 100 之间")
	public String getZhushouthree() {
		return zhushouthree;
	}

	public void setZhushouthree(String zhushouthree) {
		this.zhushouthree = zhushouthree;
	}
	
	@Length(min=0, max=100, message="zhushoufour长度必须介于 0 和 100 之间")
	public String getZhushoufour() {
		return zhushoufour;
	}

	public void setZhushoufour(String zhushoufour) {
		this.zhushoufour = zhushoufour;
	}
	
	@Length(min=0, max=100, message="麻醉方法长度必须介于 0 和 100 之间")
	public String getMazuifangfa() {
		return mazuifangfa;
	}

	public void setMazuifangfa(String mazuifangfa) {
		this.mazuifangfa = mazuifangfa;
	}
	
	@Length(min=0, max=100, message="麻醉医师长度必须介于 0 和 100 之间")
	public String getMazuiyishi() {
		return mazuiyishi;
	}

	public void setMazuiyishi(String mazuiyishi) {
		this.mazuiyishi = mazuiyishi;
	}
	
	@Length(min=0, max=100, message="助手长度必须介于 0 和 100 之间")
	public String getZhushou() {
		return zhushou;
	}

	public void setZhushou(String zhushou) {
		this.zhushou = zhushou;
	}
	
	@Length(min=0, max=100, message="供血医师长度必须介于 0 和 100 之间")
	public String getGongxueyishi() {
		return gongxueyishi;
	}

	public void setGongxueyishi(String gongxueyishi) {
		this.gongxueyishi = gongxueyishi;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<DocOperationGrade> getList() {
		return list;
	}

	public void setList(List<DocOperationGrade> list) {
		this.list = list;
	}

}