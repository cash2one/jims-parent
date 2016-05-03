/*==============================================================*/
/* Table: EMR_DATA_ICD10      数据表，ICD-10编码表               */
/*==============================================================*/
create table EMR_DATA_ICD10
(
   ID                   VARCHAR2(64)         not null,
   PID                  INTEGER,
   CODE                 VARCHAR2(255),
   ZHONGWEN_MINGCHENG   VARCHAR2(255),
   PINYIN_INDEX         VARCHAR2(255),
   KEYWORD_SHUOMING     VARCHAR2(255),
   YINGWEN_MINGCHENG    VARCHAR2(255),
   SHUOMING             CLOB,
   OTHER_INFO           CLOB,
   SUB_DIRECT_NUMBER    INTEGER,
   SUB_TOTAL_NUMBER     INTEGER,
   DEL_FLAG             VARCHAR2(255),
   constraint "PK_emr_data_icd10" primary key (ID)
);

comment on table EMR_DATA_ICD10 is
'数据表，ICD-10编码表';

comment on column EMR_DATA_ICD10.PID is
'父id';

comment on column EMR_DATA_ICD10.CODE is
'ICD-10编码';

comment on column EMR_DATA_ICD10.ZHONGWEN_MINGCHENG is
'中文名称';

comment on column EMR_DATA_ICD10.PINYIN_INDEX is
'拼音检索码';

comment on column EMR_DATA_ICD10.KEYWORD_SHUOMING is
'用于检索的关键词，同时也是简短说明';

comment on column EMR_DATA_ICD10.YINGWEN_MINGCHENG is
'英文名称';

comment on column EMR_DATA_ICD10.SHUOMING is
'具体说明内容';

comment on column EMR_DATA_ICD10.OTHER_INFO is
'其它说明';

comment on column EMR_DATA_ICD10.SUB_DIRECT_NUMBER is
'直接子元素个数 1:是 0:否';

comment on column EMR_DATA_ICD10.SUB_TOTAL_NUMBER is
'所有子元素个数 1:是 0:否';
);


