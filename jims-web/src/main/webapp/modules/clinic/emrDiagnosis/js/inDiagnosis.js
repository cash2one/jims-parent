var administration = [{ "value": "1", "text": "初步诊断" }, { "value": "2", "text": "鉴别诊断" }, { "value": "4", "text": "入院诊断" }];
$(function() {
    $('#tg').treegrid({
        rownumbers: true,
        animate: true,
        collapsible: true,
        fitColumns: true,
       // url: 'treegrid_data2.json',
        method: 'get',
        idField: 'id',
        treeField: 'type',
        // showFooter: true,
        columns:[[      //每个列具体内容
            //  {field:'id',title:'序号',width:'5%',align:'center',editor:'hidden'},
            /*{field:'itemNo',title:'序号',width:'5%',align:'center',editor:'text'},*/
            {field:'type',title:'诊断类型',width:'10%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :administration,
                    valueField:'value',
                    textField:'text',
                    required:true,
                    onLoadSuccess: function () {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].text);
                    }
                }

            }
            },
            {field:'description',title:'诊断描述',width:'20%',align:'center',editor:'text'},
            {field:'treatResult',title:'治疗结果',width:'20%',align:'center',editor:'text'},
            {field:'operTreatIndicator',title:'手术',width:'5%',align:'center',editor:'text'},
            {field:'diagnosisDate',title:'诊断日期',width:'15%',align:'center',editor:'datebox'},
            {field:'pathologyNo',title:'病理号',width:'10%',align:'center',editor:'text'},
            {field:'diagnosisId',title:'诊断名称',width:'20%',align:'center',editor:{
                type:'combobox',
                options:{
                    required:true,
                    url: basePath+'/dataicd/autoComplete',
                    valueField: 'code',
                    textField: 'keywordShuoming',
                    method: 'GET'

                }
            }}

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
                }
            ]
    });
});
loadMenu();
function loadMenu() {
    var menus = [];//菜单列表
    var menuTreeData = [];//菜单树的列表
    var menuPromise = $.get(basePath + '/diagnosis/findList', function (data) {
        $.each(data, function (index, item) {
            var d = {};
            d.id = item.id;
            d.type = item.type;
            d.description = item.description;
            d.treatResult = item.treatResult;
            d.diagnosisDate = formatDatebox(item.diagnosisDate);
            d.operTreatIndicator = item.operTreatIndicator;
            d.pathologyNo = item.pathologyNo;
            d.diagnosisId = item.diagnosisId;
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
    //menuPromise.done(function () {
    //
    //});

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
    $('#type').combobox({
        data :administration,
        editable:false,
        valueField:'value',
        textField:'text',
        method: 'GET',
        onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            $(this).combobox('select', node.type);
        }
    });
    $("#description").textbox('setValue', node.description);
    $("#treatResult").textbox('setValue', node.treatResult);
    $("#diagnosisDate").datebox("setValue",node.diagnosisDate);
    $("#operTreatIndicator").textbox('setValue', node.operTreatIndicator);
    $("#pathologyNo").textbox('setValue', node.pathologyNo);
    $("#id").val(node.id);
    $("#parentId").val(node._parentId);
    $('#diagnosisId').combobox({
        url: basePath+'/dataicd/autoComplete',
        editable:false,
        valueField: 'code',
        textField: 'keywordShuoming',
        method: 'GET',
        onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            $(this).combobox('select', node.diagnosisId);
        }



    });
}


//保存

function save(){
    if ($("#fm").form('validate')) {
        var d = {};
        d.id = $("#id").val();
        d.type =$('#type').combobox('getValue');
        d.description = $("#description").val();
        d.treatResult =  $("#treatResult").val();
        d.diagnosisDate = $("#diagnosisDate").datebox('getValue');
        d.operTreatIndicator =  $("#operTreatIndicator").val();
        d.pathologyNo = $("#pathologyNo").val();
        d.diagnosisId =$('#diagnosisId').combobox('getValue');
        d.parentId = $("#parentId").val();
        if($("#parentId").val()==null||$("#parentId").val()=='undefined'||$("#parentId").val()==''){
            d.parentId='0';
        }
        $.postJSON(basePath+"/diagnosis/saveIn",  JSON.stringify(d), function (data) {
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

function cancel(){
    if (editingId != undefined){
        $('#tg').treegrid('cancelEdit', editingId);
        editingId = undefined;
    }
}


function insert(){
   /* var node = $("#tg").treegrid('getSelected');
    if (!node) {
        $.messager.alert("系统提示", "请选择，所添加诊断的同一级的任意一个诊断");
        return;
    }
    if (node) {*/
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加同级诊断');
        $('#fm').form('clear');
        //    $("#parentId").val(node._parentId);
        $("#parentId").val("0");
        $('#type').combobox({
            data :administration,
            valueField:'value',
            textField:'text',
            method: 'GET'
        });
        $('#diagnosisId').combobox({

            required:true,
            url: basePath+'/dataicd/autoComplete',
            valueField: 'code',
            textField: 'keywordShuoming',
            method: 'GET'
        });

        //  $("#position").textbox('setValue', node.position);
  //  }
}

/**
 * 添加下级诊断
 */
function addNextLevel() {
    var node = $("#tg").treegrid('getSelected');

    if (node==null) {
        alert(1);
        $.messager.alert("系统提示", "请选择，所添加诊断的同一级的任意一个诊断");
        return;
    }

    if(node.id==''){
        alert(2);
        $.messager.alert("系统提示",'所选诊断为新添加诊断，请刷新后，添加子诊断','info') ;
        return ;
    }
    if (node!=null) {

        $('#dlg').dialog('open').dialog('setTitle', '添加子诊断');
        $('#fm').form('clear');
        /*    $("#parentName").textbox('setValue', node.menuName);*/
        $("#parentId").val(node.id);
        $('#type').combobox({
            data :administration,
            valueField:'value',
            textField:'text',
            method: 'GET'


        });
        $('#diagnosisId').combobox({

            required:true,
            url: basePath+'/dataicd/autoComplete',
            valueField: 'code',
            textField: 'keywordShuoming',
            method: 'GET'
        });

        $("#type").attr({"disabled":true});
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
                        $('#tg').treegrid('reload');
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");

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