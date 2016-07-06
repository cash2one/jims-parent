-- Create table
--    author:yangruidong
/*==============================================================*/
/* Table:DRUG_PRICE_LIST      DRUG_DICT         drug_supplier_catalog                */
/*==============================================================*/

-- Create view
CREATE OR REPLACE VIEW V_DRUG_PROVIDE_APPLICATION AS
SELECT A.DRUG_CODE,
       A.DRUG_SPEC,
       B.DRUG_NAME,
       A.UNITS,
       A.FIRM_ID,
       A.TRADE_PRICE,
       A.RETAIL_PRICE,
       b.input_code,
       c.supplier_id,
	     s.label
  FROM DRUG_PRICE_LIST A, DRUG_DICT B,
  DRUG_SUPPLIER_CATALOG  C,
  (SELECT VALUE,LABEL FROM SYS_DICT WHERE  TYPE = 'SPEC_UNIT')  S
  WHERE A.DRUG_CODE = B.DRUG_CODE AND C.ID=A.FIRM_ID AND S.VALUE=A.UNITS
   AND SYSDATE > A.START_DATE
   AND (A.STOP_DATE IS NULL OR A.STOP_DATE > SYSDATE);