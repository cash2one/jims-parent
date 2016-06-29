var bloodType=[];//血型
var bloodTypeData = {};
bloodTypeData.orgId ="";
bloodTypeData.dictType = "SYS_DICT";


var InputParamVo = {};
var inputParamVos=[];
var q='blood_type_dict';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue='20';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
bloodTypeData.inputParamVos = inputParamVos;

$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(bloodTypeData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        bloodType=data;
    }
});
/**
 * 血型翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function bloodTypeFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0; i<bloodType.length; i++){
        if(bloodType[i].value == value){
            return bloodType[i].label;
        }
    }
}