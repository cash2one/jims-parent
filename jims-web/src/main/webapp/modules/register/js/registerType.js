
function onloadMethod(){
    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: '100%',
        height: '94%',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        //fit: true,//自动大小
        // url:basePath+'/courseRecord/list',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'luruShijian',title:'收费项目',width:'30%',align:'center'},
            {field:'keshi',title:'项目价表',width:'30%',align:'center'},
            {field:'type',title:'项目名称',width:'30%',align:'center'},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '增加',
            iconCls: 'icon-add',
            handler: function() {

            }
        }, {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function() {

            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){

            }
        }]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');

}


