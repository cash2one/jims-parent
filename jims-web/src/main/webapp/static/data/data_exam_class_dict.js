var examClass = [] // 检查类别
var examClassData = {};
examClassData.orgId="";
examClassData.dictType="EXAM_CLASS_DICT";

$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(examClassData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        examClass=data;
    }
});

/**
 * 检查类别翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function examClassFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0; i<examClass.length; i++){
        if(examClass[i].value == value){
            return examClass[i].label;
        }
    }
}