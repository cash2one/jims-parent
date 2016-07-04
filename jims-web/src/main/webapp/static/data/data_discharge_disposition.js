/**
 * Created by pq on 2016/7/4 0004.
 * 出院方式字典
 */
var discharge = [];

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=DISCHARGE_DISPOSITION_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        discharge=data;
    }
});


/**
 * 性别翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function dischargeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < discharge.length; i++) {
        if (discharge[i].value == value) {
            return discharge[i].label;
        }
    }
}