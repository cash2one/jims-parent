/*================================================================================*/
/* Table: his.sys_dict    系统公用字典表                                          */
/* Description: 从comm用户下查询数据插入到his用户下的sys_dict表中                 */
/* CREATE_DATE: 2016-07-19                                                        */
/* CREATE_BY :  娄会丽                                                            */
/*================================================================================*/

insert into his.sys_dict(ID,LABEL,VALUE,TYPE,DESCRIPTION,SORT,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE,REMARKS,DEL_FLAG,INPUT_CODE)
select '100'||rownum,BED_TYPE_CODE,BED_TYPE_NAME,UPPER('bed_type_dict'),'床位类型字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.bed_type_dict
union all
select '110'||rownum,diagnosis_TYPE_CODE,diagnosis_TYPE_NAME,UPPER('diagnosis_type_dict'),'诊断类别字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.diagnosis_type_dict
union all
select '120'||rownum,patient_class_CODE,patient_class_NAME,UPPER('patient_class_dict'),'入院方式字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.patient_class_dict
union all
select '130'||rownum,country_CODE,country_NAME,UPPER('country_dict'),'国家及地区字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.country_dict
union all
select '140'||rownum,patient_source_CODE,patient_source_NAME,UPPER('patient_source_dict'),'病人来源字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.patient_source_dict
union all
select '150'||rownum,mr_value_CODE,mr_value_NAME,UPPER('mr_value_dict'),'病案价值字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.mr_value_dict
union all
select '160'||rownum,admission_cause_CODE,admission_cause_NAME,UPPER('admission_cause_dict'),'住院目的字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.admission_cause_dict
union all
select '170'||rownum,title_CODE,title_NAME,UPPER('title_dict'),'技术职务字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.title_dict
union all
select '180'||rownum,order_class_CODE,order_class_NAME,UPPER('order_class_dict'),'医嘱类别字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.order_class_dict
union all
select '190'||rownum,title_CODE,title_NAME,UPPER('doctor_title_dict'),'医生职务字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.doctor_title_dict
union all
select '200'||rownum,armed_services_CODE,armed_services_NAME,UPPER('armed_services_dict'),'军种字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.armed_services_dict
union all
select '210'||rownum,diag_corr_CODE,diag_corr_NAME,UPPER('diag_corr_dict'),'诊断符合情况字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.diag_corr_dict
union all
select '220'||rownum,treating_result_CODE,treating_result_NAME,UPPER('treating_result_dict'),'治疗结果字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.treating_result_dict
union all
select '230'||rownum,pat_condition_CODE,pat_condition_NAME,UPPER('pat_adm_condition_dict'),'入院病情字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.pat_adm_condition_dict
union all
select '240'||rownum,wound_grade_CODE,wound_grade_NAME,UPPER('wound_grade_dict'),'切口等级字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.wound_grade_dict
union all
select '250'||rownum,transact_type_CODE,transact_type_NAME,UPPER('prepay_trans_type_dict'),'预交金操作类型字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.prepay_trans_type_dict
union all
select '260'||rownum,comp_class,comp_NAME,UPPER('mr_comp_class_dict'),'病案内容分类字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.mr_comp_class_dict
union all
select '270'||rownum,transact_type_CODE,transact_type_NAME,UPPER('settle_trans_type_dict'),'结算操作类型字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.settle_trans_type_dict
union all
select '280'||rownum,mr_status_CODE,mr_status_NAME,UPPER('mr_status_dict'),'病案状态字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.mr_status_dict
union all
select '290'||rownum,heal_CODE,heal_NAME,UPPER('heal_dict'),'切口愈合情况字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.heal_dict
union all
select '300'||rownum,area_CODE,area_NAME,UPPER('area_dict'),'行政区字典',serial_no,null,null,null,null,null,'0',INPUT_CODE from comm.area_dict
union all
select '310'||rownum,job_class_CODE,job_class_NAME,UPPER('job_class_dict'),'工作类别字典',nvl(serial_no,0),null,null,null,null,null,'0',INPUT_CODE from comm.job_class_dict





