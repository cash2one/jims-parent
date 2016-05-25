$(function(){
    var liHtml="";
    $.get(basePath+'/clinicRegister/findListReg',function(data){//加载号别
          liHtml+='<ul>';
         for(var i=0;i<data.length;i++){
          liHtml+= '<li onclick="centerTypeActive(this)">'+
                  '<div>' +
                  '<strong>'+data[i].clinicLabel+'</strong>' +
                  ' <input type="hidden" value="'+data[i].id+'" class="simInput" >'+
                  '</div>' +
                  '<span style="padding-right:10px;">'+data[i].timeDesc+'</span>' +

                  '<a href="#" class="color-red">1</a>/5' +
                  '<span class="color-blue" style="padding-left:10px;">'+data[i].inputCode+'</span>' +
                  '</li>' ;

         }
        liHtml+='</ul>';
        $("#clinicIndex").html(liHtml);

    });
})
/**
 * 发开dialog
 * @param id
 * @param name
 */
//挂号
function openDialog(id,name){
    $("#"+id).dialog({title: name}).dialog("open");
    var lis = $('#clinicIndex li.active');
    var labelHtml="";
    for(var i=0;i<lis.length;i++){
         var li=lis[i];
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
            $.messager.alert("提示信息","保存成功");
            window.location.reload();
        }else{
            $.messager.alert("提示信息","保存失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","保存失败","errorzn");
    }
}
//根据条件查询退号信息
function searchReturn(){
    var visitDate =$("#visitDate").datebox('getValue');
    var visitNo=$("#visitNo").val();
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
        $.messager.alert("提示信息","该病人已经退号请勿重复操作");
        return;
    }
    $.messager.confirm("确认消息", "确认进行退号？", function () {
        $.messager.alert("提示信息","请退还病人"+charge+"元");
        $.post(basePath+'/clinicReturned/returnedAcct?id='+clinicId,function(data){
            if(data.code=="1"){
                $.messager.alert("提示信息","退号成功");
               // window.location.reload();
                searchReturn();
            }else{
                $.messager.alert("提示信息","退号失败","error");
            }

        }),function(data){
            $.messager.alert("提示信息","退号失败","errorzn");
        }
    })

}




