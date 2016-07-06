$(function () {
    var inpCount;
    var patientId = parent.patVisit.patientId;
    var visitId = parent.patVisit.visitId;
    $("#patientId").val(patientId);
    $("#visitId").val(visitId);
    $('#ruyuanshijian').datebox('setValue', parent.patVisit.admissionDateTime);
        $.ajax({
            'type': 'post',
            'url': basePath+'/electronleavehospital/get',
            'contentType': 'application/json',
            'data':JSON.stringify({"patientId": patientId,"visitId":visitId}),
            'dataType': 'json',
            'success': function(data){
              /*  if(data.chuyuanshijian!=null){
                    inpCount =getOffDays(parseToDate(parent.patVisit.admissionDateTime),parseToDate(data.chuyuanshijian));
                }else{
                    inpCount =getOffDays(parseToDate(parent.patVisit.admissionDateTime),new Date());
                }
                alert(parent.patVisit.admissionDateTime+"------"+data.chuyuanshijian);*/
                $('#form').form('load',data);
               /* $("#inpCount").val(inpCount);*/
                getDiv("form");
            }
        });




})

function save(){
    formSubmitInput("form");
    $.postForm(basePath+"/electronleavehospital/save","form",function(data){
        if(data.data=="success"){
            $.messager.alert("提示信息","保存成功");
        }else{
            $.messager.alert("保存失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","保存失败","error");
    }
}

var getOffDays = function(startDate, endDate) {

    //得到时间戳相减 得到以毫秒为单位的差

    var mmSec = (endDate.getTime() - startDate.getTime());

    //单位转换为天并返回

    return (mmSec / 3600000 / 24);

};