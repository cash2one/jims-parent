$(function () {
    $("#porchase").datagrid({
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
            title: "库存单位",
            field: "administrationCode",
            width: '10%',
            align: 'center'

        }, {
            title: "药品名称",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "供应商",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "规格",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "入库日期",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "生产厂家",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "包装价格",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "包装单位",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "批号",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "进阶",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }]]
    });

});




