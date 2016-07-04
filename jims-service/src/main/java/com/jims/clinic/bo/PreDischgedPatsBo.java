package com.jims.clinic.bo;

import com.jims.clinic.dao.PreDischgedPatsDao;
import com.jims.clinic.entity.PreDischgedPats;
import com.jims.clinic.vo.PreDischgedPatsVo;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 出院通知单Service
 *
 * @author qinlongxin
 * @version 2016-06-02
 */
@Service
@Transactional(readOnly = false)
public class PreDischgedPatsBo extends CrudImplService<PreDischgedPatsDao, PreDischgedPats> {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private PreDischgedPatsDao preDischgedPatsDao;

    /**
     * 保存出院通知单
     * @param list
     * @return
     */
    @Transactional(readOnly = false)
    public String savePreDischPat(List<PreDischgedPatsVo> list) {
        int num = 0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                PreDischgedPatsVo preDischgedPatsVo = new PreDischgedPatsVo();
                preDischgedPatsVo = list.get(i);
                Orders orders = new Orders();
                Integer orderNo = ordersDao.getMaxOrderNo(preDischgedPatsVo.getPatientId(),"1");
               if(orderNo==null){
                   orders.setOrderNo(1);
                   orders.setOrderSubNo(1);
               }else{
                   orders.setOrderNo(orderNo+1);
                   orders.setOrderSubNo(orderNo+1);
               }
                orders.setPatientId(preDischgedPatsVo.getPatientId());
                orders.setVisitId(preDischgedPatsVo.getVisitId());

                orders.setStartDateTime(preDischgedPatsVo.getDischargeDateExpcted());
                orders.setRepeatIndicator("0");//临时医嘱
                orders.setOrderClass("Z");
                orders.setOrderStatus("6");
                orders.setOrderText(preDischgedPatsVo.getOrderText());
                orders.setOrderCode(preDischgedPatsVo.getOrderCode());
                orders.setOrderingDept(preDischgedPatsVo.getDeptCode());
                orders.setDoctor("当前登录者");
                orders.setDoctorUser(Long.parseLong("2"));
                preDischgedPatsVo.getDischargeDispositionName();
                orders.setEnterDateTime(new Date());
                orders.setBillingAttr(3);
                orders.setDrugBillingAttr(3);
                orders.setAppNo("");
                orders.setFreqDetail("");
                orders.setOrderingDept(preDischgedPatsVo.getDeptCode());
                if (orders.getIsNewRecord()) {
                    orders.preInsert();
                    ordersDao.insert(orders);
                }
                //保存出院通知单
                PreDischgedPats preDischgedPats = new PreDischgedPats();
                preDischgedPats.setPatientId(preDischgedPatsVo.getPatientId());
                preDischgedPats.setOrgId("1");
                preDischgedPats.setHospitalId("1");
                preDischgedPats.setVisitId("1");
                preDischgedPats.setOrdersId(orders.getId());
                preDischgedPats.setDischargeDispositionName(preDischgedPatsVo.getDischargeDispositionName());
                preDischgedPats.setDischargeDateExpcted(preDischgedPatsVo.getDischargeDateExpcted());
                preDischgedPats.setCreateDateTime(new Date());
                preDischgedPats.setOrderNo( String.valueOf(orders.getOrderNo()));
                if (preDischgedPats.getIsNewRecord()) {
                    preDischgedPats.preInsert();
                    num = preDischgedPatsDao.insert(preDischgedPats);
                } else {
                    preDischgedPats.preUpdate();
                    preDischgedPatsDao.update(preDischgedPats);
                }
            }
        }
        return num+"";
    }

    /**
     * 查询待出院的患者的信息集合
     *
     * @param wardCode 实体类型
     * @return
     * @author qinlongxin
     */
    public List<PreDischgedPatsVo> findPreDischList(String wardCode) {
        return preDischgedPatsDao.findPreDischList(wardCode);
    }


    /**
     * 查询科室下所有的在院病人信息
     * @param patientId
     * @param wardCode
     * @author pq
     * @return
     */
    public Page<PreDischgedPatsVo> findPreList(Page<PreDischgedPatsVo> page,String patientId,String wardCode){
        PreDischgedPatsVo preDischgedPatsVo = new PreDischgedPatsVo();
        preDischgedPatsVo.setPatientId(patientId);
        preDischgedPatsVo.setWardCode(wardCode);
        preDischgedPatsVo.setPage(page);
        page.setList(preDischgedPatsDao.findPreList(patientId,wardCode));
        return page;
    }
    /**
     * 删除出院通知单和医嘱
     *
     * @param hospitalId
     * @return
     * @author qinlongxin
     */
    public String delPats(String hospitalId) {
        int j=0;
        String[] id = hospitalId.split(",");
        for (int i = 0; i < hospitalId.length(); i++) {
            preDischgedPatsDao.delAll(id[i]);
            preDischgedPatsDao.delete(id[i]);
            j++;
        }
        return j+"";
    }
}