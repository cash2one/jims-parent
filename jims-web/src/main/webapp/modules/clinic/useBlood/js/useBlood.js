var editRow = undefined;
var serialNo='';
var units = [{"value": "1", "text": "毫升"}, {"value": "2", "text": "单位"}, {"value": "3", "text": "人/份"}];
var userBlood = [{"value": "1", "text": "全血"}, {"value": "2", "text": "全血1"}, {
    "value": "3",
    "text": "全血2"
}, {"value": "4", "text": "全血3"}];
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

