function onloadMethod(){
    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: '86%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible:false,//是否可折叠的
        //fit: true,//自动大小
        url:'/modules/clinic/datagrid_data2.json',
        //sortName: 'code',
        //sortOrder: 'desc',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            {field:'id',title:'病人ID号',width:'10%',align:'center'},
            {field:'id',title:'病人住院号',width:'10%',align:'center'},
            {field:'id',title:'床号',width:'10%',align:'center'},
            {field:'id',title:'病人姓名',width:'10%',align:'center'},
            {field:'text',title:'性别',width:'10%',align:'center'},
            {field:'age',title:'年龄',width:'10%',align:'center'},
            {field:'age',title:'病人',width:'10%',align:'center'},
            {field:'age',title:'费别',width:'10%',align:'center'},
            {field:'age',title:'住院天数',width:'10%',align:'center'},
            {field:'age',title:'管床大夫',width:'10%',align:'center'},
            {field:'age',title:'入院时间',width:'10%',align:'center'},
            {field:'age',title:'上级医师',width:'10%',align:'center'},
            {field:'age',title:'主任医师',width:'10%',align:'center'},
            {field:'age',title:'出院时间',width:'10%',align:'center'},
            {field:'age',title:'出生日期',width:'10%',align:'center'},
            {field:'age',title:'身份',width:'10%',align:'center'},
            {field:'type',title:'预交金',width:'10%',align:'center'},
            {field:'age',title:'护理单元',width:'10%',align:'center'}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '新建病历',
            iconCls: 'icon-add',
            handler: function() {

            }
        }, '-', {
            text: '移入病历',
            iconCls: 'icon-large-smartart',
            handler: function() {

            }
        }, '-',{
            text: '移除病历',
            iconCls: 'icon-remove',
            handler: function(){

            }
        }, '-',{
            text: '刷新病人列表',
            iconCls: 'icon-reload',
            handler: function(){

            }
        }, '-',{
            text: '切换图标',
            iconCls: 'icon-undo',
            handler: function(){

            }
        }],
        onDblClickRow: function (rowIndex, rowData) {
            parent.addTabs(rowData.id,rowData.id,'/modules/clinic/patientHospital.html');
        },onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
            e.preventDefault(); //阻止浏览器捕获右键事件
            $(this).datagrid("clearSelections"); //取消所有选中项
            $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
            $('#menu').menu('show', {
                left: e.pageX,//在鼠标点击处显示菜单
                top: e.pageY
            });
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

}


