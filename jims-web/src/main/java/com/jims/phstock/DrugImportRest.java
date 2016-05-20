package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugImportServiceApi;
import com.jims.phstock.api.DrugStockServiceApi;
import com.jims.phstock.entity.DrugImportDetail;
import com.jims.phstock.entity.DrugImportMaster;
import com.jims.phstock.entity.DrugStock;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * 药品入库Rest
 * @author lgx
 * @version 2016/5/18
 */
@Component
@Produces("application/json")
@Path("drug-in")
public class DrugImportRest {

    @Reference(version = "1.0.0")
    private DrugImportServiceApi drugImportServiceApi;

    @Reference(version ="1.0.0")
    private DrugStockServiceApi drugStockServiceApi ;

    @POST
    @Path("save")
    public StringData save(DrugImportMaster master){
        if(master != null){
            StringData resultData = new StringData();
            List<DrugImportDetail> details = master.getDetailList();
            master.setDetailList(null);
            resultData.setCode("0");
            List<String> result = new ArrayList<String>();
            result.add(drugImportServiceApi.save(master));
            if(1 == master.getAccountIndicator() && details != null && details.size() > 0){
                DrugStock stock = new DrugStock();
                int successSave = 0;
                for(DrugImportDetail detail : details){
                    stock.setStorage(master.getStorage());
                    stock.setDrugCode(detail.getDrugCode());
                    stock.setDrugSpec(detail.getDrugSpec());
                    stock.setUnits(detail.getUnits());
                    stock.setFirmId(detail.getFirmId());
                    stock.setBatchNo(detail.getBatchNo());
                    stock.setOrgId(detail.getOrgId());
                    List<DrugStock> stocks = drugStockServiceApi.findList(stock);
                    if(stocks != null && stocks.size() > 0){
                        stock = stocks.get(0);
                        stock.setQuantity(stock.getQuantity() + detail.getQuantity());
                    } else {
                        stock.setExpireDate(detail.getExpireDate());
                        stock.setPurchasePrice(detail.getPurchasePrice());
                        stock.setDiscount(detail.getDiscount());
                        stock.setPackageSpec(detail.getPackageSpec());
                        stock.setQuantity(detail.getQuantity());
                        stock.setPackageUnits(detail.getPackageUnits());
                        stock.setSubPackage1(detail.getSubPackage1());
                        stock.setSubPackageUnits1(detail.getSubPackageUnits1());
                        stock.setSubPackageSpec1(detail.getSubPackageSpec1());
                        stock.setSubPackage2(detail.getSubPackage2());
                        stock.setSubPackageUnits2(detail.getSubPackageUnits2());
                        stock.setSubPackageSpec2(detail.getSubPackageSpec2());
                        stock.setSubStorage(master.getSubSupplier());
                        stock.setDocumentNo(detail.getDocumentNo());
                        stock.setSupplyIndicator(1);
                    }
                    String r = drugStockServiceApi.save(stock);
                    if("1".equals(r)){
                        detail.setQuantity(stock.getQuantity());
                        String saveDetail = drugImportServiceApi.saveDetail(detail);
                        if("1".equals(saveDetail)){
                            successSave ++ ;
                        }
                    }
                }
            } else {
                result.add(drugImportServiceApi.saveDetailBatch(details));
            }
            resultData.setDatas(result);
            return resultData;
        }
        return null;

    }
}
