-- Create table
--    author:yangruidong
/*==============================================================*/
/* Table:v_exp_dict_price_list                                     */
/*==============================================================*/
 create or replace view v_exp_dict_price_list as
  select a.exp_code ,
       a.exp_name ,
       a.exp_spec ,
       a.input_code,
       a.units,
       b.trade_price,
       b.retail_price,
       d.supplier,
       d.id,
	     o.label
  from exp_dict a, exp_price_list b, drug_supplier_catalog d ,org_sys_dict o where a.exp_code=b.exp_code and d.id=b.FIRM_ID and o.value=a.units