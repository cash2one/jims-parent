-- Create table
create table BED_CLASS_DICT
(
  ID             VARCHAR2(64) not null,
  BED_CLASS_CODE VARCHAR2(20) not null,
  BED_CLASS_NAME VARCHAR2(100),
  INPUT_CODE     VARCHAR2(8),
  ORG_ID         VARCHAR2(64),
  REMARKS        VARCHAR2(2000),
  UPDATE_BY      VARCHAR2(64),
  CREATE_BY      VARCHAR2(64),
  UPDATE_DATE    DATE,
  DEL_FLAG       VARCHAR2(100),
  CREATE_DATE    DATE
);
-- Add comments to the table
comment on table BED_CLASS_DICT
  is '床位等级维护';
-- Add comments to the columns
comment on column BED_CLASS_DICT.ID
  is '主键';
comment on column BED_CLASS_DICT.BED_CLASS_CODE
  is '床位等级代码对应价表项目代码';
comment on column BED_CLASS_DICT.BED_CLASS_NAME
  is '床位等级名称';
comment on column BED_CLASS_DICT.INPUT_CODE
  is '输入码';
comment on column BED_CLASS_DICT.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table BED_CLASS_DICT
  add constraint BED_CLASS_DICT_PK primary key (ID);
alter table BED_CLASS_DICT
  add constraint BED_CLASS_DICT_UK unique (BED_CLASS_CODE, ORG_ID, BED_CLASS_NAME);
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000101','普通病房床位费（四人间以上）','PTBFCWFS');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000102','普通病房床位费（三人间）','PTBFCWFS');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000103','普通病房床位费（二人间）','PTBFCWFE');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000104','普通病房床位费（单人间）','PTBFCWFD');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000105','普通病房床位费（套间）','PTBFCWFT');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000106','普通病房床位费（新生儿急救床）','PTBFCWFX');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000107','普通病房床位费（母婴病床）','PTBFCWFM');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000108','普通病房床位费（家庭式病床）','PTBFCWFT');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000109','普通病房床位费（康乐待产）','PTBFCWFK');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000110','普通病房床位费（传染精神烧伤产床加收）','PTBFCWFC');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000111','普通病房床位费（四人间以上含卫生间）','PTBFCWFS');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000112','普通病房床位费（三人间含卫生间）','PTBFCWFS');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000113','普通病房床位费（二人间含卫生间）','PTBFCWFE');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000114','普通病房床位费（单人间含卫生间）','PTBFCWFD');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000115','普通病房床位费（四人间以上急诊观察）','PTBFCWFS');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000116','普通病房床位费（三人间急诊观察）','PTBFCWFS');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000117','普通病房床位费（二人间急诊观察）','PTBFCWFE');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000118','普通病房床位费（单人间急诊观察）','PTBFCWFD');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000119','普通病房床位费（四人间以上加床）','PTBFCWFS');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000120','普通病房床位费（三人间加床）','PTBFCWFS');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000121','普通病房床位费（二人间加床）','PTBFCWFE');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000122','普通病房床位费（单人间加床）','PTBFCWFD');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000123','普通病房床位费(精神科加收)','PTBFCWFJ');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000301','监护病房床位费（单人间）','JHBFCWFD');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000302','监护病房床位费（多人间）','JHBFCWFD');
insert into bed_class_dict (id,bed_class_code,bed_class_name,input_code) values(sys_guid(),'11090000400','特殊防护病房床位费','TSFHBFCW');