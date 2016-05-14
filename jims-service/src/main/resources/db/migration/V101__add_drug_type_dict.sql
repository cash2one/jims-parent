/*==============================================================*/
/* Table: drug_type_dict    药品库房字典                             */
/* CREATE_DATE: 2016-05-14                                      */
/* CREATE_BY :  朱齐                                          */
/*==============================================================*/

-- Create table
create table DRUG_TYPE_DICT
(
  id        VARCHAR2(64) not null,
  type_name VARCHAR2(32),
  type_code VARCHAR2(16)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table DRUG_TYPE_DICT
  is '药品所属库房类别';
-- Add comments to the columns
comment on column DRUG_TYPE_DICT.type_name
  is '库房名称';
comment on column DRUG_TYPE_DICT.type_code
  is '库房代码';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_TYPE_DICT
  add constraint DRUG_TYPE_DICT_PK primary key (ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

--insert
insert into drup_type_dict (ID, TYPE_NAME, TYPE_CODE)
values ('001', '西药', '1');

insert into drup_type_dict (ID, TYPE_NAME, TYPE_CODE)
values ('002', '中草药', '2');

insert into drup_type_dict (ID, TYPE_NAME, TYPE_CODE)
values ('003', '中成药', '3');

insert into drup_type_dict (ID, TYPE_NAME, TYPE_CODE)
values ('004', '全部', '4');

