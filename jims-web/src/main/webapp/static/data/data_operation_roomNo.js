var operationRoomNo = [];
var operationRoomNoData = {};
operationRoomNoData.orgId = "";
operationRoomNoData.roomType = "operation_room";
operationRoomNoData.inputParamVos = inputParamVos;
$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(operationRoomNoData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        operationRoomNo = data;
    }
});

function operationRoomNoformatter(value, rowData, rowindex) {
    alert(value);
    if (value == 0) {
        return;
    }

    var distinction = '';
    for (var i = 0; i < operationRoomNo.length; i++) {
        distinction = operationRoomNo[i].room_no;
    }
    if (distinction == '') {
        var InputParamVo = {};
        InputParamVo.colName = 'DEPT_ID';
        InputParamVo.colValue = value;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
        $.ajax({
            'type': 'POST',
            'url': basePath + '/input-setting/listParam',
            data: JSON.stringify(operationRoomNoData),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
            'success': function (data) {
                operationRoomNo.push(data[0]);
                distinction = data[0].room_no;
            }
        });
        return distinction;
    } else {
        return distinction;
    }
}
