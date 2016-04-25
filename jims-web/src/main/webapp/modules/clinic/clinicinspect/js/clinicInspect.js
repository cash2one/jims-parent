function onloadMethod() {
    $(function () {
        var province = $('#province').combobox({
            valueField: 'areaid',
            textField: 'name',
            editable: false,
          //  url: ,
            onChange: function (newValue, oldValue) {
                $.get('cascade-data.php', {parentid: newValue}, function (data) {
                    city.combobox("clear").combobox('loadData', data);
                    county.combobox("clear")
                }, 'json');
            }
        });
        var city = $('#city').combobox({
            valueField: 'areaid',
            textField: 'name',
            editable: false,
            onChange: function (newValue, oldValue) {
                $.get('cascade-data.php', {parentid: newValue}, function (data) {
                    county.combobox("clear").combobox('loadData', data);
                }, 'json');
            }
        });
        var county = $('#county').combobox({
            valueField: 'areaid',
            textField: 'name',
            editable: false
        });
    });

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
        url:basePath+'/clinicInspect/list',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'reqDateTime',title:'申请日期',width:'25%',align:'center',formatter:formatDateBoxFull},
            {field:'examItem',title:'检查项目名称',width:'20%',align:'center'},
            {field:'reqDept',title:'检查科室',width:'20%',align:'center'},
            {field:'id',title:'操作',width:'33%',align:'center',formatter:function(value, row, index){
                var html='<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="get(\''+row.id+'\',\''+row.type+'\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>'+
                    '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\''+value+'\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                return html;
            }}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '修改',
            iconCls: 'icon-edit',
            handler: function() {
                var selectRows = $('#list_data').datagrid("getSelections");
                if (selectRows.length < 1) {
                    $.messager.alert("提示消息", "请选中需要修改的数据");
                    return;
                }
                get(selectRows[0].id);
            }
        }, '-',{
            text: '批量删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
            }
        }]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');

}