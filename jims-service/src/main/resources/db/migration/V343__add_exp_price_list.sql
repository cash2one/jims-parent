-- Create table
--    author:yangruidong
/*==============================================================*/
/* Table:exp_price_list                                     */
/*==============================================================*/
create table EXP_PRICE_LIST
(
  exp_code           VARCHAR2(20) not null,
  exp_spec           VARCHAR2(20) not null,
  firm_id            VARCHAR2(64) not null,
  units              VARCHAR2(8),
  trade_price        NUMBER(10,4),
  retail_price       NUMBER(10,4),
  amount_per_package NUMBER(5),
  min_spec           VARCHAR2(20),
  min_units          VARCHAR2(8),
  class_on_inp_rcpt  VARCHAR2(1),
  class_on_outp_rcpt VARCHAR2(1),
  class_on_reckoning VARCHAR2(3),
  subj_code          VARCHAR2(4),
  class_on_mr        VARCHAR2(4),
  start_date         DATE not null,
  stop_date          DATE,
  memos              VARCHAR2(20),
  max_retail_price   NUMBER(10,4),
  material_code      VARCHAR2(20),
  operator           VARCHAR2(20),
  permit_no          VARCHAR2(20),
  permit_date        DATE,
  register_no        VARCHAR2(20),
  register_date      DATE,
  fda_or_ce_no       VARCHAR2(20),
  fda_or_ce_date     DATE,
  other_no           VARCHAR2(20),
  other_date         DATE
);
-- Add comments to the columns
comment on column EXP_PRICE_LIST.permit_no
  is '生产经营许可证号  ';
comment on column EXP_PRICE_LIST.permit_date
  is '许可证期限';
comment on column EXP_PRICE_LIST.register_no
  is '医疗器械注册证号';
comment on column EXP_PRICE_LIST.register_date
  is '注册证期限';
comment on column EXP_PRICE_LIST.fda_or_ce_no
  is '美国或欧洲号';
comment on column EXP_PRICE_LIST.fda_or_ce_date
  is '美国或欧洲号期限';
comment on column EXP_PRICE_LIST.other_no
  is '其它证号';
comment on column EXP_PRICE_LIST.other_date
  is '其它日期';



insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210300222', '1', '2a743e6aba5e4ee8aa7c14e4c0ac2941', '个', 75, 75, 1, '1', '个', 'E', 'H', '550', '550', '材料', to_date('12-11-2015 14:45:31', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210300345', '1', '2a743e6aba5e4ee8aa7c14e4c0ac2941', '个', 150, 150, 1, '1', '个', 'E', 'H', '550', '550', '材料', to_date('06-01-2016 08:57:23', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210200003', '/', '7302ab5f08cf4ae589634f473fb69d47', '个', 12, 16, 1, '/', '个', 'E', 'H', '550', '550', '材料', to_date('23-10-2015 10:20:03', 'dd-mm-yyyy hh24:mi:ss'), null, null, 18, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0220080001', '480', '7302ab5f08cf4ae589634f473fb69d47', 'ml', 2.8, 2.94, 1, '480', 'ml', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 17:23:51', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210900209', '1', '7302ab5f08cf4ae589634f473fb69d47', '付', .38, .399, 1, '1', '付', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 17:44:50', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210900210', '1', 'aeabf9f85e2644559808aba5890d009c', '把', 25, 25, 1, '1', '把', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 17:49:28', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210900211', '1', 'aeabf9f85e2644559808aba5890d009c', '套', 1.86, 1.953, 1, '1', '套', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 17:51:43', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210900212', '1', 'aeabf9f85e2644559808aba5890d009c', '个', 1.2, 1.26, 1, '1', '个', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 17:53:30', 'dd-mm-yyyy hh24:mi:ss'), to_date('26-12-2015 12:13:08', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210900213', '60ml', 'aeabf9f85e2644559808aba5890d009c', '瓶', 4.8, 5.04, 1, '60ml', '瓶', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 17:55:05', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210900214', '1', '7302ab5f08cf4ae589634f473fb69d47', '片', 1, 1.05, 1, '1', '片', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 17:58:13', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210900215', '1', '7302ab5f08cf4ae589634f473fb69d47', '支', .56, .588, 1, '1', '支', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 18:00:07', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210900216', '1', '7302ab5f08cf4ae589634f473fb69d47', '支', .35, .3675, 1, '1', '支', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 18:01:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210900217', '1', '7302ab5f08cf4ae589634f473fb69d47', '支', .35, .3675, 1, '1', '支', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 18:02:12', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);
insert into EXP_PRICE_LIST (exp_code, exp_spec, firm_id, units, trade_price, retail_price, amount_per_package, min_spec, min_units, class_on_inp_rcpt, class_on_outp_rcpt, class_on_reckoning, subj_code, class_on_mr, start_date, stop_date, memos, max_retail_price, material_code, operator, permit_no, permit_date, register_no, register_date, fda_or_ce_no, fda_or_ce_date, other_no, other_date)
values ('0210900218', '1', '593bafc3221d4c7cb1b2beaed6f85e78', '袋', 1.7, 1.785, 1, '1', '袋', 'E', 'H', '550', '550', '材料', to_date('31-10-2015 18:03:31', 'dd-mm-yyyy hh24:mi:ss'), null, null, 0, null, null, null, null, null, null, null, null, null, null);













