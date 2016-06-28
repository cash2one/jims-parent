var bloodTypeName = [];//血液要求
var userBloodData = {};
userBloodData.orgId = "";
userBloodData.dictType = "BLOOD_COMPONENT";
userBloodData.inputParamVos = inputParamVos;

$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(userBloodData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        bloodTypeName = data;
    }
})
/**
 * 血液要求翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {Document.deptName|.queryParams.deptName|*|deptName|obj.deptName|deptDictVo.deptName}
 */
function bloodTypeNameFormatter(value, rowData, rowIndex) {
    alert(value);
    if (value == 0) {
        return;
    }
    for (var i = 0; i < bloodTypeName.length; i++) {
        if (bloodTypeName[i].blood_type == value) {
            distinction = bloodTypeName[i].blood_type_name;
        }
    }
}