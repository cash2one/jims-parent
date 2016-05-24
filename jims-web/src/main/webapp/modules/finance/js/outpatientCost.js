$(function () {
    $("#list").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        footer: '#fb',
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[
            {
            title: "分类",
            field: "administrationCode",
            width: '6%',
            align: 'center'

        }, {
            title: "名称",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "类别",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "规格",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "付",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "单次用量",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "总数量",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "退量数",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "执行科室",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "费用",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "收费比",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "实际费用",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "状态",
            field: "classCode",
            width: '10%',
            align: 'center',
            editor: {
                type: 'datebox'
            }
        }, {
            title: "开单号",
            field: "className",
            width: '6%',
            align: 'center'
        } , {
            title: "收据号",
            field: "administrationCode",
            width: '10%',
            align: 'center',
            editor: {
                type: 'datebox'
            }
        }, {
            title: "子库房",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }
        ]] ,
        onClickRow: function (index, row) {

        }
    });
    $("#list-zhu").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[
            {
                title: "挂号日期",
                field: "administrationCode",
                width: '6%',
                align: 'center'

            }, {
                title: "处方号",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: " ",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "序号",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "名称",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "执行科室",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "医师",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "开单科室",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "申请号",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "申请单",
                field: "administrationCode",
                width: '7%',
                align: 'center'
            }
        ]]
    });
    $("#list-xi").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[
            {
                title: "编号",
                field: "administrationCode",
                width: '6%',
                align: 'center'

            }, {
                title: "类别",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "项目名称",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "项目规格",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "单位",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "付数",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "数量",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },  {
                title: "开单科室",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "开单医生",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }, {
                title: "执行科室",
                field: "administrationCode",
                width: '7%',
                align: 'center'
            },{
                title: "计价",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "处方号",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "取药标志",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "处方属性",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "医嘱说明",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "单次用量",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "用量单位",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "用药途径",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "频次",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "价格",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "子库房",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },{
                title: "开单序号",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            }
        ]]
    });

    $("#list-zhu-t").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[
            {
                title: "收据单",
                field: "administrationCode",
                width: '48%',
                align: 'center'

            }, {
                title: "收费单",
                field: "administrationCode",
                width: '49%',
                align: 'center'
            }
        ]]
    });

    $("#list-xi-t").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[
            {
                title: "类别",
                field: "administrationCode",
                width: '10%',
                align: 'center'
            }, {
                title: "名称",
                field: "administrationCode",
                width: '10%',
                align: 'center'
            }, {
                title: "规格",
                field: "administrationCode",
                width: '6%',
                align: 'center'
            },  {
                title: "数量",
                field: "administrationCode",
                width: '10%',
                align: 'center'
            },{
                title: "单位",
                field: "administrationCode",
                width: '15%',
                align: 'center'
            }, {
                title: "计价",
                field: "administrationCode",
                width: '10%',
                align: 'center'
            },  {
                title: "应收",
                field: "administrationCode",
                width: '10%',
                align: 'center'
            }, {
                title: "执行科室",
                field: "administrationCode",
                width: '15%',
                align: 'center'
            },{
                title: "收据类型",
                field: "administrationCode",
                width: '12%',
                align: 'center'
            },{
                title: "退费标示",
                field: "administrationCode",
                width: '10%',
                align: 'center'
            }
        ]]
    });
});

function showReturnsDiv(){
    $('#returnsDiv').window("open");
}
function showContDiv(){
    $('#contDiv').window("open");
}



