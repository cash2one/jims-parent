var administration = [{ "value": "1", "text": "中医" }, { "value": "2", "text": "西医" }];
var editRow1 = undefined;
var rowNum1=-1;
$(function(){
    var clinicId=$("#clinicMasterId",parent.document).val();
    var parentId = $("#diagnosisParent").val();
    $('#zhenduan').datagrid({
        singleSelect: true,
        fit: true,
        method:'GET',
        url:basePath+'/diagnosis/findListOfOut?diagnosisParent='+parentId,
        idField:'id',
        columns:[[      //每个列具体内容
             {field:'type',title:'诊断类型',width:'10%',align:'center',formatter:typeFormatter,editor:{
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
            {field:'diagnosisId',title:'诊断名称',width:'30%',align:'center',formatter:icdFormatter,editor:{
                type:'combogrid',
                options:{
                    panelWidth: 200,
                    data:icdAllData,
                    idField:'code',
                    textField:'zhongwen_mingcheng',
                    columns:[
                        [
                            {field: 'zhongwen_mingcheng', title: '中文名称', width: '40%', align: 'center'},
                            {field: 'code', title: 'ICD-10编码', width: '10%', align: 'center'},
                            {field: 'keyword_shuoming', title: '关键词', width: '50%', align: 'center'},
                        ]
                    ],onClickRow: function (index, row) {
                        var icdMingcheng = $("#zhenduan").datagrid('getEditor', {index: rowNum1, field: 'icdMingcheng'});
                        $(icdMingcheng.target).textbox('setValue', row.zhongwen_mingcheng);

                    },
              keyHandler: {
                up: function() {},
                down: function() {},
                enter: function() {},
                query: function(q) {
                    var ed = $('#zhenduan').datagrid('getEditor', {index:rowNum1,field:'diagnosisId'});
                        comboGridCompleting(q,'diagnosisId');
                        $(ed.target).combogrid("grid").datagrid("loadData", comboGridComplete);
                }
            }
            }}},
            {field:'diagnosisDate',title:'诊断时间',width:'30%',align:'center',formatter:formatDatebox,editor:{type: 'datebox'}
            },
            {field:'diagnosisDoc',title:'诊断医生',width:'30%',align:'center',editor:'text',
                formatter:function(value, row, index){
                  return "李俊山";
            }},
            {field:'clinicId',editor:{type:'textbox',options:{editable:true,disable:false}},hidden:'true'},
            {field:'icdMingcheng',editor:{type:'textbox',options:{editable:true,disable:false}},hidden:'true'},
            {field:'itemNo',editor:{type:'textbox',options:{editable:true,disable:false}},hidden:'true'}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {

                if(rowNum1>=0){
                    rowNum1++;
                }
                    $("#zhenduan").datagrid("insertRow", {
                        index: 0, // index start with 0
                        row: {
                            clinicId:clinicId

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
                $("#zhenduan").datagrid('endEdit', rowNum1);
                if (rowNum1 != -1) {
                    $("#zhenduan").datagrid("endEdit", rowNum1);
                }

                saveDiagnosis();
            }
          }

        ],onAfterEdit: function (rowIndex, rowData, changes) {

        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow1 != undefined) {
                $("#zhenduan").datagrid('endEdit', rowNum1);
            }
            if (editRow1 == undefined) {
                $("#zhenduan").datagrid('beginEdit', rowIndex);
                editRow1 = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData) {
            var dataGrid = $('#zhenduan');

            if (!dataGrid.datagrid('validateRow', rowNum1)) {
                return false
            }
            if (rowNum1 != rowIndex) {
                if (rowNum1 >= 0) {
                    dataGrid.datagrid('endEdit', rowNum1);
                }
                rowNum1 = rowIndex;
                dataGrid.datagrid('beginEdit', rowIndex);

                var itemNo = dataGrid.datagrid("getRowIndex",rowData) +1;
                var itemNoTarget = $("#zhenduan").datagrid('getEditor', {index: rowNum1, field: 'itemNo'});
                $(itemNoTarget.target).textbox('setValue', itemNo);
            }
        }
    });

});

function saveDiagnosis(){

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