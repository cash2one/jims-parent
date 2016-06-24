package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.PersionInfoDao;
import com.jims.sys.dao.PersionServiceListDao;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.dao.SysUserDao;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.PersionServiceList;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by txb on 2016-06-17.
 */
@Service
@Component
@Transactional(readOnly = false)
public class PersonInfoBo extends CrudImplService<PersionInfoDao, PersionInfo> {


    @Autowired
    private PersionInfoDao persionInfoDao;

    @Autowired
    private SysServiceDao sysServiceDao;

    @Autowired
    private SysUserDao sysUserDao;


    /**
     * 用户注册
     * @param persionInfo
     * @return
     */

    public String register(PersionInfo persionInfo, SysUser sysUser) {

        persionInfo.preInsert();
        //向注册表中添加数据
        int i =persionInfoDao.register(persionInfo);

        String id = persionInfo.getId();
        //登录表中添加记录（身份证号）
        if (StringUtils.isNotBlank(persionInfo.getCardNo())) {
            sysUser.preInsert();
            sysUser.setPersionId(id);
            sysUser.setLoginName(persionInfo.getCardNo());
            sysUserDao.insert(sysUser);
        }
        //登录表中添加记录（手机号）
        if (StringUtils.isNotBlank(persionInfo.getPhoneNum())) {
            sysUser.preInsert();
            sysUser.setPersionId(id);
            sysUser.setLoginName(persionInfo.getPhoneNum());
            sysUserDao.insert(sysUser);
        }
        //登录表中添加记录（用户名）
        if (StringUtils.isNotBlank(persionInfo.getNickName())) {
            sysUser.preInsert();
            sysUser.setPersionId(id);
            sysUser.setLoginName(persionInfo.getNickName());
            sysUserDao.insert(sysUser);
        }
        //登录表中添加记录（邮箱）
        if (StringUtils.isNotBlank(persionInfo.getEmail())) {
            sysUser.preInsert();
            sysUser.setPersionId(id);
            sysUser.setLoginName(persionInfo.getEmail());
            sysUserDao.insert(sysUser);
        }

        if(i!=0)
        {
            return "success";
        }
        return null;

    }

    /**
     * 校验身份证号是否存在
     * @param persionInfo
     * @return
     */
    public PersionInfo getCard(PersionInfo persionInfo)
    {
        //验证数据是否正确
        if (StringUtils.isNotBlank(persionInfo.getCardNo())) {
            PersionInfo card = persionInfoDao.getCard(persionInfo);
            return card;
        }
        return null;
    }

    /**
     * 校验用户名是否存在
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getNick(PersionInfo persionInfo) {
        //验证数据是否正确
        if (StringUtils.isNotBlank(persionInfo.getNickName())) {
            PersionInfo nick = persionInfoDao.getNick(persionInfo);
            return nick;
        }
        return null;
    }

    /**
     * 校验手机号是否存在
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getPhone(PersionInfo persionInfo) {
        //验证数据是否正确
        if (StringUtils.isNotBlank(persionInfo.getPhoneNum())) {
            PersionInfo phone = persionInfoDao.getPhone(persionInfo);
            return phone;
        }
        return null;
    }

    /**
     * 校验邮箱是否存在
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getEmail(PersionInfo persionInfo) {
        //验证数据是否正确
        if (StringUtils.isNotBlank(persionInfo.getEmail())) {
            PersionInfo email = persionInfoDao.getEmail(persionInfo);
            return email;
        }
        return null;
    }

    /**
     * 根据身份证号查询相关的信息
     * @param cardNo
     * @return
     */

    public PersionInfo findInfoByCardNo(String cardNo) {
        return persionInfoDao.findInfoByCardNo(cardNo);
    }
}
