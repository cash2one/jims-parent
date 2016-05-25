/*$(function(){
    var bodyHeight= $(window).height();
    $("#registerCenter").height(bodyHeight*0.5);
})*/
//加载门诊号别
$(function(){
    var liHtml="";
    $.get(basePath+'/clinicRegister/findListReg?status='+'预约',function(data){//加载号别
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
        $("#registerCenter").html(liHtml);

    });
})
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
    var tableJson="[";
    $('.simInput').each(function (index, element) {
        tableJson+='{"id":"'+$(this).val()+'"},';
    })
    tableJson = tableJson.substring(0, tableJson.length - 1);
    tableJson+="]";
    var formJson=fromJson('patMasterInfo');
    formJson = formJson.substring(0, formJson.length - 1);
    var submitJson=formJson+",\"clinicForRegistList\":"+tableJson+"}";
    alert(submitJson);
    $.postJSON(basePath+'/clinicAppoints/saveAppoints',submitJson,function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","预约成功");
            $("#patMasterInfo").form('clear');
        }else{
            $.messager.alert("提示信息","预约失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","预约失败","errorzn");
    }
}