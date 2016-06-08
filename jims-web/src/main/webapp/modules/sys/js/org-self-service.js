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

    var currentOrgId = '8c4c7d182b404aa1a770c75c62431e60';  // 当前机构ID
    var currentSelectIndex;  // 服务当前选择行
    var operatorFlag ;  // 删除菜单操作标志

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


    var p
    $('#selectedMenu').tree({
        animate:true,
        dnd:true,
        lines:true,
        onStopDrag:function(){
            while(p){
                var index = $('#selectServiceMenu').accordion('getPanelIndex',$('#selectServiceMenu').accordion('getSelected'))
                alert(JSON.stringify(p))
                alert($('#tree'+index).tree('isLeaf', p.target))
                if($('#tree'+index).tree('isLeaf', p.target)){
                    p = undefined;
                    break;
                }
                var t = $('#selectedMenu').tree('getParent', p.target);
                $('#selectedMenu').tree('remove', p.target);
                p = t;
            }
        },
        onBeforeDrop:function(t, s){
            p = $('#selectedMenu').tree('getParent', s.target);
        }

    })

    $('#delMenu').click(function(){
        var node = $('#selectedMenu').tree('getSelected');
        if(node){
            operatorFlag = true    // 使用来控制取消菜单选择界面
            var index = $('#selectServiceMenu').accordion('getPanelIndex',$('#selectServiceMenu').accordion('getSelected'))
            var parent;
            var child;
            do{
                parent = $('#selectedMenu').tree('getParent',node.target);
                $('#selectedMenu').tree('remove',node.target);
                var n = $('#tree'+index).tree('find',node.id);
                $('#tree'+index).tree('uncheck',n.target);
                if(parent)
                    child = $('#selectedMenu').tree('getChildren',parent.target)
                node = parent;
            } while(node && (!child || child.length == 0))

            operatorFlag = false
        } else {
            $.messager.alert('警告','请选择要删除的节点！','warning')
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

    var addMenu = function(node,treeId){
        if(node && treeId){
            var data = $('#'+treeId).tree('getData',node.target);
            var parentNode = $('#'+treeId).tree('getParent',node.target);
            var selectParent = $('#selectedMenu').tree('find',node.id);
            var d = data;
            // 处理已选菜单没有的菜单数据
            while(parentNode && !selectParent){
                var temp = d;
                var parentData = $('#'+treeId).tree('getData',parentNode.target);
                d = {
                    id:parentData.id,
                    text:parentData.text,
                    href:parentData.href,
                    endData:parentData.endData,
                    children:[temp]
                }
                selectParent = $('#selectedMenu').tree('find',parentData.id);
                parentNode = $('#'+treeId).tree('getParent',parentNode.target);
            }
            addMenu1(d,selectParent)
        }
    }
    var addMenu1 = function(data,parent){
        if(data){
            var d = $('#selectedMenu').tree('find',data.id);
            if(!d){
                var t = {
                    id:data.id,
                    text:data.text,
                    href:data.href,
                    endData:data.endData
                }
                var param = {data:t}
                if(parent){
                    param.parent = parent.target
                }
                $('#selectedMenu').tree('append',param)
            }
            var children = data.children
            if(children && children.length > 0) {
                for (var i = 0; i < children.length; i++) {
                    addMenu1(children[i],$('#selectedMenu').tree('find',data.id));
                }
            }
        }
    }

    //加载机构所选择的服务
    $.get('/service/org-service/find-service',{orgId:currentOrgId},function(res){
        var handlerTreeData = function(res,endData){
            var r = [];
            if(res){
                for(var j=0;j<res.length;j++){
                    var o = res[j];
                    var node = {
                        id: o.id,
                        text: o.menuName,
                        href: o.href,
                        endData: parent.formatDateBoxFull(endData)
                    }
                    if(o.children){
                        node.children = handlerTreeData(o.children,endData);
                    }
                    r.push(node);
                }
            }
            return r;
        }
        if(res) {
            for(var i=0;i<res.length;i++){
                $('#selectServiceMenu').accordion('add', {
                    title: res[i].serviceName,
                    content: '<ul class="easyui-tree" id="tree'+i+'"></ul>',
                    selected: false
                });
                $('#tree'+i).tree({
                    data:handlerTreeData(res[i].menus,res[i].serviceEndDate),
                    checkbox:true,
                    animate:true,
                    lines:true,
                    onCheck:function(o,check){
                        if(check){
                            var index = $('#selectServiceMenu').accordion('getPanelIndex',$('#selectServiceMenu').accordion('getSelected'))
                            addMenu(o,'tree'+index);
                        }
                    },onBeforeCheck:function(o,c){
                        if(operatorFlag) return true
                        return c
                    }
                })
            }
        }
    })
})
