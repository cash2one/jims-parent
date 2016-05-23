-- drop table PAT_VISIT cascade constraints;

/*==============================================================*/
/* Table: PAT_VISIT   病人住院主记录                             */
/*==============================================================*/

-- Create table
create table PAT_VISIT
(
  ID                   VARCHAR2(64) not null,
  ORG_ID               VARCHAR2(64),
  VISIT_ID                     NUMBER(2) ,
  PATIENT_ID VARCHAR2(64),
  DEPT_ADMISSION_TO            VARCHAR2(80),
  ADMISSION_DATE_TIME          TIMESTAMP,
  DEPT_DISCHARGE_FROM          VARCHAR2(80),
  DISCHARGE_DATE_TIME          TIMESTAMP,
  OCCUPATION                   VARCHAR2(80),
  MARITAL_STATUS               VARCHAR2(80),
  IDENTITY                     VARCHAR2(10),
  ARMED_SERVICES               VARCHAR2(80),
  DUTY                         VARCHAR2(80),
  TOP_UNIT                     VARCHAR2(80),
  SERVICE_SYSTEM_INDICATOR     NUMBER(1),
  UNIT_IN_CONTRACT             VARCHAR2(11),
  CHARGE_TYPE                  VARCHAR2(80),
  WORKING_STATUS               NUMBER(1),
  INSURANCE_TYPE               VARCHAR2(16),
  INSURANCE_NO                 VARCHAR2(18),
  SERVICE_AGENCY               VARCHAR2(40),
  MAILING_ADDRESS              VARCHAR2(100),
  ZIP_CODE                     VARCHAR2(80),
  NEXT_OF_KIN                  VARCHAR2(80),
  RELATIONSHIP                 VARCHAR2(80),
  NEXT_OF_KIN_ADDR             VARCHAR2(40),
  NEXT_OF_KIN_ZIPCODE          VARCHAR2(80),
  NEXT_OF_KIN_PHONE            VARCHAR2(16),
  PATIENT_CLASS                VARCHAR2(80),
  ADMISSION_CAUSE              VARCHAR2(80),
  CONSULTING_DATE              TIMESTAMP,
  PAT_ADM_CONDITION            VARCHAR2(80),
  CONSULTING_DOCTOR            VARCHAR2(80),
  ADMITTED_BY                  VARCHAR2(80),
  EMER_TREAT_TIMES             NUMBER(2),
  ESC_EMER_TIMES               NUMBER(2),
  SERIOUS_COND_DAYS            NUMBER(4),
  CRITICAL_COND_DAYS           NUMBER(4),
  ICU_DAYS                     NUMBER(4),
  CCU_DAYS                     NUMBER(4),
  SPEC_LEVEL_NURS_DAYS         NUMBER(4),
  FIRST_LEVEL_NURS_DAYS        NUMBER(4),
  SECOND_LEVEL_NURS_DAYS       NUMBER(4),
  AUTOPSY_INDICATOR            NUMBER(1),
  BLOOD_TYPE                   VARCHAR2(80),
  BLOOD_TYPE_RH                VARCHAR2(80),
  INFUSION_REACT_TIMES         NUMBER(2),
  BLOOD_TRAN_TIMES             NUMBER(2),
  BLOOD_TRAN_VOL               NUMBER(5),
  BLOOD_TRAN_REACT_TIMES       NUMBER(2),
  DECUBITAL_ULCER_TIMES        NUMBER(2),
  ALERGY_DRUGS                 VARCHAR2(80),
  ADVERSE_REACTION_DRUGS       VARCHAR2(80),
  MR_VALUE                     VARCHAR2(80),
  MR_QUALITY                   VARCHAR2(80),
  FOLLOW_INDICATOR             NUMBER(1),
  FOLLOW_INTERVAL              NUMBER(2),
  FOLLOW_INTERVAL_UNITS        VARCHAR2(2),
  DIRECTOR                     VARCHAR2(80),
  ATTENDING_DOCTOR             VARCHAR2(80),
  DOCTOR_IN_CHARGE             VARCHAR2(80),
  DISCHARGE_DISPOSITION        VARCHAR2(80),
  TOTAL_COSTS                  NUMBER(12,4),
  TOTAL_PAYMENTS               NUMBER(12,4),
  CATALOG_DATE                 TIMESTAMP,
  CATALOGER                    VARCHAR2(80),
  HBSAG_INDICATOR              NUMBER(1),
  HCV_AB_INDICATOR             NUMBER(1),
  HIV_AB_INDICATOR             NUMBER(1),
  CHIEF_DOCTOR                 VARCHAR2(80),
  ADVANCED_STUDIES_DOCTOR      VARCHAR2(80),
  PRACTICE_DOCTOR_OF_GRADUATE  VARCHAR2(80),
  PRACTICE_DOCTOR              VARCHAR2(80),
  DOCTOR_OF_CONTROL_QUALITY    VARCHAR2(80),
  NURSE_OF_CONTROL_QUALITY     VARCHAR2(80),
  DATE_OF_CONTROL_QUALITY      TIMESTAMP,
  FIRST_CASE_INDICATOR         NUMBER(1),
  THIRD_LEVEL_NURS_DAYS        NUMBER(4),
  X_EXAM_NO                    VARCHAR2(80),
  TREATED_IN_OTHERS_INDICATOR  VARCHAR2(80),
  TREAT_METHOD                 VARCHAR2(80),
  HOSP_MADE_MEDICINE_INDICATOR VARCHAR2(80),
  PATHOLOGY_NO                 VARCHAR2(80),
  UPPER_DOCTOR_GUIDE_EFFECT    VARCHAR2(80),
  EMER_TREAT_METHOD            VARCHAR2(80),
  ICTUS_INDICATOR              VARCHAR2(80),
  DIFFICULTY_INDICATOR         VARCHAR2(80),
  FROM_OTHER_PLACE_INDICATOR   VARCHAR2(80),
  SUSPICION_INDICATOR          VARCHAR2(80),
  CHINESE_MEDICINE_INDICATOR   VARCHAR2(80),
  OPERATION_SCALE              VARCHAR2(80),
  DIAGNOSIS_CORRECTNESS        VARCHAR2(80),
  TREAT_METHOD_CORRECTNESS     VARCHAR2(80),
  PRESCRIPTION_CORRECTNESS     VARCHAR2(80),
  MR_COMPLETE_INDICATOR        VARCHAR2(80),
  MEDICAL_TERM_CORRECTNESS     VARCHAR2(80),
  PPER_DOCTOR_GUIDE_EFFECT     VARCHAR2(80),
  TREAT_METHOD_JUDGEMENT       VARCHAR2(80),
  DUTY_NURSE                   VARCHAR2(80),
  DEATH_REASON                 VARCHAR2(40),
  DEATH_DATE_TIME              TIMESTAMP,
  SCIENCE_RESEARCH_INDICATOR   VARCHAR2(80),
  FIRST_OPERATION_INDICATOR    VARCHAR2(80),
  FIRST_TREATMENT_INDICATOR    VARCHAR2(80),
  FIRST_EXAMINATION_INDICATOR  VARCHAR2(80),
  FIRST_DIAGNOSIS_INDICATOR    VARCHAR2(80),
  INFUSION_REACT_INDICATOR     VARCHAR2(80),
  SERIOUS_INDICATOR            VARCHAR2(80),
  ADT_ROOM_NO                  VARCHAR2(80),
  DDT_ROOM_NO                  VARCHAR2(80),
  INFECT_INDICATOR             NUMBER(1),
  HEALTH_LEVEL                 CHAR(2),
  MR_INFECT_REPORT             CHAR(4),
  NEWBORN                      CHAR(1),
  THIRD_LEVEL_NURSE_DAYS       NUMBER(4),
  INSURANCE_AERA               VARCHAR2(60),
  BODY_WEIGHT                  NUMBER(5,2),
  BODY_HEIGHT                  NUMBER(4,1),
  BUSINESS_ZIP_CODE            VARCHAR2(6),
  INFUSION_TRAN_TIMES          NUMBER,
  CHANGE_INDICATOR             NUMBER(1),
  DOCUM_DATE                   TIMESTAMP,
  DOCUM_DAYS                   NUMBER(3),
  DOCUM_PERSON                 VARCHAR2(80),
  ZYMOSIS_INDICATOR            VARCHAR2(80),
  ZYMOSIS_DATE                 TIMESTAMP,
  BREATH_MACH_TIMES            NUMBER(3),
  COMA_TIMES_B1                NUMBER(3),
  COMA_TIMES_B2                NUMBER(3),
  COMA_TIMES_A1                NUMBER(3),
  COMA_TIMES_A2                NUMBER(3),
  TRANS_HOSPITAL               VARCHAR2(100),
  TRANS_COMMUNITY              VARCHAR2(100),
  PHONE_NUMBER_BUSINESS        VARCHAR2(80),
  MR_BINDER                    VARCHAR2(80),
  CARRY_PERSON_NUMBER          NUMBER,
  CHANGE_OPERATOR              VARCHAR2(80),
  DISCHARGE_BED_NO             NUMBER(8),
  DISCHARGE_WARD_CODE          VARCHAR2(32),
  INP_SERIAL_NO                VARCHAR2(80),
  NHZYH                        VARCHAR2(32),
  FF_XSDM                      VARCHAR2(32),
  TRANS_FLAG                   VARCHAR2(32),
  GZ_FLAG                      VARCHAR2(32),
  VISIT_DATE                   TIMESTAMP,
  VISIT_NO                     NUMBER(5),
  PHONE_NUMBER_HOME            VARCHAR2(80),
  PATIENT_AREA                 VARCHAR2(80),
  CATALOG_STATUS               VARCHAR2(80),
  LOCKED_DATE                  TIMESTAMP,
  DIAGNOSE_DATE                TIMESTAMP,
  MEDICAL_PAY_WAY              VARCHAR2(80),
  STATISTICS_DIAGNOSE_DATE     TIMESTAMP,
  DOCUM_FLAG                   NUMBER(1),
  STATISTICS_FLAG              NUMBER(1),
  WEIGHT_BIRTH                 NUMBER(4),
  COMA_TIMES_B0                NUMBER(3),
  COMA_TIMES_A0                NUMBER(3),
  PATIENT_AREA_ADDRESS         VARCHAR2(800),
  PAT_STREET_ADDRESS           VARCHAR2(80),
  PAT_PHONE                    VARCHAR2(80),
  PAT_ZIP                      VARCHAR2(80),
  PLAN_31_ADMISSION            VARCHAR2(80),
  REASON_31_ADMISSION          VARCHAR2(100),
  TUMOR_T                      VARCHAR2(80),
  TUMOR_N                      VARCHAR2(80),
  TUMOR_M                      VARCHAR2(80),
  TUMOR_STAGE                  VARCHAR2(80),
  ADL_ADM                      NUMBER(6,2),
  ADL_DIS                      NUMBER(6,2),
  BASIS_ON                     VARCHAR2(8),
  DIFF_ID                      VARCHAR2(80),
  LOCKED_USER                  VARCHAR2(80),
  INP_NO                       VARCHAR2(80),
  RETURN_INDICATOR             VARCHAR2(80) default 'N',
  PAT_CHARGE_TYPE              NUMBER(2),
  OPERATOR                     VARCHAR2(80),
  ENTER_DATE                   TIMESTAMP,
  PARITY_NO                    NUMBER(2),
  ONSET_DATE                   TIMESTAMP,
  NH_SERIAL_NO                 VARCHAR2(50),
  IP                           VARCHAR2(50),
  REGIST_ATTR                  VARCHAR2(80),
  VISIT_TYPE                   VARCHAR2(80),
  ANTIBIOTICS_USED             VARCHAR2(80),
  ANTIBIOTICS_COUNT            NUMBER(2),
  ANTIBIOTICS_UNION            VARCHAR2(80),
  GERMICULTURE_DRUG_TEST       VARCHAR2(80),
  INFECTIOUS_DISEASE_REPORT    VARCHAR2(80),
  BLOOD_TRAN_FLAG              VARCHAR2(80),
  DRGS                         VARCHAR2(80),
  ADMISSION_BED_NO             NUMBER(2),
  NEWBORN_INH_BODY_WEIGHT      NUMBER(2),
  NEWBORN_BODY_WEIGHT          NUMBER(2),
  POF_FLAG                     NUMBER(10),
  CLINICAL_PATHWAY             VARCHAR2(80),
  CHINESE_MED_EQUIPMENT        VARCHAR2(80),
  CHINESE_MED_TECHNOLOGY       VARCHAR2(80),
  DIALECTICAL_NURSING          VARCHAR2(80),
  YB_INP                       VARCHAR2(50),
  NHZZ_TYPE                    VARCHAR2(80),
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(225 CHAR),
   DEL_FLAG             CHAR(1),
  constraint "PK_pat_visit" primary key (ID)
);
-- Add comments to the table
comment on table PAT_VISIT
  is '病人住院主记录';
-- Add comments to the columns
comment on column PAT_VISIT.ID
  is '病人ID';
  comment on column PAT_VISIT.ORG_ID
  is '医院ID';
comment on column PAT_VISIT.VISIT_ID
  is '病人本次住院标识';
comment on column PAT_VISIT.DEPT_ADMISSION_TO
  is '入院科室';
comment on column PAT_VISIT.ADMISSION_DATE_TIME
  is '入院日期及时间';
comment on column PAT_VISIT.DEPT_DISCHARGE_FROM
  is '出院科室';
comment on column PAT_VISIT.DISCHARGE_DATE_TIME
  is '出院日期及时间';
comment on column PAT_VISIT.OCCUPATION
  is '职业';
comment on column PAT_VISIT.MARITAL_STATUS
  is '婚姻状况';
comment on column PAT_VISIT.IDENTITY
  is '身份';
comment on column PAT_VISIT.ARMED_SERVICES
  is '军种';
comment on column PAT_VISIT.DUTY
  is '勤务';
comment on column PAT_VISIT.TOP_UNIT
  is '隶属大单位';
comment on column PAT_VISIT.SERVICE_SYSTEM_INDICATOR
  is '费别';
comment on column PAT_VISIT.UNIT_IN_CONTRACT
  is '合同单位';
comment on column PAT_VISIT.CHARGE_TYPE
  is '医保类别';
comment on column PAT_VISIT.WORKING_STATUS
  is '在职标志';
comment on column PAT_VISIT.INSURANCE_TYPE
  is '工作单位';
comment on column PAT_VISIT.INSURANCE_NO
  is '医疗保险号';
comment on column PAT_VISIT.SERVICE_AGENCY
  is '医疗体系病人标志';
comment on column PAT_VISIT.MAILING_ADDRESS
  is '通信地址(户口地址)';
comment on column PAT_VISIT.ZIP_CODE
  is '邮政编码';
comment on column PAT_VISIT.NEXT_OF_KIN
  is '联系人姓名';
comment on column PAT_VISIT.RELATIONSHIP
  is '与联系人关系';
comment on column PAT_VISIT.NEXT_OF_KIN_ADDR
  is '联系人地址';
comment on column PAT_VISIT.NEXT_OF_KIN_ZIPCODE
  is '联系人邮政编码';
comment on column PAT_VISIT.NEXT_OF_KIN_PHONE
  is '联系人电话';
comment on column PAT_VISIT.PATIENT_CLASS
  is '入院方式';
comment on column PAT_VISIT.ADMISSION_CAUSE
  is '住院目的';
comment on column PAT_VISIT.CONSULTING_DATE
  is '住院目的';
comment on column PAT_VISIT.PAT_ADM_CONDITION
  is '入院病情';
comment on column PAT_VISIT.CONSULTING_DOCTOR
  is '门诊医师';
comment on column PAT_VISIT.ADMITTED_BY
  is '办理住院者';
comment on column PAT_VISIT.EMER_TREAT_TIMES
  is '抢救次数';
comment on column PAT_VISIT.ESC_EMER_TIMES
  is '抢救成功次数';
comment on column PAT_VISIT.SERIOUS_COND_DAYS
  is '病重天数';
comment on column PAT_VISIT.CRITICAL_COND_DAYS
  is '病危天数';
comment on column PAT_VISIT.ICU_DAYS
  is 'ICU天数';
comment on column PAT_VISIT.CCU_DAYS
  is 'CCU天数';
comment on column PAT_VISIT.SPEC_LEVEL_NURS_DAYS
  is '特别护理天数';
comment on column PAT_VISIT.FIRST_LEVEL_NURS_DAYS
  is '一级护理天数';
comment on column PAT_VISIT.SECOND_LEVEL_NURS_DAYS
  is '二级护理天数';
comment on column PAT_VISIT.AUTOPSY_INDICATOR
  is '尸检标识';
comment on column PAT_VISIT.BLOOD_TYPE
  is '血型';
comment on column PAT_VISIT.BLOOD_TYPE_RH
  is 'Rh血型';
comment on column PAT_VISIT.INFUSION_REACT_TIMES
  is '输液反应次数';
comment on column PAT_VISIT.BLOOD_TRAN_TIMES
  is '输血次数';
comment on column PAT_VISIT.BLOOD_TRAN_VOL
  is '输血总量';
comment on column PAT_VISIT.BLOOD_TRAN_REACT_TIMES
  is '输血反应次数';
comment on column PAT_VISIT.DECUBITAL_ULCER_TIMES
  is '发生褥疮次数';
comment on column PAT_VISIT.ALERGY_DRUGS
  is '过敏药物';
comment on column PAT_VISIT.ADVERSE_REACTION_DRUGS
  is '不良反应药物';
comment on column PAT_VISIT.MR_VALUE
  is '病案价值';
comment on column PAT_VISIT.MR_QUALITY
  is '病案质量';
comment on column PAT_VISIT.FOLLOW_INDICATOR
  is '随诊标志';
comment on column PAT_VISIT.FOLLOW_INTERVAL
  is '随诊期限';
comment on column PAT_VISIT.FOLLOW_INTERVAL_UNITS
  is '随诊期限单位';
comment on column PAT_VISIT.DIRECTOR
  is '科主任';
comment on column PAT_VISIT.ATTENDING_DOCTOR
  is '主治医师';
comment on column PAT_VISIT.DOCTOR_IN_CHARGE
  is '经治医师';
comment on column PAT_VISIT.DISCHARGE_DISPOSITION
  is '出院方式';
comment on column PAT_VISIT.TOTAL_COSTS
  is '总费用';
comment on column PAT_VISIT.TOTAL_PAYMENTS
  is '实付费用';
comment on column PAT_VISIT.CATALOG_DATE
  is '编目日期';
comment on column PAT_VISIT.CATALOGER
  is '编目人';
comment on column PAT_VISIT.HBSAG_INDICATOR
  is 'HbsAg';
comment on column PAT_VISIT.HCV_AB_INDICATOR
  is 'HCV-Ab';
comment on column PAT_VISIT.HIV_AB_INDICATOR
  is 'HIV_AB';
comment on column PAT_VISIT.CHIEF_DOCTOR
  is '主任医师';
comment on column PAT_VISIT.ADVANCED_STUDIES_DOCTOR
  is '进修医师';
comment on column PAT_VISIT.PRACTICE_DOCTOR_OF_GRADUATE
  is '研究生实习医师';
comment on column PAT_VISIT.PRACTICE_DOCTOR
  is '实习医师';
comment on column PAT_VISIT.DOCTOR_OF_CONTROL_QUALITY
  is '质控医师';
comment on column PAT_VISIT.NURSE_OF_CONTROL_QUALITY
  is '质控护士';
comment on column PAT_VISIT.DATE_OF_CONTROL_QUALITY
  is '质控日期';
comment on column PAT_VISIT.FIRST_CASE_INDICATOR
  is '是否为本院第一例';
comment on column PAT_VISIT.THIRD_LEVEL_NURS_DAYS
  is '三级护理天数';
comment on column PAT_VISIT.X_EXAM_NO
  is 'X线号';
comment on column PAT_VISIT.TREATED_IN_OTHERS_INDICATOR
  is '入院前经外院诊治';
comment on column PAT_VISIT.TREAT_METHOD
  is '治疗类别';
comment on column PAT_VISIT.HOSP_MADE_MEDICINE_INDICATOR
  is '自制中药制剂';
comment on column PAT_VISIT.PATHOLOGY_NO
  is '病理号';
comment on column PAT_VISIT.UPPER_DOCTOR_GUIDE_EFFECT
  is '上级医生指导作用';
comment on column PAT_VISIT.EMER_TREAT_METHOD
  is '抢救方法';
comment on column PAT_VISIT.ICTUS_INDICATOR
  is '住院期间是否出现急症';
comment on column PAT_VISIT.DIFFICULTY_INDICATOR
  is '住院期间是否出现危难';
comment on column PAT_VISIT.FROM_OTHER_PLACE_INDICATOR
  is '外县市来病人';
comment on column PAT_VISIT.SUSPICION_INDICATOR
  is '出院诊断疑诊';
comment on column PAT_VISIT.CHINESE_MEDICINE_INDICATOR
  is '中医特色治疗';
comment on column PAT_VISIT.OPERATION_SCALE
  is '手术级别';
comment on column PAT_VISIT.DIAGNOSIS_CORRECTNESS
  is '辨证准确度';
comment on column PAT_VISIT.TREAT_METHOD_CORRECTNESS
  is '治法准确度';
comment on column PAT_VISIT.PRESCRIPTION_CORRECTNESS
  is '方药准确度';
comment on column PAT_VISIT.MR_COMPLETE_INDICATOR
  is '病案书写齐全';
comment on column PAT_VISIT.MEDICAL_TERM_CORRECTNESS
  is '医学术语应用正确';
comment on column PAT_VISIT.TREAT_METHOD_JUDGEMENT
  is '治疗类别判断';
comment on column PAT_VISIT.DUTY_NURSE
  is '责任护士';
comment on column PAT_VISIT.DEATH_REASON
  is '死亡原因';
comment on column PAT_VISIT.DEATH_DATE_TIME
  is '死亡时间';
comment on column PAT_VISIT.SCIENCE_RESEARCH_INDICATOR
  is '科研病历';
comment on column PAT_VISIT.FIRST_OPERATION_INDICATOR
  is '手术为本院第一例';
comment on column PAT_VISIT.FIRST_TREATMENT_INDICATOR
  is '治疗为本院第一例';
comment on column PAT_VISIT.FIRST_EXAMINATION_INDICATOR
  is '检查为本院第一例';
comment on column PAT_VISIT.FIRST_DIAGNOSIS_INDICATOR
  is '诊断为本院第一例';
comment on column PAT_VISIT.SERIOUS_INDICATOR
  is '住院期间是否出现危重';
comment on column PAT_VISIT.ADT_ROOM_NO
  is '入院病室';
comment on column PAT_VISIT.DDT_ROOM_NO
  is '出院病室';
comment on column PAT_VISIT.INFECT_INDICATOR
  is '感染类别';
comment on column PAT_VISIT.HEALTH_LEVEL
  is '健康等级';
comment on column PAT_VISIT.MR_INFECT_REPORT
  is '诊断错漏';
comment on column PAT_VISIT.NEWBORN
  is '是否新生儿';
comment on column PAT_VISIT.THIRD_LEVEL_NURSE_DAYS
  is '三级护理天数';
comment on column PAT_VISIT.INSURANCE_AERA
  is '保险地区';
comment on column PAT_VISIT.BODY_WEIGHT
  is '体重';
comment on column PAT_VISIT.BODY_HEIGHT
  is '身高';
comment on column PAT_VISIT.BUSINESS_ZIP_CODE
  is '工作单位邮政编码';
comment on column PAT_VISIT.INFUSION_TRAN_TIMES
  is '输液次数';
comment on column PAT_VISIT.ZYMOSIS_INDICATOR
  is '传染病标志(1已报 2未报 3无)';
comment on column PAT_VISIT.ZYMOSIS_DATE
  is '上报日期';
comment on column PAT_VISIT.BREATH_MACH_TIMES
  is '呼吸机用时';
comment on column PAT_VISIT.COMA_TIMES_B1
  is '昏迷时间(入院前小时)';
comment on column PAT_VISIT.COMA_TIMES_B2
  is '入院前分钟';
comment on column PAT_VISIT.COMA_TIMES_A1
  is '入院后小时';
comment on column PAT_VISIT.COMA_TIMES_A2
  is '入院后分钟';
comment on column PAT_VISIT.TRANS_HOSPITAL
  is '转入医院名称';
comment on column PAT_VISIT.TRANS_COMMUNITY
  is '转社区名称';
comment on column PAT_VISIT.PHONE_NUMBER_BUSINESS
  is '工作单位电话';
comment on column PAT_VISIT.MR_BINDER
  is '整理者';
comment on column PAT_VISIT.WEIGHT_BIRTH
  is '新生儿体重，单位克';
comment on column PAT_VISIT.PAT_CHARGE_TYPE
  is '住院收费类型';
comment on column PAT_VISIT.OPERATOR
  is '收费类型录入人';
comment on column PAT_VISIT.ENTER_DATE
  is '收费类型录入时间';
comment on column PAT_VISIT.PARITY_NO
  is '胎次';
comment on column PAT_VISIT.ONSET_DATE
  is '发病日期';
comment on column PAT_VISIT.NH_SERIAL_NO
  is '农合登记流水号';
comment on column PAT_VISIT.IP
  is '农合IP';
comment on column PAT_VISIT.REGIST_ATTR
  is '农合登记属性';
comment on column PAT_VISIT.VISIT_TYPE
  is '就诊类型';
comment on column PAT_VISIT.YB_INP
  is '医保住院号';
INSERT INTO "PAT_VISIT" ("ID", "ORG_ID", "VISIT_ID", "DEPT_ADMISSION_TO", "ADMISSION_DATE_TIME", "DEPT_DISCHARGE_FROM", "DISCHARGE_DATE_TIME", "OCCUPATION", "MARITAL_STATUS", "IDENTITY", "ARMED_SERVICES", "DUTY", "TOP_UNIT", "SERVICE_SYSTEM_INDICATOR", "UNIT_IN_CONTRACT", "CHARGE_TYPE", "WORKING_STATUS", "INSURANCE_TYPE", "INSURANCE_NO", "SERVICE_AGENCY", "MAILING_ADDRESS", "ZIP_CODE", "NEXT_OF_KIN", "RELATIONSHIP", "NEXT_OF_KIN_ADDR", "NEXT_OF_KIN_ZIPCODE", "NEXT_OF_KIN_PHONE", "PATIENT_CLASS", "ADMISSION_CAUSE", "CONSULTING_DATE", "PAT_ADM_CONDITION", "CONSULTING_DOCTOR", "ADMITTED_BY", "EMER_TREAT_TIMES", "ESC_EMER_TIMES", "SERIOUS_COND_DAYS", "CRITICAL_COND_DAYS", "ICU_DAYS", "CCU_DAYS", "SPEC_LEVEL_NURS_DAYS", "FIRST_LEVEL_NURS_DAYS", "SECOND_LEVEL_NURS_DAYS", "AUTOPSY_INDICATOR", "BLOOD_TYPE", "BLOOD_TYPE_RH", "INFUSION_REACT_TIMES", "BLOOD_TRAN_TIMES", "BLOOD_TRAN_VOL", "BLOOD_TRAN_REACT_TIMES", "DECUBITAL_ULCER_TIMES", "ALERGY_DRUGS", "ADVERSE_REACTION_DRUGS", "MR_VALUE", "MR_QUALITY", "FOLLOW_INDICATOR", "FOLLOW_INTERVAL", "FOLLOW_INTERVAL_UNITS", "DIRECTOR", "ATTENDING_DOCTOR", "DOCTOR_IN_CHARGE", "DISCHARGE_DISPOSITION", "TOTAL_COSTS", "TOTAL_PAYMENTS", "CATALOG_DATE", "CATALOGER", "HBSAG_INDICATOR", "HCV_AB_INDICATOR", "HIV_AB_INDICATOR", "CHIEF_DOCTOR", "ADVANCED_STUDIES_DOCTOR", "PRACTICE_DOCTOR_OF_GRADUATE", "PRACTICE_DOCTOR", "DOCTOR_OF_CONTROL_QUALITY", "NURSE_OF_CONTROL_QUALITY", "DATE_OF_CONTROL_QUALITY", "FIRST_CASE_INDICATOR", "THIRD_LEVEL_NURS_DAYS", "X_EXAM_NO", "TREATED_IN_OTHERS_INDICATOR", "TREAT_METHOD", "HOSP_MADE_MEDICINE_INDICATOR", "PATHOLOGY_NO", "UPPER_DOCTOR_GUIDE_EFFECT", "EMER_TREAT_METHOD", "ICTUS_INDICATOR", "DIFFICULTY_INDICATOR", "FROM_OTHER_PLACE_INDICATOR", "SUSPICION_INDICATOR", "CHINESE_MEDICINE_INDICATOR", "OPERATION_SCALE", "DIAGNOSIS_CORRECTNESS", "TREAT_METHOD_CORRECTNESS", "PRESCRIPTION_CORRECTNESS", "MR_COMPLETE_INDICATOR", "MEDICAL_TERM_CORRECTNESS", "PPER_DOCTOR_GUIDE_EFFECT", "TREAT_METHOD_JUDGEMENT", "DUTY_NURSE", "DEATH_REASON", "DEATH_DATE_TIME", "SCIENCE_RESEARCH_INDICATOR", "FIRST_OPERATION_INDICATOR", "FIRST_TREATMENT_INDICATOR", "FIRST_EXAMINATION_INDICATOR", "FIRST_DIAGNOSIS_INDICATOR", "INFUSION_REACT_INDICATOR", "SERIOUS_INDICATOR", "ADT_ROOM_NO", "DDT_ROOM_NO", "INFECT_INDICATOR", "HEALTH_LEVEL", "MR_INFECT_REPORT", "NEWBORN", "THIRD_LEVEL_NURSE_DAYS", "INSURANCE_AERA", "BODY_WEIGHT", "BODY_HEIGHT", "BUSINESS_ZIP_CODE", "INFUSION_TRAN_TIMES", "CHANGE_INDICATOR", "DOCUM_DATE", "DOCUM_DAYS", "DOCUM_PERSON", "ZYMOSIS_INDICATOR", "ZYMOSIS_DATE", "BREATH_MACH_TIMES", "COMA_TIMES_B1", "COMA_TIMES_B2", "COMA_TIMES_A1", "COMA_TIMES_A2", "TRANS_HOSPITAL", "TRANS_COMMUNITY", "PHONE_NUMBER_BUSINESS", "MR_BINDER", "CARRY_PERSON_NUMBER", "CHANGE_OPERATOR", "DISCHARGE_BED_NO", "DISCHARGE_WARD_CODE", "INP_SERIAL_NO", "NHZYH", "FF_XSDM", "TRANS_FLAG", "GZ_FLAG", "VISIT_DATE", "VISIT_NO", "PHONE_NUMBER_HOME", "PATIENT_AREA", "CATALOG_STATUS", "LOCKED_DATE", "DIAGNOSE_DATE", "MEDICAL_PAY_WAY", "STATISTICS_DIAGNOSE_DATE", "DOCUM_FLAG", "STATISTICS_FLAG", "WEIGHT_BIRTH", "COMA_TIMES_B0", "COMA_TIMES_A0", "PATIENT_AREA_ADDRESS", "PAT_STREET_ADDRESS", "PAT_PHONE", "PAT_ZIP", "PLAN_31_ADMISSION", "REASON_31_ADMISSION", "TUMOR_T", "TUMOR_N", "TUMOR_M", "TUMOR_STAGE", "ADL_ADM", "ADL_DIS", "BASIS_ON", "DIFF_ID", "LOCKED_USER", "INP_NO", "RETURN_INDICATOR", "PAT_CHARGE_TYPE", "OPERATOR", "ENTER_DATE", "PARITY_NO", "ONSET_DATE", "NH_SERIAL_NO", "IP", "REGIST_ATTR", "VISIT_TYPE", "ANTIBIOTICS_USED", "ANTIBIOTICS_COUNT", "ANTIBIOTICS_UNION", "GERMICULTURE_DRUG_TEST", "INFECTIOUS_DISEASE_REPORT", "BLOOD_TRAN_FLAG", "DRGS", "ADMISSION_BED_NO", "NEWBORN_INH_BODY_WEIGHT", "NEWBORN_BODY_WEIGHT", "POF_FLAG", "CLINICAL_PATHWAY", "CHINESE_MED_EQUIPMENT", "CHINESE_MED_TECHNOLOGY", "DIALECTICAL_NURSING", "YB_INP", "NHZZ_TYPE","PATIENT_ID","DEL_FLAG") VALUES ('1', NULL, 1, '140103', TO_TIMESTAMP(' 2010-01-05 1:5:2:000000', 'yyyy-mm-dd hh24:mi:ss:ff'), '140103', TO_TIMESTAMP(' 2010-04-02 0:3:3:000000', 'yyyy-mm-dd hh24:mi:ss:ff'), '27', '已婚', '一般人员', '地方', NULL, NULL, NULL, NULL, '外县农合', NULL, NULL, '110300076701', NULL, '130821', NULL, '宋荣云', '5', NULL, NULL, '3096836', '1', '治疗', TO_TIMESTAMP(' 2011-11-01 1:5:2:000000', 'yyyy-mm-dd hh24:mi:ss:ff'), '3', '000TYW', '刘丽娜', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '未查', '未查', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '甲', NULL, NULL, NULL, '000TYW', '000TYW', NULL, '1', 8513.1420, 8513.14, TO_TIMESTAMP(' 2010-01-04 1:2:0:000000', 'yyyy-mm-dd hh24:mi:ss:ff'), '000QYD', NULL, NULL, NULL, '000TYW', NULL, NULL, NULL, '000TYW', '000ZSJ', TO_TIMESTAMP(' 2011-02-01 0:0:0:000000', 'yyyy-mm-dd hh24:mi:ss:ff'), NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '00ZLJ1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '开放', '205', NULL, NULL, NULL, NULL, NULL, '130821', 70, 165, NULL, NULL, NULL, TO_TIMESTAMP(' 2010-01-01 1:4:2:000000', 'yyyy-mm-dd hh24:mi:ss:ff'), NULL, '000MXC', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 38, '161101', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '130821', '1', NULL, TO_TIMESTAMP(' 2016-11-11 0:0:0:000000', 'yyyy-mm-dd hh24:mi:ss:ff'), '3', NULL, NULL, NULL, NULL, NULL, NULL, '河北承德承德县两家乡两家村', NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '000186', '承德县(市级)', '20', '2101', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '15006135','0');
