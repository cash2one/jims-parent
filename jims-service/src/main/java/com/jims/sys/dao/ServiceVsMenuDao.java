
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.ServiceVsMenu;

/**
 * 服务对应菜单表DAO接口
 * @author txb
 * @version 2016-05-31
 */
@MyBatisDao
public interface ServiceVsMenuDao extends CrudDao<ServiceVsMenu> {
	
}