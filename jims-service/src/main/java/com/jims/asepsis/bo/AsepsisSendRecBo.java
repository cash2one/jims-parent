package com.jims.asepsis.bo;

import com.jims.asepsis.dao.AsepsisAntiRecDao;
import com.jims.asepsis.dao.AsepsisStockDao;
import com.jims.asepsis.entity.AsepsisAntiRec;
import com.jims.asepsis.entity.AsepsisSendRec;
import com.jims.asepsis.dao.AsepsisSendRecDao;
import com.jims.asepsis.entity.AsepsisStock;
import com.jims.asepsis.vo.AsepsisVo;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* 送物领物bo
* @author lgx
* @version 2016-06-27
*/
@Service
@Transactional(readOnly = false)
public class AsepsisSendRecBo extends CrudImplService<AsepsisSendRecDao, AsepsisSendRec>{

    @Autowired
    private AsepsisStockDao stockDao;
    @Autowired
    private AsepsisAntiRecDao antiDao;
    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<AsepsisSendRec> list) {
        if(list != null && list.size() > 0) {
            for(AsepsisSendRec entity : list) {
                if(!entity.getIsNewRecord()){
                    Integer stock = entity.getStock() == null ? 0 : entity.getStock();
                    AsepsisStock stockParam = new AsepsisStock();
                    stockParam.setFromDept(entity.getFromDept());
                    stockParam.setDocumentNo(entity.getDocumentNo());
                    stockParam.setOrgId(entity.getOrgId());
                    List<AsepsisStock> stocks = stockDao.findListNoJoin(stockParam);
                    for(int i=0;i<stocks.size();i++){
                        AsepsisStock asepsisStock = stocks.get(i);
                        Integer amount =  asepsisStock.getAmount() == null ? 0 : asepsisStock.getAmount().intValue();
                        if(amount > stock){
                            asepsisStock.setAmount(amount - stock.doubleValue());
                            asepsisStock.preUpdate();
                            stockDao.update(asepsisStock);
                            break;
                        } else if(amount == stock){
                            stockDao.delete(asepsisStock.getId());
                            break;
                        } else {
                            stock = stock - amount;
                            stockDao.delete(asepsisStock.getId());
                        }
                    }
                } else {
                    AsepsisAntiRec anti = new AsepsisAntiRec();
                    anti.setImpDate(entity.getSendDate());
                    anti.preInsert();
                    anti.setDocumnetNo(entity.getDocumentNo());
                    anti.setAsepsisCode(entity.getItemCode());
                    anti.setAsepsisName(entity.getItemName());
                    anti.setAsepsisSpec(entity.getItemSpec());
                    anti.setUnits(entity.getUnits());
                    anti.setBelongDept(entity.getFromDept());
                    anti.setAsepsisState("0");
                    anti.setAmount(entity.getSendAmount().intValue());
                    anti.setOrgId(entity.getOrgId());
                    anti.setItemNo(1);
                    antiDao.insert(anti);
                }
                save(entity);
            }
        }
    }

    /**
     * 保存  增删改
     *
     * @param asepsisVo
     * @return
     * @author yangruidong
     */
    public List<AsepsisSendRec> saveAll(AsepsisVo<AsepsisSendRec> asepsisVo) {
        List<AsepsisSendRec> newUpdateDict = new ArrayList<AsepsisSendRec>();
        List<AsepsisSendRec> updated = asepsisVo.getUpdated();
        //更新
        for (AsepsisSendRec asepsisSendRec : updated) {
            asepsisSendRec.preUpdate();
            int num = dao.update(asepsisSendRec);
        }
        return newUpdateDict;
    }

    /**
     * 获取当天最大的编码
     * @param orgId
     * @return
     */
    public String getMaxDocumentNo(String orgId){
        return dao.getMaxDocumentNo(orgId);
    }

    /**
     * 检索有库存、在保质期内的数据
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListWithStock(AsepsisSendRec entity){
        return dao.findListWithStock(entity);
    }

    /**
     * 检索没有库存、在保质期内的数据
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListNoStock(AsepsisSendRec entity){
        return dao.findListNoStock(entity);
    }
}