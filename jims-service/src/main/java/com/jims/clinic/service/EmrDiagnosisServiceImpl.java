
package com.jims.clinic.service;





import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.EmrDiagnosisServiceApi;
import com.jims.clinic.dao.EmrDiagnosisDao;
import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.common.persistence.Page;
import com.jims.common.service.CrudService;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;




import java.util.ArrayList;
import java.util.List;

/**
 * 诊断Service
 * @author zhangyao
 * @version 2015-12-27
 */
@Service(version = "1.0.0")

public class EmrDiagnosisServiceImpl extends CrudImplService<EmrDiagnosisDao, EmrDiagnosis> implements EmrDiagnosisServiceApi{
	@Autowired
	private EmrDiagnosisDao emrDiagnosisDao;

	/**
	 * 保存门诊诊断
	 * @param emrDiagnosis
	 * @return
	 */
	//@Override
	public String saveDiagnosis(List<EmrDiagnosis> emrDiagnosis){
		String num = "";
		if(emrDiagnosis.size()>0){
			for(int i=0;i<emrDiagnosis.size();i++){
				EmrDiagnosis diagnosis=emrDiagnosis.get(i);
				diagnosis.setInOrOutFlag("0");//门诊
				num =	save(diagnosis);
			}
			return num;
		}
		return null;
	}

	/**
	 * 保存住院诊断
	 * @param emrDiagnosis
	 * @return
	 */
	public String saveIn(List<EmrDiagnosis> emrDiagnosis){

		String i1="0";
		if(emrDiagnosis.size()>0){
			for(int i=0;i<emrDiagnosis.size();i++){
				EmrDiagnosis diagnosis=emrDiagnosis.get(i);
				diagnosis.setInOrOutFlag("1");//住院
				i1 = save(diagnosis);
				if(diagnosis.getChildren()!=null){
					for(int j=0;j<diagnosis.getChildren().size();j++){

						i1=	save(diagnosis.getChildren().get(j));}

				}
			}
		}
		return i1;
	}



	/**
	 * 通过父级Id查找诊断结果集
	 * @param parentId
	 * @return
	 */

	public  List<EmrDiagnosis> findAllListByParent(String parentId){
		return emrDiagnosisDao.findAllListByParent(parentId);

	}


	public List<EmrDiagnosis> findAllListByType(String parentId,String type){
		return emrDiagnosisDao.findAllListByType(parentId, type);
	}

	public List<EmrDiagnosis> findListChildren(String id){
		return emrDiagnosisDao.findListChildren(id);
	}
	/**
	 * 查询病人诊断数据数据
	 * @param-inOrOutFlag
	 * @param-clinicId
	 * @paramc-visitId
	 * @return
	 */
	public List<EmrDiagnosis> findAllDiagnosisForOne(EmrDiagnosis emrDiagnosis){
		return dao.findAllDiagnosisForOne(emrDiagnosis);
	}

	/**
	 * 根据clinicId 或 visitIds查询临床诊断
	 * @param clinicId
	 * @param visitId
	 * @return
	 */
	@Override
	public EmrDiagnosis getDescription(String clinicId,String visitId) {
		return emrDiagnosisDao.getDescription(clinicId,visitId);
	}


}