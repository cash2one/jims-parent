/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ElectronGroupConsultationApi;
import com.jims.clinic.bo.ElectronGroupConsultationBo;
import com.jims.clinic.dao.ElectronGroupConsultationDao;
import com.jims.clinic.dao.ElectronGroupConsultationInDao;
import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.clinic.entity.ElectronGroupConsultationIn;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 会诊记录Service
 * @author zhaoning
 * @version 2016-04-23
 */
@Service(version = "1.0.0")

public class ElectronGroupConsultationService extends CrudImplService<ElectronGroupConsultationDao, ElectronGroupConsultation> implements ElectronGroupConsultationApi {

	@Autowired
	private ElectronGroupConsultationBo electronGroupConsultationBo;

	/**
	 * 保存会诊信息
	 * @param electronGroupConsultation
	 * @author zhaoning
	 * @version 2016-04-23
	 */
	@Override
	public String saveGroupConsulation(ElectronGroupConsultation electronGroupConsultation) {
		String num = electronGroupConsultationBo.saveGroupConsulation(electronGroupConsultation);
		return num;
	}

	/**异步参与医生信息列表
	 * 查询所属同一主表的所有的子表（ElectronGroupConsultationIn）信息List
	 * @param “ElectronGroupConsultationIn.id”
	 * @author xueyx
	 * @version 2016/4/22
	 */
	public void fabu(ElectronGroupConsultation electronGroupConsultation){
		electronGroupConsultationBo.fabu(electronGroupConsultation);
	}

	/**
	 * 保存发布会诊人的意见
	 * @param "ElectronGroupConsultation.id 会诊主表id"
	 * @param "ElectronGroupConsultation.huizhenyijian "
	 * @author xueyx
	 * @version 2016-04-26
	 */
	public String saveMainOnly(ElectronGroupConsultation electronGroupConsultation){
		String num = electronGroupConsultationBo.saveMainOnly(electronGroupConsultation);
		return num;
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