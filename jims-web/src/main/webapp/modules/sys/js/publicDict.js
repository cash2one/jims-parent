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
        url:basePath+'/dict/list',
        //sortName: 'code',
        //sortOrder: 'desc',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            {field:'value',title:'键值',width:'10%',align:'center'},
            {field:'label',title:'标签',width:'10%',align:'center'},
            {field:'type',title:'类型',width:'10%',align:'center'},
            {field:'description',title:'描述',width:'18%',align:'center'},
            {field:'sort',title:'排序',width:'18%',align:'center'},
            {field:'id',title:'操作',width:'30%',align:'center',formatter:function(value, row, index){
                return '<a href="#">查看</a>&nbsp<a href="#">修改</a>&nbsp<a href="#">删除</a>';
            }}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                $("#dlg").dialog({title: '添加字典信息'}).dialog("open")
            }
        }, '-', {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function() {
                $("#dlg").dialog({title: '修改字典信息'}).dialog("open")
            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
            }
        }]
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
}