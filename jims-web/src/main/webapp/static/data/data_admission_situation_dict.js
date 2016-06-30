var situation=[] //入院情况
var situationData={};
situationData.orgId="";
situationData.dictType="SYS_DICT";

var InputParamVo = {};
var inputParamVos=[];
var q='ADMISSION_SITUATION_DICT';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue='20';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
situationData.inputParamVos = inputParamVos;
$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(situationData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        situation=data;
    }
});
/**
 * 入院情况翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function situationFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0; i<situation.length; i++){
        if(situation[i].value == value){
            return situation[i].label;
        }
    }
}