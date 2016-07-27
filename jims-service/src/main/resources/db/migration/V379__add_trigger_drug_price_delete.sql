CREATE OR REPLACE TRIGGER DRUG_PRICE_LIST_DELETE
  AFTER DELETE ON DRUG_PRICE_LIST
  FOR EACH ROW

  WHEN (OLD.STOP_DATE IS NULL)
BEGIN

  DELETE FROM PRICE_LIST
   WHERE (ITEM_CLASS = 'A' OR ITEM_CLASS = 'B')
     AND ITEM_CODE = :OLD.DRUG_CODE
     AND ITEM_SPEC =
         :OLD.DRUG_SPEC || GET_DRUG_SUPPLIER_ID(:OLD.FIRM_ID, :OLD.ORG_ID)
     AND UNITS = :OLD.UNITS
     AND STOP_DATE IS NULL
     AND ID = :OLD.ID;


   ----诊疗项目名称
   --DELETE FROM clinic_item_name_dict WHERE item_code=:OLD.drug_code AND org_id=:OLD.ORG_ID;
   ----诊疗项目
   --DELETE FROM clinic_item_dict WHERE item_code=:OLD.drug_code AND org_id=:OLD.org_id ;
   ----价表项目名称
   --DELETE FROM price_item_name_dict WHERE item_code=:OLD.drug_code AND org_id=:OLD.org_id ;
   ----诊疗项目与价表项目对照
   --DELETE FROM clinic_vs_charge WHERE clinic_item_code = :OLD.drug_code AND org_id=:OLD.org_id ;

END;