
//加载门诊号别
$(function(){
    /**
     * 科室下拉框
     */
    $('#deptNameId').combobox({
        data: clinicDeptCode,
        valueField: 'id',
        textField: 'dept_name'
    })
    /**
     * 就诊科室
     */
    $('#visitDeptId').combobox({
        data: clinicDeptCode,
        valueField: 'id',
        textField: 'dept_name'
    })
    $("#visitDeptId").combobox('select',clinicDeptCode[0].id);
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
     * 费别下拉框
     */
    $('#chargeTypeId').combobox({
        data: chargeType,
        valueField: 'id',
        textField: 'charge_type_name'
    })
    $("#chargeTypeId ").combobox('select',chargeType[0].id);
    /**
     * 诊别下拉框
     */
    $('#clinicTypeId').combobox({
        data: chargeTypeDict,
        valueField: 'value',
        textField: 'label'
    })
    $("#clinicTypeId ").combobox('select',chargeTypeDict[0].value);
    /**
     * 身份下拉框
     */
    $('#identityId').combobox({
        data: identityDict,
        valueField: 'id',
        textField: 'identityName'
    })
    $("#identityId ").combobox('select',identityDict[0].id);
    /**
     * 合同单位下拉框
     */
    $('#companyId').combobox({
        data: unitContract,
        valueField: 'id',
        textField: 'unitName'
    })
})
//获取挂号
function getClinicForRegist(){
    var liHtml="";
    var deptName=$("#deptNameId").combobox('getValue');
    var clinicTypeName=$("#clinicTypeNameId").val();
    var clinicDate=$("#clinicDateId").datebox('getValue');
    /**
     * 获取号表
     */
    $.postJSON(basePath+'/clinicRegister/findListReg',"{\"clinicDept\":\""+deptName+"\",\"clinicDate\":\""+clinicDate+"\",\"clinicLabelName\":\""+clinicTypeName+"\"}",function(data){
        liHtml+='<ul>';
        for(var i=0;i<data.length;i++){
            liHtml+= '<li onclick="centerTypeActive(this)" input_id="liClinicLabel'+i+'">'+
            '<div>' +
            '<strong>'+data[i].clinicLabelName+'</strong>' +
            ' <input type="hidden" value="'+data[i].id+'" class="simInput" >'+
            ' <input type="hidden" value="'+data[i].price+'" input_hidden="liClinicLabel'+i+'" >'+
            '</div>' +
            '<span style="padding-right:10px;">'+timeDescFormatter(data[i].timeDesc)+'</span>';
            var appointmentNum=0;
            var registrationLimits="";
            if(data[i].appointmentNum!=null){
                appointmentNum=data[i].appointmentNum;
            }
            if(data[i].registrationLimits!='0'){
                registrationLimits=data[i].registrationLimits
            }
            liHtml=liHtml+'<a href="#" class="color-red">'+appointmentNum+'</a>/'+registrationLimits+
            '<span class="color-blue" style="padding-left:10px;">'+clinicDeptCodeFormatter(data[i].clinicDept, '', '')+'</span>' +
            '</li>' ;
        }
        liHtml+='</ul>';
        $("#registerCenter").html(liHtml);

    },function(){
        $.messager.alert("提示信息","网络连接失败");
    });
}


function centerTypeActive(li){
    var classLi=$(li).attr("class");
    if(classLi=='active'){
        $(li).removeClass();
    }else{
        $(li).attr("class","active");
    }
}
//预约挂号 ---
function appointsRegister(){
  // if($("#patMasterInfo").form("validate")) {
        var tableJson = "[";
        $('#registerCenter li.active input.simInput').each(function (index, element) {
            tableJson += '{"id":"' + $(this).val() + '"},';
        });
        tableJson = tableJson.substring(0, tableJson.length - 1);
        tableJson += "]";
        var formJson = fromJson('patMasterInfo');
        formJson = formJson.substring(0, formJson.length - 1);
        var submitJson = formJson + ",\"clinicForRegistList\":" + tableJson + "}";
        alert(submitJson);
        $.postJSON(basePath + '/clinicAppoints/saveAppoints', submitJson, function (data) {
            if (data.code == "1") {
                $.messager.alert("提示信息", "预约成功");
                $("#patMasterInfo").form('clear');
                getClinicForRegist();
            } else {
                $.messager.alert("提示信息", "预约失败", "error");
            }

        }, function (data) {
            $.messager.alert("提示信息", "预约失败", "error");
        })
   // }
}