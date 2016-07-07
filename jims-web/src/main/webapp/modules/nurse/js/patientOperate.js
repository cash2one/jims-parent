/**
 * Created by JIMS on 2016/7/6.
 */
var wardCode='160101';
$(function () {
    $('#discharge_disposition').combobox({
        data: discharge,
        valueField: 'value',
        textField: 'label'
    });
    //出院界面回车查询信息
    $('#bed_no').textbox('textbox').keydown(function(e){
        if (e.keyCode == 13) {
            var bed_no = $.trim($('#bed_no').val());
            if (bed_no != '') {
                $.ajax({
                    method:"POST",
                    contentType: "application/json", //必须有
                    dataType: 'json',
                    data: JSON.stringify({"wardCode":wardCode,"bedNo":bed_no}),
                    url: basePath + '/patsInHospital/searchInfoByParams',
                    success: function (data) {
                        if(data !=null){
                            $('#leaveHospForm').form('load', data);
                        }else{
                            $.messager.alert('提示',"请输入正确且存在的床位号", "error");
                        }
                    }
                })
            }else{
                $.messager.alert('提示',"请输入床位号", "error");
            }
        }else{
            return false;
        }
    });
    //转出-转科处理界面回车查询信息
    $('#obed_no').textbox('textbox').keydown(function(e){
        if (e.keyCode == 13) {
            var obed_no = $.trim($('#obed_no').val());
            if (obed_no != '') {
                $.ajax({
                    method:"POST",
                    contentType: "application/json", //必须有
                    dataType: 'json',
                    data: JSON.stringify({"wardCode":wardCode,"bedNo":obed_no}),
                    url: basePath + '/patsInHospital/searchTurnOutInfoByParams',
                    success: function (data) {
                        if(data !=null){
                            $('#turnOutForm').form('load', data);
                        }else{
                            $.messager.alert('提示',"请输入正确且存在的床位号", "error");
                        }
                    }
                })
            }else{
                $.messager.alert('提示',"请输入床位号", "error");
            }
        }else{
            return false;
        }
    });


    $('#leaveHosp').dialog({
        title: '出院处理',
        iconCls:"icon-edit",
        collapsible: true,
        minimizable: true,
        maximizable: true,
        resizable: true,
        width: 600,
        height: 400,
        modal: true,
        href: "Content.aspx",
        onClose: function () {
            alert("Close");
        },
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: function () {
                alert('确定');
            }
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#dlg').dialog('close');
            }
        }]
    });

    $('#turnOutDept').dialog({
        title: '转出-转科处理',
        iconCls:"icon-edit",
        collapsible: true,
        minimizable: true,
        maximizable: true,
        resizable: true,
        width: 300,
        height: 200,
        modal: true,
        href: "Content.aspx",
        onClose: function () {
            alert("Close");
        },
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: function () {
                alert('确定');
            }
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#dlg').dialog('close');
            }
        }]
    });

    $('#cancelTurnOut').dialog({
        title: '取消转科处理',
        iconCls:"icon-edit",
        collapsible: true,
        minimizable: true,
        maximizable: true,
        resizable: true,
        width: 300,
        height: 200,
        modal: true,
        href: "Content.aspx",
        onClose: function () {
            alert("Close");
        },
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: function () {
                alert('确定');
            }
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#dlg').dialog('close');
            }
        }]
    });
});

//点击右键出院
function leaveHosp(){
    $('#leaveHosp').dialog('open');
}
//点击确认出院
function leaveHospSure(){
    var patientId = $("#patient_id").val();
    var dischargeDisposition = $('#discharge_disposition').combobox('getValue');
    var visitId = $("#visit_id").val();
    $.messager.confirm("确认消息", "您确定要将该床位上的病人出院吗？", function (r) {
        $.ajax({
            'type': 'POST',
            'url': basePath + '/patsInHospital/leaveHosp',
            'contentType': 'application/json',
            'data': ids = id,
            'dataType': 'json',
            'success': function (data) {
                if (data.data == 'success') {
                    $.messager.alert("提示消息", data.code + "条记录删除成功！");
                    //$('#leftList').datagrid('load');
                } else {
                    $.messager.alert('提示', "删除失败", "error");
                }
            },
            'error': function (data) {
                $.messager.alert('提示', "删除失败", "error");
            }
        });
    });
}


//点击右键转出
function turnOutHosp(){
    $('#turnOutDept').dialog('open');
}
//点击转出确认
function turnOutDeptSure(){
    var patientId = $("#opatient_id").val();
    var hl_unit = $('#hl_unit').combobox('getValue');
    var hl_unit_dept = $('#hl_unit_dept').combobox('getValue');
    var visitId = $("#ovisit_id").val();
    $.messager.confirm("确认消息", "您确定要将该床位上的病人转出吗？", function (r) {
        $.ajax({
            'type': 'POST',
            'url': basePath + '/patsInHospital/turnOutDept',
            'contentType': 'application/json',
            'data': ids = id,
            'dataType': 'json',
            'success': function (data) {
                if (data.data == 'success') {
                    $.messager.alert("提示消息", data.code + "条记录转出成功！");
                    //$('#leftList').datagrid('load');
                } else {
                    $.messager.alert('提示', "转出失败", "error");
                }
            },
            'error': function (data) {
                $.messager.alert('提示', "转出失败", "error");
            }
        });
    });
}

//点击右键取消转出
function cancelTurnOut(){
    $('#cancelTurnOut').dialog('open');
    $('#cancelTurnOutTable').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:"GET",
        url: basePath + '/patsInHospital/waitTurnOutList',
        columns: [[      //每个列具体内容
            {field: 'name', title: '姓名',width: '15%', align: 'center'},
            {field: 'sex', title: '性别',width: '15%', align: 'center'},
            {field: 'id_no', title: '身份证号', width: '15%', align: 'center'},
            {field: 'dept_name_from', title: '转出科室', width: '15%', align: 'center'},
            {field: 'dept_name_to', title: '转向科室', width: '15%', align: 'center'},
            {field: 'transfer_date_time', title: '转出日期', width: '15%', align: 'center'},
            {field: 'patient_id', title: '病人ID',hidden:true},
            {field: 'dept_transfered_from', title: '转出科室',hidden:true},
            {field: 'inp_no', title: '住院号',hidden:true}

        ]], onClickRow: function (index, row) {
            $("#cancelTurnOutForm").form(row);
        }, onLoadSuccess: function (data) {

        }
    });

}
//点击取消转出确认
function cancelTurnOutSure(){


}

