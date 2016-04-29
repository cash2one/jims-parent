var editRow = undefined;
var serialNo='';
var administration = [{ "value": "1", "text": "科室1" }, { "value": "2", "text": "科室2" }, { "value": "3", "text": "科室3" }, { "value": "4", "text": "科室4" }, { "value": "5", "text": "科室5" }];
var doctors = [{ "value": "1", "text": "医生1" }, { "value": "2", "text": "医生" }, { "value": "3", "text": "医生" }, { "value": "4", "text": "医生" }, { "value": "5", "text": "医生" }];
$(function(){
    $('#list_doctor').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'post',
        url:basePath+'/operationApply/list',
        columns:[[
            {field:'id',title:'id',hidden:true,align:'center'},
            //每个列具体内容
            {field:'officeId',title:'科室',width:'50%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :administration,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'doctorId',title:'参与医生',width:'50%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :doctors,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'doctorId',title:'参与医生',width:'50%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :doctors,
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
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                inDoDelete();
            }
        }],onAfterEdit: function (rowIndex, rowData, changes) {
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

