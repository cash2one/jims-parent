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
                }

              /*  $("#maritalStatus ").combobox('setValue',data.maritalStatus);
                $("#patAdmCondition ").combobox('setValue',data.patAdmCondition);*/
            }
        });



    /**
     * 查看住院通知单
     * @param id
     */


    //$("#visitDate").val(parent.clinicMaster.visitDate);//入院时间
    //$("#visitDept").val(clinicDeptCodeFormatter(parent.clinicMaster.visitDept,'',''));//入院科室
    //$("#chargeType").val(itemFormatter(parent.clinicMaster.chargeType,'',''));//费别
 /*   $.ajax({
        'type': 'GET',
        'url':basePath + '/bloodApply/getPatient',
        'contentType': 'application/json',
        'data': {id : 1},
        'dataType': 'json',
        'success': function(data){
            $("#name").textbox("setValue",data.name);
            //$("#sex").val(sexFormatter(data.sex,'',''));
            $("#sexId").combobox("setValue",itemFormatter(data.sex));
            //$("#age").val(data.age);
            //$("#patientId").val(data.patientId);
            $("#masterId").val(data.id);
          *//*  var date = data.patMaster.dateOfBirth;
            var year=date.substr(0,10);
            $("#dateOfBirth").val(year);//出生日期*//*
         //   $("#idNo").val(data.patMaster.idNo);//身份证
          *//*  $("#nation").val(nationFormatter(data.patMaster.nation));//名族
            $("#nationId").val(data.patMaster.nation);
            $("#birthPlace").val(data.patMaster.mailingAddress);//出生地
            $("#serviceAgencyPhone").val(data.patMaster.phoneNumberHome);//本人联系方式
            $("#relationship").val(data.patMaster.relationship);//联系人关系
            $("#nextOfKin").val(data.patMaster.nextOfKin);//联系人
            $("#nextOfIdNo").val(data.patMaster.nextOfIdNo);//联系人身份证
            $("#nextOfKinAddr").val(data.patMaster.nextOfKinAddr);//联系人地址
            $("#nextOfKinPhone").val(data.patMaster.nextOfKinPhone);//联系人电话
            $("#nextOfNation").val(nationFormatter(data.patMaster.nextOfNation));//联系人名族
            //$("#visitDate").val(data.visitDate);//入院时间*//*
            $("#visitDept").combobox("setValue",clinicDeptCodeFormatter(data.visitDept,'',''));//入院科室
           *//* $("#chargeType").val(itemFormatter(data.chargeType,'',''));//费别
            $("#enterDate").datetimebox("setValue",new Date);*//*

        }
    })*/

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

    $("#patAdmCondition").combobox({
        data:admissionDict,
        valueField:'value',
        textField:'label',
        onSelect:function(data){
            if(data==null || data =="" || data=="undefined"){
                $("#patAdmConditionId").val("");
            }else{
                $("#patAdmConditionId").val(data.value);
            }

        }
    })


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
        $.messager.alert("提示信息", "保存失败", "error");
    }
            }else{
                $.messager.alert("提示信息", "病人没有诊断信息，不能保存出院通知单", "warning");
            }
        }});
}




/**
 * 修改住院通知单
 * @param id
 */
/*
function get(id){
    $("#savePatHospitalNotice").show();
    $.ajax({
        'type': 'post',
        'url': basePath+'/patHospitalNotice/get',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            $('#patHospitalForm').form('load',data);
            $("#maritalStatus ").combobox('select',data.maritalStatus);
            $("#patAdmCondition ").combobox('select',data.patAdmCondition);
        }
    });
}
*/



