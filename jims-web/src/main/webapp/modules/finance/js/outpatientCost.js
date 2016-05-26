function init(){
    $('#clinicNoId').textbox('textbox').keydown(function(e){
        if (e.keyCode == 13) {
            getCost();
            return false;
        }
    });
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
        border: true,
        method: 'GET',
        rownumbers: true,
        singleSelect:false,//是否单选
        remoteSort:false,
        loadMsg: '数据正在加载中，请稍后.....',
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        columns: [[
            {
                title: "挂号日期",
                field: "visit_date",
                width: '10%',
                align: 'center'

            }, {
                title: "处方号",
                field: "presc_no",
                width: '10%',
                align: 'center'

            }, {
                title: " ",
                field: "item_class",
                width: '10%',
                align: 'center',formatter:function(value, row, index) {
                    if (value == 'A' || value == 'B') {
                        return "药品";
                    } else {
                        return "非药品";
                    }
                }
            }, {
                title: "序号",
                field: "item_no",
                width: '8%',
                align: 'center'
            }, {
                title: "名称",
                field: "item_name",
                width: '10%',
                align: 'center'
            }, {
                title: "执行科室",
                field: "performed_by",
                width: '10%',
                align: 'center'
            }, {
                title: "医师",
                field: "doctor",
                width: '10%',
                align: 'center'
            }, {
                title: "开单科室",
                field: "ordered_by",
                width: '10%',
                align: 'center'
            }, {
                title: "申请号",
                field: "appoint_no",
                width: '10%',
                align: 'center'
            },{
                title: "申请单",
                field: "appoint_item_no",
                width: '7%',
                align: 'center',formatter:function(value, row, index){
                    if(value=='0'){
                        return "否";
                    }else{
                        return "是";
                    }
                }
            }
        ]],
        onSelect:function(rowIndex,rowData){
            $.each($('#list-zhu').datagrid('getRows'),function(j,val){
                if(rowData.presc_no==val.presc_no){
                    var index = $('#list-zhu').datagrid('getRowIndex', val);
                    alert(val.id);
                    $("#list-zhu").datagrid('selectRecord',val.id);
                }
            });
        }
    });
    $("#list-xi").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        method: 'GET',
        rownumbers: true,
        singleSelect:false,//是否单选
        loadMsg: '数据正在加载中，请稍后.....',
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        columns: [[
            {
                title: "编号",
                field: "item_no",
                width: '6%',
                align: 'center'

            }, {
                title: "类别",
                field: "item_class",
                width: '6%',
                align: 'center'
            }, {
                title: "项目名称",
                field: "item_name",
                width: '6%',
                align: 'center'
            }, {
                title: "项目规格",
                field: "item_spec",
                width: '6%',
                align: 'center'
            }, {
                title: "单位",
                field: "units",
                width: '6%',
                align: 'center'
            }, {
                title: "付数",
                field: "repetition",
                width: '6%',
                align: 'center'
            }, {
                title: "数量",
                field: "amount",
                width: '6%',
                align: 'center'
            },  {
                title: "开单科室",
                field: "ordered_by",
                width: '6%',
                align: 'center'
            }, {
                title: "开单医生",
                field: "ordered_by_doctor",
                width: '6%',
                align: 'center'
            }, {
                title: "执行科室",
                field: "performed_by",
                width: '7%',
                align: 'center'
            },{
                title: "计价",
                field: "costs",
                width: '6%',
                align: 'center'
            },{
                title: "处方号",
                field: "presc_no",
                width: '6%',
                align: 'center'
            },{
                title: "取药标志",
                field: "getdrug_flag",
                width: '6%',
                align: 'center'
            },{
                title: "处方属性",
                field: "presc_attr",
                width: '6%',
                align: 'center'
            },{
                title: "医嘱说明",
                field: "freq_detail",
                width: '6%',
                align: 'center'
            },{
                title: "单次用量",
                field: "dosage",
                width: '6%',
                align: 'center'
            },{
                title: "用量单位",
                field: "dosage_units",
                width: '6%',
                align: 'center'
            },{
                title: "用药途径",
                field: "administration",
                width: '6%',
                align: 'center'
            },{
                title: "频次",
                field: "frequency",
                width: '6%',
                align: 'center'
            },{
                title: "价格",
                field: "charges",
                width: '6%',
                align: 'center'
            },{
                title: "子库房",
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
};

function showReturnsDiv(){
    $('#returnsDiv').window("open");
}
function showContDiv(){
    $('#contDiv').window("open");
}
/**
 * 根据就诊ID获取收费项目
 */
function getCost(){
    var  clinicNoValue=$("#clinicNoId").val();
    $.ajax({
        cache: true,
        'type': 'get',
        'url': basePath+'/outPatientCost/list',
        'contentType': 'application/json',
        'data': {orgId:'1',clinicNo:clinicNoValue},
        'dataType': 'json',
        'success': function(data){
            if(data.code=='success'){
                $("#xiForm").form("load",data.data)
                if(data.datas.length>0){
                    $('#list-zhu').datagrid('loadData',data.datas);
                    $('#list-xi').datagrid('loadData',data.datas1);
                    $('#chargeDiv').window("open");
                }
            }else{
                $.messager.alert("提示消息", data.code);
                $("#xiForm").form("clear")
            }

        },
        'error': function(){

        }
    })
}


