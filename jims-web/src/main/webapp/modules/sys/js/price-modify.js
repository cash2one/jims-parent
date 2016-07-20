/**
 * Created by fyg on 2016/5/7.
 */
var id = '';    //主键ID
var itemClass = ''; //项目类别
var feeTypeMask = '';   //自费标志
var memo = '';  //备注信息
$(function () {
    var orgId = config.org_Id;
    //会计科目
    $('#bb').combobox({
        url: basePath + '/dict/label-value-list?type=' + 'TALLY_SUBJECT_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        editable: false
    });
    //门诊收据
    $('#cc').combobox({
        url: basePath + '/dict/label-value-list?type=' + 'OUTP_RCPT_FEE_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        editable: false
    });
    //住院收据
    $('#dd').combobox({
        url: basePath + '/dict/label-value-list?type=' + 'INP_RCPT_FEE_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        editable: false
    });
    //病案首页
    $('#ee').combobox({
        url: basePath + '/dict/label-value-list?type=' + 'MR_FEE_CLASS_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        editable: false
    });
    //核算项目
    $('#ff').combobox({
        url: basePath + '/dict/label-value-list?type=' + 'RECK_ITEM_CLASS_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        editable: false
    });
    //启用日期
    $('#dt').datetimebox({
        showSeconds: false
    });
    //停用日期
    $('#stopDate').datetimebox({
        showSeconds: false
    });

    //执行科室
    $("#performedBy").combogrid({
        panelWidth: 300,
        idField: 'deptCode',
        textField: 'deptName',
        mode: 'remote',
        method: 'GET',
        url: basePath + '/dept-dict/findListWithFilter?orgId=' + orgId,
        columns: [[
            {field: 'deptCode', title: '科室编码', width: 100},
            {field: 'deptName', title: '科室名称', width: 100},
            {field: 'inputCode', title: '拼音码', width: 100}
        ]], filter: function (q, row) {

            return row.inputCode.indexOf(q) == 0;
        }
    });

    $("#stopDate").textbox('disable');
    //是否停用
    $("#flag").on("click", function () {
        if ($("#flag").prop("checked") == true) {
            $("#stopDate").textbox('enable');
        }
    });

    //保存当前标签页
    $("#saveDict").on("click", function () {
        var priceDictListVo = {};
        priceDictListVo.itemName = $("#itemName").val();            //名称
        priceDictListVo.itemCode = $("#itemCode").val();            //代码
        priceDictListVo.itemSpec = $("#itemSpec").val();            //规格
        priceDictListVo.units = $("#units").val();                  //计价单位
        priceDictListVo.price = $("#price").val();                  //基本价格
        priceDictListVo.preferPrice = $("#preferPrice").val();    //优惠价格
        priceDictListVo.foreignerPrice = $("#foreignerPrice").val();    //外宾价格
        priceDictListVo.startDate = $("#dt").datetimebox('getValue');    //启用日期
        priceDictListVo.stopDate = $("#stopDate").datetimebox('getValue');    //停用日期
        priceDictListVo.performedBy = $("#performedBy").combogrid('getValue');  //执行科室
        priceDictListVo.classOnInpRcpt = $("#dd").combobox('getValue');     //住院收据
        priceDictListVo.classOnOutpRcpt = $("#cc").combobox('getValue');     //门诊收据
        priceDictListVo.classOnReckoning = $("#ff").combobox('getValue');     //核算科目
        priceDictListVo.subjCode = $("#bb").combobox('getValue');           //会计科目
        priceDictListVo.classOnMr = $("#ee").combobox('getValue');      //病案首页
        priceDictListVo.inputCode = $("#inputCode").val();      //拼音码
        priceDictListVo.materialCode = $("#materialCode").val();    //物价码
        priceDictListVo.orgId = orgId;      //所属组织机构

        priceDictListVo.itemClass = itemClass;  //项目类别
        priceDictListVo.feeTypeMask = feeTypeMask;  //自费标志
        priceDictListVo.memo = memo;  //备注信息
        priceDictListVo.id = id;  //主键

        console.log(priceDictListVo);
        if (priceDictListVo) {
            $.postJSON(basePath + '/price/update-price', JSON.stringify(priceDictListVo), function (data) {
                if (data.data == 'success') {
                    $.messager.alert("提示消息", "保存成功", "success");
                    reset();
                } else {
                    $.messager.alert('提示消息', data.code, "error");
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            });
        }
    });

    $("#reset").on('click',function(){
        reset();
    });
    //清空输入框
    var reset = function () {
        $("#itemName").val('');  //项目名称
        $("#inputCode").textbox('setValue',''); //拼音码
        $("#itemCode").val('');  //项目代码
        $("#materialCode").val('');  //物价码
        $("#itemSpec").val('');   //项目规格
        $("#units").val('');         //计价单位
        $("#price").val('');         //基本价格
        $("#preferPrice").val('');   //优惠价格
        $("#foreignerPrice").val('');  //外宾价格
        $("#dt").datetimebox('setValue', '');   //启用日期
        $("#stopDate").datetimebox('setValue','');  //停用日期
        $("#performedBy").combogrid('setValue', '');    //执行科室
        $("#dd").combobox('setValue', '');     //住院收据
        $("#cc").combobox('setValue', '');     //门诊收据
        $("#ff").combobox('setValue', '');     //核算科目
        $("#bb").combobox('setValue', '');     //会计科目
        $("#ee").combobox('setValue', '');     //病案首页

        $("#flag").attr("checked",false);   //停用日期取消选中
    }
});

//根据项目名称输入框输入的拼音码查询数据赋值给其他输入框
function ShowInfo() {
    var itemName = $("#itemName").val(); //获取项目名称的值
    if (itemName && itemName != "") {
        $.ajax({
            'type': 'GET',
            'url': basePath + '/price/get-by-inputCode?inputCode=' + itemName + '&orgId=' + config.org_Id,
            'success': function (data) {
                console.log(data);
                if(data && data != ""){
                    id = data[0].id;    //主键ID
                    itemClass = data[0].itemClass;  //项目类别
                    feeTypeMask = data[0].feeTypeMask; //自费标志
                    memo = data[0].memo;  //备注信息

                    $("#itemName").val(data[0].itemName);  //项目名称
                    $("#itemCode").val(data[0].itemCode);  //项目代码
                    $("#materialCode").val(data[0].materialCode);  //物价码
                    $("#itemSpec").val(data[0].itemSpec);   //项目规格
                    $("#units").val(data[0].units);         //计价单位
                    $("#price").val(data[0].price);         //基本价格
                    $("#preferPrice").val(data[0].preferPrice);   //优惠价格
                    $("#foreignerPrice").val(data[0].foreignerPrice);  //外宾价格
                    $("#dt").datetimebox('setValue', data[0].startDate);   //启用日期
                    $("#performedBy").combogrid('setValue', data[0].performedBy);    //执行科室
                    $("#dd").combobox('setValue', data[0].classOnInpRcpt);     //住院收据
                    $("#cc").combobox('setValue', data[0].classOnOutpRcpt);     //门诊收据
                    $("#ff").combobox('setValue', data[0].classOnReckoning);     //核算科目
                    $("#bb").combobox('setValue', data[0].subjCode);     //会计科目
                    $("#ee").combobox('setValue', data[0].classOnMr);     //病案首页

                    if(data[0].stopDate && data[0].stopDate != ""){
                        //$("#flag").prop("checked");
                        $("#flag").attr("checked",'checked');
                        $("#stopDate").textbox('enable');
                        $("#stopDate").datetimebox('setValue', data[0].stopDate);   //启用日期
                    }
                    //if ($("#flag").prop("checked") == true) {
                    //    $("#stopDate").textbox('enable');
                    //}
                }
            }
        });
    }
    if (itemName != '') {
        $.ajax({
            'type': 'GET',
            'url': basePath + '/price/abb/' + itemName,
            'success': function (data) {
                $("#inputCode").textbox('setValue', data.code);
            }
        });
    } else {
        $("#inputCode").textbox('setValue', "");
    }
}

