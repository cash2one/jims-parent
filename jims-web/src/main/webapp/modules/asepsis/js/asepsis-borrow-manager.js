/**
 * 借物领物
 * @author yangruidong
 * @version 2016-06-25
 */
$(function () {
    var currentOrgId = '1';
    var currentUsername = '测试员';
    var currentSelectDeptCode = undefined;
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
        method: 'get'
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
        onSelect: function (index, row) {
            currentSelectDeptCode = row.deptCode;
            var param = {
                orgId:currentOrgId,
                lendDate: parent.parseToDate($('#exchangeStart').datebox('getValue')),
                toDept: row.deptCode
            }
            $.get(parent.basePath + '/asepsisLendRec/findList',param,function(res){
                $('#lendManager').datagrid('loadData',res)
            })
        },
        onBeforeCollapse:function(){
            return false
        }
    })
    $('#tabs').tabs({
        fit:true,
        justified:true,
        onSelect: function(title,index){
            switch (index){
                case 0 :
                    $('#text1').html('借物日期：');
                    $('#text2').html('借物人：');
                    $('#text3').html('借物科室：');
                    $('#exchangeEnd').datebox('disable');
                    $('#addBtn').linkbutton('enable');
                    $('#delBtn').linkbutton('enable');
                    $('#queryBtn').linkbutton('disable');
                    $('#printBtn').linkbutton('disable');
                    break;
                case 1 :
                    $('#text1').html('送物日期：');
                    $('#text2').html('领物人：');
                    $('#text3').html('领物科室：');
                    $('#exchangeEnd').datebox('enable');
                    $('#addBtn').linkbutton('disable');
                    $('#delBtn').linkbutton('disable');
                    $('#queryBtn').linkbutton('enable');
                    break;
                case 2 :
                    $('#text1').html('对换日期：');
                    $('#text2').html('对换人：');
                    $('#text3').html('对换科室：');
                    $('#exchangeEnd').datebox('enable');
                    $('#addBtn').linkbutton('disable');
                    $('#delBtn').linkbutton('disable');
                    $('#queryBtn').linkbutton('enable');
                    break;
            }
        }
    })
    /**
     * 校验通过则结束编辑
     * @returns {boolean}
     */
    var endEditing = function(){
        if (currentSelectIndex == undefined){return true}
        var editor = $('#lendManager').datagrid('getEditor',{index:currentSelectIndex,field:'itemName'})
        if(editor){
            var rows = $(editor.target).combogrid('grid').datagrid('getRows');
            if(rows.length > 0){
                if(!$(editor.target).combogrid('grid').datagrid('getSelected')){
                    $(editor.target).combogrid('grid').datagrid('selectRow',0)
                }
            } else {
                $(editor.target).combogrid('setValue','')
            }
        }
        if ($('#lendManager').datagrid('validateRow', currentSelectIndex)){
            $('#lendManager').datagrid('endEdit', currentSelectIndex);
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
        if (endEditing()){
            currentSelectIndex = index;
            var row = $('#lendManager').datagrid('getRows')[index];
            if(row.id) return ;
            $('#lendManager').datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});

        }
    }
    $('#lendManager').datagrid({
        fit : true,
        border:1,
        fitColumns: true,
        singleSelect : true,
        remoteSort: false,
        idField :'id',
        columns:[[      //每个列具体内容
            {field:'documentNo',title:'单据号',width:'80',align:'center'}
            ,{field:'itemNo',title:'序号',width:'50',align:'center'}
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
                        url : parent.basePath + '/asepsisDict/findListHasStock?orgId='+currentOrgId,
                        method:'get',
                        mode:'remote',
                        columns:[[
                        {field:'asepsisCode',title:'包代码',width:100,align : "center"},
                        {field:'asepsisName',title:'包名称',width:160,halign : "center",align : "left" },
                        {field:'inputCode',title:'拼音码',width:70,align : "center"}
                    ]],onSelect:function(index,row){
                            loadStock(row);
                    }
                }
            }}
            ,{field:'itemSpec',title:'规格',width:'50',align:'center'}
            ,{field:'lendAmount',title:'数量',width:'50',align:'center',editor:{
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
                var n = isNaN(row.lendAmount) ? 0 : (+row.lendAmount);
                var f = isNaN(row.antiFee) ? 0 : (+row.antiFee);
                return (n * f).toFixed(3)
            }}
            ,{field:'nobackFee',title:'辅料费',width:'60',align:'center'}
            ,{field:'antiDate',title:'消毒日期',width:'80',align:'center'}
            ,{field:'lendDate',title:'借物日期',width:'80',align:'center',formatter: function(value){
                return parent.formatDatebox(value)
            }}
            ,{field:'lender',title:'借物人',width:'60',align:'center'}
            ,{field:'memos',title:'备注',width:'70',align:'center',editor:'textbox'}
            //,{field:'toDept',title:'借物科室',width:'100',align:'center'}
            //,{field:'returnAmount',title:'已还数量',width:'100',align:'center'}
            //,{field:'returnDate',title:'还物日期',width:'100',align:'center'}
            //,{field:'operator',title:'操作员',width:'100',align:'center'}
            //,{field:'expDocumentNo',title:'借出单号',width:'100',align:'center'}
            //,{field:'reqDate',title:'申请时间',width:'100',align:'center'}
            //,{field:'reqOperator',title:'申请人',width:'100',align:'center'}
            //,{field:'orgId',title:'所属机构ID',width:'100',align:'center'}
        ]],
        onClickCell:onClickCell
    });

    /**
     * 加载库存
     * @param drugDict
     */
    var loadStock = function(asepsisDict){
        $.get('/service/asepsisStock/findList',
            {orgId:asepsisDict.orgId,fromDept:asepsisDict.belongDept,itemCode:asepsisDict.asepsisCode},function(res){
                showAsepsisWindow(res,asepsisDict)
        })
    }

    /**
     * 返回到选择前的值
     * @param oldV 选择前的值
     */
    var rollBack = function(oldV){
        var editor = $('#lendManager').datagrid('getEditor',{index:currentSelectIndex,field:'itemName'})
        $(editor.target).combogrid('setValue',oldV)
        $('#lendManager').datagrid('endEdit',currentSelectIndex)
    }

    /**
     * 判断是否已存在此批号
     * @param o
     */
    var chargeExisted = function(o){
        var _allData = $('#lendManager').datagrid('getRows')
        for(var i= 0,j = _allData.length-1;i < j;i++){
            if(i != currentSelectIndex && _allData[i].expDocumentNo == o.documentNo){
                return true
            }
        }
        return false
    }

    /**
     * 展现库存，当参数内只有一条数据时不显示，直接赋值
     * @param stocks 库存数据
     * @param asepsisDict 包
     */
    var showAsepsisWindow = function(stocks,asepsisDict){
        if(!stocks) return
        var row = $('#lendManager').datagrid('getSelected')
        var oldItemName = row.itemName

        var initData = function(stock){
            var _o = {documentNo:stock.documentNo}
            if(chargeExisted(_o)){
                $.messager.alert('提示','该批号的包已借！','warning')
                rollBack(oldItemName)
                return
            }
            row.itemCode = stock.itemCode
            row.itemSpec = stock.itemSpec
            row.units = stock.units
            row.antiFee = asepsisDict.antiPrice
            row.nobackFee = asepsisDict.nobackPrice
            row.antiDate = stock.antiDate
            row.expDocumentNo = stock.documentNo

            $('#lendManager').datagrid('endEdit',currentSelectIndex)
            tempFlag = true
        }
        var tempFlag = false
        if(stocks.length == 1){
            initData(stocks[0])
            return
        }
        $('#asepsisWindow').window({
            title: '库存信息',
            width: '550',
            height: '450',
            collapsible :false,
            minimizable : false,
            maximizable : false,
            modal : true,
            resizable: false,
            onClose : function(){
                if(!tempFlag){
                    rollBack(oldItemName)
                }
            }
        })
        $("#asepsisTable").datagrid({
            fit: true,
            border: 0,
            fitColumns: true, //列自适应宽度
            singleSelect: true,
            remoteSort: false,
            data: stocks,
            idField: 'id',
            columns: [[
                {field: 'documentNo', title: '单据号'},
                {field: 'fromDept', title: '所属科室', width: 60, align: "center"},
                {field: 'itemCode', title: '代码', width: 60, align: "center"},
                {field: 'itemName', title: '名称',hidden:true},
                {field: 'itemSpec', title: '规格', width: 200, align: "center"},
                {field: 'amount', title: '数量', width: 60, align: "center"},
                {field: 'antiDate', title: '消毒日期', width: 60, align: "center"},
                {field: 'memos', title: '备注', width: 60, align: "center"},
                {field: 'alterDate', title: '修改日期', width: 60, align: "center"},
                {field: 'antiBatchNo', title: '消毒批号', width: 60, align: "center"}
            ]],
            onDblClickRow : function(index,row){
                initData(row)
                $('#asepsisWindow').window('close')
            }
        })
    }

    $('#addBtn').click(function(){
        if(!$('#staff').textbox('getValue')){
            $.messager.alert("提示","请先选择借物人")
            return false
        }
        if(currentSelectDeptCode == undefined){
            $.messager.alert("提示","请先选择借物科室")
            return false
        }
        var document;
        var itemNo;
        var rows = $('#lendManager').datagrid('getRows')
        if(rows.length > 0){
            var temp = rows[rows.length-1].documentNo;
            itemNo = +temp.substr(6)+1
            document = temp.substr(0,6) + ('0000' + itemNo).substr(-4);
        } else {
            itemNo = '1'
            document = parent.formatDatebox(new Date()).replace(/-/g,'').substr(2)+'0001';
        }
        var row = {
            orgId: currentOrgId,
            documentNo: document,
            itemNo: itemNo,
            lendDate: new Date(),
            lender: $('#staff').combobox('getValue'),
            toDept: $('#dept').combogrid('getValue'),
            reqDate: new Date(),
            operator: currentUsername,
            lendAmount:'1'
        }
        $('#lendManager').datagrid('appendRow',row)
    })
    $('#delBtn').click(function(){
        if(currentSelectIndex == undefined){
            $.messager.alert("提示","请先选择要删除的数据！",'info');
            return false;
        }
        $('#lendManager').datagrid('deleteRow',currentSelectIndex);
        currentSelectIndex = undefined;
    })
    $('#saveBtn').click(function(){
        var rows = $('#lendManager').datagrid('getChanges','inserted');
        parent.$.postJSON(parent.basePath + '/asepsisLendRec/saveList',JSON.stringify((rows)), function (res) {
            alert(res)
        })
    })
    $('#closeBtn').click(function(){

    })

});




