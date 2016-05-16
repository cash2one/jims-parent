/*==============================================================*/
/* Table: EMR_DIAGNOSIS    诊断表                                */
/* CREATE_BY :  pq                                             */
/*==============================================================*/
-- 修改诊断表字段
-- Add/modify columns
alter table EMR_DIAGNOSIS modify ORG_ID VARCHAR2(64);
alter table EMR_DIAGNOSIS modify CLINIC_ID VARCHAR2(64);
alter table EMR_DIAGNOSIS add VISIT_ID VARCHAR2(64);
-- Add comments to the columns 
comment on column EMR_DIAGNOSIS.VISIT_ID
  is '住院ID';