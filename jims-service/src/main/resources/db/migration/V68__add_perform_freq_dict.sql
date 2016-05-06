-- drop table PERFORM_FREQ_DICT cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: PERFORM_FREQ_DICT      医嘱执行频率字典 
/* CREATE_DATE: 2016-05-05                                 */
/* CREATE_BY :  zp      
/*==============================================================*/
create table PERFORM_FREQ_DICT 
(
   id                   varchar2(64 char)              not null,
   HOSPITAL_ID          varchar2(64 char),
   FREQ_DESC            char(16),
   FREQ_COUNTER         number(11,2) ,
   FREQ_INTERVAL        number(11,2),
   FREQ_INTERVAL_UNITS  char(4),
   CREATE_BY            varchar2(64 char),
   CREATE_DATE          TIMESTAMP(6),
   UPDATE_BY            varchar2(64 char),
   UPDATE_DATE          TIMESTAMP(6),
   DEL_FLAG             char(1) ,
   REMARKS              varchar2(2000 char),
  constraint "PK_PERFORM_FREQ_DICT" primary key (ID)
  );

comment on table PERFORM_FREQ_DICT is 
'医嘱执行频率字典';

comment on column PERFORM_FREQ_DICT.id is 
'主键';

comment on column PERFORM_FREQ_DICT.HOSPITAL_ID is 
'医院id';

comment on column PERFORM_FREQ_DICT.FREQ_DESC is 
'执行频率描述';

comment on column PERFORM_FREQ_DICT.FREQ_COUNTER is 
'频率次数';

comment on column PERFORM_FREQ_DICT.FREQ_INTERVAL is 
'频率间隔';

comment on column PERFORM_FREQ_DICT.FREQ_INTERVAL_UNITS is 
'频率间隔单位';

comment on column PERFORM_FREQ_DICT.CREATE_BY is 
'创建人';

comment on column PERFORM_FREQ_DICT.CREATE_DATE is 
'创建时间';

comment on column PERFORM_FREQ_DICT.UPDATE_BY is 
'修改人';

comment on column PERFORM_FREQ_DICT.UPDATE_DATE is 
'修改时间';

comment on column PERFORM_FREQ_DICT.DEL_FLAG is 
'删除标记';

comment on column PERFORM_FREQ_DICT.REMARKS is 
'备注信息';