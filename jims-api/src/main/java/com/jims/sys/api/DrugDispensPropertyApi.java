package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.DrugDispensProperty;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface DrugDispensPropertyApi {
    public Page<DrugDispensProperty> findPage(Page<DrugDispensProperty> page, DrugDispensProperty drugDispensProperty);
    public void save(DrugDispensProperty drugDispensProperty);
    public void delete(DrugDispensProperty drugDispensProperty);
}
