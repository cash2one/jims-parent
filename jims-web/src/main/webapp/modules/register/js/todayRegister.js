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
    alert(tableJson);
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
        $.messager.alert("提示信息","保存失败","error");
    }
}




