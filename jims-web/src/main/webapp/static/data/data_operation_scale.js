var operationScaleName = [];//手术等级
var operationScaleNameData = {};
operationScaleNameData.orgId = "";
operationScaleNameData.dictType = "SYS_DICT";

var InputParamVo = {};
var inputParamVos=[];
var q = 'OPERATION_SCALE_DICT';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue = '20';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
operationScaleNameData.inputParamVos = inputParamVos;
$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(operationScaleNameData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        operationScaleName = data;
    }
});

/**
 * 手术等级翻译
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function operationScaleFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < operationScaleName.length; i++) {
        if (operationScaleName[i].value == value) {
            return operationScaleName[i].label;
        }
    }
}