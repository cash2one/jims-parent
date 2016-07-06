/*==============================================================*/
/* Table: orders   入院记录 */
/* CREATE_DATE: 2016-07-04 11:52:40                             */
/* CREATE_BY: pq                                                */
/*==============================================================*/
alter table ELECTRON_ENTER_HOSPITAL add INOROUT_FLAG VARCHAR2(2);
-- Add comments to the columns 
comment on column ELECTRON_ENTER_HOSPITAL.INOROUT_FLAG
  is '门诊/住院';
--删除字段
  alter table electron_enter_hospital drop (zhuyuan_id);

  alter table ELECTRON_ENTER_HOSPITAL add CLINIC_ID VARCHAR2(64);
  comment on column ELECTRON_ENTER_HOSPITAL.CLINIC_ID
  is '门诊主ID';

  -- Add/modify columns
alter table ELECTRON_ENTER_HOSPITAL add VISIT_ID NUMBER(2)  null;
-- Add comments to the columns
comment on column ELECTRON_ENTER_HOSPITAL.VISIT_ID
  is '住院次数';