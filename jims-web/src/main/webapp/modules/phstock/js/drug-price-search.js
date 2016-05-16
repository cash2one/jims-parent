
$(function () {
    $("#search").datagrid({
        title: '药品价格查询',
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        //  url: basePath + "/AdministrationDict/listAll",

        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "药品名称",
            field: "administrationCode",
            width: '10%',
            align: 'center'

        }, {
            title: "药品规格",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "批发价",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "零售价",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "最高限价",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "包装量",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "最小规格",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "最小单位",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "起用日期",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "停止日期",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "备注",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }]]
    });
    $('#chk2').click(function () {

        $("#inputCode").textbox('enable');
        $("#supplierType").combogrid('disable');
    });
    $('#chk1').click(function () {

        $("#inputCode").textbox('disable');
        $("#supplierType").combogrid('enable');

    });
});




