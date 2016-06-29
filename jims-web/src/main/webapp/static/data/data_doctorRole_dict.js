var doctorRole = [];
var doctorRoleData = {};
doctorRoleData.orgId = "";
doctorRoleData.dictType = "SYS_DICT";

var InputParamVo = {};
var inputParamVos= [];
var q = 'doctor_role';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue = '20';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
doctorRoleData.inputParamVos = inputParamVos;
$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(doctorRoleData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        doctorRole = data;
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
    for (var i = 0; i < doctorRole.length; i++) {
        if (doctorRole[i].id == value) {
            return doctorRole[i].label;
        }
    }
}