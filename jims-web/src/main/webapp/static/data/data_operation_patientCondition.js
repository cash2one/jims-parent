var patientCondition=[];//手术病情

$.ajax({
    'type':'get',
    'url': basePath + '/dict/findListByType',
    data: 'type=PATIENT_STATUS_DICT',
    'contentType':'application/json',
    'dataType':'json',
    'async':'false',
    'success':function(data){
        patientCondition=data;
    }
})
/**
 * 手术病情翻译
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function patientConditionFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < patientCondition.length; i++) {
        if (patientCondition[i].value == value) {
            return patientCondition[i].label;
        }
    }
}