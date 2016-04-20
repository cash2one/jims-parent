$("head").append('<meta HTTP-EQUIV="pragma" CONTENT="no-cache">');
$("head").append('<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">');
$("head").append('<meta HTTP-EQUIV="expires" CONTENT="0">');
$("head").append('<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />');
$("head").append('<meta name="renderer" content="webkit">');
$("head").append('<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />');
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/css/index.css"}).appendTo("head");
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/circularNav/css/component2.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/locale/easyui-lang-zh_CN.js"}).appendTo("head");
basePath=getRootPath()+"/service";
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    return(localhostPaht);
}

//病历文书页面保存时需要的
function formSubmitInput(fromId){
    $("#"+btnUpdateId).remove();
    $(".check-history").remove();
    var zhuyuanId=$("#zhuyuanIdHidden").val();
    var patientId=$("#patientIdHidden").val();
    var zhuyuanHiddenId=$("#zhuyuanBasicInfoHiddenId").val();
    var patientHiddenId=$("#patientBasicInfoHiddenId").val();
    if(zhuyuanHiddenId!="" && zhuyuanHiddenId==null){
        if(patientHiddenId!="" && patientHiddenId==null){
            var newHtml='<input type="hidden" id="zhuyuanBasicInfoHiddenId" name="zhuyuanBasicInfo.id" value="'+zhuyuanId+'" />'
                        +'<input type="hidden" id="patientBasicInfoHiddenId"  name="patientBasicInfo.id" value="'+patientId+'" />';
            $("#"+fromId).append(newHtml);
        }
    }

    $("#"+fromId+" div").each(function(){
        var inputId=$(this).attr("submit_id");
        var html=$(this).html();
        $("#"+inputId).val(html);
    })
}

