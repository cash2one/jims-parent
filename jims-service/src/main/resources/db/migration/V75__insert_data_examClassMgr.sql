--检查类别数据插入
insert into EXAM_CLASS_DICT (id, org_id, serial_no, exam_class_code, exam_class_name, input_code, perform_by, print_style, specialties_dept, loacal_id_class, ward_code, http_ip, memo, outp_perform, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('1', '2', null, 'K', '急诊心电', 'JZXD', '110201', null, 0, null, null, null, null, null, null, null, null, null, '0', null);
insert into EXAM_CLASS_DICT (id, org_id, serial_no, exam_class_code, exam_class_name, input_code, perform_by, print_style, specialties_dept, loacal_id_class, ward_code, http_ip, memo, outp_perform, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('2', '1', null, 'H', '血管造影', 'XGZY', '150303', null, 0, null, null, null, null, null, null, null, null, null, '0', null);
insert into EXAM_CLASS_DICT (id, org_id, serial_no, exam_class_code, exam_class_name, input_code, perform_by, print_style, specialties_dept, loacal_id_class, ward_code, http_ip, memo, outp_perform, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('3', '1', null, 'A', 'CT', null, '150301', null, 0, null, null, null, null, null, null, null, null, null, '0', null);
insert into EXAM_CLASS_DICT (id, org_id, serial_no, exam_class_code, exam_class_name, input_code, perform_by, print_style, specialties_dept, loacal_id_class, ward_code, http_ip, memo, outp_perform, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('4', '1', null, 'B', 'X线透视', null, '150302', null, 0, null, null, null, null, null, null, null, null, null, '0', null);
insert into EXAM_CLASS_DICT (id, org_id, serial_no, exam_class_code, exam_class_name, input_code, perform_by, print_style, specialties_dept, loacal_id_class, ward_code, http_ip, memo, outp_perform, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('5', '1', null, 'C', 'X线造影', null, '150302', null, 0, null, null, null, null, null, null, null, null, null, '0', null);
insert into EXAM_CLASS_DICT (id, org_id, serial_no, exam_class_code, exam_class_name, input_code, perform_by, print_style, specialties_dept, loacal_id_class, ward_code, http_ip, memo, outp_perform, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('6', '1', null, 'D', 'DR', null, '150302', null, 0, null, null, null, null, null, null, null, null, null, '0', null);
insert into EXAM_CLASS_DICT (id, org_id, serial_no, exam_class_code, exam_class_name, input_code, perform_by, print_style, specialties_dept, loacal_id_class, ward_code, http_ip, memo, outp_perform, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('7', '1', null, 'E', '彩超', null, '150401', null, 0, null, null, null, null, null, null, null, null, null, '0', null);
insert into EXAM_CLASS_DICT (id, org_id, serial_no, exam_class_code, exam_class_name, input_code, perform_by, print_style, specialties_dept, loacal_id_class, ward_code, http_ip, memo, outp_perform, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('8', '2', null, 'F', '心电', null, '150402', null, 0, null, null, null, null, null, null, null, null, null, '0', null);
insert into EXAM_CLASS_DICT (id, org_id, serial_no, exam_class_code, exam_class_name, input_code, perform_by, print_style, specialties_dept, loacal_id_class, ward_code, http_ip, memo, outp_perform, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('9', '1', null, 'G', '病理', 'BL', '150202', null, 0, null, null, null, null, null, null, null, null, null, '0', null);

--检查子类别数据插入
insert into EXAM_SUBCLASS_DICT (exam_class_name, exam_subclass_name, input_code, id, org_id, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('急诊心电', '心电', 'XD', '1', '1', null, null, null, null, '0', null);
insert into EXAM_SUBCLASS_DICT (exam_class_name, exam_subclass_name, input_code, id, org_id, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('CT', '头颅CT', 'TLCTPS', '2', '1', null, null, null, null, '0', null);
insert into EXAM_SUBCLASS_DICT (exam_class_name, exam_subclass_name, input_code, id, org_id, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('CT', '脊柱CT', 'JZCTPS', '3', '1', null, null, null, null, '0', null);
insert into EXAM_SUBCLASS_DICT (exam_class_name, exam_subclass_name, input_code, id, org_id, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('CT', '胸部CT', 'XBCTPS', '4', '1', null, null, null, null, '0', null);
insert into EXAM_SUBCLASS_DICT (exam_class_name, exam_subclass_name, input_code, id, org_id, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('CT', '腹部CT', 'FBCTPS', '5', '1', null, null, null, null, '0', null);
insert into EXAM_SUBCLASS_DICT (exam_class_name, exam_subclass_name, input_code, id, org_id, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('CT', '盆腔CT', 'PQCTPS', '6', '1', null, null, null, null, '0', null);
insert into EXAM_SUBCLASS_DICT (exam_class_name, exam_subclass_name, input_code, id, org_id, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('彩超', '图文报告彩超', null, '16', '1', null, null, null, null, '0', null);
insert into EXAM_SUBCLASS_DICT (exam_class_name, exam_subclass_name, input_code, id, org_id, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('彩超', '心脏彩色多普', null, '17', '1', null, null, null, null, '0', null);
insert into EXAM_SUBCLASS_DICT (exam_class_name, exam_subclass_name, input_code, id, org_id, remarks, update_by, create_by, update_date, del_flag, create_date)
values ('彩超', '胸腹水B超穿', null, '18', '1', null, null, null, null, '0', null);

