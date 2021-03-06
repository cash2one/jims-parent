package com.jims.asepsis.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louhuiuli on 2016/7/4 0027.
 */
public class AsepsisAntiRecVo<T> implements Serializable {

    /**
     * Created by louhuiuli on 2016/7/4 0027.
     */

        private List<T> inserted ;//新增的项目
        private List<T> deleted ;//删除的项目
        private List<T> updated ;//更新的项目

        private String orgId;

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

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }
    }



