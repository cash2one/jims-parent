
package com.jims.doctor.diagnosis.bo;


import com.jims.common.vo.LoginInfo;
import com.jims.diagnosis.entity.EmrDiagnosis;
import com.jims.common.service.impl.CrudImplService;
import com.jims.doctor.diagnosis.dao.EmrDiagnosisDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 诊断Bo
 * @author zhangyao
 * @version 2015-12-27
 */
@Service
@Transactional(readOnly = false)
public class EmrDiagnosisBo extends CrudImplService<EmrDiagnosisDao, EmrDiagnosis>{


	/**
	 * 保存门诊诊断
	 * @param emrDiagnosis
	 * @return
	 */
	public String saveDiagnosis(List<EmrDiagnosis> emrDiagnosis,LoginInfo loginInfo){
		String num = "";
		if(emrDiagnosis.size()>0){

			for(int i=0;i<emrDiagnosis.size();i++){
				EmrDiagnosis diagnosis=emrDiagnosis.get(i);
                diagnosis.setParentId("0");
				diagnosis.setInOrOutFlag("0");//门诊
				diagnosis.setDiagnosisDoc(loginInfo.getPersionId());
				diagnosis.setOrgId(loginInfo.getOrgId());
				if(diagnosis.getId()!=null||diagnosis.getId()!=""){
                     delete(diagnosis.getId());
					 diagnosis.setId("");
				}
				diagnosis.setItemNo(i+1);
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
		return findAllListByParent(parentId);

	}


	public List<EmrDiagnosis> findAllListByType(String parentId,String type){
		return findAllListByType(parentId, type);
	}

	public List<EmrDiagnosis> findListChildren(String id){
		return findListChildren(id);
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
	public EmrDiagnosis getDescription(String clinicId,String visitId) {
		return getDescription(clinicId, visitId);
	}


	/**
	 * 查询最大的序号
	 * @param emrDiagnosis
	 * @return
	 */
	public int getMaxItemNo(EmrDiagnosis emrDiagnosis){
		Integer i =  dao.getMaxItemNo(emrDiagnosis);
		if(i!=null&&i!=0){
           i=i+1;
		}else{
			i=1;
		}
		return i;
	}


}