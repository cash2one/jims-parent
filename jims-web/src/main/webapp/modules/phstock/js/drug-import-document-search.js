/**
 * 入库单据查询
 * @author yangruidong
 * @version 2016-05-13
 */
$(function () {
    $("#drug-import-document-search").datagrid({
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
            title: "库房单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'

        }, {
            title: "入库类别",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "入库单号",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "供货单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "供货子单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "数量",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "入库日期",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "记账",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "操作员",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "应付金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "批价金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "零价金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "附加费用",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "已付金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "进价差额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        } , {
            title: "品种数",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "备注",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }
        ]]
    });

    //设置是否禁用控件
    $("#drugClass").combobox({'disabled':true});
    $("#ck").on("click", function () {
        if ($("#ck").prop("checked") == true) {
            $("#drugClass").combobox({'disabled':false});
        } else {
            $("#drugClass").combobox({'disabled':true});
        }
    });

    $("#documnent1").textbox({'disabled':true});
    $("#documnent2").textbox({'disabled':true});
    $("#ck1").on("click", function () {
        if ($("#ck1").prop("checked") == true) {
            $("#documnent1").textbox({'disabled':false});
            $("#documnent2").textbox({'disabled':false});
            //$("#documnent1").focus();
        } else {
            $("#documnent1").textbox({'disabled':true});
            $("#documnent2").textbox({'disabled':true});
        }
    });
    //供应商/科室
    $("#dept1").textbox({'disabled':true});
    $("#ck2").on("click", function () {
        if ($("#ck2").prop("checked") == true) {
            $("#dept1").textbox({'disabled':false});
            $("#dept1").val();
        } else {
            $("#dept1").textbox({'disabled':true});
        }
    });
    //发票号
    $("#dept2").textbox({'disabled':true});
    $("#ck21").on("click", function () {
        if ($("#ck21").prop("checked") == true) {
            $("#dept2").textbox({'disabled':false});
        } else {
            $("#dept2").textbox({'disabled':true});
        }
    });

    //日期区间
    $("#startDate").combobox({'disabled':true});
    $("#endDate").combobox({'disabled':true});
    $("#ck3").on("click", function () {
        if ($("#ck3").prop("checked") == true) {
            $("#startDate").combobox({'disabled':false});
            $("#endDate").combobox({'disabled':false});
        } else {
            $("#startDate").combobox({'disabled':true});
            $("#endDate").combobox({'disabled':true});
        }
    });
    //记账区间
    $("#startMoneyDate").combobox({'disabled':true});
    $("#endMoneyDate").combobox({'disabled':true});
    $("#ck4").on("click", function () {
        if ($("#ck4").prop("checked") == true) {
            $("#startMoneyDate").combobox({'disabled':false});
            $("#endMoneyDate").combobox({'disabled':false});
        } else {
            $("#startMoneyDate").combobox({'disabled':true});
            $("#endMoneyDate").combobox({'disabled':true});
        }
    });

    //药品名称
    $("#drugName").textbox({'disabled':true});
    $("#ck41").on("click", function () {
        if ($("#ck41").prop("checked") == true) {
            $("#drugName").textbox({'disabled':false});
        } else {
            $("#drugName").textbox({'disabled':true});
        }
    });
});




