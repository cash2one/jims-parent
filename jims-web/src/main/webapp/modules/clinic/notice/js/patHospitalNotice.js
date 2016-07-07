//var clinicId = parent.clinicMaster.id;
var patientId = parent.clinicMaster.patientId;
function onloadMethod() {
    //$("#name").val(parent.clinicMaster.name);
    //$("#sex").val(sexFormatter(parent.clinicMaster.sex,'',''));
    //$("#age").val(parent.clinicMaster.age);
    //$("#patientId").val(patientId);
    //    $("#masterId").val(parent.clinicMaster.id);
    //$("#visitDate").val(parent.clinicMaster.visitDate);//入院时间
    //$("#visitDept").val(clinicDeptCodeFormatter(parent.clinicMaster.visitDept,'',''));//入院科室
    //$("#chargeType").val(itemFormatter(parent.clinicMaster.chargeType,'',''));//费别
    $.ajax({
        'type': 'GET',
        'url':basePath + '/bloodApply/getPatient',
        'contentType': 'application/json',
        'data': {id : 1},
        'dataType': 'json',
        'success': function(data){
            $("#name").val(data.name);
            $("#sex").val(sexFormatter(data.sex,'',''));
            $("#sexId").val(data.sex);
            $("#age").val(data.age);
            $("#patientId").val(data.patientId);
            $("#masterId").val(data.id);
            $("#dateOfBirth").val(data.patMaster.dateOfBirth);//出生日期
            $("#idNo").val(data.patMaster.idNo);//身份证
            $("#nation").val(nationFormatter(data.patMaster.nation));//名族
            $("#nationId").val(data.patMaster.nation);
            $("#birthPlace").val(data.patMaster.mailingAddress);//出生地
            $("#serviceAgencyPhone").val(data.patMaster.phoneNumberHome);//本人联系方式
            $("#relationship").val(data.patMaster.relationship);//联系人关系
            $("#nextOfKin").val(data.patMaster.nextOfKin);//联系人
            $("#nextOfIdNo").val(data.patMaster.nextOfIdNo);//联系人身份证
            $("#nextOfKinAddr").val(data.patMaster.nextOfKinAddr);//联系人地址
            $("#nextOfKinPhone").val(data.patMaster.nextOfKinPhone);//联系人电话
            $("#nextOfNation").val(nationFormatter(data.patMaster.nextOfNation));//联系人名族
            $("#visitDate").val(data.visitDate);//入院时间
            $("#visitDept").val(clinicDeptCodeFormatter(data.visitDept,'',''));//入院科室
            $("#chargeType").val(itemFormatter(data.chargeType,'',''));//费别
            $("#enterDate").datetimebox("setValue",new Date);

        }
    })

    $("#maritalStatus").combobox({
        data:marriageDict,
        valueField:'value',
        textField:'label',
        onSelect:function(data){
            $("#maritalStatusId").val(data.value);
        }
    })

    $("#patAdmCondition").combobox({
        data:situation,
        valueField:'value',
        textField:'label',
        onSelect:function(data){
            $("#patAdmConditionId").val(data.value);
        }
    })

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
        url: basePath + '/patHospitalNotice/list',
        remoteSort: false,
        idField: 'fldId',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'name', title: '姓名', width: '18%', align: 'center'},
            {field: 'enterDate', title: '录入时间', width: '30%', align: 'center', formatter: formatDateBoxFull},
            {field: 'onsetDate', title: '发病时间', width: '30%', align: 'center', formatter: formatDateBoxFull},
            {field: 'noticeId', title: '通知次数', width: '18%', align: 'center'},
            {
                field: 'id', title: '操作', width: '40%', align: 'center', formatter: function (value, row, index) {
                var state = "1";
                var html = '<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="look(\'' + row.id + '\',\'' + state + '\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>' +
                    '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="get(\'' + row.id + '\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>' +
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
                    $.messager.alert("提示消息", "请选中需要修改的数据");
                    return;
                }
                get(selectRows[0].id);
            }
        }, '-', {
            text: '删除',
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
 * 保存住院通知单
 * @param id
 */
function savePatHospitalNotice() {
    $.postForm(basePath + "/patHospitalNotice/save", "patHospitalForm", function (data) {
        if (data.code == "1") {
            $.messager.alert("提示信息", "保存成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
            $("#patHospitalForm").form("clear");
        } else {
            $.messager.alert("提示信息", "保存失败", "error");
        }

    }), function (data) {
        $.messager.alert("提示信息", "保存失败", "error");
    }
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
        'url': basePath + '/patHospitalNotice/del',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == 'success') {
                if (data.code > 0) {
                    $.messager.alert("提示消息", data.code + "条记录，已经删除");
                    $('#list_data').datagrid('load');
                    $('#list_data').datagrid('clearChecked');
                } else {
                    $.messager.alert('提示', "删除失败", "error");
                }
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "删除失败", "error");
        }
    });
}
/**
 * 修改住院通知单
 * @param id
 */
function get(id){
    $("#savePatHospitalNotice").show();
    $.ajax({
        'type': 'post',
        'url': basePath+'/patHospitalNotice/get',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            $('#patHospitalForm').form('load',data);
            $("#maritalStatus ").combobox('select',data.maritalStatus);
            $("#patAdmCondition ").combobox('select',data.patAdmCondition);
        }
    });
}
/**
 * 查看住院通知单
 * @param id
 */
function look(id){
    $("#savePatHospitalNotice").hide();
    $.ajax({
        'type': 'post',
        'url': basePath+'/patHospitalNotice/get',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            $('#patHospitalForm').form('load',data);
            $("#maritalStatus ").combobox('select',data.maritalStatus);
            $("#patAdmCondition ").combobox('select',data.patAdmCondition);
        }
    });
}


