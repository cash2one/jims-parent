package com.jims.common.data;

import com.jims.common.persistence.DataEntity;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.User;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@XmlRootElement
@XmlSeeAlso(Dict.class)
public class PageData<T>  implements Serializable{

    private List<T> rows;

    private Long total;

    public List<T> getRows() {
        return rows;
    }
    public PageData() {

    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
