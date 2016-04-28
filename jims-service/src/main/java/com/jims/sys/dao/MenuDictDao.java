/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.MenuDict;

import java.util.List;

/**
 * 系统菜单DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface MenuDictDao extends CrudDao<MenuDict> {
    /**
     * 保存方法（返回id）
     * @param menuDict
     */
    public Integer insertReturnObject(MenuDict menuDict);
    /**
     * 修改方法（返回id）
     * @param menuDict
     */
    public Integer updateReturnObject(MenuDict menuDict);
}