var administration = [{ "value": "1", "text": "初步诊断" }, { "value": "2", "text": "鉴别诊断" }, { "value": "4", "text": "入院诊断" }];
$(function() {
    $('#tg').treegrid({
        rownumbers: true,
        animate: true,
        collapsible: true,
        fitColumns: true,
<<<<<<< HEAD
        //url: '/modules/clinic/emrDiagnosis/js/treegrid_data2.json',
       url:basePath+'/diagnosis/findList',
=======
        url: '/modules/clinic/emrDiagnosis/js/treegrid_data2.json',
>>>>>>> e2a5ef4bd4b40d39cead9dd2f7db47d13f28467a
        method: 'get',
        idField: 'id',
        treeField: 'type',
        showFooter: true,
        columns:[[      //每个列具体内容
            // {field:'id',title:'序号',width:'5%',align:'center',editor:'text'},
            /*{field:'itemNo',title:'序号',width:'5%',align:'center',editor:'text'},*/
            {field:'type',title:'诊断类型',width:'10%',align:'center',editor:'text',editor:{
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
                    method: 'GET',
                    onLoadSuccess: function () {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].keywordShuoming);
                    }
                }
            }}


        ]]

    });
});


















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
    if (editingId != undefined){
        $('#tg').treegrid('select', editingId);
        return;
    }
    var row = $('#tg').treegrid('getSelected');
    if (row){
        editingId = row.id
        $('#tg').treegrid('beginEdit', editingId);
    }
}
function save(){
    if (editingId != undefined){
        var t = $('#tg');
        t.treegrid('endEdit', editingId);
        editingId = undefined;
        var persons = 0;
        var rows = t.treegrid('getChildren');
        for(var i=0; i<rows.length; i++){
            var p = parseInt(rows[i].persons);
            if (!isNaN(p)){
                persons += p;
            }
        }
        var frow = t.treegrid('getFooterRows')[0];
        frow.persons = persons;
        t.treegrid('reloadFooter');
    }
}
function cancel(){
    if (editingId != undefined){
        $('#tg').treegrid('cancelEdit', editingId);
        editingId = undefined;
    }
<<<<<<< HEAD
}


function insert(){
 /*   var node = $("#tg").treegrid('getSelected');
    if (!node) {
        $.messager.alert("系统提示", "请选择，所添加诊断的同一级的任意一个诊断");
        return;
    }
    if (node) {*/
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加同级诊断');
        $('#fm').form('clear');
    //    $("#parentId").val(node._parentId);
        $("#parentId").val(node._parentId);
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

      /*  var node = $("#tg").treegrid('getSelected');

        if (!node) {
            alert(111111111);
            $.messager.alert("系统提示", "请选择，所添加诊断的同一级的任意一个诊断");
            return;
        }
        if (node) {
            alert("node="+node);
            $('#dlg').dialog('center').dialog('setTitle', '添加同级诊断');
            $('#fm').form('clear');
              $('#type').combobox({
                data :administration,
                valueField:'value',
                textField:'text',
                method: 'GET',
                onLoadSuccess: function (data) {
                    var data = $(this).combobox('getData');
                    $(this).combobox('select', data[0].text);
                }
            });

         $("#parentId").val(node._parentId);

          $("#diagnosisId").combobox({
                required:true,
                url: basePath+'/dataicd/autoComplete',
                valueField: 'code',
                textField: 'keywordShuoming',
                method: 'GET',
                onLoadSuccess: function () {
                    var data = $(this).combobox('getData');
                    $(this).combobox('select', data[0].keywordShuoming);
                }
            });

        }
*/


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
=======
>>>>>>> e2a5ef4bd4b40d39cead9dd2f7db47d13f28467a
}