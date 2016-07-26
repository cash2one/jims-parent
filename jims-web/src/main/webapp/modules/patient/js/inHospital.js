
//页面加载
$(function() {

    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        /*title:'与当前登记病人重名记录',*/
        toolbar: '#searchDiv',
        method: 'GET',
        url: basePath + '/patMasterIndex/list',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'id', title: 'ID', hidden: 'true'},
            {field: 'name', title: '姓名', width: '15%', align: 'center'},
            {field: 'idNo', title: '身份证号', width: '25%', align: 'center'},
            {field: 'sex', title: '性别', width: '10%', align: 'center',formatter:setDataFormatter},
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

    function ages(d) {
        var str  = d.format("yyyy-MM-dd");
        var   r   =   str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
        if(r==null)return   false;
        var   d=   new   Date(r[1],   r[3]-1,   r[4]);
        if   (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]){
            var   Y   =   new   Date().getFullYear();
            $("#age").numberbox("setValue",(Y-r[1]));
        }
        //return("输入的日期格式错误！");
    }
    //性别
    $('#sexId').combobox({
        data: setData,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#sex").val(n.value);
        }
    });
    if(setData.length>0) {
        $("#setId").combobox('select', setData[0].value);
    }
    $("#dateOfBirth").datebox({
        onSelect:function(date){
            var nowDate = new Date();
            if(date>nowDate){
                $("#dateOfBirth").datebox("setValue","");
                message:"出生日期不能大于当前日期！";
            }else{
                ages(date);
            }
        }
    });


    //婚姻状况
    $('#maritalStatusId').combobox({
        data: marriageDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#maritalStatus").val(n.value);
        }

    });
    if(marriageDict.length>0) {
        $("#maritalStatusId").combobox('select',marriageDict[0].value);
    }
    //国籍
    $('#citizenshipId').combobox({
        data: nationalityDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#citizenship").val(n.value);
        }
    });
    if(nationalityDict.length>0) {
        $("#citizenshipId").combobox('select', nationalityDict[0].value);
    }
    //民族
    $('#nationId').combobox({
        data: nationDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#nation").val(n.value);
        }
    });
    if(nationDict.length>0) {
        $("#nationId").combobox('select', nationDict[0].value);
    }
    //费别
    $('#chargeTypeId').combobox({
        data: chargeType,
        valueField: 'id',
        textField: 'charge_type_name',
        onSelect: function (n, o) {
            $("#chargeType").val(n.id);
        }
    });
    if(chargeType.length>0) {
        $("#chargeTypeId").combobox('select', chargeType[0].id);
    }
    //身份
    $('#identityId').combobox({
        data: identityDict,
        valueField: 'id',
        textField: 'identityName',
        onSelect: function (n, o) {
            $("#identityId").val(n.id);
        }
    });
    if(identityDict.length>0) {
        $("#identityDict").combobox('select', identityDict[0].id);
    }
    //职业
    $('#occupationId').combobox({
        data: professionDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#occupation").val(n.value);
        }
    });
    if(professionDict.length>0) {
        $("#occupationId").combobox('select', professionDict[0].value);
    }

    //关系
    $('#relationshipId').combobox({
        data: relationshipDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#relationship").val(n.value);
        }
    });
    if(relationshipDict.length>0) {
        $("#relationshipId").combobox('select', relationshipDict[0].value);
    }
    //入院来源
    $('#fromOtherPlaceIndicatorId').combobox({
        data: resourceDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#fromOtherPlaceIndicator").val(n.value);
        }
    });
    if(resourceDict.length>0) {
        $("#fromOtherPlaceIndicatorId").combobox('select',resourceDict[0].value);
    }

    //入院方式
    $('#patientClassId').combobox({
        data: patientClass,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#patientClass").val(n.value);
        }
    });
    if(patientClass.length>0) {
        $("#patientClassId").combobox('select', patientClass[0].value);
    }
    //住院目的
    $('#admissionCauseId').combobox({
        data: objectiveDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#admissionCause").val(n.value);
        }
    });
    if(objectiveDict.length>0) {
        $("#admissionCauseId").combobox('select', objectiveDict[0].value);
    }
    //病情
    $('#patAdmConditionId').combobox({
        data: admissionDict,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#patAdmCondition").val(n.value);
        }
    });
    if(admissionDict.length>0) {
        $("#patAdmConditionId").combobox('select', admissionDict[0].value);
    }

    //入院科室
    $('#deptAdmissionToId').combobox({
        data: clinicDeptCode,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n, o) {
            $("#deptAdmissionTo").val(n.value);
        }
    });
    if(clinicDeptCode.length>0) {
        $("#deptAdmissionTo").combobox('select', clinicDeptCode[0].value);
    }

    //诊断
    $('#diagnosis').combogrid({
        data: icdAllData,
        idField: 'zhongwen_mingcheng',
        textField: 'zhongwen_mingcheng',
        mode: 'remote',
        columns: [[
            {field: 'zhongwen_mingcheng', title: '中文名称', width: '30%', align: 'left'},
            {field: 'code', title: 'ICD-10编码', width: '20%', align: 'left'},
            {field: 'keyword_shuoming', title: '关键词', width: '50%', align: 'left'},
        ]],onClickRow:function(rowIndex,rowData) {
            $("#diagnosisNo").textbox("setValue",rowData.code);
        }, keyHandler: {
            query: function (q) {
                comboGridCompleting(q, 'diagnosis');
                $('#diagnosis').combogrid("grid").datagrid("loadData", comboGridComplete);
            }
        }
    });
    //接诊医生
    $('#consultingDoctor').combogrid({
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

});

//列表条件查询
function searchByCondition(){
    var name=$("#name").textbox('getValue');
    var idNo=$("#idNo").textbox('getValue');
    var hospNo=$("#hospNo").textbox('getValue');
   /* var ybType=$("#ybType").textbox('getValue');
    var ybNo=$("#ybNo").textbox('getValue');*/
    $("#orderList").datagrid({url:basePath+'/patMasterIndex/list',queryParams:{"name":name,"idNo":idNo,"hospNo":hospNo}});
}
//根据身份证号查询是否是该院病人
function validIdNo(idNo){
    $.ajax({
        'type': 'POST',
        'url': basePath + '/patMasterIndex/validateIdCard',
        'contentType': 'application/json',
        'data': idNo = idNo,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == '1') {
                $.messager.alert("提示消息", "非该院登记病人，请进行登记！");
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "删除失败", "error");
        }
    });
}
//根据病人主索引查询该病人是否是在院病人
function validIfInHosp(id){
    $.ajax({
        'type': 'POST',
        'url': basePath + '/patMasterIndex/validIfInHosp',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == '1') {
                $.messager.alert("提示消息", "该病人是在院病人，不能进行重复登记！");
                return false
            } else if(data.data == '2'){
                return true;
            }else{
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "删除失败", "error");
        }
    });
}

//保存
function saveMaster() {
    if($("#masterForm").form('validate')) {
        //根据身份证号去patmasterindex查询是否有该人，如果有，在根据id去pat_in_hospital查询patient_id是否已住院，若未住院，则回填，若已住院则提示，否则新增
        if(validIfInHosp($("#id").val())){
            $.postForm(basePath + '/patMasterIndex/save', 'masterForm', function (data) {
                if (data.data == 'success') {
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

    }
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





