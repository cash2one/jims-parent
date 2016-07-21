/**
 * Created by Administrator on 2016/5/7.
 */
$(function () {
    var orgId = config.org_Id;
    var itemClassList = []; //项目类别
    $.get(basePath + '/dict/label-value-list?type=' + 'BILL_ITEM_CLASS_DICT', function(data){
        itemClassList = data;
    });
    init_clinic_data();
    //类别
    $("#item_class").combobox({
        valueField: 'value',
        textField: 'label',
        width: 150,
        method: 'GET',
        url: basePath + '/dict/label-value-list?type=' + 'BILL_ITEM_CLASS_DICT',
        editable: false,
         onSelect: function () {
             var itemClass = $("#item_class").combobox('getValue');
             $.get(basePath + '/price/find-by-item-class?itemClass=' + itemClass + '&orgId=' + orgId, function(data){
                 reset();
                 $("#clinic_item").datagrid('loadData',data);
             });
         }
    });
    //项目类别
    $("#aa").combobox({
        valueField: 'value',
        textField: 'label',
        width: 150,
        method: 'GET',
        url: basePath + '/dict/label-value-list?type=' + 'BILL_ITEM_CLASS_DICT',
        editable: false
    });
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
    //是否自费   1,自费；0,非自费
    $("#feeTypeMask").on("click", function () {
        if ($("#feeTypeMask").prop("checked") == true) {
            $("#feeTypeMask").val(1);
        } else {
            $("#feeTypeMask").val(0);
        }
    });
    //是否生成诊疗项目
    $("#clinicDict").on("click", function () {
        if ($("#clinicDict").prop("checked") == true) {
            $("#clinicDict").val(1);
        } else {
            $("#clinicDict").val(0);
        }
    });
    //生成代码
    $("#generate").on("click", function () {
        var code = $("#aa").combobox("getValue");   //项目类别的值
        if(code == ''){
            $.messager.alert('提示','请选择项目类别','error');
            return ;
        }
        $.ajax({
            'type': 'GET',
            'url': basePath + '/price/code/' + code,
            'success': function (data) {
                $("#itemCode").textbox('setValue', data.data);
                $.messager.alert('提示', "成功生成项目代码!", "success");

            },
            'error': function (data) {
                $.messager.alert('提示', "获取失败", "error");
            }
        });
    });
    //保存当前标签页
    $("#saveDict").on("click", function () {
        var priceDictListVo = {};
        priceDictListVo.itemClass = $("#aa").combobox('getValue');  //类别
        priceDictListVo.itemName = $("#itemName").val();            //名称
        priceDictListVo.itemCode = $("#itemCode").val();            //代码
        priceDictListVo.itemSpec = $("#itemSpec").val();            //规格
        priceDictListVo.units = $("#units").val();                  //计价单位
        priceDictListVo.price = $("#price").val();                  //基本价格
        priceDictListVo.preferPrice = $("#preferPrice").val();    //优惠价格
        priceDictListVo.foreignerPrice = $("#foreignerPrice").val();    //外宾价格
        priceDictListVo.startDate = $("#dt").datetimebox('getValue');    //启用日期
        priceDictListVo.performedBy = $("#performedBy").combogrid('getValue');  //执行科室
        priceDictListVo.feeTypeMask = $("#feeTypeMask").val();   //是否自费
        priceDictListVo.classOnInpRcpt = $("#dd").combobox('getValue');     //住院收据
        priceDictListVo.classOnOutpRcpt = $("#cc").combobox('getValue');     //门诊收据
        priceDictListVo.classOnReckoning = $("#ff").combobox('getValue');     //核算科目
        priceDictListVo.subjCode = $("#bb").combobox('getValue');           //会计科目
        priceDictListVo.classOnMr = $("#ee").combobox('getValue');      //病案首页
        priceDictListVo.memo = $("#memo").val();            //备注信息
        priceDictListVo.inputCode = $("#inputCode").val();      //拼音码
        priceDictListVo.materialCode = $("#materialCode").val();    //物价码
        priceDictListVo.clinicDict = $("#clinicDict").val();        //诊疗标识
        priceDictListVo.orgId = orgId;      //所属组织机构

        if(priceDictListVo){
            $.postJSON(basePath + '/price/save',JSON.stringify(priceDictListVo),function(data){
                if (data.data == 'success') {
                    $.messager.alert("提示消息", "保存成功", "success");
                    reset();
                } else {
                    $.messager.alert('提示消息', data.code, "error");
                }
            },function(data){
                $.messager.alert('提示', "保存失败", "error");
            });
        }
    });
    //清空输入框
    var reset = function(){
        $("#aa").combobox('setValue','');  //类别
        $("#itemName").val('');            //名称
        $("#itemCode").textbox('setValue','');    //代码
        $("#itemSpec").textbox('setValue', '');         //规格
        $("#units").textbox('setValue','');           //计价单位
        $("#price").textbox('setValue','0.00');               //基本价格
        $("#preferPrice").textbox('setValue','0.00');   //优惠价格
        $("#foreignerPrice").textbox('setValue','0.00');    //外宾价格
        $("#dt").datetimebox('setValue','');    //启用日期
        $("#performedBy").combogrid('setValue','');  //执行科室
        $("#feeTypeMask").prop('checked',false);   //是否自费
        $("#dd").combobox('setValue','');     //住院收据
        $("#cc").combobox('setValue', '');     //门诊收据
        $("#ff").combobox('setValue', '');     //核算科目
        $("#bb").combobox('setValue', '');           //会计科目
        $("#ee").combobox('setValue', '');      //病案首页
        $("#memo").val('');            //备注信息
        $("#inputCode").textbox('setValue','');      //拼音码
        $("#materialCode").textbox('setValue','');    //物价码
        $("#clinicDict").prop('checked',false);        //诊疗标识
    }

    // 刷新当前标签页
    $("#refresh").on("click", function () {
        window.location.reload();
    });
    function init_clinic_data() {
        $("#clinic_item").datagrid({
            title: "价表管理列表",
            //url: basePath +'/price/find',
            method:'get',
            idField: 'id',
            toolbar: '#tb',
            fit: true,
            pagination: false, //显示分页
            singleSelect: true,//是否单选
            pageSize: 10, //页大小
            pageList: [10, 15, 20, 25], //页大小下拉选项此项各value是pageSize的倍数
            fitColumns: true, //列自适应宽度
            columns: [[//显示的列
                {field: 'itemCode', title: '项目代码', width: '15%',align: 'center'},
                {field: 'itemName', title: '项目名称', width: '25%',align: 'center'},
                {field: 'itemSpec', title: '项目规格', width: '15%',align: 'center'},
                {field: 'materialCode', title: '物价码', width: '15%',align: 'center'},
                {
                    field: 'itemClass',
                    title: '项目类别',
                    width: '15%',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var itemClass = value;
                        $.each(itemClassList, function (index, item) {
                            if (item.value == value) {
                                itemClass = item.label;
                            }
                        });
                        return itemClass;
                    }
                },
                {field: 'inputCode', title: '拼音码', width: '15%',align: 'center'}
            ]],
            onDblClickRow: function(index,row){
                $("#aa").combobox('setValue',row.itemClass);
                $("#itemCode").textbox('setValue',row.itemCode);
                $("#materialCode").textbox('setValue',row.materialCode);
                $("#itemSpec").textbox('setValue',row.itemSpec);
                $("#units").textbox('setValue',row.units);
                $("#itemName").val(row.itemName);
                $("#inputCode").textbox('setValue',row.inputCode);
                $("#price").textbox('setValue',row.price);
                $("#preferPrice").textbox('setValue',row.preferPrice);
                $("#foreignerPrice").textbox('setValue',row.foreignerPrice);
                $("#dt").textbox('setValue',row.startDate);
                $("#performedBy").combogrid('setValue',row.performedBy);
                $("#memo").val(row.memo);
                $("#bb").combobox('setValue',row.subjCode);
                $("#cc").combobox('setValue',row.classOnOutpRcpt);
                $("#dd").combobox('setValue',row.classOnInpRcpt);
                $("#ee").combobox('setValue',row.classOnMr);
                $("#ff").combobox('setValue',row.classOnReckoning);
            }

        });
    }
});

/**
 * 修改字典
 * @param id
 */
function get(id) {
    $.ajax({
        'type': 'post',
        'url': basePath + '/price/get',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $('#pro_form').form('load', data);
            $('#add_pro_win').window({
                title: '修改价表项目',
                width: '350',
                height: '200',
                resizable: false
            })
        }
    });
}

//批量删除
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#clinic_item').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }

    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            //strIds = strIds.substr(0, strIds.length - 1);
            del(strIds);
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
        'url': basePath + '/price/del',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == 'success') {
                $.messager.alert("提示消息", data.code + "条记录，已经删除");
                $('#clinic_item').datagrid('load');
                $('#clinic_item').datagrid('clearChecked');
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "删除失败", "error");
        }
    });
}

function ShowInfo() {
    var oDiv = $("#itemName").val();    //获取项目名称的值
    if (oDiv != '') {
        $.ajax({
            'type': 'GET',
            'url': basePath + '/price/abb/' + oDiv,
            'success': function (data) {
                $("#inputCode").textbox('setValue', data.code);
            }
        });
    } else {
        $("#inputCode").textbox('setValue', "");
    }
}
//根据拼音码定位加载数据
function load_data() {
    var inputCode = $('#code_gps').val();
    if(inputCode && inputCode != ""){
        $.ajax({
            'type': 'GET',
            'url': basePath + '/price/get-by-inputCode?inputCode=' + inputCode + '&orgId=' + config.org_Id,
            'success': function (data) {
                $("#clinic_item").datagrid('loadData', data);
            }
        });
    }else{
        $("#clinic_item").datagrid('loadData', []);
        $("#aa").combobox('setValue', '');  //类别
        $("#itemName").val('');            //名称
        $("#itemCode").textbox('setValue', '');    //代码
        $("#itemSpec").textbox('setValue', '');         //规格
        $("#units").textbox('setValue', '');           //计价单位
        $("#price").textbox('setValue', '0.00');               //基本价格
        $("#preferPrice").textbox('setValue', '0.00');   //优惠价格
        $("#foreignerPrice").textbox('setValue', '0.00');    //外宾价格
        $("#dt").datetimebox('setValue', '');    //启用日期
        $("#performedBy").combogrid('setValue', '');  //执行科室
        $("#feeTypeMask").prop('checked', false);   //是否自费
        $("#dd").combobox('setValue', '');     //住院收据
        $("#cc").combobox('setValue', '');     //门诊收据
        $("#ff").combobox('setValue', '');     //核算科目
        $("#bb").combobox('setValue', '');           //会计科目
        $("#ee").combobox('setValue', '');      //病案首页
        $("#memo").val('');            //备注信息
        $("#inputCode").textbox('setValue', '');      //拼音码
        $("#materialCode").textbox('setValue', '');    //物价码
        $("#clinicDict").prop('checked', false);        //诊疗标识
    }

}


