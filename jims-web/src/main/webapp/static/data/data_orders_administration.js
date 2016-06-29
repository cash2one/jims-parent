/**
 * Created by Administrator on 2016/6/29 0029.
 */

var administrationDict = [];
/**
 * 途径
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/AdministrationDict/listAdministrationByInpOrOutpFlag',
    data: 'inpOrOutpFlag=1',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        administrationDict=data;
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
    for (var i = 0; i < administrationDict.length; i++) {
        if (administrationDict[i].id == value) {
            return administrationDict[i].administrationName;
        }
    }
}
