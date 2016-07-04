$(function(){
    var patientId=$("#patientId",parent.document).val();
    /**
     * 性别下拉框
     */
    $('#setId').combobox({
        data: setData,
        valueField: 'value',
        textField: 'label'
    })
    $("#setId ").combobox('select',setData[0].value);
    /**
     * 民族下拉框
     */
  $("#nation").combobox({
      data: nationDict,
      valueField: 'value',
      textField: 'label'
  })
    $("#nation ").combobox('select',setData[0].value);
    /**
     * 职业
     */
    /*$("#occupation").combobox({
        data: setData,
        valueField: 'value',
        textField: 'label'
    })*/
    /**
     * 身份
     */
    $("#identity").combobox({
        data: identityDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#identity ").combobox('select',setData[0].value);
    /**
     * 婚姻状态
     */
    $("#maritalStatus").combobox({
        data: marriageDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#maritalStatus ").combobox('select',setData[0].value);
    /**
     * 费别下拉框
     */
    $("#chargeTypeId").combobox({
        data: chargeTypeDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#chargeTypeId ").combobox('select',setData[0].value);
    /**
     * 关系
     */
    $("#relationship").combobox({
        data: relationshipDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#relationship ").combobox('select',setData[0].value);

    $.ajax({
        'type': 'get',
        'url': basePath + '/patList/getPatMasterIndex',
        'contentType': 'application/json',
        'data': {patientId:patientId},
        'dataType': 'json',
        'success': function(data){
            $("#xinxiForm").form('load',data);
        },
        'error': function(){

        }
    })
})
function savePatInfo(){
    var patientId=$("#patientId",parent.document).val();
    var formJson=fromJson('xinxiForm');
    $.postJSON(basePath+"/patList/savePatInfo",formJson,function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","保存成功");
            $.ajax({
                'type': 'get',
                'url': basePath + '/patList/getPatMasterIndex',
                'contentType': 'application/json',
                'data': {patientId:patientId},
                'dataType': 'json',
                'success': function(data){
                    $("#xinxiForm").form('load',data);
                },
                'error': function(){
                }
            })
        }else{
            $.messager.alert("提示信息","保存失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","保存成功","errorzn");
    }
}