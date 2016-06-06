package com.jims.nurse.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.nurse.dao.BedRecDao;
import com.jims.nurse.entity.BedRec;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BedRecBo
 *
 * @author PangQian
 * @date2016/6/2 0002
 */
@Service
@Component
@Transactional(readOnly = false)
public class BedRecBo extends CrudImplService<BedRecDao, BedRec> {

    /**
     * 保存床位信息
     * @param bedRecList
     * @author pq
     * @return
     */
    public String saveBed(List<BedRec> bedRecList){
        String str="";
      if(bedRecList !=null){
            for(int i=0;i<bedRecList.size();i++){
                BedRec bedRec = new BedRec();
                bedRec = bedRecList.get(i);
                str = str + save(bedRec);
            }
      }else{
       return str;
      }
        return str;
    }

    /**
     * 判断 病区 下的床位号的唯一性
     * @param bedNo
     * @param wardCode
     * @author pq
     * @return
     */
    public  boolean judgeBedNo(Integer bedNo,String wardCode){
        boolean flag = false;
      List<BedRec> bedRecs=dao.judgeBedNo(bedNo,wardCode);
        if(bedRecs!=null && bedRecs.size()>0){
            flag =true;
        }
        return flag;
    }


    /**
     * 查询病区下所有的床位信息
     * @param wardCode
     * @author pq
     * @return
     */
    public List<BaseDto> getAllBed(String wardCode){
      return dao.getAllBed(wardCode);
    }


}
