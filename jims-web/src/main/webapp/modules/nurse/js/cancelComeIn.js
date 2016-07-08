$(function() {
    //入住科室
    $('#deptStayed').combobox({
        data: clinicDeptCode,
        valueField: 'value',
        textField: 'label'
    });
    //病情
    $('#patientCondition').combobox({
        data: patientCondition,
        valueField: 'value',
        textField: 'label'
    });
    //护理等级
    $('#nursingClass').combobox({
        data: nurseClass,
        valueField: 'value',
        textField: 'label'
    });

    //费别
    $('#chargeType').combobox({
        data: chargeType,
        valueField: 'value',
        textField: 'label'
    });
    //接诊医生
    $('#doctorUser').combogrid({
        width: '300',
        height: '20',
        data: doctorName,
        idField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'doctorUser');
                $('#doctorUser').combogrid("grid").datagrid("loadData", comboGridComplete);
            }
        }
    });
    //上级医生
    $('#superDoctorId').combogrid({
        width: '300',
        height: '20',
        data: doctorName,
        idField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'superDoctorId');
                $('#superDoctorId').combogrid("grid").datagrid("loadData", comboGridComplete);
            }
        }
    });
    //主任医生
    $('#parentDoctorId').combogrid({
        width: '300',
        height: '20',
        data: doctorName,
        idField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'parentDoctorId');
                $('#parentDoctorId').combogrid("grid").datagrid("loadData", comboGridComplete);
            }
        }
    });
    //诊断
    $('#diagnosis').combogrid({
        width: '300',
        height: '19',
        data: icdAllData,
        idField: 'zhongwen_mingcheng',
        textField: 'zhongwen_mingcheng',
        mode: 'remote',
        columns: [[
            {field: 'zhongwen_mingcheng', title: '中文名称', width: '30%', align: 'left'},
            {field: 'code', title: 'ICD-10编码', width: '20%', align: 'left'},
            {field: 'keyword_shuoming', title: '关键词', width: '50%', align: 'left'},
        ]], keyHandler: {
            query: function (q) {
                comboGridCompleting(q, 'diagnosis');
                $('#diagnosis').combogrid("grid").datagrid("loadData", comboGridComplete);
            }
        }
    })
    $('#centerList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:"GET",
        url: basePath + '/patsInHospital/cancelPatientlist',
        columns: [[      //每个列具体内容
            {field: 'bed_no', title: '床号',width: '5%', align: 'center'},
            {field: 'bed_label', title: '床标号', width: '5%', align: 'center'},
            {field: 'bed_class', title: '等级',width: '5%', align: 'center'},
            {field: 'name', title: '姓名',width: '5%', align: 'center'},
            {field: 'sex', title: '性别', width: '5%', align: 'center'},
            {field: 'date_of_birth', title: '出生年月',width: '5%', align: 'center'},
            {field: 'identity', title: '身份', width: '5%', align: 'center'},
            {field: 'charge_type', title: '费别',width: '5%', align: 'center'},
            {field: 'patient_id', title: '病人id', width: '5%', align: 'center'},
            {field: 'inp_no', title: '住院号',width: '5%', align: 'center'},
            {field: 'admission_date_time', title: '入院日期', width: '5%', align: 'center'},
            {field: 'prepayments', title: '预交金',width: '5%', align: 'center'},
            {field: 'total_costs', title: '应交费用', width: '5%', align: 'center'},
            {field: 'total_charges', title: '计价费用', width: '5%', align: 'center'},
            {field: 'doctor_in_charge', title: '医生', width: '5%', align: 'center'},
            {field: 'patient_condition', title: '病情', width: '5%', align: 'center'},
            {field: 'nursing_class', title: '护理', width: '5%', align: 'center'},
            {field: 'diagnosis', title: '诊断', width: '5%', align: 'center'},
            {field: 'dept_code', title: '入住科室', width: '5%', align: 'center'},
            {field: 'adm_ward_date_time', title: '入科日期', width: '5%', align: 'center'},
            {field: 'mailing_address', title: '通讯地址', width: '5%', align: 'center'},
            {field: 'next_of_kin', title: '亲属', width: '5%', align: 'center'},
            {field: 'phone_number_home', title: '电话（家）', width: '5%', align: 'center'}

        ]], onClickRow: function (index, row) {
            $("#eastForm").form('load', row);
        }, onLoadSuccess: function (data) {

        }
    });
});

//确认取消入科
function cancelSureFun(){
    $.messager.confirm("确认消息", "您确定要将该病人取消入科吗？", function (r) {
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

