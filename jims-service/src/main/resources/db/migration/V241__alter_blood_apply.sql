/*==============================================================*/
/* Table: BLOOD_APPLY   用血 */
/* CREATE_DATE: 2016-07-07 11:52:40                             */
/* CREATE_BY: pq                                                */
/*==============================================================*/
alter table BLOOD_APPLY add APPLY_STATUS VARCHAR2(2);
-- Add comments to the columns 
comment on column BLOOD_APPLY.APPLY_STATUS
  is '用血申请状态';
