-- 无菌用品消毒表
create table ASEPSIS_ANTI_REC
(
  id            VARCHAR2(64),
  documnet_no   VARCHAR2(30) not null,
  asepsis_code  VARCHAR2(20) not null,
  asepsis_name  VARCHAR2(100),
  asepsis_spec  VARCHAR2(20),
  units         VARCHAR2(8),
  belong_dept   VARCHAR2(20) not null,
  anti_date     DATE,
  anti_operator VARCHAR2(20),
  anti_ways     VARCHAR2(20),
  ster_operator VARCHAR2(20),
  ster_date     DATE,
  pack_operator VARCHAR2(20),
  pack_date     DATE,
  memos         VARCHAR2(50),
  asepsis_state VARCHAR2(2),
  amount        NUMBER(6,2),
  anti_batch_no VARCHAR2(30),
  imp_date      DATE not null,
  boiler_no     VARCHAR2(2),
  boiler_times  VARCHAR2(2),
  checker       VARCHAR2(20),
  item_no       NUMBER not null,
  pack_ways     VARCHAR2(20),
  clean_ways    VARCHAR2(20),
  clean_no      VARCHAR2(2),
  clean_times   VARCHAR2(2),
  org_id        VARCHAR2(64)
);
-- Add comments to the table
comment on table ASEPSIS_ANTI_REC
  is '无菌用品消毒表';
-- Add comments to the columns 
comment on column ASEPSIS_ANTI_REC.documnet_no
  is '业务单据号(S开头为送物单据，T开头为对换单据，数字开头为供应室消毒批号)';
comment on column ASEPSIS_ANTI_REC.asepsis_code
  is '代码';
comment on column ASEPSIS_ANTI_REC.asepsis_name
  is '名称';
comment on column ASEPSIS_ANTI_REC.asepsis_spec
  is '规格';
comment on column ASEPSIS_ANTI_REC.units
  is '单位';
comment on column ASEPSIS_ANTI_REC.belong_dept
  is '所属科室';
comment on column ASEPSIS_ANTI_REC.anti_date
  is '消毒日期';
comment on column ASEPSIS_ANTI_REC.anti_operator
  is '消毒人';
comment on column ASEPSIS_ANTI_REC.anti_ways
  is '消毒方式';
comment on column ASEPSIS_ANTI_REC.ster_operator
  is '清洗人';
comment on column ASEPSIS_ANTI_REC.ster_date
  is '清洗日期';
comment on column ASEPSIS_ANTI_REC.pack_operator
  is '打包人';
comment on column ASEPSIS_ANTI_REC.pack_date
  is '打包日期';
comment on column ASEPSIS_ANTI_REC.memos
  is '备注';
comment on column ASEPSIS_ANTI_REC.asepsis_state
  is '0-未清洗；1-清洗未打包；2-打包未消毒；3-消毒加库存；null-未作任何处理';
comment on column ASEPSIS_ANTI_REC.amount
  is '数量';
comment on column ASEPSIS_ANTI_REC.anti_batch_no
  is '消毒包处理后的单据号，用来标记是同一批消毒、打包的';
comment on column ASEPSIS_ANTI_REC.imp_date
  is '还物/送物时间';
comment on column ASEPSIS_ANTI_REC.boiler_no
  is '锅号';
comment on column ASEPSIS_ANTI_REC.boiler_times
  is '锅次';
comment on column ASEPSIS_ANTI_REC.checker
  is '查对者';
comment on column ASEPSIS_ANTI_REC.pack_ways
  is '打包方法';
comment on column ASEPSIS_ANTI_REC.clean_ways
  is '清洗方式';
comment on column ASEPSIS_ANTI_REC.clean_no
  is '清洗机号';
comment on column ASEPSIS_ANTI_REC.clean_times
  is '清洗机次';
comment on column ASEPSIS_ANTI_REC.org_id
  is '所属机构ID';

alter table ASEPSIS_ANTI_REC
  add constraint PK_ASEPSIS_ANTI_REC primary key (ID);

