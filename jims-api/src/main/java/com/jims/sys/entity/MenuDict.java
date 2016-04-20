/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 系统菜单Entity
 * @author yangruidong
 * @version 2016-04-13
 */
public class MenuDict extends DataEntity<MenuDict> {
	
	private static final long serialVersionUID = 1L;
	private String menuName;		// 菜单名称
	private String href;		// 链接地址
	private String icon;		// 图标
	private Long sort;		// 排序
	private String target;		// 打开方式
	
	public MenuDict() {
		super();
	}

	public MenuDict(String id){
		super(id);
	}

	@Length(min=0, max=20, message="菜单名称长度必须介于 0 和 20 之间")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	@Length(min=0, max=300, message="链接地址长度必须介于 0 和 300 之间")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	@Length(min=0, max=100, message="图标长度必须介于 0 和 100 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=100, message="打开方式长度必须介于 0 和 100 之间")
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
}