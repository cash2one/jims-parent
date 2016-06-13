var wardCode='160101';
$(function(){
    var html = '';
    $.ajax({
        method:"POST",
        dataType: 'json',
        contentType: 'application/json',
        data:wardCode=wardCode,
        url: basePath + '/bedRec/getAllBed',
        success: function (data) {
            $.each(data,function(id,item){ //循环对象取值
                html = html+'<li class="bgcolor-green">  ' ;
                html = html+'<div class="bednum-cusinf"> <span class="bednum-val">'+item.bed_no1+'</span> <span class="bednum-name">'+item.name+'</span>  <span class="bednum-age">'+item.age+'岁</span></div>';
                html = html+'<table cellpadding="0" cellspacing="0" border="0" width="100%" class="bn-cusinfo-tab">';
                html = html+'<tr><td>性别：</td> <td >'+item.sex+'</td></tr>';
                html = html+'<tr><td>ID：</td> <td >'+item.patient_id+'</td></tr>';
                html = html+'<tr><td>诊断：</td> <td >'+item.diagnosis+'</td></tr>';
                html = html+'<tr><td>入院日期：</td> <td >'+item.admission_date_time+'</td></tr>';
                html = html+'<tr><td>医生：</td> <td >'+item.doctor_in_charge+'</td></tr>';
                html = html+'<tr><td>护理等级：</td> <td >'+item.nursing_class+'</td></tr>';
                html = html+'<tr><td>住院号：</td> <td >'+item.visit_id+'</td></tr>';
                html = html+'<tr><td>费别：</td> <td >'+item.charge_type+'</td></tr>';
                html = html+'</table>';
                html = html +'</li>';
            });
            $(".bednum-main ul").append(html);
        }
    });




    $("#content").onmousedown = function(e){
        //去掉默认的contextmenu事件，否则会和右键事件同时出现。
        document.oncontextmenu = function(e){
            e.preventDefault();
            alert("你点了右键");
            if(e.button ==2){
                $('#menu').menu('show', {
                    left: e.pageX,//在鼠标点击处显示菜单
                    top: e.pageY
                });

            }else if(e.button ==0){
                alert("你点了左键");
            }else if(e.button ==1){
                alert("你点了滚轮");
            }
        }
    };

});






































