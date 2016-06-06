function centerRefresh(id, name, url) {
    $(window.parent.document).contents().find("#centerIframe")[0].contentWindow.addTabs(id, name, url);
}

$(function () {
    /**
     * 服务管理
     */
    $("#serviceMgr").on("click", function () {
        $(this).parent().siblings().removeClass('active');
        $('#centerIframe').attr('src','/modules/sys/sys-service.html');
        $(this).parent().addClass('active')
    });
    /**
     * 菜单管理
     */
    $("#menuMgr").on("click", function () {
        $(this).parent().siblings().removeClass('active');
        $('#centerIframe').attr('src','/modules/sys/menuDict.html');
        $(this).parent().addClass('active')
    });
    /**
     * 机构审核
     */
    $("#companyCheckMgr").on("click", function () {
        $(this).parent().siblings().removeClass('active');
        $('#centerIframe').attr('src','/modules/sys/sys-company.html');
        $(this).parent().addClass('active')
    });
    /**
     * 字典管理
     */
    $("#dictMgr").on("click", function () {
        $(this).parent().siblings().removeClass('active');
        $('#centerIframe').attr('src','/modules/sys/public-dict.html');
        $(this).parent().addClass('active')
    });
    /**
     * 退出
     */
    $("#exit").on("click", function () {
       location.href="/modules/sys/login.html";
    });
});