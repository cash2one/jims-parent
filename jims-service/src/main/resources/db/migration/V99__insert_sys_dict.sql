/*==============================================================*/
/* Table: menu_dict    字典表插入数据                             */
/* CREATE_DATE: 2016-05-12                                      */
/* CREATE_BY :  冯宇光                                              */
/*==============================================================*/
-- insert into

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT)values ('4afe4f27a181403998ca93aeddc4df83', '药库', '0', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 0);

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT)values ('2433a1c90d26436eabe15f033ae1f0f3', '门诊药局', '1', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 1);

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT)values ('803fc4b087c04f5099ebb6cb5cd90073', '住院药局', '2', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 2);

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT)values ('e0da71d3797843ceb45cab57d7f28264', '其他药局', '3', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 3);
