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
        userBlood = data;
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
    if (value == 0 || value == undefined) {
        return;
    }
    var distinction = '';
    for (var i = 0; i < userBlood.length; i++) {
        if (userBlood[i].blood_type == value) {
            distinction = userBlood[i].blood_type_name;
        }
    }
    if (distinction == '') {
        var InputParamVo = {};
        InputParamVo.colName = 'id';
        InputParamVo.colValue = value;
        InputParamVo.operateMethod = '=';
        inputParamVos.push(InputParamVo);
        $.ajax({
            'type': 'POST',
            'url': basePath + '/input-setting/listParam',
            data: JSON.stringify(userBloodData),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
            'success': function (data) {
                doctorName.push(data[0]);
                distinction = data[0].blood_type_name;
            }
        });
        return distinction;
    } else {
        return distinction;
    }
}
/**
 * 下拉框自动 补全
 * @param q
 * @param id
 */
function comboCompleting(q, id) {
    var userBloodData = {};
    userBloodData.orgId = "1";
    userBloodData.dictType = "BLOOD_COMPONENT";
    var inputParamVos = new Array();
    var InputParamVo1 = {};
    InputParamVo1.colName = "rownum";
    InputParamVo1.colValue = "20";
    InputParamVo1.operateMethod = '<';
    inputParamVos.push(InputParamVo1);
    if (q != '' && q != null) {
        var InputParamVo = {};
        InputParamVo.colName = 'input_code';
        InputParamVo.colValue = 'q';
        InputParamVo.operateMethod = 'like';
    } else {
        $("#" + id).combogrid('setValue', '');
    }
    userBloodData.inputParamVos = inputParamVos;
    $.ajax({
        'type': 'POST',
        'url': basePath + '/input-setting/listParam',
        data: JSON.stringify(userBloodData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function (data) {
            $("#" + id).combogrid('setText', q);
        }
    })
}