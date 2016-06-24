

/*drop table LEND_BED_LOG cascade constraints;*/

/*==============================================================*/
/* Table: LEND_BED_LOG                                          */
/*==============================================================*/
create table LEND_BED_LOG 
(
   ID                   VARCHAR(64)          not null,
   WARD_CODE            VARCHAR(64)          ,
   LEND_START_DATE      TIMESTAMP            ,
   PATIENT_ID           VARCHAR(64)         ,
   VISIT_ID             VARCHAR(64)         ,
   DEPT_CODE            VARCHAR2(8),
   LEND_WARD_CODE       VARCHAR2(8),
   LEND_DEPT_CODE       VARCHAR(64),
   LEND_END_DATE        TIMESTAMP,
   ORG_ID               VARCHAR(64),
   constraint PK_LEND_BED_LOG primary key (ID)
);

comment on column LEND_BED_LOG.ID is
'ID';

comment on column LEND_BED_LOG.WARD_CODE is
'护理单元';

comment on column LEND_BED_LOG.LEND_START_DATE is
'借床起始日期';

comment on column LEND_BED_LOG.PATIENT_ID is
'病人ID';

comment on column LEND_BED_LOG.VISIT_ID is
'住院次数';

comment on column LEND_BED_LOG.DEPT_CODE is
'部门代码';

comment on column LEND_BED_LOG.LEND_WARD_CODE is
'借床护理单元';

comment on column LEND_BED_LOG.LEND_DEPT_CODE is
'借床科室代码';

comment on column LEND_BED_LOG.LEND_END_DATE is
'借床结束日期';

comment on column LEND_BED_LOG.ORG_ID is
'机构标识';
