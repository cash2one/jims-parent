-- 药品库存名称视图
create or replace view v_drug_stock_name_dict as
select
       dd.drug_code,
       dd.drug_name,
       ds.storage,
       ds.org_id,
       dd.input_code
  from drug_stock ds,(select distinct drug_code,drug_name,input_code from drug_dict where del_flag='0') dd
  where ds.drug_code = dd.drug_code
  and ds.del_flag = '0'
  and ds.supply_indicator = '1'
  and ds.quantity > 0;

  
-- 药品库存数据视图
  create or replace view v_drug_stock as
select
     d.id,
     d.drug_code,
     d.drug_spec,
     d.units,
     d.batch_no,
     to_char(d.expire_date,'yyyy-MM-dd') expire_date,
     d.firm_id,
     s.supplier,
     d.quantity,
     p.retail_price,
     p.trade_price,
     d.purchase_price,
     d.package_spec,
     d.package_units,
     d.sub_package_1,
     d.sub_package_units_1,
     d.sub_package_spec_1,
     d.sub_package_2,
     d.sub_package_units_2,
     d.sub_package_spec_2,
     d.org_id,
     d.storage
  from drug_stock d,(select id,supplier from drug_supplier_catalog where del_flag='0') s,drug_price_list p
  where d.firm_id = s.id(+)
  and d.drug_code = p.drug_code
  and d.drug_spec = p.drug_spec
  and d.firm_id = p.firm_id
  and d.org_id = p.org_id
  and d.units = p.units
  and d.del_flag = '0'
  and p.del_flag = '0'
  and d.supply_indicator = '1'
  and d.quantity > 0;



alter table drug_export_detail modify (firm_id varchar2(64));
