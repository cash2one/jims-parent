$(function(){
    var base_url = '/service/clinicItem/'
    var select_index = 0   //诊疗项目默认选择行数，从0开始
    var save_index_str = ',' //记录有需要保存的诊疗项目序列,仅含有修改
    var name_and_vs_obj = {}
    init_clinic_data()
    init_clinic_name_data()
    init_clinic_vs_charge()
    initButton()
    load_data()

    function initButton(){
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

            }
        });

    }

    function init_clinic_data(){
        $("#clinic_item").datagrid({
            title : "临床诊疗项目列表",
            iconCls: 'icon-save', //图标
            fit : true,
            pagination: true, //显示分页
            pageSize: 15, //页大小
            pageList: [15, 30, 45, 60], //页大小下拉选项此项各value是pageSize的倍数
            fitColumns: true, //列自适应宽度
            singleSelect : true,
            columns: [[//显示的列
                {field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
                { field: 'itemCode', title: '代码', width: 80, sortable: true },
                { field: 'itemName', title: '项目名称', width: 200,editor:'textbox' },
                { field: 'expand3', title: '执行科室', width: 120 ,editor:'textbox'},
                { field: 'expand4', title: '频次', width: 80 ,editor:'textbox'},
                { field: 'expand2', title: '类别', width: 60,editor:'textbox' },
                { field: 'expand1', title: '标本', width: 60 ,editor:'textbox'},
                { field: 'expand5', title: '长期临时', width: 60 ,editor:'textbox'},
                { field: 'inputCode', title: '拼音码', width: 60 },
                { field: 'inputCodeWb', title: '五笔码', width: 60 },
                { field: 'itemClass', title: '项目分类', hidden:true},
                { field: 'itemStatus', title: '', hidden:true},
                { field: 'memo', hidden:true},
                { field: 'orgId', title: '所属组织主键', hidden:true}
            ]],
            toolbar: '#tb',
            onClickCell:onClickCell
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
            save_name_and_vs(select_index)
            handler_name_and_vs(index)

            if (endEditing()){
                $('#clinic_item').datagrid('selectRow', index)
                    .datagrid('editCell', {index:index,field:field});
                select_index = index;
            }
        }
    }

    function init_clinic_name_data(){
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
                { field: 'inputCodeWb', title: '五笔码', width:50,align:'center'},
                { field: 'itemClass', hidden:true},
                { field: 'itemCode', hidden:true},
                { field: 'expand1', hidden:true},
                { field: 'expand2', hidden:true},
                { field: 'expand3', hidden:true},
                { field: 'expand4', hidden:true},
                { field: 'expand5', hidden:true},
                { field: 'itemStatus', hidden:true},
                { field: 'bbsm', hidden:true},
                { field: 'userGrant', hidden:true},
                { field: 'orgId', hidden:true}
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
    function init_clinic_vs_charge(){
        $("#clinic_vs_charge").datagrid({
            fit : true,
            fitColumns: true, //列自适应宽度
            singleSelect : true,
            columns: [[//显示的列
                {field: 'id', title: '编号', width: 100,hidden:true},
                { field: 'clinicItemClass', title: '类别', width:30},
                { field: 'chargeItemNo', title: '名称', width:80},
                { field: 'chargeItemSpec', title: '规格', width:50},
                { field: 'amount', title: '数量', width:20},
                { field: 'units', title: '单位', width:20},
                { field: 'units', title: '单价', width:20},
                { field: 'backbillRule', title: '划价规则', width:50},
                { field: 'clinicItemCode', hidden:true},
                { field: 'chargeItemClass', hidden:true},
                { field: 'chargeItemCode', hidden:true},
                { field: 'orgId', hidden:true}
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

/************************************************************************************************************************/
                                             /* 数据加载方法 */

    /**
     *  加载数据
     */
    function load_data() {
        var params = {'itemClass':$('#item_class').val()}
        params.itemCode = $(':radio[name="adminFlag"][value="0"]').attr('checked') ? $('#code_filter').val() : ''
        params.inputCode = $(':radio[name="adminFlag"][value="1"]').attr('checked') ? $('#code_filter').val() : ''
        $.postJSON('/service/clinicItem/findList',JSON.stringify(params),function(res){
            $('#clinic_item').datagrid('loadData',res)
            if(res && res.length>0){
                $('#clinic_item').datagrid('selectRow',select_index)
                load_name_data(0)
                load_vs_data(0)
            }
        })
    }
    /**
     * 加载临床诊疗项目所有名称(正/别名)数据
     * @param index  诊疗项目索引行
     */
    function load_name_data(index){
        var row = $('#clinic_item').datagrid('getRows')[index]
        $.postJSON(base_url+'findNameList',JSON.stringify(row),function(res){
            load_name_data1(res)
            row.name_old_list = res
        })
    }
    function load_name_data1(json){
        $('#clinic_item_name').datagrid('loadData',json)
        $('#clinic_item_name').datagrid('selectRow',0)
    }
    /**
     * 加载临床诊疗与价表对照信息
     * @param index 诊疗项目索引行
     */
    function load_vs_data(index){
        var row = $('#clinic_item').datagrid('getRows')[index]
        $.postJSON(base_url+'findVsList',JSON.stringify(row),function(res){
            $('#clinic_vs_charge').datagrid('loadData',res)
            $('#clinic_vs_charge').datagrid('selectRow',0)
            row.vs_old_list = res
        })
    }
/************************************************************************************************************************/

    /**
     * 加载处理名称和对照
     * @param index 需要加载的诊疗项目索引
     */
    function handler_name_and_vs(index){
        var row = $('#clinic_item').datagrid('getRows')[index]
        if(!row.id){
            if(row.saveNameList && row.saveNameList.length > 0)
                $('#clinic_item_name').datagrid('loadData',row.saveNameList)
            if(row.saveVsList && row.saveVsList.length > 0)
                 $('#clinic_vs_charge').datagrid('loadData',row.saveVsList)
        } else {
            if (row.name_old_list) {
                $('#clinic_item_name').datagrid('loadData', row.name_old_list)
            } else {
                load_name_data(index)
            }
            if (row.vs_old_list) {
                $('#clinic_vs_charge').datagrid('loadData', row.vs_old_list)
            } else {
                load_vs_data(index)
            }
        }
    }

    /**
     * 通过点击诊疗项目，暂存上个修改的名称和对照信息
     * @param index 失去选择数据的索引
     */
    function save_name_and_vs(index){
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
     * 保存
     */
    function save_data_handler(){
        save_name_and_vs($('#clinic_item').datagrid('getRowIndex',$('#clinic_item').datagrid('getSelected')))
        // 传输修改的参数
        var item_data_save = []
        item_data_save = item_data_save.concat($('#clinic_item').datagrid('getChanges','inserted'))
        item_data_save = item_data_save.concat($('#clinic_item').datagrid('getChanges','updated'))
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
        item_data_save.push({'id':ids.length>0?ids.substr(1):'','delFlag':'1'})

        var name_save=[] ,name_ids='' ,vs_save=[], vs_ids = ''
        for(var id in name_and_vs_obj){
            var obj = name_and_vs_obj[id]
            name_save = name_save.concat(obj.name_data_add ? obj.name_data_add : [])
            name_save = name_save.concat(obj.name_data_update ? obj.name_data_update : [])
            var name_del_data = obj.name_data_del ? obj.name_data_del : []
            for(var i=0;i<name_del_data;i++){
                name_ids += ','+name_del_data[i].id
            }
            vs_save = vs_save.concat(obj.vs_data_add ? obj.vs_data_add : [])
            vs_save = vs_save.concat(obj.vs_data_update ? obj.vs_data_update : [])
            var vs_del_data = obj.name_data_del ? obj.name_data_del : []
            for(var i=0;i<vs_del_data;i++){
                vs_ids += ','+vs_del_data[i].id
            }
        }
        name_ids = name_ids.length > 0 ? name_ids.substr(1) : name_ids
        vs_ids = vs_ids.length > 0 ? vs_ids.substr(1) : vs_ids
        //诊疗项目的名称和对照修改信息，标志updateFlag=1
        item_data_save.push({updateFlag: '1',saveNameList :name_save,saveVsList:vs_save,delNameIds:name_ids,delVsIds:vs_ids})
        $.postJSON('/service/clinicItem/save',JSON.stringify(item_data_save),function(res){
            if(res.success = '0')
                $.messager.alert('成功',res.msg)

        })
    }

    /**
     * 新增临床诊疗项目
     */
    function init_pro_win(){
        $('#add_pro_win').window({
            title : '新增临床诊疗项目',
            width : '350',
            height : '300',
            resizable : false
        })
    }
    $('#yes_pro_form').click(function(){
        var inputCode = makePy($('#item_name').val())
        var new_name_json = [{'stdIndicator':'1'
            ,'itemName':$('#item_name').val()
            ,'itemCode':$('#item_code').val()
            ,'itemClass':$('#item_class').val()
            ,'inputCode': (inputCode.length > 0 ? inputCode[0] : $('#item_name').val())
            ,'expand3': $(':radio[name="org_type"][value="1"]').attr('checked') ? $('#clinic_org').val() : ''
            ,'inputCodeWb': ''}]
        var pro_obj = {'itemClass':$('#item_class').val()
            ,'itemCode':$('#item_code').val()
            ,'itemName':$('#item_name').val()
            ,'inputCode': (inputCode.length > 0 ? inputCode[0] : $('#item_name').val())
            ,'inputCodeWb': ''
            ,'expand3': $(':radio[name="org_type"][value="1"]').attr('checked') ? $('#clinic_org').val() : ''
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
    /**
     * 删除诊疗项目
     */
    function del_project(){
        select_index = undefined
        var row = $('#clinic_item').datagrid('getSelected')
        if(row){
            $('#clinic_item').datagrid('deleteRow',$('#clinic_item').datagrid('getRowIndex',row))
            $('#clinic_item_name').datagrid('loadData',[])
            $('#clinic_vs_charge').datagrid('loadData',[])
        } else {
            $.messager.alert('警告','请选择一条删除数据！');
        }
    }

    /**
     * 添加别名
     */
    function add_alias(){
        var row = $("#clinic_item").datagrid('getSelected')
        if(row){
            var new_name_json = {'stdIndicator':'2'
                ,'itemName':''
                ,'itemCode':row.itemCode
                ,'itemClass':row.itemClass
                ,'inputCode': row.itemCode
                ,'expand1': row.expand1
                ,'expand2': row.expand2
                ,'expand3': row.expand3
                ,'expand4': row.expand4
                ,'expand5': row.expand5
                ,'inputCodeWb': row.inputCodeWb
                ,'orgId': row.orgId
            }
            $('#clinic_item_name').datagrid('appendRow',new_name_json)
        } else {
            $.messager.alert('警告','请先选择需要添加别名的诊疗项目!')
        }
    }

    /**
     * 删除别名
     */
    function del_alias(){
        var index = $("#clinic_item_name").datagrid('getRowIndex',$("#clinic_item_name").datagrid('getSelected'))
        if(index == 0){
            $.messager.alert('警告','请选择别名！')
        } else {
            $("#clinic_item_name").datagrid('deleteRow',index)
        }
    }
    function add_vs(){
        var row = $("#clinic_item").datagrid('getSelected')
        if(row){
            var vs_json = {
                'clinicItemClass':row.itemClass,
                'clinicItemCode':row.itemCode,
                'chargeItemNo':'1',
                'chargeItemClass':'1',
                'chargeItemCode':'1',
                'chargeItemSpec':'1',
                'amount':'1',
                'units':'1',
                'backbillRule':'1',
                'orgId':'1'
            }
            $('#clinic_vs_charge').datagrid('appendRow',vs_json)
        } else {
            $.messager.alert('警告','请先选择需要添加对照的诊疗项目!')
        }
    }
    function del_vs(){
        var index = $("#clinic_vs_charge").datagrid('getRowIndex',$("#clinic_vs_charge").datagrid('getSelected'))
        $("#clinic_vs_charge").datagrid('deleteRow',index)
    }
})