<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@globalsProperties['Globals.javascript.version']" var="jsVersion"/>
<spring:eval expression="@globalsProperties['Global.juso.search']" var="jusoSearchApi"/>

        <!-- ---------------------------------------  popup  ----------------------------------------------------------------------------------------------------------------------------- -->
        <div class="popup" style="display: none">
            <div class="popupBG">

                <!-- ----------------------- 주소 검색 -------------------- -->
                <div class="popupStyle3 W800" style="display:none;" id="popupAddr">

                    <div class="popupStyle3_head">
                        <h1>주소검색</h1>
                        <button type="button" onclick="closePopupAddr();"><img src="${pageContext.request.contextPath}/images/ico_popupClose.png"></button>
                    </div>

                    <form name="addrSearchform" id="addrSearchform" method="post" onsubmit="return false;">
                        <input type="hidden" id="currentPage" name="currentPage" value="1"/> <!-- 요청 변수 설정 (현재 페이지. currentPage : n > 0) -->
                        <input type="hidden" name="countPerPage" value="10"/><!-- 요청 변수 설정 (페이지당 출력 개수. countPerPage 범위 : 0 < n <= 100) -->
                        <input type="hidden" name="resultType" value="json"/> <!-- 요청 변수 설정 (검색결과형식 설정, json) -->
                        <input type="hidden" name="confmKey" value="${jusoSearchApi }"/><!-- 요청 변수 설정 (승인키) -->

                    <div class="popupStyle3_body">
                        <div class="addressSearch MgT20">
                            <input type="text" class="W300" style="background-color: #fff !important;" id="addrKeyword" name="keyword" value="" onkeydown="enterSearch();">
                            <button type="button" class="btn13 btn_blue" onClick="getAddr();">검색</button>
                        </div>
                        <div class="table_wrap">
                            <div class="table_top">
                                <div class="table_count">
                                    <p id="addrTotal">총<b>0</b>건</p>
                                </div>
                            </div>

                            <div class="tebla_area">
                                <table class="listTable1">
                                    <colgroup>
                                        <col width="8%">
                                        <col width="">
                                        <col width="12%">
                                        <col width="12%">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>도로명 주소</th>
                                            <th>우편번호</th>
                                            <th>선택</th>
                                        </tr>
                                    </thead>
                                    <tbody id="addrList">
                                        <tr>
                                            <td colspan="4">데이터가 없습니다.</td>
                                        </tr>

                                    </tbody>
                                </table>
                            </div>

                            <div class="table_bottom">
                                <div class="table_bot_left"></div>
                                <div class="table_bot_center pagenation" id="pageApi"></div>
                                <div class="table_bot_right">
                                </div>
                            </div>
                        </div>
                    </div>
                    </form>
                </div>
                <!-- ---------------------------------------------------------- -->

                <!-- -------------------------------------------------  영상공유  ---------------------------------------------------- -->
                <div class="popupStyle1 maxW900" id="VodPopup" style="display: none;">
                    <form id="vdoInsher" name="vdoInsher" method="post" onsubmit="return false;">
                        <input type="hidden" id="PopvdoToken" name="vdoToken"/>
                        <input type="hidden" id="emgYn" name="emgYn"/>
                        <input type="hidden" id="pstnShrdLmtYn" name="pstnShrdLmtYn"/>
                        <input type="hidden" id="vdoLatLotYn" name="vdoLatLotYn"/>
                        <input type="hidden" id="shrdVdoAddr" name="shrdVdoAddr"/>

                        <div class="popupStyle1_head">
                            <h1>영상공유</h1>
                            <button type="button" onclick="ClosePopShrng()"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                        </div>

                        <div class="popupStyle1_body">
                            <div class="popupTable_area">
                                <table class="popupConTable1">
                                    <colgroup>
                                        <col width="70px">
                                        <col width="">
                                    </colgroup>
                                    <tr>
                                        <th>현장제목</th>
                                        <td>
                                            <select id="vdosttnNtcSn" name="sttnNtcSn" class="W100p">
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>영상조회<br>허용기간</th>
                                        <td>
                                            <input id="vdoTime" name="shrdHrCnt" type="text" class="W10p" value="6" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"> 시간
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>영상제목</th>
                                        <td><input id="titleVdo" type="text" class="W100p readonly" readonly></td>
                                    </tr>
                                    <tr>
                                        <th>촬영위치</th>
                                        <td>
                                            <div class="flex-align-c gap4">
                                                <input type="text" id="titleLat" class="W200 readonly" name="shrdVdoLat" readonly >
                                                <input type="text" id="titleLot" class="W200 readonly" name="shrdVdoLot" readonly>
                                                <input type="checkbox" id="pstnShrdLmt"><label for="pstnShrdLmt" class="MgL20">공유제한</label>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>공유<br>요청자</th>
                                        <td>
                                            <div class="flex-align-c gap4">
                                                <input type="text" id="InDepart" class="W390 readonly" readonly value="${loginVO.userDeptNm}">
                                                <input type="text" id="InName" class="W340 readonly" readonly value="${loginVO.userNm}">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </form>

                        <div class="table_coment">
                            <p>1) 사용자의 소속기관에서 전파(=등록)된 상황의 현장제목 리스트가 표시됩니다.</p>
                            <p>2) 영상의 메타데이터 중 제공이 어려운 경우 선택 시 해당 정보는 제외하고 공유됩니다.</p>
                        </div>

                        <div class="popupBtn_area">
                            <div class="btn_left"></div>
                            <div class="btn_right">
                                <button type="button" onclick="SherngVdo('Y')" class="btn03 btn_blue imgBtn"><img src="${pageContext.request.contextPath}/images/btn_siren.png">긴급공유</button>
                                <button type="button" onclick="SherngVdo('N')" class="btn03 btn_navy">일반공유</button>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->



                <!-- -------------------------------------------------  상황전파  ---------------------------------------------------- -->
                <div class="popupStyle1 maxW950" id="situationRegistPopup" style="display: none;">
                <input type="hidden" id="urlInfoTemp" value="/msituationprop/situationPropList"/>
                <form id="situationFrm" name="situationFrm" method="post"  enctype="multipart/form-data">
                <input type="hidden" id="emrgYn" name="emrgYn" value=""/>
                <input type="hidden" id="sttnNtcSnPopup" name="sttnNtcSn" value="" >
                <input type="hidden" id="urlInfo" name="urlInfo"/>
                <input type="hidden" id="moveInfo" name="moveInfo">
                <input type="hidden" id="popupIncd" name="popupIncd">
                <input type="hidden" id="poupRegDt" name="regDtTemp"/>
                
                    <div class="popupStyle1_head MgB3">
                        <h1>상황전파</h1>
                        <button type="button" onclick="situationRegistClose();"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                    </div>

                    <div class="popupStyle1_body">
                        
                        <div class="table_title MgB3">
                            <h3></h3>
                            <span>* 필수입력</span>
                        </div>
                        <div class="popupTable_area">
                            <table class="popupConTable1">
                                <colgroup>
                                    <col width="70px">
                                    <col width="190px">
                                    <col width="70px">
                                    <col width="230px">
                                    <col width="100px">
                                    <col width="">
                                </colgroup>
                                <tr>
                                    <th class="red_dot">현장제목</th>
                                    <td colspan="5"><input type="text" class="W100p" placeholder="50자 이내로 현장제목을 입력해주세요." maxlength="50" id="sttnNtcTtl" name="sttnNtcTtl"></td>
                                </tr>
                                <tr>
                                    <th class="red_dot">현장구분</th>
                                    <td>
                                        <select class="W140" id="sttnNtcSeCd" name="sttnNtcSeCd">
                                             <option value="SNC001">자연재난</option>
                                            <option value="SNC002">사회재난</option>
                                            <option value="SNC003">생활안전</option>
                                        </select>
                                    </td>
                                    <th>전파기관</th>
                                    <td>
                                    <input type="hidden" id="ntcInstCd" name="ntcInstCd" readonly value="${loginVO.userInstCd}">
                                    <input type="text" class="W200 readonly" id="ntcInstCdNm" name="ntcInstCdNm" readonly value="${loginVO.userInstNm}">
                                    </td>
                                    <th>전파기관 담당자</th>
                                    <td><input type="hidden" class="W200 readonly" id="ntcInstPicSn" name="ntcInstPicSn" readonly value="${loginVO.userSn}">
                                    <input type="text" class="W200 readonly" id="ntcInstPicSnNm" name="ntcInstPicSnNm" readonly value="${loginVO.userNm}">
                                    </td>
                                </tr>
                                <tr>
                                    <th>현장내용</th>
                                    <td colspan="5"><textarea class="W100p" style="min-height: 88px;" placeholder="300자 이내로 입력해 주세요." maxlength="300" id="sttnNtcCn" name="sttnNtcCn"></textarea></td>
                                </tr>
                                <tr>
                                    <th>공유<br>현장영상</th>
                                    <td colspan="5">
                                        <div class="popupSearchVideo">
                                            <button type="button" onclick="moveSeach()" class="btn07 btn_navy2">영상검색</button>
                                            <div class="popupSearchVideo_box">
                                                <table class="listTable5">
                                                    <colgroup>
                                                        <col width="60px">
                                                        <col width="">
                                                        <col width="150px">
                                                        <col width="80px">
                                                        <col width="92px">
                                                    </colgroup>
                                                    <thead>
                                                        <tr>
                                                            <th>번호</th>
                                                            <th>영상제목</th>
                                                            <th>영상조회 허용시간</th>
                                                            <th>공유제한</th>
                                                            <th>관리</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id ="situationMovieArea">
                                                        <tr>
                                                            <td colspan="5">선택된 영상이 없습니다.</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th>협력기관</th>
                                    <td colspan="5">
                                        <div class="checkbox_wrap">
                                            <button type="button" onclick="openFavoritePopup();" class="btn07 btn_navy2">즐겨찾기</button>
                                            <div class="checkbox_area">
                                                <input type="checkbox" id="coopOrgAll" name="coopOrgAll" onclick="checkAllCoop('coopOrg')"><label for="coopOrgAll">전체</label>
                                                <input type="checkbox" name="coopOrg" id="cb2-1" value="INC001" onclick="modifyEachCoop('coopOrg', 'coopOrgAll', '${loginVO.userInstCd}')"><label for="cb2-1">경찰청</label>
                                                <input type="checkbox" name="coopOrg" id="cb2-2" value="INC002" onclick="modifyEachCoop('coopOrg', 'coopOrgAll', '${loginVO.userInstCd}')"><label for="cb2-2">산림청</label>
                                                <input type="checkbox" name="coopOrg" id="cb2-3" value="INC003" onclick="modifyEachCoop('coopOrg', 'coopOrgAll', '${loginVO.userInstCd}')"><label for="cb2-3">소방청</label>
                                                <input type="checkbox" name="coopOrg" id="cb2-4" value="INC004" onclick="modifyEachCoop('coopOrg', 'coopOrgAll', '${loginVO.userInstCd}')"><label for="cb2-4">해양경찰청</label>
                                                <input type="checkbox" name="coopOrg" id="cb2-5" value="INC005" onclick="modifyEachCoop('coopOrg', 'coopOrgAll', '${loginVO.userInstCd}')"><label for="cb2-5">행정안전부</label>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="red_dot">현장위치<br>(위/경도)</th>
                                    <td colspan="5">
                                        <div class="address">
                                            <button type="button" onclick="searchAdress()" class="btn07 btn_navy2">주소검색</button>
                                            <input type="text" class="W88 readonly" id="sttnNtcZip" name="sttnNtcZip" placeholder="우편번호" readonly>
                                            <input type="text" class="W310 readonly" id="sttnNtcAddr" name="sttnNtcAddr" placeholder="기본주소" readonly>
                                            <input type="text" class="W100p addressDetail" maxlength="150" id="sttnNtcDaddr" name="sttnNtcDaddr" placeholder="상세주소">
                                        </div>
                                        <div class="MgT10">
                                            <input type="text" class="W200 readonly" id="sttnNtcLat" name="sttnNtcLat" placeholder="위도" readonly>
                                            <input type="text" class="W200 readonly" id="sttnNtcLot" name="sttnNtcLot" placeholder="경도" readonly>
                                        </div>
                                        <div class="mapArea MgT10" style="width: 100%; height: 200px; background: #aaa;" id="situationMap"></div>
                                    </td>
                                </tr>
                                <tr id="situationRoomYNArea">
                                    <th>영상회의<br>개설여부</th>
                                    <td colspan="5">
                                        <div class="radio_wrap">
                                            <div class="radio_area">
                                                <input type="radio" id="situationRoomY" name="situationRoom"><label for="situationRoomY" class="radio_blue">개설</label>
                                                <input type="radio" id="situationRoomN" name="situationRoom" checked><label for="situationRoomN" class="radio_blue">미개설</label>
                                            </div>
                                            <span>* 선택에 따라 상화전파와 동시에 영상회의를 개설합니다.</span>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>


                        <div class="popupBtn_area">
                            <div class="btn_left"></div>
                            <div class="btn_right" id="registButton">
                                <button type="button" class="btn03 btn_blue imgBtn" id="emrgYbutton" onclick="emrg('Y')"><img src="${pageContext.request.contextPath}/images/btn_siren.png">긴급전파</button>
                                <button type="button" class="btn03 btn_navy" id="emrgNbutton" onclick="emrg('N')">일반전파</button>
                            </div>
                            <div class="btn_right" id="modifyButton" style="display:none;">
                                <button type="button" class="btn03 btn_blue imgBtn" onclick="modifySave()">저장</button>
                            </div>
                        </div>

                    </div>
                    <div class="popup_underPopup" style="display: none;"></div>
                    </form>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->
                
                
                <!-- -------------------------------------------------  협업기관 즐겨찾기  ---------------------------------------------------- -->
                <div class="popupStyle1 maxW950" id="favoritePopup" style="display: none;">
                    <form id="bookmarkFrm" name="bookmarkFrm" method="post" onsubmit="return false;"  enctype="multipart/form-data">
                        <input type="hidden" id="bookmarkPageIndex" name="pageIndex" value="1"/>
                        <input type="hidden" id="bookmarkIndex" name="fvrtsSn"/>
                        <input type="hidden" id="fNtcInstCdArr" />
                    <div class="popupStyle1_head">
                        <h1>협업기관 즐겨찾기</h1>
                        <button type="button" onclick="closeFavoritePopup();"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                    </div>
                    
                    <div class="popupStyle1_body">
                        <div class="popupTitle_area">
                            <h3>즐겨찾기 설정</h3>
                            <span>*필수입력</span>
                        </div>
                        <div class="popup_bgWhite">
                            <div class="popupTable_area">
                                <table class="popupConTable1">
                                    <colgroup>
                                        <col width="100px">
                                        <col width="">
                                    </colgroup>
                                    <tr>
                                        <th class="red_dot">즐겨찾기 이름</th>
                                        <td><input type="text" id="fvrtsNm" name="fvrtsNm" class="W100p" maxlength="50" placeholder="50자 이내로 입력해 주세요."></td>
                                    </tr>
                                    <tr>
                                        <th class="red_dot">협업기관</th>
                                        <td>
                                            <div class="checkbox_area">
                                                <input type="hidden" id="fNtcInstCd" value="${loginVO.userInstCd}">
                                                <input type="checkbox" id="fCoopAll" name="fCoopAll" onclick="checkAllCoop('coopInstCd')"><label for="fCoopAll">전체</label>
                                                <input type="checkbox" id="cb_bookmark2" name="coopInstCd" value="INC001" onclick="modifyEachCoop('coopInstCd', 'fCoopAll', '${loginVO.userInstCd}')"><label for="cb_bookmark2">경찰청</label>
                                                <input type="checkbox" id="cb_bookmark6" name="coopInstCd" value="INC002" onclick="modifyEachCoop('coopInstCd', 'fCoopAll', '${loginVO.userInstCd}')"><label for="cb_bookmark6">산림청</label>
                                                <input type="checkbox" id="cb_bookmark3" name="coopInstCd" value="INC003" onclick="modifyEachCoop('coopInstCd', 'fCoopAll', '${loginVO.userInstCd}')"><label for="cb_bookmark3">소방청</label>
                                                <input type="checkbox" id="cb_bookmark4" name="coopInstCd" value="INC004" onclick="modifyEachCoop('coopInstCd', 'fCoopAll', '${loginVO.userInstCd}')"><label for="cb_bookmark4">해양경찰청</label>
                                                <input type="checkbox" id="cb_bookmark5" name="coopInstCd" value="INC005" onclick="modifyEachCoop('coopInstCd', 'fCoopAll', '${loginVO.userInstCd}')"><label for="cb_bookmark5">행정안전부</label>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="popupBtn_area MgB25">
                            <div class="btn_left"></div>
                            <div class="btn_right">
                                <button type="button" class="btn03 btn_blue" onclick="saveBookmark()">저장</button>
                            </div>
                        </div>
                        
                        <div class="popupTitle_area popup_search_area">
                            <h3>협업기관 즐겨찾기</h3>
                            <div class="search_area">
                                <input type="text" class="W260" id="fSearchWrd" name="searchWrd" onkeydown="enterBookmarkSearch();">
                                <button type="button" class="btn_black2" onclick="getSearchBookmarkPage(1)">검색</button>
                            </div>
                        </div>
                        <div class="popupTable_area">
                            <table class="popupListTable1">
                                <colgroup>
                                    <col width="50px">
                                    <col width="">
                                    <col width="220px">
                                    <col width="216px">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>번호</th>
                                        <th>즐겨찾기 이름</th>
                                        <th>협업기관</th>
                                        <th>관리</th>
                                    </tr>
                                </thead>
                                <tbody id="bookmarkList"></tbody>
                                <script id="bookmarkListTmpl" type="text/x-jquery-tmpl">
                                    {{each list}}
                                        <tr>
                                            <td class="num">{{= (paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + $index))}}</td>
                                            <td>{{= fvrtsNm}}</td>
                                            <td>{{= instNm}}</td>
                                            <td>
                                                <div class="listBtn_box">
                                                    <button type="button" class="btn08 btn_blue" onclick="selectPopupBookmark('{{= instCd}}')">선택</button>
                                                    <button type="button" class="btn08 btn_navy" onclick="updateBookmark('{{= fvrtsSn}}', '{{= fvrtsNm}}', '{{= instCd}}')">수정</button>
                                                    <button type="button" class="btn08 btn_red_line" onclick="delBookmark('{{= fvrtsSn}}')">삭제</button>
                                                </div>
                                            </td>
                                        </tr>
                                    {{/each}}
                                </script>
                            </table>
                        </div>
                        <div class="btn_area MgT20">
                            <div class="btn_left"></div>
                            <div class="pagenation" id="bookmarkPage">
                                <button type="button" class="btn_arrow"><img src="${pageContext.request.contextPath}/images/ico_agoprev.png"></button>
                                <button type="button" class="btn_arrow"><img src="${pageContext.request.contextPath}/images/ico_prev.png"></button>
                                <button type="button" class="on">1</button>
                                <button type="button">2</button>
                                <button type="button">3</button>
                                <button type="button">4</button>
                                <button type="button">5</button>
                                <button type="button" class="btn_arrow"><img src="${pageContext.request.contextPath}/images/ico_next.png"></button>
                                <button type="button" class="btn_arrow"><img src="${pageContext.request.contextPath}/images/ico_agonext.png"></button>
                            </div>
                            <div class="btn_right"></div>
                        </div>
                    </div>
                    </form>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->

                <!-- -------------------------------------------------  영상검색  ---------------------------------------------------- -->
                <div class="popupStyle1 maxW1230" id="movieSearchPopup" style="display: none;">
                <form id="movieSearchFrm" name="movieSearchFrm" method="post"  enctype="multipart/form-data" onsubmit="return false;">
                <input type="hidden" id="moviePageIndex" name="pageIndex" value="1"/>
                    <div class="popupStyle1_head MgB10">
                        <h1>영상검색</h1>
                        <button type="button" onclick="moveSearchClose();"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                    </div>

                    <div class="popupStyle1_body">
                        
                        <div class="popup_search_area">
                            <select class="W100" name="order" id="order">
                                <option value="1">최신순</option>
                                <option value="2">제목순</option>
                            </select>
                            <div class="search_area">
                                <select class="W100" name="searchCnd1" id="movieSearchCnd1">
                                    <option value="0">전체</option>
                                    <option value="1">제목</option>
                                </select>
                                <input type="text" class="W260" name="searchWrd" id="movieSearchWrd" onkeyup="searchKeyPressedMovie(event);">
                                <button type="button" onclick="moveGetSearchPage(1)" class="btn_black2" >검색</button>
                            </div>
                        </div>
                        
                        <div class="videoSearchList_wrap">
                        <div id="movieSearchList"></div>
                        <script id="movieSearchListTmpl" type="text/x-jquery-tmpl">
                        {{each list}}
                        <div class="videoSearchList_box">
                            <div class="videoSearchList_img">
                                <input type="checkbox" id="videoCheck{{= vdoToken}}" onclick="chekboxMove(this)" 
                                    data-vdotoken="{{= vdoToken}}" data-imgutladdr="{{= imgUtlAddr}}" data-vdottl="{{= vdoTtl}}" data-vdoextnnm="{{= vdoExtnNm}}" data-vdolat="{{= vdoLat}}" data-vdolot="{{= vdoLot}}">
                                {{if imgUtlAddr}}
                                    <label for="videoCheck{{= vdoToken}}" class="cb_wh"><img src="${pageContext.request.contextPath}{{= imgUtlAddr}}"></label>
                                {{else}}
                                    <label for="videoCheck{{= vdoToken}}" class="cb_wh"><img src="${pageContext.request.contextPath}/images/testImg.png"></label>
                                {{/if}}
                                {{if vdoPlayTime}}
                                    <div class="time"><p>{{= vdoPlayTime}}</p></div>
                                {{else}}
                                    
                                {{/if}}
                            </div>
                            <h4 class="text_cut2">{{= vdoTtl}}
                                {{if vdoExtnNm}}
                                     .{{= vdoExtnNm}}
                                {{else}}
                                     
                                {{/if}}
                            </h4>
                            <div class="videoSearchList_info">
                                {{if vdoSz}}
                                    <p>{{= vdoSz}}MB</p>
                                    <span></span>
                                {{else}}
                                     
                                {{/if}}
                                <p>{{= regDt}}</p>
                            </div>
                        </div>
                        {{/each}}
                        </script>
                        </div>

                        <div class="btn_area MgT25">
                            <div class="btn_left"></div>
                            <div class="pagenation" id="movePagenation">
                            </div>
                            <div class="btn_right"></div>
                        </div>

                        <div class="videoSearchResult_wrap" id="movieSearchResult">
                           
                        </div>

                        <div class="popupBtn_area MgT25">
                            <div class="btn_left"></div>
                            <div class="btn_right">
                                <button type="button" class="btn03 btn_blue" onclick="insertMovie();">영상추가</button>
                            </div>
                        </div>

                    </div>
                    </form>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->


                <!-- -------------------------------------------------  회의개설  ---------------------------------------------------- -->
                <div class="popupStyle1 maxW900" id="meetingPop" style="display: none;">
                    <div class="popupStyle1_head">
                        <h1>회의개설</h1>
                        <button type="button" onclick="closeMeeting()"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                    </div>

                    <div class="popupStyle1_body">
                        
                        <div class="popupTable_area">
                            <table class="popupConTable1">
                                <colgroup>
                                    <col width="70px">
                                    <col width="">
                                </colgroup>
                                <tr>
                                    <th>회의제목</th>
                                    <td><input type="text" class="W100p" placeholder="50자 이내로 입력해 주세요."></td>
                                </tr>
                                <tr>
                                    <th>참여<br>협업기관</th>
                                    <td>
                                        <div class="checkbox_area">
                                            <input type="checkbox" id="cb3" data-instcd="" onclick="meetingCoopCkbClick(this)"><label for="cb3">전체</label>
                                            <c:forEach var="code" items="${incList}" varStatus="status">
                                                <c:if test="${loginVO.userInstCd ne code.totalCd}">
                                                    <input type="checkbox" id="cb3-${status.count}" data-instcd="${code.totalCd}" onclick="meetingCoopCkbClick(this)"><label for="cb3-${status.count}">${code.cdNm}</label>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <div class="table_coment">
                            <p>1) 선택된 협업기관의 책임자로 설정된 사용자에게 회의실 접근을 허용</p>
                        </div>

                        <div class="popupBtn_area">
                            <div class="btn_left"></div>
                            <div class="btn_right">
                                <button type="button" class="btn03 btn_blue" onclick="meetingIn()">등록</button>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->


                <!-- -------------------------------------------------  회의참여  ---------------------------------------------------- -->
                <div class="popupStyle1 maxW900" id="meetingJoinPop" style="display: none;">
                    <div class="popupStyle1_head">
                        <h1>회의참여</h1>
                        <button type="button" onclick="closeMeetingJoin()"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                    </div>

                    <div class="popupStyle1_body">
                        
                        <div class="popupTable_area">
                            <table class="popupConTable1">
                                <colgroup>
                                    <col width="70px">
                                    <col width="">
                                </colgroup>
                                <tr>
                                    <th>회의제목</th>
                                    <td>2022년 재난현장 구급대응훈련 일정공유 및 사전회의 개최 알림</td>
                                </tr>
                                <tr>
                                    <th>접속코드</th>
                                    <td><input type="text" class="W100p"></td>
                                </tr>
                            </table>
                        </div>

                        <div class="popupBtn_area">
                            <div class="btn_left"></div>
                            <div class="btn_right">
                                <button type="button" class="btn03 btn_blue" onclick="btnMeetingJoin()">참여</button>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->


                <!-- -------------------------------------------------  사용자 검색  ---------------------------------------------------- -->
                <div class="popupStyle1 maxW900" id="authSitRoom" style="display: none;">
                    <div class="popupStyle1_head">
                        <h1>사용자 검색</h1>
                        <button type="button" onclick="authSitRoomClosePop()"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                    </div>

                    <div class="popupStyle1_body">
                        <form id="authSitRoomPopForm" name="authSitRoomPopForm" method="post" onsubmit="return false;">
                            <div class="popup_search_area">
                                <input type="hidden" id="sttnSn" name="sttnNtcSn" value="${situationInfo.sttnNtcSn}"/>
                                <input type="hidden" id="SituationRoomPageIndex" name="pageIndex" value="1"/>
                                <span></span>
                                <div class="search_area">
                                    <select class="W100" id="SituationRoomsearchCnd1" name="searchCnd1">
                                        <option value="">전체</option>
                                        <option value="name">이름</option>
                                        <option value="depart">부서</option>
                                        <option value="tel">전화번호</option>
                                    </select>
                                    <input type="text" id="SituationRoomSearchWrd" name="searchWrd" class="W260" onkeyup="if(window.event.keyCode==13){GetAuthList(1)}">
                                    <button type="button" onclick="GetAuthList(1)" class="btn_black2">검색</button>
                                    <button type="button" onclick="authSitRoomSrchReset()" class="btn_black2">초기화</button>
                                </div>
                            </div>
                        </form>

                        <div class="popupTable_area">
                            <table class="popupListTable1">
                                <colgroup>
                                    <col width="70px">
                                    <col width="140px">
                                    <col width="160px">
                                    <col width="120px">
                                    <col width="">
                                    <col width="100px">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>번호</th>
                                        <th>기관</th>
                                        <th>부서</th>
                                        <th>이름</th>
                                        <th>전화번호</th>
                                        <th>선택</th>
                                    </tr>
                                </thead>
                                <tbody id="sitRoomAuthList"></tbody>
                                <script id="sitRoomAuthListTmpl" type="text/x-jquery-tmpl">
                                    {{each authSitList}}
                                        <tr>
                                            <td>{{= (paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + $index))}}</td>
                                            <td>{{= cdNm}}</td>
                                            <td>{{= userDeptNm}}</td>
                                            <td>{{= userNm}}</td>
                                            <td>{{= userTelNo}}</td>
                                            {{if sttnAuthrtSn}}
                                                <td><button type="button" onclick="RemoveSitRoomAuth({{= sttnAuthrtSn}})" class="btn08 btn_grey2">추가됨</button></td>
                                            {{else}}
                                                <td><button type="button" onclick="AddSitRoomAuth({{= userSn}})" class="btn08 btn_blue">선택</button></td>
                                            {{/if}}
                                        </tr>
                                    {{/each}}
                               </script>
                            </table>
                        </div>

                        <div class="btn_area MgT20">
                            <div class="btn_left"></div>
                            <div class="pagenation" id="pagenationSituationRoom">
                            </div>
                            <div class="btn_right"></div>
                        </div>

                    </div>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->

                <!-- -------------------------------------------------  녹화영상 및 회의록  --------------------------------------------------------- -->
                <div class="popupStyle1 maxW900" id="meetingReportPop" style="display: none;">
                    <div class="popupStyle1_head">
                        <h1>녹화영상 및 회의록</h1>
                        <button type="button" onclick="closeMeetingReport()"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                    </div>

                    <div class="popupStyle1_body">
                        
                        <div class="popupCategory">
                            <button type="button" onclick="OnMeetingReportChOn(this)" class="on">녹화영상</button>
                            <button type="button" onclick="OnMeetingReportChOn(this)">회의록</button>
                        </div>

                        <div class="popupTable_area">
                            <table class="popupListTable1">
                                <colgroup></colgroup>
                                <script id="meetingReportVdoColgroupTmpl" type="text/x-jquery-tmpl">
                                    <col width="60px">
                                    <col width="">
                                    <col width="100px">
                                    <col width="140px">
                                </script>
                                <script id="meetingReportNoteColgroupTmpl" type="text/x-jquery-tmpl">
                                    <col width="60px">
                                    <col width="">
                                    <col width="140px">
                                </script>
                                <thead></thead>
                                <script id="meetingReportVdoTheadTmpl" type="text/x-jquery-tmpl">
                                    <tr>
                                        <th>번호</th>
                                        <th>녹화파일명</th>
                                        <th>다운로드</br>가능여부</th>
                                        <th>관리</th>
                                    </tr>
                                </script>
                                <script id="meetingReportNoteTheadTmpl" type="text/x-jquery-tmpl">
                                    <tr>
                                        <th>번호</th>
                                        <th>회의록제목</th>
                                        <th>관리</th>
                                    </tr>
                                </script>
                                <tbody id="meetingReportPopList"></tbody>
                                <script id="meetingVdoReportPopListTmpl" type="text/x-jquery-tmpl">
                                    {{each recordFiles}}
                                        <tr>
                                            <td class="num">{{= (paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + $index))}}</td>
                                            <td>녹화영상{{= (paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + $index))}}</td>
                                            {{if downloadable}}
                                                <td><b class="fc-blue">Y</b></td>
                                                <td><button type="button" onclick="videoDownReq(this);" class="btn11 btn_grey2" data-fileid="{{= recordedFileId}}">다운로드 요청</button></td>
                                            {{else}}
                                                <td><b>N</b></td>
                                                <td><b>-</b></td>
                                            {{/if}}
                                        </tr>
                                    {{/each}}
                                </script>
                                <!--
                                            <td><button type="button" onclick="" class="btn11 btn_navy">다운로드 준비</button></td>
                                            <td><button type="button" onclick="" class="btn11 btn_blue">다운로드</button></td>
                                -->
                                <script id="meetingNoteReportPopListTmpl" type="text/x-jquery-tmpl">
                                    <tr>
                                        <td class="num">2</td>
                                        <td>{{= mtgNm}} A.I.회의록</td>
                                        <td><button type="button" onclick="downMeetingReportAiNote();" class="btn11 btn_blue">다운로드</button></td>
                                    </tr>
                                    <tr>
                                        <td class="num">1</td>
                                        <td>{{= mtgNm}} 회의록</td>
                                        <td><button type="button" onclick="downMeetingReportNote();" class="btn11 btn_blue">다운로드</button></td>
                                    </tr>
                                </script>
                            </table>
                        </div>

                        <div class="btn_area MgT20">
                            <div class="btn_left"></div>
                            <div class="pagenation" id="pagenationMeetingReportPop"></div>
                            <div class="btn_right"></div>
                        </div>

                    </div>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->
                
                
                
                <!-- -------------------------------------------------  영상공유 승인  ---------------------------------------------------- -->
                <div class="popupStyle1 maxW720"  id="playHrCntSetPopup" style="display: none;">
                    <form id="playHrCntSetPopupFrm" name="playHrCntSetPopupFrm" method="post" onsubmit="return false;">
                    <div class="popupStyle1_head">
                        <h1>영상공유 승인</h1>
                        <button type="button" onclick="playHrCntSetPopupClose()"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                    </div>

                    <div class="popupStyle1_body">
                        
                        <div class="popupTable_area">
                            <table class="popupConTable1">
                                <colgroup>
                                    <col width="100px">
                                    <col width="">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>조회허용시간</th>
                                        <td><input type="text" class="W200" id="popupPlayHrCnt" oninput="this.value = onlyNumber(this.value);">&nbsp;시간</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="radio_wrap MgT8">
                               <span>* 선택된 값에 따라 승인 일시 기준으로 허용시간 내에서만 협업기관에서 조회할 수 있습니다.</span>
                           </div>
                        
                        <div class="popupBtn_area">
                            <div class="btn_left"></div>
                            <div class="btn_right">
                                <button type="button" class="btn03 btn_blue" onclick="savePoupPlayhrCnt()">저장</button>
                            </div>
                        </div>

                    </div>
                    </form>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->
                
                
                
                <!-- -------------------------------------------------  영상재생팝업  ---------------------------------------------------- -->
                <div class="popupStyle2 popupWauto" style="display: none;" id="playMoviePopup">
                <form id="playMoviePopupFrm" name="playMoviePopupFrm" method="post" onsubmit="return false;">
                <input type="hidden" id="vdoTokenPopup">
                    <div class="popupStyle2_head">
                        <h1 id="playTtl">영상제목</h1>
                        <button type="button" onclick="playMoivePopupClose()"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                    </div>

                    <div class="popupStyle2_body">
                        
                        <div class="popup_videoPlay" id="movieIframe">
                        
                        </div>
                        

                    </div>
                 </form>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->
                
                
                
                <!-- -------------------------------------------------  사용자지정 그룹 영상 검색  ---------------------------------------------------- -->
                <div class="popupStyle1 maxW1440" style="display: none;" id="videoGroupPopup">
                    <form id="gorupPopupfrm" name="gorupPopupfrm" method="post" onsubmit="return false;">
                    <input type="hidden" id="groupPageIndex" name="pageIndex" value="${not empty param.pageIndex ? param.pageIndex:'1'}"/>
                    <input type="hidden" name="recordCountPerPage" value="10"/>
                    <div class="popupStyle1_head">
                        <h1>영상추가</h1>
                        <button type="button" onclick="closeVideoGroupPopup()"><img src="${pageContext.request.contextPath}/images/popupClose.png"></button>
                    </div>

                    <div class="popupStyle1_body">
                        
                        <div class="popupTable_area">
                            <table class="popupListTable1">
                                <colgroup>
                                    <col width="4%">
                                    <col width="4%">
                                    <col width="10%">
                                    <col width="">
                                    <col width="8%">
                                    <col width="6%">
                                    <col width="8%">
                                    <col width="26%">
                                    <col width="8%">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>
                                            <input type="checkbox" id="popupVdoCheckAll" onclick="checkAllCheckbox('popupVdoCheck')"><label for="popupVdoCheckAll"></label>
                                        </th>
                                        <th>번호</th>
                                        <th>전파기관</th>
                                        <th>영상제목</th>
                                        <th>생성일자</th>
                                        <th>확장자</th>
                                        <th>영상길이</th>
                                        <th>주소(좌표)</th>
                                        <th>영상크기</th>
                                    </tr>
                                </thead>
                                <tbody id="groupPopupList"></tbody>
                <script id="groupPopupListTmpl" type="text/x-jquery-tmpl">
                    {{each videoGroupDetailPoupList}}
                        <tr>
                            <td><input type="checkbox" id="popupVdoCheck{{= $index}}" name="popupVdoCheck" value="{{= vdoToken}}" data-shrdvdosn="{{= shrdVdoSn}}" onclick="modifyAllCheckerStatus('popupVdoCheck', 'popupVdoCheckAll')"><label for="popupVdoCheck{{= $index}}"></label></td>
                            <td class="num">{{= (paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + $index))}}</td>
                            <td>{{= cdNm}}
                                {{if userNm}}
                                    <br>({{= userNm}})
                                {{else}}{{/if}}
                            </td>
                            <td>{{= vdoTtl}}</td>
                            <td>{{= regDt}}</td>
                            <td>{{= vdoExtnNm}}</td>
                            <td>{{= vdoPlayTime}}</td>
                            <td>{{if shrdVdoSn}}
                                 {{= sttnNtcAddr}}<br>({{= sttnNtcLot}} / {{= sttnNtcLat}})
                                {{else}}
                                 {{= vdoAddr}}
                                    {{if vdoLot}}
                                        <br>({{= vdoLot}} / {{= vdoLat}})
                                    {{else}}
                                    {{/if}}
                                {{/if}}
                            </td>
                            <td>{{= vdoSz}}MB</td>
                        </tr>
                    {{/each}}
                </script>
                            </table>
                        </div>
                        
                        <div class="popupBtn_area btn_area">
                            <div class="btn_left"></div>
                            <div class="pagenation" id="groupPagenation">
                            </div>
                            <div class="btn_right">
                                <button type="button" class="btn03 btn_blue" onclick="groupAddRow()">추가</button>
                            </div>
                        </div>

                    </div>
                    </form>
                </div>
                <!-- --------------------------------------------------------------------------------------------------------------------------- -->
                
                
            </div>
        </div>
    </body>
</html>