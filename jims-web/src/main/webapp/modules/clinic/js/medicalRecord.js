var patientId = parent.patVisit.patientId;
$(function(){
    $("#nation").combobox({//民族
        data: nationDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#occupation").combobox({//职业
        data: professionDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#maritalStatus").combobox({//婚姻状态
        data: marriageDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#citizenship").combobox({//国籍
        data: nationalityDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#relationship").combobox({//关系
        data: relationshipDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#patientClass").combobox({//入院方式
        data: patientClass,
        valueField: 'value',
        textField: 'label'
    })
    $("#dept").combobox({//科室
        data: clinicDeptCode,
        valueField: 'value',
        textField: 'label'
    })
    $("#deptDisFrom").combobox({
        data: clinicDeptCode,
        valueField: 'value',
        textField: 'label'
    })

    $.get( basePath+'/medicalRecord/getMedicalRecInfo?patientId='+patientId,function(data){
        $("#baseInfoId").val(data.patMasterIndex.id);
        $("#patId").val(data.patVisit.id);
        $("#baseInfo").form('load',data.patMasterIndex);
        $("#inHosInfo").form('load',data.patVisit);
        $("#visitOtherInfo").form('load',data.patVisit);
        $("#inpCosts").form('load',data.InpBillDetailDto);

    });
});
//保存其他信息
function saveOtherInfo(){
    var formJson=fromJson('visitOtherInfo');
    $.postJSON(basePath+'/medicalRecord/updateOtherInfo?patientId='+patientId,formJson,function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","保存成功");
            $("#visitOtherInfo").form('visitOtherInfo');
        }else{
            $.messager.alert("提示信息","保存失败","error");
        }
    });
}
//保存基本信息
function baseInfo(){
    var formJson=fromJson('baseInfo');
    $.postJSON(basePath+'/medicalRecord/updateOtherInfo?patientId='+patientId,formJson,function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","保存成功");
            $("#visitOtherInfo").form('visitOtherInfo');
        }else{
            $.messager.alert("提示信息","保存失败","error");
        }
    });
}





