package com.jims.phstock.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class DrugProvideApplicationVo<T> implements Serializable {

    /**
     * Created by yangruidong on 2016/7/04 0027.
     */

        private List<T> inserted ;//新增的项目
        private List<T> deleted ;//删除的项目
        private List<T> updated ;//更新的项目


        public List<T> getInserted() {
            return inserted;
        }

        public void setInserted(List<T> inserted) {
            this.inserted = inserted;
        }

        public List<T> getDeleted() {
            return deleted;
        }

        public void setDeleted(List<T> deleted) {
            this.deleted = deleted;
        }

        public List<T> getUpdated() {
            return updated;
        }

        public void setUpdated(List<T> updated) {
            this.updated = updated;
        }

    }



