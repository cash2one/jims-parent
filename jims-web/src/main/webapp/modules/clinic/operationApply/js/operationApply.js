var editRow=undefined;
$(function(){
    var clinicId=$("#clinicMasterId",parent.document).val();
    $("#clinicId").val(clinicId);
    $('#operationName').datagrid({
        rownumbers: true,
        singleSelect: true,
        fit: true,
        method:'POST',
        url: basePath+'/operatioinOrder/getOperationName',
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'operation', title: '拟实施手术名称', width: '70%', align: 'center', editor:{
                type:'combogrid',
                options: {
                    panelWidth: 500,
                    idField: 'itemCode',
                    textField: 'itemName',
                    url: '/modules/operation/js/clinic_data.json',
                    columns: [[
                        {field: 'itemCode', title: '项目代码', width: '20%', align: 'center'},
                        {field: 'itemName', title: '项目名称', width: '20%', align: 'center'},
                        {field: 'inputCode', title: '拼音输入码', width: '10%', align: 'center', editor: 'text'},
                        {field: 'inputCodeWb', title: '五笔输入码', width: '10%', align: 'center', editor: 'text'}
                    ]],
                    fitColumns: true
                }
            }
            },
            {field: 'operationScale', title: '等级', width: '20%', align: 'center'}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {

                $("#operationName").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#operationName").datagrid("endEdit", editRow);
                }
                //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                if (editRow == undefined) {
                    $("#operationName").datagrid("insertRow", {
                        index: 0, // index start with 0
                        row: {}
                    });
                    //将新插入的那一行开户编辑状态
                    $("#operationName").datagrid("beginEdit", 0);
                    //给当前编辑的行赋值
                    editRow = 0;
                }
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                doDelete();
            }
        }
        ]
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
        url:basePath+'/operationApply/list',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'mazuifangfa',title:'麻醉方法',width:'18%',align:'center'},
            {field:'shoushuDoctor',title:'手术医师',width:'18%',align:'center'},
            {field:'shoushuDate',title:'手术时间',width:'30%',align:'center',formatter:formatDateBoxFull},
            {field:'id',title:'操作',width:'40%',align:'center',formatter:function(value, row, index){
                var html='<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="getOperation(\''+row.id+'\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>'+
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
 * 保存申请记录
 * @param id
 */
function savePperationApply() {

    alert("clinicId="+clinicId);

    $("#operationName").datagrid('endEdit', editRow);
    var  rows=$('#operationName').datagrid('getRows');
    var formJson=fromJson('operation');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson=JSON.stringify(rows);
    var submitJson=formJson+",\"scheduledOperationNameList\":"+tableJson+"}";

    $.postJSON(basePath+'/operatioinOrder/save',submitJson,function(data){
        if(data=="1"){
            $.messager.alert("提示消息",data+"条记录，保存成功");
            $('#operationName').datagrid('load');
            $('#operationName').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
            $('#operationName').datagrid('load');
            $('#operationName').datagrid('clearChecked');
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}
//列删除
function deleteRow(id) {
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            del(id);
        }
    })
}
//批量删除
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            //真删除了  1,3,4
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            del(strIds);
        }
    })
}

/**
 * 显示修改
 * @param data
 */
function getOperation(id){
    $.ajax({
        'type': 'post',
        'url': basePath+'/operationApply/getOperation',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            $('#operationApplyForm').form('load',data);
        }
    })
}

/**
 * 删除方法
 * @param id
 */
function del(id){
    $.ajax({
        'type': 'POST',
        'url': basePath+'/operationApply/del',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                if(data.code>0){
                    $.messager.alert("提示消息",data.code+"条记录，已经删除");
                    $('#list_data').datagrid('load');
                    $('#list_data').datagrid('clearChecked');
                }else{
                    $.messager.alert('提示',"删除失败", "error");
                }
            }else{
                $.messager.alert('提示',"删除失败", "error");
            }
        },
        'error': function(data){
            $.messager.alert('提示',"保存失败", "error");
        }
    });
}

