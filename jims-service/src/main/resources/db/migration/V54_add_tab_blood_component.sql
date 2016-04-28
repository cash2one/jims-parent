-- Create table
create table BLOOD_COMPONENT
(
  BLOOD_TYPE      VARCHAR2(10) not null,
  BLOOD_TYPE_NAME VARCHAR2(40),
  BLOOD_MATCH     VARCHAR2(1),
  USEFUL_LIFE     NUMBER(4),
  TEMPERATURE     VARCHAR2(24),
  UNIT            VARCHAR2(4),
  STOP_DATE       DATE,
  SORT_NUM        NUMBER(2),
  ID              VARCHAR2(64) not null,
  REMARKS         VARCHAR2(2000),
  UPDATE_BY       VARCHAR2(64),
  CREATE_BY       VARCHAR2(64),
  UPDATE_DATE     TIMESTAMP(6),
  DEL_FLAG        VARCHAR2(2),
  CREATE_DATE     TIMESTAMP(6)
);
-- Add comments to the table 
comment on table BLOOD_COMPONENT
  is '血液成分字典';
-- Add comments to the columns 
comment on column BLOOD_COMPONENT.BLOOD_TYPE
  is '血液成分';
comment on column BLOOD_COMPONENT.BLOOD_TYPE_NAME
  is '血液成分名称';
comment on column BLOOD_COMPONENT.BLOOD_MATCH
  is '是否配血';
comment on column BLOOD_COMPONENT.USEFUL_LIFE
  is '保存天数';
comment on column BLOOD_COMPONENT.TEMPERATURE
  is '保存温度';
comment on column BLOOD_COMPONENT.UNIT
  is '计量单位';
comment on column BLOOD_COMPONENT.STOP_DATE
  is '停止日期';
comment on column BLOOD_COMPONENT.SORT_NUM
  is '排序序号';
comment on column BLOOD_COMPONENT.ID
  is '主键';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BLOOD_COMPONENT
  add constraint BLOOD_COMPONENT_PK primary key (ID);

--血液要求字典表 应为平台级别数据，不需区分组织机构，维护界面应该在血库管理此程序中记性维护
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'QXRHYX','全血Rh(-)','1','35','4-6℃贮存','U','','16');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'QX','全血','1','35','4±2℃贮存','ml','','1');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'XXBDXJ','新鲜冰冻血浆','1','365','-18℃贮存','ml','','2');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'PTBDXJ','普通冰冻血浆','1','1460','-18℃贮存','ml','','3');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'SGFNSXXB','手工分浓缩血小板','1','5','22℃贮存振摇','U','','27');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'LCD','冷沉淀','1','365','-35℃贮存','U','','5');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'JCSBXBXXB','机采少白细胞血小板-5','1','5','22℃贮存振摇','U','','28');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'XDHXB','洗涤红细胞','1','1','立即输用','U','','10');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'XFHXB','悬浮红细胞','1','35','4-6℃贮存','U','','15');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'DCXXBRHYX','单采血小板Rh(-)-365','1','365','22℃贮存振摇','U','','30');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'XFHXB-0.5','悬浮红细胞-0.5','1','35','4-6℃贮存','U','','29');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'XFHXBRHYX','悬浮红细胞Rh（-）','1','35','4±2℃贮存','U','','12');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'XFSBXBHXB','悬浮少白细胞红细胞','1','35','4-6℃贮存','U','','13');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'XXBDXJRHYX','新鲜冰冻血浆Rh（-）','1','365','-18℃贮存','ml','','17');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'LCDNXYZ','冷沉淀凝血因子','1','365','-18℃贮存','U','','6');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'DCXXB-1','单采血小板-1','1','1','22±2℃贮存','U','','18');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'DCXXB-5','单采血小板-5','1','5','22±2℃贮存','U','','7');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'DCXXB-365','单采血小板-365','1','365','-80℃贮存','U','','4');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'XDHXBRHYX','洗涤红细胞Rh(-)','1','1','4-6℃贮存','U','','8');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'PTBDXJRHYX','普通冰冻血浆Rh(-)','1','1460','-18℃贮存','ml','','9');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'PCLXB','单采粒细胞','1','1','立即输注','U','','11');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'PCLBXB','单采淋巴细胞','1','1','立即输注','U','','19');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'NSXXB','浓缩血小板','1','1','22℃贮存振摇','U','','20');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'NSXXBRHYX','浓缩红细胞Rh(-)','1','1','4℃贮存','U','','21');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'NSHXB','浓缩红细胞','1','35','4℃贮存','U','','22');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'JDQGYHXB','Rh(-)冰冻解冻去甘油红细胞（全自动法）','1','1','4℃贮存','U','','14');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'DCXXBDXJ','单采新鲜冰冻血浆','1','365','-30℃贮存','ml','','23');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'DCWZXGXB','单采外周血干细胞','1','60','-80℃贮存','U','','24');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'BDHXBRHYX','冰冻红细胞Rh(-)','1','3650','-80℃贮存','U','','25');
insert into blood_component (id,blood_type,blood_type_name,blood_match,useful_life,temperature,unit,stop_date,sort_num) values (sys_guid,'BDHXB','冰冻红细胞','1','3650','-80℃贮存','U','','26');