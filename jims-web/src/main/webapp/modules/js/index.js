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

alert(id);
alert(pid);
config.org_Id = id;
config.persion_Id=pid;
config.operator = 'thinkgem';
config.currentStorage = '1001';
$(function () {
    //var orgId = parent.config.org_Id;
    var orgId = config.org_Id;
    var personId = config.persion_Id;
    var staffId = '';   //员工Id
    var serviceId = []; //员工对应的多个角色下的所有服务(去掉重复的服务)

    //查询员工信息
    $.get(basePath + '/orgStaff/find-staff-by-orgId-personId?persionId=' + personId + '&orgId=' + orgId, function (data) {
        staffId = data.id;

        //查询员工的服务
        $.get(basePath + '/orgStaff/find-serviceId-by-staffId?staffId=' + staffId, function (data) {
            for (var i = 0; i < data.length; i++) {
                serviceId.push(data[i].serviceId);
            }
            for (var i = 0; i < serviceId.length; i++) {
                serviceId = serviceId.unique2();    //去掉相同的serviceId
                $.get(basePath + '/org-service/find-selfServiceList-by-id?id=' + serviceId[i], function (data) {
                    $("#menu").append("<li><a id='" + data.id + "'>" + data.serviceName + "</a></li>");
                    $("#" + data.id).on('click', function () {

                       var iframe='<iframe width="100%" id="centerIframe" height="99.6%" frameborder="no"  border="0"  ' +
                           'scrolling="yes" src="/modules/sys/template.html?serviceId='+data.id+'?staffId='+staffId+'"></iframe>'

                       $("#iframe").html('');
                        $("#iframe").append(iframe);


                    });
                });
            }
        });
    });

    //数组元素去重
    Array.prototype.unique2 = function () {
        this.sort(); //先排序
        var res = [this[0]];
        for (var i = 1; i < this.length; i++) {
            if (this[i] !== res[res.length - 1]) {
                res.push(this[i]);
            }
        }
        return res;
    }
});