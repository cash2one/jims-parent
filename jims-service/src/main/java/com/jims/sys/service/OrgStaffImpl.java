package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.service.ElectronEnterHospitalServiceImpl;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;

import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.dao.DictDao;
import com.jims.sys.dao.OrgStaffDao;
import com.jims.sys.dao.PersionInfoDao;
import com.jims.sys.dao.SysUserDao;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import com.jims.sys.vo.OrgStaffVo;
import com.sun.org.apache.bcel.internal.generic.I2D;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class OrgStaffImpl extends CrudImplService<OrgStaffDao, OrgStaff> implements OrgStaffApi {

    @Autowired
    private PersionInfoDao persionInfoDao;
    @Autowired
    private SysUserDao sysUserDao;


    /**
     * 多表分页查询  返回orgStaffVo
     *
     * @param page
     * @param orgStaffVo
     * @return
     */
    @Override
    public Page<OrgStaffVo> findPageByVo(Page<OrgStaffVo> page, OrgStaffVo orgStaffVo) {
        orgStaffVo.setPage(page);
        page.setList(dao.findListByVo(orgStaffVo));
        return page;
    }

    /**
     * 删除三张表的数据
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public String deleteByAll(String ids) {
        int i = 0;
        try {
                dao.deleteByPid(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 添加组织机构人员
     * 向orgStaff表中插入一条记录，并且向persionInfo，sysUser表中插入或更新
     *
     * @param persionInfo
     * @param sysUser
     * @param orgStaff
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public String insertOrgStaffAndPersion(PersionInfo persionInfo, SysUser sysUser, OrgStaff orgStaff) {
        PersionInfo oldPersion = new PersionInfo();

        if (StringUtils.isNotBlank(persionInfo.getId())) {
            boolean flag = false;
            oldPersion = persionInfoDao.get(persionInfo.getId());

            persionInfoDao.updateById(persionInfo);

            OrgStaff neworgStaff = dao.getByPersionId(persionInfo.getId());
            System.out.print("对象是"+neworgStaff);
            OrgStaff of=null;
            if(neworgStaff==null)
            {
                of=new OrgStaff();
                of.setTitle(orgStaff.getTitle());
            }

            if (of!=null) {

                //向orgStaff表中插入数据
                orgStaff.preInsert();
                dao.insert(orgStaff);

            } else {
                neworgStaff.setTitle(orgStaff.getTitle());
                neworgStaff.setDelFlag("0");
                //如果页面传递的科室和从数据库中查询的科室一样，说明不修改科室，直接更改
                if (StringUtils.equalsIgnoreCase(orgStaff.getDeptId(), neworgStaff.getDeptId())) {

                    dao.update(neworgStaff);
                } else {

                    //如果也面传递的科室和数据库中的科室不一样，说明要更改科室
                    neworgStaff.setDeptId(orgStaff.getDeptId());
                    dao.update(neworgStaff);
                }
            }
            //登录表中添加记录（身份证号）
            if (StringUtils.isNotBlank(persionInfo.getCardNo())) {
                SysUser sysUser1 = sysUserDao.findByLoginName(oldPersion.getCardNo(), persionInfo.getId());
                sysUser1.setLoginName(persionInfo.getCardNo());
                sysUser1.setPassword(sysUser.getPassword());

                sysUserDao.updateById(sysUser1);
            }
            //登录表中添加记录（联系电话）
            if (StringUtils.isNotBlank(persionInfo.getPhoneNum())) {
                SysUser sysUser1 = sysUserDao.findByLoginName(oldPersion.getPhoneNum(), persionInfo.getId());
                sysUser1.setLoginName(persionInfo.getPhoneNum());
                sysUser1.setPassword(sysUser.getPassword());
                sysUserDao.updateById(sysUser1);
            }
            //登录表中添加记录（邮箱）
            if (StringUtils.isNotBlank(persionInfo.getEmail())) {
                SysUser sysUser1 = sysUserDao.findByLoginName(oldPersion.getEmail(), persionInfo.getId());
                sysUser1.setLoginName(persionInfo.getEmail());
                sysUser1.setPassword(sysUser.getPassword());
                sysUserDao.updateById(sysUser1);
            }
            //登录表中添加记录（昵称）
            if (StringUtils.isNotBlank(persionInfo.getNickName())) {
                SysUser sysUser1 = sysUserDao.findByLoginName(oldPersion.getNickName(), persionInfo.getId());
                sysUser1.setLoginName(persionInfo.getNickName());
                sysUser1.setPassword(sysUser.getPassword());
                sysUserDao.updateById(sysUser1);
            }
            return "success";

        } else {
            persionInfo.preInsert();
            //向persionInfo表中插入数据
            persionInfoDao.register(persionInfo);
            //插入成功后返回的id
            String id = persionInfo.getId();
            //向orgStaff表中插入组织机构人员信息
            orgStaff.preInsert();
            orgStaff.setPersionId(id);
            dao.insert(orgStaff);
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

            return "success";
        }
    }

    /**
     * 通过persionId查询密码，并用于回显
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    @Override
    public SysUser findPasswordByPersionId(String persionId) {
        return sysUserDao.findPasswordByPersionId(persionId);
    }

    /**
     * 通过persionId查询title职称  ，用于回显数据
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    @Override
    public OrgStaff findTitleByPersionId(String persionId) {
        return dao.findTitleByPersionId(persionId);
    }
}
