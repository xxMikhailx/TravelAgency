<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 2/16/2016
  Time: 10:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.text" var="rb" />
<html>
<head>
    <title>${ vacation.name }</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="/js/epamtravel.js"></script>
    <![endif]-->
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="/jsp/header.jsp"%>
<section>
    <span style="text-align: center;"><h1><c:out value="${ vacation.name }" /></h1></span>
    <div class="before-grid">
        <hr>
    </div>
    <div class="tour-header">
        <div class="tour-photo">
            <img src="${vacation.pathImage}" alt="img" class="img-rounded">
        </div>

        <div class="short-description">
            <div class="first-column">
                <p><b><fmt:message key="label.vacation.departureDate" bundle="${rb}" />:</b> <c:out value="${ vacation.departureDate }" /></p>
                <p><b><fmt:message key="label.vacation.destinationCity" bundle="${rb}" />:</b> <c:out value="${ vacation.destinationCity }" /></p>
                <p><b><fmt:message key="label.vacation.hotel" bundle="${rb}" />:</b> <c:out value="${ vacation.hotel }" /></p>
            </div>
            <div class="second-column">
                <p><b><fmt:message key="label.vacation.arrivalDate" bundle="${rb}" />:</b> <c:out value="${ vacation.arrivalDate }" /></p>
                <p><b><fmt:message key="label.vacation.destinationCounty" bundle="${rb}" />:</b> <c:out value="${ vacation.destinationCountry }" /></p>
                <p><b><fmt:message key="label.vacation.transport" bundle="${rb}" />:</b> <c:out value="${ vacation.transport.name}" /></p>
            </div>
            <div class="bottom-column">
                <p><b><fmt:message key="label.vacation.price" bundle="${rb}" />:</b> <c:out value="${ vacation.price }" />$</p>
            </div>
        </div>
    </div>
    <hr>
    <div class="full-description">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#description" data-toggle="tab"><fmt:message key="label.vacation.description" bundle="${rb}" /></a></li>
            <li><a href="#services" data-toggle="tab"><fmt:message key="label.vacation.services" bundle="${rb}" /></a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="description"><pre><c:out value="${ vacation.description}" /></pre></div>
            <div class="tab-pane" id="services"><pre><c:out value="${ vacation.services}" /></pre></div>
        </div>
    </div>

    <div class="after-grid">
        <hr>
        <div class="tour-controls">
            <button type="button" class="btn btn-default" onclick="history.back()"><fmt:message key="label.vacation.button.back" bundle="${rb}" /></button>
            <button type="button" class="btn btn-success" disabled="disabled"><fmt:message key="label.vacation.button.buy" bundle="${rb}" /></button>
        </div>
    </div>
</section>

<%@ include file="/jsp/footer.jsp"%>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/bootstrap.min.js"></script>
</body></html>

