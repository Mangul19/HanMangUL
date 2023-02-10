function ListOnReady() {
    callAjaxPost("/main/getBoardList", "sucgetBoardList", null, "fail", true);
}

function sucgetBoardList(res) {
    if($("#totalCount").html() != undefined) {
        var jsonData = res.data;
        $("#totalCount").html(jsonData.paginationInfo.totalRecordCount);

        $("#boardList").empty();
        $("#boardListTmpl").tmpl(jsonData).appendTo("#boardList");

        $("#pagination").empty();
        temp = egovPaginationBtn.getPaginationTag(jsonData.paginationInfo, 'getSearchPage');
        $('#pagination').html(temp);
    }
}