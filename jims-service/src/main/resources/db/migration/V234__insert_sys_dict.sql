/*==============================================================*/
/* TABLE: sys_dict    ׼护理等级                              */
/* CREATE_BY :  CTQ                                             */
/*=============================================================*/
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '特级护理', '0', 'NURSING_CLASS_DICT', '护理等级',1,'TJHL');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '一级护理', '1', 'NURSING_CLASS_DICT', '护理等级',2,'YJHL');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '二级护理', '2', 'NURSING_CLASS_DICT', '护理等级',3,'EJHL');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '三级护理', '3', 'NURSING_CLASS_DICT', '护理等级',4,'SJHL');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '病重护理', '4', 'NURSING_CLASS_DICT', '护理等级',5,'BZHL');
    insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '病危护理', '5', 'NURSING_CLASS_DICT', '护理等级',6,'BWHL');
    insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '整体护理', '6', 'NURSING_CLASS_DICT', '护理等级',7,'ZTHL');
