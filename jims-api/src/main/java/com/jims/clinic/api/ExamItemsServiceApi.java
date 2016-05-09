package com.jims.clinic.api;

import com.jims.clinic.entity.ExamItems;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 * 检查项目Api接口
 * @author zhaoning
 * @version 2016-04-25
 */
public interface ExamItemsServiceApi {
    //检查项目保存
    public void saveExamItem(ExamItems examItems);

    /**
     * 根据申请序号查询项目
     */
    public List<ExamItems> loadExamItems(String examNo);

    /**
     * 删除检查项目
     * @param examNo
     */
    public String deleteItems(String examNo);

}
