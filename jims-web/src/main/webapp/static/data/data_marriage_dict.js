var marriage =[];//婚姻
var marriageData = {};
marriageData.orgId="";
marriageData.dictType="SYS_DICT";

var InputParamVo = {};
var inputParamVos=[];
var q='MARRIAGE_DICT';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue='20';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
marriageData.inputParamVos = inputParamVos;
$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(marriageData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        marriage=data;
    }
});
/**
 * 婚姻翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function marriageFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0; i<marriage.length; i++){
        if(marriage[i].value == value){
            return marriage[i].label;
        }
    }
}
