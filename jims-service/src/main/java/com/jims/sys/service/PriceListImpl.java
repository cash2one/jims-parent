package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.common.utils.IdGen;
import com.jims.sys.api.PriceListApi;
import com.jims.sys.bo.PriceListServiceBo;
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
    private PriceListServiceBo priceListServiceBo;

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public PriceList get(String id){
        return priceListServiceBo.get(id);
    }

    /**
     * 获取多条数据
     *
     * @param priceList
     * @return
     */
    public List<PriceList> findList(PriceList priceList){
        return priceListServiceBo.findList(priceList);
    }

    /**
     * 保存修改数据
     *
     * @param priceList
     */
    public String save(PriceList priceList){
        return priceListServiceBo.save(priceList);
    }

    /**
     * 查询字段列表
     *
     * @param page
     * @param priceList
     * @return
     */
    public Page<PriceList> findPage(Page<PriceList> page, PriceList priceList){
        return priceListServiceBo.findPage(page,priceList);
    }

    /**
     *  保存数据
     * @param dictListVo
     * @return
     */
    public String save(PriceDictListVo dictListVo) {
       return priceListServiceBo.save(dictListVo);
    }

    /**
     * 查询序列
     *
     * @return
     */
    public String findSeqences() {
        return priceListServiceBo.findSeqences();
    }

    /**
     * 通过拼音码查询数据
     *
     * @param inputCode
     * @return
     */
    public List<PriceList> findCode(String inputCode) {
        return priceListServiceBo.findCode(inputCode);
    }

    /**
     * 现行价格表
     *
     * @param page
     * @param priceListVo
     * @return
     * @author wei
     */
    public Page<PriceListVo> findPage(String orgId, Page<PriceListVo> page, PriceListVo priceListVo) {
        return priceListServiceBo.findPage(orgId, page, priceListVo);
    }

    /**
     * 历史价格表
     *
     * @param page
     * @param priceListVo
     * @return
     * @author wei
     */
    public Page<PriceListVo> findOLdPage(String orgId, Page<PriceListVo> page, PriceListVo priceListVo) {
        return priceListServiceBo.findOLdPage(orgId, page, priceListVo);
    }

    /**
     * 拼音码查询现行价表
     *
     * @param inputCode
     * @param label
     * @return
     * @author wei
     */
    public List<PriceListVo> getInputCodeNow(String orgId, String inputCode, String label) {
        return priceListServiceBo.getInputCodeNow(orgId, inputCode, label);
    }

    /**
     * 拼音码查询历史价表
     *
     * @param orgId
     * @param inputCode
     * @param label
     * @return
     */
    public List<PriceListVo> getInputCodeOld(String orgId, String inputCode, String label) {
        return priceListServiceBo.getInputCodeOld(orgId, inputCode, label);
    }

    /**
     * 下拉框查询药品类别
     *
     * @return
     * @author wei
     */
    public List<PriceListVo> list() {
        return priceListServiceBo.list();
    }


    /**
     * 根据诊疗项目获取诊疗项目所对应的价表项目
     *
     * @param orgId
     * @param clinicItemCode
     * @return
     */
    public List<PriceListVo> getListByClinicItemCodeAndOrgId(String orgId, String clinicItemCode) {
        return priceListServiceBo.getListByClinicItemCodeAndOrgId(orgId, clinicItemCode);
    }

    /**
     * 删除数据
     *
     * @param priceList
     */
    public String delete(PriceList priceList){
        return priceListServiceBo.delete(priceList);
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public String delete(String id){
        return priceListServiceBo.delete(id);
    }
}