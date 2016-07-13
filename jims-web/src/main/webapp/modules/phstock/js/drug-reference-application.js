/**
 *查阅
 * @author yangruidong
 * @version 2016-06-29
 */
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
var basePath = "/service";
$(function () {

    //var orgId=parent.config.org_id;
    var orgId = 1;
    $("#application").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        url: basePath + '/drugProvideApplication/findListByDistinct?orgId=' + orgId + '&flag=0',
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: '申请单位',
            field: 'storageName',
            width: '130',
            align: 'center'
        },{
            title: '申请单号',
            field: 'documentNo',
            width: '130',
            align: 'center'
        }, {
            title: '申请时间',
            field: 'enterDateTime',
            width: '170',
            align: 'center',
            formatter: function (value, row, index) {
                row.enterDateTime = formatDateBoxFull(value).substr(0,13)+':00:00'
                return formatDateBoxFull(value).substr(0,13)
            }
        }]],
        onClickRow: function (index, row) {
            var node = $("#application").datagrid("getSelected");
            var params = {
                orgId: orgId,
                enterDateTime: parseToDate(node.enterDateTime),
                applicantStorage: node.applicantStorage,
                documentNo:node.documentNo
            }
            $.get(basePath + '/drugProvideApplication/findList', params, function (res) {
                $('#application-info').datagrid('loadData', res)
            })
        }
    });

    //申请数量，药品名称等详细的信息
    $("#application-info").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        method: 'GET',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "药名",
            field: "drugName",
            width: '10%',
            align: 'center'
        }, {
            title: "规格",
            field: 'drugSpec',
            width: '10%',
            align: 'center'
        }, {
            title: "单位",
            field: "label",
            width: '10%',
            align: 'center'
        }, {
            title: "批号",
            field: "batchNo",
            width: '10%',
            align: 'center'
        }, {
            title: "申请数量",
            field: "quantity",
            width: '10%',
            align: 'center'
        }, {
            title: "生产厂家",
            field: "supplierId",
            width: '10%',
            align: 'center'
        }
        ]]
    });


});




