$(function(){
    var org_id = '1'
        ,select_index = 0   //诊疗项目默认选择索引，从0开始计算
        ,name_and_vs_obj = {}  //作已修改的别名和对照缓存对象

    var type_arr //诊疗项目类别数组
        ,clinic_data_arr // 执行科室数组
        ,hz_arr // 频次数组
        ,long_arr //长期临时数组
        ,price_type_arr //对照价表类别
        ,vs_grid_data = {}   // 各类型对照价表数据
        ,vs_type_data = {'1':'A,B'}   // 对照价表类别,1药品，2非药品
        ,price_rules_arr // 计费规则数组
    /**
     * 初始化诊疗项目类别下拉框
     * @param data
     */
    var init_item_class = function(data,default_value){
        $('#item_class').combobox({
            data : data,
            valueField:'value',
            textField:'label'
        });
        if(default_value)
            $('#item_class').combobox('setValue',default_value)
    }

    /**
     * 初始化操作按钮
     */
    var initButton = function (){
        $('#add_project').linkbutton({
            onClick : function(){
                init_pro_win()
            }
        });
        $('#del_project').linkbutton({
            onClick : function(){
                del_project()
            }
        });
        $('#build_module').linkbutton({
            onClick : function(){

            }
        });
        $('#add_alias').linkbutton({
            onClick : function(){
                add_alias()
            }
        });
        $('#del_alias').linkbutton({
            onClick : function(){
                del_alias()
            }
        });
        $('#add_vs').linkbutton({
            onClick : function(){
                add_vs()
            }
        });
        $('#del_vs').linkbutton({
            onClick : function(){
                del_vs()
            }
        });
        $('#reload_html').linkbutton({
            onClick : function(){
                window.location.reload();
            }
        });
        $('#save_data').linkbutton({
            onClick : function(){
                save_data_handler()
            }
        });
        $('#close_html').linkbutton({
            onClick : function(){
                parent.location.href = parent.getRootPath() + '/modules/clinic/index.html'
            }
        });

    }

    /**
     * 初始化诊疗项目列表
     */
    var init_clinic_data = function (){
        $("#clinic_item").datagrid({
            title : "临床诊疗项目列表",
            fit : true,
            border:0,
            //pagination: true, //显示分页
            //pageSize: 15, //页大小
            //pageList: [15, 30, 45, 60], //页大小下拉选项此项各value是pageSize的倍数
            fitColumns: true, //列自适应宽度
            singleSelect : true,
            remoteSort: false,
            idField :'id',
            columns: [[//显示的列
                {field: 'id', title: '编号', width: 20, hidden:true},
                { field: 'itemCode', title: '代码', width: 80, sortable: true,order:'desc',align : "center" },
                { field: 'itemName', title: '项目名称', width: 200,align:'center',formatter:function(value){
                    return '<div style="text-align:left">'+value+'</div>'
                }},
                { field: 'expand3', title: '执行科室', width: 120,align:'center',editor:{
                    type:'combogrid',
                    options:{
                        panelWidth:300,
                        idField:'deptCode',
                        editable:false,
                        textField:'deptName',
                        fitColumns: true,
                        data : clinic_data_arr,
                        columns:[[
                            {field:'deptCode',title:'部门代码',width:60},
                            {field:'deptName',title:'部门名称',width:100},
                            {field:'deptPropertity',title:'部门名称',width:100}
                        ]],
                        onChange:function(newV,oldV){
                            if(newV != oldV)
                            change_name_and_vs(newV,'expand3')
                        }
                    }
                },formatter:function(value){
                    if(value == undefined || value == '') return ''
                    for(var i= 0,j= (clinic_data_arr ? clinic_data_arr.length : 0 );i<j;i++){
                        if(clinic_data_arr[i].deptCode == value){
                            value = clinic_data_arr[i].deptName
                            break
                        }
                    }
                    return '<div style="text-align:left">'+value+'</div>';
                }},
                { field: 'expand4', title: '频次',align : "center", width: 80 ,editor:{
                    type:'combobox',
                    options:{
                        valueField:'value',
                        textField:'label',
                        data:hz_arr,
                        editable:false,
                        required:false,
                        onChange:function(newV,oldV){
                            if(newV != oldV)
                            change_name_and_vs(newV,'expand4')
                        }
                    }
                },formatter:function(value){
                    return format(hz_arr,value);
                }},
                { field: 'expand2', title: '类别',align : "center", width: 60,editor:{type : 'textbox',options:{
                    onChange:function(newV,oldV){
                        if(newV != oldV)
                        change_name_and_vs(newV,'expand2')
                    }
                }} },
                { field: 'expand1', title: '标本',align : "center", width: 60 ,editor:{type : 'textbox',options:{
                    onChange:function(newV,oldV){
                        if(newV != oldV)
                        change_name_and_vs(newV,'expand1')
                    }
                }}},
                { field: 'expand5', title: '长期临时',align : "center", width: 60 ,editor:{
                    type:'combobox',
                    options:{
                        valueField:'value',
                        textField:'label',
                        editable:false,
                        data:long_arr,
                        required:false,
                        onChange:function(newV,oldV){
                            if(newV != oldV)
                            change_name_and_vs(newV,'expand5')
                        }
                    }
                },formatter:function(value){
                    return format(long_arr,value);
                }},
                { field: 'inputCode', title: '拼音码',align : "center", width: 60 },
                { field: 'inputCodeWb', title: '五笔码',align : "center", width: 60 },
                { field: 'itemClass', title: '项目分类',align : "center", hidden:true},
                { field: 'itemStatus', title: '', hidden:true},
                { field: 'memo', hidden:true},
                { field: 'orgId', title: '所属组织主键', hidden:true}
            ]],
            toolbar: '#tb',
            onClickCell:onClickCell_clinic
        });
    }
    function endEditing_clinic(){
        if (select_index == undefined){return true}
        if ($('#clinic_item').datagrid('validateRow', select_index)){
            $('#clinic_item').datagrid('endEdit', select_index);
            return true;
        } else {
            return false;
        }
    }
    function onClickCell_clinic(index, field){
        end_other_edit('clinic_item')
        if (endEditing_clinic()){
            if(select_index != index) {
                save_name_and_vs(select_index)
                handler_name_and_vs(index)
            }
            $('#clinic_item').datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});
            select_index = index;
        }
    }

    /**
     * 初始化诊疗项目别名列表
     */
    var init_clinic_name_data = function (){
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
                { field: 'itemName', title: '诊疗项目名称及别名',align:'center', width:100,editor:{
                    type : 'textbox',
                    options:{
                        required:true,
                        missingMessage:'别名不能为空',
                        validType : ['nameIsExisted'],
                        onChange : function(value,oldV){
                            var _temp = makePy(value)
                            if(_temp){
                                _temp = _temp[0]
                            }
                            $('#clinic_item_name').datagrid('getSelected').inputCode = _temp.toUpperCase()
                        }
                    }
                },formatter:function(value){
                    return '<div style="text-align: left">'+value+'</div>'
                }},
                { field: 'inputCode', title: '拼音码', width:50,align:'center'},
                { field: 'inputCodeWb', title: '五笔码', width:50,align:'center'}
            ]],
            onClickCell:onClickCell
        });
        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            if ($('#clinic_item_name').datagrid('validateRow', editIndex)){
                $('#clinic_item_name').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickCell(index, field){
            end_other_edit('clinic_item_name')
            if (endEditing()){
                if(index == 0) return
                $('#clinic_item_name').datagrid('selectRow', index)
                    .datagrid('editCell', {index:index,field:field});
                editIndex = index;
            }
        }
    }

    /**
     * 初始化对照项目列表
     */
    var init_clinic_vs_charge = function (){
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
                        data:price_type_arr,
                        required:true
                        ,missingMessage:'类别必填',
                        onChange:function(newV,oldV){
                            if(oldV && oldV != newV){
                                var row = $('#clinic_vs_charge').datagrid('getSelected')
                                row.chargeItemCode = ''
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
                    return format(price_type_arr,value);
                }
                },
                { field: 'chargeItemCode', title: '名称',align:'center' ,width:80,editor:{
                    type:'combogrid',
                    options:{
                        panelWidth:383,
                        idField:'itemCode',
                        editable:false,
                        textField:'itemName',
                        fitColumns: true,
                        required:true
                        ,missingMessage:'名称必填',
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
                        }
                    }
                },formatter:function(value,row,index){
                    if(value == undefined || value == '') return ''
                    var type = row.priceType
                    var data = vs_grid_data[type]
                    for(var i= 0,j=data.length;i<j;i++){
                        if(value == data[i].itemCode){
                            row.price = data[i].price
                            row.stopDate = data[i].stopDate
                            //row.backbillRule = ''
                            row.count =  + row.price * +row.amount
                            value = data[i].itemName
                            break
                        }
                    }

                    return '<div style="text-align: left">'+value+'</div>'
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
                        data:price_rules_arr,
                        editable:false,
                        required:true
                        ,missingMessage:'计费规则必填'
                    }
                },formatter:function(value,row,index){
                    return format(price_rules_arr,value);
                }},
                { field: 'chargeItemClass', title: '详细类别', width:30,align:'center',formatter:function(value){
                    return format(type_arr,value)
                }},
                { field: 'count', title: '小计', width:30,align:'center'}
            ]],
            onClickCell:onClickCell
        });
        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            if ($('#clinic_vs_charge').datagrid('validateRow', editIndex)){
                $('#clinic_vs_charge').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickCell(index, field){
            end_other_edit('clinic_vs_charge')
            if (endEditing()){
                $('#clinic_vs_charge').datagrid('selectRow', index)
                    .datagrid('editCell', {index:index,field:field});
                if(field == 'chargeItemCode'){
                    var editor = $('#clinic_vs_charge').datagrid('getEditor',{index:index,field:'chargeItemCode'})
                    var type = $('#clinic_vs_charge').datagrid('getSelected').priceType
                    $(editor.target).combogrid('grid').datagrid('loadData',vs_grid_data[type]);
                }
                editIndex = index;
            }
        }
    }

    /**
     * 点击时取消其他编辑，并校验，不通过则删除整条数据
     * @param id
     */
    var end_other_edit = function(id){
        switch (id){
            case 'clinic_item' :
                validateNameList()
                validateVsList()
                break
            case 'clinic_item_name' :
                validateVsList()
                $('#clinic_item').datagrid('endEdit',select_index)
                break
            case 'clinic_vs_charge' :
                validateNameList()
                $('#clinic_item').datagrid('endEdit',select_index)
                break
            default :
                $('#clinic_item').datagrid('endEdit',select_index)
                validateNameList()
                validateVsList()
        }
    }

    /**
     * 校验所有别名,校验不通过的则移除
     * @returns {boolean}
     */
    var validateNameList = function(){
        var rows = $('#clinic_item_name').datagrid('getRows')
        for(var index = rows.length - 1;index > -1;index--) {
            $('#clinic_item_name').datagrid('endEdit',index)
            if (!$('#clinic_item_name').datagrid('validateRow', index) || !rows[index].itemName) {
                $('#clinic_item_name').datagrid('deleteRow',index)
            }
        }
    }

    /**
     * 校验对照,校验不通过的则移除
     * @returns {boolean}
     */
    var validateVsList = function(){
        var rows = $('#clinic_vs_charge').datagrid('getRows')
        for(var index = rows.length - 1;index > -1;index--) {
            $('#clinic_vs_charge').datagrid('endEdit',index)
            if (!$('#clinic_vs_charge').datagrid('validateRow', index) || !rows[index].chargeItemCode || !rows[index].amount || !rows[index].backbillRule) {
                $('#clinic_vs_charge').datagrid('deleteRow',index)
            }
        }
    }

    /**
     * 诊疗项目内容更新，则对应别名数据也更新为最新
     * @param value 更新对象值
     * @param field 更新域
     */
    var change_name_and_vs = function(value,field){
        var name_rows = $('#clinic_item_name').datagrid('getRows')
        if(name_rows[0].id) {
            if (!name_and_vs_obj[name_rows[0].id]) name_and_vs_obj[name_rows[0].id] = {}
            var name_data_update = name_and_vs_obj[name_rows[0].id].name_data_update
        }
        if(name_rows[0].id) {
            name_and_vs_obj[name_rows[0].id].name_data_update = []
            for(var i=0;i<name_rows.length;i++){
                name_rows[i][field] = value
                $('#clinic_item_name').datagrid('refreshRow', i);
                name_and_vs_obj[name_rows[0].id].name_data_update.push(name_rows[i])
            }
        }
    }

    /************************************ 数据加载方法 **********************************/
    /**
     * 加载诊疗项目数据，
     * @param index 默认选择索引
     */
    var load_data = function (index){
        var itemClass = $('#item_class').combobox('getValue') ? $('#item_class').combobox('getValue') : 'A'
        var params = {'itemClass':itemClass}
        params.itemCode = $(':radio[name="adminFlag"][value="0"]').prop('checked') ? $('#code_filter').textbox('getValue').toUpperCase() : ''
        params.inputCode = $(':radio[name="adminFlag"][value="1"]').prop('checked') ? $('#code_filter').textbox('getValue').toUpperCase() : ''
        params.orgId = org_id
        parent.$.postJSON('/service/clinicItem/findList',JSON.stringify(params),function(res){
            $('#clinic_item').datagrid('loadData',res)
            if(res && res.length>0 && index > -1){
                $('#clinic_item').datagrid('selectRow',index)
                load_name_data(index)
                load_vs_data(index)
            }
        })
    }

    /**
     * 加载临床诊疗项目所有名称(正/别名)数据
     * @param index  诊疗项目索引行
     */
    var load_name_data = function (index){
        var row = $('#clinic_item').datagrid('getRows')[index]
        parent.$.postJSON(base_url+'findNameList',JSON.stringify(row),function(res){
            load_name_data1(res)
            row.saveNameList = res
        })
    }
    var load_name_data1 = function (json){
        $('#clinic_item_name').datagrid('loadData',json)
        $('#clinic_item_name').datagrid('selectRow',0)
    }

    /**
     * 加载临床诊疗与价表对照信息
     * @param index 诊疗项目索引行
     */
    var load_vs_data = function (index){
        var row = $('#clinic_item').datagrid('getRows')[index]
        parent.$.postJSON(base_url+'findVsList',JSON.stringify(row),function(res){
            for(var i=0;i<res.length;i++){
                var chargeItemClass = res[i].chargeItemClass
                var type
                if(vs_type_data['1'].indexOf(chargeItemClass) > -1) type = '1'
                else type = '2'
                res[i].priceType = type
            }
            $('#clinic_vs_charge').datagrid('loadData',res)
            $('#clinic_vs_charge').datagrid('selectRow',0)
            row.saveVsList = res
        })
    }

    /**
     * 加载价表数据
     * @param type 类别，1 药品，2 非药品
     */
    var load_price_data = function(type){
        parent.$.postJSON('/service/price/findList','{"priceType" : "' + (vs_type_data[type]?vs_type_data[type]:'') + '"}',function(res){
            vs_grid_data[type] = res
        })
    }

    /**
     * 初始化一些码表数据
     */
    var init_arr = function (){
        $.ajaxAsync('/service/dict/findListByType',{type:'CLINIC_ITEM_CLASS_DICT'},function(res){
            type_arr = res
            var _temp = ''
            for(var i= 0,j = res.length; i<j ;i++){
                if(vs_type_data['1'].indexOf(res[i].value) < 0 )
                    _temp += "," + res[i].value
            }
            vs_type_data['2'] = _temp == '' ? _temp : _temp.substr(0)
            load_price_data('1')
            load_price_data('2')
            init_item_class(res,'A')
        },'GET',false)
        if(! clinic_data_arr)
            clinic_data_arr = [{
                "value":1,
                "label":"text1"
            },{
                "value":2,
                "label":"text2"
            }]
        $.ajaxAsync('/service/dept-dict/list','',function(res){
            clinic_data_arr = res
        },'GET',false)
        if(!hz_arr)
            hz_arr = [{
                "value":1,
                "label":"一日一次"
            },{
                "value":2,
                "label":"一日两次"
            }]
        if(!long_arr)
            long_arr = [{
                "value":1,
                "label":"长期"
            },{
                "value":2,
                "label":"临时"
            }]
        if(!price_type_arr)
            price_type_arr = [{
                "value":1,
                "label":"药品"
            },{
                "value":2,
                "label":"非药品"
            }]
        if(!price_rules_arr)
            price_rules_arr = [{
                "value":1,
                "label":"按次"
            },{
                "value":2,
                "label":"按日"
            },{
                "value":3,
                "label":"只记一次"
            },{
                "value":4,
                "label":"不计价"
            }]
    }
    /************************************************************************************/

    /**
     * 加载处理名称和对照
     * @param index 需要加载的诊疗项目索引
     */
    var handler_name_and_vs = function (index){
        var row = $('#clinic_item').datagrid('getRows')[index]
        //有则加载，没有则查询数据库
        if (row.saveNameList) {
            $('#clinic_item_name').datagrid('loadData',row.saveNameList)
        } else {
            load_name_data(index)
        }
        if (row.saveVsList) {
            $('#clinic_vs_charge').datagrid('loadData', row.saveVsList)
        } else {
            load_vs_data(index)
        }
    }

    /**
     * 通过点击诊疗项目，暂存修改的名称和对照信息
     * 别名和对照修改信息保存在name_and_vs_obj（只包含诊疗项目有ID的，没有ID的为添加数据，会保存在诊疗项目数据中）
     * @param index 失去选择数据的索引
     */
    var save_name_and_vs = function (index){
        if(index == undefined || index < 0) return
        var row = $('#clinic_item').datagrid('getRows')[index]
        if(!row) return
        var name_data_add = handle_add('clinic_item_name')
        var vs_data_add = handle_add('clinic_vs_charge')
        if(row.id){
            var name_data_update = handler_update('clinic_item_name',name_and_vs_obj[row.id] ? name_and_vs_obj[row.id].name_data_update : undefined)
            var name_data_del = handler_del('clinic_item_name',name_and_vs_obj[row.id] ? name_and_vs_obj[row.id].name_data_del : undefined)
            var vs_data_update = handler_update('clinic_vs_charge',name_and_vs_obj[row.id] ? name_and_vs_obj[row.id].vs_data_update : undefined)
            var vs_data_del = handler_del('clinic_vs_charge',name_and_vs_obj[row.id] ? name_and_vs_obj[row.id].vs_data_del : undefined)
            if(name_data_add.length > 0 || name_data_update.length > 0 || name_data_del.length > 0 || vs_data_add.length > 0 || vs_data_update.length > 0 || vs_data_del.length > 0){
                name_and_vs_obj[row.id] = {}
                name_and_vs_obj[row.id].name_data_add = name_data_add
                name_and_vs_obj[row.id].name_data_update = name_data_update
                name_and_vs_obj[row.id].name_data_del = name_data_del
                name_and_vs_obj[row.id].vs_data_add = vs_data_add
                name_and_vs_obj[row.id].vs_data_update = vs_data_update
                name_and_vs_obj[row.id].vs_data_del = vs_data_del
            }
        } else {
            row.saveNameList =name_data_add
            row.saveVsList = vs_data_add
        }
        function handle_add(id){
            var rows = $('#'+id).datagrid('getRows')
            var add_rows = []
            for(var i=0;i<rows.length;i++){
                if(!rows[i].id){
                    add_rows.push(rows[i])
                }
            }
            return add_rows
        }
        function handler_update(id,old_update){
            var update_rows = $('#'+id).datagrid('getChanges','updated')
            old_update = old_update ? old_update : []
            if(old_update.length < 1){
                for(var i=0;i<update_rows.length;i++){
                    if(update_rows[i].id)
                        old_update.push(update_rows[i])
                }
            } else {
                for(var i=0;i<update_rows.length;i++){
                    if(update_rows[i].id){
                        for(var j=0;j<old_update.length;j++){
                            if(update_rows[i].id == old_update[j].id){
                                old_update.push(update_rows[i])
                            }
                        }
                    }
                }
            }
            return old_update
        }
        function handler_del(id,old_delete){
            var del_rows = $('#'+id).datagrid('getChanges','deleted')
            old_delete = old_delete ? old_delete : []
            for(var i=0;i<del_rows.length;i++){
                if(del_rows[i].id){
                    old_delete.push(del_rows[i])
                }
            }
            return old_delete
        }
    }

    /**
     * 新增临床诊疗项目
     */
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
    var init_pro_win = function (){
        $('#dept_grid').datagrid('loadData',vs_grid_data['1'].concat(vs_grid_data['2']))
        $('#clinic_org').combobox({
            disabled : true
        })
        $('#add_pro_win').window('open')
        $('#pro_form input:radio[value="0"]').prop('checked',true)
        $('#clinic_org').combobox('setValue','')
        $('#clinic_org').combobox('loadData',clinic_data_arr)
        $('#item_code').textbox('setValue','')
        $('#item_name').textbox('setValue','')
    }
    $('#yes_pro_form').click(function(){
        if(!$('#item_code').textbox('isValid') || !$('#item_name').textbox('isValid'))return
        end_other_edit()
        var inputCode = makePy($('#item_name').textbox('getValue'))
        var new_name_json = [{'stdIndicator':'1'
            ,'itemName':$('#item_name').textbox('getValue')
            ,'itemCode':$('#item_code').textbox('getValue')
            ,'itemClass':$('#item_class').combobox('getValue')
            ,'inputCode': (inputCode.length > 0 ? inputCode[0].toUpperCase() : $('#item_name').textbox('getValue'))
            ,'expand3': $(':radio[name="org_type"][value="1"]').prop('checked') ? $('#clinic_org').combobox('getValue') : ''
            ,'orgId' : org_id
            ,'inputCodeWb': ''}]
        var pro_obj = {'itemClass':$('#item_class').combobox('getValue')
            ,'itemCode':$('#item_code').textbox('getValue')
            ,'itemName':$('#item_name').textbox('getValue')
            ,'inputCode': (inputCode.length > 0 ? inputCode[0].toUpperCase() : $('#item_name').textbox('getValue'))
            ,'inputCodeWb': ''
            ,'expand3': $(':radio[name="org_type"][value="1"]').prop('checked') ? $('#clinic_org').combobox('getValue') : ''
            ,'orgId' : org_id
            ,'saveNameList':new_name_json
        }
        $("#clinic_item").datagrid('appendRow',pro_obj)
        select_index = $("#clinic_item").datagrid('getRows').length-1
        $("#clinic_item").datagrid('selectRow',select_index)
        handler_name_and_vs(select_index)
        $('#add_pro_win').window('close')
    })
    $('#cancle_pro_form').click(function(){
        $('#add_pro_win').window('close')
    })
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

    /**
     * 删除诊疗项目
     */
    var del_project = function (){
        select_index = undefined
        var row = $('#clinic_item').datagrid('getSelected')
        if(row){
            $('#clinic_item').datagrid('deleteRow',$('#clinic_item').datagrid('getRowIndex',row))
            $('#clinic_item_name').datagrid('loadData',[])
            $('#clinic_vs_charge').datagrid('loadData',[])
        } else {
            $.messager.alert('警告','请选择一条删除数据！','error');
        }
    }

    /**
     * 添加别名
     */
    var add_alias = function (){
        var row = $("#clinic_item").datagrid('getSelected')
        if(row){
            var new_name_json = {'stdIndicator':'2'
                ,'itemName':''
                ,'itemCode':row.itemCode
                ,'itemClass':row.itemClass
                ,'inputCode': ''
                ,'expand1': row.expand1
                ,'expand2': row.expand2
                ,'expand3': row.expand3
                ,'expand4': row.expand4
                ,'expand5': row.expand5
                ,'inputCodeWb': ''
                ,'orgId': row.orgId
            }
            $('#clinic_item_name').datagrid('appendRow',new_name_json)
        } else {
            $.messager.alert('警告','请先选择需要添加别名的诊疗项目!','error')
        }
    }

    /**
     * 删除别名
     */
    var del_alias = function (){
        var row = $("#clinic_item_name").datagrid('getSelected')
        if(!row){
            $.messager.alert('警告','请选择需要删除的别名！','error')
        } else {
            var index = $("#clinic_item_name").datagrid('getRowIndex',row)
            if(index == 0){
                $.messager.alert('警告','不能删除正名！','error')
            } else {
                $("#clinic_item_name").datagrid('deleteRow',index)
            }
        }
    }

    /**
     * 添加价表对照
     */
    var add_vs = function (){
        var row = $("#clinic_item").datagrid('getSelected')
        if(row){
            var vs_json = {
                'priceType':'1',
                'clinicItemClass':row.itemClass,
                'clinicItemCode':row.itemCode,
                'chargeItemNo':'1',
                'chargeItemClass':'',
                'chargeItemCode':'',
                'chargeItemSpec':'',
                'amount':'',
                'units':'',
                'backbillRule':'',
                'orgId':org_id
            }
            $('#clinic_vs_charge').datagrid('appendRow',vs_json)
        } else {
            $.messager.alert('警告','请先选择需要添加对照的诊疗项目!','error')
        }
    }

    /**
     * 删除价表对照
     */
    var del_vs = function (){
        var row = $("#clinic_vs_charge").datagrid('getSelected')
        if(!row){
            $.messager.alert('警告','请选择需要删除的对照项目！','error')
        } else {
            var index = $("#clinic_vs_charge").datagrid('getRowIndex',row)
            $("#clinic_vs_charge").datagrid('deleteRow',index)
        }
    }

    /**
     * 保存
     */
    var save_data_handler = function (){
        end_other_edit()
        save_name_and_vs($('#clinic_item').datagrid('getRowIndex',$('#clinic_item').datagrid('getSelected')))
        // 传输修改的参数
        var item_data_save = []
        item_data_save = item_data_save.concat($('#clinic_item').datagrid('getChanges','updated'))
        for(var i= 0,j=item_data_save.length;i<j;i++){
            delete item_data_save[i].saveNameList
            delete item_data_save[i].saveVsList
        }
        item_data_save = item_data_save.concat($('#clinic_item').datagrid('getChanges','inserted'))
        //删除
        var item_data_del = $('#clinic_item').datagrid('getChanges','deleted')
        var ids = ''
        for(var i=0;i<item_data_del.length;i++){
            var id = item_data_del[i].id
            ids += ','+id
            if(name_and_vs_obj[item_data_del[i].id]){
                delete name_and_vs_obj[item_data_del[i].id]
            }
        }
        //诊疗项目的删除信息
        if(ids.length > 0)
            item_data_save.push({'id':ids.substr(1),'delFlag':'1'})

        var name_save=[] ,name_ids='' ,vs_save=[], vs_ids = ''
        for(var id in name_and_vs_obj){
            var obj = name_and_vs_obj[id]
            name_save = name_save.concat(obj.name_data_add ? obj.name_data_add : [])
            name_save = name_save.concat(obj.name_data_update ? obj.name_data_update : [])
            var name_del_data = obj.name_data_del ? obj.name_data_del : []
            for(var i=0;i<name_del_data.length;i++){
                name_ids += ',' + name_del_data[i].id
            }

            vs_save = vs_save.concat(obj.vs_data_add ? obj.vs_data_add : [])
            vs_save = vs_save.concat(obj.vs_data_update ? obj.vs_data_update : [])
            var vs_del_data = obj.vs_data_del ? obj.vs_data_del : []
            for(var i=0;i<vs_del_data.length;i++){
                vs_ids += ','+vs_del_data[i].id
            }
        }
        name_ids = name_ids.length > 0 ? name_ids.substr(1) : name_ids
        vs_ids = vs_ids.length > 0 ? vs_ids.substr(1) : vs_ids
        //诊疗项目的名称和对照修改信息，标志updateFlag=1
        if(name_save.length > 0 || vs_save.length > 0 || name_ids.length > 0 || vs_ids.length > 0){
            item_data_save.push({updateFlag: '1',saveNameList :name_save,saveVsList:vs_save,delNameIds:name_ids,delVsIds:vs_ids})
        }
        parent.$.postJSON('/service/clinicItem/save',JSON.stringify(item_data_save),function(res){
            if(res.success = '0')
                $.messager.alert('成功',res.data,'info',function(){
                    window.location.reload()
                })
        })
    }

    /**
     * 通过数组中数据格式化value
     * @param arr 格式为[{'value':'value','label':'label'}....]
     * @param value
     * @returns {*}
     */
    var format = function (arr,value){
        if(arr){
            for(var i= 0,j=arr.length;i<j;i++){
                if(arr[i].value == value) return arr[i].label
            }
        }
        return value
    }

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
                    //$('#clinic_item').datagrid('selectRow',index)
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
            load_data(0)
        }
    })

    init_arr()
    init_clinic_data()
    init_clinic_name_data()
    init_clinic_vs_charge()
    initButton()

    load_data(select_index)
})