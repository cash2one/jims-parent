package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.PreDischgedPatsServiceApi;
import com.jims.clinic.bo.PreDischgedPatsBo;
import com.jims.clinic.entity.PreDischgedPats;
import com.jims.clinic.vo.PreDischgedPatsVo;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 出院通知单Service
 * @author qinlongxin
 * @version 2016-06-02
 */
@Service(version = "1.0.0")
public class PreDischgedPatsServiceImpl implements PreDischgedPatsServiceApi{
    @Autowired
    private PreDischgedPatsBo preDischgedPatsBo;
    /**
     * 获取出院通知单信息
     * @param id
     *  @author qinlongxin
     * @return
     */
    @Override
    public PreDischgedPats get(String id){
        return  preDischgedPatsBo.get(id);
    }
    /**
     * 出院通知单分页
     * @param page,preDischgedPats
     *  @author qinlongxin
     * @return
     */
    @Override
    public Page<PreDischgedPats> findPage(Page<PreDischgedPats> page,PreDischgedPats preDischgedPats){
       return preDischgedPatsBo.findPage(page, preDischgedPats);
    }
    /**
     * 删除出院通知单
     * @param ids 主键id集合
     *  @author qinlongxin
     * @return
     */
    @Override
    public String delete(String ids){
        return preDischgedPatsBo.delete(ids);
    }
    /**
     * 查询出院通知单动态行
     * @param wardCode 实体类型
     *  @author qinlongxin
     * @return
     */
    @Override
    public List<PreDischgedPatsVo> findPreDischList(String wardCode){
        return preDischgedPatsBo.findPreDischList(wardCode);
    }
    /**
     * 保存通知和医嘱
     * @param list 实体类型
     *  @author qinlongxin
     * @return
     */
    @Override
    public String save(List<PreDischgedPatsVo> list){
        return preDischgedPatsBo.savePreDischPat(list);
    }
    /**
     * 查询出院通知单动态行
     * @param preDischgedPats 实体类型
     *  @author qinlongxin
     * @return
     */
    @Override
    public String savePats(PreDischgedPats preDischgedPats){
        return preDischgedPatsBo.save(preDischgedPats);
    }
    /**
     * 查询出院通知单动态行
     * @param hospitalId
     *  @author qinlongxin
     * @return
     */
    @Override
    public String delPats(String hospitalId){
        return preDischgedPatsBo.delPats(hospitalId);
    }
}