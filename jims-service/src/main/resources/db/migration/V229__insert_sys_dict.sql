/*==============================================================*/
/* TABLE: sys_dict    ׼出院方式                              */
/* CREATE_BY :  pq                                             */
/*=============================================================*/
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '医嘱离院', '1', 'DISCHARGE_DISPOSITION_DICT', '出院方式字典',1,'ZC');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '医嘱转院', '2', 'DISCHARGE_DISPOSITION_DICT', '出院方式字典',2,'ZY');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '转社区卫生机构', '3', 'DISCHARGE_DISPOSITION_DICT', '出院方式字典',3,'SW');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '非医嘱离院', '4', 'DISCHARGE_DISPOSITION_DICT', '出院方式字典',4,'QT');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '死亡', '5', 'DISCHARGE_DISPOSITION_DICT', '出院方式字典',5,'');
    insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '其他', '9', 'DISCHARGE_DISPOSITION_DICT', '出院方式字典',9,'');
