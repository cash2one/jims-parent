
$(function() {
    $('#tg').treegrid({
        rownumbers: true,
        animate: true,
        singleSelect: true,
        fit: true,
        method: 'get',
        idField: 'id',
        treeField: 'type',
        columns:[[      //每个列具体内容
            {field:'type',title:'诊断类型',width:'10%',align:'center',formatter:diagnosisTypeFormatter,editor:{
                type:'combobox',
                options:{
                    data :diagnosisType,
                    valueField:'value',
                    textField:'label',
                    required:true
                }
            }
            },
            {field:'description',title:'诊断描述',width:'20%',align:'center',editor:'text'},
            {field:'treatResult',title:'治疗结果',width:'20%',align:'center',editor:'text'},
            {field:'operTreatIndicator',title:'手术',width:'5%',align:'center',editor:'text'
                ,formatter:function(value, rowData, rowIndex){
                  if(value=='1'){
                       return '是';
                  }else{
                      return '否';
                  }
            }
            },
            {field:'diagnosisDate',title:'诊断日期',width:'15%',align:'center',formatter:formatDateBoxFull},
            {field:'pathologyNo',title:'病理号',width:'10%',align:'center',editor:'text'},
            {field:'icdName',title:'诊断名称',width:'20%',align:'center'}

        ]],
        toolbar:
            [
                {
                    text: '添加诊断',
                    iconCls: 'icon-add', // handler: InsertData
                    handler:function(){
                        insert();
                    }
                },
                {
                    text: '添加下级诊断',
                    iconCls: 'icon-add', // handler: InsertData
                    handler:function(){
                        addNextLevel();
                    }
                },
                {
                    text: '编辑',
                    iconCls: 'icon-edit',
                    handler:function(){
                        edit();
                    }
                },
                {
                    text: '删除',
                    iconCls: 'icon-del',
                    handler:function(){
                        del();
                    }
                },
                {
                    text: '刷新',
                    iconCls: 'icon-reload',
                    handler:function(){
                        refash();
                    }
                }
            ]
    });
    loadMenu();
});

function loadMenu() {
    var patientId = parent.patVisit.patientId;
    var visitId = parent.patVisit.visitId;
    var menus = [];//菜单列表
    var menuTreeData = [];//菜单树的列表
    var menuPromise = $.get(basePath + '/diagnosis/findListOfIn?patientId='+patientId+'&visitId='+visitId, function (data) {
        $.each(data, function (index, item) {
            var d = {};
            d.id = item.id;
            d.type = item.type;
            d.description = item.description;
            d.treatResult = item.treatResult;
            d.diagnosisDate = formatDatebox(item.diagnosisDate);
            d.operTreatIndicator = item.operTreatIndicator;
            d.pathologyNo = item.pathologyNo;
            d.icdName = item.icdName;

           // d.diagnosisId = item.diagnosisId;
            d.parentId = item.parentId;
            d.children = [];
            menus.push(d);
        });
        for (var i = 0; i < menus.length; i++) {
            //判断儿子节点
            for(var j = 0 ;j<menus.length;j++){
                if(menus[i].id ==menus[j].parentId){
                    menus[i].children.push(menus[j]) ;
                }
            }
            if(menus[i].children.length>0 && !menus[i].parentId){
                menuTreeData.push(menus[i]) ;
            }

            if(!menus[i].parentId&&menus[i].children.length<=0){
                menuTreeData.push(menus[i]) ;
            }

            if(menus[i].parentId=="0"||menus[i].parentId==""){
                menuTreeData.push(menus[i]);
            }
        }
        $("#tg").treegrid('loadData',menuTreeData) ;
       // $("#tg").treegrid("selectRow", 1);
        return false;
    });

}

function formatProgress(value){
    if (value){
        var s = '<div style="width:100%;border:1px solid #ccc">' +
            '<div style="width:' + value + '%;background:#cc0000;color:#fff">' + value + '%' + '</div>'
        '</div>';
        return s;
    } else {
        return '';
    }
}
var editingId;
function edit(){

    var node = $("#tg").treegrid('getSelected');
    if (node == null) {
        $.messager.alert("系统提示", "请选中要修改的诊断");
        return;
    }
    $('#dlg').dialog('open').dialog('center').dialog('setTitle', '修改诊断');

    $('#fm').form('clear');
    $('#typeId').val(node.type);
    var typeName = diagnosisTypeFormatter(node.type,'','');
    $('#type').textbox('setValue',typeName);
    $("#type").attr("readonly", true) ;
    $("#description").val(node.description);
    $("#treatResult").val(node.treatResult);
    $('#diagnosisId').val(node.icdName);
    $("#diagnosisDate").datebox("setValue",node.diagnosisDate);
    if(node.operTreatIndicator=='1'){
        $("#operTreatIndicator").attr("checked", true) ;
    }else{
        $("#operTreatIndicator").attr("checked", false) ;
    }
    $('#diagnosisId').combogrid({
        panelWidth: 200,
        data:icdAllData,
        idField:'code',
        textField:'zhongwen_mingcheng',
        columns:[
            [
                {field: 'zhongwen_mingcheng', title: '中文名称', width: '40%', align: 'center'},
                {field: 'code', title: 'ICD-10编码', width: '10%', align: 'center'},
                {field: 'keyword_shuoming', title: '关键词', width: '50%', align: 'center'},
            ]
        ],onClickRow:function(rowIndex,rowData) {
            $("#icdName").val(rowData.zhongwen_mingcheng);
        },
        keyHandler: {
            up: function() {},
            down: function() {},
            enter: function() {},
            query: function(q) {
                icdCompleting(q,'diagnosisId');
                $('#diagnosisId').combogrid('grid').datagrid("loadData", icdAllData);
                $('#diagnosisId').combogrid("setText",q);

            }
        }
    })
    $('#pathologyNoId').textbox('setValue',node.pathologyNo);
    $("#id").val(node.id);
    $("#parentId").val(node._parentId);
    $("#inOrOutFlag").val("1");
}


//保存

function save(){
    var patientId = parent.patVisit.patientId;
    var visitId = parent.patVisit.visitId;
    $("#patientId").val(patientId);
    $("#visitId").val(visitId);
    if ($("#fm").form('validate')) {
        var d = {};
        d.id = $("#id").val();
        d.type =$('#typeId').val();
        d.description = $("#description").val();
        d.treatResult =  $("#treatResult").val();
        d.diagnosisDate = $("#diagnosisDate").datebox('getValue');
        d.operTreatIndicator =  $("#operTreatIndicator").val();
        d.pathologyNo = $("#pathologyNoId").val();
        d.diagnosisId =$('#diagnosisId').combogrid("getValue");
        d.icdName = $("#icdName").val();
        d.parentId = $("#parentId").val();
        d.patientId =patientId;
        d.visitId = visitId;
        d.inOrOutFlag = $("#inOrOutFlag").val();
        if($("#parentId").val()==null||$("#parentId").val()=='undefined'||$("#parentId").val()==''){
            d.parentId='0';
        }
        $.postJSON(basePath+"/diagnosis/saveIn",  JSON.stringify(d), function (data) {
          if(data.code>0){
              $.messager.alert('提示',"保存成功");

              $('#dlg').dialog('close');
              if(d.id==''|| d.id==null){
                  $("#tg").treegrid('append',{
                      parent:d.parentId,
                      data:[d]
                  })
              }else{
                  $("#tg").treegrid('update',{
                      id:d.id,
                      row:d
                  })
              }
              $('#tg').treegrid('reload');
          }else{
              $.messager.alert('提示',"保存失败", "error");
          }

        },function(){
            $.messager.alert('提示',"保存失败", "error");
        })

    }
}
function cancel(){
    if (editingId != undefined){
        $('#tg').treegrid('cancelEdit', editingId);
        editingId = undefined;
    }
}

function refash(){
    $('#tg').treegrid('reload');
}

function insert(){

    $("#dlg").dialog({title: '添加诊断'}).dialog("open").dialog('center');
    $('#fm').form('clear');
        $("#parentId").val("0");

        $('#type').combobox({
            data :diagnosisType,
            valueField:'value',
            textField:'label',
            onSelect: function (n, o) {
                $("#typeId").val(n.value);
            }
        });
    $('#diagnosisId').combogrid({
        panelWidth: 200,
        data:icdAllData,
        idField:'code',
        textField:'zhongwen_mingcheng',
        columns:[
            [
                {field: 'zhongwen_mingcheng', title: '中文名称', width: '40%', align: 'center'},
                {field: 'code', title: 'ICD-10编码', width: '10%', align: 'center'},
                {field: 'keyword_shuoming', title: '关键词', width: '50%', align: 'center'},
            ]
        ],onClickRow:function(rowIndex,rowData) {
            $("#icdName").val(rowData.zhongwen_mingcheng);
        },keyHandler: {
            up: function() {},
            down: function() {},
            enter: function() {},
            query: function(q) {
                icdCompleting(q,'diagnosisId');
                $('#diagnosisId').combogrid('grid').datagrid("loadData", icdAllData);
                $('#diagnosisId').combogrid("setText",q);
            }
        }
    })


}

/**
 * 添加下级诊断
 */
function addNextLevel() {
    var node = $("#tg").treegrid('getSelected');

    if (node==null) {
        $.messager.alert("系统提示", "请选择，所添加诊断的同一级的任意一个诊断");
        return;
    }

    if(node.id==''){
        $.messager.alert("系统提示",'所选诊断为新添加诊断，请刷新后，添加子诊断','info') ;
        return ;
    }
    if (node!=null) {

        $('#dlg').dialog('open').dialog('setTitle', '添加子诊断').dialog('center');
        $('#fm').form('clear');
        $('#typeId').val(node.type);
        var typeName = diagnosisTypeFormatter(node.type,'','');
        $('#type').textbox('setValue',typeName);
        $("#parentId").val(node.id);

        $('#diagnosisId').combogrid({
            panelWidth: 200,
            data:icdAllData,
            idField:'code',
            textField:'zhongwen_mingcheng',
            columns:[
                [
                    {field: 'zhongwen_mingcheng', title: '中文名称', width: '40%', align: 'center'},
                    {field: 'code', title: 'ICD-10编码', width: '10%', align: 'center'},
                    {field: 'keyword_shuoming', title: '关键词', width: '50%', align: 'center'},
                ]
            ],onClickRow:function(rowIndex,rowData) {
            $("#icdName").val(rowData.zhongwen_mingcheng);
            },keyHandler: {
                up: function() {},
                down: function() {},
                enter: function() {},
                query: function(q) {
                    icdCompleting(q,'diagnosisId');
                    $('#diagnosisId').combogrid('grid').datagrid("loadData", icdAllData);
                    $('#diagnosisId').combogrid("setText",q);
                }
            }
        })

    }
}

//删除
function del() {
    //把你选中的 数据查询出来。
    var row = $('#tg').treegrid('getSelected');

    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            $.ajax({
                'type': 'POST',
                'url': basePath+'/diagnosis/delete',
                'contentType': 'application/json',
                'data': id=row.id,
                'dataType': 'json',
                'success': function(data){
                    if(data.code=='1'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        $("#tg").treegrid('remove', row.id);

                    }else{
                        $.messager.alert('提示',"删除失败", "error");
                    }
                },
                'error': function(data){
                    $.messager.alert('提示',"删除失败", "error");
                }
            });
        }
    })
}