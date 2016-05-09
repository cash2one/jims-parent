patientList('0');
$(function(){
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
        var tablist = $('#tabs-header').tabs('tabs');
        for(var i=tablist.length-1;i>=0;i--){
            $('#tabs-header').tabs('close',i);
        }
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
    var content = '<iframe  src="'+url+'" frameborder="0" scrolling="yes" marginheight="0" marginwidth="0" width="100%" height="99%"></iframe>';
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
function patientList(status){
    var liHtml='';
    if(status=='0'){
        $.get(basePath + '/clinicMaster/clinicMasterList', function (data) {
            for (var i = 0; i < data.length; i++) {
                liHtml+='<li><a href="#">' +
                '<span class="cus-lbor"></span>' +
                '<span class="cus-name">'+data[i].name+'</span>' +
                ''+data[i].sex+'&nbsp; '+data[i].age+'</a></li>';
            }
            $('ul.cus-list').html(liHtml);
        })
    }else if(status=='1'){
        $.get(basePath + '/clinicMaster/clinicMasterDiagnosed', function (data) {
            for (var i = 0; i < data.length; i++) {
                liHtml+='<li><a href="#">' +
                '<span class="cus-lbor"></span>' +
                '<span class="cus-name">'+data[i].name+'</span>' +
                ''+data[i].sex+'&nbsp; '+data[i].age+'</a></li>';
            }
            $('ul.cus-list').html(liHtml);
        })
    }


}




