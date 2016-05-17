var administration = [{ "value": "1", "text": "中医" }, { "value": "2", "text": "西医" }];
var editRow = undefined;
$(function(){
    $('#zhenduan').datagrid({
        singleSelect: true,
        fit: true,
        method:'GET',
        url:basePath+'/diagnosis/findListOfOut',
        idField:'id',
        columns:[[      //每个列具体内容
           // {field:'id',title:'序号',width:'5%',align:'center',editor:'text'},
            /*{field:'itemNo',title:'序号',width:'5%',align:'center',editor:'text'},*/
             {field:'type',title:'诊断类型',width:'10%',align:'center',editor:{
                 type:'combobox',
                 options:{
                     required:true,
                     data :administration,
                     valueField:'value',
                     textField:'text',
                     required:true
                 }

             }
             },
            {field:'diagnosisId',title:'诊断名称',width:'30%',align:'center',editor:{
                type:'combobox',
                options:{
                    required:true,
                    url: basePath+'/dataicd/autoComplete',
                    valueField: 'code',
                    textField: 'keywordShuoming',
                    method: 'GET',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].keywordShuoming);
                    }
                }
            }},

            {field:'description',title:'诊断时间',width:'30%',align:'center',editor:{type: 'datebox'}
            },
            {field:'diagnosisDoc',title:'诊断医生',width:'30%',align:'center',editor:'text',
                formatter:function(value, row, index){
                  return "李俊山";
            }}

        ]],
      /*  frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],*/
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {

                    $("#zhenduan").datagrid("insertRow", {
                        index: 0, // index start with 0
                        row: {

                        }
                    });

            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
            }},{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                $("#zhenduan").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#zhenduan").datagrid("endEdit", editRow);
                }
                save();
            }
          }

        ],onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#zhenduan").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#zhenduan").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData){
            if (editRow != undefined) {
                $("#zhenduan").datagrid('endEdit', editRow);
            }

        }





    });






});

function save(){
    var  rows=$('#zhenduan').datagrid('getRows');
    var tableJson=JSON.stringify(rows);


    $.postJSON(basePath+'/diagnosis/saveOut',tableJson,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#zhenduan').datagrid('load');
            $('#zhenduan').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}







//删除
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#zhenduan').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            //真删除数据
            $.ajax({
                'type': 'POST',
                'url': basePath+'/diagnosis/delete',
                'contentType': 'application/json',
                'data': id=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data.code=='1'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        $('#zhenduan').datagrid('load');
                        $('#zhenduan').datagrid('clearChecked');
                    }else{
                        $.messager.alert('提示',"删除失败", "error");
                    }
                },
                'error': function(data){
                    $.messager.alert('提示',"删除失败", "error");
                }
            });
        }
    })
}