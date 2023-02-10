<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@globalsProperties['Globals.javascript.version']" var="jsVersion"/>

<html>
<head>
    <title>한망울 통합 사이트</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script src="/js/global.js"></script>
    <link rel='stylesheet' href='/css/global.css' type='text/css' media='all'/>
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans%3A300italic%2C400italic%2C700italic%2C300%2C400%2C700%2C800%7CMontserrat%3A300italic%2C400italic%2C700italic%2C300%2C400%2C700%7CDosis%3A400%2C700&#038;ver=4.5' type='text/css' media='all'/>
</head>

<body class="frontpage">
    <div class="page-loader loading_area" style="display: none">
        <img src="/images/loadingImg.svg" alt="loader">
    </div>
        
    <!-- Header ================================================== -->
    <header id="header">
        <input type="hidden" id="contextPath" valud=""/>
        <c:set var="menuPath" value="${fn:split(requestScope['javax.servlet.forward.servlet_path'], '/')}"/>
        <div id="mega-menu" class="header header2 header-sticky primary-menu icons-no default-skin zoomIn align-right">
            <nav class="navbar navbar-default redq">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="http://한망울.메인.한국">
                        <img class="custom_logo" src="/images/HanMangUL.png" alt="logo">
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="navbar">
                    <a class="mobile-menu-close"><i class="fa fa-close"></i></a>
                    <div class="menu-top-menu-container">
                        <ul id="menu-top-menu" class="nav navbar-nav nav-list">
                        </ul>
                        <script id="menuListTmpl" type="text/x-jquery-tmpl">
                            {{each MenuList}}
                                <li><a onclick="setPage('menuFold', 'list')">{{= menuName}}</a></li>
                            {{/each}}
                        </script>
                    </div>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
            </nav>
        </div>
    </header>