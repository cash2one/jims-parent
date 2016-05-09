var administration = [{ "value": "1", "text": "科室1" }, { "value": "2", "text": "科室2" }, { "value": "3", "text": "科室3" }, { "value": "4", "text": "科室4" }, { "value": "5", "text": "科室5" }];
var doctors = [{ "value": "1", "text": "医生1" }, { "value": "2", "text": "医生" }, { "value": "3", "text": "医生" }, { "value": "4", "text": "医生" }, { "value": "5", "text": "医生" }];
$(function(){
    //下拉框选择控件，下拉框的内容是动态查询数据库信息
    $('#performedBy').combobox({
        url: basePath + '/dept-dict/findListByCode',
        valueField: 'deptCode',
        textField: 'deptName',
        queryParams:{code:1502},
        /*onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            $(this).combobox('select', data[0].deptName);
        },*/
        onSelect: function (data) {
            $.ajax({
                type: "POST",
                url: basePath +'/labitemclass/findListByDeptCode',
                data: code = data.deptCode,
                dataType: "json",
                success: function (data) {
                    $("#labItemClass").combobox('loadData',data);
                }
            });
            $.ajax({
                type: "POST",
                url: basePath +'/speciman/findListByDeptCode',
                data: code = data.deptCode,
                dataType: "json",
                success: function (data) {
                    $("#specimen").combobox('loadData',data);
                }
            });
        }
    });
    $('#labItemClass').combobox({
        valueField: 'classCode',
        textField: 'className'
        /*,
        onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            $(this).combobox('select', data[0].className);
        }*/
    });
    $('#specimen').combobox({
        valueField: 'specimanCode',
        textField: 'specimanName',
        /*onLoadSuccess: function () {
            var data = $(this).combobox('getData');
            $(this).combobox('select', data[0].specimanName);*/
        onChange : function(n,o){SendProduct();
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
        url: basePath + '/labtest/list',
        remoteSort: false,
        idField: 'fldId',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'requestedDateTime', title: '申请日期', width: '30%', align: 'center', formatter: formatDateBoxFull},
            {field: 'performedBy', title: '检查科室', width: '30%', align: 'center'},
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
        view: detailview,
        detailFormatter: function(rowIndex, rowData){
            return '<table><tr>' +
                '<td style="border:0">' +
                '<p>检验项目: ' + rowData.memo + '</p>' +
                '</td>' +
                '</tr></table>';
        },
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
    $('#items').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'post',
        url: basePath + '/labtest/list',
        columns:[[
            {field:'itemCode',title:'itemCode',width:'40%',align:'center'},
            {field:'itemName',title:'itemName',width:'60%',align:'center'}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]]
    });

});
//保存
function save(){
    //formSubmitInput("enterForm");
    //
    $("#patientId").val("06b45a7a-b286-11e5-b259-0894ef010b20");
    $("#orderingProvider").val("");
    $("#orderingDept").val("");
    $.postForm(basePath+"/enter/save","labtestForm",function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","保存成功");
            clearForm();
        }else{
            $.messager.alert("提示信息","保存失败","error");
            clearForm();
        }

    }),function(data){
        $.messager.alert("提示信息","保存失败","error");
        clearForm();
    }
};

function SendProduct() {
    var expand3 = $("#performedBy").val();
    var expand2 = $("#labItemClass").val();
    var expand1 = $("#specimen").val();
    $.ajax({
        type: "post",
        url: basePath + '/clinicitemname/selectlabitem',contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({"expand1":expand1,"expand2":expand2,"expand3":expand3}),

        success: function (data) {
            var divstr ="<table>";
            for(var i=0; i<data.length; i++)
            {   if(i==0){
                    divstr =divstr+"<tr><td><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='"+data[i].itemCode+"'>"+data[i].itemName+"<input type='hidden' name='price' value='10'/></div></td>";
                }
                else if(i%3==0){
                    divstr =divstr+"<tr><td><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='"+data[i].itemCode+"'>"+data[i].itemName+"<input type='hidden' name='price' value='10'/></div></td>";
                }
                else if(i%3==2){
                    divstr =divstr+"<td><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='"+data[i].itemCode+"'>"+data[i].itemName+"<input type='hidden' name='price' value='10'/></div></td></tr>";
                }
                else{
                     divstr =divstr+"<td ><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='"+data[i].itemCode+"'>"+data[i].itemName+"<input type='hidden' name='price' value='10'/></div></td>";
                }
            }
            divstr = divstr +"</table>";
            divstr = divstr +"<div align='center'><a href='javascript:void(0)'  class='easy-nbtn easy-nbtn-padd' onclick='doSelect();' style='width: 90px'>提交</a></div>";
            $("#SendProduct").html(divstr);
        }
    });
    $("#SendProduct").dialog("open");
}

$("#SendProduct").dialog({
    title: '发货操作',
    width:'400',
    height:'300',
    iconCls: 'icon-edit',
    modal: true,
    closed: true
});
function doSelect() {
    //把你选中的 数据查询出来。
    var selectRows = $('div.easyui-window :checkbox:checked');
    if (selectRows.size() < 1) {
        $.messager.alert("提示消息", "请选中数据!");
        return;
    }else{
        var rows=[];
        var row={};
        var all=0;
        selectRows.each(
            function(){
                row.itemCode=$(this).val();//增
                var temp = $(this).next(":hidden").val();
                row.itemName=temp;//增
                all=all-1.0+1.0+temp;
                rows.push(row);
            }
        )
        var selectJson={'total':selectRows.size(),'rows':rows};
        $('#items').datagrid('loadData',selectJson);
    }
    $("#SendProduct").dialog("close");
}

