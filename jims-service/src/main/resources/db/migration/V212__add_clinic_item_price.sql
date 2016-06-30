create or replace view v_clinic_item_price as
select a.storage,
       a.sub_storage,--子药局
       a.drug_spec,--药品规格
       a.drug_code,
       a.units,--单位
       a.quantity,--库存量
       a.package_units,--包装单位
       a.package_spec,--包装规格
       b.item_name,
       b.item_class,
       c.input_code,
       b.price,
       b.item_spec,
       d.supplier,
       d.supplier_id,
       d.id as firm_id,
      c.dose_units,--剂量单位
      c.dose_per_unit,--最小用量
       b.subj_code,
       b.performed_by
  from drug_stock            a,
       current_price_list    b,
       drug_dict             c,
       drug_supplier_catalog d
 where a.drug_code = c.drug_code
   and a.drug_code = b.item_code
   and a.org_id = b.org_id
   and a.org_id = d.org_id
   and a.firm_id = d.id;
