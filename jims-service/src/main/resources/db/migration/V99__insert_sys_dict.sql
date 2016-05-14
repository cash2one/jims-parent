/*==============================================================*/
/* Table: menu_dict    字典表插入数据                             */
/* CREATE_DATE: 2016-05-12                                      */
/* CREATE_BY :  冯宇光                                              */
/*==============================================================*/
-- insert into

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE, ROWID)
values ('4afe4f27a181403998ca93aeddc4df83', '药库', '0', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 0, null, '13-5月 -16 11.44.25.1240000 上午', null, '13-5月 -16 11.44.25.1240000 上午', null, '0', 'YK', 'AAAX/8AAEAAAAxWAAS');

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE, ROWID)
values ('2433a1c90d26436eabe15f033ae1f0f3', '门诊药局', '1', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 1, null, '13-5月 -16 11.44.25.2010000 上午', null, '13-5月 -16 11.44.25.2010000 上午', null, '0', 'MZYJ', 'AAAX/8AAEAAAAxWAAT');

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE, ROWID)
values ('803fc4b087c04f5099ebb6cb5cd90073', '住院药局', '2', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 2, null, '13-5月 -16 11.44.25.2600000 上午', null, '13-5月 -16 11.44.25.2600000 上午', null, '0', 'ZYYJ', 'AAAX/8AAEAAAAxWAAU');

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE, ROWID)
values ('e0da71d3797843ceb45cab57d7f28264', '其他药局', '3', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 3, null, '13-5月 -16 11.44.25.2930000 上午', null, null, null, '0', 'QTYJ', 'AAAX/8AAEAAAAxWAAV');
