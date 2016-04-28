var administration = [{ "value": "1", "text": "初步诊断" }, { "value": "2", "text": "鉴别诊断" }, { "value": "4", "text": "入院诊断" }];
var editRow = undefined;
$(function(){
    alert(1111111111111);
   var parentId='24ba6528c0004041a9a2b391fe3839d3';
    $('#zhenduan').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/diagnosis/findList',
        idField:'id',
        columns:[[      //每个列具体内容
           // {field:'id',title:'序号',width:'5%',align:'center',editor:'text'},
            /*{field:'itemNo',title:'序号',width:'5%',align:'center',editor:'text'},*/
             {field:'type',title:'诊断类型',width:'10%',align:'center',editor:'text',editor:{
                 type:'combobox',
                 options:{
                     data :administration,
                     valueField:'value',
                     textField:'text',
                     required:true,
                     onLoadSuccess: function () {
                         var data = $(this).combobox('getData');
                         $(this).combobox('select', data[0].text);
                     }
                 }

             }
             },
            {field:'diagnosisId',title:'诊断名称',width:'30%',align:'center',editor:{
                type:'combobox',
                options:{required:true,
                    url: basePath+'/dataicd/autoComplete',
                    valueField: 'code',
                    textField: 'keywordShuoming',
                    method: 'GET',
                    onLoadSuccess: function () {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].keywordShuoming);
                    }
                }
            }},
            {field:'basis',title:'诊断依据',width:'30%',align:'center',editor:'text'},
            {field:'description',title:'诊断描述',width:'30%',align:'center',editor:'text'}

        ]],
      /*  frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],*/
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                $("#zhenduan").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#zhenduan").datagrid("endEdit", editRow);
                }
                //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                if (editRow == undefined) {
                    $("#zhenduan").datagrid("insertRow", {
                        index: 0, // index start with 0
                        row: {

                        }
                    });
                    //将新插入的那一行开户编辑状态
                    $("#zhenduan").datagrid("beginEdit", 0);
                    //给当前编辑的行赋值
                    editRow = 0;
                 }
                  /*  $("#zhenduan").datagrid('insertRow', {
                        index:0,

                        row:{}
                    });*/
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
          }/*,{
            text:'添加子诊断',
            iconCls:'easyui-linkbutton c1',
            handler:function(rowData){
                var parentIndex = $('#zhenduan').datagrid('getRowIndex',$('#zhenduan').datagrid('getSelected'));
                alert("parentIndex="+parentIndex);
                if(parentIndex==-1){
                   alert("请选择诊断！");
                }else{
                    //保存父行数据，用于新增数据。
                    $('#zhenduan').datagrid('endEdit', parentIndex);
                    $('#zhenduan').datagrid('updateRow',{index: parentIndex,row:{}});
                    //获取父行数据，进行新增操作。

                   // $("#zhenduan").datagrid("beginEdit",newIndex);
                    var newIndex = parentIndex+1;

                    $('#zhenduan').datagrid('selectRow',parentIndex);
                    var rowParent = $('#zhenduan').datagrid('getSelected');
                    var newRow = jQuery.extend(true, {}, newIndex);
                    $("#zhenduan").datagrid("selectRow", 0);
                    $('#zhenduan').datagrid('insertRow',{
                        index:newIndex,
                        row:newRow
                    });

                }



            }

        }*/

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
            //tooltips选中行，药品价目列表信息
            if (editRow != undefined) {
                $("#zhenduan").datagrid('endEdit', editRow);
            }

        }





    });






});

function save(){
    var  rows=$('#zhenduan').datagrid('getRows');
    var tableJson=JSON.stringify(rows);
    alert("tableJson="+tableJson);

    $.postJSON(basePath+'/diagnosis/save',tableJson,function(data){
        if(data.data=='success'){
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
                    if(data.data=='success'){
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