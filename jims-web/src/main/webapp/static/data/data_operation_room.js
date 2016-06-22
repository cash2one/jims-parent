var operatingRoom = [];//手术室
var operatingDate = {};
operatingDate.orgId = "";
var operationRoomNo = [];//手术间
var operationRoomNoData = {};
operationRoomNoData.orgId = "";
operationRoomNoData.dictType = "OPERATING_ROOM";
operationRoomNoData.inputParamVos = inputParamVos;

$.ajax({
    'type': 'POST',
    'url': basePath + '/dept-dict/getOperation',
    'data': JSON.stringify(operatingDate),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': 'false',
    'success': function (data) {
        operatingRoom = data;
    }
})
/**
 * 手术室翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {Document.deptName|.queryParams.deptName|*|deptName|obj.deptName|deptDictVo.deptName}
 */
function operationFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < operatingRoom.length; i++) {
        if (operatingRoom[i].deptCode == value) {
            return operatingRoom[i].deptName;
        }
    }
}
/**
 * 手术间翻译
 * @param value
 * @param id
 * @param rowData
 * @param rowindex
 */
function operationRoomNoFormatter(value, id, rowData, rowindex) {
    if (value == 0) {
        return;
    }
    var distinction = '';
    for (var i = 0; i < operationRoomNo.length; i++) {
        distinction = operationRoomNo[i].room_no;
    }

}

/**
 * 手术室下拉框加载
 * @param q
 * @param id
 */
function comboboxLoad(q, id) {
    if (q != '' && q != null) {
        var InputParamVo = {};
        InputParamVo.colName = 'DEPT_ID';
        InputParamVo.colValue = q;
        InputParamVo.operateMethod = '=';
        inputParamVos.push(InputParamVo);
        operationRoomNoData.inputParamVos = inputParamVos;
        $.ajax({
            'type': 'POST',
            'url': basePath + '/input-setting/listParam',
            data: JSON.stringify(operationRoomNoData),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
            'success': function (data) {
                $("#" + id).combobox({
                    data: data,
                    valueField: 'id',
                    textField: 'room_no'
                })
            }
        });
    } else {
        $("#" + id).combobox('loadData', '');
    }
}