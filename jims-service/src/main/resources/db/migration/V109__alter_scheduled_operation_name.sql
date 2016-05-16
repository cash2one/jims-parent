/*==============================================================*/
/* Table: EMR_DIAGNOSIS    手术名称表                                */
/* CREATE_BY :  pq                                             */
/*==============================================================*/
-- 修改手术名称表字段
-- Add/modify columns
alter table SCHEDULED_OPERATION_NAME add PATIENT_ID VARCHAR2(64);
alter table SCHEDULED_OPERATION_NAME add VISIT_ID VARCHAR2(64);
-- Add comments to the columns 
comment on column SCHEDULED_OPERATION_NAME.PATIENT_ID
  is '病人Id';
comment on column SCHEDULED_OPERATION_NAME.VISIT_ID
  is '住院Id';