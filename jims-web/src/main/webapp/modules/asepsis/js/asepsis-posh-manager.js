/**
 * 还物送物
 * @author yangruidong
 * @version 2016-06-25
 */
$(function () {
    var currentOrgId = '1';
    var currentUsername = '测试员';
    var currentSelectIndex = undefined;

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
        ]]
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
        }
    })
    /**
     * 校验通过则结束编辑
     * @returns {boolean}
     */
    var endEditing = function(id){
        if (currentSelectIndex == undefined){return true}
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
            {field:'toDept',title:'借物科室',width:'100',align:'center'}
            ,{field:'documentNo',title:'单据号',width:'80',align:'center'}
            ,{field:'itemNo',title:'序号',width:'50',align:'center'}
            ,{field:'itemCode',title:'代码',width:'80',align:'center'}
            ,{field:'itemName',title:'名称',width:'150',align:'center'}
            ,{field:'itemSpec',title:'规格',width:'50',align:'center'}
            ,{field:'units',title:'单位',width:'50',align:'center'}
            ,{field:'lendAmount',title:'数量',width:'50',align:'center'}
            ,{field:'returnAmount',title:'已还数量',width:'50',align:'center'}
            ,{field:'sendAmount',title:'还物数量',width:'50',align:'center',editor:{
                type : 'numberbox',
                options:{
                    precision : 0
                }
            },formatter: function(value,row ){
                var max = (isNaN(row.lendAmount) ? 0 : +row.lendAmount) - (isNaN(row.returnAmount) ? 0 : +row.returnAmount)
                var v = isNaN(value) ? 0 : value
                row.sendAmount = max > v ? v : max
                return row.sendAmount
            }}
            ,{field:'lendDate',title:'借出日期',width:'80',align:'center',formatter: function(value){
                return parent.formatDatebox(value)
            }}
            ,{field:'lender',title:'借物人',width:'60',align:'center'}
            ,{field:'memos',title:'备注',width:'70',align:'center'}
        ]],
        onClickCell:onClickCell
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
                    ]],onClickRow:function(index,row){
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
        onClickCell:onClickCell
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
        onClickCell:onClickCell
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

            var prefix = 'S'+parent.formatDatebox(new Date()).replace(/-/g,'').substr(2);
            var suffix;
            var rows = $('#poshManager').datagrid('getRows');
            if(rows.length > 0){
                var temp = rows[rows.length-1].documentNo;
                suffix = +temp.substr(7) + 1;
            } else {
                $.ajax({
                    type: 'get',
                    url: parent.basePath + '/asepsisSendRec/getMaxDocumentNo',
                    async : false,   // true 异步,false 同步
                    data: {orgId:currentOrgId},
                    contentType: 'application/json',
                    success: function(res){
                        if(res){
                            suffix = +(res.toString().substr(7)) + 1;
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
        } else if(index == 2){
            if(!$('#staff').textbox('getValue')){
                $.messager.alert("提示","请先选择对换人")
                return false
            }
            if(!$('#dept').combogrid('grid').datagrid('getSelected')){
                $.messager.alert("提示","请先选择对换科室")
                return false
            }

            var prefix = 'S'+parent.formatDatebox(new Date()).replace(/-/g,'').substr(2);
            var suffix;
            var rows = $('#tradeManager').datagrid('getRows');
            if(rows.length > 0){
                var temp = rows[rows.length-1].documentNo;
                suffix = +temp.substr(7) + 1;
            } else {
                $.ajax({
                    type: 'get',
                    url: parent.basePath + '/asepsisLendRec/getMaxDocumentNo',
                    async : false,   // true 异步,false 同步
                    data: {orgId:currentOrgId},
                    contentType: 'application/json',
                    success: function(res){
                        if(res){
                            suffix = +(res.toString().substr(7)) + 1;
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
        var index = $('#tabs').tabs('getTabIndex',$('#tabs').tabs('getSelected'));
        var rows,url;
        if(index == 0) {
            if ($('#staff').combobox('getValue') == '') {
                $.messager.alert('提示', '请选择还物人！', 'warning');
                return false;
            }
            if ($('#dept').combobox('getValue') == '') {
                $.messager.alert('提示', '请选择还物科室！', 'warning');
                return false;
            }
            rows = $('#sendManager').datagrid('getChanges','updated');
            url = parent.basePath + '/asepsisLendRec/saveList';
        } else if(index == 1){
            rows = $('#poshManager').datagrid('getRows');
            url = parent.basePath + '/asepsisSendRec/saveList';
        } else if(index == 2){
            rows = $('#tradeManager').datagrid('getRows');
            url = parent.basePath + '/asepsisLendRec/saveList';
        }
        if(rows.length == 0){
            $.messager.alert('提示','没有要保存的数据！','warning');
            return false;
        }
        parent.$.postJSON(url,JSON.stringify(rows), function (res) {
            if(res == '1') {
                $.messager.alert('保存','保存成功！','info',function(){
                    window.location.reload()
                })
            } else {
                $.messager.alert('保存','保存失败！','error');
            }
        })
    })
    $('#closeBtn').click(function(){

    })
    $('#clearBtn').click(function(){

    })
    $('#queryBtn').click(function(){
        $.get(parent.basePath + '/asepsisLendRec/findList',getParams(),function(res){
            $('#sendManager').datagrid('loadData',res)
        })
    })

    var loadData = function(){
        $.get(parent.basePath + '/asepsisLendRec/findList',{})
    }

    function getParams(){
        var row = $('#dept').combogrid('grid').datagrid('getSelected')
        var params = {
            orgId: currentOrgId,
            lendDateStart: $('#exchangeStart').datebox('getValue'),
            lendDateEnd: $('#exchangeEnd').datebox('getValue')+' 23:59:59',
            toDept: row ? row.deptCode : '',
            returnFlag: '1'
        }
        return params
    }
});




