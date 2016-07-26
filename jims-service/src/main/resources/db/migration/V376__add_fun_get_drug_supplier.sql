create or replace function get_drug_supplier_id(as_id STRING,org_id STRING)

  return string is
  Result string(100);
BEGIN

  select a.supplier_id
    into Result
    from drug_supplier_catalog a
   where a.ID = as_id AND a.org_id = org_id;

  return (Result);
end get_drug_supplier_id;