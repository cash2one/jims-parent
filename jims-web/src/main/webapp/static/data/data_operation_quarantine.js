var isolationIndicator=[];//隔离标志
var quarantineData={};
quarantineData.orgId="";
quarantineData.dictType="SYS_DICT";

var InputParamVo = {};
var inputParamVos=[];
var q='QUARANTINE_DICT';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue='20';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
quarantineData.inputParamVos = inputParamVos;
$.ajax({
    'type':'POST',
    'url': basePath + '/input-setting/listParam',
    data:JSON.stringify(quarantineData),
    'contentType':'application/json',
    'dataType':'json',
    'async':'false',
    'success':function(data){
        isolationIndicator=data;
    }

})
/**
 * 隔离标志翻译
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function isolationIndicatorFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < quarantine.length; i++) {
        if (quarantine[i].id == value) {
            return quarantine[i].label;
        }
    }
}