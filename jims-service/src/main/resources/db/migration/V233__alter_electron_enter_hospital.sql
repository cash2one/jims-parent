/*==============================================================*/
/* Table: orders   入院记录 */
/* CREATE_DATE: 2016-07-04 11:52:40                             */
/* CREATE_BY: pq                                                */
/*==============================================================*/
alter table ELECTRON_ENTER_HOSPITAL add INOROUT_FLAG VARCHAR2(2);
-- Add comments to the columns 
comment on column ELECTRON_ENTER_HOSPITAL.INOROUT_FLAG
  is '门诊/住院';