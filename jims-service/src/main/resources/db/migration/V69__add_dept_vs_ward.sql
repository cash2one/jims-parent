-- drop table DEPT_VS_WARD cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: DEPT_VS_WARD      临床科室与病房（区）对照 
/* CREATE_DATE: 2016-05-05                                */
/* CREATE_BY :  zp      
/*==============================================================*/
create table DEPT_VS_WARD 
(
   id                   varchar2(64 char)              not null,
   HOSPITAL_ID          varchar2(64 char)              null,
   DEPT_CODE            char(8)                        null,
   WARD_CODE            char(8)                        null,
   UPDATE_BY            varchar2(64 char)              null,
   UPDATE_DATE          timestamp                      null,
   CREATE_BY            varchar2(64 char)              null,
   CREATE_DATE          timestamp                      null,
   DEL_FLAG             char(1)                        null,
   REMARKS              varchar2(2000 char)            null,
   constraint PK_DEPT_VS_WARD primary key  (id)
);

comment on table DEPT_VS_WARD is 
'临床科室与病房（区）对照';

comment on column DEPT_VS_WARD.id is 
'主键';

comment on column DEPT_VS_WARD.HOSPITAL_ID is 
'医院id';

comment on column DEPT_VS_WARD.DEPT_CODE is 
'科室代码';

comment on column DEPT_VS_WARD.WARD_CODE is 
'病房代码';

comment on column DEPT_VS_WARD.UPDATE_BY is 
'修改人';

comment on column DEPT_VS_WARD.UPDATE_DATE is 
'修改时间';

comment on column DEPT_VS_WARD.CREATE_BY is 
'创建人';

comment on column DEPT_VS_WARD.CREATE_DATE is 
'创建时间';

comment on column DEPT_VS_WARD.DEL_FLAG is 
'删除标记';

comment on column DEPT_VS_WARD.REMARKS is 
'备注信息';