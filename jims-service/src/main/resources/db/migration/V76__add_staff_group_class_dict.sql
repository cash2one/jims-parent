-- drop table STAFF_GROUP_CLASS_DICT cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: STAFF_GROUP_CLASS_DICT      工作组类字典
/* CREATE_DATE: 2016-05-05                                 */
/* CREATE_BY :  zp      
/*==============================================================*/
create table STAFF_GROUP_CLASS_DICT 
(
   ID                   varchar2(64 char)              null,
   SERIAL_NO            number(11,2)                   null,
   GROUP_CLASS          char(16)                       null,
   HOSPITAL_ID          varchar2(64 char)              null,
   CREATE_BY            varchar(64 char)               null,
   CREATE_DATE          timestamp                      null,
   UPDATE_BY            varchar(64 char)               null,
   UPDATE_DATE          timestamp                      null,
   DEL_FLAG             char(1)                        null,
   REMARKS              varchar2(2000 char)            null,
    constraint PK_STAFF_GROUP_CLASS_DICT primary key  (id)
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