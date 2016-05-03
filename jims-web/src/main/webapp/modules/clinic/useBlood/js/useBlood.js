var editRow = undefined;
var serialNo='';
var units = [{"value": "1", "text": "毫升"}, {"value": "2", "text": "单位"}, {"value": "3", "text": "人/份"}];
var userBlood = [{"value": "1", "text": "全血"}, {"value": "2", "text": "全血1"}, {"value": "3", "text": "全血2"}, {"value": "4", "text": "全血3"}];
$(function(){
    $('#list_doctor').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'post',
        url:basePath+'/operationApply/list',
        columns:[[
            {field:'id',title:'id',hidden:true,align:'center'},
            {field: 'description', title: '用血方式', width: '20%', align: 'center', editor: 'text'},
            //每个列具体内容
            {field: 'description', title: '预订输血时间', width: '20%', align: 'center', editor: 'text'},
            {field: 'description', title: '血量', width: '20%', align: 'center', editor: 'text'},
            {
                field: 'doctorId', title: '单位', width: '20%', align: 'center', editor: {
                type:'combobox',
                options:{
                    data: units,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {
                field: 'doctorId', title: '血液要求', width: '20%', align: 'center', editor: {
                type:'combobox',
                options:{
                    data: userBlood,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                $("#list_doctor").datagrid('insertRow', {
                    index:0,
                    row:{}
                });
            }
        }, {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                inDoDelete();
            }
        }, {
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                inDoDelete();
            }
        }
        ],

        onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#list_doctor").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#list_doctor").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData){
            //tooltips选中行，药品价目列表信息
            if (editRow != undefined) {
                $("#list_doctor").datagrid('endEdit', editRow);
            }
        }
    });
});
function onloadMethod(){
    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        fit: true,//自动大小
        url:basePath+'/bloodApply/list',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'mazuifangfa',title:'科室',width:'18%',align:'center'},
            {field:'applyNum',title:'申请单号',width:'18%',align:'center'},
            {field:'shoushuDoctor',title:'血源',width:'18%',align:'center'},
            {field:'shoushuDoctor',title:'诊断',width:'18%',align:'center'},
            {field:'shoushuDoctor',title:'血型',width:'18%',align:'center'},
            {field:'shoushuDoctor',title:'方式',width:'18%',align:'center'},
            {field:'shoushuDoctor',title:'用血量',width:'18%',align:'center'},
            {field:'shoushuDoctor',title:'血液成分',width:'18%',align:'center'},
            {field:'shoushuDate',title:'手术时间',width:'30%',align:'center',formatter:formatDateBoxFull},
            {field:'id',title:'操作',width:'40%',align:'center',formatter:function(value, row, index){
                var state="1";
                var html='<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="getOperation(\''+row.id+'\',\''+state+'\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>'+
                    '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="getOperation(\''+row.id+'\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>'+
                    '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\''+value+'\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                return html;
            }}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '修改',
            iconCls: 'icon-edit',
            handler: function() {
                var selectRows = $('#list_data').datagrid("getSelections");
                if (selectRows.length < 1) {
                    $.messager.alert("提示消息", "请选中需要修改的数据");
                    return;
                }
                get(selectRows[0].id);
            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
            }
        }]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
}


/**
 * 保存用血申请记录
 * @param id
 */
function saveUseBloodApply() {
    $("#inpNo").attr("value","123");
    $.postForm(basePath + "/bloodApply/save", "useBloodForm", function (data) {
        if (data.code == "1") {
            $.messager.alert("提示信息", "保存成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
            $("#useBloodForm").form("clear");
        } else {
            $.messager.alert("提示信息", "保存失败", "error");
        }

    }), function (data) {
        $.messager.alert("提示信息", "保存失败", "error");
    }
}
