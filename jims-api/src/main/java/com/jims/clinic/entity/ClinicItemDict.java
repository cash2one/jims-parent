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
    private String expand1Name; // 标本名称
    private String expand2Name; // 类别名称
    private String expand3Name; // 执行科室名称
    private String expand4Name; // 频次名称
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

	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getInputCodeWb() {
		return inputCodeWb;
	}

	public void setInputCodeWb(String inputCodeWb) {
		this.inputCodeWb = inputCodeWb;
	}

	public String getExpand1() {
		return expand1;
	}

	public void setExpand1(String expand1) {
		this.expand1 = expand1;
	}

	public String getExpand2() {
		return expand2;
	}

	public void setExpand2(String expand2) {
		this.expand2 = expand2;
	}

	public String getExpand3() {
		return expand3;
	}

	public void setExpand3(String expand3) {
		this.expand3 = expand3;
	}

	public String getExpand4() {
		return expand4;
	}

	public void setExpand4(String expand4) {
		this.expand4 = expand4;
	}

	public String getExpand5() {
		return expand5;
	}

	public void setExpand5(String expand5) {
		this.expand5 = expand5;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

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

    public String getExpand4Name() {
        return expand4Name;
    }

    public void setExpand4Name(String expand4Name) {
        this.expand4Name = expand4Name;
    }

    public String getExpand1Name() {
        return expand1Name;
    }

    public void setExpand1Name(String expand1Name) {
        this.expand1Name = expand1Name;
    }

    public String getExpand2Name() {
        return expand2Name;
    }

    public void setExpand2Name(String expand2Name) {
        this.expand2Name = expand2Name;
    }

    public String getExpand3Name() {
        return expand3Name;
    }

    public void setExpand3Name(String expand3Name) {
        this.expand3Name = expand3Name;
    }
}