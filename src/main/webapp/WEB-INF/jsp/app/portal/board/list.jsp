<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- s:container -->
<div class="container">
    <div class="inner">
        <!--
        <div class="videoDetailList_title">
            <p>메뉴 1</p>
            <h4>메뉴 2</h4>
        </div>
        -->

        <form id="frm" name="frm" method="post" onsubmit="return false;">
            <input type="hidden" id="pageIndex" name="pageIndex" value="${not empty param.pageIndex ? param.pageIndex:'1'}"/>
            <div class="table_search_wrap">
                <div class="table_count_area">
                    <p>총 <span id="totalCount">-</span> 건</p>
                    <select name="recordCountPerPage" id="recordCountPerPage">
                        <option value="10">10개</option>
                        <option value="20">20개</option>
                        <option value="30">30개</option>
                        <option value="50">50개</option>
                        <option value="100">100개</option>
                    </select>
                </div>

                <div class="table_search_area">
                    <input type="text" placeholder="검색어를 입력해 주세요." class="W220" id="searchWrd" name="searchWrd" onkeyup="searchKeyPressed(event)">
                    <button type="button" onclick="" class="btn_search">검색</button>
                    <button type="button" onclick="" class="btn_reset">초기화</button>
                </div>
            </div>
        </form>

        <div class="table_area">
            <table class="listTable3">
                <colgroup>
                    <col width="12%">
                    <col width="">
                    <col width="15%">
                    <col width="15%">
                </colgroup>
                <thead>
                <tr>
                    <th>제목</th>
                    <th>내용</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
                </thead>
                <tbody id="boardList"></tbody>
                <script id="boardListTmpl" type="text/x-jquery-tmpl">
                    {{if paginationInfo.totalRecordCount == 0}}
                        <tr>
                            <td colspan="8">데이터가 없습니다.</td>
                        </tr>
                    {{/if}}
                    {{each boardList}}
                        <tr onclick="setPage('${Path}', 'view')">
                            <td>{{= $index + 1}}</td>
                            <td>{{= boardTitle}}</td>
                            <td>{{= boardUser}}</td>
                            <td>{{= boardCreDt}}</td>
                        </tr>
                    {{/each}}
                </script>
            </table>
        </div>
        
        <div class="btn_area">
            <div class="btn_left">

            </div>
            <div class="btn_center pagenation pagination" id="pagination">

            </div>
            <div class="btn_right">
                <button type="button" onclick="setPage('${Path}', 'write')" class="btn02 btn_black">작성</button>
            </div>
        </div>
    </div>
</div>