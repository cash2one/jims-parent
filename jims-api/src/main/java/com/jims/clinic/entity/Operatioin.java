/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 手术申请Entity
 * @author qlx
 * @version 2016-04-26
 */
public class Operatioin extends DataEntity<Operatioin> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 患者信息表主键id
	private String zhuyuanId;		// 住院表id
	private String bingqing;		// 病情
	private Date shoushuDate;		// 手术日期
	private String shoushushi;		// 手术室
	private String shoushujian;		// 手术间
	private String taici;		// 台次
	private String gelibiaozhi;		// 隔离标志
	private String taishoushuGrade;		// 台手术等级
	private String shoushuDept;		// 手术科室
	private String shoushuDoctor;		// 手术医师
	private Date shenqingDate;		// 申请时间
	private String zhushouone;		// 助手1
	private String zhushoutwo;		// 助手2
	private String zhushouthree;		// 助手3
	private String zhushoufour;		// 助手4
	private String shuxueysihi;		// 输血医师
	private String gongxuefangshi;		// 供血方式
	private String mazuiDoctor;		// 麻醉医师
	private String mazuifangfa;		// 麻醉方法
	private String beizhu;		// 备注
	private String shenqingDoctor;		// 申请医生
	private String beiyong1;		// beiyong1
	private String beiyong2;		// beiyong2
	private String beiyong3;		// beiyong3
	private String beiyong4;		// beiyong4
	private String pemarks;		// pemarks
	private OperationGrade operationGrade;
	private List<OperationGrade> list=new ArrayList<OperationGrade>();
	public Operatioin() {
		super();
	}

	public Operatioin(String id){
		super(id);
	}
	@Length(min=1, max=128, message="患者信息表主键id长度必须介于 1 和 128 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=0, max=128, message="住院表id长度必须介于 0 和 128 之间")
	public String getZhuyuanId() {
		return zhuyuanId;
	}

	public void setZhuyuanId(String zhuyuanId) {
		this.zhuyuanId = zhuyuanId;
	}
	
	@Length(min=0, max=128, message="病情长度必须介于 0 和 128 之间")
	public String getBingqing() {
		return bingqing;
	}

	public void setBingqing(String bingqing) {
		this.bingqing = bingqing;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getShoushuDate() {
		return shoushuDate;
	}

	public void setShoushuDate(Date shoushuDate) {
		this.shoushuDate = shoushuDate;
	}
	
	@Length(min=0, max=400, message="手术室长度必须介于 0 和 400 之间")
	public String getShoushushi() {
		return shoushushi;
	}

	public void setShoushushi(String shoushushi) {
		this.shoushushi = shoushushi;
	}
	
	@Length(min=0, max=400, message="手术间长度必须介于 0 和 400 之间")
	public String getShoushujian() {
		return shoushujian;
	}

	public void setShoushujian(String shoushujian) {
		this.shoushujian = shoushujian;
	}
	
	@Length(min=0, max=128, message="台次长度必须介于 0 和 128 之间")
	public String getTaici() {
		return taici;
	}

	public void setTaici(String taici) {
		this.taici = taici;
	}
	
	@Length(min=0, max=400, message="隔离标志长度必须介于 0 和 400 之间")
	public String getGelibiaozhi() {
		return gelibiaozhi;
	}

	public void setGelibiaozhi(String gelibiaozhi) {
		this.gelibiaozhi = gelibiaozhi;
	}
	
	@Length(min=0, max=400, message="台手术等级长度必须介于 0 和 400 之间")
	public String getTaishoushuGrade() {
		return taishoushuGrade;
	}

	public void setTaishoushuGrade(String taishoushuGrade) {
		this.taishoushuGrade = taishoushuGrade;
	}
	
	@Length(min=0, max=400, message="手术科室长度必须介于 0 和 400 之间")
	public String getShoushuDept() {
		return shoushuDept;
	}

	public void setShoushuDept(String shoushuDept) {
		this.shoushuDept = shoushuDept;
	}
	
	@Length(min=0, max=400, message="手术医生长度必须介于 0 和 400 之间")
	public String getShoushuDoctor() {
		return shoushuDoctor;
	}

	public void setShoushuDoctor(String shoushuDoctor) {
		this.shoushuDoctor = shoushuDoctor;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getShenqingDate() {
		return shenqingDate;
	}

	public void setShenqingDate(Date shenqingDate) {
		this.shenqingDate = shenqingDate;
	}
	
	@Length(min=0, max=400, message="助手1长度必须介于 0 和 400 之间")
	public String getZhushouone() {
		return zhushouone;
	}

	public void setZhushouone(String zhushouone) {
		this.zhushouone = zhushouone;
	}
	
	@Length(min=0, max=400, message="助手2长度必须介于 0 和 400 之间")
	public String getZhushoutwo() {
		return zhushoutwo;
	}

	public void setZhushoutwo(String zhushoutwo) {
		this.zhushoutwo = zhushoutwo;
	}
	
	@Length(min=0, max=400, message="助手3长度必须介于 0 和 400 之间")
	public String getZhushouthree() {
		return zhushouthree;
	}

	public void setZhushouthree(String zhushouthree) {
		this.zhushouthree = zhushouthree;
	}
	
	@Length(min=0, max=400, message="助手4长度必须介于 0 和 400 之间")
	public String getZhushoufour() {
		return zhushoufour;
	}

	public void setZhushoufour(String zhushoufour) {
		this.zhushoufour = zhushoufour;
	}
	
	@Length(min=0, max=400, message="输血医师长度必须介于 0 和 400 之间")
	public String getShuxueysihi() {
		return shuxueysihi;
	}

	public void setShuxueysihi(String shuxueysihi) {
		this.shuxueysihi = shuxueysihi;
	}
	
	@Length(min=0, max=400, message="供血方式长度必须介于 0 和 400 之间")
	public String getGongxuefangshi() {
		return gongxuefangshi;
	}

	public void setGongxuefangshi(String gongxuefangshi) {
		this.gongxuefangshi = gongxuefangshi;
	}
	
	@Length(min=0, max=400, message="麻醉医师长度必须介于 0 和 400 之间")
	public String getMazuiDoctor() {
		return mazuiDoctor;
	}

	public void setMazuiDoctor(String mazuiDoctor) {
		this.mazuiDoctor = mazuiDoctor;
	}
	
	@Length(min=0, max=400, message="麻醉方法长度必须介于 0 和 400 之间")
	public String getMazuifangfa() {
		return mazuifangfa;
	}

	public void setMazuifangfa(String mazuifangfa) {
		this.mazuifangfa = mazuifangfa;
	}
	
	@Length(min=0, max=400, message="备注长度必须介于 0 和 400 之间")
	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	@Length(min=0, max=400, message="申请医生长度必须介于 0 和 400 之间")
	public String getShenqingDoctor() {
		return shenqingDoctor;
	}

	public void setShenqingDoctor(String shenqingDoctor) {
		this.shenqingDoctor = shenqingDoctor;
	}
	
	@Length(min=0, max=400, message="beiyong1长度必须介于 0 和 400 之间")
	public String getBeiyong1() {
		return beiyong1;
	}

	public void setBeiyong1(String beiyong1) {
		this.beiyong1 = beiyong1;
	}
	
	@Length(min=0, max=400, message="beiyong2长度必须介于 0 和 400 之间")
	public String getBeiyong2() {
		return beiyong2;
	}

	public void setBeiyong2(String beiyong2) {
		this.beiyong2 = beiyong2;
	}
	
	@Length(min=0, max=400, message="beiyong3长度必须介于 0 和 400 之间")
	public String getBeiyong3() {
		return beiyong3;
	}

	public void setBeiyong3(String beiyong3) {
		this.beiyong3 = beiyong3;
	}
	
	@Length(min=0, max=400, message="beiyong4长度必须介于 0 和 400 之间")
	public String getBeiyong4() {
		return beiyong4;
	}

	public void setBeiyong4(String beiyong4) {
		this.beiyong4 = beiyong4;
	}
	
	@Length(min=0, max=450, message="pemarks长度必须介于 0 和 450 之间")
	public String getPemarks() {
		return pemarks;
	}

	public void setPemarks(String pemarks) {
		this.pemarks = pemarks;
	}
	public List<OperationGrade> getList() {
		return list;
	}

	public void setList(List<OperationGrade> list) {
		this.list = list;
	}
	public OperationGrade getOperationGrade() {
		return operationGrade;
	}

	public void setOperationGrade(OperationGrade operationGrade) {
		this.operationGrade = operationGrade;
	}
}