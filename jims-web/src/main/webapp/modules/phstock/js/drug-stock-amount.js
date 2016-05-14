//全院库存量查询
$(function(){

    $("#dg").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        //toolbar: '#tb',
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
            title: "库存管理单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'

        }, {
            title: "子单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "品名规格",
            field: "administrationCode",
            width: '17%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "数量合计",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "零价金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "供货单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "进价",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "进价金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "单据号",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }
        ]]
    })

    $('#test').combobox({
        panelHeight:"60px",
        idField: 'id',
        valueField: 'id',
        textField: 'text',
        data: [
            {'id': '0', 'text': '药房', width: "100px"},
            {'id': '1', 'text': '药库', width: "100px"}
        ]
    });
})