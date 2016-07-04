
--drop table PRE_DISCHARGE_DICT cascade constraints;

/*==============================================================*/
/* Table: PRE_DISCHARGE_DICT    出院通知单 - 出院方式字典表 */
/* CREATE_DATE: 2016-07-04 11:52:40                             */
/* CREATE_BY: pq                                                */
/*==============================================================*/
create table PRE_DISCHARGE_DICT 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   DISCHARGE_NAME       VARCHAR2(100)        not null,
   DISCHARGE_CODE       VARCHAR2(16),
   ORDERS_TEXT          VARCHAR2(100),     
   ORDERS_CODE          VARCHAR2(16),
   INPUT_CODE           VARCHAR2(16),
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200),
   DEL_FLAG             char(1) default '0',
   constraint PK_PRE_DISCHARGE_DICT primary key (ID)
   );

comment on table PRE_DISCHARGE_DICT is
'出院通知单 - 出院方式字典表';

comment on column PRE_DISCHARGE_DICT.ID is
'主键';

comment on column PRE_DISCHARGE_DICT.ORG_ID is
'机构ID';

comment on column PRE_DISCHARGE_DICT.DISCHARGE_NAME is
'出院方式名称';

comment on column PRE_DISCHARGE_DICT.DISCHARGE_CODE is
'出院方式代码';

comment on column PRE_DISCHARGE_DICT.ORDERS_TEXT is
'医嘱内容';

comment on column PRE_DISCHARGE_DICT.ORDERS_CODE is
'医嘱代码';

comment on column PRE_DISCHARGE_DICT.INPUT_CODE is
'输入码';

comment on column PRE_DISCHARGE_DICT.CREATE_BY is
'创建人';

comment on column PRE_DISCHARGE_DICT.CREATE_DATE is
'创建时间';

comment on column PRE_DISCHARGE_DICT.UPDATE_BY is
'更新人';

comment on column PRE_DISCHARGE_DICT.UPDATE_DATE is
'更新时间';

comment on column PRE_DISCHARGE_DICT.REMARKS is
'备注';

comment on column PRE_DISCHARGE_DICT.DEL_FLAG is
'是否删除';