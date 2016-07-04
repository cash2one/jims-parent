package com.jims.asepsis.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class AsepsisVo<T> implements Serializable {
        private List<T> updated ;//更新的项目

        private String orgId;


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



