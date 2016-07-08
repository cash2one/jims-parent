-- drop table EMR_DIAGNOSIS cascade constraints;

/*==============================================================*/
/* Table: EMR_DIAGNOSIS       诊断列表                                    */
/*==============================================================*/
create table EMR_DIAGNOSIS  (
   ID                     VARCHAR2(64)                    not null,
   PARENT_ID             VARCHAR2(64)                    not null,
   PARENT_IDS            VARCHAR2(500),
   TYPE                   CHAR(1),
   ICD_NAME              VARCHAR2(500),
   DIAGNOSIS_ID          VARCHAR2(500),
   ITEM_NO               INTEGER,
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(100 CHAR),
   DEL_FLAG             CHAR(1),
   DESCRIPTION          CLOB,
   BASIS                CLOB,
   constraint "PK_emr_diagnosis" primary key (ID)
);

comment on table EMR_DIAGNOSIS is
'诊断列表';

comment on column EMR_DIAGNOSIS.PARENT_ID is
'主表id';

comment on column EMR_DIAGNOSIS.PARENT_IDS is
'主表id集合';

comment on column EMR_DIAGNOSIS.ICD_NAME is
'idc10名称';

comment on column EMR_DIAGNOSIS.TYPE is
'诊断类型';

comment on column EMR_DIAGNOSIS.DIAGNOSIS_ID is
'诊断id（ICD10） 内容';

comment on column EMR_DIAGNOSIS.ITEM_NO is
'诊断序号';

comment on column EMR_DIAGNOSIS.CREATE_BY is
'创建者';

comment on column EMR_DIAGNOSIS.CREATE_DATE is
'创建日期';

comment on column EMR_DIAGNOSIS.UPDATE_BY is
'更新者';

comment on column EMR_DIAGNOSIS.UPDATE_DATE is
'更新日期';

comment on column EMR_DIAGNOSIS.REMARKS is
'备注信息';

comment on column EMR_DIAGNOSIS.DEL_FLAG is
'删除标记';

comment on column EMR_DIAGNOSIS.DESCRIPTION is
'诊断描述';

comment on column EMR_DIAGNOSIS.BASIS is
'诊断依据';
