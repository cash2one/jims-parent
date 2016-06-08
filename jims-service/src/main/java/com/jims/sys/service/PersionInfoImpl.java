package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.bo.PersionServiceListBo;
import com.jims.sys.dao.PersionInfoDao;
import com.jims.sys.dao.PersionServiceListDao;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.dao.SysUserDao;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.PersionServiceList;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yangruidong on 2016/4/13 0013.
 */
@Service(version = "1.0.0")
public class PersionInfoImpl extends CrudImplService<PersionInfoDao, PersionInfo> implements PersionInfoApi {

    @Autowired
    private PersionInfoDao persionInfoDao;

    @Autowired
    private SysServiceDao sysServiceDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private PersionServiceListDao persionServiceListDao;


    /**
     * 用户注册
     * @param persionInfo
     * @return
     */
    @Override
    public String register(PersionInfo persionInfo, SysUser sysUser) {

        persionInfo.preInsert();
        //向注册表中添加数据
        int i =persionInfoDao.register(persionInfo);

        String id = persionInfo.getId();
        String serviceType="0";
        String serviceClass ="1";
        List<SysService> listService=sysServiceDao.serviceListByTC(serviceType,serviceClass);
        PersionServiceList persionServiceList=new PersionServiceList();
        if(listService!=null)
        {
            for(int j=0;j<listService.size();j++)
            {
                persionServiceList.preInsert();
                persionServiceList.setFlag("0");
                persionServiceList.setServiceStartDate(new Date());
                persionServiceList.setServiceEndDate(null);
                persionServiceList.setPersionId(id);
                persionServiceList.setServiceId(listService.get(j).getId());
                persionServiceListDao.insert(persionServiceList);
            }
        }
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
    @Override
    public PersionInfo findInfoByCardNo(String cardNo) {
        return persionInfoDao.findInfoByCardNo(cardNo);
    }


}
