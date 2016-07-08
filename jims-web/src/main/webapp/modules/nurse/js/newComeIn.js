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
    $('#waitList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:"GET",
        url: basePath + '/patsInHospital/patientlist',
        columns: [[      //每个列具体内容
            {field: 'name', title: '姓名',width: '15%', align: 'center'},
            {field: 'patientId', title: '病人ID', width: '15%', align: 'center'}
        ]], onClickRow: function (index, row) {
        }, onLoadSuccess: function (data) {

        }
    });
})

