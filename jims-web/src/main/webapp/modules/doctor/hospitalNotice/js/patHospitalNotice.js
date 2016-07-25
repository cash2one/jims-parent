var cId = parent.clinicMaster.id;
var patientId = parent.clinicMaster.patientId;


function onloadMethod() {
        $.ajax({
            'type': 'post',
            'url': basePath+'/patHospitalNotice/get',
            'contentType': 'application/json',
            'data': clinicId=cId,
            'dataType': 'json',
            'success': function(data){
                if(data==null){
                    var sex;
                    for (var i = 0; i < setData.length; i++) {
                        if (setData[i].value == parent.clinicMaster.sex) {
                            sex = setData[i].label;
                        }
                    }
                    $("#name").textbox("setValue",parent.clinicMaster.name);
                    $("#sexId").combobox("setValue",sex);
                    $("#age").textbox("setValue",parent.clinicMaster.age);
                    $("#patientId").val(patientId);
                    $("#clinicId").val(parent.clinicMaster.id);
                    $("#visitDept").combobox("setValue",clinicDeptCodeFormatter(parent.clinicMaster.visitDept,'',''));//入院科室
                }else{
                    $('#patHospitalForm').form('load',data);
                    $("#maritalStatus").combobox('setValue',marriageFormatter(data.maritalStatus,'',''))
                    $("#relationship").combobox('setValue',relationshipFormatter(data.relationship,'',''));
                    $("#patAdmCondition").combobox('setValue',admissionFormatter(data.patAdmCondition,'',''));
                }

              /*  $("#maritalStatus ").combobox('setValue',data.maritalStatus);
                $("#patAdmCondition ").combobox('setValue',data.patAdmCondition);*/
            }
        });





    $("#maritalStatus").combobox({
        data:marriageDict,
        valueField:'value',
        textField:'label',
        onSelect:function(data){
          if(data==null || data =="" || data=="undefined"){
              $("#maritalStatusId").val("");
          }else{
              $("#maritalStatusId").val(data.value);
          }

        }
    })

    /**
     * 关系
     */
    $("#relationship").combobox({
        data: relationshipDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#relationship").combobox('select',setData[0].value);

    /**
     * 入院情况
     */

    $("#patAdmCondition").combobox({
        data: admissionDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#patAdmCondition").combobox('select',setData[0].value);




}
/**
 * 保存住院通知单
 * @param id
 */
function savePatHospitalNotice() {
    $.ajax({
        url: basePath+"/diagnosis/findListOfOut",
        type: "GET",
        dataType: "json",
        data: {"clinicId":cId},
        success: function (data) {
            if (data != "" && data != null) {
    $.postForm(basePath + "/patHospitalNotice/save", "patHospitalForm", function (data) {
        if (data.data == "success") {
            $.messager.alert("提示信息", "保存成功");
            $.ajax({
                'type': 'post',
                'url': basePath+'/patHospitalNotice/get',
                'contentType': 'application/json',
                'data': clinicId=cId,
                'dataType': 'json',
                'success': function(data){
                    $('#patHospitalForm').form('load',data);
                    $("#maritalStatus ").combobox('select',data.maritalStatus);
                    $("#patAdmCondition ").combobox('select',data.patAdmCondition);
                }
            });
        } else {
            $.messager.alert("提示信息", "保存失败", "error");
        }

    }), function (data) {
        $.messager.alert("提示信息", "网络异常", "error");
    }
            }else{
                $.messager.alert("提示信息", "病人没有诊断信息，不能保存出院通知单", "warning");
            }
        }});
}





