$(function () {

    $("#dg").datagrid({
        title:'调价记录',
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        //method: 'GET',
        rownumbers: true,
        //  url: basePath + "/AdministrationDict/listAll",

        loadMsg: '数据正在加载中，请稍后.....',
       /* pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表*/
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "药品名称",
            field: "administrationCode",
            width: '8%',
            align: 'center'

        }, {
            title: "包装规格",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '17%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "原批发价",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "新批发价",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "原零售价",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "新零售价",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "实际生效日期",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "通知生效日期",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "调价依据",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }
        ]]
    })
})