create or replace view v_clinic_item_price as
select a.storage,
       a.sub_storage,
       a.drug_spec,
       a.drug_code,
       a.units,
       a.package_spec,
       a.quantity,
       b.item_name,
       b.item_class,
       c.input_code,
       b.price,
       b.item_spec,
       d.supplier,
       d.supplier_id,
      c.dose_units,
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