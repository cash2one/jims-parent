$(function() {
    $.extend($.fn.datagrid.methods, {
        editCell: function(jq,param){
            return jq.each(function(){
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field){
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        nameIsExisted :{
            validator : function(value){
                var rows = $('#orgSelfService').datagrid('getRows')
                var select_index = $('#orgSelfService').datagrid('getRowIndex',$('#orgSelfService').datagrid('getSelected'))
                for(var index = rows.length - 1;index > -1;index--) {
                    if(index != select_index && rows[index].serviceName == value){
                        return false
                    }
                }
                return true
            },
            message : '名称已存在'
        }
    })

    var currentOrgId = 'd1f3875c3c3b4668a347a81f1094d52b';  // 当前机构ID
    var currentSelectIndex;  // 服务当前选择行

    var endEditing = function (){
        if (currentSelectIndex == undefined){
            return true;
        }
        if ($('#orgSelfService').datagrid('validateRow', currentSelectIndex)){
            $('#orgSelfService').datagrid('endEdit', currentSelectIndex);
            return true;
        } else {
            return false;
        }
    }
    var onClickCell = function (index, field){
        if (endEditing()){
            $('#orgSelfService').datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});
            currentSelectIndex = index;
        }
    }

    // 机构自定义服务
    $("#orgSelfService").datagrid({
        fit: true,
        border: 0,
        singleSelect: true,
        remoteSort: false,
        url : '/service/org-service/find-self-service?orgId=' + currentOrgId,
        loadMsg: '数据加载中，请稍后...',
        method: 'GET',
        idField: 'id',
        toolbar: '#tbn',
        columns: [[
            {field: 'serviceName', title: '服务', width: 260, halign: "center",align:"left",editor:{
                type:'textbox',
                options:{
                    required:true,
                    missingMessage:'服务名不能为空',
                    validType : ['nameIsExisted']
                }
            }},
            {field: 'menuName', title: '上传图片', width: 160, align: "center"}
        ]],
        onClickCell:onClickCell,
        onBeforeSelect: function(index){
            return $('#orgSelfService').datagrid('validateRow', currentSelectIndex);
        }
    })

    $('#clearButton').click(function(){
        var rows = $('#orgSelfService').datagrid('getRows');
        for(var i= 0,j=rows.length;i<j;i++){
            $('#orgSelfService').datagrid('deleteRow',0);
        }
    })
    $('#addButton').click(function(){
        if(endEditing()) {
            var row = {orgId: currentOrgId};
            $('#orgSelfService').datagrid('appendRow', row);
            onClickCell($('#orgSelfService').datagrid('getRows').length - 1,'serviceName');
        }
    })
    $('#delButton').click(function(){
        if(currentSelectIndex){
            $('#orgSelfService').datagrid('deleteRow',currentSelectIndex);
            currentSelectIndex = undefined;
        } else {
            $.messager.alert('警告','请选择要删除的服务！','warning');
        }

    })
    $('#menuButton').click(function(){
        if(!endEditing()) return false
        var row = $('#orgSelfService').datagrid('getSelected');
        if(row){
            if(row.menus){
                $('#selfServiceMenu').datagrid('loadData', row.menus)
            } else if(!row.id){
                $('#selfServiceMenu').datagrid('loadData', [])
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
        if($)
        if(!endEditing()) return
        //  服务删除数据
        var delRows = $('#orgSelfService').datagrid('getChanges','deleted');
        var delIds = ''
        for(var i=0;i<delRows.length;i++){
            delIds += ',' + delRows[i].id;
        }
        // 服务添加的数据(应包含添加的菜单)
        var addRows = $('#orgSelfService').datagrid('getChanges','inserted');
        // 服务修改的数据(不应该包含添加的菜单)
        var updateRows = $('#orgSelfService').datagrid('getChanges','updated');

        // 菜单更新的数据
        var updateMenus = [];
        // 菜单删除的数据
        var deleteMunusId = '';
        var allRows = $('#orgSelfService').datagrid('getRows');
        for(var i=0;i<allRows.length;i++){
            if(allRows[i].id) {
                if (allRows[i].menus) {
                    var menus = allRows[i].menus;
                    for (var j = menus.length - 1; j > -1; j--) {
                        if (menus[j].id && menus[j].menuSort == j + 1) {
                            continue;
                        }
                        delete menus[j].selfMenuName;
                        menus[j].menuEndDate = parent.parseToDate(menus[j].menuEndDate);
                        menus[j].menuSort = j + 1;
                        updateMenus.push(menus[j]);
                    }
                }
                if (allRows[i].deleteMunusId && allRows[i].deleteMunusId.length > 0) {
                    deleteMunusId += ',' + allRows[i].deleteMunusId.join(',');
                }
                delete allRows[i].menus;
            } else {
                if (allRows[i].menus) {
                    var menus = allRows[i].menus;
                    for (var j = menus.length - 1; j > -1; j--) {
                        menus[j].menuSort = j + 1;
                        menus[j].menuEndDate = parent.parseToDate(menus[j].menuEndDate);
                        delete menus[j].selfMenuName;
                    }
                }
            }

            delete allRows[i].deleteMunusId;
        }

        //合并处理保存数据
        var rows = addRows.concat(updateRows);
        if(delIds.length > 0){
            rows.push({operateFlag:'1',id:delIds.substr(1)});
        }
        if(deleteMunusId.length > 0 || updateMenus.length > 0){
            if(deleteMunusId.length > 0) deleteMunusId = deleteMunusId.substr(1);
            rows.push({operateFlag:'2',id:deleteMunusId,menus:updateMenus});
        }
        parent.$.postJSON('/service/org-service/save-self-service',JSON.stringify(rows),function(res){
            if(res == '1'){
                $.messager.alert('保存','保存成功！','info',function(){
                    window.location.reload();
                })
            } else {
                $.messager.alert('保存','保存失败！','error')
            }
        })
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
    });
    $('#selfServiceMenuWin').window('close');
    // 已分配给选择的服务的菜单
    $('#selfServiceMenu').datagrid({
        fit: true,
        border:0,
        footer: '#menuBtn',
        rownumbers: true,
        columns: [[
            {field: 'selfMenuName', title: '菜单名称', width: 193, halign: "center",aligh:'left'},
            {field: 'menuEndDate', title: '有效期', width: 145, align: "center",formatter: function(value){
                return parent.formatDateBoxFull(value)
            }}
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
                selfMenuName: row.name,
                selfServiceId: $('#orgSelfService').datagrid('getSelected').id,
                menuEndDate: row.value,
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
        row.menus = rows.concat([]);  // 防止联动
        var delRows = $('#selfServiceMenu').datagrid('getChanges','deleted');
        if(!row.deleteMunusId) row.deleteMunusId = []
        for(var i=0;i<delRows.length;i++){
            if(delRows[i].id)
                row.deleteMunusId.push(delRows[i].id)
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
                        value : parent.formatDateBoxFull(res[i].serviceEndDate),
                        group : group
                    }
                    $('#selectServiceMenu').propertygrid('appendRow',menuRow);
                }
            }
        }
    })
})
