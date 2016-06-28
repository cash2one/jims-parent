
var administrationmzDict = [];

/**
 * 途径
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/AdministrationDict/mzViewList',
    data: 'orgId=1',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        administrationmzDict=data;
    }
});
/**
 * 途径翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function administrationFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < administrationmzDict.length; i++) {
        if (administrationmzDict[i].id == value) {
            return administrationmzDict[i].administrationName;
        }
    }
}
