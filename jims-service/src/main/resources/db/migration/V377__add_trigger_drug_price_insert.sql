CREATE OR REPLACE TRIGGER DRUG_PRICE_LIST_INSERT
  AFTER INSERT ON DRUG_PRICE_LIST
  REFERENCING NEW AS NEW OLD AS OLD
  FOR EACH ROW
DECLARE
  V_DRUG_INDICATOR NUMBER(1);
  V_DRUG_NAME      VARCHAR2(100);
  V_DRUG_CLASS     VARCHAR2(1);
  V_INPUT_CODE     VARCHAR2(50);
  V_TEMP_FLAG      NUMBER;
BEGIN

  --判断诊疗项目是否已经存在
  SELECT COUNT(*)
    INTO V_TEMP_FLAG
    FROM CLINIC_ITEM_DICT
   WHERE ITEM_CODE = :NEW.DRUG_CODE;

  SELECT DISTINCT DRUG_INDICATOR
    INTO V_DRUG_INDICATOR
    FROM DRUG_DICT
   WHERE DRUG_CODE = :NEW.DRUG_CODE;

  SELECT DRUG_NAME, INPUT_CODE
    INTO V_DRUG_NAME, V_INPUT_CODE
    FROM DRUG_NAME_DICT
   WHERE STD_INDICATOR = 1
     AND DRUG_CODE = :NEW.DRUG_CODE;

  IF V_DRUG_INDICATOR = 1 OR V_DRUG_INDICATOR = 2 OR V_DRUG_INDICATOR = 3 OR
     V_DRUG_INDICATOR = 5 OR V_DRUG_INDICATOR = 6 OR V_DRUG_INDICATOR = 9 OR
     V_DRUG_INDICATOR = 8 THEN
    IF V_DRUG_INDICATOR = 1 OR V_DRUG_INDICATOR = 3 OR V_DRUG_INDICATOR = 5 OR
       V_DRUG_INDICATOR = 6 OR V_DRUG_INDICATOR = 9 OR V_DRUG_INDICATOR = 8 THEN
      V_DRUG_CLASS := 'A';
    ELSE
      V_DRUG_CLASS := 'B';
    END IF;
    --4,价表
    INSERT INTO PRICE_LIST
      (ID,
       ITEM_CLASS,
       ITEM_CODE,
       ITEM_NAME,
       ITEM_SPEC,
       UNITS,
       PRICE,
       PREFER_PRICE,
       FOREIGNER_PRICE,
       FEE_TYPE_MASK,
       CLASS_ON_INP_RCPT,
       CLASS_ON_OUTP_RCPT,
       CLASS_ON_RECKONING,
       SUBJ_CODE,
       CLASS_ON_MR,
       MEMO,
       START_DATE,
       ENTER_DATE)
    VALUES
      (SYS_GUID(),
       V_DRUG_CLASS,
       :NEW.DRUG_CODE,
       V_DRUG_NAME,
       :NEW.DRUG_SPEC || GET_DRUG_SUPPLIER_ID(:NEW.FIRM_ID, :NEW.ORG_ID),
       :NEW.UNITS,
       :NEW.RETAIL_PRICE,
       :NEW.RETAIL_PRICE,
       :NEW.RETAIL_PRICE,
       0,
       :NEW.CLASS_ON_INP_RCPT,
       :NEW.CLASS_ON_OUTP_RCPT,
       :NEW.CLASS_ON_RECKONING,
       :NEW.SUBJ_CODE,
       :NEW.CLASS_ON_MR,
       :NEW.MEMOS,
       :NEW.START_DATE,
       SYSDATE);
    IF V_TEMP_FLAG = 0 THEN
      --1,诊疗项目名称表
      INSERT INTO CLINIC_ITEM_NAME_DICT
        (ITEM_CLASS,
         ITEM_NAME,
         ITEM_CODE,
         STD_INDICATOR,
         INPUT_CODE,
         ITEM_STATUS,
         ID,
         ORG_ID,
         UPDATE_DATE,
         DEL_FLAG,
         CREATE_DATE)
      VALUES
        (V_DRUG_CLASS,
         V_DRUG_NAME,
         :NEW.DRUG_CODE,
         V_DRUG_INDICATOR,
         V_INPUT_CODE,
         '0',
         SYS_GUID(),
         :NEW.ORG_ID,
         SYSDATE,
         '1',
         SYSDATE);
      --2,诊疗项目表
      INSERT INTO CLINIC_ITEM_DICT
        (ID,
         ITEM_CLASS,
         ITEM_CODE,
         ITEM_NAME,
         INPUT_CODE,
         ITEM_STATUS,
         UPDATE_DATE,
         DEL_FLAG,
         CREATE_DATE,
         ORG_ID)
      VALUES
        (SYS_GUID(),
         V_DRUG_CLASS,
         :NEW.DRUG_CODE,
         V_DRUG_NAME,
         V_INPUT_CODE,
         '0',
         SYSDATE,
         '0',
         SYSDATE,
         :NEW.ORG_ID);
      --3,价表名称表
      INSERT INTO PRICE_ITEM_NAME_DICT
        (ITEM_CLASS,
         ITEM_NAME,
         ITEM_CODE,
         STD_INDICATOR,
         INPUT_CODE,
         STOP_FLAG,
         ID,
         UPDATE_DATE,
         DEL_FLAG,
         ORG_ID)
      VALUES
        (V_DRUG_CLASS,
         V_DRUG_NAME,
         :NEW.DRUG_CODE,
         '1',
         V_INPUT_CODE,
         '0',
         SYS_GUID(),
         SYSDATE,
         '0',
         :NEW.ORG_ID);
    
      --价表与诊疗项目对照
      INSERT INTO CLINIC_VS_CHARGE
        (ID,
         CLINIC_ITEM_CLASS,
         CLINIC_ITEM_CODE,
         CHARGE_ITEM_NO,
         CHARGE_ITEM_CLASS,
         CHARGE_ITEM_CODE,
         ORG_ID,
         UPDATE_DATE,
         DEL_FLAG,
         CREATE_DATE)
      VALUES
        (SYS_GUID(),
         V_DRUG_CLASS,
         :NEW.DRUG_CODE,
         1,
         V_DRUG_CLASS,
         :NEW.DRUG_CODE,
         :NEW.ORG_ID,
         SYSDATE,
         0,
         SYSDATE);
    END IF;
  
  END IF;

END;