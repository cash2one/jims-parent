/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/jims.his.>JeeSite</a> All rights reserved.
 */
package com.jims.sys.vo;
import com.jims.clinic.entity.*;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;

import java.util.List;

/**
 * 检查预约记录Entity
 * @author zhangpeng
 * @version 2016-04-28
 */
public class ExamAppointsVo {
	private ExamAppoints examAppoints;
	private List<ExamItems> list;
	private List<OutpOrdersCosts> outpOrdersCostses;
	private List<OutpTreatRec> outpTreatRecs;
	private OutpOrders outpOrders;

	public ExamAppointsVo(){
		super();
	}

	public ExamAppoints getExamAppoints() {
		return examAppoints;
	}

	public void setExamAppoints(ExamAppoints examAppoints) {
		this.examAppoints = examAppoints;
	}

	public List<ExamItems> getList() {
		return list;
	}

	public void setList(List<ExamItems> list) {
		this.list = list;
	}

	public List<OutpOrdersCosts> getOutpOrdersCostses() {
		return outpOrdersCostses;
	}

	public void setOutpOrdersCostses(List<OutpOrdersCosts> outpOrdersCostses) {
		this.outpOrdersCostses = outpOrdersCostses;
	}

	public List<OutpTreatRec> getOutpTreatRecs() {
		return outpTreatRecs;
	}

	public void setOutpTreatRecs(List<OutpTreatRec> outpTreatRecs) {
		this.outpTreatRecs = outpTreatRecs;
	}

	public OutpOrders getOutpOrders() {
		return outpOrders;
	}

	public void setOutpOrders(OutpOrders outpOrders) {
		this.outpOrders = outpOrders;
	}
}