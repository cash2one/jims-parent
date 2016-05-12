-- Create table
create table CLINIC_ITEM_DICT
(
  ID            VARCHAR2(64) not null,
  ITEM_CLASS    VARCHAR2(1) not null,
  ITEM_CODE     VARCHAR2(20) not null,
  ITEM_NAME     VARCHAR2(100),
  INPUT_CODE    VARCHAR2(8),
  INPUT_CODE_WB VARCHAR2(8),
  EXPAND1       VARCHAR2(100),
  EXPAND2       VARCHAR2(100),
  EXPAND3       VARCHAR2(100),
  EXPAND4       VARCHAR2(100),
  EXPAND5       VARCHAR2(100),
  ITEM_STATUS   VARCHAR2(1) default '0',
  MEMO          VARCHAR2(60),
  REMARKS       VARCHAR2(2000),
  UPDATE_BY     VARCHAR2(64),
  CREATE_BY     VARCHAR2(64),
  UPDATE_DATE   TIMESTAMP(6),
  DEL_FLAG      VARCHAR2(2),
  CREATE_DATE   TIMESTAMP(6),
  ORG_ID        VARCHAR2(64)
);

-- Add comments to the table
comment on table CLINIC_ITEM_DICT
  is '诊疗项目字典表';
-- Add comments to the columns
comment on column CLINIC_ITEM_DICT.ID
  is '主键';
comment on column CLINIC_ITEM_DICT.ITEM_CLASS
  is '项目分类';
comment on column CLINIC_ITEM_DICT.ITEM_CODE
  is '项目代码';
comment on column CLINIC_ITEM_DICT.ITEM_NAME
  is '项目名称';
comment on column CLINIC_ITEM_DICT.INPUT_CODE
  is '输入码';
comment on column CLINIC_ITEM_DICT.EXPAND1
  is '标本';
comment on column CLINIC_ITEM_DICT.EXPAND2
  is '类别';
comment on column CLINIC_ITEM_DICT.EXPAND3
  is '执行科室';
comment on column CLINIC_ITEM_DICT.EXPAND4
  is '频次';
comment on column CLINIC_ITEM_DICT.EXPAND5
  is '长期临时（1 长期，2 临时）';
comment on column CLINIC_ITEM_DICT.ORG_ID
  is '所属组织结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table CLINIC_ITEM_DICT
  add constraint CLINIC_ITEM_DICT_PK primary key (ID);

-- Create/Recreate primary, unique and foreign key constraints
alter table CLINIC_ITEM_DICT
  add constraint CLINIC_ITEM_DICT_UK unique (ITEM_CODE, ORG_ID);


insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('10AFF59F7A184E3782C757E31FCB8645','西药','A','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','1',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('E11F27EF26BD4ECDA9A1DBEF370C9F3B','中药','B','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','2',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('ED2D393A51D84B1F80B83912D16418C5','检验','C','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','3',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('4214D194EEF54312886441536B289269','检查','D','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','4',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('27F396DF3D804BB0B9ADF95570C2898F','治疗','E','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','5',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('0BF2161DEA3E4169A9660C5115B1C067','手术','F','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','6',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('9971A73902B84D0E957519C3442F48FA','麻醉','G','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','7',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('4226B7B9D01C4D4AB91F21174A40B322','护理','H','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','8',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('A3B14E00B4714299B75BE9A4FDA235EF','膳食','I','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','9',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('61BD9A78562D42CE814FB14B21E50004','床位','J','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','10',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('6E094F773AAF4C489452771BB61CD243','其他','Z','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','11',0);


