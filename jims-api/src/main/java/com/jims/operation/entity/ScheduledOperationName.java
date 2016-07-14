
package com.jims.operation.entity;


import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 手术安排名称Entity
 * @author pq
 * @version 2016-05-12
 */
public class ScheduledOperationName extends DataEntity<ScheduledOperationName> {
	
	private static final long serialVersionUID = 1L;
	private String  scheduleId;		// 手术安排标识
	private Integer operationNo;		// 手术序号
	private String operation;		// 手术
	private String operationScale;		// 手术等级
	private String operationCode;		// 手术编码
	private OperationSchedule operationSchedule;

	public ScheduledOperationName() {
		super();
	}

	public ScheduledOperationName(String id){
		super(id);
	}
	public String getScheduleId() {
		return scheduleId;
	}

	public OperationSchedule getOperationSchedule() {
		return operationSchedule;
	}

	public void setOperationSchedule(OperationSchedule operationSchedule) {
		this.operationSchedule = operationSchedule;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	@NotNull(message="手术序号不能为空")
	public Integer getOperationNo() {
		return operationNo;
	}

	public void setOperationNo(Integer operationNo) {
		this.operationNo = operationNo;
	}
	
	@Length(min=0, max=400, message="手术长度必须介于 0 和 400 之间")
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@Length(min=0, max=4, message="手术等级长度必须介于 0 和 4 之间")
	public String getOperationScale() {
		return operationScale;
	}

	public void setOperationScale(String operationScale) {
		this.operationScale = operationScale;
	}
	
	@Length(min=0, max=40, message="手术编码长度必须介于 0 和 40 之间")
	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}


}