/*==============================================================*/
/* Table: CLINIC_TYPE_SETTING    门诊挂号的收费种类              */
/* CREATE_DATE: 2016-05-16                                      */
/* CREATE_BY :  cfj                                             */
/*==============================================================*/
-- drop table CLINIC_TYPE_SETTING cascade constraints;
create table CLINIC_TYPE_SETTING
(
   ID                   VARCHAR2(64),
   ORG_ID               VARCHAR2(64),
   CLINIC_TYPE_NAME     VARCHAR2(100 char),
   CLINIC_TYPE_CODE     VARCHAR2(100 char),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             char        default '0',
constraint CLINIC_TYPE_SETTING primary key (ID)
);

comment on table CLINIC_TYPE_SETTING is
'号类字典：此表定义了门诊挂号的收费种类，用户定义。';

comment on column CLINIC_TYPE_SETTING.ID is
'主键';

comment on column CLINIC_TYPE_SETTING.ORG_ID is
'所属组织';

comment on column CLINIC_TYPE_SETTING.CLINIC_TYPE_NAME is
'号类名称';

comment on column CLINIC_TYPE_SETTING.CLINIC_TYPE_CODE is
'号类代码';

comment on column CLINIC_TYPE_SETTING.CREATE_DATE is
'创建日期';

comment on column CLINIC_TYPE_SETTING.CREATE_BY is
'创建人';

comment on column CLINIC_TYPE_SETTING.UPDATE_DATE is
'更新日期';

comment on column CLINIC_TYPE_SETTING.UPDATE_BY is
'更新人';

comment on column CLINIC_TYPE_SETTING.REMARKS is
'备注信息';

comment on column CLINIC_TYPE_SETTING.DEL_FLAG is
'删除标志';
