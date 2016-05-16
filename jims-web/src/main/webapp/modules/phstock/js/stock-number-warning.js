//药品库存警告
//朱齐
//2016-5-14
$(function(){
    $('#type').combobox({
        mode: 'remote',
        method: 'GET',
        url: basePath + '/dict/findListByType?type=DRUG_TOXI_PROPERTY_DICT',
        idField: 'value',
        valueField: 'value',
        panelHeight:"185px",
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
            title: "药品编码",
            field: "administrationCode",
            width: '7%',
            align: 'center'

        }, {
            title: "药品名称",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "规格",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "包装单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "库存下限",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "库存上限",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "参考进价",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "参考批价",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "参考零价",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "当前库存",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }
        ]]
    })
})