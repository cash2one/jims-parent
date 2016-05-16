$(function(){
    $('#storage').combobox({
        idField: 'id',
        valueField: 'id',
        panelHeight:"110px",
        textField: 'text',
        data: [
            {'id': '0', 'text': '西药字库'},
            {'id': '1', 'text': '成药字库'},
            {'id': '2', 'text': '草药字库'},
            {'id': '3', 'text': '全部'}
        ]
    });

    $('#drugType').combobox({
        mode: 'remote',
        method: 'GET',
        url: basePath + '/dict/findListByType?type=drug_type_dict',
        idField: 'value',
        valueField: 'value',
        panelHeight:"110px",
        textField: 'label'
    });

    $("#drug").datagrid({
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
            title: "库房",
            field: "administrationCode",
            width: '7%',
            align: 'center'

        }, {
            title: "药名",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "规格",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "单价",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "期初数",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "期初金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "入库数",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "入库金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "退药出库数",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "退药出库金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "报损出库数",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "报损出库金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "总出库数",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "总出库金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "结存数",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "结存金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "调价盈亏",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }
        ]]
    })

})