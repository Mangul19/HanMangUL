<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:eval expression="@globalsProperties['Globals.javascript.version']" var="jsVersion"/>

<c:import url="/EgovPageLink?link=app/portal/inc/header"/>

<!-- s:container -->
<div class="container">
    <div class="inner">
        <div class="login_container container_center login_bg">
            <div class="loginInner">
                <div class="login_wrap">
                    <div class="login_input_area">
                        <input id="userId" name="userId" type="text" placeholder="아이디">
                        <input id="userPswd" name="userPswd" type="password" placeholder="비밀번호">
                        <button type="button" onclick="loginAction()" class="btnLogin">로그인</button>
                        <button type="button" onclick="regist()" class="btnLogin">회원가입</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!-- e:container -->

<c:import url="/EgovPageLink?link=app/portal/inc/footer"/>