
package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.dao.SysServicePriceDao;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServicePrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 系统服务BAO层
 *
 * @author txb
 * @version 2016-05-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SysServiceBo extends CrudImplService<SysServiceDao, SysService>{

    @Autowired
    private SysServicePriceDao priceDao;
    /**
     * 保存修改
     * @param sysService
     * @return
     * @author txb
     * @version 2016-05-31
     */
    public String save(SysService sysService,String savePath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File sourceFile = new File(sysService.getServiceImage());
        int sourceIndex = sourceFile.getName().lastIndexOf(".");

        int index = savePath.indexOf("target");
        String path = "src/main/webapp/static/images/sysService/";
        String fileName = generateShortUuid() + sourceFile.getName().substring(sourceIndex);

        savePath = savePath.substring(0,index) + path + fileName;
        try {
            // System.out.println("=="+targetPath+File.separator+sourceFile.getName());
            fis = new FileInputStream(sourceFile);
            bis = new BufferedInputStream(fis);
            System.out.print(savePath);
            fos = new FileOutputStream(new File(savePath));
            bos = new BufferedOutputStream(fos);

            int d;
            while ((d = bis.read()) != -1) {
                bos.write(d);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return super.save(sysService);
    }
    /**
     * 查询服务明细全部列表
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public List<SysServicePrice> findDetailList(String serviceId){
        return priceDao.findListByServiceId(serviceId);
    };


    /**
     * 修改保存删除服务明细
     * @param priceBeanVo
     * @return
     * @author txb
     * @version 2016-06-01
     */
    @Transactional(readOnly = false)
    public String saveDetail(DrugCatalogChangeVo priceBeanVo){
        if (priceBeanVo != null){
            List<SysServicePrice> inserts = priceBeanVo.getInserted();
            List<SysServicePrice> updates = priceBeanVo.getUpdated();
            List<SysServicePrice> deletes = priceBeanVo.getDeleted();

            if (inserts != null && inserts.size() > 0) {
                for (SysServicePrice sysServicePrice : inserts) {
                    sysServicePrice.preInsert();
                    priceDao.insert(sysServicePrice);

                }
            }
            if (updates != null && updates.size() > 0) {
                for (SysServicePrice sysServicePrice : updates) {
                    sysServicePrice.preUpdate();
                    priceDao.update(sysServicePrice);
                }
            }
            if (deletes != null && deletes.size() > 0) {
                for (SysServicePrice sysServicePrice : deletes) {
                    priceDao.delete(sysServicePrice);
                }
            }
        }
        return "1";
    }
    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<SysService> serviceListByTC( String serviceType,String serviceClass) {
        return dao.serviceListByTC(serviceType,serviceClass);
    }




    //生成8位随机uuid
    private  String generateShortUuid() {
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }
}