<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL 테스트</title>
</head>
<body>
<c:if test="${empty a}">a</c:if>
<c:if test="${empty b}">b</c:if>
<c:if test="${empty c}">c</c:if>
<c:if test="${empty d}">d</c:if>
<c:if test="${empty e}">e</c:if>

<%--choose--%>
<c:set var="a" value="3"/>
<c:choose>
    <c:when test="${a > 1}">
        a가 1보다 큽니다.
    </c:when>
	<c:when test="${a == 1}">
        a는 1입니다.
    </c:when>
    <c:otherwise>
        a가 1보다 작습니다.
    </c:otherwise>
</c:choose>
<br/>


<%--for--%>
<c:forEach var="i" begin="0" end = "9" step="1">
    i값: ${i}<br/>
</c:forEach>

<%--forEach (Array, List, Set..)--%>
<c:forEach var="s" items="${stringArray}">
    ${s}<br/>
</c:forEach>


<%--forEach (Map)--%>
<c:forEach var="entry" items="${stringMap}">
    ${entry.key}, ${entry.value}<br/>
</c:forEach>


<%--redirect--%>
<%--<c:set var="address" value="g"/>

<c:choose>
    <c:when test="${address == 'g'}">
        <c:redirect url="http://www.google.co.kr"/>
    </c:when>
    <c:when test="${address == 'n'}">
        <c:redirect url="http://www.naver.com"/>
    </c:when>
    <c:otherwise>
        <c:redirect url="http://www.daum.net"/>
    </c:otherwise>
</c:choose>--%>


</body>
</html>
