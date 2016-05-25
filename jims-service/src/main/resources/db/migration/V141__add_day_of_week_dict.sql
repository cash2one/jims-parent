/*==============================================================*/
/* Table: TIME_INTERVAL_DICT                 星期字典表      */
/*==============================================================*/
create table DAY_OF_WEEK_DICT
(
  serial_no  NUMBER(1),
  day_number NUMBER(1) not null,
  day_symbol VARCHAR2(2)
);
-- Add comments to the table 
comment on table DAY_OF_WEEK_DICT
  is '星期字典';
-- Add comments to the columns 
comment on column DAY_OF_WEEK_DICT.serial_no
  is '序号';
comment on column DAY_OF_WEEK_DICT.day_number
  is '天';
comment on column DAY_OF_WEEK_DICT.day_symbol
  is '记号';
insert into DAY_OF_WEEK_DICT (SERIAL_NO, DAY_NUMBER, DAY_SYMBOL)
values (1, 2, '一');

insert into DAY_OF_WEEK_DICT (SERIAL_NO, DAY_NUMBER, DAY_SYMBOL)
values (2, 3, '二');

insert into DAY_OF_WEEK_DICT (SERIAL_NO, DAY_NUMBER, DAY_SYMBOL)
values (3, 4, '三');

insert into DAY_OF_WEEK_DICT (SERIAL_NO, DAY_NUMBER, DAY_SYMBOL)
values (4, 5, '四');

insert into DAY_OF_WEEK_DICT (SERIAL_NO, DAY_NUMBER, DAY_SYMBOL)
values (5, 6, '五');

insert into DAY_OF_WEEK_DICT (SERIAL_NO, DAY_NUMBER, DAY_SYMBOL)
values (6, 7, '六');

insert into DAY_OF_WEEK_DICT (SERIAL_NO, DAY_NUMBER, DAY_SYMBOL)
values (7, 1, '日');
