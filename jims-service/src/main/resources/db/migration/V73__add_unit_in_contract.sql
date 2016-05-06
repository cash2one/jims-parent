-- drop table UNIT_IN_CONTRACT cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: UNIT_IN_CONTRACT      合同单位记录
/* CREATE_DATE: 2016-05-05                                 */
/* CREATE_BY :  zp      
/*==============================================================*/
create table UNIT_IN_CONTRACT 
(
   id                   varchar2(64 char)              not null,
   UNIT_CODE            char(11)                       null,
   UNIT_NAME            char(40)                       null,
   INPUT_CODE           char(8)                        null,
   ADDRESS              char(40)                       null,
   UNIT_TYPE            char(1)                        null,
   SUBORDINATE_TO       char(11)                       null,
   DISTANCE_TO_HOSPITAL number(4,2)                    null,
   REGULAR_NUM          number(4,2)                    null,
   TEMP_NUM             number(4,2)                    null,
   RETIRED_NUM          number(4,2)                    null,
   INPUT_CODE_WB        number(8,2)                    null,
   CREATE_BY            varchar2(64 char)              null,
   CREATE_DATE          timestamp                      null,
   UPDATE_BY            varchar2(64 char)              null,
   UPDATE_DATE          timestamp                      null,
   DEL_FLAG             char(1)                        null,
   REMARKS              varchar2(2000 char)            null,
   constraint PK_UNIT_IN_CONTRACT primary key (id)
);

comment on table UNIT_IN_CONTRACT is 
'合同单位记录';

comment on column UNIT_IN_CONTRACT.id is 
'主键';

comment on column UNIT_IN_CONTRACT.UNIT_CODE is 
'合同单位代码';

comment on column UNIT_IN_CONTRACT.UNIT_NAME is 
'合同单位名称';

comment on column UNIT_IN_CONTRACT.INPUT_CODE is 
'输入码';

comment on column UNIT_IN_CONTRACT.ADDRESS is 
'单位地址';

comment on column UNIT_IN_CONTRACT.UNIT_TYPE is 
'单位性质';

comment on column UNIT_IN_CONTRACT.SUBORDINATE_TO is 
'隶属单位';

comment on column UNIT_IN_CONTRACT.DISTANCE_TO_HOSPITAL is 
'就医距离';

comment on column UNIT_IN_CONTRACT.REGULAR_NUM is 
'在编人数';

comment on column UNIT_IN_CONTRACT.TEMP_NUM is 
'非编人数';

comment on column UNIT_IN_CONTRACT.RETIRED_NUM is 
'离退休人数';

comment on column UNIT_IN_CONTRACT.INPUT_CODE_WB is 
'五笔码';

comment on column UNIT_IN_CONTRACT.CREATE_BY is 
'创建人';

comment on column UNIT_IN_CONTRACT.CREATE_DATE is 
'创建时间';

comment on column UNIT_IN_CONTRACT.UPDATE_BY is 
'修改人';

comment on column UNIT_IN_CONTRACT.UPDATE_DATE is 
'修改时间';

comment on column UNIT_IN_CONTRACT.DEL_FLAG is 
'删除标记';

comment on column UNIT_IN_CONTRACT.REMARKS is 
'备注信息';
