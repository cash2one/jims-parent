/*==============================================================*/
/* Table: EMR_DIAGNOSIS   诊断 */
/* CREATE_DATE: 2016-07-05 11:52:40                             */
/* CREATE_BY: pq                                                */
/*==============================================================*/
-- Add/modify columns
alter table EMR_DIAGNOSIS add VISIT_ID NUMBER(4);
-- Add comments to the columns 
comment on column EMR_DIAGNOSIS.VISIT_ID
  is '住院次数';