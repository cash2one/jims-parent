-- Create table
--    author:yangruidong
/*==============================================================*/
/* Table:exp_dict                                     */
/*==============================================================*/

create table EXP_DICT
(
  exp_code               VARCHAR2(20) not null,
  exp_name               VARCHAR2(100),
  exp_spec               VARCHAR2(20) not null,
  units                  VARCHAR2(8),
  exp_form               VARCHAR2(30),
  toxi_property          VARCHAR2(10),
  dose_per_unit          NUMBER(8,3),
  dose_units             VARCHAR2(8),
  input_code             VARCHAR2(8),
  exp_indicator          NUMBER(1) not null,
  storage_code           VARCHAR2(10),
  single_group_indicator VARCHAR2(1),
  org_id                 VARCHAR2(64)
);


insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210300222', '5米线板', '1','1','1', '耗材', '03', 1, '个', 'MXB', 2, '100303', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210300345', '路由器', '1','1','2', '耗材', null, 1, '个', 'LYQ', 2, '100303', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210200003', '鼠标垫（彩色）', '/','1', '1', '耗材', '01', 1, '个', 'SBDDCSD', 2, '100305', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0220080001', '84消毒液', '480', '1','2', '低值易耗', '01', 1, '瓶', 'XDY', 2, '100302', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210900209', '一次性PVC检查手套', '1','1', '1', '耗材', '01', 1, '付', 'YCXPVCJC', 2, '100302', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210900210', '脑打诊锤', '1','1', '1', '耗材', '01', 1, '把', 'NDZC', 2, '100302', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210900211', '输血器', '1','1', '1', '耗材', '01', 1, '套', 'SXQ', 2, '100302', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210900212', '一次性使用鼻痒管', '1','1', '1', '耗材', '01', 1, '个', 'YCXSYBYG', 2, '100302', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210900213', '爱尔碘', '60ml', '1','1', '耗材', '01', 1, '瓶', 'AED', 2, '100302', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210900214', '齿科砂轮', '1','1', '1', '耗材', '01', 1, '片', 'CKSL', 2, '100302', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210900215', '10ml注射器', '1','1','1', '耗材', '01', 1, '支', 'MLZSQ', 2, '100302', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210900216', '5ml注射器', '1','1', '1', '耗材', '01', 1, '支', 'MLZSQ', 2, '100302', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210900217', '1ml注射器', '1','1','1', '耗材', '01', 1, '支', 'MLZSQ', 2, '100302', 'S');
insert into EXP_DICT (exp_code, exp_name, exp_spec,org_id, units, exp_form, toxi_property, dose_per_unit, dose_units, input_code, exp_indicator, storage_code, single_group_indicator)
values ('0210900218', '一次性使用引流袋', '1','1', '1', '耗材', '01', 1, '袋', 'YCXSYYLD', 2, '100302', 'S');
