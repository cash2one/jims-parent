/**
 * 借物领物
 * @author yangruidong
 * @version 2016-06-25
 */
$(function () {
    var currentOrgId = '1';
    //$.get('/service/asepsisSendRec/findList',{orgId:currentOrgId},function(){
    //    alert()
    //})
    $("#asepsis-borrow-manager").datagrid({
        /*fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,*/

        method: 'GET',
        rownumbers: true,
      //  url: basePath + '/org-role/findAllListByOrgId?orgId=1',

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "单据号",
            field: "roleName",
            width: '5%',
            align: 'center'

        }, {
            title: "序号",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "编码",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "名称",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "规格",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "数量",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "消毒费",
            field: "administrationCode",
            width: '5%',
            align: 'center'
        }, {
            title: "消毒费合计",
            field: "administrationCode",
            width: '10%',
            align: 'right'
        }, {
            title: "辅料费",
            field: "administrationCode",
            width: '5%',
            align: 'left'
        }, {
            title: "消毒日期",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        } , {
            title: "消毒日期",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "借物日期",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "借物人",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        } , {
            title: "说明",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }
        ]]
    });

});




