var editRow = undefined;
$('#list_data').datagrid({
    iconCls:'icon-edit',//图标
    width: '80%',
    height: 'auto',
    nowrap: false,
    striped: true,
    border: true,
    collapsible:false,//是否可折叠的
    fit: true,//自动大小
    url:'/modules/doctor/datagrid_data2.json',
    //sortName: 'code',
    //sortOrder: 'desc',
    remoteSort:false,
    idField:'fldId',
    singleSelect:false,//是否单选
    pagination:true,//分页控件
    rownumbers:true,//行号
    columns:[[      //每个列具体内容
        {field:'orderNo',title:'处方号',width:'5%',align:'center'},
        {field:'drugName',title:'药名',width:'10%',align:'center',/*formatter:function(value){
            for(var i=0; i<products.length; i++){
                if (products[i].productid == value) return products[i].name;
            }
            return value;
        },*/
            editor:{
                type:'combobox',
                options:{
                    valueField:'productid',
                    textField:'name',
                    data:1,
                    required:true
                }
            }},
        {field:'drugSpec',title:'规格',width:'5%',align:'center'},
        {field:'firmId',title:'厂家',width:'5%',align:'center'},
        {field:'amount',title:'药品数量',width:'5%',align:'center',editor:'numberbox'},
        {field:'units',title:'单位',width:'5%',align:'center'},
        {field:'performNurse',title:'剂量',width:'5%',align:'center',editor:'numberbox'},
        {field:'dosage',title:'单次用量',width:'5%',align:'center',editor:'text'},
        {field:'dosageUnits',title:'用量单位',width:'5%',align:'center'},
        {field:'administration',title:'途径',width:'5%',align:'center',editor:{
            type:'combobox',
            options:{
                valueField:'productid',
                textField:'name',
                data:1,
                required:true
            }
        }},
        {field:'frequency',title:'频次',width:'5%',align:'center',editor:{
            type:'combobox',
            options:{
                valueField:'productid',
                textField:'name',
                data:1,
                required:true
            }
        }},
        {field:'abidance',title:'用药天数',width:'5%',align:'center',editor:'numberbox'},
        {field:'charges',title:'实收',width:'5%',align:'center'},
        {field:'itemClass',title:'药局',width:'5%',align:'center'},
        {field:'freqDetail',title:'医生说明',width:'5%',align:'center',editor:'text'},
        {field:'providedIndicator',title:'取药属性',width:'5%',align:'center',editor:{
            type:'combobox',
            options:{
                valueField:'productid',
                textField:'name',
                data:1,
                required:true
            }
        }},
     /*   {field:'skinFlag',title:'代煎',width:'5%',align:'center',editor:'text'},*/
        {field:'skinFlag',title:'皮试结果',width:'5%',align:'center',editor:{
            type:'combobox',
            options:{
                valueField:'productid',
                textField:'name',
                data:1,
                required:true
            }
        }}
    ]],
    frozenColumns:[[
        {field:'ck',checkbox:true}
    ]],
    toolbar: [{
        text: '添加',
        iconCls: 'icon-add',
        handler: function() {
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#list_data").datagrid('insertRow', {
                    index: 0,
                    row: {}
                });
                $("#list_data").datagrid('beginEdit', 0);
                editRow = 0;
            }
        }
    }],onAfterEdit: function (rowIndex, rowData, changes) {
        editRow = undefined;
    },onDblClickRow:function (rowIndex, rowData) {
        if (editRow != undefined) {
            $("#list_data").datagrid('endEdit', editRow);
        }

        if (editRow == undefined) {
            $("#list_data").datagrid('beginEdit', rowIndex);
            editRow = rowIndex;
        }
    },
    onClickRow:function(rowIndex,rowData){
        if (editRow != undefined) {
            $("#list_data").datagrid('endEdit', editRow);

        }

    }
});
//设置分页控件
var p = $('#list_data').datagrid('getPager');
$(p).pagination({
    pageSize: 10,//每页显示的记录条数，默认为10
    pageList: [5,10,15],//可以设置每页记录条数的列表
    beforePageText: '第',//页数文本框前显示的汉字
    afterPageText: '页    共 {pages} 页',
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
});

//删除用户数据
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
            $.messager.alert("提示消息","删除成功~~");
        }
    })
}

function addPre(){
    $('#leftList').datagrid('appendRow', {
        FlowTypeName: 'new name',
        FlowTypeCode: 30
    });
}