
//页面加载
$(function() {
    var patientId = '1';
    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        /*title:'与当前登记病人重名记录',*/
        toolbar: '#searchDiv',
        method: 'GET',
        url: basePath + '/patMasterIndex/list?patientId=' + patientId,
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'id', title: 'ID', hidden: 'true'},
            {field: 'name', title: '姓名', width: '15%', align: 'center'},
            {field: 'idNo', title: '身份证号', width: '25%', align: 'center'},
            {field: 'sex', title: '性别', width: '10%', align: 'center',formatter:sexFormatter},
            {field: 'inpNo', title: '住院号', width: '10%', align: 'center'},
            {field: 'birthPlace', title: '出生地', width: '20%', align: 'center'},
            {field: 'mailingAddress', title: '通信地址', width: '20%', align: 'center'}
        ]], onClickRow: function (index, row) {
            $("#masterForm").form('load', row);
        }, onLoadSuccess: function () {

        }
    });
    //设置分页控件
    var p = $('#leftList').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });


    //性别
    $('#sexId').combobox({
        data: sexData,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#sex").val(n.value);
        }
    });
    //$("#sexId ").combobox('select',setData[0].value);
    //婚姻状况
    $('#maritalStatusId').combobox({
        data: marriageDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#maritalStatus").val(n.value);
        }

    });
    //$("#maritalStatusId ").combobox('select',marriageDict[0].value);
    //国籍
    $('#citizenshipId').combobox({
        data: nationalityDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#citizenship").val(n.value);
        }
    });
    //$("#citizenshipId ").combobox('select',nationalityDict[0].value);
    //民族
    $('#nationId').combobox({
        data: nationDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#nation").val(n.value);
        }
    });
    //$("#nationId").combobox('select',nationDict[0].value);
    //费别
    $('#chargeTypeId').combobox({
        data: chargeType,
        valueField: 'id',
        textField: 'charge_type_name',
        onSelect: function (n, o) {
            $("#chargeType").val(n.id);
        }
    });
    //$("#chargeTypeId ").combobox('select',chargeType[0].value);
 /*   //身份
    $('#identityId').combobox({
        data: identityDict,
        valueField: 'id',
        textField: 'identityName',
        onSelect: function (n, o) {
            $("#identityId").val(n.id);
        }
    });
    //$("#identityId ").combobox('select',identityDict[0].id);
*/
    //职业
    $('#occupationId').combobox({
        data: professionDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#occupation").val(n.value);
        }
    });
    //$("#occupationId ").combobox('select',professionDict[0].value);

    //关系
    $('#relationshipId').combobox({
        data: relationshipDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#relationship").val(n.value);
        }
    });
    //$("#relationshipId ").combobox('select',relationshipDict[0].value);
    //入院来源
    $('#fromOtherPlaceIndicatorId').combobox({
        data: resourceDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#fromOtherPlaceIndicator").val(n.value);
        }
    });
    //$("#fromOtherPlaceIndicatorId ").combobox('select',resourceDict[0].value);
    //入院方式
    $('#patientClassId').combobox({
        data: methodDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#patientClass").val(n.value);
        }
    });
    //$("#patientClassId ").combobox('select',methodDict[0].value);
    //住院目的
    $('#admissionCauseId').combobox({
        data: objectiveDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#admissionCause").val(n.value);
        }
    });
    //$("#admissionCauseId").combobox('select',objectiveDict[0].value);
    //病情
    $('#patAdmConditionId').combobox({
        data: admissionDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#patAdmCondition").val(n.value);
        }
    });
    //$("#patAdmConditionId").combobox('select',admissionDict[0].value);


    //入院科室
    $('#deptAdmissionTo').combobox({
        data: clinicDeptCode,
        valueField: 'value',
        textField: 'label'
    });
    //$("#deptAdmissionTo ").combobox('select',clinicDeptCode[0].value);
    //
    //诊断
    $('#diagnosis').combogrid({
        width: '300',
        height: 'auto',
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
    //接诊医生
    $('#consultingDoctor').combogrid({
        width: '300',
        height: 'auto',
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
                comboGridCompleting(q, 'consultingDoctor');
                $('#consultingDoctor').combogrid("grid").datagrid("loadData", comboGridComplete);
            }
        }

    });

})

//列表条件查询
function searchByCondition(){

}

//保存
function saveMaster() {
    $.postForm(basePath + '/patMasterIndex/save', 'masterForm', function (data) {
        if (data.data == 'success') {

            //$.messager.alert("提示消息", data.code + "条记录，保存成功");

            $.messager.confirm("操作提示", "是否交预交金？", function (data) {
                $('#centerList').datagrid('load');
                $('#centerList').datagrid('clearChecked');
                if (data) {
                    window.parent.document.getElementById("centerIframe").src = "/modules/finance/prepaymentList.html";
                }
            });
        } else {
            $.messager.alert('提示', "保存失败", "error");
        }
    }, function (data) {
        $.messager.alert('提示', "保存失败", "error");
    });
}
//取消登记
function removeMaster(){
    var selRow = $('#leftList').datagrid('getChecked');
    if(selRow!=null && selRow!='' && selRow!=undefined){
        var id = selRow[0].id;
        $.ajax({
            'type': 'POST',
            'url': basePath+'/patMasterIndex/delete',
            'contentType': 'application/json',
            'data': ids=id,
            'dataType': 'json',
            'success': function(data){

                if(data.data=='success'){
                    $.messager.alert("提示消息",data.code+"条记录取消登记成功！");
                    $('#leftList').datagrid('load');
                    $('#list_data').datagrid('load');
                    $('#list_data').datagrid('clearChecked');
                }else if(data.data=='warning') {
                    $.messager.alert('提示',"已缴纳预交金，不能取消登记！", "error");
                }else if(data.data=='warn') {
                    $.messager.alert('提示',"该病人未进行入院登记，不能进行取消登记操作！", "error");
                }else{
                    $.messager.alert('提示',"取消登记失败", "error");
                }
            },
            'error': function(data){
                $.messager.alert('提示',"取消登记失败", "error");
            }
        });
    }else{
        $.messager.alert('提示',"请选择要取消的登记！", "warning");
    }
}

function clearForm(){
    $("#masterForm").form('clear');
}



