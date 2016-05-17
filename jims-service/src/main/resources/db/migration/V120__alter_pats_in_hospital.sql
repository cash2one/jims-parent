/*==============================================================*/
/* Table: PATS_IN_HOSPITAL    在院病人记录表                    */
/* CREATE_BY :  pq                                             */
/*==============================================================*/
-- 修改在院病人记录表字段
-- Add/modify columns
alter table PATS_IN_HOSPITAL add ORG_ID VARCHAR2(64);
alter table PATS_IN_HOSPITAL add CLINIC_ID VARCHAR2(64);
alter table PATS_IN_HOSPITAL add PATIENT_ID VARCHAR2(64);
-- Add comments to the columns 
comment on column PATS_IN_HOSPITAL.PATIENT_ID
  is '组织机构';
comment on column PATS_IN_HOSPITAL.CLINIC_ID
  is '就诊ID';
comment on column PATS_IN_HOSPITAL.PATIENT_ID
 is '病人ID';