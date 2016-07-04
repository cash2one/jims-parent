package com.jims.clinic.bo;

import com.jims.clinic.dao.PreDischgedPatsDao;
import com.jims.clinic.entity.PreDischgedPats;
import com.jims.clinic.vo.PreDischgedPatsVo;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = false)
    public String savePreDischPat(List<PreDischgedPatsVo> list) {
        String strState = "";
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                PreDischgedPatsVo preDischgedPatsVo = list.get(i);
                //保存之前先删除原有的通知单和医嘱
                preDischgedPatsDao.delAll(preDischgedPatsVo.getHospitalId());
                preDischgedPatsDao.delete(preDischgedPatsVo.getHospitalId());
                Orders orders = new Orders();
                orders.setOrderSubNo(i + 1);
                if (ordersDao.getOrderNo(preDischgedPatsVo.getPatientId(), preDischgedPatsVo.getVisitId(), preDischgedPatsVo.getId()) != null) {
                    orders.setOrderNo(ordersDao.getOrderNo(preDischgedPatsVo.getPatientId(), preDischgedPatsVo.getVisitId(), preDischgedPatsVo.getId()) + 1);
                } else {
                    orders.setOrderNo(1);
                }
                orders.setPatientId(preDischgedPatsVo.getPatientId());
                orders.setVisitId(preDischgedPatsVo.getVisitId());
                orders.setOrderNo(orders.getOrderNo());
                orders.setStartDateTime(preDischgedPatsVo.getAdmissionDateTime());
                orders.setRepeatIndicator("1");
                orders.setOrderClass("Z");
                orders.setOrderStatus("0");
                orders.setOrderingDept(preDischgedPatsVo.getDeptCode());
                orders.setDoctor(preDischgedPatsVo.getDoctor());
                orders.setDoctorUser(Long.parseLong("2"));
                orders.setEnterDateTime(preDischgedPatsVo.getAdmissionDateTime());
                orders.setBillingAttr(Integer.parseInt("2"));
                orders.setDrugBillingAttr(Integer.parseInt("2"));
                orders.setAppNo("");
                orders.setFreqDetail("");
                orders.setOrderingDept(preDischgedPatsVo.getDeptCode());
                if (orders.getIsNewRecord()) {
                    orders.preInsert();
                    ordersDao.insert(orders);
                } else {
                    orders.preUpdate();
                    ordersDao.update(orders);
                }
                //保存出院通知单
                PreDischgedPats preDischgedPats = new PreDischgedPats();
                preDischgedPats.setPatientId(preDischgedPatsVo.getPatientId());
                preDischgedPats.setHospitalId(preDischgedPatsVo.getHospitalId());
                preDischgedPats.setOrdersId(orders.getId());
                preDischgedPats.setOrgId("1");
                preDischgedPats.setVisitId("2");
                preDischgedPats.setOrders(orders);
                preDischgedPats.setDischargeDispositionName(preDischgedPatsVo.getDischargeDispositionName());
                strState = super.save(preDischgedPats);
                if (preDischgedPats.getIsNewRecord()) {
                    preDischgedPats.preInsert();
                    preDischgedPatsDao.insert(preDischgedPats);
                } else {
                    preDischgedPats.preUpdate();
                    preDischgedPatsDao.update(preDischgedPats);
                }
            }
        }
        return strState;
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