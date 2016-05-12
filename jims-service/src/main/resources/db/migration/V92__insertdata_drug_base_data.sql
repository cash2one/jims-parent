insert into DRUG_EXPORT_CLASS_DICT (ID, EXPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, ACCOUNT_FLAG, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('16', '纠错出库', '调整入库', '药局', 1, null, null, null, to_date('12-05-2016 09:47:44', 'dd-mm-yyyy hh24:mi:ss'), '0', null);
insert into DRUG_EXPORT_CLASS_DICT (ID, EXPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, ACCOUNT_FLAG, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('17', '发放出库', '发放出库', '药库', 0, null, null, null, null, '0', null);
insert into DRUG_EXPORT_CLASS_DICT (ID, EXPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, ACCOUNT_FLAG, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('18', '退药出库', '申请出库', '全部', 1, null, null, null, null, '0', null);
insert into DRUG_EXPORT_CLASS_DICT (ID, EXPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, ACCOUNT_FLAG, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('19', '制剂出库', '发放出库', '全部', 1, null, null, null, null, '0', null);
insert into DRUG_EXPORT_CLASS_DICT (ID, EXPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, ACCOUNT_FLAG, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('20', '申请出库', '申请出库', '全部', 1, null, null, null, null, '0', null);
insert into DRUG_EXPORT_CLASS_DICT (ID, EXPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, ACCOUNT_FLAG, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('21', '盘点出库', '发放出库', '全部', 1, null, null, null, null, '0', null);
insert into DRUG_EXPORT_CLASS_DICT (ID, EXPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, ACCOUNT_FLAG, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('22', '调拨出库', '发放出库', '全部', null, null, null, null, null, '0', null);
insert into DRUG_EXPORT_CLASS_DICT (ID, EXPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, ACCOUNT_FLAG, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('9e58ba5c8eb5433b895e2943c8cda064', '没有入库', '盘点入库', '药库', null, null, null, null, to_date('12-05-2016 09:48:20', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('12-05-2016 09:48:20', 'dd-mm-yyyy hh24:mi:ss'));
commit;

insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('bb65271acf34478aae7e51d16e9e81b1', '2', '赠送入库', '药局', null, null, null, to_date('12-05-2016 09:02:27', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('12-05-2016 09:02:27', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('f14e4e1cbbe24b90ac070bac3921653d', '2', '赠送入库', '药局', null, null, null, to_date('12-05-2016 08:53:42', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('12-05-2016 08:53:42', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('f93c4d51bfba42948c3a1c36b60c7170', '1', '赠送入库', '药库', null, null, null, to_date('12-05-2016 08:53:42', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('12-05-2016 08:53:42', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('1', '正常入库', '发放入库', '药局', null, null, null, null, '1', null, null);
insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('2', '建帐入库', '生产入库', '药库', null, null, null, to_date('11-05-2016 16:11:05', 'dd-mm-yyyy hh24:mi:ss'), '0', null, null);
insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('3', '制剂入库', '生产入库', '药库', null, null, null, to_date('11-05-2016 15:50:25', 'dd-mm-yyyy hh24:mi:ss'), '0', null, null);
insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('4', '纠错入库', '生产入库', '全部', null, null, null, null, '0', null, null);
insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('5', '退药入库', '退药入库', '药库', null, null, null, null, '0', null, null);
insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('6', '盘点入库', '生产入库', '全部', null, null, null, null, '0', null, null);
insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('7', '采购入库', '采购入库', '药库', null, null, null, null, '0', null, null);
insert into DRUG_IMPORT_CLASS_DICT (ID, IMPORT_CLASS, STATISTIC_CLASS, STORAGE_TYPE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ACCOUNT_FLAG)
values ('8', '调拨入库', '调整入库', '全部', null, null, null, null, '0', null, null);
commit;

insert into DRUG_DISP_PROPERTY_DICT (ID, DISPENSING_PROPERTY, DRUG_ADMINISTRATIONS, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('23', '其他类医嘱', null, null, null, null, null, '0', null);
insert into DRUG_DISP_PROPERTY_DICT (ID, DISPENSING_PROPERTY, DRUG_ADMINISTRATIONS, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('24', '注射类医嘱', '静脉注射', null, null, null, null, '0', null);
insert into DRUG_DISP_PROPERTY_DICT (ID, DISPENSING_PROPERTY, DRUG_ADMINISTRATIONS, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('25', '服药类医嘱', '口服;冲服', null, null, null, null, '0', null);
insert into DRUG_DISP_PROPERTY_DICT (ID, DISPENSING_PROPERTY, DRUG_ADMINISTRATIONS, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('26', '外用类医嘱', '皮贴', null, null, null, null, '0', null);
insert into DRUG_DISP_PROPERTY_DICT (ID, DISPENSING_PROPERTY, DRUG_ADMINISTRATIONS, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('27', '输液类医嘱', '静脉输液;续静滴', null, null, null, null, '0', null);
insert into DRUG_DISP_PROPERTY_DICT (ID, DISPENSING_PROPERTY, DRUG_ADMINISTRATIONS, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('2ae64a3404314747bab3605eb67f1c29', '外用类医数', '口服;重复', null, null, null, to_date('12-05-2016 11:05:28', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('12-05-2016 11:05:16', 'dd-mm-yyyy hh24:mi:ss'));
insert into DRUG_DISP_PROPERTY_DICT (ID, DISPENSING_PROPERTY, DRUG_ADMINISTRATIONS, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('908a3820e8134212a5f4c338c2b64b6f', '输液类医嘱', '口服;冲服', null, null, null, to_date('12-05-2016 11:06:07', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('12-05-2016 11:06:07', 'dd-mm-yyyy hh24:mi:ss'));
commit;


insert into DRUG_CODING_RULE (ID, CODE_LEVEL, LEVEL_WIDTH, CLASS_NAME , REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('28', 1, 2, '药品类别', null, null, null, null, '0', null);
insert into DRUG_CODING_RULE (ID, CODE_LEVEL, LEVEL_WIDTH, CLASS_NAME, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('29', 2, 3, '药品亚类', null, null, null, null, '0', null);
insert into DRUG_CODING_RULE (ID, CODE_LEVEL, LEVEL_WIDTH, CLASS_NAME, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('30', 3, 3, '药品序号', null, null, null, null, '0', null);
insert into DRUG_CODING_RULE (ID, CODE_LEVEL, LEVEL_WIDTH, CLASS_NAME, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('31', 4, 2, '药品剂型', null, null, null, null, '0', null);
insert into DRUG_CODING_RULE (ID, CODE_LEVEL, LEVEL_WIDTH, CLASS_NAME, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('32', 5, 2, '商品名称', null, null, null, null, '0', null);
insert into DRUG_CODING_RULE (ID, CODE_LEVEL, LEVEL_WIDTH, CLASS_NAME, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('05d22fa5979e4aabb4cab00f439b97df', 6, 2, '药品鸭梨', null, null, null, to_date('12-05-2016 10:34:40', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('12-05-2016 10:34:40', 'dd-mm-yyyy hh24:mi:ss'));
insert into DRUG_CODING_RULE (ID, CODE_LEVEL, LEVEL_WIDTH, CLASS_NAME, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('eb2fab6f075f4efeb8062ba7b8c4a71b', 6, 2, '药品鸭梨', null, null, null, to_date('12-05-2016 10:35:26', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('12-05-2016 10:35:26', 'dd-mm-yyyy hh24:mi:ss'));
insert into DRUG_CODING_RULE (ID, CODE_LEVEL, LEVEL_WIDTH, CLASS_NAME, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('f8814ef89da84c749e8cf2d7044d5059', 7, 2, '是否', null, null, null, to_date('12-05-2016 10:40:18', 'dd-mm-yyyy hh24:mi:ss'), '1', to_date('12-05-2016 10:40:18', 'dd-mm-yyyy hh24:mi:ss'));
commit;