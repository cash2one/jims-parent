/*==============================================================*/
/* Table: EMR_DIAGNOSIS    诊断表                              */
/* CREATE_BY :  pq                                             */
/*=============================================================*/
-- 修改诊断表字段
alter table EMR_DIAGNOSIS add DIAGNOSIS_PARENT varchar2(64 char);
-- Add comments to the columns 
comment on column EMR_DIAGNOSIS.DIAGNOSIS_PARENT
  is '诊断的主表Id';