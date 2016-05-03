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
                    $("#d1").draggable("options",data);
                }
            });
            //$('#descriptionId').combobox({
            //    panelHeight: '150',//自动高度适合
            //    valueField: 'description',
            //    textField: 'description'
            //});
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
    $('#target').droppable({
        accept:'#d1,#d2,#d3',
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