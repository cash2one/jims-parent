/**
 * 摆药管理
 * @author yangruidong
 * @version 2016-06-24
 */
$(function () {
    $("#drug-dispensing-manager").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        url: basePath + '/org-role/findAllListByOrgId?orgId=1',

        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "床",
            field: "roleName",
            width: '5%',
            align: 'center'

        }, {
            title: "病人ID",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "姓名",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "性别",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "费别",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "年龄",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "医嘱",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "剂量",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "频次",
            field: "administrationCode",
            width: '5%',
            align: 'right'
        }, {
            title: "执行时间",
            field: "administrationCode",
            width: '10%',
            align: 'left'
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
            title: "数量",
            field: "administrationCode",
            width: '5%',
            align: 'right'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '5%',
            align: 'left'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "上次摆药截止时间",
            field: "administrationCode",
            width: '15%',
            align: 'center'
        }, {
            title: "开始时间",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "医嘱说明",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "停止时间",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "给药途径",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "预交金剩余(元)",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "预出院日期",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "应收费用",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "开单医生",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "参与累积剂量",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "入虚拟药柜数量",
            field: "administrationCode",
            width: '15%',
            align: 'right'
        }, {
            title: "批号",
            field: "administrationCode",
            width: '7%',
            align: 'left'
        }, {
            title: "适应症与非适应症",
            field: "administrationCode",
            width: '15%',
            align: 'center'
        }
        ]],
        onDblClickCell: function (index, field, value) {
            $("#selectDrug").dialog();
        }

    });

    //关闭dialog
    $("#cancelBtn").on('click', function () {
        $("#selectDrug").dialog('close');
    });


    $("#drug-manager").datagrid({
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        /*  url: basePath + '/drug-class-dict/list?orgId=1',*/

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "摆药时间",
            field: "administrationCode",
            width: '10%',
            align: 'center'

        }, {
            title: "数量",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "药品名称",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "规格",
            field: "administrationCode",
            width: '5%',
            align: 'right'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '5%',
            align: 'left'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '10%',
            align: 'center'
        }, {
            title: "应收(元)",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "实收(元)",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "调剂者",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "药房",
            field: "administrationCode",
            width: '7%',
            align: 'left'
        }, {
            title: "子库房",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }
        ]]
    });

    $("#table1 input").attr("disabled", "disabled");
    $("#table2 input").attr("disabled", "disabled");
    $("#table3 input").attr("disabled", "disabled");
});




