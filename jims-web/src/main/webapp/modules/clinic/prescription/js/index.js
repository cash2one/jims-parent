function centerRefresh(id,name,url){
    $(window.parent.document).contents().find("#centerIframe")[0].contentWindow.addTabs(id,name,url);
}