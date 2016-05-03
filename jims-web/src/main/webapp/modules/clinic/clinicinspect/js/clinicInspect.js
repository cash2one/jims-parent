function onloadMethod() {
    //下拉框选择控件，下拉框的内容是动态查询数据库信息
    $('#examClassNameId').combobox({
        url: basePath + '/examClassDict/getEx',
        valueField: 'examClassName',
        textField: 'examClassName',
        onSelect: function (data) {
            $.ajax({
                type: "POST",
                url: basePath +'/examClassDict/getExamSubclass',
                data: examClassName = data.examClassName,
                dataType: "json",
                success: function (data) {
                    $("#examSubclassNameId").combobox('loadData',data);
                }
            });
        }
    });

    $('#examSubclassNameId').combobox({
        valueField: 'examSubclassName',
        textField: 'examSubclassName',
        onSelect: function (data) {
            $.ajax({
                type: "POST",
                url: basePath +'/examClassDict/getExamRptPattern',
                data: description = data.examSubclassName,
                dataType: "json",
                success: function (data) {
                    var html='';
                    var ids="";
                    var hidden="";
                    for(var i= 0;i<data.length;i++){
                        hidden='<input type="hidden" class="submitName"  value="'+data[i].description+'" id="descriptionId'+data[i].inputCode+i+'">';
                        html+='<div id="descriptionId'+data[i].inputCode+i+'"  style="height:20px " submit_id="descriptionId'+data[i].inputCode+i+'" class="drag">'+data[i].description+hidden+'</div>';

                        ids+="#descriptionId"+data[i].inputCode+i+",";
                    }

                    ids = ids.substring(0, ids.length - 1);
                    $("#descriptionId").html(html);
                    $('#target').droppable({
                        accept:ids,
                        onDragEnter:function(e,descriptionId){
                            $(descriptionId).draggable('options').cursor='auto';
                            $(descriptionId).draggable('proxy').css('border','1px solid red');
                        },
                        onDragLeave:function(e,descriptionId){
                            $(descriptionId).draggable('options').cursor='not-allowed';
                            $(descriptionId).draggable('proxy').css('border','1px solid #ccc');
                        },
                        onDrop:function(e,descriptionId){
                            $(this).append(descriptionId)
                        }
                    });
                    $('.drag').draggable({
                        proxy:'clone',
                        revert:true,
                        cursor:'auto',
                        onStartDrag:function(){
                            $(this).draggable('options').cursor='not-allowed';
                        },
                        onStopDrag:function(){
                            $(this).draggable('options').cursor='auto';
                        }
                    });
                }
            });
            //$('#descriptionId').combobox({
            //    panelHeight: '150',//自动高度适合
            //    valueField: 'description',
            //    textField: 'description'
            //});
        }
    });



    $('#list_data').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/clinicInspect/list',
        remoteSort: false,
        idField: 'fldId',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'reqDateTime', title: '申请日期', width: '20%', align: 'center', formatter: formatDateBoxFull},
            {field: 'updateBy.id', title: '检查项目名称', width: '20%', align: 'center'},
            {field: 'reqDept', title: '检查科室', width: '20%', align: 'center'},
            {
                field: 'id',
                title: '操作',
                width: '40%',
                align: 'center',
                formatter: function (value, row, index) {
                    var html = '<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="look(\'' + value + '\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>' +
                        '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="get(\'' + row.id + '\',\'' + row.type + '\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>' +
                        '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                    return html;
                }
            }
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '修改',
            iconCls: 'icon-edit',
            handler: function () {
                var selectRows = $('#list_data').datagrid("getSelections");
                if (selectRows.length < 1) {
                    return;
                }
                get(selectRows[0].id);
            }
        }, '-', {
            text: '批量删除',
            iconCls: 'icon-remove',
            handler: function () {
                doDelete();
            }
        }]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');


}

//批量删除
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
            del(strIds);
        }
    })
}

//列删除
function deleteRow(id) {
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            del(id);
        }
    })
}

/**
 * 删除方法
 * @param id
 */
function del(id) {
    $.ajax({
        'type': 'POST',
        'url': basePath + '/clinicInspect/del',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == 'success') {
                $.messager.alert("提示消息", data.code + "条记录，已经删除");
                $('#list_data').datagrid('load');
                $('#list_data').datagrid('clearChecked');
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "保存失败", "error");
        }
    });
}


/**
 * 查看字典
 * @param id
 */
function look(id) {
    $("#fm").dialog({title: '查看'}).dialog("open");
    $("#saveBut").hide();
    $.ajax({
        'type': 'post',
        'url': basePath + '/clinicInspect/get',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $('#clinicInspectForm').form('load', data);
        }
    });
}

/**
 * 修改字典
 * @param id
 */
function get(id) {
    $.ajax({
        'type': 'post',
        'url': basePath + '/clinicInspect/get',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $('#clinicInspectForm').form('load', data);
        }
    });
}
    //保存
    function saveClinicInspect(){
        var formJson=fromJson('clinicInspectForm');
        formJson = formJson.substring(0, formJson.length - 1);
        var divJson="";
        $('#target .submitName').each(function (index, element) {
            divJson+="{\"itemName\":\""+$(this).val()+"\"},";
        })
        divJson = divJson.substring(0, divJson.length - 1);
        var submitJson=formJson+",\"outpOrdersCostses\":["+divJson+"]}";

        $.postJSON(basePath+"/clinicInspect/saveExamAppoints",submitJson,function(data){
            if(data.code=="1"){
                $.messager.alert("提示信息","保存成功");
                $('#list_data').datagrid('load');
                $("#clinicInspectForm").form('clear');
            }else{
                $.messager.alert("提示信息","保存失败","error");
            }

        }),function(data){
            $.messager.alert("提示信息","保存失败","error");
        }
}

