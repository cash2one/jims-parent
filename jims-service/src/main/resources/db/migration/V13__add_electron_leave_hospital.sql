--  drop table ELECTRON_LEAVE_HOSPITAL cascade constraints;

/*==============================================================*/
/* Table: ELECTRON_LEAVE_HOSPITAL   出院记录                             */
/*==============================================================*/
create table ELECTRON_LEAVE_HOSPITAL  (
   ID                   VARCHAR2(64)                    not null,
   ZHUYUANXINXI_ID      VARCHAR2(64)                    not null,
   PATIENT_ID           VARCHAR2(64)                    not null,
   CHUYUANSHIJIAN       TIMESTAMP,
   CHUYAUNZHENDUAN      CLOB,
   ZHENLIAOJIEGUO       CLOB,
   CHUYUANQINGKUANG     CLOB,
   CHUYUANYIZHU         CLOB,
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(255 CHAR),
   DEL_FLAG             CHAR(1)                        default '0',
   RUYUANBINGQING       CLOB,
   constraint "PK_electron_leave_hospital" primary key (PATIENT_ID, ZHUYUANXINXI_ID)
);

comment on table ELECTRON_LEAVE_HOSPITAL is
'出院记录';

comment on column ELECTRON_LEAVE_HOSPITAL.ID is
'出院小结';

comment on column ELECTRON_LEAVE_HOSPITAL.ZHUYUANXINXI_ID is
'住院信息外键';

comment on column ELECTRON_LEAVE_HOSPITAL.PATIENT_ID is
'病人信息外键';

comment on column ELECTRON_LEAVE_HOSPITAL.CHUYUANSHIJIAN is
'出院时间';

comment on column ELECTRON_LEAVE_HOSPITAL.CHUYAUNZHENDUAN is
'出院诊断';

comment on column ELECTRON_LEAVE_HOSPITAL.ZHENLIAOJIEGUO is
'诊疗结果';

comment on column ELECTRON_LEAVE_HOSPITAL.CHUYUANQINGKUANG is
'出院情况';

comment on column ELECTRON_LEAVE_HOSPITAL.CHUYUANYIZHU is
'出院医嘱';

comment on column ELECTRON_LEAVE_HOSPITAL.CREATE_BY is
'创建者';

comment on column ELECTRON_LEAVE_HOSPITAL.CREATE_DATE is
'创建时间';

comment on column ELECTRON_LEAVE_HOSPITAL.UPDATE_BY is
'更新者';

comment on column ELECTRON_LEAVE_HOSPITAL.UPDATE_DATE is
'更新时间';

comment on column ELECTRON_LEAVE_HOSPITAL.REMARKS is
'备注信息';

comment on column ELECTRON_LEAVE_HOSPITAL.DEL_FLAG is
'删除标记';

comment on column ELECTRON_LEAVE_HOSPITAL.RUYUANBINGQING is
'入院病情';
