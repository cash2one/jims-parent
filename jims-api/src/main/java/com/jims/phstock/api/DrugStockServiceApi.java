package com.jims.phstock.api;

import com.jims.common.persistence.Page;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.vo.DrugStockAllVo;
import com.jims.phstock.vo.DrugStockVo;
import com.jims.phstock.vo.DrugWorkCount;

import java.util.List;

/**
 * Created by Administrator on 2016/4/23.
 * 药品库存Api接口
 * @author zhaoning
 * @version 2016-04-23
 */
public interface DrugStockServiceApi{
    /**
     * 保存药品库存
     * @param drugStock
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public String save(DrugStock drugStock);

    /**
     * 分页查询数据
     * @param page
     * @param drugStock
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public Page<DrugStock> findPage(Page<DrugStock> page,DrugStock drugStock);

    /**
     * 列表数据查询
     * @param drugStock
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public List<DrugStock> findList(DrugStock drugStock);

    /**
     * 获取单个对象
     * @param id
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public DrugStock get(String id);

    /**
     * 删除数据（单条数据删除，批量删除）
     * @param ids
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public String delete(String ids);


    /**
     * 查询某库存一段时间内的工作量
     * @param storage 库存代码
     * @param startTime 开始时间
     * @param endDate 结束时间
     * @param orgId 所属组织结构
     * @return
     * @Author ztq
     *
     */
    public List<DrugWorkCount> getWorkCountBy(String storage,String startTime,String endDate,String orgId) ;

    /**
     * 根据进价范围，或者库存单位，查询某一组织机构的库存量
     * @param storageCode
     * @param priceMin
     * @param priceMax
     * @param orgId
     * @return
     * @author ztq
     */
    public List<DrugStockAllVo> listDrugStockAllVo(String storageCode,double priceMin,double priceMax,String orgId);

    /**
     * 检索有库存的药品库存
     * @param drugStock
     * @return
     */
    public List<DrugStock> findListHasStock(DrugStock drugStock);
    /**
     * 保存  增删改
     * @param drugStockVo
     * @return
     *  @author  yangruidong
     */
    public List<DrugStock> saveAll(DrugStockVo<DrugStock> drugStockVo);
}
