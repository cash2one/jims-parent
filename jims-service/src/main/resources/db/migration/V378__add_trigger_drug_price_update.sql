CREATE OR REPLACE TRIGGER DRUG_PRICE_LIST_UPDATE
  AFTER UPDATE ON DRUG_PRICE_LIST
  FOR EACH ROW

when (old.stop_date is NULL )
BEGIN
  UPDATE price_list
     SET item_code          = :new.drug_code,
         item_spec          = :new.drug_spec || get_drug_supplier_id(:NEW.firm_id,:NEW.org_id),
         units              = :new.units,
         price              = :new.retail_price,
         prefer_price       = :new.retail_price,
         foreigner_price    = :new.retail_price,
         memo               = :new.memos,
         stop_date          = :new.stop_date,
         class_on_inp_rcpt  = :new.class_on_inp_rcpt,
         class_on_outp_rcpt = :new.class_on_outp_rcpt,
         class_on_reckoning = :new.class_on_reckoning,
         subj_code          = :new.subj_code,
         class_on_mr        = :new.class_on_mr
   WHERE (item_class = 'A' or item_class = 'B')
     and item_code = :old.drug_code
     and item_spec = :old.drug_spec || get_drug_supplier_id(:OLD.firm_id,:OLD.org_id)
     and units = :old.units
     and stop_date is null
     and id=:old.id;
END;
