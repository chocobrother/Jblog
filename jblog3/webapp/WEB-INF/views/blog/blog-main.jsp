<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${map.vo.title} 이야기</h1>
			<c:import url ="/WEB-INF/views/includes/blog-header.jsp" />
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${map.postvo.title}</h4>
					<p>
						${map.postvo.content }
						 
					<p>
				</div>
				<c:set var ="cateno" value="${0}" />
				<c:forEach items="${ map.postlist }" var="list" varStatus="status">
				<ul class="blog-list">
					<li><a href="${pageContext.servletContext.contextPath }/blog/main3/${list.post_no}">PostName :${list.title }</a> <span>${list.postdate }</span>	</li>
				</ul>
				</c:forEach>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<!--  <img src="${pageContext.servletContext.contextPath }/assets/images/spring-logo.jpg">-->
				<img src="${pageContext.request.contextPath }${map.vo.image }" style="width:150px"><br>
				
			</div>
		</div>

		
		<div id="navigation">
			<h2>카테고리</h2>
			<c:forEach items="${ map.list }" var="map" varStatus="status">
			<ul>
					<c:set var = "cateno" value = "${map.no}"/>
				<li><a href="${pageContext.servletContext.contextPath }/blog/main2/${map.no}">${map.posttype }</a></li>
				
			</ul>
			</c:forEach>
		</div>
		
		<div id="footer">
			<p>
			<c:import url ="/WEB-INF/views/includes/blog-footer.jsp" />
			</p>
		</div>
	</div>
</body>
</html>