/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ElectronGroupConsultationApi;
import com.jims.clinic.dao.ElectronGroupConsultationDao;
import com.jims.clinic.dao.ElectronGroupConsultationInDao;
import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.clinic.entity.ElectronGroupConsultationIn;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会诊记录Service
 * @author zhaoning
 * @version 2016-04-23
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ElectronGroupConsultationService extends CrudImplService<ElectronGroupConsultationDao, ElectronGroupConsultation> implements ElectronGroupConsultationApi {
    @Autowired
	private ElectronGroupConsultationInDao electronGroupConsultationInDao;

	/**
	 * 保存会诊信息
	 * @param electronGroupConsultation
	 * @author zhaoning
	 * @version 2016-04-23
	 */
	@Override
	@Transactional(readOnly = false)
	public void saveGroupConsulation(ElectronGroupConsultation electronGroupConsultation) {
		this.save(electronGroupConsultation);//保存会诊主记录
		if(electronGroupConsultation!=null && electronGroupConsultation.getId()!=null){
			List<ElectronGroupConsultationIn> list = electronGroupConsultation.getElectronGroupConsultationInList();
			if(list.size()>0){
				for (int i=0;i<list.size();i++){
					ElectronGroupConsultationIn electronGroupConsultationIn=list.get(i);
					if (electronGroupConsultationIn.getIsNewRecord()){//保存参与会诊信息
						electronGroupConsultationIn.preInsert();
						electronGroupConsultationIn.setHuizhenId(electronGroupConsultation.getId());
						i=electronGroupConsultationInDao.insert(electronGroupConsultationIn);
					}else{
						electronGroupConsultationIn.preUpdate();
						electronGroupConsultationIn.setHuizhenId(electronGroupConsultation.getId());
						i=electronGroupConsultationInDao.update(electronGroupConsultationIn);
					}
				}
			}
		}
	}
}