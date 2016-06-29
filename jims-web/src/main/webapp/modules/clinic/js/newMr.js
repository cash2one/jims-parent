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
        method:'GET',
        url:basePath+'/patList/getPatMaster',
        //sortName: 'code',
        //sortOrder: 'desc',
        remoteSort:false,
        idField:'id',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            /*{field:'id',title:'病人ID号',width:'10%',align:'center'},*/
            {field:'inpNo',title:'病人住院号',width:'10%',align:'center'},
            {field:'name',title:'病人姓名',width:'10%',align:'center'},
            {field:'sex',title:'性别',width:'10%',align:'center'},
            {field:'bedNo',title:'床号',width:'10%',align:'center'},
            {field:'bedLabel',title:'床标号',width:'10%',align:'center'},
            {field:'diagnosis',title:'主要诊断',width:'10%',align:'center'},
            {field:'doctorInCharge',title:'经治医师',width:'10%',align:'center'},
        ]],
        onDblClickRow: function (rowIndex, rowData) {
            parent.addTabs(rowData.name,rowData.name,'/modules/clinic/patientHospital.html');
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

