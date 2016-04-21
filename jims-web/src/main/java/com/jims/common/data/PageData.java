package com.jims.common.data;

import com.jims.common.persistence.DataEntity;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.User;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class PageData<T> implements Serializable{
    private List rows;

    private Long total;

    public PageData() {

    }

    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
