
var editRow = undefined;
$(function(){
    $('#treatment').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:'/modules/clinic/treatment/js/treegrid_data2.json',
        idField:'id',
        columns:[[      //每个列具体内容
            {field:'prescriptionNum',title:'处方号',width:'5%',align:'center',editor:'text'},
            {field:'treatmentDate',title:'就诊日期',width:'5%',align:'center'},
            {field:'drugName',title:'药名',width:'5%',align:'center',editor:'text'},
            {field:'specifications',title:'规格',width:'5%',align:'center',editor:'text'},
            {field:'manufactor',title:'厂家',width:'5%',align:'center',editor:'text'},
            {field:'skinTest',title:'是否皮试',width:'2%',align:'center',editor:'text'},
            {field:'result',title:'结果',width:'10%',align:'center',editor:'text'},
            {field:'number',title:'数量',width:'5%',align:'center',editor:'text'},
            {field:'company',title:'单位',width:'2%',align:'center',editor:'text'},
            {field:'agentsNum',title:'剂数',width:'3%',align:'center',editor:'text'},
            {field:'consumption',title:'用量',width:'5%',align:'center',editor:'text'},
            {field:'channel',title:'途径',width:'5%',align:'center',editor:'text'},
            {field:'frequency',title:'频次',width:'5%',align:'center',editor:'text'},
            {field:'drugsDays',title:'用药天数',width:'3%',align:'center',editor:'text'},
            {field:'netReceipts',title:'实收',width:'5%',align:'center',editor:'text'},
            {field:'drugAdministration',title:'药局',width:'5%',align:'center',editor:'text'},
            {field:'childDrug',title:'子药局',width:'5%',align:'center',editor:'text'},
            {field:'docDetial',title:'医生说明',width:'5%',align:'center',editor:'text'},
            {field:'quyaoshuxing',title:'取药属性',width:'5%',align:'center',editor:'text'},
            {field:'prescriptionshuxing',title:'处方属性',width:'5%',align:'center',editor:'text'},
            {field:'money',title:'收费',width:'5%',align:'center',editor:'text'}

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
            //tooltips选中行，药品价目列表信息
            if (editRow != undefined) {
                $("#zhenduan").datagrid('endEdit', editRow);
            }

        }
    });




    $('#treatment_data').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:'/modules/clinic/treatment/js/datagrid_data2.json',
        idField:'id',
        columns:[[      //每个列具体内容
            {field:'type',title:'类别',width:'10%',align:'center',editor:'text'},
            {field:'treatmentDate',title:'就诊日期',width:'10%',align:'center'},
            {field:'xiangmuName',title:'项目名称',width:'20%',align:'center',editor:'text'},
            {field:'number',title:'数量',width:'5%',align:'center',editor:'text'},
            {field:'company',title:'单位',width:'5%',align:'center',editor:'text'},
            {field:'frequency',title:'频次',width:'5%',align:'center',editor:'text'},
            {field:'dept',title:'执行科室',width:'5%',align:'center',editor:'text'},
            {field:'netReceipts',title:'实收',width:'5%',align:'center',editor:'text'},
            {field:'docDetial',title:'医生说明',width:'5%',align:'center',editor:'text'},
            {field:'money',title:'收费标识',width:'10%',align:'center',editor:'text'}

        ]],
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
            //tooltips选中行，药品价目列表信息
            if (editRow != undefined) {
                $("#zhenduan").datagrid('endEdit', editRow);
            }

        }
    });

});

