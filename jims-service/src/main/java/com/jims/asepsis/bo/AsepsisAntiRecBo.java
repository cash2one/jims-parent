package com.jims.asepsis.bo;

import com.jims.asepsis.dao.AsepsisAntiRecDao;
import com.jims.asepsis.dao.AsepsisStockDao;
import com.jims.asepsis.entity.AsepsisAntiRec;
import com.jims.asepsis.entity.AsepsisLendRec;
import com.jims.asepsis.entity.AsepsisSendRec;
import com.jims.asepsis.entity.AsepsisStock;
import com.jims.asepsis.vo.AsepsisAntiRecVo;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Auser on
 * 消毒包处理Service
 *
 * @author louhuili
 * @version 2016/6/27.
 */
@Service
@Transactional(readOnly = false)
public class AsepsisAntiRecBo extends CrudImplService<AsepsisAntiRecDao, AsepsisAntiRec> {

    @Autowired
    private AsepsisAntiRecDao asepsisAntiRecDao;
    @Autowired
    private AsepsisStockBo asepsisStockBo;
    @Autowired
    private AsepsisSendRecBo asepsisSendRecBo;
    @Autowired
    private AsepsisLendRecBo asepsisLendRecBo;

    /**
     * 查询某状态下的消毒包（无菌物品的信息）集合
     * @return
     * @author louhuili
     */
    public List<AsepsisAntiRec> getAsepsisAntiRecByState(AsepsisAntiRec aar) {
        return asepsisAntiRecDao.getAsepsisAntiRecByState(aar);
    }
    /**
     * 无菌物品消毒包管理(清洗，打包，灭菌)(修改)
     * @param asepsisAntiRec
     * @return int
     * @author louhuili
     */
    public int saveClean(AsepsisAntiRec asepsisAntiRec){
        int num = 0;
        if(asepsisAntiRec.getAmountAnti()!=null&&!asepsisAntiRec.getAmountAnti().equals("0")&&!asepsisAntiRec.getAmountAnti().equals("0.0")&&!asepsisAntiRec.getAmountAnti().equals("0.00")
                &&asepsisAntiRec.getAntiOperator()!=null&&!asepsisAntiRec.getAntiOperator().equals("")&&asepsisAntiRec.getAntiWays()!=null&&!asepsisAntiRec.getAntiWays().equals("")){
//            asepsisAntiRec.setAntiBatchNo(asepsisAntiRec.getAsepsisCode().substring(0, 1) + (System.currentTimeMillis() + "").substring(0, 10));
            num = asepsisAntiRecDao.saveClean(asepsisAntiRec);

            /**
             *  当且仅当是灭菌时才会需要下面的功能：
             * 1、若灭菌数量小于总数量，需要在上面的基础上，再在asepsis_Anti_Rec 表中添加一条数据
             * 2、在asepsis_stock 表中添加一条记录
             * 3、修改asepsis_send_rec 表
             */
            if(asepsisAntiRec.getAmount()-asepsisAntiRec.getAmountAnti()>0){
                asepsisAntiRec.setIsNewRecord(true);
                asepsisAntiRec.setAsepsisState("2");
                asepsisAntiRec.setAmount(asepsisAntiRec.getAmount()-asepsisAntiRec.getAmountAnti());
                asepsisAntiRec.setBoilerTimes(null);
                asepsisAntiRec.setBoilerNo(null);
                asepsisAntiRec.setAntiDate(null);
                asepsisAntiRec.setAntiOperator(null);
                asepsisAntiRec.setAntiWays(null);
                save(asepsisAntiRec); 
            }
//insert into ASEPSIS_STOCK ( DOCUMENT_NO , FROM_DEPT , ITEM_CODE , ITEM_NAME , ITEM_SPEC , AMOUNT , UNITS , ANTI_DATE , OPERATOR , ANTI_BATCH_NO , item_no ) values
//                  ( '1606301607' , '1506' , 'PCF0000012' , '拆线包（赔偿）' , '标准' , '1.00' , '套' , '2016-06-30 15:35:02' , '000XHH' , '1606301607' , 909115393 )
            AsepsisStock asepsisStock = new AsepsisStock();

            //供应室的物品灭菌之后加库存时该单号为消毒批号，其他科室的灭菌后加库存时该单号为送物单号
            asepsisStock.setDocumentNo((asepsisAntiRec.getDocumnetNo().startsWith("S")||asepsisAntiRec.getDocumnetNo().startsWith("T"))?asepsisAntiRec.getDocumnetNo():asepsisAntiRec.getAntiBatchNo());
            asepsisStock.setFromDept(asepsisAntiRec.getBelongDept());
            asepsisStock.setItemCode(asepsisAntiRec.getAsepsisCode());
            asepsisStock.setItemName(asepsisAntiRec.getAsepsisName());
            asepsisStock.setItemSpec(asepsisAntiRec.getAsepsisSpec());
            asepsisStock.setAmount((double) asepsisAntiRec.getAmount());
            asepsisStock.setUnits(asepsisAntiRec.getUnits());
            asepsisStock.setAntiDate(new Date());
            asepsisStock.setOperator(asepsisAntiRec.getAntiOperator());
            asepsisStock.setAntiBatchNo(asepsisAntiRec.getAntiBatchNo());
            asepsisStock.setItemNo((double) asepsisAntiRec.getItemNo());
            asepsisStock.setOrgId(asepsisAntiRec.getOrgId());
            asepsisStock.setDelFlag("0");
            asepsisStockBo.save(asepsisStock);
//update ASEPSIS_SEND_REC set GET_FLAG ='2' where document_no ='' and from_dept ='1506' and item_code ='PCF0000012'
            //if(当前所属科室belongDept是供应室，就不需要修改下面所有的语句了，因为送物领物是指的所属科室为其他科室){}
            if (asepsisAntiRec.getDocumnetNo().startsWith("S")){
                AsepsisSendRec asepsisSendRec = new AsepsisSendRec();
                asepsisSendRec.setDocumentNo(asepsisAntiRec.getDocumnetNo());
                asepsisSendRec.setFromDept(asepsisAntiRec.getBelongDept());
                asepsisSendRec.setItemCode(asepsisAntiRec.getAsepsisCode());
                asepsisSendRec.setOrgId(asepsisAntiRec.getOrgId());
                List<AsepsisSendRec> las = asepsisSendRecBo.findListNoId(asepsisSendRec);
                if(las!=null&&las.size()>0){
                    for(int i = 0;i<las.size();i++){
                        asepsisSendRec = las.get(i);
                        asepsisSendRec.setGetFlag("2");//送物领物
                        asepsisSendRecBo.save(asepsisSendRec);
                    }

                }
            }else if (asepsisAntiRec.getDocumnetNo().startsWith("T")){
                AsepsisLendRec asepsisLendRec = new AsepsisLendRec();
                asepsisLendRec.setDocumentNo(asepsisAntiRec.getDocumnetNo());
                asepsisLendRec.setToDept(asepsisAntiRec.getBelongDept());
                asepsisLendRec.setItemCode(asepsisAntiRec.getAsepsisCode());
                asepsisLendRec.setOrgId(asepsisAntiRec.getOrgId());
                List<AsepsisLendRec> las = asepsisLendRecBo.findList(asepsisLendRec);
                if(las!=null&&las.size()>0){
                    for(int i = 0;i<las.size();i++){
                        asepsisLendRec = las.get(i);
                        asepsisLendRec.setReturnFlag("4");//包对换
                        asepsisLendRecBo.save(asepsisLendRec);
                    }

                }
            }
        }else{
            num = asepsisAntiRecDao.saveClean(asepsisAntiRec);
        }
        return num;
    };
    /**
     * 无菌物品消毒包管理(清洗，打包，灭菌)(新增，修改，删除)
     * @param asepsisAntiRecVo
     * @return int
     * @author louhuili
     */
    public int saveAll(AsepsisAntiRecVo<AsepsisAntiRec> asepsisAntiRecVo){
        List<AsepsisAntiRec> newUpdateDict = new ArrayList<AsepsisAntiRec>();
        List<AsepsisAntiRec> inserted = asepsisAntiRecVo.getInserted();
        List<AsepsisAntiRec> updated = asepsisAntiRecVo.getUpdated();
        List<AsepsisAntiRec> deleted = asepsisAntiRecVo.getDeleted();
        int num = 0;
        //插入
        for (AsepsisAntiRec asepsisAntiRec : inserted) {
            asepsisAntiRec.preInsert();
            asepsisAntiRec.setOrgId(asepsisAntiRecVo.getOrgId());
            num += dao.insert(asepsisAntiRec);
        }
        //更新
        for (AsepsisAntiRec asepsisAntiRec : updated) {
            asepsisAntiRec.preUpdate();
            num +=  dao.update(asepsisAntiRec);
        }
        //删除
        Map<String,String> ids = new HashMap<String, String>();

        for (AsepsisAntiRec asepsisAntiRec : deleted) {
            ids.put(asepsisAntiRec.getId(), asepsisAntiRec.getAsepsisCode());
        }

        for (Map.Entry<String, String> entry : ids.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            num += dao.delete(key);
//            asepsisAntiRecBo.deleteByCode(value);
        }
        return num;
    }

}

