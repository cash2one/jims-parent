-- 无菌用品包库存表
create table ASEPSIS_STOCK
(
  id            VARCHAR2(64),
  document_no   VARCHAR2(30) not null,
  from_dept     VARCHAR2(20) not null,
  item_code     VARCHAR2(20) not null,
  item_name     VARCHAR2(100),
  item_spec     VARCHAR2(20),
  amount        NUMBER(6,2),
  units         VARCHAR2(8),
  memos         VARCHAR2(50),
  anti_date     DATE,
  operator      VARCHAR2(20),
  alter_date    DATE,
  anti_batch_no VARCHAR2(20),
  item_no       NUMBER not null,
  org_id        VARCHAR2(64)
);
-- Add comments to the table
comment on table ASEPSIS_STOCK
  is '无菌用品包库存表';
-- Add comments to the columns 
comment on column ASEPSIS_STOCK.document_no
  is '供应室还物加库存时该单号为消毒批号，其他科室送物时该单号为送物单号';
comment on column ASEPSIS_STOCK.from_dept
  is '包所属科室';
comment on column ASEPSIS_STOCK.item_code
  is '代码';
comment on column ASEPSIS_STOCK.item_name
  is '名称';
comment on column ASEPSIS_STOCK.item_spec
  is '规格';
comment on column ASEPSIS_STOCK.amount
  is '库存数';
comment on column ASEPSIS_STOCK.units
  is '单位';
comment on column ASEPSIS_STOCK.memos
  is '备注';
comment on column ASEPSIS_STOCK.anti_date
  is '消毒日期';
comment on column ASEPSIS_STOCK.operator
  is '操作员';
comment on column ASEPSIS_STOCK.alter_date
  is '修改日期';
comment on column ASEPSIS_STOCK.anti_batch_no
  is '消毒批号';
comment on column ASEPSIS_STOCK.item_no
  is '序号';
comment on column ASEPSIS_STOCK.org_id
  is '所属机构ID';

alter table ASEPSIS_STOCK
  add constraint PK_ASEPSIS_STOCK primary key (ID);

