package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 诊疗项目Entity
 * @author lgx
 * @version 2016-04-28
 */
public class ClinicItemDict extends DataEntity<ClinicItemDict> {
	
	private static final long serialVersionUID = 1L;
	private String itemClass;		// 项目分类
	private String itemCode;		// 项目代码
	private String itemName;		// 项目名称
	private String inputCode;		// 输入码
	private String inputCodeWb;		// input_code_wb
	private String expand1;		// 标本
	private String expand2;		// 类别
	private String expand3;		// 执行科室
	private String expand4;		// 频次
	private String expand5;		// 长期临时
	private String itemStatus;		// item_status
	private String memo;		// memo
	private String orgId;		// 所属组织结构

    //====不保存字段
    private String sumPrice;  //项目合计金额

    private Double num;  //诊疗项目的数量  不保存

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    private List<ClinicItemNameDict> saveNameList;  // 添加、修改的名称数据
    private List<ClinicVsCharge> saveVsList;  // 添加、修改的对照数据
    private String delNameIds;   // 删除的名称主键
    private String delVsIds;    // 删除的对照主键
    private String updateFlag;   //更新标志，值为 1 时不更新

    public List<ClinicItemNameDict> getSaveNameList() {
        return saveNameList;
    }

    public void setSaveNameList(List<ClinicItemNameDict> saveNameList) {
        this.saveNameList = saveNameList;
    }

    public List<ClinicVsCharge> getSaveVsList() {
        return saveVsList;
    }

    public void setSaveVsList(List<ClinicVsCharge> saveVsList) {
        this.saveVsList = saveVsList;
    }

    public String getDelNameIds() {
        return delNameIds;
    }

    public void setDelNameIds(String delNameIds) {
        this.delNameIds = delNameIds;
    }

    public String getDelVsIds() {
        return delVsIds;
    }

    public void setDelVsIds(String delVsIds) {
        this.delVsIds = delVsIds;
    }

    public ClinicItemDict() {
		super();
	}

	public ClinicItemDict(String id){
		super(id);
	}

	@Length(min=1, max=1, message="项目分类长度必须介于 1 和 1 之间")
	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}
	
	@Length(min=1, max=20, message="项目代码长度必须介于 1 和 20 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@Length(min=0, max=100, message="项目名称长度必须介于 0 和 100 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=0, max=8, message="输入码长度必须介于 0 和 8 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=8, message="input_code_wb长度必须介于 0 和 8 之间")
	public String getInputCodeWb() {
		return inputCodeWb;
	}

	public void setInputCodeWb(String inputCodeWb) {
		this.inputCodeWb = inputCodeWb;
	}
	
	@Length(min=0, max=100, message="标本长度必须介于 0 和 100 之间")
	public String getExpand1() {
		return expand1;
	}

	public void setExpand1(String expand1) {
		this.expand1 = expand1;
	}
	
	@Length(min=0, max=100, message="类别长度必须介于 0 和 100 之间")
	public String getExpand2() {
		return expand2;
	}

	public void setExpand2(String expand2) {
		this.expand2 = expand2;
	}
	
	@Length(min=0, max=100, message="执行科室长度必须介于 0 和 100 之间")
	public String getExpand3() {
		return expand3;
	}

	public void setExpand3(String expand3) {
		this.expand3 = expand3;
	}
	
	@Length(min=0, max=100, message="频次长度必须介于 0 和 100 之间")
	public String getExpand4() {
		return expand4;
	}

	public void setExpand4(String expand4) {
		this.expand4 = expand4;
	}
	
	@Length(min=0, max=100, message="长期临时长度必须介于 0 和 100 之间")
	public String getExpand5() {
		return expand5;
	}

	public void setExpand5(String expand5) {
		this.expand5 = expand5;
	}
	
	@Length(min=0, max=1, message="item_status长度必须介于 0 和 1 之间")
	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	@Length(min=0, max=60, message="memo长度必须介于 0 和 60 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=64, message="所属组织结构长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }
}