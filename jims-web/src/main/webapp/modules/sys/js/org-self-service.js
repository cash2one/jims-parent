$(function() {
    var currentOrgId = '1';

    $("#orgSelfService").datagrid({
        fit: true,
        border: 0,
        singleSelect: true,
        remoteSort: false,
        url : '/service/org-service/find-self-service?orgId=' + currentOrgId,
        method: 'GET',
        idField: 'id',
        toolbar: '#tbn',
        columns: [[
            {field: 'serviceName', title: '服务', width: 260, align: "center"},
            {field: 'menuName', title: '上传图片', width: 160, align: "center"}
        ]]
    })
    $('#addButton').click(function(){
        var row = {}
        $('#orgSelfService').datagrid('appendRow',row)
    })
    $('#delButton').click(function(){
        var row = $('#orgSelfService').datagrid('getSelected');
        if(row){
            $('#orgSelfService').datagrid('deleteRow',$('#orgSelfService').datagrid('getRowIndex',row))
        } else {
            $.messager.alert('警告','请选择要删除的服务！','warning')
        }

    })
    $('#menuButton').click(function(){
        var row = $('#orgSelfService').datagrid('getSelected');
        if(row){
            if(row.manageMenus){
                $('#selfServiceMenu').datagrid('loadData', row.manageMenus)
            } else {
                $.get('/service/org-service/find-self-service-menu', {selfServiceId: row.id}, function (res) {
                    $('#selfServiceMenu').datagrid('loadData', res)
                })
            }
            $('#selfServiceMenuWin').window('open')
        } else {
            $.messager.alert('警告','请选择要维护菜单的服务！','warning')
        }
    })
    $('#saveButton').click(function(){
        var delRows = $('#orgSelfService').datagrid('getChanges','deleted');
        var addRows = $('#orgSelfService').datagrid('getChanges','deleted');
        var updateRows = $('#orgSelfService').datagrid('getChanges','updated');
        var updateMenus = []
        var deleteMunusId = ''
        var allRows = $('#orgSelfService').datagrid('getRows');
        for(var i=0;i<allRows.length;i++){
            if(allRows[i].id && allRows[i].manageMenus){
                var menus = allRows[i].manageMenus;
                for(var j=menus.length-1 ;j> -1;j--){
                    if(menus[j].id && menus[j].menuSort == j+1){
                        menus.splice(j,1);
                        continue;
                    }
                    menus[j].menuSort = j+1;
                    if(menus[j].delete)
                    updateMenus.push(menus[j])
                }
            }
        }

        alert(JSON.stringify(updateMenus))
    })

    //服务菜单维护窗口
    $('#selfServiceMenuWin').window({
        title: '服务菜单维护',
        width: '750',
        height: '450',
        collapsible :false,
        minimizable : false,
        maximizable : false,
        modal : true,
        resizable: false
    })
    $('#selfServiceMenuWin').window('close')
    // 已分配给选择的服务的菜单
    $('#selfServiceMenu').datagrid({
        fit: true,
        border:0,
        footer: '#menuBtn',
        idField: 'id',
        rownumbers: true,
        columns: [[
            {field: 'serviceName', title: '菜单名称', width: 193, halign: "center",aligh:'left'},
            {field: 'endStart', title: '有效期', width: 145, align: "center"}
        ]]
    })
    // 机构所定制的服务
    $('#selectServiceMenu').propertygrid({
        fit: true,
        showGroup: true,
        border:0,
        scrollbarSize: 0,
        columns: [[
            {field: 'name', title: '菜单名称', width: 187, halign: "center",aligh:'left'},
            {field: 'value', title: '有效期', width: 145, align: "center"}
        ]],
        onClickRow: function(index,row){
            $('#selfServiceMenu').datagrid('unselectAll')
            var menuRows = $('#selfServiceMenu').datagrid('getRows')
            for(var i=0;i<menuRows.length;i++){
                if(menuRows[i].menuId == row.id){
                    $('#selfServiceMenu').datagrid('selectRow',i);
                    return ;
                }
            }
            var menuRow = {
                serviceName: row.name,
                endStart: row.value,
                menuId: row.id
            };
            $('#selfServiceMenu').datagrid('appendRow',menuRow);
            $('#selfServiceMenu').datagrid('selectRow',i);
        }
    });

    $('#delMenu').click(function(){
        var row = $('#selfServiceMenu').datagrid('getSelected');
        if(row){
            do{
                $('#selfServiceMenu').datagrid('deleteRow',$('#selfServiceMenu').datagrid('getRowIndex',row));
                row = $('#selfServiceMenu').datagrid('getSelected');
            } while (row)
        } else {
            $.messager.alert('警告','请选择要删除的菜单！','warning')
        }
    })
    $('#okMenu').click(function(){
        var rows = $('#selfServiceMenu').datagrid('getRows');
        var row = $('#orgSelfService').datagrid('getSelected');
        row.manageMenus = rows;
        var delRows = $('#selfServiceMenu').datagrid('getChanges','deleted');
        if(!row.deleteRowsId) row.deleteRowsId = []
        for(var i=0;i<delRows.length;i++){
            if(delRows[i].id)
                row.deleteRowsId.push(delRows[i].id)
        }
        $('#selfServiceMenuWin').window('close')
    })

    //加载机构所选择的服务
    $.get('/service/org-service/find-service',{orgId:currentOrgId},function(res){
        if(res){
            for(var i=0;i<res.length;i++){
                var group = res[i].serviceName
                var menus = res[i].menus
                for(var j= 0,k=(menus ? menus.length : 0);j<k;j++){
                    var menuRow = {
                        id : menus[j].id,
                        name: menus[j].menuName,
                        value : '1111-11-11',
                        group : group
                    }
                    $('#selectServiceMenu').propertygrid('appendRow',menuRow);
                }
            }
        }
    })
})
