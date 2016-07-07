/*==============================================================*/
/* Table: menu_dict    增加菜单按钮                             */
/* CREATE_DATE: 2016-06-22                                     */
/* CREATE_BY :  魏申                                             */
/*==============================================================*/
-- insert into

DELETE  FROM  menu_dict;
insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('D82D1E51D3F8490BB3CD35ED696B3844', '采购计划', null, null, null, null, null, null, null, '23-6月 -16 11.46.40.065000 上午', '0', '23-6月 -16 11.46.40.065000 上午', '065969698a724445b2c03085aad8dfcd', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('A917A81F718E485EA716BB9AFB82D7C0', '出入库管理', null, null, 2, null, null, null, null, '23-6月 -16 11.49.40.907000 上午', '0', '23-6月 -16 11.40.54.594000 上午', '065969698a724445b2c03085aad8dfcd', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('D02211C9C95C4BB499AF5F21DEA15D49', '调价管理', null, null, null, null, null, null, null, '23-6月 -16 11.48.58.663000 上午', '0', '23-6月 -16 11.48.58.663000 上午', '065969698a724445b2c03085aad8dfcd', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('81A6B79F536F4EB18C957D09783BECE0', '住院管理', '/modules/clinic/centerRegionHospital.html', '1', 2, '1', null, null, null, '17-5月 -16 02.02.44.213000 下午', '0', '17-5月 -16 02.02.06.090000 下午', 'c41e3b43214a404ba50115c7d545a62d', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('c41e3b43214a404ba50115c7d545a62d', '医师工作', '/modules/sys/menuDict.html', '1', 1, '2', null, null, null, '17-5月 -16 02.00.56.202000 下午', '0', '28-4月 -16 09.30.34.000000 上午', null, '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('96A23404AF23484AA57827EAD3854E0F', '门诊管理', '/modules/clinic/centerRegion.html', '1', 1, '2', null, null, null, '17-5月 -16 02.02.56.453000 下午', '0', '17-5月 -16 02.01.47.562000 下午', 'c41e3b43214a404ba50115c7d545a62d', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('35E111DB41F9420B9B19B200A41488CB', '菜单管理', '/modules/sys/menuDict.html', '1', 1, '1', null, null, null, '28-4月 -16 09.37.43.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('A1F9DA40723D498CBD2FA81F59D99A30', '消耗品管理', '/modules/sys/menuDict.html', '1', 6, '1', null, null, null, '28-4月 -16 09.30.34.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', null, '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('9396B1CBF46D4902B850331A5BF9ABED', '财务管理', '/modules/sys/menuDict.html', '1', 5, '2', null, null, null, '28-4月 -16 09.30.34.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', null, '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('352F4FCC7FAD4895BC14AC833DDC564D', '固定资产管理', '/modules/sys/menuDict.html', '1', 7, '1', null, null, null, '28-4月 -16 09.30.34.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', null, '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('9ED7DB110B4F41F7AED1340F9B26CF1C', '运维管理', '/modules/sys/menuDict.html', '1', 8, '2', null, null, null, '28-4月 -16 09.30.34.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', null, '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('4DF3F8BACB5345BC93B62FE8D2BE2695', '医技管理', '/modules/sys/menuDict.html', '1', 4, '1', null, null, null, '28-4月 -16 09.30.34.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', null, '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('55CB0105F69B490DA48F7D1F1029370A', '系统管理', null, '1', 9, '2', null, null, null, '28-4月 -16 09.30.34.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', null, '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('3f9c90d23d52440eb465c9924e38ac77', '护理工作', null, '1', 2, '2', null, null, null, '28-4月 -16 09.30.34.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', null, '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('b194262cc2a049828e28b2685b8be350', '住院管理', '/modules/nurse/nurseIndex.html', '1', 2, '1', null, null, null, '23-6月 -16 11.43.01.663000 上午', '0', '28-4月 -16 09.30.34.000000 上午', '3f9c90d23d52440eb465c9924e38ac77', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('79cb0ea6ab764b81a481add71053df02', '门诊管理', '/modules/sys/menuDict.html', '1', 1, '1', null, null, null, '23-6月 -16 11.43.01.663000 上午', '0', '28-4月 -16 09.30.34.000000 上午', '3f9c90d23d52440eb465c9924e38ac77', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('065969698a724445b2c03085aad8dfcd', '药品管理', null, '1', 3, '2', null, null, null, '28-4月 -16 09.30.34.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', null, '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('FB33AE1598FA4280B659CD8CB1EB5413', '挂号管理', null, '1', 0, '2', null, null, null, '13-5月 -16 11.20.29.524000 上午', '0', '13-5月 -16 11.19.20.187000 上午', null, null);

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('83AFF64A55874CB0B15CDFF853E8527C', '门诊挂号', '/modules/register/centerRegisterRegion.html', '1', 1, '1', null, null, null, '13-5月 -16 11.19.50.235000 上午', '0', '13-5月 -16 11.19.50.235000 上午', 'FB33AE1598FA4280B659CD8CB1EB5413', '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('76161BB117E84D5E9D50D35A4621A350', '挂号安排', '/modules/register/centerRegion.html', '1', 2, '1', null, null, null, '13-5月 -16 11.40.15.528000 上午', '0', '13-5月 -16 11.20.07.369000 上午', 'FB33AE1598FA4280B659CD8CB1EB5413', '0');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('1475786619D5435890E8C43B2AA323B9', '门诊收费', '/modules/finance/outpatientCost.html', null, 1, '1', null, null, null, '24-5月 -16 11.20.07.369000 上午', '0', '24-5月 -16 11.20.07.369000 上午', '9396B1CBF46D4902B850331A5BF9ABED', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('83D14E8E38E04BE0B92AEA1021D0BCED', '检查项目维护', '/modules/exam/examRptPattern.html', '1', 2, '1', null, null, null, '03-5月 -16 10.36.59.415000 上午', '0', '03-5月 -16 10.36.49.269000 上午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('6511D9F989EE4CBD99BFF390AAD92A05', '检查类别维护', '/modules/exam/examClassDict.html', '1', 1, '1', null, null, null, '29-4月 -16 08.54.27.847000 上午', '0', '29-4月 -16 08.54.15.916000 上午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('CBA9B6C774464B7A95FC24F50401F620', '组织机构科室管理', '/modules/sys/deptManager.html', null, null, '1', null, null, null, '05-5月 -16 03.39.30.443000 下午', '0', '05-5月 -16 03.39.23.878000 下午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('804696A188E24EB3A5CBCB55E7F051B9', '给药途径管理', '/modules/sys/administration-dict.html', '1', 1, '1', null, null, null, '10-5月 -16 09.49.29.427000 下午', '1', '10-5月 -16 09.49.29.427000 下午', '867C72D258484175AAF02A76A1F501A4', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('3154CD0A37344E4BBF3B49A00B2C77DF', '药品类别管理', '/modules/phstock/drug-class-dict.html', '1', 7, '1', null, null, null, '11-5月 -16 03.11.18.646000 下午', '0', '11-5月 -16 03.11.18.646000 下午', 'CE5C0FB0739243E3A76A245A1D54CADF', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('F2A22CAD7CA646EB8353A3A8ABFBB327', '检验项目分类', '/modules/sys/labItemClassDict.html', '1', 1, '1', null, null, null, '09-5月 -16 03.16.30.252000 下午', '0', '09-5月 -16 03.12.43.114000 下午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('AA68F77546214C038A1F1833365F9123', '组织机构人员管理', '/modules/sys/orgStaff.html', null, 1, '1', null, null, null, '12-5月 -16 09.41.48.960000 上午', '0', '12-5月 -16 09.41.48.960000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('3CC4823AB9994B6F953BF86CBF8ECEC7', '出库记录查询', '/modules/phstock/drug-stock-export-search.html', null, 7, '1', null, null, null, '23-6月 -16 11.54.49.736000 上午', '0', '12-5月 -16 04.17.57.623000 下午', 'A917A81F718E485EA716BB9AFB82D7C0', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('1E797895EC524374B81F8BAD3F1A3DA5', '库存量查询', '/modules/phstock/drug-stock-search.html', null, 0, '1', null, null, null, '23-6月 -16 11.50.49.468000 上午', '0', '12-5月 -16 04.18.41.070000 下午', '4318233E7FD14AD6B60CE7FDC6C16C1B', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('ADF7BF849BC049F9B5C5B46DCB118D48', '入库记录查询', '/modules/phstock/drug-stock-import-search.html', null, 1, '1', null, null, null, '23-6月 -16 11.54.36.439000 上午', '0', '12-5月 -16 04.17.21.271000 下午', 'A917A81F718E485EA716BB9AFB82D7C0', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('637DAE5298AB4962949BFF8A93DEFC41', '出库单据查询', '/modules/phstock/drug-export-document-search.html', null, 7, '1', null, null, null, '23-6月 -16 11.54.31.866000 上午', '0', '13-5月 -16 01.26.56.356000 下午', 'A917A81F718E485EA716BB9AFB82D7C0', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('9F84FE7A94374F1F89F90145DCFA189B', '入库单据查询', '/modules/phstock/drug-import-document-search.html', null, 2, '1', null, null, null, '23-6月 -16 11.54.36.439000 上午', '0', '13-5月 -16 01.26.07.167000 下午', 'A917A81F718E485EA716BB9AFB82D7C0', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('A668E4DC8162410FBC636EB14445295C', '药理信息维护', '/modules/phstock/drug-theory-manager.html', null, 3, '1', null, null, null, '23-6月 -16 02.03.14.692000 下午', '0', '14-5月 -16 09.09.29.024000 上午', 'CE5C0FB0739243E3A76A245A1D54CADF', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('1C8C524251E64378BC0DF5FE2E08918B', '药品限制等级维护', '/modules/phstock/drug-limit-grade-manager.html', null, 4, '1', null, null, null, '23-6月 -16 02.04.14.054000 下午', '0', '14-5月 -16 09.11.26.881000 上午', 'CE5C0FB0739243E3A76A245A1D54CADF', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('3EB073514965406AAF293621A91ADA40', '药品目录查询', '/modules/phstock/drug-name-search.html', null, 1, '1', null, null, null, '23-6月 -16 01.40.36.203000 下午', '0', '14-5月 -16 09.12.58.361000 上午', 'F091DC51565646FDB438810D32685FB2', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('990A664A3B394160AAE0FF6F21AE1626', '入库处理', '/modules/phstock/drug-import.html', null, 0, '1', null, null, null, '23-6月 -16 11.52.53.756000 上午', '0', '14-5月 -16 02.37.16.898000 下午', 'A917A81F718E485EA716BB9AFB82D7C0', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('963A7B56CA144CA3977DE38897CA6192', '批量入库', '/modules/phstock/drug-import-batch.html', null, 3, '1', null, null, null, '23-6月 -16 11.54.31.867000 上午', '0', '14-5月 -16 02.38.06.194000 下午', 'A917A81F718E485EA716BB9AFB82D7C0', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('952F68CF94844B4EABF273EE7E37A2C4', '消耗量定库存量高低限', '/modules/phstock/drug-stock-level-set.html', null, 1, '1', null, null, null, '23-6月 -16 11.51.14.156000 上午', '0', '14-5月 -16 02.39.23.527000 下午', '4318233E7FD14AD6B60CE7FDC6C16C1B', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('8A3097F4A8DE489E9B9509E444ACB735', '字典表维护', '/modules/sys/public-dict.html', null, null, null, null, null, null, '12-5月 -16 01.45.42.300000 下午', '1', '12-5月 -16 01.45.42.300000 下午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('7EA23F56AF7A43D7911649283C83C9BF', '药品库存单位字典维护', '/modules/phstock/drug-storage-dept.html', null, 1, '1', null, null, null, '23-6月 -16 01.44.29.131000 下午', '0', '17-6月 -16 09.49.41.287000 上午', 'CE5C0FB0739243E3A76A245A1D54CADF', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('5364008C1B3A49B18B3262320372DEA0', '身份字典表管理', '/modules/sys/identity-dict.html', null, null, null, null, null, null, '22-6月 -16 03.42.18.018000 下午', '0', '22-6月 -16 03.42.18.018000 下午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('B6570E77F0B749A0B0B37DA515711538', '调价记录维护', '/modules/phstock/drug-price-modify-marge.html', '1', 0, '1', null, null, null, '23-6月 -16 11.49.22.487000 上午', '0', '14-5月 -16 09.08.08.226000 上午', 'D02211C9C95C4BB499AF5F21DEA15D49', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('75A75BA42DE64C4C916C6BB9F6F04668', '药品字典维护管理', '/modules/phstock/drug_class_dict.html', '1', 0, '1', null, null, null, '23-6月 -16 01.43.03.067000 下午', '0', '12-5月 -16 01.57.10.687000 下午', 'CE5C0FB0739243E3A76A245A1D54CADF', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('2DF5B93096BC483D96F61474308D4516', '药品价格维护', '/modules/phstock/drug-price-marger.html', '1', 0, '1', null, null, null, '23-6月 -16 11.59.08.731000 上午', '0', '14-5月 -16 09.04.33.038000 上午', '42AEDF877B73436D958982CB9FFED93B', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('372DDCF727AA40E484D15C7258D93B39', '药品价格查询', '/modules/phstock/drug-price-search.html', '1', 1, '1', null, null, null, '23-6月 -16 11.59.50.408000 上午', '0', '14-5月 -16 09.06.07.596000 上午', '42AEDF877B73436D958982CB9FFED93B', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('51D049B7DE194FD7A6A4788CDBCCDA11', '药品进阶查询', '/modules/phstock/drug-price-porchase.html', '1', 2, '1', null, null, null, '23-6月 -16 02.24.18.156000 下午', '0', '14-5月 -16 09.07.12.921000 上午', 'F091DC51565646FDB438810D32685FB2', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('48A0B15CD6F9402BB7F4353A48559CED', '药品供应维护', '/modules/phstock/drug-supplier-manager.html', '1', 2, '1', null, null, null, '23-6月 -16 02.02.38.641000 下午', '0', '14-5月 -16 03.20.07.383000 下午', 'CE5C0FB0739243E3A76A245A1D54CADF', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('CDD82C03C7484898A5607476AC3AAD4D', '出库申请', '/modules/phstock/drug-apply-export.html', '1', 5, '1', null, null, null, '23-6月 -16 11.54.31.866000 上午', '0', '14-5月 -16 03.18.26.285000 下午', 'A917A81F718E485EA716BB9AFB82D7C0', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('359D00114B93453DBEE156DACF6163FC', '出库处理', '/modules/phstock/drug-export.html', '1', 6, '1', null, null, null, '23-6月 -16 11.54.31.866000 上午', '0', '14-5月 -16 03.19.26.865000 下午', 'A917A81F718E485EA716BB9AFB82D7C0', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('C89161413BE544B585D210542501ECEF', '调价确认', '/modules/phstock/drug-price-confirm.html', '1', 1, '1', null, null, null, '23-6月 -16 11.49.48.321000 上午', '0', '20-5月 -16 09.25.47.024000 上午', 'D02211C9C95C4BB499AF5F21DEA15D49', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('440df1e9ab2b49deb7ad2ae62163d8dc', '诊疗项目维护', '/modules/clinic/clinic-item.html', '1', 1, '1', null, null, null, '23-6月 -16 11.32.47.000000 上午', '0', '23-6月 -16 11.32.47.000000 上午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('75a1acabf3c74d22a13c881d848d0581', '采购计划生成', '/modules/phstock/drug-buy-plan-create.html', '1', 0, '1', null, null, null, '23-6月 -16 11.47.37.434000 上午', '0', '23-6月 -16 11.32.47.000000 上午', 'D82D1E51D3F8490BB3CD35ED696B3844', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('fab5abf284dc46378ef14a14e8aa78e5', '采购计划调整', '/modules/phstock/drug-buy-plan-update.html', '1', 1, '1', null, null, null, '23-6月 -16 11.47.50.484000 上午', '0', '23-6月 -16 11.32.47.000000 上午', 'D82D1E51D3F8490BB3CD35ED696B3844', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('b06124a036644a649a008ddd7ea55660', '采购计划审核', '/modules/phstock/drug-buy-plan-audit.html', '1', 2, '1', null, null, null, '23-6月 -16 11.47.50.484000 上午', '0', '23-6月 -16 11.32.47.000000 上午', 'D82D1E51D3F8490BB3CD35ED696B3844', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('55cd209bdcd94f288e205e2a6786f259', '采购计划执行', '/modules/phstock/drug-buy-plan-execute.html', '1', 3, '1', null, null, null, '23-6月 -16 11.47.23.762000 上午', '0', '23-6月 -16 11.32.47.000000 上午', 'D82D1E51D3F8490BB3CD35ED696B3844', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('27a5892967d9474e980dcea42bb8e808', '采购计划查询', '/modules/phstock/drug-buy-plan-query.html', '1', 4, '1', null, null, null, '23-6月 -16 11.47.29.715000 上午', '0', '23-6月 -16 11.32.47.000000 上午', 'D82D1E51D3F8490BB3CD35ED696B3844', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('53BC035BC5AE4FC8B407A9AC37298B07', '药品目录', '/modules/phstock/drug-catalog-manager.html', '1', 0, '1', null, null, null, '23-6月 -16 01.40.20.758000 下午', '0', '12-5月 -16 01.45.42.300000 下午', 'F091DC51565646FDB438810D32685FB2', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('06509BF1BEA1440FB4299E27F65290C3', '输入法字典维护', '/modules/sys/input-setting.html', null, 2, '1', null, null, null, '20-5月 -16 10.31.27.008000 上午', '0', '20-5月 -16 10.31.00.576000 上午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('26E0CA25706E42129B730337295010AE', '发药管理', '/modules/clinic/outDispensing/outDispenseDept.html', null, null, null, null, null, null, '25-5月 -16 10.58.52.957000 上午', '0', '25-5月 -16 09.53.18.068000 上午', 'c41e3b43214a404ba50115c7d545a62d', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('69D09F5777004B6AA46E72D17CDCCE22', '入院患者登记', '/modules/patient/inHospital.html', '1', 2, '1', null, null, null, '27-5月 -16 09.18.27.454000 上午', '0', '27-5月 -16 09.18.27.454000 上午', '9396B1CBF46D4902B850331A5BF9ABED', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('BF1520FFD2F742999E279136A65B3EBB', '科室组人员维护', '/modules/sys/staff-vs-group.html', '1', 4, '1', null, null, null, '28-5月 -16 10.43.12.858000 上午', '0', '28-5月 -16 10.42.17.221000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('AADA20D179AB4C3595449DC63FCA5F5C', '科室分组维护', '/modules/sys/staff-group.html', '1', 5, '1', null, null, null, '28-5月 -16 10.42.45.651000 上午', '0', '28-5月 -16 10.42.45.651000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('A40B806BB00C45568BEE9CB768BB109E', '挂号结账', '/modules/finance/outpAccounts/regiAccounts.html', '1', 5, '1', null, null, null, '31-5月 -16 03.16.25.212000 下午', '0', '31-5月 -16 03.16.25.212000 下午', '9396B1CBF46D4902B850331A5BF9ABED', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('6C2A108387314F6AB92A12514FA3B76B', '价表类别管理', '/modules/sys/priceDictList.html', '1', 1, '1', null, null, null, '16-6月 -16 11.10.34.663000 上午', '0', '16-6月 -16 11.10.34.663000 上午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('6651d30ef59e4b3589b00f238577f572', '自定义服务', ' /modules/sys/org-self-service.html', '1', 1, '1', null, null, null, '23-6月 -16 11.32.54.000000 上午', '0', '23-6月 -16 11.32.54.000000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('c667b7033b934f01933731ad50bf4e2e', '收费结账', '/modules/finance/outpAccounts/outpChargeCheck.html', null, null, null, null, null, null, '01-6月 -16 10.00.10.466000 上午', '0', '01-6月 -16 10.00.10.466000 上午', '9396B1CBF46D4902B850331A5BF9ABED', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('e072e6a3fe744d02b26047af983f45dd', '住院管理', '/modules/nurse/centerRegion.html', '1', 1, '1', null, null, null, '03-6月 -16 09.16.09.473000 上午', '0', '28-4月 -16 09.30.34.000000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('69357a97e49f4f8397f05d858384b817', '角色维护', '/modules/sys/org-role.html', '1', 1, '1', null, null, null, '03-6月 -16 09.16.09.473000 上午', '0', '28-4月 -16 09.30.34.000000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('a96676df95914212828865d13f26f83a', '权限分配', '/modules/sys/org-service-menu.html', '1', 1, '1', null, null, null, '03-6月 -16 09.16.09.473000 上午', '0', '28-4月 -16 09.30.34.000000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('1a6e1a83209e4f99b3a4387fd13d38d0', '药品盘点', '/modules/phstock/drug-stock-inventory.html', '1', 3, '1', null, null, null, '23-6月 -16 02.24.27.068000 下午', '0', '28-4月 -16 09.30.34.000000 上午', 'F091DC51565646FDB438810D32685FB2', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('90185383E114475A9C4829F1D6B0C800', '药品厂商维护', '/modules/phstock/drug-supplier-catalog.html', null, 5, '1', null, null, null, '23-6月 -16 02.06.34.892000 下午', '0', '16-6月 -16 01.41.02.757000 下午', 'CE5C0FB0739243E3A76A245A1D54CADF', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('7C73CFCFF9534B618EAE7C30651789E2', '合同单位管理', '/modules/sys/unitInContract.html', '1', 1, '1', null, null, null, '22-6月 -16 04.42.41.995000 下午', '0', '22-6月 -16 04.41.40.052000 下午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('4318233E7FD14AD6B60CE7FDC6C16C1B', '库存管理', null, null, null, null, null, null, null, '23-6月 -16 11.50.22.394000 上午', '0', '23-6月 -16 11.50.22.394000 上午', '065969698a724445b2c03085aad8dfcd', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('42AEDF877B73436D958982CB9FFED93B', '药品价格', null, null, null, null, null, null, null, '23-6月 -16 11.58.36.878000 上午', '0', '23-6月 -16 11.58.36.878000 上午', '065969698a724445b2c03085aad8dfcd', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('F091DC51565646FDB438810D32685FB2', '药品信息', null, null, null, null, null, null, null, '23-6月 -16 02.23.54.624000 下午', '0', '23-6月 -16 01.39.55.456000 下午', '065969698a724445b2c03085aad8dfcd', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('CE5C0FB0739243E3A76A245A1D54CADF', '药品信息维护', null, null, null, null, null, null, null, '23-6月 -16 02.03.30.219000 下午', '0', '23-6月 -16 01.41.59.998000 下午', '065969698a724445b2c03085aad8dfcd', '1');
