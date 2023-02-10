<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="/EgovPageLink?link=app/portal/inc/header"/>

<section class="custom_home" style="padding:100px 0; background-image: url(/images/homeImg.gif); background-position: center; background-repeat: no-repeat;background-size: cover;background-attachment:fixed;">
    <div class="custom_top">
        <div class="custom_title">
            <div>
                <h3 class="custom_title_font">맛집 지도</h3>
            </div>

            <div>
                검색 :
                <input type="text" onkeyup="SrchMap(this.value)">
            </div>
        </div>
        <div class="mapArea" id="map" style="height: 90%;"></div>
    </div>
    
</section>

<c:import url="/EgovPageLink?link=app/portal/inc/footer"/>