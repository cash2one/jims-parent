/**
 * 测试数据
 * @author lgx
 */
 -- 诊疗项目维护菜单添加
 INSERT INTO menu_dict( id, menu_name, href, icon, sort, target, pid, menu_level, remarks, update_by, create_by, update_date, del_flag, create_date ) VALUES (sys_guid(), '诊疗项目维护', '/modules/clinic/clinic-item.html', '1', 1, '1', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1', null, null, null, sysdate, '0', sysdate );
 -- 采购计划生产菜单添加
 INSERT INTO menu_dict( id, menu_name, href, icon, sort, target, pid, menu_level, remarks, update_by, create_by, update_date, del_flag, create_date ) VALUES (sys_guid(), '采购计划生成', '/modules/phstock/drug-buy-plan-create.html', '1', 1, '1', '065969698a724445b2c03085aad8dfcd', '1', null, null, null, sysdate, '0', sysdate );
 -- 采购计划调整菜单添加
 INSERT INTO menu_dict( id, menu_name, href, icon, sort, target, pid, menu_level, remarks, update_by, create_by, update_date, del_flag, create_date ) VALUES (sys_guid(), '采购计划调整', '/modules/phstock/drug-buy-plan-update.html', '1', 1, '1', '065969698a724445b2c03085aad8dfcd', '1', null, null, null, sysdate, '0', sysdate );
 -- 采购计划审核菜单添加
 INSERT INTO menu_dict( id, menu_name, href, icon, sort, target, pid, menu_level, remarks, update_by, create_by, update_date, del_flag, create_date ) VALUES (sys_guid(), '采购计划审核', '/modules/phstock/drug-buy-plan-audit.html', '1', 1, '1', '065969698a724445b2c03085aad8dfcd', '1', null, null, null, sysdate, '0', sysdate );
--项目类别
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('10AFF59F7A184E3782C757E31FCB8645','西药','A','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','1',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('E11F27EF26BD4ECDA9A1DBEF370C9F3B','中药','B','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','2',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('ED2D393A51D84B1F80B83912D16418C5','检验','C','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','3',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('4214D194EEF54312886441536B289269','检查','D','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','4',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('27F396DF3D804BB0B9ADF95570C2898F','治疗','E','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','5',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('0BF2161DEA3E4169A9660C5115B1C067','手术','F','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','6',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('9971A73902B84D0E957519C3442F48FA','麻醉','G','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','7',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('4226B7B9D01C4D4AB91F21174A40B322','护理','H','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','8',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('A3B14E00B4714299B75BE9A4FDA235EF','膳食','I','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','9',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('61BD9A78562D42CE814FB14B21E50004','床位','J','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','10',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('6E094F773AAF4C489452771BB61CD243','其他','Z','CLINIC_ITEM_CLASS_DICT','诊疗项目分类字典','11',0);

--计费规则
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('62A761458A2A41349650A40F3F2DEA1A','按次','按次','PRICE_RULES_DICT','计费规则字典','1',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('7D6AC7717D44412AA02BFBC0E5C0C370','按日','按日','PRICE_RULES_DICT','计费规则字典','2',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('220AA85DDB224A53BC1CB42E4F95249E','只记一次','只记一次','PRICE_RULES_DICT','计费规则字典','3',0);
insert into sys_dict(id,label,value,type,description,sort,del_flag) values ('E5D4C241710C4360B9662BCE2D3EF35E','不计价','不计价','PRICE_RULES_DICT','计费规则字典','4',0);

--频次
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('5/周',5,1,'周','59F9493CDCE0484AAC627B709F93200A','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('一日一次',1,1,'日','86462963A2B7458A80010E1E975696C7','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('一日二次',2,1,'日','978D4D8D3ACE4221B3368CD5186E1351','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('一日三次',3,1,'日','E80151A017BF41EEB951E23FBDFA0A1B','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('一日四次',4,1,'日','63E33C8DF3C54B13B98D55F8515376CF','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('一日六次',6,1,'日','058D4822C3EE485393A936FFFFC5A03B','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('必要时',0,0,'','EEBA641BD4344CBCBFD2DD5FF64AE737','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('术晨',1,1,'日','C8815839941E4F7B8FF44A3C8A07726C','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('术前',1,1,'日','A9A5F7C437714BF387654176730BF832','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('术前30''',1,1,'日','7F84DE71951341ABB05799CBB1677463','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/周',1,1,'周','CCA9A71C04CF4799B6C19F256B2E51BC','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('2/周',2,1,'周','0AFBD8C21D7F45BEB86A3CFA3CFF5632','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('3/周',3,1,'周','97AF0153F2084A469C4F2970BA0AD745','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/隔日',1,2,'日','3D50798C7F324CBFB2A2ABF8CECFEB07','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/早晨',1,1,'日','7331D9674AB94883A8A1C0A093EC3628','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/中午',1,1,'日','78CF52BEDA204B8EAE75C9E339FE3D36','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/下午',1,1,'日','F14EBAB8E80D4C4FA89E4B24EA3E4B65','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/睡前',1,1,'日','8EBD6A21FB9F4B8EA17EA7C9B1FFE19C','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/早餐前30''',1,1,'日','F127901C192A4F91A62810CC96E02443','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/午餐前30''',1,1,'日','2FEA53CD86704816A37288AF7714F8DB','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/晚餐前30''',1,1,'日','1E02FB70D0EA4B7DB05B60FDD1A146D3','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/早餐前15''',1,1,'日','4515ED6FA0034307BF0C35647FEC1749','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/午餐前15''',1,1,'日','6CCE417490FE4C1894B87D8596529E17','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('1/晚餐前15''',1,1,'日','29B219D328DD4184A2F94E6DDA47AB6D','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('餐后2H',3,1,'日','27964D352D6C40148F11E7F7FE32A4B5','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('餐后30''',3,1,'日','F25E02B8C2514312A6C9320782DD152B','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('一日五次',5,1,'日','13B9AF69FB3342448E5DF36F82BDC9AE','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('立即',1,1,'日','E67ADE0FEA5F47679909324798FCF2FD','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('Q12H',1,12,'小时','B5C8C5BF00784D95A2277F40EF45A354','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('Q2H',1,2,'小时','5F210625CBB24DA68906E9B42EDC24C6','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('Q4H',1,4,'小时','55F7A860C31D43DF9F5DBBD139F9F617','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('Q6H',1,6,'小时','7A148DF6D12940C987CA3651F9EA75E0','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('Q8H',1,8,'小时','B91405D381634A23A713C8401237AF21','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('QH',1,1,'小时','2302186E18DB4B2FBBA37015E13ADC3D','0','频次');
insert into PERFORM_FREQ_DICT(freq_desc,Freq_Counter,Freq_Interval,freq_interval_units,id,del_flag,remarks) values('频点',0,0,'','82EAA6DB01E54650965FF95EC69D8229','0','频次');

--检验类别
insert into lab_item_class_dict(id,class_code,class_name,remarks,del_flag) values('ECBD9795A05946FC9047E4A015414FB2','10','病理','类别','0');
insert into lab_item_class_dict(id,class_code,class_name,remarks,del_flag) values('3BE3693F17F24F1BBF618BA00042069E','1','输血八项','类别','0');
insert into lab_item_class_dict(id,class_code,class_name,remarks,del_flag) values('F95F29F8B2D747779EF1E8FE73402D9E','2','生化','类别','0');
insert into lab_item_class_dict(id,class_code,class_name,remarks,del_flag) values('2A920CC75DC2470CB2ACB20EA802B83C','3','细菌','类别','0');
insert into lab_item_class_dict(id,class_code,class_name,remarks,del_flag) values('840276631E4848FD87B56147B0493D05','4','乙肝五项','类别','0');
insert into lab_item_class_dict(id,class_code,class_name,remarks,del_flag) values('DE46611F0B3E4A9694F63879D2258DDA','5','常规','类别','0');
insert into lab_item_class_dict(id,class_code,class_name,remarks,del_flag) values('67A76A0D82C848B98E07320A867129EA','6','特殊血型','类别','0');
insert into lab_item_class_dict(id,class_code,class_name,remarks,del_flag) values('7FB6D6CC864F4B3688079E1F0EDB1FF2','7','免疫','类别','0');
insert into lab_item_class_dict(id,class_code,class_name,remarks,del_flag) values('D4E936378987468A90559620F9C7F249','9','常规','类别','0');
