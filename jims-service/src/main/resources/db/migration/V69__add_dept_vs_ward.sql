-- drop table DEPT_VS_WARD cascade constraints;

/*==============================================================*/
/* Table: DEPT_VS_WARD    临床科室与病房（区）对照         */
/* CREATE_DATE: 2016-05-05                                      */
/* CREATE_BY :  zp                                              */
/*==============================================================*/
create table DEPT_VS_WARD 
(
   ID                   VARCHAR2(64 char)    not null,
   HOSPITAL_ID          VARCHAR2(64 char),
   DEPT_CODE            CHAR(8),
   WARD_CODE            CHAR(8),
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64 char),
   CREATE_DATE          TIMESTAMP,
   DEL_FLAG             CHAR,
   REMARKS              VARCHAR2(2000 char),
   constraint PK_DEPT_VS_WARD primary key (ID)
      
);
comment on table DEPT_VS_WARD is
'临床科室与病房（区）对照';

comment on column DEPT_VS_WARD.ID is
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
