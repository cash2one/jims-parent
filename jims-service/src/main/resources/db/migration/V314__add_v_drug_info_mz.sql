--门诊药品视图
create or replace view v_drug_info_mz as
select a.storage,
       a.sub_storage,
       a.drug_spec,
       a.drug_code,
       a.units,
       a.package_spec,
       a.quantity,
       b.item_name,
       b.input_code,
       d.supplier,
       d.supplier_id,
       c.dose_per_unit,
       c.dose_units
  from drug_stock            a,
       current_price_list    b,
       drug_dict             c,
       drug_supplier_catalog d
 where a.drug_code = c.drug_code
   and a.drug_code = b.item_code
   and a.org_id = b.org_id
   and a.org_id = d.org_id
   and a.firm_id = d.id
   and b.item_spec = a.firm_id||a.drug_spec



