var rowNumZ=0;
var orderNo=0;
var prescNo;
var itemClass;
var clinicId;
var chargeIndicator='新开';
//页面加载
$(function() {
    itemClass = $("#itemClass").val();
    clinicId = parent.clinicMaster.id;
    $("#clinicId").val(clinicId);



    //途径
    $('#administration').combobox({
        data: administrationmzDict,
        valueField: 'id',
        textField: 'administrationName',
        required:true
    });
    //$("#administration").combobox('select',administrationmzDict[0].value);
    //频次
    $('#frequency').combobox({
        data: performFreqDict,
        valueField: 'id',
        textField: 'freqDesc',
        required:true
    });
    //自动补全药品
    $("#drugNameId").keyup(function(event){
        var q=$("#drugNameId").val();
        comboGridCompletingHerbalDrug(q,'');
        $('#drugNameTableId').datagrid("loadData", herbalDrugData);
    });
    //$("#frequency").combobox('select',performFreqDict[0].value);
    $('#drugNameTableId').datagrid({
        width: 'auto',
        height: 'auto',
        data: herbalDrugData,
        columns: [[
            {field: 'item_name', title: '名称', width: '30%', align: 'center'},
            {field: 'drug_spec', title: '规格', width: '10%', align: 'center'},
            {field: 'quanity', title: '库存', width: '10%', align: 'center'},
            {field: 'units', title: '包装单位', width: '8%', align: 'center'},
            {field: 'item_class', title: '库房', width: '8%', align: 'center'},
            {field: 'supplier', title: '厂家', width: '20%', align: 'center'},
            {field: 'dose_per_unit', title: '单次用量', width: '8%', align: 'center'},
            {field: 'dose_units', title: '用量单位', width: '8%', align: 'center'},
            {field: 'subj_code', title: '',hidden:true},
            {field: 'performed_by', title: '',hidden:true},
            {field: 'price', title: '',hidden:true},
            {field: 'firm_id', title: '',hidden:true},
            {field: 'drug_code',hidden:true}
        ]],onClickRow: function (index, row) {
            var i=$("#numHideId").val();
            $("#drugName"+i).val(row.item_name);
            $("#drugCode"+i).val(row.drug_code);
            $("#drugSpec"+i).val(row.drug_spec);
            $("#firmId"+i).val(row.firm_id);
            $("#dosage"+i).val(row.dose_per_unit);
            $("#dosageUnits"+i).val(row.dose_units);
            $("#itemClass"+i).val(row.item_class);
            $("#span"+i).text(row.dose_units);
            $("#units"+i).val(row.dose_units);
            $("#subjCode"+i).val(row.subj_code);
            $("#performedBy"+i).val(row.performed_by);
            $("#charges"+i).val(row.price);
            $("#drugNameDialog").dialog('close');
        }
    });
    $("#addBtn").on("click",function(){
        rowNumZ++;
        /**药品**/
        var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
        if(selRow!=null&&selRow!=''&&selRow!='undefined') {
            var html='';
            html += '<li  style="position:relative;" onclick="centerActive(this,\'herbalHide'+rowNumZ+'\')" id="herbal'+rowNumZ+'" inputhide="herbalHide'+rowNumZ+'">' +
                '<div><input  type="text" id="freqDetail'+rowNumZ+'" namehide="freqDetail" inputhide="herbalHide'+rowNumZ+'"  class="easyui-textbox" style="width: 200px;"/></div>' +
                '<input  type="text" id="drugName'+rowNumZ+'" namehide="drugName" class="easyui-textbox" onclick="openOombogrid(this,\''+rowNumZ+'\')"  inputhide="herbalHide'+rowNumZ+'" style="width: 150px"/>' +
                '<input type="text" value="0" style="width: 50px" class="easyui-textbox" id="amount'+rowNumZ+'" namehide="amount" inputhide="herbalHide'+rowNumZ+'"/>' +
            '<span id="span'+rowNumZ+'" class="color-blue" style="padding-left:10px;"></span>' +
                '<a class="ul_li_a" href="#" onclick="delActive(this)" >X</a>' +
                '<input type="hidden" id="drugCode'+rowNumZ+'" namehide="drugCode" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="drugSpec'+rowNumZ+'" namehide="drugSpec" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="firmId'+rowNumZ+'" namehide="firmId" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="dosage'+rowNumZ+'" namehide="dosage" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="dosageUnits'+rowNumZ+'" namehide="dosageUnits" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="itemClass'+rowNumZ+'" namehide="itemClass" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="units'+rowNumZ+'" namehide="units" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="subjCode'+rowNumZ+'" namehide="subjCode" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="performedBy'+rowNumZ+'" namehide="performedBy" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="orderNo'+rowNumZ+'" namehide="orderNo" inputhide="herbalHide'+rowNumZ+'" value="'+rowNumZ+'"/> ' +
                '<input type="hidden" id="subOrderNo'+rowNumZ+'" namehide="subOrderNo" inputhide="herbalHide'+rowNumZ+'" value="'+rowNumZ+'"/> ' +
                '<input type="hidden" id="charges'+rowNumZ+'" namehide="charges" inputhide="herbalHide'+rowNumZ+'" /> '+
                '<input type="hidden" id="serialNo'+rowNumZ+'" namehide="serialNo" inputhide="herbalHide'+rowNumZ+'" /> ';
            $("#herbal_ul").append(html);
            html='';
          /*  $('#freqDetail'+rowNumZ).textbox("setValue",'');
            $('#amount'+rowNumZ).numberbox("setValue",'');*/


        }else{
            $.messager.alert("提示消息", "请选择处方后再进行添加操作!");
            return false;
        }
    });

});

function delActive(obj){
    $("#herbal_ul .active").remove();
}

function centerActive(li){
    var classLi=$(li).attr("class");
    if(classLi=='active'){
        $(li).removeClass();
    }else{
        $(li).attr("class","active");
    }
}
function openOombogrid(input,i){
    $("#numHideId").val(i);
    $("#drugNameDialog").dialog('open');
}

