$(function(){
    var base_url = '/service/clinicItem/'
        ,org_id = "1"

        ,select_index = 0   //诊疗项目默认选择索引，从0开始计算
        ,name_and_vs_obj = {}  //作已修改的别名和对照缓存对象
    var type_arr //诊疗项目类别数组
        ,clinic_data_arr // 执行科室数组
        ,hz_arr // 频次数组
        ,long_arr //长期临时数组
        ,price_type_arr //对照价表类别

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
                window.close()
            }
        });

    }

    /**
     * 初始化诊疗项目列表
     */
    var init_clinic_data = function (){
        $("#clinic_item").datagrid({
            title : "临床诊疗项目列表",
            iconCls: 'icon-save', //图标
            fit : true,
            pagination: true, //显示分页
            pageSize: 15, //页大小
            pageList: [15, 30, 45, 60], //页大小下拉选项此项各value是pageSize的倍数
            fitColumns: true, //列自适应宽度
            singleSelect : true,
            idField :'id',
            columns: [[//显示的列
                {field: 'id', title: '编号', width: 20, hidden:true},
                { field: 'itemCode', title: '代码', width: 80, sortable: true,align : "center" },
                { field: 'itemName', title: '项目名称', width: 200,editor:{
                    type : 'textbox',
                    options:{
                        required:true,
                        onChange : function(value){
                            var _temp = makePy(value)
                            if(_temp){
                                _temp = _temp[0]
                            }
                            $('#clinic_item').datagrid('getSelected').inputCode = _temp
                        }
                    }
                } },
                { field: 'expand3', title: '执行科室', width: 120 ,editor:{
                    type:'combobox',
                    options:{
                        valueField:'value',
                        textField:'label',
                        data:clinic_data_arr,
                        required:false
                    }
                },formatter:function(value){
                    return format(clinic_data_arr,value);
                }},
                { field: 'expand4', title: '频次', width: 80 ,editor:{
                    type:'combobox',
                    options:{
                        valueField:'value',
                        textField:'label',
                        data:hz_arr,
                        required:false
                    }
                },formatter:function(value){
                    return format(hz_arr,value);
                }},
                { field: 'expand2', title: '类别', width: 60,editor:'textbox' },
                { field: 'expand1', title: '标本', width: 60 ,editor:'textbox'},
                { field: 'expand5', title: '长期临时', width: 60 ,editor:{
                    type:'combobox',
                    options:{
                        valueField:'value',
                        textField:'label',
                        data:long_arr,
                        required:false
                    }
                },formatter:function(value){
                    return format(long_arr,value);
                }},
                { field: 'inputCode', title: '拼音码', width: 60 },
                { field: 'inputCodeWb', title: '五笔码', width: 60 },
                { field: 'itemClass', title: '项目分类', hidden:true},
                { field: 'itemStatus', title: '', hidden:true},
                { field: 'memo', hidden:true},
                { field: 'orgId', title: '所属组织主键', hidden:true}
            ]],
            toolbar: '#tb',
            onClickCell:onClickCell,
            onSelect:function(){
                return false
            }
        });
        function endEditing(){
            if (select_index == undefined){return true}
            if ($('#clinic_item').datagrid('validateRow', select_index)){
                $('#clinic_item').datagrid('endEdit', select_index);
                return true;
            } else {
                return false;
            }
        }
        function onClickCell(index, field){
            if(select_index != index) {
                save_name_and_vs(select_index)
                handler_name_and_vs(index)
            }
            if (endEditing()){
                $('#clinic_item').datagrid('selectRow', index)
                    .datagrid('editCell', {index:index,field:field});
                select_index = index;
            }
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
            columns: [[//显示的列
                {field: 'id', title: '编号', width: 100,hidden:true},
                { field: 'stdIndicator', title: '正名', width:30,align:'center',formatter:function(value){
                    if(value == '1')return '正名'
                    return '别名'
                }},
                { field: 'itemName', title: '诊疗项目名称及别名', width:100,editor:{
                    type : 'textbox',
                    options:{
                        required:true,
                        missingMessage:'别名不能为空',
                        onChange : function(value){
                            var _temp = makePy(value)
                            if(_temp){
                                _temp = _temp[0]
                            }
                            $('#clinic_item_name').datagrid('getSelected').inputCode = _temp
                        }
                    }
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
            if(index == 0) return
            if (endEditing()){
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
            columns: [[//显示的列
                {field: 'id', title: '编号', width: 100,hidden:true},
                { field: 'prictType', title: '类别', width:30,align:"center",editor:{
                    type:'combobox',
                    options:{
                        valueField:'value',
                        textField:'label',
                        data:price_type_arr,
                        required:false
                    }
                },formatter:function(value){
                    return format(price_type_arr,value);
                }
                },
                { field: 'chargeItemCode', title: '名称', width:80,editor:{
                    type:'combobox',
                    options:{
                        valueField:'value',
                        textField:'label',
                        data:price_type_arr,
                        required:false
                    }
                },formatter:function(value){
                    return format(price_type_arr,value);
                }},
                { field: 'chargeItemSpec', title: '规格', width:50},
                { field: 'amount', title: '数量', width:20},
                { field: 'units', title: '单位', width:20},
                { field: 'price', title: '单价', width:20},
                { field: 'stopDate', title: '停止日期', width:20},
                { field: 'backbillRule', title: '计费规则', width:30},
                { field: 'chargeItemClass', title: '详细类别', width:30},
                { field: 'count', title: '小计', width:30}
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
            if (endEditing()){
                $('#clinic_vs_charge').datagrid('selectRow', index)
                    .datagrid('editCell', {index:index,field:field});
                editIndex = index;
            }
        }
    }

    /**
     * 校验所有别名
     * @returns {boolean}
     */
    var validateNameList = function(){
        var rows = $('#clinic_item_name').datagrid('getRows')
        for(var index = 0,j = rows.length;index < j;index++) {
            if (!$('#clinic_item_name').datagrid('validateRow', index)) {
                return false
            }
        }
        return true
    }

    /**
     * 校验对照
     * @returns {boolean}
     */
    var validateVsList = function(){
        var rows = $('#clinic_vs_charge').datagrid('getRows')
        if(!rows || rows.length == 0) return false
        for(var index = 0,j = rows.length;index < j;index++) {
            if (!$('#clinic_item_name').datagrid('validateRow', index)) {
                return false
            }
        }
        return true
    }

    /************************************ 数据加载方法 **********************************/
    /**
     * 加载诊疗项目数据，
     * @param index 默认选择索引
     */
    var load_data = function (index){
        var params = {'itemClass':$('#item_class').val()}
        params.itemCode = $(':radio[name="adminFlag"][value="0"]').attr('checked') ? $('#code_filter').val() : ''
        params.inputCode = $(':radio[name="adminFlag"][value="1"]').attr('checked') ? $('#code_filter').val() : ''
        $.postJSON('/service/clinicItem/findList',JSON.stringify(params),function(res){
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
        $.postJSON(base_url+'findNameList',JSON.stringify(row),function(res){
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
        $.postJSON(base_url+'findVsList',JSON.stringify(row),function(res){
            $('#clinic_vs_charge').datagrid('loadData',res)
            $('#clinic_vs_charge').datagrid('selectRow',0)
            row.saveVsList = res
        })
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
            if(name_data_add.length > 0 || name_data_update.length > 0 || name_data_del.length > 0 || vs_data_add.length > 0 || vs_data_update.length > 0 || vs_data_update.length > 0){
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
    var init_pro_win = function (){
        $('#add_pro_win').window({
            title : '新增临床诊疗项目',
            width : '350',
            height : '300',
            resizable : false
        })
        $('#pro_form input:radio[value="0"]').prop('checked',true)
        $('#clinic_org').combobox('loadData',clinic_data_arr)
        $('#item_code').val('')
        $('#item_name').val('')
    }
    $('#yes_pro_form').click(function(){
        var inputCode = makePy($('#item_name').val())
        var new_name_json = [{'stdIndicator':'1'
            ,'itemName':$('#item_name').val()
            ,'itemCode':$('#item_code').val()
            ,'itemClass':$('#item_class').val()
            ,'inputCode': (inputCode.length > 0 ? inputCode[0] : $('#item_name').val())
            ,'expand3': $(':radio[name="org_type"][value="1"]').attr('checked') ? $('#clinic_org').val() : ''
            ,'orgId' : org_id
            ,'inputCodeWb': ''}]
        var pro_obj = {'itemClass':$('#item_class').val()
            ,'itemCode':$('#item_code').val()
            ,'itemName':$('#item_name').val()
            ,'inputCode': (inputCode.length > 0 ? inputCode[0] : $('#item_name').val())
            ,'inputCodeWb': ''
            ,'expand3': $(':radio[name="org_type"][value="1"]').attr('checked') ? $('#clinic_org').val() : ''
            ,'orgId' : org_id
            ,'saveNameList':new_name_json
        }
        $("#clinic_item").datagrid('appendRow',pro_obj)
        select_index = $("#clinic_item").datagrid('getRows').length-1

        $("#clinic_item").datagrid('selectRow',select_index)
        //handler_name_and_vs(select_index)
        $('#add_pro_win').window('close')
    })
    $('#cancle_pro_form').click(function(){
        $('#add_pro_win').window('close')
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
                'clinicItemClass':'1',
                'clinicItemCode':row.itemCode,
                'chargeItemNo':'',
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
        $.postJSON('/service/clinicItem/save',JSON.stringify(item_data_save),function(res){
            if(res.success = '0')
                $.messager.alert('成功',res.msg,'info',function(){
                    window.location.reload()
                })
        })
    }

    /**
     * 初始化一些码表数据
     */
    var init_arr = function (){
        if(! clinic_data_arr)
            clinic_data_arr = [{
                "value":1,
                "label":"text1"
            },{
                "value":2,
                "label":"text2"
            }]
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



    init_arr()
    init_clinic_data()
    init_clinic_name_data()
    init_clinic_vs_charge()
    initButton()
    load_data(select_index)
})