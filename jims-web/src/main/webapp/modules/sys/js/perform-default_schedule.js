$("<script>").attr({type: "application/javascript", src: "/static/easyui/locale/easyui-lang-zh_CN.js"}).appendTo("head");
$(function(){
    $.extend($.fn.validatebox.defaults.rules, {
        minLength: {
            validator: function(value, param){
                return getByteLen(value) <= param[0];
            },
            message: '字符过长'
        }
    });

    // 当前radio选择值，0 全部，1频次，2给药途径
    var currentCheckRadio = '1'

    // 获取字符串长度
    var getByteLen = function(val) {
        var len = 0;
        for (var i = 0; i < val.length; i++) {
            var length = val.charCodeAt(i);
            if (length >= 0 && length <= 128) {
                len += 1;
            }
            else {
                len += 2;
            }
        }
        return len;
    }
    //加载datagrid数据
    var loadData = function(){
        var param = {}
        if(currentCheckRadio == '1'){
            param.freqDesc = $('#operateCombo').combobox('getValue')
        } else if(currentCheckRadio == '2'){
            param.administration = $('#operateCombo').combobox('getValue')
        }
        $.get('/service/performDefaultSchedule/findList',param,function(res){
            $('#scheduleTable').datagrid('loadData',res)
            $("#scheduleTable").datagrid('unselectAll')
        })
    }
    //加载combobox选框数据
    var loadComboData = function(){
        var url = '/service/performDefaultSchedule/findTypeList'
        if(currentCheckRadio == '1'){
            url += '?type=freqDesc'
        }
        $('#operateCombo').combobox('clear')
        $('#operateCombo').combobox('reload',url)
    }

    $(':radio[name="operateType"][value="0"]').click(function(){
        if(currentCheckRadio != '0') {
            currentCheckRadio = '0'
            loadData()
            $('#operateCombo').combobox('clear')
            $('#operateCombo').combobox('disable')
        }
    })
    $(':radio[name="operateType"][value="1"]').click(function(){
        if(currentCheckRadio != '1') {
            currentCheckRadio = '1'
            loadComboData('1');
            $('#scheduleTable').datagrid('loadData', []);
            $('#operateCombo').combobox('enable')
        }
    })
    $(':radio[name="operateType"][value="2"]').click(function(){
        if(currentCheckRadio != '2') {
            currentCheckRadio = '2'
            loadComboData('2');
            $('#scheduleTable').datagrid('loadData', [])
            $('#operateCombo').combobox('enable')
        }
    })

    $('#operateCombo').combobox({
        valueField:'id',
        textField:'administrationName',
        editable : false,
        url:'/service/performDefaultSchedule/findTypeList?type=freqDesc',
        method:'get',
        mode:'remote',
        onSelect:function(record){
            loadData()
        }
    })

    $("#scheduleTable").datagrid({
        fit : true,
        border:0,
        singleSelect : false,
        remoteSort: false,
        idField :'id',
        rownumbers:true,
        toolbar: '#tb',
        columns: [[
            { field: 'id', checkbox:true},
            { field: 'freqDescName', title: '执行频率', width: 120,align : "center" },
            { field: 'administrationName', title: '给药途径和方法', width: 150,align : "center" },
            { field: 'defaultSchedule', title: '缺省执行时间', width: 200,align : "center" }
        ]]
    });

    var selectTypeTemp = true
    $('#freqDesc').combobox({
        valueField:'id',
        textField:'freqDesc',
        width:155,
        url:'/service/performDefaultSchedule/findNoExistFreq',
        method:'get',
        required:true,
        onSelect: function(record){
            selectTypeTemp = false;
            var url = '/service/performDefaultSchedule/findNoExistAdministration?freqDesc='+record.id
            var value = $('#administration').combobox('getValue')
            $('#administration').combobox('reload',url)
            $('#administration').combobox('setValue',value)
        },
        onHidePanel: function(){
            if(selectTypeTemp){
                var nodes = $('.combobox-item[style="display: block;"]',$('#freqDesc').combobox('panel'))
                if(nodes.length > 0){
                    $(nodes[0]).click()
                } else {
                    $('#freqDesc').combobox('clear')
                }
            }
            selectTypeTemp = true;
        }
    })
    $('#administration').combobox({
        valueField:'id',
        textField:'administrationName',
        width:155,
        url:'/service/performDefaultSchedule/findNoExistAdministration',
        method:'get',
        required:true,
        onSelect: function(record){
            selectTypeTemp = false;
            var url = '/service/performDefaultSchedule/findNoExistFreq?administration='+record.id;
            var value = $('#freqDesc').combobox('getValue')
            $('#freqDesc').combobox('reload',url)
            $('#freqDesc').combobox('setValue',value)
        },
        onHidePanel: function(){
            if(selectTypeTemp){
                var nodes = $('.combobox-item[style="display: block;"]',$('#administration').combobox('panel'))
                if(nodes.length > 0){
                    $(nodes[0]).click()
                } else {
                    $('#administration').combobox('clear')
                }
            }
            selectTypeTemp = true;
        }
    })
    $('#defaultSchedule').textbox({
        width:155,
        validType:'minLength[16]',
        required:true
    })
    $('#addBtn').click(function(){
        $('#saveForm').form('clear')
        $('#freqDesc').combobox('enable');
        $('#administration').combobox('enable');
        $('#freqDesc').combobox('disableValidation')
        $('#administration').combobox('disableValidation')
        $('#defaultSchedule').combobox('disableValidation')
        $('#dlg').dialog('open')
    })
    $('#editBtn').click(function(){
        var selects = $('#scheduleTable').datagrid('getSelections');
        if(selects.length > 1){
            $.messager.alert('修改','每次只能修改一条数据！','warning');
            return false;
        } else if(selects.length == 0){
            $.messager.alert('修改','请选择一条修改数据！','warning');
            return false;
        }
        var row = selects[0]
        $('#id').val(row.id);
        $('#freqDesc').combobox('setValue',row.freqDesc);
        $('#administration').combobox('setValue',row.administration);
        $('#freqDesc').combobox('disable');
        $('#administration').combobox('disable');
        $('#defaultSchedule').textbox('setValue',row.defaultSchedule);
        $('#dlg').dialog('open')
    })

    $('#saveBtn').click(function(){
        $('#freqDesc').combobox('enableValidation')
        $('#administration').combobox('enableValidation')
        $('#defaultSchedule').combobox('enableValidation')
        if(!$('#freqDesc').combobox('isValid') || !$('#administration').combobox('isValid') || !$('#defaultSchedule').combobox('isValid')){
            return false;
        }
        var data = {
            id: $('#id').val(),
            freqDesc: $('#freqDesc').combobox('getValue'),
            administration: $('#administration').combobox('getValue'),
            defaultSchedule: $('#defaultSchedule').textbox('getValue')
        }
        parent.$.postJSON('/service/performDefaultSchedule/save',JSON.stringify(data),function(res){
            if(res == '1'){
                $.messager.alert('保存','保存成功！','info',function(){
                    var v = $('#operateCombo').combobox('getValue');
                    loadComboData()
                    if(! v){
                        if(currentCheckRadio = '1'){
                            $('#operateCombo').combobox('setValue',$('#freqDesc').combobox('getValue'))
                        } else if(currentCheckRadio = '2'){
                            $('#operateCombo').combobox('setValue',$('#administration').combobox('getValue'))
                        }
                    } else {
                        $('#operateCombo').combobox('setValue',v)
                    }
                    loadData()
                    $('#dlg').dialog('close')
                })
            } else {
                $.messager.alert('保存','保存失败！','error')
            }
        })
    })
    $('#delBtn').click(function(){
        var selects = $('#scheduleTable').datagrid('getSelections');
        var ids = '';
        for(var i= 0,j=selects.length;i<j;i++){
            ids += ',' + selects[i].id;
        }
        if(ids == ''){
            $.messager.alert('删除','没有要删除的数据！','warning')
        } else {
            $.get('/service/performDefaultSchedule/delete',{ids:ids.substr(1)},function(res){
                if(res == '1'){
                    for(var i=selects.length-1;i>-1;i--){
                        $('#scheduleTable').datagrid('deleteRow',$('#scheduleTable').datagrid('getRowIndex',selects[i]))
                    }
                    if($('#scheduleTable').datagrid('getRows').length == 0){
                        loadComboData()
                    }
                    $.messager.alert('删除','删除成功！','info')
                } else {
                    $.messager.alert('删除','删除失败！','error')
                }
            })
        }
    })
})