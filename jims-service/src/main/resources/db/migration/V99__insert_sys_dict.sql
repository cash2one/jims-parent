/*==============================================================*/
/* Table: menu_dict    字典表插入数据                             */
/* CREATE_DATE: 2016-05-12                                      */
/* CREATE_BY :  冯宇光                                              */
/*==============================================================*/
-- insert into

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, remarks)values ('4afe4f27a181403998ca93aeddc4df83', '药库', '0', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 0, 1);

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, remarks)values ('2433a1c90d26436eabe15f033ae1f0f3', '门诊药局', '1', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 1, 2);

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, remarks)values ('803fc4b087c04f5099ebb6cb5cd90073', '住院药局', '2', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 2, 2);

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, remarks)values ('e0da71d3797843ceb45cab57d7f28264', '其他药局', '3', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 3, 2);


INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '22464f0c0ded4b0c83ae35a3630d770c', '1', '上级', 'DRUG_TRANSFER_DIR', '药品入出库方向', 0, null, to_date('2016-07-14 14:29:26','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-07-14 14:29:26','yyyy-MM-dd HH24:mi:ss'), '', '0', 'sj' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '99833dc1d75c41b28fbf4cc462f05de9', '2', '平级', 'DRUG_TRANSFER_DIR', '药品入出库方向', 1, null, to_date('2016-07-14 14:31:05','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-07-14 14:31:05','yyyy-MM-dd HH24:mi:ss'), '', '0', 'pj' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( 'fdaa160fb77847fb97629b5992713cfc', '3', '下级', 'DRUG_TRANSFER_DIR', '药品入出库方向', 2, null, to_date('2016-07-14 14:31:05','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-07-14 14:31:05','yyyy-MM-dd HH24:mi:ss'), '', '0', 'xj' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '7cb930e4cc4047cabc34faa357ff4fc1', '4', '供应商', 'DRUG_TRANSFER_DIR', '药品入出库方向', 3, null, to_date('2016-07-14 14:31:31','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-07-14 14:31:31','yyyy-MM-dd HH24:mi:ss'), '', '0', 'gys' ) ;
