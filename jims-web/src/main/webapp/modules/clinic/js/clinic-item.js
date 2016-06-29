$(function(){
    var currentOrgId = '1'   // 当前机构ID
        ,currentSelectClinicIndex   //诊疗项目 当前选择行
        ,currentSelectNameIndex   // 正别名当前选择行
        ,currentSelectVsIndex ;   // 对照项目当前选择行

    // 长期、临时数据
    var longArr = [{
        "value":1,
        "label":"长期"
    },{
        "value":2,
        "label":"临时"
    }];
    //诊疗项目类别数组
    var typeArr = [];
    //药品价格类别
    var priceTypeArr = [{
        "value":1,
        "label":"药品"
    },{
        "value":2,
        "label":"非药品"
    }]

    /*********** 操作按钮等等 ☟ ***********/
    $('#item_class').combobox({
        valueField:'value',
        textField:'label',
        editable : false,
        value:'A',
        onSelect : function(r){
            currentSelectClinicIndex = undefined
            load_data()
        }
    });
    $(':radio[name="adminFlag"][value="0"]').click(function(){
        $('#code_text').html('　代码定位')
        $('#filter_text').html('　代码筛选')
    })
    $(':radio[name="adminFlag"][value="1"]').click(function(){
        $('#code_text').html('拼音码定位')
        $('#filter_text').html('拼音码筛选')
    })
    $('#code_gps').textbox({
        buttonText : '定位',
        width:130,
        onClickButton : function(){
            var value = $('#code_gps').textbox('getText')
            var rows = $('#clinic_item').datagrid('getRows')
            var code_type = $(':radio[name="adminFlag"]:checked').val()
            var code = code_type=='1' ? 'inputCode' : 'itemCode'
            for(var index = 0, j = rows.length;index < j;index ++){
                if(rows[index][code].toUpperCase().indexOf(value.toUpperCase()) == 0){
                    onClickCell_clinic(index)
                    return
                }
            }
        }
    })
    $('#code_filter').textbox({
        buttonText : '筛选',
        width:130,
        onClickButton : function(){
            load_data()
        }
    })
    //加载诊疗项目类别
    $.get('/service/dict/findListByType',{type:'CLINIC_ITEM_CLASS_DICT'},function(res){
        typeArr = res
        $('#item_class').combobox('loadData',res);
    })

    /***********诊疗项目 ☟ ***********/
    $("#clinic_item").datagrid({
        title : "临床诊疗项目列表",
        fit : true,
        border:0,
        fitColumns: true, //列自适应宽度
        singleSelect : true,
        remoteSort: false,
        idField :'id',
        columns: [[//显示的列
            {field: 'id', title: '编号', width: 20, hidden:true},
            { field: 'itemCode', title: '代码', width: 80, sortable: true,order:'desc',align : "center" },
            { field: 'itemName', title: '项目名称', width: 200,halign:'center',align:'left'},
            { field: 'expand3Name', title: '执行科室', width: 120,halign:'center',align:'left',editor:{
                type:'combogrid',
                options:{
                    panelWidth:180,
                    idField:'deptName',
                    textField:'deptName',
                    fitColumns: true,
                    mode:'remote',
                    url:'/service/dept-dict/findListWithFilter?orgId='+currentOrgId,
                    method:'get',
                    columns:[[
                        {field:'deptCode',title:'部门代码',width:60},
                        {field:'deptName',title:'部门名称',width:100}
                    ]],
                    onClickRow:function(index,row){
                        $('#clinic_item').datagrid('getSelected').expand3 = row.id
                        //if(newV != oldV)
                        //    change_name_and_vs(newV,'expand3')

                    }}
            }},
            { field: 'expand4Name', title: '频次',align : "center", width: 80 ,editor:{
                type:'combobox',
                options:{
                    valueField:'freqDesc',
                    textField:'freqDesc',
                    url:'/service/PerformFreqDict/findPer',
                    method:'get',
                    editable:false,
                    required:false,
                    onSelect:function(recode){
                        $('#clinic_item').datagrid('getSelected').expand4 = recode.id
                        //if(newV != oldV)
                        //    change_name_and_vs(newV,'expand4')
                    }
                }
            }},
            { field: 'expand2Name', title: '类别',align : "center", width: 60,editor:{
                type:'combobox',
                options:{
                    valueField:'className',
                    textField:'className',
                    editable:false,
                    url:'/service/labitemclass/findListByOrgId?orgId='+currentOrgId,
                    method:'get',
                    required:false,
                    onSelect:function(recode){
                        $('#clinic_item').datagrid('getSelected').expand2 = recode.id;
                    }
                }
            }},
            { field: 'expand1Name', title: '标本',align : "center", width: 60 ,editor:{
                type:'combobox',
                options:{
                    valueField:'label',
                    textField:'label',
                    editable:false,
                    url:'/service/dict/findListByType?type=SPECIMAN_DICT',
                    method:'get',
                    required:false,
                    onSelect:function(recode){
                        $('#clinic_item').datagrid('getSelected').expand1 = recode.value;
                    }
                }
            }},
            { field: 'expand5', title: '长期临时',align : "center", width: 60 ,editor:{
                type:'combobox',
                options:{
                    valueField:'value',
                    textField:'label',
                    editable:false,
                    data:longArr,
                    required:false,
                    onChange:function(newV,oldV){
                        //if(newV != oldV)
                        //    change_name_and_vs(newV,'expand5')
                    }
                }
            },formatter:function(value){
                return format(longArr,value);
            }},
            { field: 'inputCode', title: '拼音码',align : "center", width: 60 },
            { field: 'inputCodeWb', title: '五笔码',align : "center", width: 60 },
            { field: 'itemClass', title: '项目分类',align : "center", hidden:true},
            { field: 'itemStatus', title: '', hidden:true},
            { field: 'memo', hidden:true},
            { field: 'orgId', title: '所属组织主键', hidden:true}
        ]],
        toolbar: '#tb',
        onClickCell:onClickCell_clinic,
        onBeforeSelect:function(){
            return endEditing_name() && endEditing_vs();
        }
    });
    function endEditing_clinic(){
        if (currentSelectClinicIndex == undefined){return true}
        if ($('#clinic_item').datagrid('validateRow', currentSelectClinicIndex)){
            $('#clinic_item').datagrid('endEdit', currentSelectClinicIndex);
            return true;
        } else {
            return false;
        }
    }
    function onClickCell_clinic(index, field){
        if(index != currentSelectClinicIndex && currentSelectClinicIndex != undefined){
            if(!endEditing_name() || !validVs()) return
        }
        if (endEditing_clinic()){
            loadNameAndVs(index);
            if($('#item_class').combobox('getText') != '检验' && (field == 'expand1Name' || field == 'expand2Name')){
                $.messager.alert('警告','非检验项目没有标本和类别！','warning')
                return
            }
            $('#clinic_item').datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});
            currentSelectClinicIndex = index;
        }
    }
    function loadNameAndVs(index){
        if(currentSelectClinicIndex != undefined && index != currentSelectClinicIndex){
            var row = $('#clinic_item').datagrid('getRows')[currentSelectClinicIndex];
            var nameRows = $('#clinic_item_name').datagrid('getRows');
            //五个字段是否更新
            var flags = [row.expand1!=nameRows[0].expand1,
                row.expand2!=nameRows[0].expand2,
                row.expand3!=nameRows[0].expand3,
                row.expand4!=nameRows[0].expand4,
                row.expand5!=nameRows[0].expand5];
            //拓展的五个字段是否有更新
            var updateFlag = flags[0] || flags[1] || flags[2] || flags[3] || flags[4]
            for(var i=0;i<nameRows.length;i++){
                if(flags[0]){
                    nameRows[i].expand1 = row.expand1
                }
                if(flags[1]){
                    nameRows[i].expand2 = row.expand2
                }
                if(flags[2]){
                    nameRows[i].expand3 = row.expand3
                }
                if(flags[3]){
                    nameRows[i].expand4 = row.expand4
                }
                if(flags[4]){
                    nameRows[i].expand5 = row.expand5
                }
            }

            if(updateFlag || row.saveNameList){
                row.saveNameList = $('#clinic_item_name').datagrid('getRows');
            } else {
                var names = $('#clinic_item_name').datagrid('getChanges', 'inserted')
                    .concat($('#clinic_item_name').datagrid('getChanges', 'deleted'))
                    .concat($('#clinic_item_name').datagrid('getChanges', 'updated'));
                if(names.length > 0){
                    row.saveNameList = $('#clinic_item_name').datagrid('getRows');
                }
            }
            if(row.saveVsList){
                row.saveVsList = $('#clinic_vs_charge').datagrid('getRows');
            } else {
                var vss = $('#clinic_vs_charge').datagrid('getChanges', 'inserted')
                    .concat($('#clinic_vs_charge').datagrid('getChanges', 'deleted'))
                    .concat($('#clinic_vs_charge').datagrid('getChanges', 'updated'));
                if(vss.length > 0){
                    row.saveVsList = $('#clinic_vs_charge').datagrid('getRows');
                }
            }
        }

        if(index != undefined && index != currentSelectClinicIndex) {
            var row = $('#clinic_item').datagrid('getRows')[index]
            if (row.saveVsList) {
                $('#clinic_vs_charge').datagrid('loadData', row.saveVsList);
            } else if (row.oldVsList) {
                $('#clinic_vs_charge').datagrid('loadData', row.oldVsList);
            } else {
                $.get(base_url + 'findVsList', {
                    itemClass: row.itemClass,
                    itemCode: row.itemCode,
                    orgId: row.orgId
                }, function (res) {
                    $('#clinic_vs_charge').datagrid('loadData', res);
                    row.oldVsList = res;
                })
            }
            if(row.saveNameList){
                $('#clinic_item_name').datagrid('loadData', row.saveNameList);
            } else if(row.oldNameList){
                $('#clinic_item_name').datagrid('loadData', row.oldNameList);
            } else {
                $.get(base_url + 'findNameList', {
                    itemClass: row.itemClass,
                    itemCode: row.itemCode,
                    orgId: row.orgId
                }, function (res) {
                    row.oldNameList = res
                    $('#clinic_item_name').datagrid('loadData', res);
                })
            }
            $('#clinic_vs_charge').datagrid('unselectAll');
            $('#clinic_item_name').datagrid('unselectAll');
            currentSelectNameIndex = undefined;
            currentSelectVsIndex = undefined;
        }
    }

    /***********诊疗项目正别名 ☟ ***********/
    $("#clinic_item_name").datagrid({
        fit : true,
        fitColumns: true, //列自适应宽度
        singleSelect : true,
        border:0,
        columns: [[//显示的列
            {field: 'id', title: '编号', width: 100,hidden:true},
            { field: 'stdIndicator', title: '正名', width:30,align:'center',formatter:function(value){
                if(value == '1')return '正名'
                return '别名'
            }},
            { field: 'itemName', title: '诊疗项目名称及别名',halign:'center',align:'left', width:100,editor:{
                type : 'textbox',
                options:{
                    required:true,
                    missingMessage:'别名不能为空',
                    validType : ['nameIsExisted'],
                    onChange : function(value,oldV){
                        if(value != undefined) {
                            var _temp = makePy(value)
                            if (_temp) {
                                _temp = _temp[0]
                            }
                            $('#clinic_item_name').datagrid('getSelected').inputCode = _temp.toUpperCase()
                        }
                    }
                }
            }},
            { field: 'inputCode', title: '拼音码', width:50,align:'center'},
            { field: 'inputCodeWb', title: '五笔码', width:50,align:'center'}
        ]],
        onClickCell:onClickCell_name,
        onBeforeSelect: function(){
            if (currentSelectNameIndex == undefined){return true}
            return $('#clinic_item_name').datagrid('validateRow', currentSelectNameIndex);
        }
    });
    function endEditing_name(){
        if (currentSelectNameIndex == undefined){return true}
        if ($('#clinic_item_name').datagrid('validateRow', currentSelectNameIndex)){
            $('#clinic_item_name').datagrid('endEdit', currentSelectNameIndex);
            return true;
        } else {
            return false;
        }
    }
    function onClickCell_name(index, field){
        if (endEditing_name()){
            if(index == 0) return
            $('#clinic_item_name').datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});
            currentSelectNameIndex = index;
        }
    }

    /***********诊疗项目对照项目 ☟ ***********/
    $("#clinic_vs_charge").datagrid({
        fit : true,
        fitColumns: true, //列自适应宽度
        singleSelect : true,
        border:0,
        columns: [[//显示的列
            {field: 'id', title: '编号', width: 100,hidden:true},
            { field: 'priceType', title: '类别', width:30,align:"center",editor:{
                type:'combobox',
                options:{
                    valueField:'value',
                    textField:'label',
                    editable:false,
                    data:priceTypeArr,
                    required:true
                    ,missingMessage:'类别必填',
                    onChange:function(newV,oldV){
                        if(oldV && oldV != newV){
                            var row = $('#clinic_vs_charge').datagrid('getSelected')
                            row.chargeItemCode = ''
                            row.chargeItemName = ''
                            row.chargeItemSpec = ''
                            row.amount = ''
                            row.units = ''
                            row.price = ''
                            row.stopDate = ''
                            row.backbillRule = ''
                            row.chargeItemClass = ''
                            row.count = ''

                        }
                    }
                }
            },formatter:function(value,row,index){
                if(value == undefined) {
                    value = row.chargeItemClass == 'A' || row.chargeItemClass == 'B' ? '1' : '2';
                    row.priceType = value;
                }
                return format(priceTypeArr,value);
            }
            },
            { field: 'chargeItemName', title: '名称',halign:'center',align:'left' ,width:80,editor:{
                type:'combogrid',
                options:{
                    panelWidth:383,
                    idField:'itemName',
                    editable:true,
                    textField:'itemName',
                    fitColumns: true,
                    required:true,
                    method:'get',
                    mode:'remote',
                    missingMessage:'名称必填',
                    columns:[[
                        {field:'itemCode',hidden:true},
                        {field:'itemClass',title:'详细类别',width:60,align:"center"},
                        {field:'itemName',title:'项目名称',width:100},
                        {field:'itemSpec',title:'规格',width:60,align:"center"},
                        {field:'price',title:'单价',width:40,align:"center"},
                        {field:'stopDate',title:'停止日期',width:120,align:"center",formatter:function(value){
                            if(value && value.length > 9)
                                return value.substr(0,10)
                            return value
                        }}
                    ]],
                    onSelect:function(index,data){
                        var row = $('#clinic_vs_charge').datagrid('getSelected')
                        row.chargeItemSpec = data.itemSpec
                        row.units = data.units
                        row.price = data.price
                        row.stopDate = data.stopDate
                        row.chargeItemClass = data.itemClass
                        row.chargeItemCode = data.itemCode
                    }
                }
            }},
            { field: 'chargeItemSpec', title: '规格', width:50,align:'center'},
            { field: 'amount', title: '数量', width:20,align:'center',editor : {type : 'numberbox',options:{required:true,min:1,missingMessage:'数量必填',onChange:function(newV){

            }}}},
            { field: 'units', title: '单位', width:20,align:'center'},
            { field: 'price', title: '单价', width:20,align:'center'},
            { field: 'stopDate', title: '停止日期', width:30,align:'center',
                formatter : function(value){
                    if(value && value.length >= 10) return value.substr(0,10)
                    return value
                }
            },
            { field: 'backbillRule', title: '计费规则', width:30,align:'center',editor:{
                type:'combobox',
                options:{
                    valueField:'value',
                    textField:'label',
                    url:'/service/dict/findListByType?type=PRICE_RULES_DICT',
                    method:'get',
                    editable:false,
                    required:true,
                    missingMessage:'计费规则必填'
                }
            }},
            { field: 'chargeItemClass', title: '详细类别', width:30,align:'center',formatter:function(value){
                return format(typeArr,value)
            }},
            { field: 'count', title: '小计', width:30,align:'center',formatter:function(value,row){
                var m = isNaN(row.amount) ? 0 : (+row.amount);
                var n = isNaN(row.price) ? 0 : (+row.price);
                return (m * n).toFixed(3)
            }}
        ]],
        onClickCell:onClickCell_vs,
        onBeforeSelect: function(){
            if (currentSelectVsIndex == undefined) return true;
            return $('#clinic_vs_charge').datagrid('validateRow', currentSelectVsIndex);
        }
    });
    function endEditing_vs(){
        if (currentSelectVsIndex == undefined){return true}
        if ($('#clinic_vs_charge').datagrid('validateRow', currentSelectVsIndex)){
            $('#clinic_vs_charge').datagrid('endEdit', currentSelectVsIndex);
            return true;
        } else {
            return false;
        }
    }
    function onClickCell_vs(index, field){
        if (endEditing_vs()){
            $('#clinic_vs_charge').datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});
            if(field == 'chargeItemName'){
                var editor = $('#clinic_vs_charge').datagrid('getEditor',{index:index,field:'chargeItemName'})
                var type = $('#clinic_vs_charge').datagrid('getSelected').priceType;
                if(type == '1') type = 'A,B'
                else {
                    var t = ''
                    for(var i=0;i<typeArr.length;i++){
                        if(typeArr[i].value != 'A' && typeArr[i].value != 'B'){
                            t += ',' + typeArr[i].value;
                        }
                    }
                    type = t.length > 0 ? t.substr(1) : t
                }
                $(editor.target).combogrid({
                    value: $(editor.target).combogrid('getValue'),
                    url : '/service/price/findListWithLimit?limit=50&orgId='+currentOrgId+'&priceType='+type
                })
                $(editor.target).combogrid('grid').datagrid('reload')
            }
            currentSelectVsIndex = index;
        }
    }
    function validVs (){
        if(!endEditing_vs()) return false;
        var rows = $('#clinic_vs_charge').datagrid('getRows');
        for(var i=0;i<rows.length;i++){
            var row = rows[i]
            if(row.chargeItemName == undefined || row.chargeItemName == ''){
                onClickCell_vs(i,'chargeItemName');
                return false;
            }
            if(row.amount == undefined || row.amount == ''){
                onClickCell_vs(i,'amount');
                return false;
            }
            if(row.backbillRule == undefined || row.backbillRule == ''){
                onClickCell_vs(i,'backbillRule');
                return false;
            }
        }
        return true;
    }

    // 新增临床诊疗项目
    $('#add_pro_win').window({
        title : '新增临床诊疗项目',
        width : '550',
        height : '450',
        resizable : false
    })
    $('#add_pro_win').window('close')
    $("#dept_grid").datagrid({
        iconCls: 'icon-save', //图标
        fit : true,
        singleSelect : true,
        columns: [[//显示的列
            {field:'itemCode',title:'项目编码',width:100,align:"center"},
            {field:'itemName',title:'项目名称',width:200},
            {field:'itemSpec',title:'规格',width:73,align:"center"},
            {field:'price',title:'单价',width:40,align:"center"},
            {field:'stopDate',title:'停止日期',width:100,align:"center",formatter:function(value){
                if(value && value.length > 9)
                    return value.substr(0,10)
                return value
            }}
        ]],
        onClickRow : function(index,rowData){
            $('#item_code').textbox('setValue',rowData.itemCode)
            $('#item_name').textbox('setValue',rowData.itemName)
        }
    });
    $(':radio[name="org_type"]').click(function(){
        if(this.value == '0')
            $('#clinic_org').combobox({
                disabled : true
            })
        else if(this.value == '1')
            $('#clinic_org').combobox({
                disabled : false
            })
    })
    $('#clinic_org').combobox({
        mode:'remote',
        url:'/service/dept-dict/findListWithFilter?orgId='+currentOrgId,
        method:'get'
    })
    $('#add_project').click(function(){
        if(!endEditing_name() || !validVs()) return false;
        $.get('/service/price/findListWithLimit',{itemClass:$('#item_class').combobox('getValue'),
                orgId:currentOrgId,start:'0',limit:'200'},
            function(res){
                $('#dept_grid').datagrid('loadData',res)
            }
        )
        $('#clinic_org').combobox({
            disabled : true
        })
        $('#add_pro_win').window('open')
        $('#pro_form input:radio[value="0"]').prop('checked',true)
        $('#clinic_org').combobox('setValue','')
        $('#item_code').textbox('setValue','')
        $('#item_name').textbox('setValue','')
    });
    $('#yes_pro_form').click(function(){
        if(!$('#item_code').textbox('isValid') || !$('#item_name').textbox('isValid'))return
        var inputCode = makePy($('#item_name').textbox('getValue'))
        var new_name_json = [{'stdIndicator':'1'
            ,'itemName':$('#item_name').textbox('getValue')
            ,'itemCode':$('#item_code').textbox('getValue')
            ,'itemClass':$('#item_class').combobox('getValue')
            ,'inputCode': (inputCode.length > 0 ? inputCode[0].toUpperCase() : $('#item_name').textbox('getValue'))
            ,'expand3': $(':radio[name="org_type"][value="1"]').prop('checked') ? $('#clinic_org').combobox('getValue') : ''
            ,'orgId' : currentOrgId
            ,'inputCodeWb': ''}]
        var pro_obj = {'itemClass':$('#item_class').combobox('getValue')
            ,'itemCode':$('#item_code').textbox('getValue')
            ,'itemName':$('#item_name').textbox('getValue')
            ,'inputCode': (inputCode.length > 0 ? inputCode[0].toUpperCase() : $('#item_name').textbox('getValue'))
            ,'inputCodeWb': ''
            ,'expand3': $(':radio[name="org_type"][value="1"]').prop('checked') ? $('#clinic_org').combobox('getValue') : ''
            ,'orgId' : currentOrgId
            ,'saveNameList':new_name_json
        }
        $("#clinic_item").datagrid('appendRow',pro_obj);
        currentSelectClinicIndex = $("#clinic_item").datagrid('getRows').length-1;
        $("#clinic_item").datagrid('selectRow',currentSelectClinicIndex);

        $("#clinic_item_name").datagrid('loadData',new_name_json);
        $("#clinic_vs_charge").datagrid('loadData',[]);
        currentSelectNameIndex = undefined;
        currentSelectVsIndex = undefined;
        $('#add_pro_win').window('close');
    })
    $('#cancle_pro_form').click(function(){
        $('#add_pro_win').window('close')
    })

    $('#del_project').click(function(){
        if(currentSelectClinicIndex != undefined){
            $('#clinic_item').datagrid('deleteRow',currentSelectClinicIndex);
            currentSelectClinicIndex = undefined;
            currentSelectNameIndex = undefined;
            currentSelectVsIndex = undefined;
            $('#clinic_item_name').datagrid('loadData',[])
            $('#clinic_vs_charge').datagrid('loadData',[])
        } else {
            $.messager.alert('警告','请选择一条删除数据！','error');
        }
    });
    $('#add_alias').click(function(){
        if(currentSelectClinicIndex != undefined){
            if(endEditing_name()) {
                var row = $("#clinic_item").datagrid('getSelected')
                var new_name_json = {
                    'stdIndicator': '2'
                    , 'itemCode': row.itemCode
                    , 'itemClass': row.itemClass
                    , 'expand1': row.expand1
                    , 'expand2': row.expand2
                    , 'expand3': row.expand3
                    , 'expand4': row.expand4
                    , 'expand5': row.expand5
                    , 'orgId': row.orgId
                }
                $('#clinic_item_name').datagrid('appendRow', new_name_json);
                currentSelectNameIndex = $('#clinic_item_name').datagrid('getRows').length - 1;
                onClickCell_name(currentSelectNameIndex, 'itemName')
            }
        } else {
            $.messager.alert('警告','请先选择需要添加别名的诊疗项目!','error')
        }
    });
    $('#del_alias').click( function(){
        if(!currentSelectNameIndex){
            $.messager.alert('警告','请选择需要删除的别名！','error')
        } else {
            $("#clinic_item_name").datagrid('deleteRow',currentSelectNameIndex);
            currentSelectNameIndex = undefined;
        }
    });
    $('#add_vs').click( function(){
        var row = $("#clinic_item").datagrid('getSelected')
        if(row){
            if(endEditing_vs()) {
                var vs_json = {
                    'priceType': '1',
                    'clinicItemClass': row.itemClass,
                    'clinicItemCode': row.itemCode,
                    'chargeItemNo': '1',
                    'orgId': currentOrgId
                }
                $('#clinic_vs_charge').datagrid('appendRow', vs_json);
                currentSelectVsIndex = $('#clinic_vs_charge').datagrid('getRows').length - 1;
                onClickCell_vs(currentSelectVsIndex,'chargeItemName')
            }
        } else {
            $.messager.alert('警告','请先选择需要添加对照的诊疗项目!','error')
        }
    });
    $('#del_vs').click(function(){
        if(currentSelectVsIndex == undefined){
            $.messager.alert('警告','请选择需要删除的对照项目！','error')
        } else {
            $("#clinic_vs_charge").datagrid('deleteRow',currentSelectVsIndex);
            currentSelectVsIndex = undefined;
        }
    });
    $('#reload_html').click( function(){
        window.location.reload();
    });
    $('#save_data').click( function(){
        endEditing_clinic();
        if(!endEditing_vs() || !endEditing_name() || !validVs()) return
        loadNameAndVs();
        // 传输修改的参数
        var clinicItem = $('#clinic_item').datagrid('getChanges','updated');

        var rows = $('#clinic_item').datagrid('getRows');
        var updateNameOrVs = []
        for(var i= 0,j=rows.length;i<j;i++) {
            var row = rows[i];
            if (row.id && ((row.saveNameList && row.saveNameList.length > 0)
                || (row.saveVsList && row.saveVsList.length > 0))) {
                for (var m = 0,n=clinicItem.length; m < n; m++) {
                    if (rows[i].id == clinicItem[m].id){
                        break;
                    }
                }
                if(m == 0 || m == n-1){
                    updateNameOrVs.push({
                        id : row.id,
                        saveNameList:row.saveNameList,
                        saveVsList:row.saveVsList
                    })
                }
            }
        }

        clinicItem = clinicItem.concat(updateNameOrVs).concat($('#clinic_item').datagrid('getChanges','inserted'));
        for(var i=0;i<clinicItem.length;i++){
            delete clinicItem[i].oldNameList;
            delete clinicItem[i].oldVsList;
        }
        //删除
        var dels = $('#clinic_item').datagrid('getChanges','deleted')
        var ids = ''
        for(var i=0;i<dels.length;i++){
            var id = dels[i].id
            ids += ','+id
        }
        //诊疗项目的删除信息
        if(ids.length > 0)
            clinicItem.push({'id':ids.substr(1),'delFlag':'1'});
        if(clinicItem.length == 0){
            $.messager.alert('保存','没有需要保存的数据！','info');
            return false
        }
        parent.$.postJSON('/service/clinicItem/save',JSON.stringify(clinicItem),function(res){
            if(res = '1')
                $.messager.alert('保存','保存成功!','info',function(){
                    window.location.reload()
                })
        })
    });
    $('#close_html').click( function(){
        parent.location.href = parent.getRootPath() + '/modules/index.html';
    });

    // 加载诊疗项目数据
    var load_data = function (){
        var itemClass = $('#item_class').combobox('getValue') ? $('#item_class').combobox('getValue') : 'A'
        var params = {'itemClass':itemClass}
        params.itemCode = $(':radio[name="adminFlag"][value="0"]').prop('checked') ? $('#code_filter').textbox('getValue').toUpperCase() : ''
        params.inputCode = $(':radio[name="adminFlag"][value="1"]').prop('checked') ? $('#code_filter').textbox('getValue').toUpperCase() : ''
        params.orgId = currentOrgId
        $.get('/service/clinicItem/findList',params,function(res){
            $('#clinic_item').datagrid('loadData',res)
            $('#clinic_item').datagrid('uncheckAll')
            $('#clinic_item_name').datagrid('loadData',[])
            $('#clinic_vs_charge').datagrid('loadData',[])
        })
    }

    /**
     * 通过数组中数据格式化value
     * @param arr 格式为[{'value':'value','label':'label'}....]
     * @param value
     * @returns {*}
     */
    function format(arr,value){
        if(arr){
            for(var i= 0,j=arr.length;i<j;i++){
                if(arr[i].value == value) return arr[i].label
            }
        }
        return value
    }

    load_data()
})