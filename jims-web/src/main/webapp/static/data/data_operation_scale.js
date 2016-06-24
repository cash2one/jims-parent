var operationScaleName = [];//手术等级
var operationScaleData = {};
operationScaleData.orgId = "";
operationScaleData.dictType = "OPERATION_SCALE_DICT";

//$.ajax({
//    type:"POST",
//    url:basePath + "/input-setting/listParam",
//    data:JSON.stringify(operationScaleData),
//    contentType: "application/json",
//    dataType:"json",
//    async:"false",
//    success:function(data){
//        alert(1);
//        operationScaleName=data;
//    }
//})

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