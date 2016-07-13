var wardCode='160101';
$(function(){
    var html = '<ul>';
    $.ajax({
        method:"POST",
        dataType: 'json',
        contentType: 'application/json',
        data:wardCode=wardCode,
        url: basePath + '/bedRec/getAllBed',
        success: function (data) {
            if(data!=null){
            $.each(data,function(id,item){ //循环对象取值
                html = html+'<li class="bgcolor-green"  onmouseup="showMenu(event);" style="background: #CDCDB4">  ' ;
                html = html+'<div class="bednum-cusinf"> <span class="bednum-val">'+item.bed_no1+'</span> <span class="bednum-name">'+(item.name==null?"":item.name)+'</span>  <span class="bednum-age">'+(item.age==null?"":item.name)+'岁</span></div>';
                html = html+'<table cellpadding="0" cellspacing="0" border="0" width="100%" class="bn-cusinfo-tab">';
                html = html+'<tr><td>性别：</td> <td >'+(item.sex==null?"":item.sex)+'</td></tr>';
                html = html+'<tr><td>ID：</td> <td >'+(item.patient_id==null?"":item.patient_id)+'</td></tr>';
                html = html+'<tr><td>诊断：</td> <td >'+(item.diagnosis==null?"":item.diagnosis)+'</td></tr>';
                html = html+'<tr><td>入院日期：</td> <td >'+(item.admission_date_time==null?"":item.name)+'</td></tr>';
                html = html+'<tr><td>医生：</td> <td >'+(item.doctor_in_charge==null?"":item.doctor_in_charge)+'</td></tr>';
                html = html+'<tr><td>护理等级：</td> <td >'+(item.nursing_class==null?"":item.nursing_class)+'</td></tr>';
                html = html+'<tr><td>住院号：</td> <td >'+(item.visit_id==null?"":item.visit_id)+'</td></tr>';
                html = html+'<tr><td>费别：</td> <td >'+(item.charge_type==null?"":item.charge_type)+'</td></tr>';
                html = html+'</table>';
                html = html +'</li>';
            });
                html= html+" </ul>";
                $(".bednum-main").append(html);
            }else{
                $(".easyui-layout").append('<div ><div >该病区下暂无床位信息!</div></div>')
            }

        }
    });
});

function showMenu(e) {
    document.oncontextmenu = function(e){
        e.preventDefault();
    };
    if (e.button == 2) {
        $('#menuId').menu('show', {
            left: e.pageX,//在鼠标点击处显示菜单
            top: e.pageY
        });
    }
return false;
}


function getVisitId(visitId){
    $("#visitId").val(visitId);
}

























