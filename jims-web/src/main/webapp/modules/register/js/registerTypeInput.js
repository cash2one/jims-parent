var rowNum=-1;
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
        // url:basePath+'/courseRecord/list',
        remoteSort:false,
        idField:'fldId',
        singleSelect: true,
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'luruShijian',title:'门诊名称',width:'15%',align:'center',editor: 'text'},
            {field:'type',title:'输入码',width:'10%',align:'center',editor: 'text'},
            {field:'keshi',title:'门诊科室',width:'15%',align:'center',editor: {
                type: 'combobox',
                options: {
                    data: [{value:1,text:'骨科'}],
                    valueField: 'value',
                    textField: 'text'
                }
            }},
            {field:'type1',title:'医师',width:'13%',align:'center',editor: {
                type: 'combobox',
                options: {
                    data: [{value:1,text:'张三'}],
                    valueField: 'value',
                    textField: 'text'
                }
            }},
            {field:'type2',title:'医师职称',width:'10%',align:'center',editor: {
                type: 'combobox',
                options: {
                    data: [{value:1,text:'主治医生'}],
                    valueField: 'value',
                    textField: 'text'
                }
            }},
            {field:'type3',title:'号类',width:'15%',align:'center',editor: {
                type: 'combobox',
                options: {
                    data: [{value:1,text:'急症号'}],
                    valueField: 'value',
                    textField: 'text',
                    required: true
                }
            }},
            {field:'type4',title:'门诊位置',width:'15%',align:'center',editor: 'text'},
            {field:'type5',title:'号别序号',width:'5%',align:'center',editor: 'text'},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function() {
                if(rowNum>=0){
                    rowNum++;
                }
                $("#list_data").datagrid('insertRow', {
                    index: 0,
                    row: {}
                });
            }
        }, '-',{
            text: '修改',
            iconCls: 'icon-edit',
            handler: function() {

            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){

            }
        }],onClickRow: function (rowIndex, rowData) {
            var dataGrid=$('#list_data');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false
            }
            if(rowNum!=rowIndex){
                if(rowNum>=0){
                    dataGrid.datagrid('endEdit', rowNum);
                }
                rowNum=rowIndex;
                dataGrid.datagrid('beginEdit', rowIndex);
            }
        }
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
}


