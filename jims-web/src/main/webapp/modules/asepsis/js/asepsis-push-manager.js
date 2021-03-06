$("<script>").attr({type: "application/javascript", src: "/static/easyui/locale/easyui-lang-zh_CN.js"}).appendTo("head");
$(function () {
    var currentOrgId = config.org_Id;
    var currentUsername = config.userName;
    var currentSelectIndex = undefined;
    var supplyRoomCode = '161303';  // 供应室编码

    $('#exchangeStart').datebox({
        height:'25',
        value: parent.formatDatebox(new Date())
    })
    $('#exchangeEnd').datebox({
        height:'25',
        value: parent.formatDatebox(new Date())
    })
    $('#staff').combobox({
        valueField: 'name',
        textField: 'name',
        url: parent.basePath + '/orgStaff/findList?orgId='+currentOrgId,
        method: 'get',
        height:'25',
        onSelect: function(){

        }
    })
    $('#staff').combobox('panel').panel({
        onBeforeClose: function(){
            if($('#staff').combogrid('getValue') == ''){
                return
            }
            if($('.combobox-item-selected', this).length == 0) {
                if ($('.combobox-item[style="display: block;"]', this).length == 0) {
                    $('#staff').combobox('setValue', '');
                } else {
                    $('.combobox-item[style="display: block;"]:eq(0)', this).click()
                }
            }
        }
    })
    $('#dept').combogrid({
        width:'315',
        height: '25',
        idField: 'deptCode',
        textField: 'deptName',
        url: parent.basePath + '/dept-dict/findListWithFilter?orgId='+currentOrgId,
        method: 'get',
        mode: 'remote',
        columns: [[
            {field: 'deptCode', title: '代码', width: 70, align: "center"},
            {field: 'deptName', title: '名称', width: 170, halign: "center", align: "left" },
            {field: 'inputCode', title: '拼音码', width: 70, align: "center"}
        ]],
        onSelect: function(){
            $('#dept').combo('panel').attr('selectedIndex', $('#dept').combogrid('grid').datagrid('getRowIndex', $('#dept').combogrid('grid').datagrid('getSelected')))
            loadData()
        }
    })
    $('#dept').combo('panel').panel({
        onBeforeClose: function(){
            if($('#dept').combogrid('getValue') == ''){
                return
            }
            var temp = $(this).attr('selectedIndex')
            var index = 0
            if ($('#dept').combogrid('grid').datagrid('getRows').length == 0) {
                $('#dept').combogrid('setValue', '');
                index = undefined;
            } else if ($('#dept').combogrid('grid').datagrid('getSelected')) {
                index = $('#dept').combogrid('grid').datagrid('getRowIndex', $('#dept').combogrid('grid').datagrid('getSelected'))
            }
            if (index != undefined && temp != index) {
                $(this).attr('selectedIndex', index)
                $('.datagrid-row:eq(' + index + ')', this).click();
            } else if(temp == index){
                $(this).removeAttr('selectedIndex')
            }
        }
    })

    $('#tabs').tabs({
        fit:true,
        justified:true,
        onSelect: function(title,index){
            switch (index){
                case 0 :
                    currentSelectIndex = undefined;
                    loadData()
                    $('#text1').html('借物日期：');
                    $('#text2').html('还物人：');
                    $('#text3').html('还物科室：');
                    $('#exchangeEnd').datebox('enable');
                    $('#addBtn').css('display','none');
                    $('#delBtn').css('display','none');
                    $('#printBtn').css('display','none');
                    $('#queryBtn').css('display','inline-block');
                    break;
                case 1 :
                    currentSelectIndex = undefined;
                    $('#poshManager').datagrid('loadData',[])
                    $('#text1').html('送物日期：');
                    $('#text2').html('送物人：');
                    $('#text3').html('送物科室：');
                    $('#exchangeEnd').datebox('enable');
                    $('#addBtn').css('display','inline-block');
                    $('#delBtn').css('display','inline-block');
                    $('#queryBtn').css('display','none');
                    $('#exchangeEnd').datebox('disable');
                    break;
                case 2 :
                    currentSelectIndex = undefined;
                    $('#tradeManager').datagrid('loadData',[])
                    $('#text1').html('对换日期：');
                    $('#text2').html('对换人：');
                    $('#text3').html('对换科室：');
                    $('#exchangeEnd').datebox('enable');
                    $('#addBtn').css('display','inline-block');
                    $('#delBtn').css('display','inline-block');
                    $('#queryBtn').css('display','none');
                    $('#exchangeEnd').datebox('disable');
                    break;
            }
        },
        onUnselect: function(title,index){
            save(title,index)
        }
    })
    /**
     * 校验通过则结束编辑
     * @returns {boolean}
     */
    var endEditing = function(id){
        if (currentSelectIndex == undefined){return true}
        var editor = $('#'+id).datagrid('getEditor',{index:currentSelectIndex,field:'itemName'})
        if(editor && $(editor.target).combogrid('getValue')){
            var rows = $(editor.target).combogrid('grid').datagrid('getRows');
            if(rows.length > 0){
                if(!$(editor.target).combogrid('grid').datagrid('getSelected')){
                    $(editor.target).combogrid('grid').datagrid('selectRow',0)
                }
            } else {
                $(editor.target).combogrid('setValue','')
            }
        }
        if ($('#'+id).datagrid('validateRow', currentSelectIndex)){
            $('#'+id).datagrid('endEdit', currentSelectIndex);
            return true;
        } else {
            return false;
        }
    }
    /**
     * 点击datagrid单元格事件
     * @param index
     * @param field
     */
    var onClickCell = function(index, field){
        var tabIndex = $('#tabs').tabs('getTabIndex',$('#tabs').tabs('getSelected'));
        var datagridId ;
        if(tabIndex == 0){
            datagridId = 'sendManager'
        } else if(tabIndex == 1) {
            datagridId = 'poshManager'
        } else if(tabIndex == 2) {
            datagridId = 'tradeManager'
        }
        if (endEditing(datagridId)){
            $('#'+datagridId).datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});
            if(field == 'itemName'){
                var editor = $('#'+datagridId).datagrid('getEditor',{index:index,field:'itemName'})
                if(editor){
                    var v = $(editor.target).combogrid('getValue');
                    $(editor.target).combogrid({
                        url:parent.basePath + '/asepsisDict/findPage?orgId='+currentOrgId+'&deptCode='+$('#dept').combogrid('getValue')
                        ,value:v
                    })
                }
            }
            currentSelectIndex = index;
        }
    }

    $('#sendManager').datagrid({
        fit : true,
        border:1,
        fitColumns: true,
        singleSelect : true,
        remoteSort: false,
        idField :'id',
        columns:[[      //每个列具体内容
            {field:'id',title:'还物',width:'40',align:'center',formatter:function(value,row){
                return '<input id=' + value +' type="checkbox"  name="pb" ' + (row.sendAmount == 0 ? 'disabled="disabled"' : '')  + '>'
            }}
            ,{field:'toDeptName',title:'借物科室',width:'100',align:'center'}
            ,{field:'documentNo',title:'单据号',width:'80',align:'center'}
            ,{field:'itemNo',title:'序号',width:'50',align:'center'}
            ,{field:'itemCode',title:'代码',width:'80',align:'center'}
            ,{field:'itemName',title:'名称',width:'150',align:'center'}
            ,{field:'itemSpec',title:'规格',width:'50',align:'center'}
            ,{field:'units',title:'单位',width:'50',align:'center'}
            ,{field:'lendAmount',title:'数量',width:'50',align:'center'}
            ,{field:'returnAmount',title:'已还数量',width:'60',align:'center',formatter:function(value){
                return value ? value : 0;
            }}
            ,{field:'sendAmount',title:'还物数量',width:'60',align:'center',editor:{
                type : 'numberbox',
                options:{
                    precision : 0
                }
            },formatter: function(value,row ){
                var max = (isNaN(row.lendAmount) ? 0 : +row.lendAmount) - (isNaN(row.returnAmount) ? 0 : +row.returnAmount)
                row.sendAmount = (isNaN(value) || value > max )? max : value
                return row.sendAmount
            }}
            ,{field:'lendDate',title:'借出日期',width:'80',align:'center',formatter: function(value){
                return parent.formatDatebox(value)
            }}
            ,{field:'lender',title:'借物人',width:'60',align:'center'}
            ,{field:'memos',title:'备注',width:'70',align:'center'}
        ]],
        onClickCell: function(index,field){
            for(var i= 0,j=$(this).datagrid('getRows').length;i<j;i++) {
                var c = $(':checkbox[name="pb"]')[i].checked
                $(this).datagrid('endEdit', i)
                $(':checkbox[name="pb"]')[i].checked = c
            }
            if(field == 'sendAmount'){
                $(this).datagrid('beginEdit',index)
            }
        },
        onBeforeSelect: function(index,row){
            return false;
        }
    });

    $('#poshManager').datagrid({
        fit : true,
        border:1,
        fitColumns: true,
        singleSelect : true,
        remoteSort: false,
        idField :'id',
        columns:[[      //每个列具体内容
            {field:'fromDept',title:'送物科室',width:'100',align:'center',formatter:function(value){
                var d = $('#dept').combogrid('grid').datagrid('getSelected');
                if(d){
                    return d.deptName
                }
                return ''
            }}
            ,{field:'itemNo',title:'序号',width:'50',align:'center'}
            ,{field:'itemCode',title:'包代码',width:'80',align:'center'}
            ,{field:'itemName',title:'包名称',width:'150',align:'center',editor:{
                type:'combogrid',
                options:{
                    panelWidth:463,
                    idField:'asepsisName',
                    textField:'asepsisName',
                    required:true,
                    missingMessage:'不能为空',
                    fitColumns: true,
                    url : parent.basePath + '/asepsisDict/findPage',
                    method:'get',
                    mode:'remote',
                    columns:[[
                        {field:'asepsisCode',title:'包代码',width:100,align : "center"},
                        {field:'asepsisName',title:'包名称',width:160,halign : "center",align : "left" },
                        {field:'inputCode',title:'拼音码',width:70,align : "center"}
                    ]],onSelect:function(index,row){
                        var flag;
                        var rows = $('#poshManager').datagrid('getRows')
                        for(var i= 0,j = rows.length;i < j;i++){
                            if(i != currentSelectIndex && rows[i].itemCode == row.asepsisCode){
                                flag = true;
                                break;
                            }
                        }
                        if(flag) {
                            $.messager.alert('提示','列表中已添加该包！','warning')
                            var editor = $('#poshManager').datagrid('getEditor',{index:currentSelectIndex,field:'itemName'})
                            $(editor.target).combogrid('setValue','')
                            return
                        }
                        var send = $('#poshManager').datagrid('getSelected')
                        send.itemCode = row.asepsisCode;
                        send.itemSpec = row.asepsisSpec;
                        send.units = row.units;
                        send.antiFee = row.antiPrice;
                        send.nobackFee = row.nobackPrice;
                        //结束语句报错，没发现原先，不影响结果
                        $('#poshManager').datagrid('endEdit',currentSelectIndex)
                    }
                }
            }}
            ,{field:'itemSpec',title:'包规格',width:'50',align:'center'}
            ,{field:'sendAmount',title:'送物数量',width:'80',align:'center',editor:{
                type : 'numberbox',
                options:{
                    required:true,
                    missingMessage:'数量不能为空',
                    min : 1,
                    precision : 0
                }
            }}
            ,{field:'units',title:'单位',width:'50',align:'center'}
            ,{field:'antiFee',title:'消毒费',width:'60',align:'center'}
            ,{field:'antiFeeSum',title:'消毒费合计',width:'80',align:'center',formatter:function(value,row){
                var n = isNaN(row.sendAmount) ? 0 : (+row.sendAmount);
                var f = isNaN(row.antiFee) ? 0 : (+row.antiFee);
                row.antiFeeSum = (n * f).toFixed(3)
                return row.antiFeeSum
            }}
            ,{field:'nobackFee',title:'辅料费',width:'60',align:'center'}
            ,{field:'sender',title:'送物人',width:'70',align:'center'}
            ,{field:'memos',title:'备注',width:'70',align:'center',editor:'textbox'}
        ]],
        onClickCell:onClickCell,
        onBeforeSelect: function(index){
            return $('#poshManager').datagrid('validateRow', currentSelectIndex)
        }
    });

    $('#tradeManager').datagrid({
        fit : true,
        border:1,
        fitColumns: true,
        singleSelect : true,
        remoteSort: false,
        idField :'id',
        columns:[[      //每个列具体内容
            {field:'itemNo',title:'序号',width:'50',align:'center'}
            ,{field:'itemCode',title:'编码',width:'80',align:'center'}
            ,{field:'itemName',title:'名称',width:'150',align:'center',editor:{
                type:'combogrid',
                options:{
                    panelWidth:463,
                    idField:'asepsisName',
                    textField:'asepsisName',
                    required:true,
                    missingMessage:'不能为空',
                    fitColumns: true,
                    url : parent.basePath + '/asepsisDict/findPage',
                    method:'get',
                    mode:'remote',
                    columns:[[
                        {field:'asepsisCode',title:'包代码',width:100,align : "center"},
                        {field:'asepsisName',title:'包名称',width:160,halign : "center",align : "left" },
                        {field:'inputCode',title:'拼音码',width:70,align : "center"}
                    ]],onClickRow:function(index,row){
                        var flag;
                        var rows = $('#tradeManager').datagrid('getRows')
                        for(var i= 0,j = rows.length;i < j;i++){
                            if(i != currentSelectIndex && rows[i].itemCode == row.asepsisCode){
                                flag = true;
                                break;
                            }
                        }
                        if(flag) {
                            $.messager.alert('提示','列表中已添加该包！','warning')
                            var editor = $('#tradeManager').datagrid('getEditor',{index:currentSelectIndex,field:'itemName'})
                            $(editor.target).combogrid('setValue','')
                            return
                        }
                        var send = $('#tradeManager').datagrid('getSelected')
                        send.itemCode = row.asepsisCode;
                        send.itemSpec = row.asepsisSpec;
                        send.units = row.units;
                        send.antiFee = row.antiPrice;
                        send.nobackFee = row.nobackPrice;
                        $('#tradeManager').datagrid('endEdit',currentSelectIndex)
                    }
                }
            }}
            ,{field:'itemSpec',title:'规格',width:'50',align:'center'}
            ,{field:'returnAmount',title:'数量',width:'50',align:'center',editor:{
                type : 'numberbox',
                options:{
                    required:true,
                    missingMessage:'数量不能为空',
                    min : 1,
                    precision : 0
                }
            },formatter: function(value,row){
                row.stock = value;
                return value
            }}
            ,{field:'units',title:'单位',width:'50',align:'center'}
            ,{field:'antiFee',title:'消毒费',width:'60',align:'center'}
            ,{field:'antiFeeSum',title:'消毒费合计',width:'80',align:'center',formatter:function(value,row){
                var n = isNaN(row.returnAmount) ? 0 : (+row.returnAmount);
                var f = isNaN(row.antiFee) ? 0 : (+row.antiFee);
                row.antiFeeSum = (n * f).toFixed(3)
                return row.antiFeeSum
            }}
            ,{field:'nobackFee',title:'辅料费',width:'60',align:'center'}
            ,{field:'antiDate',title:'消毒日期',width:'80',align:'center'}
            ,{field:'returnMan',title:'对换人',width:'60',align:'center'}
            ,{field:'memos',title:'说明',width:'70',align:'center',editor:'textbox'}
            ,{field:'documentNo',title:'单据号',width:'80',align:'center'}
        ]],
        onClickCell:onClickCell,
        onBeforeSelect: function(index){
            return $('#tradeManager').datagrid('validateRow', currentSelectIndex)
        }
    });

    $('#addBtn').click(function(){
        var index = $('#tabs').tabs('getTabIndex',$('#tabs').tabs('getSelected'));
        if(index == 1){
            if(!$('#staff').textbox('getValue')){
                $.messager.alert("提示","请先选择送物人")
                return false
            }
            if(!$('#dept').combogrid('grid').datagrid('getSelected')){
                $.messager.alert("提示","请先选择送物科室")
                return false
            }
            if(!$('#poshManager').datagrid('validateRow', currentSelectIndex)) return
            var prefix = 'S'+parent.formatDatebox(new Date()).replace(/-/g,'').substr(2);
            var suffix;
            var rows = $('#poshManager').datagrid('getRows');
            if(rows.length > 0){
                var temp = rows[rows.length-1].documentNo;
                suffix = +temp.substr(7) + 1;
            } else {
                $.ajax({
                    type: 'get',
                    url: parent.basePath + '/asepsisSendRec/getMaxSuffix',
                    async : false,   // true 异步,false 同步
                    data: {orgId:currentOrgId,prefix:prefix},
                    contentType: 'application/json',
                    success: function(res){
                        if(res){
                            suffix = + res + 1;
                        } else {
                            suffix = 1;
                        }
                    }
                })
            }
            var row = {
                documentNo: prefix + (suffix>9999 ? suffix : ('000'+suffix).substr(-4)),
                itemNo: rows.length+1,
                sender: $('#staff').combobox('getValue'),
                orgId: currentOrgId,
                sendDate: new Date(),
                getFlag:'1',
                fromDept: $('#dept').combogrid('getValue'),
                reqDate: new Date(),
                reqOperator: $('#staff').combobox('getValue'),
                operator: currentUsername,
                sendAmount:'1'
            }
            $('#poshManager').datagrid('appendRow',row)
            onClickCell(rows.length-1 ,'itemName')
        } else if(index == 2){
            if(!$('#staff').textbox('getValue')){
                $.messager.alert("提示","请先选择对换人")
                return false
            }
            if(!$('#dept').combogrid('grid').datagrid('getSelected')){
                $.messager.alert("提示","请先选择对换科室")
                return false
            }
            if(!$('#tradeManager').datagrid('validateRow', currentSelectIndex)) return
            var prefix = 'T'+parent.formatDatebox(new Date()).replace(/-/g,'').substr(2);
            var suffix;
            var rows = $('#tradeManager').datagrid('getRows');
            if(rows.length > 0){
                var temp = rows[rows.length-1].documentNo;
                suffix = +temp.substr(7) + 1;
            } else {
                $.ajax({
                    type: 'get',
                    url: parent.basePath + '/asepsisLendRec/getMaxSuffix',
                    async : false,   // true 异步,false 同步
                    data: {orgId:currentOrgId,prefix:prefix},
                    contentType: 'application/json',
                    success: function(res){
                        if(res){
                            suffix = + res + 1;
                        } else {
                            suffix = 1;
                        }
                    }
                })
            }
            var row = {
                orgId: currentOrgId,
                documentNo: prefix + (suffix>9999 ? suffix : ('000'+suffix).substr(-4)),
                expDocumentNo: prefix + (suffix>9999 ? suffix : ('000'+suffix).substr(-4)),
                itemNo: rows.length + 1,
                returnDate: new Date(),
                returnMan: $('#staff').combobox('getValue'),
                reqOperator: $('#staff').combobox('getValue'),
                toDept: $('#dept').combogrid('getValue'),
                reqDate: new Date(),
                operator: currentUsername,
                returnAmount:'1',
                returnFlag:'4'
            }
            $('#tradeManager').datagrid('appendRow',row)
            onClickCell(rows.length-1 ,'itemName')
        }

    })
    $('#delBtn').click(function(){
        if(currentSelectIndex == undefined){
            $.messager.alert("提示","请先选择要删除的数据！",'info');
            return false;
        }
        var tabIndex = $('#tabs').tabs('getTabIndex',$('#tabs').tabs('getSelected'));
        var datagridId ;
        if(tabIndex == 1) {
            datagridId = 'poshManager'
        } else if(tabIndex == 2) {
            datagridId = 'tradeManager'
        }
        $('#'+datagridId).datagrid('deleteRow',currentSelectIndex);
        var rows = $('#'+datagridId).datagrid('getRows');
        for(var len = rows.length;currentSelectIndex<len;currentSelectIndex++){
            rows[currentSelectIndex].itemNo = currentSelectIndex + 1
            $('#'+datagridId).datagrid('refreshRow', currentSelectIndex)
        }
        currentSelectIndex = undefined;
    })
    $('#saveBtn').click(function(){
        save()
    })
    $('#closeBtn').click(function(){

    })
    $('#clearBtn').click(function(){
        $('#exchangeStart').datebox('setValue',parent.formatDatebox(new Date()))
        $('#exchangeEnd').datebox('setValue',parent.formatDatebox(new Date()))
        $('#staff').combobox('setValue','')
        $('#dept').combogrid('setValue','')
        var tabIndex = $('#tabs').tabs('getTabIndex',$('#tabs').tabs('getSelected'));
        if(tabIndex == 0){
            $('#sendManager').datagrid('loadData',[])
        } else if(tabIndex == 1) {
            $('#poshManager').datagrid('loadData',[])
        } else if(tabIndex == 2) {
            $('#tradeManager').datagrid('loadData',[])
        }
    })
    $('#queryBtn').click(function(){
        loadData()
    })

    function loadData(){
        var row = $('#dept').combogrid('grid').datagrid('getSelected')
        var params = {
            orgId: currentOrgId,
            lendDateStart: parent.parseToDate($('#exchangeStart').datebox('getValue')+' 00:00:00'),
            lendDateEnd: parent.parseToDate($('#exchangeEnd').datebox('getValue')+' 23:59:59'),
            toDept: row ? row.deptCode : '',
            returnFlag: '1,2'
        }
        $.get(parent.basePath + '/asepsisLendRec/findList',params,function(res){
            $('#sendManager').datagrid('loadData',res)
        })
    }

    function save(title,tabIndex){
        var index = tabIndex == undefined ? $('#tabs').tabs('getTabIndex',$('#tabs').tabs('getSelected')) : tabIndex;
        var url;
        var saveRows = []
        if(index == 0) {
            if(tabIndex == undefined) {
                if ($('#staff').combobox('getValue') == '') {
                    $.messager.alert('提示', '请选择还物人！', 'warning');
                    return false;
                }
                if ($('#dept').combobox('getValue') == '') {
                    $.messager.alert('提示', '请选择还物科室！', 'warning');
                    return false;
                }
            }
            var rows = $('#sendManager').datagrid('getRows');
            $(':checkbox[name="pb"]').each(function(index){
                $('#sendManager').datagrid('endEdit', index)
                if($(this).prop('checked') && rows[index].sendAmount){
                    var row = rows[index];
                    row.stock = row.sendAmount;
                    row.returnAmount = isNaN(row.returnAmount) ? row.sendAmount : +row.returnAmount + +row.sendAmount;
                    if(row.returnAmount == row.lendAmount){
                        row.returnFlag = '3';
                    } else {
                        row.returnFlag = '2';
                    }
                    if(!row.returnMan) row.returnMan = $('#staff').combobox('getValue');
                    else {
                        if((','+row.returnMan+',').indexOf(','+$('#staff').combobox('getValue')+',') == -1){
                            row.returnMan += ',' + $('#staff').combobox('getValue')
                        }
                    }
                    row.returnDate = new Date()
                    row.belongDept = supplyRoomCode
                    delete row.sendAmount;
                    saveRows.push(row);
                }
            })
            url = parent.basePath + '/asepsisLendRec/saveList';
        } else if(index == 1){
            if(!endEditing('poshManager')) return
            saveRows = $('#poshManager').datagrid('getRows');
            url = parent.basePath + '/asepsisSendRec/saveList';
        } else if(index == 2){
            if(!endEditing('tradeManager')) return
            saveRows = $('#tradeManager').datagrid('getRows');
            url = parent.basePath + '/asepsisLendRec/saveList';
        }
        if(saveRows.length == 0){
            if(tabIndex == undefined) {
                $.messager.alert('提示', '没有要保存的数据！', 'warning');
            }
            return true;
        }
        if(tabIndex != undefined) {
            $.messager.confirm("操作", "您是否要保存" + title +"数据？", function (data) {
                if (data) {
                    parent.$.postJSON(url, JSON.stringify(saveRows), function (res) {
                        if (res == '1') {
                            $.messager.alert('保存', '保存成功！', 'info')
                        } else {
                            $.messager.alert('保存', '保存失败！', 'error');
                        }
                    })
                }
            });
        } else {
            parent.$.postJSON(url, JSON.stringify(saveRows), function (res) {
                if (res == '1') {
                    $.messager.alert('保存', '保存成功！', 'info', function () {
                        window.location.reload()
                    })
                } else {
                    $.messager.alert('保存', '保存失败！', 'error');
                }
            })
        }
    }
});




