-- 无菌用品包送物领物表
create table ASEPSIS_SEND_REC
(
  id           VARCHAR2(64),
  document_no  VARCHAR2(30) not null,
  item_no      NUMBER,
  from_dept    VARCHAR2(20) not null,
  send_date    DATE,
  item_code    VARCHAR2(20) not null,
  item_name    VARCHAR2(100),
  item_spec    VARCHAR2(20),
  send_amount  NUMBER(6,2),
  get_amount   NUMBER(6,2),
  get_date     DATE,
  get_man      VARCHAR2(20),
  get_flag     VARCHAR2(2),
  units        VARCHAR2(8),
  operator     VARCHAR2(20),
  sender       VARCHAR2(20),
  memos        VARCHAR2(50),
  anti_fee     NUMBER(6,2),
  anti_fee_sum NUMBER(6,2),
  noback_fee   NUMBER(6,2),
  req_date     DATE,
  req_operator VARCHAR2(20),
  org_id       VARCHAR2(64)
);
-- Add comments to the table
comment on table ASEPSIS_SEND_REC
  is '送物领物表';
-- Add comments to the columns 
comment on column ASEPSIS_SEND_REC.document_no
  is '单据号';
comment on column ASEPSIS_SEND_REC.item_no
  is '序号';
comment on column ASEPSIS_SEND_REC.from_dept
  is '送物科室';
comment on column ASEPSIS_SEND_REC.send_date
  is '送物日期';
comment on column ASEPSIS_SEND_REC.item_code
  is '代码';
comment on column ASEPSIS_SEND_REC.item_name
  is '项目名称';
comment on column ASEPSIS_SEND_REC.item_spec
  is '规格';
comment on column ASEPSIS_SEND_REC.send_amount
  is '送物数量';
comment on column ASEPSIS_SEND_REC.get_amount
  is '已领数量';
comment on column ASEPSIS_SEND_REC.get_date
  is '领物日期';
  comment on column ASEPSIS_SEND_REC.get_man
  is '领物人';
comment on column ASEPSIS_SEND_REC.get_flag
  is '领物标记,1-申请确认(未领取),2-部分领取， 3-全部领取';
comment on column ASEPSIS_SEND_REC.units
  is '单位';
comment on column ASEPSIS_SEND_REC.operator
  is '操作员';
comment on column ASEPSIS_SEND_REC.sender
  is '送物人';
comment on column ASEPSIS_SEND_REC.memos
  is '备注';
comment on column ASEPSIS_SEND_REC.anti_fee
  is '消毒费';
comment on column ASEPSIS_SEND_REC.anti_fee_sum
  is '消毒费合计';
comment on column ASEPSIS_SEND_REC.noback_fee
  is '辅料费';
comment on column ASEPSIS_SEND_REC.req_date
  is '申请时间';
comment on column ASEPSIS_SEND_REC.req_operator
  is '申请人';
comment on column ASEPSIS_SEND_REC.org_id
  is '所属机构ID';

alter table ASEPSIS_SEND_REC
  add constraint PK_ASEPSIS_SEND_REC primary key (ID);

