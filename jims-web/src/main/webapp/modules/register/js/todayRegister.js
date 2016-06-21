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
    var liHtml="";
    /**
     * 获取号表
     */
    $.get(basePath+'/clinicRegister/findListReg?status='+'当日',function(data){//加载号别
          liHtml+='<ul>';
         for(var i=0;i<data.length;i++){
          liHtml+= '<li onclick="centerTypeActive(this)" input_id="liClinicLabel'+i+'">'+
                  '<div>' +
                  '<strong>'+data[i].clinicLabelName+'</strong>' +
                  ' <input type="hidden" value="'+data[i].id+'" class="simInput" >'+
                    ' <input type="hidden" value="'+data[i].price+'" input_hidden="liClinicLabel'+i+'" >'+
                  '</div>' +
                  '<span style="padding-right:10px;">'+timeDescFormatter(data[i].timeDesc)+'</span>' +

                  '<a href="#" class="color-red">1</a>/5' +
                  '<span class="color-blue" style="padding-left:10px;">'+data[i].inputCode+'</span>' +
                  '</li>' ;

         }
        liHtml+='</ul>';
        $("#clinicIndex").html(liHtml);

    });
})

//挂号
function openDialog(id,name){
    $("#"+id).dialog({title: name}).dialog("open");
    var lis = $('#clinicIndex li.active');
    var price=0;
    var labelHtml="";
    for(var i=0;i<lis.length;i++){
         var li=lis[i];
         var inputName=$(li).attr("input_id");
        //price=price+$("#")
         var clinicLabel=lis[i].getElementsByTagName("strong")[0].innerHTML;
         labelHtml+='<tbody>' +
         '<tr>' +
         ' <td name="clinicLabel" class="easyui-validatebox">'+clinicLabel+'</td>' +
         '</tr>' +
         '</tbody>';
    }
    $("#clinicLabe").html(labelHtml);

}
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




