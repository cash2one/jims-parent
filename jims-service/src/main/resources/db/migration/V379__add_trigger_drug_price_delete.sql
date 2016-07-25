CREATE OR REPLACE TRIGGER DRUG_PRICE_LIST_DELETE
  AFTER DELETE ON DRUG_PRICE_LIST
  FOR EACH ROW

when (old.stop_date is NULL)
BEGIN
  DELETE FROM price_list
   WHERE (item_class = 'A' or item_class = 'B')
     and item_code = :old.drug_code
     and item_spec = :old.drug_spec || get_drug_supplier_id(:old.firm_id,:OLD.org_id)
     and units = :old.units
     and stop_date is NULL
     AND ID=:OLD.ID;
END;
