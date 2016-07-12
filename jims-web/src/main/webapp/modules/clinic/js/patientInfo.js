
$(function() {
    /**
     * 性别下拉框
     */
    $('#setId').combobox({
        data: setData,
        valueField: 'value',
        textField: 'label'
    })
    //$("#setId").combobox('select',setData[0].value);
    /**
     * 身份下拉框
     */
    $('#identityId').combobox({
        data: identityDict,
        valueField: 'id',
        textField: 'identityName'
    })
    //$("#identityId ").combobox('select',identityDict[0].id);
  /**
     * 费别下拉框
     */
    $('#chargeTypeId').combobox({
        data: chargeType,
        valueField: 'id',
        textField: 'charge_type_name'
    })

    /**
     * 合同单位下拉框
     */
    $('#companyId').combobox({
        data: unitContract,
        valueField: 'id',
        textField: 'unitName'
    })
    var clinicId=parent.clinicMaster.id;
    $.ajax({
        'type': 'get',
        'url':basePath + '/clinicMaster/getPatInfo',
        'contentType': 'application/json',
        'data': {id:clinicId},
        'dataType': 'json',
        'success': function(data){
            $("#patientInfo").form('load',data);
        }
    })
})
function saveInfo(){
    var formJson=fromJson('patientInfo');
    $.postJSON(basePath+"/clinicMaster/savePatInfo",formJson,function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","病人信息保存成功");

            getClinicForRegist()
        }else{
            $.messager.alert("提示信息","病人信息保存失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","病人信息保存失败","errorzn");
    }
}



