<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
		
	<c:choose>
		<c:when test="${empty authUser}">
		
			<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/blog/main">내블로그</a></li>
		</c:when>
		
		<c:otherwise>
			<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}">내블로그</a></li>		
	</c:otherwise>
	</c:choose>
	
	
</body>
</html>