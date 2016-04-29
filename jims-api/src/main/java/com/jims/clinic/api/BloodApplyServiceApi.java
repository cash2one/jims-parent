package com.jims.clinic.api;

import com.jims.clinic.entity.BloodApply;
import com.jims.common.persistence.Page;

/**
 * Created by qinlongxin on 2016/4/28.
 */
public interface BloodApplyServiceApi {
    /**
     * ����id��ѯ��Ѫ����Ϣ
     * @author zhangyao
     * @version 2016/4/23
     */
    public BloodApply  get(String id);
    /**
     * ��ѯ��Ѫ�����ҳ��Ϣ
     * @author qinlongxin
     * @version 2016/4/20
     */
    public Page<BloodApply> findPage(Page<BloodApply> page, BloodApply bloodApply);
    /**
     * �����༭
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String save(BloodApply bloodApply);
    /**
     * ɾ��
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String delete(String ids);
}
