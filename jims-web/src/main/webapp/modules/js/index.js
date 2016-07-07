function centerRefresh(id, name, url) {
    $(window.parent.document).contents().find("#centerIframe")[0].contentWindow.addTabs(id, name, url);
}
var str = decodeURI(window.location.search);   //location.search是从当前URL的
if (str.indexOf(name) != -1) {
    var pos_start = str.indexOf(name) + name.length + 1;
    var pos_end = str.indexOf("&", pos_start);
    if (pos_end == -1) {
        var id = str.substring(4,str.lastIndexOf("?") );
        //人员id
        var pid=str.substring(id.length+16);
    }


}
var config = {} ;

config.org_Id = id;
config.persion_Id=pid;
config.operator = 'thinkgem';
config.currentStorage = '1001';
$(function () {
    var orgId = config.org_Id;
    var personId = config.persion_Id;
    var staffId = '';   //员工Id
    //var serviceId = []; //员工对应的多个角色下的所有服务(去掉重复的服务)
    var sysService ;    //名称为系统管理的服务

    //根据机构ID和人员ID查询员工ID
    $.get(basePath + '/orgStaff/find-staff-by-orgId-personId?persionId=' + personId + '&orgId=' + orgId, function (data) {
        staffId = data.id;
    });
    //根据机构ID和人员ID查询该员工在该机构的所有服务
    $.get(basePath + '/org-service/find-selfServiceList-by-orgId-personId?personId=' + personId + '&orgId=' + orgId,function(data){
        for(var i=0;i<data.length;i++){
            if (data[i].serviceName != '系统管理') {
                $("#menu").append("<li><a id='" + data[i].id + "'>" + data[i].serviceName + "</a></li>");
                $("#" + data[i].id).on('click', function () {

                    var iframe = '<iframe width="100%" id="centerIframe" height="99.6%" frameborder="no"  border="0"  ' +
                        'scrolling="yes" src="/modules/sys/template.html?serviceId=' + this.id + '?staffId=' + staffId + '"></iframe>'
                    $("#iframe").html('');
                    $("#iframe").append(iframe);
                });
            }
            if(data[i].serviceName == '系统管理'){
                sysService = {}
                sysService.id = data[i].id;
                sysService.serviceName = data[i].serviceName;
            }
        }
        if(sysService) {
            $("#menu").append("<li><a id='" + sysService.id + "'>" + sysService.serviceName + "</a></li>");
            $("#" + sysService.id).on('click', function () {
                var iframe = '<iframe width="100%" id="centerIframe" height="99.6%" frameborder="no"  border="0"  ' +
                    'scrolling="yes" src="/modules/sys/template.html?serviceId=' + sysService.id + '?staffId=' + staffId + '"></iframe>'
                $("#iframe").html('');
                $("#iframe").append(iframe);
            });
        }
    });
});