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
import com.jims.common.utils.StringUtils;
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

	@Autowired
	private
	ElectronGroupConsultationDao electronGroupConsultationDao;

	/**
	 * 保存会诊信息
	 * @param electronGroupConsultation
	 * @author zhaoning
	 * @version 2016-04-23
	 */
	//@Override
	@Transactional(readOnly = false)
	public void saveGroupConsulation(ElectronGroupConsultation electronGroupConsultation) {
		//electronGroupConsultationDao.saveGroupConsulation(electronGroupConsultation);//保存会诊主记录
		/*if(StringUtils.isBlank(electronGroupConsultation.getId())){
			electronGroupConsultation.setFabuflag("0");
			electronGroupConsultation.setIdeaFlag("0");
			electronGroupConsultation.preInsert();
			electronGroupConsultationDao.insert(electronGroupConsultation);
		}else{
			electronGroupConsultationDao.update(electronGroupConsultation);
		}*/
		electronGroupConsultation.setFabuflag("0");
		electronGroupConsultation.setIdeaFlag("0");
		save(electronGroupConsultation);
		if(electronGroupConsultation!=null && electronGroupConsultation.getId()!=null){
			electronGroupConsultationInDao.delByMain(electronGroupConsultation);
			List<ElectronGroupConsultationIn> list = electronGroupConsultation.getElectronGroupConsultationInList();
			if(list.size()>0){
				for (int i=0;i<list.size();i++){
					ElectronGroupConsultationIn electronGroupConsultationIn=list.get(i);
					electronGroupConsultationIn.setDelFlag("0");
					electronGroupConsultationIn.preInsert();
					electronGroupConsultationIn.setHuizhenId(electronGroupConsultation.getId());
					electronGroupConsultationInDao.insert(electronGroupConsultationIn);
				}
			}
		}
	}

	/**异步参与医生信息列表
	 * 查询所属同一主表的所有的子表（ElectronGroupConsultationIn）信息List
	 * @param “ElectronGroupConsultationIn.id”
	 * @author xueyx
	 * @version 2016/4/22
	 */
	public void fabu(ElectronGroupConsultation electronGroupConsultation){
		dao.fabu(electronGroupConsultation);
	}

	/**
	 * 保存发布会诊人的意见
	 * @param "ElectronGroupConsultation.id 会诊主表id"
	 * @param "ElectronGroupConsultation.huizhenyijian "
	 * @author xueyx
	 * @version 2016-04-26
	 */
	public void saveMainOnly(ElectronGroupConsultation electronGroupConsultation){
		electronGroupConsultationDao.saveMainOnly(electronGroupConsultation);
	}
	/**
	 * 保存或编辑会诊主表方法
	 * @paramElectronGroupConsultation
	 */
	/*public void saveGroupConsulation(ElectronGroupConsultation electronGroupConsultation){
		if(StringUtils.isBlank(electronGroupConsultation.getId())){

		}else{

		}
	}*/
}