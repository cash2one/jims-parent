package com.jims.clinic.api;

import com.jims.clinic.entity.Operatioin;
import com.jims.common.persistence.Page;

/**
 * Created by qinlongxin on 2016/4/26.
 */
public interface OperatioinSerivceApi {
    /**
     * �������������id��ѯ�ñ�
     * @author qinlongxin
     * @version 2016/4/23
     */
    public Operatioin get(String id);
    /**
     * ��ѯ��������ҳ��Ϣ
     * @author qinlongxin
     * @version 2016/4/20
     */
    public Page<Operatioin> findPage(Page<Operatioin> page, Operatioin operatioin);
    /**
     * �����༭
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String saveOperatioin(Operatioin operatioin);
    /**
     * ɾ���������������
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String delete(String ids);
}
