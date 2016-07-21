package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.common.utils.IdGen;
import com.jims.sys.api.PriceListApi;
import com.jims.sys.bo.PriceListBo;
import com.jims.sys.dao.PriceItemNameDictDao;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.entity.PriceItemNameDict;
import com.jims.sys.entity.PriceList;
import com.jims.sys.vo.PriceDictListVo;
import com.jims.sys.vo.PriceListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;


import java.util.List;


/**
 * 价格表Service
 *
 * @author 罗海昆
 * @version 2016-04-26
 */
@Service(version = "1.0.0")
public class PriceListImpl implements PriceListApi {

    @Autowired
    private PriceListBo bo;

    @Override
    public PriceList get(String id) {
        return bo.get(id);
    }

    @Override
    public List<PriceList> findList(PriceList priceList) {
        return bo.findList(priceList);
    }

    @Override
    public Page<PriceList> findPage(Page<PriceList> page, PriceList priceList) {
        return bo.findPage(page, priceList);
    }

    @Override
    public String save(PriceList priceList) {
        try {
            bo.save(priceList);
            return "1";
        } catch (Exception e) {
        }
        return "0";
    }

    /**
     * 根据类别查询价表
     * @param itemClass 类别
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<PriceList> findByItemClass(String itemClass, String orgId){
        return bo.findByItemClass(itemClass,orgId);
    }

    /**
     * 根据输入码查询价表数据
     * @param inputCode 输入码
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<PriceList> getByInputCode(String inputCode, String orgId){
        return bo.getByInputCode(inputCode,orgId);
    }

    /**
     * 价表的保存
     * @param dictListVo
     * @return
     */
    @Override
    public String saveData(PriceDictListVo dictListVo) {
        return bo.saveData(dictListVo);
    }

    /**
     * 修改价表
     * @param priceDictListVo
     * @return
     * @author fengyuguang
     */
    public String updatePrice(PriceDictListVo priceDictListVo){
        return bo.updatePrice(priceDictListVo);
    }

    /**
     * 保存调价
     * @param priceDictListVo
     * @return
     * @author fengyuguang
     */
    public String saveAdjustPrice(PriceDictListVo priceDictListVo){
        return bo.saveAdjustPrice(priceDictListVo);
    }

    @Override
    public String delete(PriceList priceList) {
        try {
            bo.delete(priceList);
            return "1";
        } catch (Exception e) {
        }
        return "0";
    }

    @Override
    public String delete(String id) {
        try {
            bo.delete(id);
            return "1";
        } catch (Exception e) {
        }
        return "0";
    }

    /**
     * 查询序列
     *
     * @return
     */
    public String findSeqences() {
        return bo.findSeqences();
    }

    /**
     * 通过拼音码查询数据
     *
     * @param inputCode
     * @return
     */
    public List<PriceList> findCode(String inputCode){
        return  bo.findCode(inputCode);
    }
    /**
     * 现行价格表
     * @param page
     * @param priceListVo
     * @return
     * @author wei
     */
    @Override
    public Page<PriceListVo> findPage(String orgId,Page<PriceListVo> page, PriceListVo priceListVo) {
        return bo.findPage(orgId, page, priceListVo);
    }

    /**
     * 历史价格表
     * @param page
     * @param priceListVo
     * @return
     * @author wei
     */
    @Override
    public Page<PriceListVo> findOLdPage(String orgId,Page<PriceListVo> page, PriceListVo priceListVo) {
        return bo.findOLdPage(orgId, page, priceListVo);
    }

    /**
     * 拼音码查询现行价表
     * @param inputCode
     * @param label
     * @return
     * @author wei
     */
    @Override
    public List<PriceListVo> getInputCodeNow(String orgId,String inputCode,String label) {
        return bo.getInputCodeNow(orgId, inputCode, label);
    }

    /**
     * 拼音码查询历史价表
     * @param orgId
     * @param inputCode
     * @param label
     * @return
     */
    @Override
    public List<PriceListVo> getInputCodeOld(String orgId, String inputCode, String label) {
        return bo.getInputCodeOld(orgId, inputCode, label);
    }

    /**
     * 下拉框查询药品类别
     * @return
     * @author wei
     */
    @Override
    public List<PriceListVo> list() {
        return bo.list();
    }


    /**
     * 根据诊疗项目获取诊疗项目所对应的价表项目
     * @param orgId
     * @param clinicItemCode
     * @return
     */
    @Override
    public List<PriceListVo> getListByClinicItemCodeAndOrgId(String orgId, String clinicItemCode) {
        return bo.getListByClinicItemCodeAndOrgId(orgId, clinicItemCode);
    }

    /**
     * 调价通知单
     * @param label
     * @param startDate
     * @param stopDate
     * @param orgId
     * @return
     */
    public  List<PriceList> priceNotice(String label,String startDate,String stopDate,String orgId){
        return bo.priceNotice(label,startDate,stopDate,orgId);
    };
}