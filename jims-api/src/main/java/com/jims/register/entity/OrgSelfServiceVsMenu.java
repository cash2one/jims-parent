package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 机构自定义服务对应菜单Entity
 * @author lgx
 * @version 2016-05-31
 */
public class OrgSelfServiceVsMenu extends DataEntity<OrgSelfServiceVsMenu> {
	
    private static final long serialVersionUID = 1L;
    private String selfServiceId;		// 服务ID
    private String menuId;		// 菜单ID
    private String menuSort;		// 菜单排序
    private String pid ;     // 父ID
    private Date menuEndDate;    //菜单结束时间
    private String careateBy;		// careate_by
    private String remark;		// remark
    private Date createTime;		// create_time
    private String menuOperate;

    private List<OrgSelfServiceVsMenu> children ; // 子节点
    private String menuName;  // 菜单名称

    public List<OrgSelfServiceVsMenu> getChildren() {
        return children;
    }

    public void setChildren(List<OrgSelfServiceVsMenu> children) {
        this.children = children;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public OrgSelfServiceVsMenu() {
		super();
	}

	public OrgSelfServiceVsMenu(String id){
		super(id);
	}

	public String getSelfServiceId() {
		return selfServiceId;
	}

	public void setSelfServiceId(String selfServiceId) {
		this.selfServiceId = selfServiceId;
	}


	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	public String getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(String menuSort) {
		this.menuSort = menuSort;
	}

    public String getCareateBy() {
		return careateBy;
	}

	public void setCareateBy(String careateBy) {
		this.careateBy = careateBy;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


    public Date getMenuEndDate() {
        return menuEndDate;
    }

    public void setMenuEndDate(Date menuEndDate) {
        this.menuEndDate = menuEndDate;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuOperate() {
        return menuOperate;
    }

    public void setMenuOperate(String menuOperate) {
        this.menuOperate = menuOperate;
    }
}