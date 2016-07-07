var nurseClass= [];

var nurseClassData={};
nurseClassData.dictType="NURSING_CLASS_DICT";
/**
 * 护理等级
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(nurseClassData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        nurseClass=data;
    }
});
/**
 * 护理等级翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function nurseClassFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < nurseClass.length; i++) {
        if (nurseClass[i].vaule == value) {
            return nurseClass[i].label;
        }
    }
}