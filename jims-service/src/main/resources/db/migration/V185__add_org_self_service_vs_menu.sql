
--机构私人服务对应菜单表
CREATE TABLE ORG_SELF_SERVICE_VS_MENU 
(
   ID                 VARCHAR2(64)         NOT NULL,
   SELF_SERVICE_ID    VARCHAR2(64),
   MENU_ID            VARCHAR2(64),
   MENU_SORT          VARCHAR2(10),
   MENU_END_DATE        DATE,
   CAREATE_BY           VARCHAR2(64),
   REMARK               VARCHAR2(2000),
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          DATE,
   DEL_FLAG             VARCHAR2(2),
   CREATE_TIME          DATE,
   CONSTRAINT PK_ORG_SELF_SERVICE_VS_MENU PRIMARY KEY (ID)
);

COMMENT ON TABLE ORG_SELF_SERVICE_VS_MENU IS
'机构私人服务对应菜单表';

COMMENT ON COLUMN ORG_SELF_SERVICE_VS_MENU.SELF_SERVICE_ID IS
'服务ID';

COMMENT ON COLUMN ORG_SELF_SERVICE_VS_MENU.MENU_ID IS
'菜单ID';

COMMENT ON COLUMN ORG_SELF_SERVICE_VS_MENU.MENU_END_DATE IS
'菜单停用日期';

COMMENT ON COLUMN ORG_SELF_SERVICE_VS_MENU.MENU_SORT IS
'菜单排序';


INSERT INTO menu_dict( id, menu_name, href, icon, sort, target, pid, menu_level, remarks, update_by, create_by, update_date, del_flag, create_date ) VALUES ( sys_guid(),'自定义服务', ' /modules/sys/org-self-service.html', '1', '1', '1', '55CB0105F69B490DA48F7D1F1029370A', '1', null, null, null, sysdate, '0', sysdate );
