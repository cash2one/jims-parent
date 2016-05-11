--修改运维管理打开方式
update MENU_DICT a set a.target = '2' where a.id = '9ED7DB110B4F41F7AED1340F9B26CF1C';
--增加运维管理数据
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('83D14E8E38E04BE0B92AEA1021D0BCED', '检查项目维护', '/modules/clinic/exam/examRptPattern.html', '1', 2, '1', null, null, null, to_timestamp('03-05-2016 10:36:59.415000', 'dd-mm-yyyy hh24:mi:ss.ff'), '0', to_timestamp('03-05-2016 10:36:49.269000', 'dd-mm-yyyy hh24:mi:ss.ff'), '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('6511D9F989EE4CBD99BFF390AAD92A05', '检查类别维护', '/modules/clinic/exam/examClassDict.html', '1', 1, '1', null, null, null, to_timestamp('29-04-2016 08:54:27.847000', 'dd-mm-yyyy hh24:mi:ss.ff'), '0', to_timestamp('29-04-2016 08:54:15.916000', 'dd-mm-yyyy hh24:mi:ss.ff'), '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');
