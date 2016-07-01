-- 无菌用品借物还物表
create table ASEPSIS_LEND_REC
(
  id              VARCHAR2(64),
  document_no     VARCHAR2(30) not null,
  item_no         NUMBER,
  to_dept         VARCHAR2(20) not null,
  lend_date       DATE,
  item_code       VARCHAR2(20) not null,
  item_name       VARCHAR2(100),
  item_spec       VARCHAR2(20),
  lend_amount     NUMBER(6,2),
  units           VARCHAR2(8),
  return_amount   NUMBER(6,2),
  return_date     DATE,
  return_man     VARCHAR2(20),
  return_flag     VARCHAR2(2),
  operator        VARCHAR2(20),
  lender          VARCHAR2(20),
  memos           VARCHAR2(50),
  anti_fee        NUMBER(6,2),
  anti_fee_sum    NUMBER(6,2),
  anti_date       DATE,
  exp_document_no VARCHAR2(30) not null,
  noback_fee      NUMBER(6,2),
  req_date        DATE,
  req_operator    VARCHAR2(20),
  operator2       VARCHAR2(20),
  org_id          VARCHAR2(64)
);
-- Add comments to the table
comment on table ASEPSIS_LEND_REC
  is '借物还物表';
-- Add comments to the columns 
comment on column ASEPSIS_LEND_REC.document_no
  is '单据号';
comment on column ASEPSIS_LEND_REC.item_no
  is '序号';
comment on column ASEPSIS_LEND_REC.to_dept
  is '借物科室';
comment on column ASEPSIS_LEND_REC.lend_date
  is '借物日期';
comment on column ASEPSIS_LEND_REC.item_code
  is '项目代码';
comment on column ASEPSIS_LEND_REC.item_name
  is '项目名称';
comment on column ASEPSIS_LEND_REC.item_spec
  is '规格';
comment on column ASEPSIS_LEND_REC.lend_amount
  is '借物数量';
comment on column ASEPSIS_LEND_REC.units
  is '单位';
comment on column ASEPSIS_LEND_REC.return_amount
  is '已还数量';
comment on column ASEPSIS_LEND_REC.return_date
  is '还物日期';
comment on column ASEPSIS_LEND_REC.return_flag
  is '返还标志:1-未还，2-部分还，3-全部还,4-包对换';
comment on column ASEPSIS_LEND_REC.operator
  is '操作员(同时用于记录对换(回收)的操作员)';
  comment on column ASEPSIS_LEND_REC.return_man
  is '还物人';
comment on column ASEPSIS_LEND_REC.lender
  is '借物人';
comment on column ASEPSIS_LEND_REC.memos
  is '备注';
comment on column ASEPSIS_LEND_REC.anti_fee
  is '消毒费';
comment on column ASEPSIS_LEND_REC.anti_fee_sum
  is '消毒费合计';
comment on column ASEPSIS_LEND_REC.anti_date
  is '消毒日期';
comment on column ASEPSIS_LEND_REC.exp_document_no
  is '借出单号';
comment on column ASEPSIS_LEND_REC.noback_fee
  is '辅料费';
comment on column ASEPSIS_LEND_REC.req_date
  is '申请时间';
comment on column ASEPSIS_LEND_REC.req_operator
  is '申请人';
comment on column ASEPSIS_LEND_REC.operator2
  is '用于记录对换(发放)的操作员';
comment on column ASEPSIS_LEND_REC.org_id
  is '所属机构ID';

alter table ASEPSIS_LEND_REC
  add constraint PK_ASEPSIS_LEND_REC primary key (ID);

