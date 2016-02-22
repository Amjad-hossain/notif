
$(document).ready(function () {

    var tableId = "#users";
    var ajaxUri = "./userList.do";
    var columns = [
        { "data": "userName", bSortable: true },
        { "data": "displayName", bSortable: false },
        { "data": "email", bSortable: false },
        { "data": "lastLogin", bSortable: false },
        { "data": "active", bSortable: false }
    ];

    tableInit(tableId, ajaxUri, columns);

});