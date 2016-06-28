var anaesthesiaName =[];//麻醉方式
var anaesthesiaData={};
anaesthesiaData.orgId="";
anaesthesiaData.dictType="SYS_DICT";
doctorNameData.inputParamVos=inputParamVos;
var InputParamVo = {};
var q='ANAESTHESIA_DICT';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue='20';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
anaesthesiaData.inputParamVos = inputParamVos;
$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(anaesthesiaData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        anaesthesiaName=data;
    }
});
/**
 * 麻醉方式翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function anaesthesiaNameFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0; i<anaesthesiaName.length; i++){
         if(anaesthesiaName[i].id == value){
             return anaesthesiaName[i].anaesthesia_name;
         }
    }
}
