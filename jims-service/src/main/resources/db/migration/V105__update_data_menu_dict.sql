/*==============================================================*/
/* Table: MENU_DICT    菜单字典                                 */
/* CREATE_DATE: 2016-05-11                                      */
/* CREATE_BY :  txb                                              */
/*==============================================================*/
--修改菜单路径数据
update MENU_DICT set href = '/modules/exam/examRptPattern.html' where id = '83D14E8E38E04BE0B92AEA1021D0BCED';
update MENU_DICT set href = '/modules/exam/examClassDict.html' where id = '6511D9F989EE4CBD99BFF390AAD92A05';
--增加药品目录
insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL) values ('53BC035BC5AE4FC8B407A9AC37298B07', '药品目录', '/modules/phstock/drug-catalog-manager.html','1', 1, '1', null, null, null, '12-5月 -16 01.45.42.300000 下午', '0', '12-5月 -16 01.45.42.300000 下午', '065969698a724445b2c03085aad8dfcd', '1');
