create table ORG_DEPT_PROPERTY_DICT
(
   ID                   VARCHAR(64)          not null,
   PROPERTY_TYPE        VARCHAR(100),
   PROPERTY_NAME        VARCHAR(100),
   PROPERTY_VALUE       VARCHAR(100),
   ORG_ID               VARCHAR(64),
   SORT                 NUMBER,
   REMARKS              VARCHAR2(2000),
   UPDATE_BY            VARCHAR(64),
   CREATE_BY           VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR(2),
   CREATE_DATE          TIMESTAMP,
   constraint PK_ORG_DEPT_PROPERTY_DICT primary key (ID)
);

comment on table ORG_DEPT_PROPERTY_DICT is
'科室属性字典表';

comment on column ORG_DEPT_PROPERTY_DICT.ID is
'主键';

comment on column ORG_DEPT_PROPERTY_DICT.PROPERTY_TYPE is
'属性类型';

comment on column ORG_DEPT_PROPERTY_DICT.PROPERTY_NAME is
'属性名称';

comment on column ORG_DEPT_PROPERTY_DICT.PROPERTY_VALUE is
'属性值';

comment on column ORG_DEPT_PROPERTY_DICT.ORG_ID is
'所属组织';

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('84fd3c9f98db4544bc4e0e2a9de04d50', '门诊住院', '门诊', '0', '1', 1, null, null, null, to_date('29-04-2016 11:09:28', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:09:28', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('4796a08d528b4a178abea795ac38efaf', '临床属性', '机关', '3', '1', null, null, null, null, to_date('29-04-2016 11:07:15', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:07:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('1b5a7160542647dcb4b892f95c03a5ca', '临床属性', '其他', '9', '1', null, null, null, null, to_date('29-04-2016 11:08:03', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:08:03', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('91bc903d986c41b9b5f5e3324cf10368', '内外科', '内科', '0', '1', 2, null, null, null, to_date('29-04-2016 13:37:48', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 13:37:48', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('3d47ed7b919345b2870a65ffc200d253', '临床属性', '护理单元', '2', '1', null, null, null, null, to_date('29-04-2016 11:06:50', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:06:50', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('d1c95114e81647d394c4bbfded5bde42', '临床属性', '经济核算', '8','1', null, null, null, null, to_date('29-04-2016 11:07:38', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:07:38', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('8182425417eb48718a6d05a07afb269a', '门诊住院', '住院', '1', '1', null, null, null, null, to_date('29-04-2016 11:09:40', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:09:40', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('37bcaa8abd374b1c81f8c9aeb2b5d8fd', '内外科', '内科', '0','1', null, null, null, null, to_date('29-04-2016 13:39:33', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 13:39:33', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('0c8b2009d43344ac94d4596d298f73ce', '内外科', '外科', '1', '1', null, null, null, null, to_date('29-04-2016 13:39:45', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 13:39:45', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('bd57591113954ec1b8b6d93aaca33801', '临床属性', '临床', '0', '1', 0, null, null, null, to_date('29-04-2016 11:06:02', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:06:02', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('f7d04d9b5cbb4a94a61eec1dcc8c30ff', '临床属性', '辅诊', '1','1', null, null, null, null, to_date('29-04-2016 11:06:15', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 11:06:15', 'dd-mm-yyyy hh24:mi:ss'));

insert into ORG_DEPT_PROPERTY_DICT (ID, PROPERTY_TYPE, PROPERTY_NAME, PROPERTY_VALUE, ORG_ID, SORT, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('cfaa9b05fb2242fe909bca4fd8150ac5', '内外科', '内科', '0', '1', 2, null, null, null, to_date('29-04-2016 13:37:47', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('29-04-2016 13:37:47', 'dd-mm-yyyy hh24:mi:ss'));
