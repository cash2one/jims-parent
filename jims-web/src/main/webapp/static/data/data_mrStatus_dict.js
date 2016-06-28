var mrStatus = [];
var mrStatusData = {};
mrStatusData.orgId = "";
mrStatusData.dictType = "SYS_DICT";

var InputParamVo = {};
var inputParamVos= [];
var q = 'mr_status_dict';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue = '20';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
mrStatusData.inputParamVos = inputParamVos;
$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(mrStatusData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        mrStatus = data;
    }
});

/**
 *
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function operationScaleNameFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < mrStatus.length; i++) {
        if (mrStatus[i].id == value) {
            return mrStatus[i].label;
        }
    }
}