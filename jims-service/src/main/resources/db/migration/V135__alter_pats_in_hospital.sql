/*==============================================================*/
/* Table: PATS_IN_HOSPITAL    在院病人记录                     */
/* CREATE_BY :  cfj                        */
/*==============================================================*/
-- 添加表字段类型
alter table PATS_IN_HOSPITAL add HOSID VARCHAR2(64 CHAR);
alter table PATS_IN_HOSPITAL add CLINIC_ID VARCHAR2(64 CHAR);
-- Add comments to the columns
comment on column PATS_IN_HOSPITAL.HOSID
  is '医院ID';
comment on column PATS_IN_HOSPITAL.CLINIC_ID
  is '就诊记录ID';