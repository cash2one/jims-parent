-- Create table
--    author:yangruidong
/*==============================================================*/
/* Table:v_exp_dict_price_list                                     */
/*==============================================================*/
create or replace view v_exp_dict_price_list as
select a.exp_code,
       a.exp_name,
       a.exp_spec,
       a.org_id,
       a.input_code,
       a.units,
       b.trade_price,
       b.retail_price,
       d.supplier,
       d.id,
       o.label
  from
       (select distinct exp_code,exp_name,exp_spec,input_code,units,org_id from exp_dict) a,
       (select distinct supplier,id from drug_supplier_catalog) d,
       (select distinct value,label from org_sys_dict where type = 'PACKAGE_UNITS') o,
       (select distinct exp_code,trade_price,retail_price,FIRM_ID from exp_price_list) b

 where a.exp_code = b.exp_code
   and d.id = b.FIRM_ID
   and o.value = a.units;