/**
 * 药理信息维护
 * @author yangruidong
 * @version 2016-05-13
 */
$(function () {
    //数组用于存放剂型
    var drugFrom=[];
    $.get(basePath + "/dict/findListByType?type=DRUG_FORM_DICT",function (data) {
       drugFrom=data;
    });



    $("#drugName").combogrid({
        panelWidth: 300,
        idField: 'drugCode',
        textField: 'drugName',
        mode: 'remote',
        rownumbers: 'true',
        url: basePath + '/drug-catalog/drugNameDictList',
        method: 'get',
        columns: [[
            {field: 'drugCode', title: '代码', width: 80, align: 'center'},
            {field: 'drugName', title: '名称', width: 120, align: 'center'},
            {field: 'inputCode', title: '拼音码', width: 100, align: 'center'}
        ]],
        fitColumns: true ,
        onSelect: function(rowIndex,rowData){
            $.get(basePath + "/drug-info/findDrugInfoByDrugCode?drugCode=" + rowData.drugCode, function (data) {
                $("#drugCode").textbox('setValue', data.drugCode);
                $("#drugName1").textbox('setValue', data.drugName);
                $("#drugName2").textbox('setValue', data.drugEName);
                $("#drug-conduct").textbox('setValue', data.action);
                $("#adaptive").textbox('setValue', data.indication);
                $("#adverse").textbox('setValue',data.adverseReaction);
                $("#use").textbox('setValue', data.dosage);
                for(var i=0;i<drugFrom.length;i++)
                {
                    if(data.drugForm==drugFrom[i].value)
                    {
                        data.drugForm=drugFrom[i].label;
                    }
                }
                $("#type").textbox('setValue', data.drugForm);
                $("#drug-action").textbox('setValue', data.pharmacokinetics);
                $("#attention").textbox('setValue', data.attention);
                $("#taboo").textbox('setValue', data.contraindication);
                $("#id").val(data.id);
            });
        }
    });

    //通过药品的代码查询药品毒理的相关信息
    $("#seaBtn").on('click', function () {
        var drugCode = $("#drugName").combogrid('getValue');
        $.get(basePath + "/drug-info/findDrugInfoByDrugCode?drugCode=" + drugCode, function (data) {
            $("#drugCode").textbox('setValue', data.drugCode);
            $("#drugName1").textbox('setValue', data.drugName);
            $("#drugName2").textbox('setValue', data.drugEName);
            $("#drug-conduct").textbox('setValue', data.action);
            $("#adaptive").textbox('setValue', data.indication);
            $("#use").textbox('setValue', data.dosage);
            for(var i=0;i<drugFrom.length;i++)
            {
                if(data.drugForm==drugFrom[i].value)
                {
                    data.drugForm=drugFrom[i].label;
                }
            }
            $("#type").textbox('setValue', data.drugForm);
            $("#drug-action").textbox('setValue', data.pharmacokinetics);
            $("#attention").textbox('setValue', data.attention);
            $("#taboo").textbox('setValue', data.contraindication);
        });
    });
    //点击添加按钮时，清空文本框
    $("#addBtn").on('click', function () {
        clearInput();
    });
    //保存药品毒理信息
    $("#saveBtn").on('click', function () {
        var drugInfo = {};
        drugInfo.id=$("#id").val();
        drugInfo.drugCode = $("#drugCode").textbox('getValue');
        drugInfo.drugName = $("#drugName1").textbox('getValue');
        drugInfo.drugEName = $("#drugName2").textbox('getValue');
        drugInfo.action = $("#drug-conduct").textbox('getValue');
        drugInfo.indication = $("#adaptive").textbox('getValue');
        drugInfo.adverseReaction=$("#adverse").textbox('getValue');
        drugInfo.dosage = $("#use").textbox('getValue');
        var from=$("#type").textbox('getValue');
        for(var i=0;i<drugFrom.length;i++)
        {
            if(from==drugFrom[i].label)
            {
                from=drugFrom[i].value;
            }
        }
        drugInfo.form =from;
        drugInfo.pharmacokinetics = $("#drug-action").textbox('getValue');
        drugInfo.attention = $("#attention").textbox('getValue');
        drugInfo.contraindication = $("#taboo").textbox('getValue');
        $.postJSON(basePath + "/drug-info/save", JSON.stringify(drugInfo), function (data) {
            if (data.data == "success") {
                $.messager.alert("系统提示", "保存成功");
            }
            else {
                $.messager.alert("系统提示", "保存失败");
            }
        });

    });
    //清空文本框
    var clearInput=function(){
        $("#id").val('');
        $("#drugName").textbox('setValue','');
        $("#drugCode").textbox('setValue','');
        $("#drugName1").textbox('setValue','');
        $("#drugName2").textbox('setValue','');
        $("#drug-conduct").textbox('setValue','');
        $("#adaptive").textbox('setValue','');
        $("#adverse").textbox('setValue','');
        $("#use").textbox('setValue','');
        $("#type").textbox('setValue','');
        $("#drug-action").textbox('setValue','');
        $("#attention").textbox('setValue','');
        $("#taboo").textbox('setValue','');
    };
});




