/**
 * 包名称维护
 * @author yangruidong
 * @version 2016-06-27
 */


$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var basePath = "/service";
$(function () {

    var orgId=config.org_Id;
    //var orgId = 1;

    $("#asepsis-disinfect").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        method: 'GET',
        rownumbers: true,
        //  url: basePath + '/asepsisStock/findList?orgId=' + orgId ,

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "序号",
            field: "belongDept",
            width: '20%',
            align: 'center'
        }, {
            title: "名称",
            field: "documentNo",
            width: '20%',
            align: 'center'
        }, {
            title: "规格",
            field: "asepsisCode",
            width: '20%',
            align: 'center'
        }, {
            title: "单位",
            field: "asepsisName",
            width: '20%',
            align: 'center'
        }, {
            title: "数量",
            field: "asepsisSpec",
            width: '20%',
            align: 'left'

        }
        ]]
    });




})
;




