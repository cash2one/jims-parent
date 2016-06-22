var timeInterval=[];


/**
 * 时间
 * @type {{}}
 */
var timeIntervalData={};
timeIntervalData.orgId="";
timeIntervalData.dictType="TIME_INTERVAL_DICT"
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(timeIntervalData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        timeInterval=data;
    }
});

/**
 * 时间翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string}
 */
function timeDescFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < timeInterval.length; i++) {
        if (timeInterval[i].time_interval_code == value) {
            return timeInterval[i].time_interval_name;
        }
    }
}
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
     * 性别下拉框
     */
    $('#setId').combobox({
        data: setData,
        valueField: 'value',
        textField: 'label'
    })
    /**
     * 费别下拉框
     */
    $('#chargeTypeId').combobox({
        data: chargeType,
        valueField: 'id',
        textField: 'charge_type_name'
    })
    /**
     * 诊别下拉框
     */
    $('#clinicTypeId').combobox({
        data: chargeTypeDict,
        valueField: 'value',
        textField: 'label'
    })

    /**
     * 诊别下拉框
     */
    $('#identityId').combobox({
        data: identityDict,
        valueField: 'id',
        textField: 'identityName'
    })



    getClinicForRegist();

})
//获取挂号
function getClinicForRegist(){
    var liHtml="";
    var deptName=$("#deptNameId").combobox('getValue');
    var clinicTypeName=$("#clinicTypeNameId").val();
    var clinicDate=new Date().format('yyyy-MM-dd');
    //var data={};
    //data.clinicDept=deptName;
    //data.clinicDate=clinicDate;
    //data.clinicLabelName=clinicTypeName;
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
            var registrationNum=0;
            var registrationLimits="";
            if(data[i].registrationNum!=null){
                registrationNum=data[i].registrationNum;
            }
            if(data[i].registrationLimits!='0'){
                registrationLimits=data[i].registrationLimits
            }
            liHtml+='<a href="#" class="color-red">'+registrationNum+'</a>/'+registrationLimits+
            '<span class="color-blue" style="padding-left:10px;">'+clinicDeptCodeFormatter(data[i].clinicDept, '', '')+'</span>' +
            '</li>' ;
        }
        liHtml+='</ul>';
        $("#clinicIndex").html(liHtml);

    },function(){
        $.messager.alert("提示信息","网络连接失败");
    });
}
//挂号
function openDialog(id,name){
    $("#"+id).dialog({title: name}).dialog("open");
    var lis = $('#clinicIndex li.active');
    var price=0;
    var labelHtml="";
    for(var i=0;i<lis.length;i++){
         var li=lis[i];
         var inputName=$(li).attr("input_id");
         price= Number(price)+ Number($("input[input_hidden]").val());
         var clinicLabel=lis[i].getElementsByTagName("strong")[0].innerHTML;
         labelHtml+='<tbody>' +
         '<tr>' +
         ' <td name="clinicLabel" class="easyui-validatebox">'+clinicLabel+'</td>' +
         '</tr>' +
         '</tbody>';
    }
    $("#receiptsId").val(price);
    $("#receiptsHiddenId").val(price);
    $("#changeReceiptsId").val("");
    $("#clinicLabe").html(labelHtml);
}
function closeDialog(id){
    $("#"+id).dialog("close");
}

//改变实收触发
function onchangeInput(){
   var receipts=$("#receiptsId").val();
    var receiptsHidden=$("#receiptsHiddenId").val();
    if(receipts!=receiptsHidden){
        receipts=receipts-receiptsHidden;
        if(receipts>0){
            $("#changeReceiptsId").val(receipts);
        }else{
            $.messager.alert("提示信息","输入金额有误，请重新输入");
            $("#receiptsId").val(receiptsHidden);
            $("#changeReceiptsId").val("");
        }
    }else{
        $("#changeReceiptsId").val("");
    }
}
//选中号类
function centerTypeActive(li){
    var classLi=$(li).attr("class");
    if(classLi=='active'){
        $(li).removeClass();
    }else{
        $(li).attr("class","active");
    }
}
//保存信息
function saveClinic(){
    var tableJson="[";
    $('.simInput').each(function (index, element) {
        tableJson+='{"id":"'+$(this).val()+'"},';
    })
    tableJson = tableJson.substring(0, tableJson.length - 1);
    tableJson+="]";
    var formJson=fromJson('clinicForm');
    formJson = formJson.substring(0, formJson.length - 1);
    var submitJson=formJson+",\"clinicForRegists\":"+tableJson+"}";

    $.postJSON(basePath+"/clinicRegister/saveClinic",submitJson,function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","挂号成功");
            $("#clinicForm").form('clear');
            $("#chargeId").dialog({title: '挂号结果'}).dialog("close");
        }else{
            $.messager.alert("提示信息","挂号失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","挂号失败","errorzn");
    }
}
//根据条件查询退号信息
function searchReturn(){
    var visitDate =$("#visitDate").datebox('getValue');
    var visitNo=$("#visitNo").val();
    if(visitNo==null || visitNo==''){
        alert("请输入就诊序号进行查询");
        return;
    }
    $.get(basePath+'/clinicReturned/getClinicMaster?visitDate='+visitDate+'&visitNo='+visitNo,function(data){
        $("#regreteatInfo").form('load',data);
    });
}
//退号
function clinicReturnInfo(){
   /* var formJson=fromJson('regreteatInfo');
    alert(formJson);*/
    var clinicId=$("#clinicId").val();
    var charge=$("#charge").val();
    var returnDate=$("#returnDate").val();
    if(returnDate!=null && returnDate!=''){
        $.messager.alert("提示信息","已经退号，请勿重复退号");
        return;
    }
    $.messager.confirm("确认消息", "确认进行退号？", function () {
        alert("请退还病人"+charge+"元");
        $.post(basePath+'/clinicReturned/returnedAcct?id='+clinicId,function(data){
            if(data.code=="1"){
                $.messager.alert("提示信息","退号成功");
            }else{
                $.messager.alert("提示信息","退号失败","error");
            }

        }),function(data){
            $.messager.alert("提示信息","退号失败","errorzn");
        }
    })

}




