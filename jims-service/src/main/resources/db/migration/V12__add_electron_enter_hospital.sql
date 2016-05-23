-- drop table ELECTRON_ENTER_HOSPITAL cascade constraints;

/*==============================================================*/
/* Table: ELECTRON_ENTER_HOSPITAL     入院记录                          */
/*==============================================================*/
create table ELECTRON_ENTER_HOSPITAL  (
   ID                   VARCHAR2(64)                    not null,
   ZHUYUAN_ID           VARCHAR2(64)                    not null,
   PATIENT_ID           VARCHAR2(64)                    not null,
   ZHUSU                CLOB,
   XIANBINGSHI          CLOB,
   FABINGQINGKUANG      CLOB,
   JIWANGSHI            CLOB,
   GERENSHI             CLOB,
   HUNYUSHI             CLOB,
   JIAZUSHI             CLOB,
   TIWEN                VARCHAR2(100),
   MAIBO                VARCHAR2(100),
   HUXI                 VARCHAR2(100),
   XUEYA                VARCHAR2(100 CHAR),
   ZHUANKEQINGKUANG     CLOB,
   FUZHUJIANCHA         CLOB,
   CHUBUZHENDUAN        CLOB,
   XIUZHENGZHENDUAN     CLOB,
   ZHUGUANYISHENG       VARCHAR2(100),
   SHANGJIYISHI         VARCHAR2(100),
   RIQI                 TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(100 CHAR),
   DEL_FLAG             CHAR(1)                        default '0',
   BINGSHICHENSHUZHE    VARCHAR2(100),
   YUEJINGSHI           CLOB,
   TIGEJIANCHA          CLOB,
   XIAOJIEYUFENXI       CLOB,
   CAIJIRIQI            TIMESTAMP,
   ISDEPEND             VARCHAR2(50),
   constraint "PK_electron_enter_hospital" primary key (ID)
);

comment on table ELECTRON_ENTER_HOSPITAL is
'入院记录';

comment on column ELECTRON_ENTER_HOSPITAL.ID is
'入院记录';

comment on column ELECTRON_ENTER_HOSPITAL.ZHUYUAN_ID is
'住院信息表外键';

comment on column ELECTRON_ENTER_HOSPITAL.PATIENT_ID is
'病人信息表外键';

comment on column ELECTRON_ENTER_HOSPITAL.ZHUSU is
'主诉';

comment on column ELECTRON_ENTER_HOSPITAL.XIANBINGSHI is
'现病史';

comment on column ELECTRON_ENTER_HOSPITAL.FABINGQINGKUANG is
'发病后一般情况';

comment on column ELECTRON_ENTER_HOSPITAL.JIWANGSHI is
'既往史';

comment on column ELECTRON_ENTER_HOSPITAL.GERENSHI is
'个人史';

comment on column ELECTRON_ENTER_HOSPITAL.HUNYUSHI is
'婚育史';

comment on column ELECTRON_ENTER_HOSPITAL.JIAZUSHI is
'家族史';

comment on column ELECTRON_ENTER_HOSPITAL.TIWEN is
'体温';

comment on column ELECTRON_ENTER_HOSPITAL.MAIBO is
'脉搏';

comment on column ELECTRON_ENTER_HOSPITAL.HUXI is
'呼吸';

comment on column ELECTRON_ENTER_HOSPITAL.XUEYA is
'血压';

comment on column ELECTRON_ENTER_HOSPITAL.ZHUANKEQINGKUANG is
'专科情况';

comment on column ELECTRON_ENTER_HOSPITAL.FUZHUJIANCHA is
'辅助检查';

comment on column ELECTRON_ENTER_HOSPITAL.CHUBUZHENDUAN is
'初步诊断';

comment on column ELECTRON_ENTER_HOSPITAL.XIUZHENGZHENDUAN is
'修正诊断';

comment on column ELECTRON_ENTER_HOSPITAL.ZHUGUANYISHENG is
'主管医生';

comment on column ELECTRON_ENTER_HOSPITAL.SHANGJIYISHI is
'上级医师';

comment on column ELECTRON_ENTER_HOSPITAL.RIQI is
'日期';

comment on column ELECTRON_ENTER_HOSPITAL.CREATE_BY is
'创建者';

comment on column ELECTRON_ENTER_HOSPITAL.CREATE_DATE is
'创建时间';

comment on column ELECTRON_ENTER_HOSPITAL.UPDATE_BY is
'更新者';

comment on column ELECTRON_ENTER_HOSPITAL.UPDATE_DATE is
'更新时间';

comment on column ELECTRON_ENTER_HOSPITAL.REMARKS is
'备注信息';

comment on column ELECTRON_ENTER_HOSPITAL.DEL_FLAG is
'删除标记';

comment on column ELECTRON_ENTER_HOSPITAL.BINGSHICHENSHUZHE is
'病史陈述者';

comment on column ELECTRON_ENTER_HOSPITAL.YUEJINGSHI is
'月经史';

comment on column ELECTRON_ENTER_HOSPITAL.TIGEJIANCHA is
'体格检查';

comment on column ELECTRON_ENTER_HOSPITAL.XIAOJIEYUFENXI is
'小结与分析';

comment on column ELECTRON_ENTER_HOSPITAL.CAIJIRIQI is
'病史采集日期';

comment on column ELECTRON_ENTER_HOSPITAL.ISDEPEND is
'病史陈述者是否可靠';
