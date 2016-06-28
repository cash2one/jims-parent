var operationScaleName = [];//手术等级
var operationScaleData = {};
operationScaleData.orgId = "";
operationScaleData.dictType = "SYS_DICT";
//doctorNameData.inputParamVos=inputParamVos1;

    var InputParamVo = {};
    var q='OPERATION_SCALE_DICT';
    InputParamVo.colName = 'TYPE';
    InputParamVo.colValue = q;
    InputParamVo1.colValue='20';
    InputParamVo.operateMethod = '=';
    inputParamVos.push(InputParamVo);
    operationScaleData.inputParamVos = inputParamVos;
    $.ajax({
        'type': 'POST',
        'url': basePath + '/input-setting/listParam',
        data: JSON.stringify(operationScaleData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function (data) {
            operationScaleName=data;
        }
    });

/**
 * 手术等级翻译
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function operationScaleNameFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < operationScaleName.length; i++) {
        if (operationScaleName[i].id == value) {
            return operationScaleName[i].MEMO;
        }
    }
}