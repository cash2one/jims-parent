var drugStorage= [];
var drugStorageData={};
drugStorageData.storageType='1';
drugStorageData.orgId='1';/*parent.clinicMaster.orgId;*/
/**
 * 费别项目
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/drug-storage-dept/listByParams' ,
    data: JSON.stringify(drugStorageData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        drugStorage=data;
    }
});
/**
 * 费别翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function drugStorageFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < drugStorage.length; i++) {
    if (drugStorage[i].storageCode == value) {
        return drugStorage[i].storageName;
    }
    }
}