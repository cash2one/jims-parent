function onloadMethod(status){
    var listView=$("#listView").val();
    if(listView!='1'){
        $('#list_data').datagrid({
            iconCls:'icon-edit',//图标
            width: 'auto',
            height: '86%',
            nowrap: false,
            striped: true,
            border: true,
            collapsible:false,//是否可折叠的
            //fit: true,//自动大小
            method:'GET',
            url:basePath+'/patList/patVistList?status='+status,
            //sortName: 'code',
            //sortOrder: 'desc',
            remoteSort:false,
            idField:'fldId',
            singleSelect:false,//是否单选
            pagination:true,//分页控件
            rownumbers:true,//行号
            columns:[[      //每个列具体内容
                /*{field:'id',title:'病人ID号',width:'10%',align:'center'},*/
                {field:'patientId',title:'病人住院号',width:'10%',align:'center'},
                {field:'inpNo',title:'病人住院号',width:'10%',align:'center'},
                {field:'bedNo',title:'床号',width:'10%',align:'center'},
                {field:'name',title:'病人姓名',width:'10%',align:'center'},
                {field:'sex',title:'性别',width:'10%',align:'center'},
                {field:'age',title:'年龄',width:'10%',align:'center'},
                {field:'chargeType',title:'费别',width:'10%',align:'center'},
                {field:'inpCount',title:'住院天数',width:'10%',align:'center'},
                {field:'attendingDoctor',title:'管床大夫',width:'10%',align:'center'},
                {field:'admissionDateTime',title:'入院时间',width:'10%',align:'center'},
                /*{field:'superiorDoctor',title:'上级医师',width:'10%',align:'center'},*/
                {field:'director',title:'主任医师',width:'10%',align:'center'},
                {field:'dischargeDateTime',title:'出院时间',width:'10%',align:'center'},
                {field:'dataOfbith',title:'出生日期',width:'10%',align:'center'},
                {field:'patIdentity',title:'身份',width:'10%',align:'center'},
                {field:'prepayments',title:'预交金',width:'10%',align:'center'},
                {field:'wardCode',title:'护理单元',width:'10%',align:'center'}
            ]],
            frozenColumns:[[
                {field:'ck',checkbox:true}
            ]],
            toolbar: [{
                text: '新建病历',
                iconCls: 'icon-add',
                handler: function() {
                    newMr();

                    /*document.getElementsByTagName("iframe").src = "/modules/clinic/newMr.html";*/
                }
            }, '-', {
                text: '移入病历',
                iconCls: 'icon-large-smartart',
                handler: function() {
                    moveInMr();
                }
            }, '-',{
                text: '移除病历',
                iconCls: 'icon-remove',
                handler: function(){
                    removeMr();
                }
            }, '-',{
                text: '刷新病人列表',
                iconCls: 'icon-reload',
                handler: function(){
                    fresh();

                }
            }, '-',{
                text: '切换图标',
                iconCls: 'icon-undo',
                handler: function(){
                    $(".datagrid").hide();
                    $("#patientDivId").load("/modules/clinic/patientListView.html",'',function(){
                        loadPatientListView(status,'','','');
                    });

                }
            }],
            onDblClickRow: function (rowIndex, rowData) {
                var patID=rowData.patientId;
                var name=rowData.name;
                var age=rowData.age;
                var sex=rowData.sex;
                var inpNo=rowData.inpNo;
                var bedNo=rowData.bedNo;
                var deptCode=rowData.wardCode;
                $("#patientId",parent.document).val(patID);
                $("#name",parent.document).val(name);
                $("#sex",parent.document).val(sex);
                $("#age",parent.document).val(age);
                $("#inpNo",parent.document).val(inpNo);
                $("#bedNo",parent.document).val(bedNo);
                $("#deptCode",parent.document).val(deptCode);
                parent.addTabs(rowData.name,rowData.name,'/modules/clinic/patientHospital.html');
            },onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
                e.preventDefault(); //阻止浏览器捕获右键事件
                $(this).datagrid("clearSelections"); //取消所有选中项
                $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
                $('#menu').menu('show', {
                    left: e.pageX,//在鼠标点击处显示菜单
                    top: e.pageY
                });
            }
        });
        //设置分页控件
        var p = $('#list_data').datagrid('getPager');
        $(p).pagination({
            pageSize: 10,//每页显示的记录条数，默认为10
            pageList: [5,10,15],//可以设置每页记录条数的列表
            beforePageText: '第',//页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
        });
    } else if(listView=='1'){
        $("#patientDivId").load("/modules/clinic/patientListView.html",'',function(){
            loadPatientListView(status,'','','');
        });
    }
    /**
     * 病历状态 下拉框
     */
    $('#mrStatus').combobox({
        data: mrStatus,
        valueField: 'id',
        textField: 'label'
    })
    $('#doctorRole').combobox({
        data: doctorRole,
        valueField: 'id',
        textField: 'label'
    })

}
/**
 * 条件查询
 */
function searchPatList(){
     var patName=$("#patName").val();
     var startTime=$("#startTime").datebox('getValue');
     var endTime=$("#endTime").datebox('getValue');
     var status=$('#wrap input[name="status"]:checked ').val();
     var listView=$("#listView").val();
     if(listView!='1'){
         $("#list_data").datagrid({queryParams:{"status":status,"patName":patName,"startTime":startTime,"endTime":endTime}});
     }else if(listView=='1'){

         $(".datagrid").hide();
         $("#patientDivId").load("/modules/clinic/patientListView.html",'',function(){
             loadPatientListView(status,patName,startTime,endTime);
         });

     }

}
/**
 * 切换病了列表
 */
function loadTableList(){
    var currTab = parent.$('#tabs-header').tabs('getSelected'); //获得当前tab
    var frameObj=parent.$("iframe",currTab);
    parent.$(frameObj).attr("src", $(frameObj).attr("src"));
}
/**
 * 切换图标
 */
function loadPatientListView(status,patName,startTime,endTime){
    $("#listView").val("1");
    var listHtml='';
    $.get(basePath+'/patList/patVistList?status='+status+'&patName='+patName+'&startTime='+startTime+'&endTime='+endTime,function(data){
        if(data!=null&& data.length>0){
            for(var i=0;i<data.length;i++){
              listHtml+='<div class="col-md-3 list-view" id="viewList" onclick="aa();">\n' +
                        '<div class="iso-thumbnail fd-left">\n'+
                        '<img src="/static/images/index/msex-icon.png" width="34"/>\n'+
                         '<input type="hidden" value="'+data[i].patientId+'" id="patientID">\n'+
                        '</div>\n'+
                        '<div>\n' +
                        '<strong>'+data[i].name+'</strong></br>\n' +
                        '住院号：'+data[i].inpNo+'\n'+
                        '</div>\n'+
                        '<div class="docinf_text cusl-info">'+
                        '<ul>'+
                        '<li>床号： --床</li>'+
                        '<li>年龄：'+data[i].age+'岁</li>'+
                        '<li>病情：<span class="bg-color bg-green">普通</span></li>'+
                        '<li>费别：'+data[i].chargeType+'</li>'+
                        '<li>住院天数：'+data[i].inpCount+'</li>'+
                        '<li>管床大夫：'+data[i].attendingDoctor+'</li>'+
                        '</ul>'+
                        '</div>'+
                        '</div>\n';
            }
            $('#patientList').html(listHtml);
        }
    });
}
/**
 * 新建病历
 */
function newMr(){
    window.location.href="/modules/clinic/newMr.html";
}
/**
 * 移入病历
 */
function moveInMr(){
    $.get(basePath+'/patList/getPatMasterByIn',function(data){
        if(data.length<=0){
            $.messager.alert("提示消息","没有可移入的病人");
        }else{
            window.location.href="/modules/clinic/moveIn.html";
        }
    });

}
var patientId;
//点击事件
function aa(){
    $("#viewList").each(function(){
        patientId=$("#patientID").val();
        $(this).click(function(){
            $(this).css('background','#D8D8D8');
        });
    });
}

/**
 * 移除病历
 */
function removeMr(){
    var listView= $("#listView").val();
    if(listView!='1'){
        var selectRows = $('#list_data').datagrid("getSelected");
        if(selectRows==null ){
            $.messager.alert("提示消息","未选中病人");
            return false;
        }
        patientId=  selectRows.patientId;//病人主索引ID
    }else if(listView=='1'){
        if(patientId==null){
            $.messager.alert("提示消息","未选中病人");
            return false;
        }
    }
    $.post(basePath+'/patList/removeMr?patId='+patientId,function(data){
        if(data.code='1'){
            $.messager.alert("提示消息","移除成功");
            if(listView!='1'){
                $('#list_data').datagrid('load');
            }else if(listView=='1'){
                $("#patientDivId").load("/modules/clinic/patientListView.html",'',function(){
                    loadPatientListView(status,'','','');
                });
            }
        }
    });
}
/**
 * 刷新列表
 */
function fresh(){
    var status=$('#wrap input[name="status"]:checked ').val();
    var listView= $("#listView").val();
    if(listView!='1'){
        $('#list_data').datagrid('load');
    }else if(listView =='1'){
        $(".datagrid").hide();
        $("#patientDivId").load("/modules/clinic/patientListView.html",'',function(){
            loadPatientListView(status,'','','');
        });
    }

}