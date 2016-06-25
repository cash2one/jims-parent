-- 无菌用品包内物品 字典表
create table ASEPSIS_DETAIL_DICT
(
  id                 VARCHAR2(64),
  asepsis_code       VARCHAR2(20) not null,
  item_code          VARCHAR2(20) not null,
  item_name          VARCHAR2(100),
  item_spec          VARCHAR2(20),
  amount             NUMBER(6,2),
  units              VARCHAR2(8),
  item_price         NUMBER(6,2),
  storage            VARCHAR2(20),
  sub_storage        VARCHAR2(20),
  memos              VARCHAR2(50),
  firm_id            VARCHAR2(64),
  org_id             VARCHAR2(64)
);
-- Add comments to the table
comment on table ASEPSIS_DETAIL_DICT
  is '无菌用品包内物品 字典表';
-- Add comments to the columns 
comment on column ASEPSIS_DETAIL_DICT.asepsis_code
  is '包代码';
comment on column ASEPSIS_DETAIL_DICT.item_code
  is '明细代码';
comment on column ASEPSIS_DETAIL_DICT.item_name
  is '明细名称';
comment on column ASEPSIS_DETAIL_DICT.item_spec
  is '明细规格';
comment on column ASEPSIS_DETAIL_DICT.amount
  is '数量';
comment on column ASEPSIS_DETAIL_DICT.units
  is '单位';
comment on column ASEPSIS_DETAIL_DICT.item_price
  is '单价';
comment on column ASEPSIS_DETAIL_DICT.storage
  is '一级库房';
comment on column ASEPSIS_DETAIL_DICT.sub_storage
  is '二级库房';
comment on column ASEPSIS_DETAIL_DICT.memos
  is '备注';
comment on column ASEPSIS_DETAIL_DICT.firm_id
  is '厂家';
comment on column ASEPSIS_DETAIL_DICT.org_id
  is '所属机构ID';

alter table ASEPSIS_DETAIL_DICT
  add constraint PK_ASEPSIS_DETAIL_DICT primary key (ID);

