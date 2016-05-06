-- drop table STAFF_GROUP_CLASS_DICT cascade constraints;

/*==============================================================*/
/* Table: STAFF_GROUP_CLASS_DICT    工作组类字典                 */
/* CREATE_DATE: 2016-05-05                                      */
/* CREATE_BY :  zp                                              */
/*==============================================================*/
create table STAFF_GROUP_CLASS_DICT 
(
   ID                   VARCHAR2(64 char)    not null,
   SERIAL_NO            NUMBER(11,2),
   GROUP_CLASS          CHAR(16),
   HOSPITAL_ID          VARCHAR2(64 char),
   CREATE_BY            VARCHAR2(64 char),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             CHAR,
   REMARKS              VARCHAR2(2000 char),
   constraint PK_STAFF_GROUP_CLASS_DICT primary key (ID)
);
comment on table STAFF_GROUP_CLASS_DICT is
'工作组类字典';

comment on column STAFF_GROUP_CLASS_DICT.ID is
'主键';

comment on column STAFF_GROUP_CLASS_DICT.SERIAL_NO is
'序号(反映项目的排列顺序)';

comment on column STAFF_GROUP_CLASS_DICT.GROUP_CLASS is
'组类';

comment on column STAFF_GROUP_CLASS_DICT.HOSPITAL_ID is
'医院id';

comment on column STAFF_GROUP_CLASS_DICT.CREATE_BY is
'创建人';

comment on column STAFF_GROUP_CLASS_DICT.CREATE_DATE is
'创建时间';

comment on column STAFF_GROUP_CLASS_DICT.UPDATE_BY is
'修改人';

comment on column STAFF_GROUP_CLASS_DICT.UPDATE_DATE is
'修改时间';

comment on column STAFF_GROUP_CLASS_DICT.DEL_FLAG is
'删除标记';

comment on column STAFF_GROUP_CLASS_DICT.REMARKS is
'备注信息';
