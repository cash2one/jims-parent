-- Add/modify columns 
alter table EMR_DIAGNOSIS add PATIENT_ID VARCHAR2(64);
alter table EMR_DIAGNOSIS add TREAT_DAYS NUMBER(4);
alter table EMR_DIAGNOSIS add TREAT_RESULT VARCHAR2(225);
alter table EMR_DIAGNOSIS add OPER_TREAT_INDICATOR NUMBER(1);
alter table EMR_DIAGNOSIS add PATHOLOGY_NO VARCHAR2(40);
alter table EMR_DIAGNOSIS add DIAGNOSIS_DOC VARCHAR2(64);
alter table EMR_DIAGNOSIS add INOROUT_FLAG CHAR(1);
alter table EMR_DIAGNOSIS add  DIAGNOSIS_DATE TIMESTAMP(6)
-- Add comments to the columns
comment on column EMR_DIAGNOSIS.PATIENT_ID
  is '病人标识';
comment on column EMR_DIAGNOSIS.TREAT_DAYS
  is '治疗天数';
comment on column EMR_DIAGNOSIS.TREAT_RESULT
  is '治疗结果';
comment on column EMR_DIAGNOSIS.OPER_TREAT_INDICATOR
  is '手术治疗标志';
comment on column EMR_DIAGNOSIS.PATHOLOGY_NO
  is '病理号';
comment on column EMR_DIAGNOSIS.DIAGNOSIS_DOC
  is '诊断医生';
comment on column EMR_DIAGNOSIS.INOROUT_FLAG
  is '门诊/住院';
comment on column EMR_DIAGNOSIS.DIAGNOSIS_DATE
  is '诊断日期';