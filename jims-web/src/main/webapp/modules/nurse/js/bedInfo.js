var wardCode='160101';
$(function(){
    $.ajax({
        method:"POST",
        dataType: 'json',
        contentType: 'application/json',
        data:wardCode=wardCode,
        url: basePath + '/bedRec/getAllBed',
        success: function (data) {
            if(data!=null||data!=""){
                var html = '<ul>';
            $.each(data,function(id,item){ //循环对象取值
                if(item.nursing_class!=null){
                  if(item.nursing_class=='5'){//病危护理
                      html = html+'<li class="orange-tborder"  style="margin-right:10px; height: 200px;" onmouseup="showMenu(event);" >' ;
                    }else if(item.nursing_class=='4'){//病重护理
                      html = html+'<li class="red-tborder"  style="margin-right:10px; height: 200px;" onmouseup="showMenu(event);">' ;
                  }
                }else{
                    html = html+'<li class="blue-tborder"  style="margin-right:10px; height: 200px;" onmouseup="showMenu(event);">' ;
                }

                html = html+'<div class="customer-list-header">';
                html = html+'<span style="margin-right:10px;">'+item.bed_no1+'</span>'+(item.name==null?"":item.name);
                if(item.nursing_class!=null){
                  if(item.nursing_class=='1'){//一级护理
                      html = html+'<div class="star-zone" style="margin-left:10px;"><div class="star1"><div class="star2" style="width:20%"></div></div></div>';
                  }else if(item.nursing_class=='2'){//二级护理
                      html = html+'<div class="star-zone" style="margin-left:10px;"><div class="star1"><div class="star2" style="width:40%"></div></div></div>';
                  }else if(item.nursing_class=='3'){//三级护理
                      html = html+'<div class="star-zone" style="margin-left:10px;"><div class="star1"><div class="star2" style="width:60%"></div></div></div>';
                  }else if(item.nursing_class=='0'){//特及护理
                      html = html+'<div class="star-zone" style="margin-left:10px;"><div class="star2"></div></div>';
                  }else  if(item.nursing_class=='6') {//整体护理
                      html = html+'<div class="star-zone"><div class="star1"><div class="star2" style="width:80%"></div></div></div>' ;
                  }
                }else{//没有护理
                    html = html+'<div class="star-zone" style="margin-left:10px;"><div class="star1"></div></div>';
                }
                if(item.sex!=null){
                     if(item.sex=='1'){//女
                         if(item.age!=null){
                             if(0<item.age<14){//儿童
                                 html = html+'<div class="cus-sex-img"><img src="/static/images/index/girl.png" width="40" height="40"/></div></div>';
                             }else if(item.age>60){//老年人
                                 html = html+'<div class="cus-sex-img"><img src="/static/images/index/old-female.png" width="40" height="40"/></div></div>';
                             }else{
                                 html = html+'<div class="cus-sex-img"><img src="/static/images/index/female.png" width="40" height="40"/></div></div>';
                             }
                         }

                     }else if(item.sex=='2'){//男
                         if(item.age!=null){
                             if(0<item.age<14){//儿童
                                 html = html+'<div class="cus-sex-img"><img src="/static/images/index/boy.png" width="40" height="40"/></div></div>';
                             }else if(item.age>60){//老年人
                                 html = html+'<div class="cus-sex-img"><img src="/static/images/index/old-male.png" width="40" height="40"/></div></div>';
                             }else{
                                 html = html+'<div class="cus-sex-img"><img src="/static/images/index/male.png" width="40" height="40"/></div></div>';
                             }
                         }

                     }
                }else{
                    html = html+'<div class="cus-sex-img"></div></div>';
                }
                html = html+'<table cellpadding="0" cellspacing="0" border="0" width="100%" class="customer-list-tab">';
                html = html+'<tr><td>年龄：</td><td>'+(item.age==null?"":item.age)+'</td></tr>';
                html = html+'<tr><td>诊断：</td> <td >'+(item.diagnosis==null?"":item.diagnosis)+'</td></tr>';
                html = html+'<tr><td>入院日期：</td> <td >'+(item.admission_date_time==null?"":item.admission_date_time)+'</td></tr>';
                html = html+'<tr><td>医生：</td> <td >'+(item.doctor_in_charge==null?"":item.doctor_in_charge)+'</td></tr>';
                html = html+'<tr><td>费别：</td> <td >'+(item.charge_type==null?"":chargeTypeFormatter(item.charge_type))+'</td></tr>';
                html = html+'</table>';
                html = html +'</li>';
            });
                html= html+" </ul>";
                $(".customer-list-nstyle").append(html);
            }else{
                $(".customer-list-nstyle").append("<div class='nodata-wrap'><div  class='nodata-text'>该病区下暂无床位信息!</div></div>");
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

























