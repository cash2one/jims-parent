/*create or replace view v_staff_group_dict as
  select *
    from dept_dict*/


insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('BF1520FFD2F742999E279136A65B3EBB', '科室组人员维护', '/modules/sys/staff-vs-group.html', '1', 4, '1', null, null, null, '28-5月 -16 10.43.12.858000 上午', '0', '28-5月 -16 10.42.17.221000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('AADA20D179AB4C3595449DC63FCA5F5C', '科室分组维护', '/modules/sys/staff-group.html', '1', 5, '1', null, null, null, '28-5月 -16 10.42.45.651000 上午', '0', '28-5月 -16 10.42.45.651000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

 create or replace view v_staff_group_dict as
  select *
    from dept_dict;


insert into input_setting_master (DICT_NAME, DICT_TYPE, ORG_ID, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ID)
values ('科室分组', 'v_staff_group_dict', '1', null, null, null, null, '0', null, '115cc9877c83469db5f5a5288b7aee93');

insert into input_setting_detail (DATA_COL, DATA_TITLE, FLAG_SHOW, SHOW_SORT, FLAG_ISNAME, RESULT_SORT, SHOW_WIDTH, INPUT_SETTING_MASTER_ID, INPUT_CODE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ID)
values ('INPUT_CODE', '拼音码', '1', 0, 'Y', '0', 10, '115cc9877c83469db5f5a5288b7aee93', '00', null, null, null, null, '0', null, 'c24f4219913145e68c9b75b134a899c2');

insert into input_setting_detail (DATA_COL, DATA_TITLE, FLAG_SHOW, SHOW_SORT, FLAG_ISNAME, RESULT_SORT, SHOW_WIDTH, INPUT_SETTING_MASTER_ID, INPUT_CODE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ID)
values ('DEPT_CODE', '科室代码', '1', 1, 'Y', '0', 10, '115cc9877c83469db5f5a5288b7aee93', '00', null, null, null, null, '0', null, 'c149acad072e41ad9bf8a3ea8dea34d0');

insert into input_setting_detail (DATA_COL, DATA_TITLE, FLAG_SHOW, SHOW_SORT, FLAG_ISNAME, RESULT_SORT, SHOW_WIDTH, INPUT_SETTING_MASTER_ID, INPUT_CODE, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, ID)
values ('DEPT_NAME', '科室名称', '1', 2, 'Y', '0', 10, '115cc9877c83469db5f5a5288b7aee93', '00', null, null, null, null, '0', null, '1dc22f92df0b463a9ecaf4b70a7bcd1c');
