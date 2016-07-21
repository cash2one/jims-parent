/*==============================================================*/
/* Table: ELECTRON_ENTER_HOSPITAL   病历文书 */
/* CREATE_DATE: 2016-07-20 11:52:40                             */
/* CREATE_BY: pq                                                */
/*==============================================================*/
-- Add/modify columns
alter table ELECTRON_ENTER_HOSPITAL modify VISIT_ID NUMBER(5);
alter table ELECTRON_ENTER_HOSPITAL add ORG_ID VARCHAR2(64);
-- Add comments to the columns 
comment on column ELECTRON_ENTER_HOSPITAL.ORG_ID
  is '机构代码';