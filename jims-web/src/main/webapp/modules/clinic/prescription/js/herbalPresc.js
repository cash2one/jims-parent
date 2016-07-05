var rowNumZ=0;
var orderNo=0;
var prescNo;
var itemClass;
var clinicId;
var chargeIndicator='新开';
//页面加载
$(function() {
    itemClass = $("#itemClass").val();
    clinicId = $("#clinicMasterId", parent.document).val();
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
    //$("#frequency").combobox('select',performFreqDict[0].value);



    $("#addBtn").on("click",function(){
        rowNumZ++;
        /**药品**/
        var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
        if(selRow!=null&&selRow!=''&&selRow!='undefined') {
            var html = '<li onclick="centerActive(this,\'herbalHide'+rowNumZ+'\')" id="herbal'+rowNumZ+'" inputhide="herbalHide'+rowNumZ+'">' +
                '<span style="padding-right:10px;"><input  type="text" id="drugName'+rowNumZ+'" class="easyui-combogrid" style="width: 100px;" value=""/></span>' +
                '<a href="#" class="color-red"><input  type="text" id="drugName'+rowNumZ+'" class="easyui-textbox" style="width: 100px" value=""/></a>' +
                '<input type="text" value="0.0" style="width: 50px" class="easyui-numberbox" id="amount'+rowNumZ+'" namehide="amount" inputhide="herbalHide'+rowNumZ+'"/>' +
                '<input type="hidden" id="drugCode'+rowNumZ+'" namehide="drugCode" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="drugSpec'+rowNumZ+'" namehide="drugSpec" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="firmId'+rowNumZ+'" namehide="firmId" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="dosage'+rowNumZ+'" namehide="dosage" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="dosageUnits'+rowNumZ+'" namehide="dosageUnits" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="itemClass'+rowNumZ+'" namehide="itemClass" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="units'+rowNumZ+'" namehide="units" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="subjCode'+rowNumZ+'" namehide="subjCode" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="performedBy'+rowNumZ+'" namehide="performedBy" inputhide="herbalHide'+rowNumZ+'" /> ' +
                '<input type="hidden" id="orderNo'+rowNumZ+'" namehide="orderNo" inputhide="herbalHide'+rowNumZ+'" value="3"/> ' +
                '<input type="hidden" id="subOrderNo'+rowNumZ+'" namehide="subOrderNo" inputhide="herbalHide'+rowNumZ+'" value="3"/> ' +

                '<input type="hidden" id="charges'+rowNumZ+'" namehide="charges" inputhide="herbalHide'+rowNumZ+'" /> ';
            $("#herbal_ul").append(html);
            $('#drugName'+rowNumZ).combogrid({
                width: '300',
                height: 'auto',
                data: herbalDrugData,
                idField:'item_name',
                textField:'item_name',
                mode: 'remote',
                columns: [[
                    {field: 'drug_code', title: '代码', width: '8%', align: 'center'},
                    {field: 'item_name', title: '名称', width: '15%', align: 'center'},
                    {field: 'drug_spec', title: '规格', width: '15%', align: 'center'},
                    {field: 'quanity', title: '库存', width: '15%', align: 'center'},
                    {field: 'units', title: '包装单位', width: '15%', align: 'center'},
                    {field: 'item_class', title: '库房', width: '15%', align: 'center'},
                    {field: 'supplier', title: '厂家', width: '15%', align: 'center'},
                    {field: 'dose_per_unit', title: '单次用量', width: '15%', align: 'center'},
                    {field: 'dose_units', title: '用量单位', width: '15%', align: 'center'},
                    {field: 'subj_code', title: '',hidden:true},
                    {field: 'performed_by', title: '',hidden:true},
                    {field: 'price', title: '',hidden:true},
                    {field: 'firm_id', title: '',hidden:true}
                ]], keyHandler: {
                    query: function (q) {
                        comboGridCompleting(q, 'drugName'+rowNumZ);
                        $('#drugName'+rowNumZ).combogrid("grid").datagrid("loadData", comboGridComplete);
                    }
                },onClickRow: function (index, row) {
                    $("#drugName"+rowNumZ).val(row.item_name);
                    $("#drugCode"+rowNumZ).val(row.drug_code);
                    $("#drugSpec"+rowNumZ).val(row.drug_spec);
                    $("#firmId"+rowNumZ).val(row.firm_id);
                    $("#dosage"+rowNumZ).val(row.dose_per_unit);
                    $("#dosageUnits"+rowNumZ).val(row.dose_units);
                    $("#itemClass"+rowNumZ).val(row.item_class);
                    $("#units"+rowNumZ).val(row.dose_units);
                    $("#subjCode"+rowNumZ).val(row.subj_code);
                    $("#performedBy"+rowNumZ).val(row.performed_by);
                    $("#charges"+rowNumZ).val(row.price);
                }
            })

        }else{
            $.messager.alert("提示消息", "请选择处方后再进行添加操作!");
            return false;
        }
    });

    $("#delBtn").on("click",function(){
        $("#herbal_ul .active").remove();
    })

    $("#saveBtn").on("click",function(){

        alert(submitJson);
    })
});

function centerActive(li){
    var classLi=$(li).attr("class");
    if(classLi=='active'){
        $(li).removeClass();
    }else{
        $(li).attr("class","active");
    }
}