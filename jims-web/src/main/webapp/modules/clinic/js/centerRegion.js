
$(function(){
    patientList('0','');
    /**
     * 科室下拉框
     */
    $('#deptNameId').combobox({
        data: clinicDeptCode,
        valueField: 'id',
        textField: 'dept_name',
        onChange: function () {
            var status=$('#wrap input[name="rad05"]:checked ').val();
            var dept=$('#deptNameId').combobox('getValue');
            patientList(status,dept);
        }
    })
    //添加Tabs
    $(".tabs-header").bind('contextmenu',function(e){
        e.preventDefault();
        $('#rcmenu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    });
    // 刷新当前标签页
    $("#refresh").bind("click",function(){
        var currTab = $('#tabs-header').tabs('getSelected'); //获得当前tab
        var frameObj=$("iframe",currTab);
        $(frameObj).attr("src", $(frameObj).attr("src"))
        //var url = $(currTab.panel('options').content).attr('src');
        //currTab.panel('refresh', url);
    });

    //关闭当前标签页
    $("#closecur").bind("click",function(){
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        $('#tabs-header').tabs('close',index);
    });
    //关闭所有标签页
    $("#closeall").bind("click",function(){
        //var tablist = $('#tabs-header').tabs('tabs');
        //for(var i=tablist.length-1;i>=0;i--){
        //    $('#tabs-header').tabs('close',i);
        //}
        closeTabs();
    });
    //关闭非当前标签页（先关闭右侧，再关闭左侧）
    $("#closeother").bind("click",function(){
        var tablist = $('#tabs-header').tabs('tabs');
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#tabs-header').tabs('close',i);
        }
        var num = index-1;
        for(var i=num;i>=0;i--){
            $('#tabs-header').tabs('close',0);
        }
    });
    //关闭当前标签页右侧标签页
    $("#closeright").bind("click",function(){
        var tablist = $('#tabs-header').tabs('tabs');
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#tabs-header').tabs('close',i);
        }
    });
    //关闭当前标签页左侧标签页
    $("#closeleft").bind("click",function(){
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        var num = index-1;
        for(var i=0;i<=num;i++){
            $('#tabs-header').tabs('close',0);
        }
    });
});
function closeTabs(){
    var tablist = $('#tabs-header').tabs('tabs');
    for(var i=tablist.length-1;i>=0;i--){
        $('#tabs-header').tabs('close',i);
    }
}

/**
 * tabs 增加
 * @param id
 * @param name
 * @param url
 * @param lia
 */
function addTabs(id,name,url,lia){
    $(lia).parent().parent().find("li a").removeClass();
    $(lia).addClass("active");
    var content = '<iframe  src="'+url+'" frameborder="0" border="0" scrolling="yes" marginheight="0" marginwidth="0" width="100%" height="99.5%"></iframe>';
    if(!$("#tabs-header").tabs('exists',name)){
        $('#tabs-header').tabs('add',{
            id:id,
            title: name,
            selected: true,
            content:content,
            //href:url,
            closable:true
        });
    }else $('#tabs-header').tabs('select',name);
}

/**
 * 显示DIV
 * @param id
 */
function showDiv(targetid,objN){
    var d=$("#"+targetid);
    var sb=$("#"+objN);
    if (d.css('display')=="none"){
        d.show();
        sb.html("<img src='/static/images/index/up-icon.png' class='show-hid-img'/>&nbsp;收缩")

    } else {
        d.hide();
        sb.html("<img src='/static/images/index/down-icon.png' class='show-hid-img'/>&nbsp;展开")
    }
}
/**
 * 显示医生
 * @param id
 */
function showDoctor(targetid){
    var d=$("#"+targetid);
    if (d.css('display')=="none"){
        d.show();

    } else {
        d.hide();
    }
}
//加载病人列表  默认 我的病人（待诊）
function patientList(status,dept){
    var liHtml='';
    var url='';
    if(status=='0'){
        url=basePath + '/clinicMaster/clinicMasterList?deptName='+dept;
    }else{
        url=basePath + '/clinicMaster/clinicMasterDiagnosed?deptName='+dept;
    }
    $.get(url, function (data) {
        for (var i = 0; i < data.length; i++) {
            liHtml+='<li><a href="#" onclick="userMenu(\''+data[i].id+'\',this)">' +
            '<span class="cus-lbor"></span>' +
            '<span class="cus-name">'+data[i].name+'</span>' ;
            var sex="";
                if(data[i].sex=='1'){
                    sex="女";
                }else if(data[i].sex=='2'){
                    sex="男";
                }
            liHtml=liHtml+'&nbsp;'+sex+'&nbsp; '+data[i].age+'</a></li>';
        }
        $('ul.cus-list').html(liHtml);
    })
}
/**
 * 获取病人的就诊信息
 * @param clinicMasterId
 */
function userMenu(clinicMasterId,aBtn){
    $(aBtn).parent().parent().find("li a").removeClass();
    $(aBtn).addClass("active");
    closeTabs();
    $.ajax({
        'type': 'get',
        'url': basePath + '/clinicMaster/get',
        'contentType': 'application/json',
        'data': {id:clinicMasterId},
        'dataType': 'json',
        'success': function(data){
            $("#nameId").html(data.name);
            $("#ageId").html(data.age);
            $("#sexId").html(data.sex);
            $("#clinicMasterId").val(data.id);
        },
        'error': function(){

        }
    })
    var html='';
    html+='<li><a class="active" onclick="addTabs(\'3\',\'病人信息\',\'/modules/clinic/patientInfo.html\',this)"><span>病人信息</span></a></li>';
    html+='<li><a  onclick="addTabs(\'2\',\'病历文书\',\'/modules/clinic/enterHospital/enterHosptial.html\',this)"><span>病历文书</span></a></li>';
    //html+='<li><a   onclick="addTabs(\'9\',\'诊断\',\'/modules/clinic/emrDiagnosis/diagnosis.html\',this)"><span>诊断</span></a></li>';
    html+='<li><a onclick="addTabs(\'4\',\'检查申请\',\'/modules/doctor/clinicInspect/clinicInspect.html\',this)"><span>检查申请</span></a></li>';
    html+='<li><a  onclick="addTabs(\'6\',\'检验申请\',\'/modules/clinic/lab/labTest.html\',this)"><span>检验申请</span></a></li>';
    html+='<li><a onclick="addTabs(\'7\',\'处方\',\'/modules/clinic/prescription/prescriptionList.html\',this)"><span>处方</span></a></li>';
    html+='<li><a onclick="addTabs(\'10\',\'用血申请\',\'/modules/clinic/docUseBlood/docUseBloodList.html\',this)" ><span>用血申请</span></a></li>';
   // html+='<li><a   onclick="addTabs(\'10\',\'手术预约\',\'/modules/operation/operationOrder.html\',this)"><span>手术预约</span></a></li>';
   // html+='<li><a   onclick="addTabs(\'10\',\'住院诊断\',\'/modules/clinic/emrDiagnosis/inDiagnosis.html\',this)"><span>住院诊断</span></a></li>';
    html+='<li><a   onclick="addTabs(\'13\',\'手术申请\',\'/modules/clinic/operationApply/operationApplyList.html\',this)"><span>手术申请</span></a></li>';
    html+='<li><a   onclick="addTabs(\'14\',\'发药查询\',\'/modules/clinic/outDispensing/queryConfirmDrug.html\',this)"><span>发药查询</span></a></li>';
    html+='<li><a   onclick="addTabs(\'14\',\'住院通知单\',\'/modules/clinic/notice/patHospitalNoticeList.html\',this)"><span>住院通知单</span></a></li>';
    $("#userMenuId").html(html);
    $("#userMenuId li:first a").click();
}




