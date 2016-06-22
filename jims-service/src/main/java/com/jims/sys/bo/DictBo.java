package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.sys.dao.DictDao;
import com.jims.sys.entity.Dict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 字典表bo事务处理层
 * Created by fyg on 2016/6/16.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DictBo extends CrudImplService<DictDao, Dict> {

    /**
     * 查询字典表的类型和描述这两个字段
     * @return 类型描述的列表list集合
     * @author fengyuguang
     */
    public List<Dict> leftList() {
        return dao.leftList();
    }

    /**
     * 根据字典表的类型查询属于该类型的数据列表
     * @param type 字典表类型
     * @return 字典表某类的列表集合
     * @author fengyuguang
     */
    public List<Dict> rightList(String type) {
        return dao.rightList(type);
    }

    /**
     * 根据类型或描述模糊查询
     * @param type
     * @param description
     * @return 查询到的字典表List集合
     * @author fengyuguang
     */
    public List<Dict> select(String type, String description) {
        return dao.select(type, description);
    }

    /**
     * 保存增删改多条数据
     * @param beanChangeVo 多条数据的Vo类
     * @return 操作数据条数
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<Dict> beanChangeVo) {
        List<Dict> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (Dict dict : insertedList) {
            String label = dict.getLabel();
            dict.setInputCode(PinYin2Abbreviation.cn2py(label));
            inNum = Integer.valueOf(save(dict));
            inNum++;
        }
        String insertedNum = inNum + "";
        List<Dict> updatedList = beanChangeVo.getUpdated();

        int updNum = 0;
        for (Dict dict : updatedList) {
            Date date = new Date();
            dict.setUpdateDate(date);
            updNum = dao.update(dict);
            updNum++;
        }
        String updatedNum = updNum + "";
        if (insertedNum == "0" && updatedNum == "0") {
            return "0";
        } else {
            return "1";
        }
    }

    /**
     * 查询字段类型列表
     *
     * @return
     */
    public List<String> findTypeList() {
        return dao.findTypeList(new Dict());
    }

    public List<String> findListType(String dict) {
        return dao.findListType(dict);
    }

    public String getLabel(String type, String value) {
        return dao.getLabel(type, value);
    }

    /**
     * 根据类型检索字典
     *
     * @param type
     * @return
     */
    public List<Dict> findList(String type) {
        Dict d = new Dict();
        d.setType(type);
        return dao.findList(d);
    }
}
