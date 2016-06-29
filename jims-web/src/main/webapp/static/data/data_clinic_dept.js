var clinicDeptCode=[];//门诊科室
var clinicDeptCodeData={};
clinicDeptCodeData.orgId="1";
clinicDeptCodeData.dictType="v_outp_dept_dict"
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(clinicDeptCodeData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        clinicDeptCode=data;
    }
});

/**
 * 科室翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string}
 */
function clinicDeptCodeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < clinicDeptCode.length; i++) {
        if (clinicDeptCode[i].id == value) {
            return clinicDeptCode[i].dept_name;
        }
    }
}