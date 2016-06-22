package com.jims.sys.api;

import com.jims.common.data.StringData;
import com.jims.sys.entity.IdentityDict;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;

/**
 * 身份字典表api接口
 * Created by fyg on 2016/6/21.
 */
public interface IdentityDictApi {

    /**
     * 查询所有记录
     * @return 身份字典的list集合
     * @author fengyuguang
     */
    public List<IdentityDict> findList();

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<IdentityDict> beanChangeVo);

    /**
     * 根据身份名称模糊查询记录
     * @param identityName 身份名称
     * @return
     * @author fengyuguang
     */
    public List<IdentityDict> search(String identityName);
}
