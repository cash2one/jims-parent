var clinicId = parent.clinicMaster.id;
var patientId = parent.clinicMaster.patientId;
function onloadMethod() {
    $("#name").val(parent.clinicMaster.name);
    $("#sex").val(sexFormatter(parent.clinicMaster.sex,'',''));
    $("#age").val(parent.clinicMaster.age);
    $("#patientId").val(patientId);
        $("#masterId").val(parent.clinicMaster.id);
    //$("#visitDate").val(parent.clinicMaster.visitDate);//入院时间
    //$("#visitDept").val(clinicDeptCodeFormatter(parent.clinicMaster.visitDept,'',''));//入院科室
    $("#chargeType").val(itemFormatter(parent.clinicMaster.chargeType,'',''));//费别
    $.ajax({
        'type': 'GET',
        'url':basePath + '/bloodApply/getPatient',
        'contentType': 'application/json',
        'data': {id : 1},
        'dataType': 'json',
        'success': function(data){
            //$("#name").val(data.name);
            //$("#sex").val(sexFormatter(data.sex,'',''));
            //$("#sexId").val(data.sex);
            //$("#age").val(data.age);
            //$("#patientId").val(data.patientId);
            $("#masterId").val(data.id);
            var date = data.patMaster.dateOfBirth;
            var year=date.substr(0,10);
            $("#dateOfBirth").val(year);//出生日期
            $("#idNo").val(data.patMaster.idNo);//身份证
            $("#nation").val(nationFormatter(data.patMaster.nation));//名族
            $("#nationId").val(data.patMaster.nation);
            $("#birthPlace").val(data.patMaster.mailingAddress);//出生地
            $("#serviceAgencyPhone").val(data.patMaster.phoneNumberHome);//本人联系方式
            $("#relationship").val(data.patMaster.relationship);//联系人关系
            $("#nextOfKin").val(data.patMaster.nextOfKin);//联系人
            $("#nextOfIdNo").val(data.patMaster.nextOfIdNo);//联系人身份证
            $("#nextOfKinAddr").val(data.patMaster.nextOfKinAddr);//联系人地址
            $("#nextOfKinPhone").val(data.patMaster.nextOfKinPhone);//联系人电话
            $("#nextOfNation").val(nationFormatter(data.patMaster.nextOfNation));//联系人名族
            //$("#visitDate").val(data.visitDate);//入院时间
            $("#visitDept").val(clinicDeptCodeFormatter(data.visitDept,'',''));//入院科室
            $("#chargeType").val(itemFormatter(data.chargeType,'',''));//费别
            $("#enterDate").datetimebox("setValue",new Date);

        }
    })

    $("#maritalStatus").combobox({
        data:marriageDict,
        valueField:'value',
        textField:'label',
        onSelect:function(data){
            $("#maritalStatusId").val(data.value);
        }
    })

    $("#patAdmCondition").combobox({
        data:situation,
        valueField:'value',
        textField:'label',
        onSelect:function(data){
            $("#patAdmConditionId").val(data.value);
        }
    })


}
/**
 * 保存住院通知单
 * @param id
 */
function savePatHospitalNotice() {
    $.postForm(basePath + "/patHospitalNotice/save", "patHospitalForm", function (data) {
        if (data.code == "1") {
            $.messager.alert("提示信息", "保存成功");

            $("#patHospitalForm").form("clear");
        } else {
            $.messager.alert("提示信息", "保存失败", "error");
        }

    }), function (data) {
        $.messager.alert("提示信息", "保存失败", "error");
    }
}

//列删除
function deleteRow(id) {
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            del(id);
        }
    })
}


/**
 * 修改住院通知单
 * @param id
 */
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
/**
 * 查看住院通知单
 * @param id
 */
function look(id){
    $("#savePatHospitalNotice").hide();
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


